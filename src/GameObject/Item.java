package GameObject;

import Common.Entity;
import Common.Vector2;
import GameData.ItemPresets;
import org.lwjgl.Sys;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * pick-uppable items for the player in game
 * @author MaxLee
 */
public class Item extends Entity {
    // id identifies item
    private int id;

    /**
     * @param id item id
     * @param name default name
     * @param pos default position
     * @param sprite default sprite
     * @param collisionRadius default collision radius
     */
    public Item(int id, String name, Vector2 pos, Image sprite, int collisionRadius) {
        super(name, pos, sprite, collisionRadius);
        this.id = id;
    }

    /**
     * no inner update
     */
    @Override
    protected void innerUpdate(int delta) throws SlickException {

    }

    /**
     * handle collision
     * @param entities entities collided
     */
    @Override
    protected void handleCollision(Entity[] entities) {
        for (Entity entity : entities) {
            if (entity instanceof Player) {
                Player player = (Player) entity;

                // will only be 'removed from world' if player inventory is not full
                boolean success = player.getInventory().addItem(this);

                if(success) {
                    // update and check attribute change
                    boolean attAdded = ItemPresets.applyItemEffect(player, this);
                    if(!attAdded)
                        System.out.println("No attribute effect set for item ID: " + this.getId());

                    this.destroy();
                }
            }
        }

    }

    // getters
    public int getId() {
        return id;
    }
}
