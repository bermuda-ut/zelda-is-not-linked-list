package Common;

import org.newdawn.slick.Image;

/**
 * Created by noxm on 25/09/16.
 */
public abstract class NPC extends Character {
    private int id;

    public NPC(int id, String name, Vector2 pos, Image sprite, int collisionRadius, double speed, int cooldown,
               int HP, int damage, boolean isBasic) {
        super(name, pos, sprite, collisionRadius, speed, cooldown, HP, damage, isBasic);
        this.id = id;
    }
}
