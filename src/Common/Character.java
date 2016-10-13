package Common;

import GameManager.MapManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * characters are able to move, attack, talk, damage
 * @author MaxLee
 */
public abstract class Character extends Entity {
    // basics
    private final double ACCEL_MODIFIER = 0.90;
    private final double DECEL_MODIFIER = 0.03;
    private final double STOP_SPEED = 0.01;
    private final String BLOCKED = "1";
    private final String NOT_BLOCKED = "";
    private final String TILE_BLOCK = "block";
    private final Color  HP_BAR_COL = new Color(0.8f, 0, 0, 0.8f);

    // movement
    private Vector2 move;
    private boolean basicMovement;
    private double ac;
    private double dc;
    private double speed;

    //stats
    private int cooldown;
    private int currCooldown;
    private int damage;
    private int maxHP;
    private int currHP;

    // helper
    private boolean isAttacking;
    private boolean isTalking;
    private String speech;

    // abstract methods
    @Override
    protected abstract void innerUpdate(int delta) throws SlickException;
    @Override
    protected abstract void handleCollision(Entity[] entities);
    protected abstract void handleDeath();

    /**
     * create character
     * @param name default name
     * @param pos default pos
     * @param sprite default sprite
     * @param collisionRadius default radius
     * @param speed default speed
     * @param cooldown default cooldwon
     * @param HP default HP
     * @param damage default damage
     * @param isBasic default movement method
     */
    public Character(String name, Vector2 pos, Image sprite, int collisionRadius, double speed, int cooldown, int HP,
                     int damage, boolean isBasic) {
        super(name, pos, sprite, collisionRadius);
        this.speed = speed;
        this.cooldown = cooldown;
        this.maxHP = HP;
        this.damage = damage;
        this.basicMovement = isBasic;
        this.speech = "";

        move = new Vector2();
        isAttacking = false;
        currHP = maxHP;
        currCooldown = 0;
        isAttacking = false;
        ac = speed * ACCEL_MODIFIER;
        dc = speed * DECEL_MODIFIER;
    }

    /**
     * make the character talk
     * @param line string to display
     * @param g slick graphics
     */
    public void talk(String line, Graphics g) {
        int height = getSprite().getHeight() + DISPLAY_OFFSET + HALF_FONT_SIZE * 4;
        Vector2 loc = calcBarLoc(line, height);
        Vector2 size = calcBarSize(line);
        Vector2 strLoc = calcString(line, height);

        g.setColor(Color.black);
        g.fillRect((float)loc.x, (float)loc.y, (float)size.x, (float)size.y);
        g.setColor(Color.white);
        g.drawString(line, (float)strLoc.x, (float)strLoc.y);
    }

    /**
     * set current speech
     * @param line string line to make it to speech
     */
    public void setSpeech(String line) {
        speech = line;
    }

    public float getHPpercentage() {
        float perc = (float)getCurrHP()/getMaxHP();
        if(perc > 0f)
            return perc;
        return 0f;
    }

    /**
     * slick update
     * @param delta milliseconds passed since previous frame
     * @throws SlickException
     */
    @Override
    public void update(int delta) throws SlickException {
        if(!isDestroyed()) {
            handleDeath();
            innerUpdate(delta);
            updateCooldown(delta);
        }
    }

    /**
     * function called for display status
     * protected so that it cannot be called from World
     * @param g slick graphics
     */
    @Override
    protected void displayStatus(Graphics g) {
        int height = getSprite().getHeight() + DISPLAY_OFFSET;
        Vector2 size = calcBarSize(getName());
        Vector2 loc = calcBarLoc(getName(), height);
        Vector2 strLoc = calcString(getName(), height);

        g.setColor(Color.black);
        g.fillRect((float)loc.x, (float)loc.y, (float)size.x, (float)size.y);

        g.setColor(HP_BAR_COL);
        g.fillRect((float)loc.x, (float)loc.y, (float)size.x * getHPpercentage(), (float)size.y);

        g.setColor(Color.white);
        g.drawString(getName(), (float)strLoc.x, (float)strLoc.y);

        if(speech.length() > 0) {
            talk(speech, g);
        }
    }

    /**
     * increase damage point
     * @param val value to increase by
     */
    public void addDamagePoint(int val) {
        damage += val;
    }

    /**
     * increase cooldwon
     * @param val value to increase by
     */
    public void addCD(int val) {
        cooldown += val;
        resetCurrCooldown();
    }

    /**
     * increase max hp and hp
     * @param val value to increase by
     */
    public void addHP (int val) {
        maxHP += val;
        currHP += val;
    }

    /**
     * make character get damaged
     * @param dmg damage to apply (maximum damage)
     */
    public void getDamaged(int dmg) {
        // random damage
        currHP -= dmg * Math.random();
    }

    /**
     * update cooldown
     * @param delta milliseconds passed since last frame
     */
    protected void updateCooldown(int delta) {
        currCooldown -= delta;
        currCooldown = (currCooldown < 0) ? 0 : currCooldown;
    }

    /**
     * reset current cooldown to maximum
     */
    public void resetCurrCooldown() {
        currCooldown = cooldown;
    }

    /**
     * handle character movement
     * @param delta milliseconds passed since last frame
     */
    protected void handleMovement(int delta) {
        // move char
        if(moveCheck(delta))
            getPos().add(getMove().multiply(delta));

        // update movement Atr
        if (!isBasicMovement())
            getMove().multiply(getDc());
        else
            getMove().set(0, 0);
    }


    /**
     * Checks whether movement speed is above limit.
     * Basic mode follows the specs and limits x and y
     * Non-Basic mode sets the magnitude of the movement vector
     * @param delta milliseconds passed since last frame
     * @return true if valid, false otherwise
     */
    private boolean moveCheck(int delta) {
        // check if movement is above limit and sets move accordingly
        Vector2 move = this.getMove();
        Vector2 pos = this.getPos();
        double speed = movementAttribute();

        if (isBasicMovement()) {
            if (move.x > 0 && move.y > speed)
                move.x = speed;
            if (move.y > 0 && move.y > speed)
                move.y = speed;
            if (move.x < 0 && move.y < -speed)
                move.x = -speed;
            if (move.y < 0 && move.y < -speed)
                move.y = -speed;
        } else {
            if (move.magnitude() > speed) {
                move.setDistance(speed);
            } else if (move.magnitude() < STOP_SPEED) {
                move.set(0, 0);
                return false;
            }
        }

        // sprite horizontal flipping according to move
        if(move.x != 0)
            setCurrSprite((move.x >= 0) ? getSprite() : getSprite().getFlippedCopy(true, false));

        // now calculate how much you are actually going to move according to frametime
        Vector2 moveTemp = move.multiply(delta);

        // calculate resulting position
        Vector2 temp = pos.copy();
        temp.add(moveTemp);

        // within map?
        if((temp.y < 0 || temp.x < 0) ||
           (temp.y >= MapManager.getMapHeight() || temp.x >= MapManager.getMapWidth()))
            move.set(0, 0);

        // Check if blocked and set slippery walls according to it
        // modifies move vector accordingly
        String blocked = MapManager.getCurrentMap().getTileProperty(temp.x, temp.y, TILE_BLOCK);
        if(blocked.equals(BLOCKED)) {
            if(MapManager.getCurrentMap().getTileProperty(temp.x - moveTemp.x, temp.y, TILE_BLOCK).equals(NOT_BLOCKED))
                move.x = 0;
            else if(MapManager.getCurrentMap().getTileProperty(temp.x, temp.y - moveTemp.y, TILE_BLOCK).equals(NOT_BLOCKED))
                move.y = 0;
            else
                return false;
        }

        return true;
    }

    // getter and setter
    public double movementAttribute() {
        return (basicMovement) ? speed : ac;
    }

    public Vector2 getMove() {
        return this.move;
    }

    public void setMove(Vector2 p) {
        this.move = p;
    }

    public boolean isBasicMovement() {
        return basicMovement;
    }

    public void toggleMovement() {
        this.basicMovement = !basicMovement;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getCurrCooldown() {
        return currCooldown;
    }

    public void setCurrCooldown(int currCooldown) {
        this.currCooldown = currCooldown;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurrHP() {
        return currHP;
    }

    public void setCurrHP(int currHP) {
        this.currHP = currHP;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public double getDc() {
        return dc;
    }

    public boolean isTalking() {
        return isTalking;
    }

    public void setTalking(boolean val) {
        isTalking = val;
    }
}
