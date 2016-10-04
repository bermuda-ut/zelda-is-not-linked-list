package GameObject;

import Common.Entity;
import Common.Vector2;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by noxm on 25/09/16.
 */
public class Item extends Entity {

    private int id;

    public Item(int id, String name, Vector2 pos, Image sprite, int collisionRadius) {
        super(name, pos, sprite, collisionRadius);
        this.id = id;
    }

    @Override
    public void innerUpdate(int delta) throws SlickException {
    }

    @Override
    public void handleCollision(Entity[] entities) {
        for (Entity entity : entities) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                boolean success = player.getInventory().addItem(this);
                if(success)
                    this.destroy();
            }
        }

    }

    // getters
    public int getId() {
        return id;
    }
}
