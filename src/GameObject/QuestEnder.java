package GameObject;

import Common.Entity;
import Common.NPC;
import Common.Vector2;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by noxm on 25/09/16.
 */
public class QuestEnder extends NPC {

    public QuestEnder(int id, String name, Vector2 pos, Image sprite, int collisionRadius, double speed,
                      int cooldown, int HP, int damage, boolean isBasic) {
        super(id, name, pos, sprite, collisionRadius, speed, cooldown, HP, damage, isBasic);
    }

    @Override
    public void innerUpdate(int delta) throws SlickException {

    }

    @Override
    public void handleCollision(Entity[] entities) {

    }

    @Override
    public void handleDeath() {

    }
}
