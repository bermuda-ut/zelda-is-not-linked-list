package GameData;

import Common.NPC;
import Common.Vector2;
import GameObject.Healer;
import GameObject.QuestEnder;
import GameObject.QuestHintGiver;
import org.newdawn.slick.Image;

/**
 * Created by noxm on 25/09/16.
 */
public abstract class NPCPresets {
    private static final int ROLE_HEALER = 0;
    private static final int ROLE_QUEST_HINTER = 1;
    private static final int ROLE_QUEST_ENDER = 2;

    private static final float[] HEAL_DATA = {
            100
    };

    private static final String[] NAME_DATA = {
            "Elvira",
            "Garth",
            "Prince Aldric",
    };

    private static final String[] SPRITE_DATA = {
            "assets/units/shaman.png",
            "assets/units/peasant.png",
            "assets/units/prince.png",
    };

    private static final int[] COLL_DATA = {
            50,
            50,
            50,
    };

    private static final int[] SPEED_DATA = {
            0,
            0,
            0,
    };

    private static final int[] CD_DATA = {
            0,
            0,
            0,
    };

    private static final int[] HP_DATA = {
            1,
            1,
            1,
    };

    private static final int[] DMG_DATA = {
            0,
            0,
            0,
    };

    public static void addNPCbyID(int id, int role, double x, double y, World world) {
        try {
            NPC toAdd;
            if(role == ROLE_HEALER) {
                toAdd = new Healer(id, NAME_DATA[id], new Vector2(x, y), new Image(SPRITE_DATA[id]), COLL_DATA[id],
                        SPEED_DATA[id], CD_DATA[id], HP_DATA[id], DMG_DATA[id], HEAL_DATA[id], false);

            } else if(role == ROLE_QUEST_HINTER) {
                toAdd = new QuestHintGiver(id, NAME_DATA[id], new Vector2(x, y), new Image(SPRITE_DATA[id]),
                        COLL_DATA[id], SPEED_DATA[id], CD_DATA[id], HP_DATA[id], DMG_DATA[id], false);

            } else if(role == ROLE_QUEST_ENDER) {
                toAdd = new QuestEnder(id, NAME_DATA[id], new Vector2(x, y), new Image(SPRITE_DATA[id]), COLL_DATA[id],
                        SPEED_DATA[id], CD_DATA[id], HP_DATA[id], DMG_DATA[id], false);

            } else {
                System.out.println("No such role.");
                return;

            }
            world.addEntity(toAdd);

        } catch (Exception e) {
            System.out.println("No such NPC with ID" + id);
            return;
        }
    }

    public static void addAllNPC(World world) {
        final Vector2 HEALER_POS = new Vector2(738, 549);
        final Vector2 QHINT_POS = new Vector2(756, 870);
        final Vector2 QEND_POS = new Vector2(467, 679);

        addNPCbyID(0, ROLE_HEALER, HEALER_POS.x, HEALER_POS.y, world);
        addNPCbyID(1, ROLE_QUEST_HINTER, QHINT_POS.x, QHINT_POS.y, world);
        addNPCbyID(2, ROLE_QUEST_ENDER, QEND_POS.x, QEND_POS.y, world);
    }
}
