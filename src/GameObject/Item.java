package GameObject;

import Common.Entity;
import Common.Vector2;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by noxm on 25/09/16.
 */
public class Item extends Entity {

    private int id;
    private boolean pickedUp;

    public Item(int id, String name, Vector2 pos, Image sprite, int collisionRadius, boolean isPickedUp) {
        super(name, pos, sprite, collisionRadius);
        this.id = id;
        this.pickedUp = isPickedUp;
    }

    @Override
    public void innerUpdate(int delta) throws SlickException {
    }

    @Override
    public void handleCollision(Entity[] entities) {

    }

    // getters
    public int getId() {
        return id;
    }
}
