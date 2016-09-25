/*
 * Created by Bermuda_UT on 28/08/16.
 * Name      : Max Lee
 * StudentID : 719577
 */

import GameManager.MapManager;
import GameObject.*;
import GameObject.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;

/**
 * Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public class World {
    private static final int ENTITY_LIMIT   = 100;

    private ArrayList<Entity> entities;
    private MapManager   map;

    /**
     * Create a new World object.
     */
    public World(Player player) throws SlickException {
        map = new MapManager("assets/map.tmx", "assets/", player, RPG.SCREEN_WIDTH, RPG.SCREEN_HEIGHT);

        // adding for update and render
        // camera MUST render first therefore added first
        entities = new ArrayList<>();
        addEntity(player);
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
        return false;
    }

    /**
     * Update the game state for a frame.
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(GameContainer gc, int delta) throws SlickException {
        for (int i = 0; i < entities.size(); i++)
            entities.get(i).update(delta);
    }

    /**
     * Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g) throws SlickException {
        // Render all entities
        map.render(g);
        for (int i = 0; i < entities.size(); i++)
            entities.get(i).render(g);
    }
}
