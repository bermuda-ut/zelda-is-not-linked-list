/*
 * Created by Bermuda_UT on 28/08/16.
 * Name      : Max Lee
 * StudentID : 719577
 */

import Common.Vector2;
import GameManager.InputManager;
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
    private static final Vector2 PLAYER_STARTING_POS = new Vector2(756, 684);
    private static final float PLAYER_SPEED = 0.25f;
    private static final int ENTITY_LIMIT   = 100;

    private ArrayList<Entity> entities;
    private InputManager inputManager;
    private MapManager   map;
    private Player       player;
    private Camera       worldCam;

    /**
     * Create a new World object.
     */
    public World(InputManager inputManager) throws SlickException {
        this.inputManager = inputManager;

        map    = new MapManager("assets/map.tmx", "assets/");
        player = new Player(PLAYER_STARTING_POS.x, PLAYER_STARTING_POS.y, PLAYER_SPEED, "assets/units/player.png");
        worldCam = new Camera(player, RPG.SCREEN_WIDTH, RPG.SCREEN_HEIGHT, true);
        inputManager.attachPlayer(player);

        // adding for update and render
        // camera MUST render first therefore added first
        entities = new ArrayList<>();
        addEntity(worldCam);
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
     * Update current player instance and attach to input manager accordingly
     * @param player new player intance
     */
    public void updatePlayer(Player player) {
        this.player = player;
        this.inputManager.attachPlayer(player);
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
        for (int i = 0; i < entities.size(); i++)
            entities.get(i).render(g);
    }
}
