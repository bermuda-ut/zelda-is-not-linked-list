package GameData;

import Common.Vector2;
import GameObject.Item;
import org.newdawn.slick.Image;

/**
 * Created by noxm on 25/09/16.
 */
public abstract class ItemPresets {
    private static final String[] NAME_DATA = {
            "Amulet of Vitality",
            "Sword of Strength",
            "Tome of Agility",
            "Elixir of Life"
    };

    private static final String[] SPRITE_DATA = {
            "assets/items/amulet.png",
            "assets/items/sword.png",
            "assets/items/tome.png",
            "assets/items/elixir.png",
    };

    private static final int[] COLL_DATA = {
            50,
            50,
            50,
            50,
    };

    public static void addItemByID(int id, double x, double y, boolean picked, World world) {
        try {
            world.addEntity(new Item(id, NAME_DATA[id], new Vector2(x, y), new Image(SPRITE_DATA[id]), COLL_DATA[id], picked));
        } catch (Exception e) {
            System.out.println("No such item with ID" + id);
            return;
        }
    }

    public static void addAllItems(World world) {
        final Vector2 amulet = new Vector2(965, 3563);
        final Vector2 sword = new Vector2(4791, 1253);
        final Vector2 tome = new Vector2(546, 6707);
        final Vector2 elixir = new Vector2(1976, 402);
        addItemByID(0, amulet.x, amulet.y, false, world);
        addItemByID(1, sword.x, sword.y, false, world);
        addItemByID(2, tome.x, tome.y, false, world);
        addItemByID(3, elixir.x, elixir.y, false, world);
    }
}
