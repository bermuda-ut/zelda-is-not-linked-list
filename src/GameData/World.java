package GameData;

import GameManager.CameraManager;
import GameManager.MapManager;
import GameObject.*;
import Common.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;

/**
 * Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 * @author Ma Lee
 */
public class World {
    private static final int ENTITY_LIMIT = 10000;
    private static final String MAP_FILE = "assets/map.tmx";
    private static final String MAP_DIR = "assets/";

    private ArrayList<Entity> entities;
    private MapManager map;

    /**
     * Create a new GameData.World object.
     * @param player player to use
     * @param cam camera to use
     * @throws SlickException
     */
    public World(Player player, CameraManager cam) throws SlickException {
        map = new MapManager(MAP_FILE, MAP_DIR, cam);

        // adding for update and render
        // camera MUST render first therefore added first
        entities = new ArrayList<>();
        addEntity(player);
        MobPresets.addAllMobs(this);
        ItemPresets.addAllItems(this);
        NPCPresets.addAllNPC(this);
    }

    /**
     * adds entity and checks if adding the entity was successful
     * @param entity entity to add
     * @return true if successful, false if failed
     */
    public boolean addEntity(Entity entity) {
        if(entities.size() < ENTITY_LIMIT) {
            entities.add(entity);
            return true;
        }
        System.out.println("Entity Limit exceeded!");
        return false;
    }

    /**
     * Update the game state for a frame.
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(GameContainer gc, int delta) throws SlickException {
        // update player
        Player player = (Player)entities.get(0);
        player.update(delta);

        for (int i = 1; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entity.update(delta);
            if(entity.hasCollided(player))
                entity.doCollision(new Entity[] {player});
        }
    }

    /**
     * Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g) throws SlickException {
        // Render all entities
        map.render(g);
        Player player = (Player)entities.get(0);

        // render entities
        for (int i = 1; i < entities.size(); i++) {
            entities.get(i).render(g);
        }

        // render player
        player.render(g);

        // render status
        for (int i = 1; i < entities.size(); i++)
            entities.get(i).renderStatus(g);
    }
}
