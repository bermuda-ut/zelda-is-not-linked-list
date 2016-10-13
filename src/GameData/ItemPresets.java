package GameData;

import Common.Vector2;
import GameObject.Item;
import GameObject.Player;
import org.newdawn.slick.Image;

/**
 * handle item behaviour and identities
 * also has methods to mass-add items to world
 * @author MaxLee
 */
public abstract class ItemPresets {

    // bunch of constants

    public static final int ID_AMULET = 0,
                            ID_SWORD = 1,
                            ID_TOME = 2,
                            ID_ELIX = 3;

    private static final int[] ITEM_EFFECT_VAL = {
            80,
            30,
            -300,
            0,
    };

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

    /**
     * apply item effect to player
     * @param player player to consider
     * @param item item to consider
     * @return
     */
    public static boolean applyItemEffect(Player player, Item item) {
        if(item.getId() == ID_AMULET) {
            player.addHP(ITEM_EFFECT_VAL[ID_AMULET]);
        } else if (item.getId() == ID_SWORD) {
            player.addDamagePoint(ITEM_EFFECT_VAL[ID_SWORD]);
        } else if (item.getId() == ID_TOME) {
            player.addCD(ITEM_EFFECT_VAL[ID_TOME]);
        } else if (item.getId() == ID_ELIX) {
            // no effect
        } else {
            return false;
        }

        return true;
    }

    /**
     * add item to the world
     * @param id id of the item
     * @param x x-coord
     * @param y y-coord
     * @param world world to add to
     */
    public static void addItemByID(int id, double x, double y, World world) {
        try {
            world.addEntity(new Item(id, NAME_DATA[id], new Vector2(x, y), new Image(SPRITE_DATA[id]), COLL_DATA[id]));
        } catch (Exception e) {
            System.out.println("No such item with ID" + id);
        }
    }

    /**
     * mass add items to the world
     * @param world world to add to
     */
    public static void addAllItems(World world) {
        // these constants are independent from the class and only relates to this function therefore they are declared
        // here rather than with class attributes.
        final Vector2 AMULET_POS = new Vector2(965, 3563);
        final Vector2 SWORD_POS = new Vector2(546, 6707);
        final Vector2 TOME_POS = new Vector2(4719, 1253);
        final Vector2 ELIX_POS = new Vector2(1976, 402);

        addItemByID(ID_AMULET, AMULET_POS.x, AMULET_POS.y, world);
        addItemByID(ID_SWORD, SWORD_POS.x, SWORD_POS.y, world);
        addItemByID(ID_TOME, TOME_POS.x, TOME_POS.y, world);
        addItemByID(ID_ELIX, ELIX_POS.x, ELIX_POS.y, world);

        // uncomment this if you want items near spawn. Left for your marking convenience, please do not mark this.
        /*
        Vector2 p = new Vector2(756, 684);
        addItemByID(0, p.x + 100, p.y + 100, world);
        addItemByID(1, p.x, p.y + 100, world);
        addItemByID(2, p.x+100, p.y, world);
        addItemByID(3, p.x-100, p.y, world);
        */
    }
}
