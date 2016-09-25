package GameObject;

import Common.Character;
import Common.Entity;
import Common.Vector2;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by noxm on 25/09/16.
 */
public class Mob extends Character {
    private int id;
    private boolean isAggro;

    public Mob(int id, String name, Vector2 pos, Image sprite, int collisionRadius, double speed, int cooldown,
               int HP, int damage, boolean isBasic, boolean isAggro) {
        super(name, pos, sprite, collisionRadius, speed, cooldown, HP, damage, isBasic);
        this.id = id;
        this.isAggro = isAggro;
    }

    @Override
    public void update(int delta) throws SlickException {

    }

    @Override
    public void handleDeath() {

    }

    @Override
    public void handleCollision(Entity[] entities) {

    }
}
