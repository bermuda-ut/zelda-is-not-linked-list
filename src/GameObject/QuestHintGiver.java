package GameObject;

import Common.Entity;
import Common.NPC;
import Common.Vector2;
import GameData.ItemPresets;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by noxm on 25/09/16.
 */
public class QuestHintGiver extends NPC {

    private static final String NO_AMUL = "Find the Amulet of Vitality, across the river to the west.",
                                NO_SWD = "Find the Sword of Strength - cross the river and back, on the east side.",
                                NO_TOME = "Find the Tome of Agility, in the Land of Shadows.",
                                FOUND_ALL = "You have found all the treasure I know of.";

    public QuestHintGiver(int id, String name, Vector2 pos, Image sprite, int collisionRadius, double speed,
                          int cooldown, int HP, int damage, boolean isBasic, int talkTime) {
        super(id, name, pos, sprite, collisionRadius, speed, cooldown, HP, damage, isBasic, talkTime);
    }

    @Override
    public void innerUpdate(int delta) throws SlickException {

    }

    @Override
    public void handleCollision(Entity[] entities) {
        for (Entity entity : entities) {
            if (entity instanceof Player) {
                Player player = (Player) entity;

                if(!isTalking() && player.isTalking()) {
                    this.startTalking();

                    String line = FOUND_ALL;
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

    @Override
    public void handleDeath() {

    }
}
