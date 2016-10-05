package GameObject;

import Common.Entity;
import Common.NPC;
import Common.Vector2;
import GameData.ItemPresets;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * npc that gives players tips in game
 * @author MaxLee
 */
public class QuestHintGiver extends NPC {

    private static final String NO_AMUL = "Find the Amulet of Vitality, across the river to the west.",
                                NO_SWD = "Find the Sword of Strength - cross the river and back, on the east side.",
                                NO_TOME = "Find the Tome of Agility, in the Land of Shadows.",
                                FOUND_ALL = "You have found all the treasure I know of.";

    /**
     * @param id npc id
     * @param name default name
     * @param pos default pos
     * @param sprite default sprite
     * @param collisionRadius default radius
     * @param speed default speed
     * @param cooldown defaualt cooldown
     * @param HP default hp
     * @param damage default damage
     * @param isBasic default movement mode
     * @param talkTime default talktime
     */
    public QuestHintGiver(int id, String name, Vector2 pos, Image sprite, int collisionRadius, double speed,
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
     * handle collsion
     * @param entities entities collided with
     */
    @Override
    public void handleCollision(Entity[] entities) {
        for (Entity entity : entities) {
            if (entity instanceof Player) {
                Player player = (Player) entity;

                if(!isTalking() && player.isTalking()) {
                    this.startTalking();

                    String line = FOUND_ALL;

                    // say according to situation
                    if(!player.getInventory().hasItem(ItemPresets.ID_AMULET))
                        line = NO_AMUL;
                    else if(!player.getInventory().hasItem(ItemPresets.ID_SWORD))
                        line = NO_SWD;
                    else if(!player.getInventory().hasItem(ItemPresets.ID_TOME))
                        line = NO_TOME;

                    setSpeech(line);
                }
            }
        }
    }
}
