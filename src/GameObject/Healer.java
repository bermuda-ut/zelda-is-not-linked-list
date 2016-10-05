package GameObject;

import Common.Entity;
import Common.NPC;
import Common.Vector2;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * healer npc in game
 * @author MaxLee
 */
public class Healer extends NPC {
    // talk lines
    private final String GAVE_HEAL = "You look much healthier now.",
                         NO_HEAL = "Return to me if you ever need healing.";

    private float healPercentage;

    /**
     * @param id healer npc id
     * @param name default name
     * @param pos deault pos
     * @param sprite default sprite
     * @param collisionRadius default radius
     * @param speed default speed
     * @param cooldown default cooldown
     * @param HP default hp
     * @param damage default damage
     * @param healPercentage default heal percentage (decimal)
     * @param isBasic default movement mode
     * @param talkTime default talk time
     */
    public Healer(int id, String name, Vector2 pos, Image sprite, int collisionRadius, double speed,
                  int cooldown, int HP, int damage, float healPercentage, boolean isBasic, int talkTime) {
        super(id, name, pos, sprite, collisionRadius, speed, cooldown, HP, damage, isBasic, talkTime);
        this.healPercentage = healPercentage;
    }

    /**
     * no innner update
     */
    @Override
    protected void innerUpdate(int delta) throws SlickException {

    }

    /**
     * handle collision
     * @param entities entities to consider
     */
    @Override
    protected void handleCollision(Entity[] entities) {
        for (Entity entity : entities) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                if(!isTalking() && player.isTalking()) {
                    this.startTalking();

                    if(player.getCurrHP() < player.getMaxHP()) {
                        player.setCurrHP((int) (player.getMaxHP() * healPercentage));
                        setSpeech(GAVE_HEAL);
                    } else {
                        setSpeech(NO_HEAL);
                    }
                }
            }
        }
    }

}
