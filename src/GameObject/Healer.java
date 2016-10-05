package GameObject;

import Common.Entity;
import Common.NPC;
import Common.Vector2;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by noxm on 25/09/16.
 */
public class Healer extends NPC {
    private float healPercentage;
    private final String GAVE_HEAL = "You look much healthier now.",
                         NO_HEAL = "Return to me if you ever need healing.";

    public Healer(int id, String name, Vector2 pos, Image sprite, int collisionRadius, double speed,
                  int cooldown, int HP, int damage, float healPercentage, boolean isBasic, int talkTime) {
        super(id, name, pos, sprite, collisionRadius, speed, cooldown, HP, damage, isBasic, talkTime);
        this.healPercentage = healPercentage;
    }

    @Override
    protected void innerUpdate(int delta) throws SlickException {

    }

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
