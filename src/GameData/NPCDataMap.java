/**
 * Created by noxm on 22/09/16.
 */
package GameData;

import GameObject.Entity;
import GameObject.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class NPCDataMap {
    /*
    private Method[] talkMapping = new Method[] {
            getClass().getMethod("talkHealer2", GLOBAL_ARGS)
    };

    private Method[] actionMapping = new Method[] {

    };

    public String talkHealer2(Entity npc) {
        return "HI I AM " + npc.toString();
    }

    private String talkHealer(String npc) {
        return "HI I AM " + npc.toString();
    }

    public void actHealer(Entity npc) {
        System.out.print("HI IMMA HEAL YOU");
    }

    //------------------

    private HashMap<Integer, Method> npcTalkMap;
    private HashMap<Integer, Method> npcActMap;
    private Player player;
    private static NPCDataMap self = null;

    private static Class[] GLOBAL_ARGS = {
        Entity.class //invoking NPC
    };


    NPCDataMap(Player player) throws NoSuchMethodException {
        if(self == null){
            this.player = player;
            for(int i = 0; i < actionMapping.length; i++)
                npcTalkMap.put(i, actionMapping[i]);

            self = this;
        }
    }

    public static NPCDataMap getNPCData() {
        return self;
    }

    public HashMap<Integer, Method> getNPCTalkMap() {
        return npcTalkMap;
    }

    public HashMap<Integer, Method> getNPCActMap() {
        return npcActMap;
    }

    //placeholder
    NPCDataMap() throws NoSuchMethodException {

    }
    // example
    public static void main(String[] args) {
        HashMap<Integer, Method> npcTalkMap = new HashMap<>();
        NPCDataMap a;
        try {
            a = new NPCDataMap();
        } catch (NoSuchMethodException e) {
            return;
        }
        Class[] cArg = new Class[1];
        cArg[0] = String.class;

        try {
            npcTalkMap.put(0, a.getClass().getMethod("talkHealer", cArg));
            System.out.print(npcTalkMap.get(0).invoke(a, "asdf"));

        } catch (NoSuchMethodException e) {
            System.out.println("Please declare all required methods");
            e.printStackTrace();

        } catch (InvocationTargetException e) {
            e.printStackTrace();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    */
}
