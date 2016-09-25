/*
 * Created by Bermuda_UT on 28/08/16.
 * Name      : Max Lee
 * StudentID : 719577
 */

package GameObject;

import Common.Character;
import Common.Entity;
import Common.Vector2;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tests.xml.Inventory;

public class Player extends Character {
    private static final int START_HP = 10;
    private static final int START_DMG = 10;
    private static final int START_CD = 10;
    private static final int START_COLL_RAD = 5;
    private static final String START_NAME = "Link[ed]List";

    private Inventory inventory;

    /**
     * @param x         starting position x of player
     * @param y         starting position y of player
     * @param speed     speed of the player in basic movement
     * @param spriteDir sprite directory
     * @throws SlickException
     */

    public Player(double x, double y, float speed, String spriteDir) throws SlickException {
        super(START_NAME, new Vector2(x, y), new Image(spriteDir), START_COLL_RAD, speed,
              START_CD, START_HP, START_DMG, true);
        inventory = new Inventory();
    }


    /**
     * Update per frame
     * @param delta milliseconds passed since previous frame
     * @throws SlickException
     */
    @Override
    public void update(int delta) throws SlickException {
        this.handleMovement(delta);
    }

    @Override
    public void handleCollision(Entity[] entities) {

    }

    @Override
    public void handleDeath() {

    }

    // getters
    public Inventory getInventory() {
        return inventory;
    }
}

