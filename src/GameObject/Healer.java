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
    float healPercentage;

    public Healer(int id, String name, Vector2 pos, Image sprite, int collisionRadius, double speed,
                  int cooldown, int HP, int damage, float healPercentage, boolean isBasic) {
        super(id, name, pos, sprite, collisionRadius, speed, cooldown, HP, damage, isBasic);
        this.healPercentage = healPercentage;
    }

    @Override
    public void innerUpdate(int delta) throws SlickException {
        setSpeech("");
    }

    @Override
    public void handleCollision(Entity[] entities) {
        for (Entity entity : entities) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                player.setCurrHP((int) (player.getMaxHP() * healPercentage));
                setSpeech("please fuck off");
            }
        }
    }

    @Override
    public void handleDeath() {

    }
}
