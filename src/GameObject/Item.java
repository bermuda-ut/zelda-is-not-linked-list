package GameObject;

import Common.Entity;
import Common.Vector2;
import GameData.ItemPresets;
import org.lwjgl.Sys;
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
                if(success) {
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
