/*
 * Created by Bermuda_UT on 28/08/16.
 * Name      : Max Lee
 * StudentID : 719577
 */

package GameObject;

import Common.Character;
import Common.Entity;
import Common.Vector2;
import PlayerData.Inventory;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Character {
    // for testing, you can continue as negative health in G0dM0d3
    public static final boolean GOD_MODE = false;

    public static final Vector2 PLAYER_STARTING_POS = new Vector2(756, 684);
    public static final float PLAYER_SPEED = 0.25f;
    public static final String PLAYER_SPRITE = "assets/units/player.png";

    private static final int START_HP = 100;
    private static final int START_DMG = 24;
    private static final int START_CD = 600;
    private static final int START_COLL_RAD = 5;
    private static final int INVENTORY_SIZE = 4;
    private static final String START_NAME = "Link[ed]List";
    private static Player currPlayer;

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
        if(currPlayer == null) {
            inventory = new Inventory(INVENTORY_SIZE);
            currPlayer = this;
        }
    }

    @Override
    protected void displayStatus(Graphics g) {
        // no status to show, all shown in UI
    }


    /**
     * return current player
     * @return current player object
     */
    public static Player getCurrPlayer() {
        return currPlayer;
    }

    /**
     * Update per frame
     * @param delta milliseconds passed since previous frame
     * @throws SlickException
     */
    @Override
    protected void innerUpdate(int delta) throws SlickException {
        handleMovement(delta);
    }

    /**
     * handle collision
     * @param entities list of colliding entities
     */
    @Override
    protected void handleCollision(Entity[] entities) {
        // do nothing
    }

    @Override
    public void handleDeath() {
        if(getCurrHP() < 0 && !GOD_MODE) {
            currPlayer.setPos(PLAYER_STARTING_POS);
            setCurrHP(getMaxHP());
        }
    }

    // getters
    public Inventory getInventory() {
        return inventory;
    }

}

