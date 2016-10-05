package GameObject;

import Common.Entity;
import Common.NPC;
import Common.Vector2;
import GameData.ItemPresets;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * quest give/ender
 * @author MaxLee
 */
public class QuestEnder extends NPC {
    // talk lines
    private static final String NO_ELIX = "Please seek out the Elixir of Life to cure the king.",
                                HAS_ELIX = "The elixir! My father is cured! Thank you!";

    /**
     * @param id npc id
     * @param name default name
     * @param pos default pos
     * @param sprite default sprite
     * @param collisionRadius default radius
     * @param speed default speed
     * @param cooldown default cooldown
     * @param HP default hp
     * @param damage default damage
     * @param isBasic default movement mode
     * @param talkTime default talk time
     */
    public QuestEnder(int id, String name, Vector2 pos, Image sprite, int collisionRadius, double speed,
                      int cooldown, int HP, int damage, boolean isBasic, int talkTime) {
        super(id, name, pos, sprite, collisionRadius, speed, cooldown, HP, damage, isBasic, talkTime);
    }

    /**
     * no inner update
     */
    @Override
    public void innerUpdate(int delta) throws SlickException {

    }

    /**
     * handle collision
     * @param entities collided entities
     */
    @Override
    public void handleCollision(Entity[] entities) {
        for (Entity entity : entities) {
            if (entity instanceof Player) {
                Player player = (Player) entity;

                if(!isTalking() && player.isTalking()) {
                    this.startTalking();

                    String line = NO_ELIX;
                    if(player.getInventory().hasItem(ItemPresets.ID_ELIX))
                        line = HAS_ELIX;

                    setSpeech(line);
                }
            }
        }
    }

    /**
     * this guy does not die
     */
    @Override
    public void handleDeath() {

    }
}
