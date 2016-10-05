package Common;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * non-player controlled friendly units in game
 * able to talk
 * @author MaxLee
 */
public abstract class NPC extends Character {
    private int id;
    private int talkTime;
    private int currTalkTime;

    /**
     * create new npc
     * @param id npc id
     * @param name default name
     * @param pos default position
     * @param sprite default sprite
     * @param collisionRadius default collision radius
     * @param speed default speed
     * @param cooldown default cooldown
     * @param HP default hp
     * @param damage default damage
     * @param isBasic default movement type
     * @param talkTime default talk time
     */
    public NPC(int id, String name, Vector2 pos, Image sprite, int collisionRadius, double speed, int cooldown,
               int HP, int damage, boolean isBasic, int talkTime) {
        super(name, pos, sprite, collisionRadius, speed, cooldown, HP, damage, isBasic);
        this.id = id;
        this.talkTime = talkTime;
        this.currTalkTime = 0;
    }

    /**
     * initiator before talking
     */
    public void startTalking() {
        currTalkTime = talkTime;
        setTalking(true);
    }

    /**
     * handle talk duration (like cooldown)
     * @param delta ms passed since last frame
     */
    public void handleTalkDuration(int delta) {
        currTalkTime -= delta;
        currTalkTime = (currTalkTime < 0)? 0 : currTalkTime;

        if(currTalkTime == 0) {
            setSpeech("");
            setTalking(false);
        }
    }

    /**
     * handle death from world
     */
    @Override
    protected void handleDeath() {
        if(this.getCurrHP() <= 0) {
            // the dead does not speak.
            setSpeech("");
            this.destroy();
        }
    }

    /**
     * slick update
     * @param delta milliseconds passed since previous frame
     * @throws SlickException
     */
    @Override
    public void update(int delta) throws SlickException {
        if (!isDestroyed()) {
            handleDeath();
            handleTalkDuration(delta);
            innerUpdate(delta);
            updateCooldown(delta);
        }
    }
}
