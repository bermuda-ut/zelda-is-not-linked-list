package GameObject;

import Common.Entity;
import Common.NPC;
import Common.Vector2;
import GameData.ItemPresets;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by noxm on 25/09/16.
 */
public class QuestEnder extends NPC {
    private static final String NO_ELIX = "Please seek out the Elixir of Life to cure the king.",
                                HAS_ELIX = "The elixir! My father is cured! Thank you!";

    public QuestEnder(int id, String name, Vector2 pos, Image sprite, int collisionRadius, double speed,
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

                    String line = NO_ELIX;
                    if(player.getInventory().hasItem(ItemPresets.ID_ELIX))
                        line = HAS_ELIX;

                    setSpeech(line);
                }
            }
        }
    }

    @Override
    public void handleDeath() {

    }
}
