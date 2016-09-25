package GameObject;

import Common.Vector2;
import GameManager.MapManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by noxm on 22/09/16.
 */
public abstract class Character extends Entity {
    private final double ACCEL_MODIFIER = 0.90;
    private final double DECEL_MODIFIER = 0.03;
    private final double STOP_SPEED = 0.01;
    private final String BLOCKED = "1";
    private final String NOT_BLOCKED = "";
    private final String TILE_BLOCK = "block";

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

    // abstract methods
    public abstract void update(int delta) throws SlickException;
    public abstract void handleDeath();
    public abstract void handleCollision(Entity[] entities);

    public Character(String name, Vector2 pos, Image sprite, int collisionRadius, double speed, int cooldown, int HP,
                     int damage, boolean isBasic) {
        super(name, pos, sprite, collisionRadius);
        this.speed = speed;
        this.cooldown = cooldown;
        this.maxHP = HP;
        this.damage = damage;
        this.basicMovement = isBasic;

        move = new Vector2();
        isAttacking = false;
        currHP = maxHP;
        currCooldown = 0;
        isAttacking = false;
        ac = speed * ACCEL_MODIFIER;
        dc = speed * DECEL_MODIFIER;
    }

    private void updateCooldown(int delta) {
        cooldown -= delta;
        cooldown = (cooldown < 0) ? 0 : cooldown;
    }

    public void talk(String line) {

    }

    public void displayStatus() {

    }

    public void getDamaged(int dmg) {
        currHP -= dmg;
    }

    void handleMovement(int delta) {
        // move char
        if(moveCheck(delta))
            getPos().add(getMove());

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
        move.multiply(delta);

        // calculate resulting position
        Vector2 temp = pos.copy();
        temp.add(move);

        // within map?
        if((temp.y < 0 || temp.x < 0) ||
           (temp.y >= MapManager.getMapHeight() || temp.x >= MapManager.getMapWidth()))
            move.set(0, 0);


        // Check if blocked and set slippery walls according to it
        // modifies move vector accordingly
        String blocked = MapManager.getCurrentMap().getTileProperty(temp.x, temp.y, TILE_BLOCK);
        if(blocked.equals(BLOCKED)) {
            if(MapManager.getCurrentMap().getTileProperty(temp.x - move.x, temp.y, TILE_BLOCK).equals(NOT_BLOCKED))
                move.x = 0;
            else if(MapManager.getCurrentMap().getTileProperty(temp.x, temp.y - move.y, TILE_BLOCK).equals(NOT_BLOCKED))
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
}
