/*
 * Created by Bermuda_UT on 28/08/16.
 * Name      : Max Lee
 * StudentID : 719577
 */

package GameObject;

import Common.Vector2;
import GameManager.MapManager;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Entity {
    private final float ACCEL_MODIFIER = 0.90f;
    private final float DECEL_MODIFIER = 0.03f;
    private final double STOP_SPEED = 0.01;
    private final String BLOCKED = "1";
    private final String NOT_BLOCKED = "";

    // basic movement = project spec, not basic = what a responsible gamedev should do
    public boolean basicMovement;

    // privates to prevent illegal changes
    private Image sprite, currSprite;
    private float ac, dc, speed;

    /**
     * @param x         starting position x of player
     * @param y         starting position y of player
     * @param speed     speed of the player in basic movement
     * @param spriteDir sprite directory
     * @throws SlickException
     */
    public Player(double x, double y, float speed, String spriteDir) throws SlickException {
        pos    = new Vector2(x, y);
        move   = new Vector2(0, 0);
        sprite = new Image(spriteDir);
        currSprite    = sprite;
        basicMovement = true;

        this.speed = speed;

        // calculate acceleration and deceleration for advanced movement
        this.dc = DECEL_MODIFIER * speed;
        this.ac = ACCEL_MODIFIER * speed;
    }

    // setters
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setAc(float ac) {
        this.ac = ac;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    // getters
    public float getSpeed() {
        return this.speed;
    }

    public float getAc() {
        return this.ac;
    }

    public Image getSprite() {
        return sprite;
    }

    public Vector2 getMove() {
        return move;
    }

    // Advanced Attribute

    /**
     * Return movement speed or acceleration according to the movement mode
     * @return acceleration or speed
     */
    public float movementAttribute() {
        if (basicMovement)
            return speed;
        else
            return ac;
    }

    /**
     * Checks whether movement speed is above limit.
     * Basic mode follows the specs and limits x and y
     * Non-Basic mode sets the magnitude of the movement vector
     */
    private void initMove(int delta) {
        // check if movement is above limit and sets move accordingly
        if (basicMovement) {
            if (move.x > 0 && move.y > speed)
                move.x = speed;
            if (move.y > 0 && move.y > speed)
                move.y = speed;
            if (move.x < 0 && move.y < -speed)
                move.x = -speed;
            if (move.y < 0 && move.y < -speed)
                move.y = -speed;
        } else {
            if (move.magnitude() > speed)
                move.setDistance(speed);
            if (move.magnitude() < STOP_SPEED)
                move.set(0, 0);
        }

        // sprite horizontal flipping according to move
        if(move.x > 0)
            currSprite = sprite;
        else if(move.x < 0)
            currSprite = sprite.getFlippedCopy(true, false);

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
        String blocked = MapManager.getCurrentMap().getTileProperty(temp.x, temp.y);
        if(blocked.equals(BLOCKED)) {
            if(MapManager.getCurrentMap().getTileProperty(temp.x - move.x, temp.y).equals(NOT_BLOCKED))
                move.x = 0;
            else if(MapManager.getCurrentMap().getTileProperty(temp.x, temp.y - move.y).equals(NOT_BLOCKED))
                move.y = 0;
            else
                move.set(0, 0);
        }
    }

    /**
     * Render for slick2d
     * @throws SlickException
     */
    public void render(Graphics a) throws SlickException {
        currSprite.drawCentered((float) pos.x, (float) pos.y);
    }

    /**
     * Update per frame
     * @param delta milliseconds passed since previous frame
     * @throws SlickException
     */
    public void update(int delta) throws SlickException {
        initMove(delta);
        pos.add(move);

        if (!basicMovement)
            move.multiply(dc);
        else
            move.set(0, 0);
    }
}

