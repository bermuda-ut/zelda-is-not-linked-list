import GameData.World;
import GameManager.CameraManager;
import GameManager.UIManager;
import GameObject.Player;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import GameManager.InputManager;

/**
 * Main class for the Role-Playing Game engine.
 * Handles initialisation, input and rendering.
 * @author MaxLee
 */
public class RPG extends BasicGame {
    // world will handle all in-game entities
    private World world;

    // manager is not part of the game world (not an entity) and managed differently.
    private InputManager inputManager;

    // player can (ideally) travel across worlds
    private Player player;

    // ui and camera is tied to the game, not world
    private UIManager ui;
    private CameraManager worldCam;

    public static final int SCREEN_WIDTH  = 800;
    public static final int SCREEN_HEIGHT = 600;

    public RPG() {
        super("Zelda_is_not_Link[ed_List].exe");
    }

    /**
     * Initialise the game state.
     * @param gc The Slick game container object.
     */
    @Override
    public void init(GameContainer gc) throws SlickException {
        // input manager is passed on to GameData.World to attach player that is in the world
        player = new Player(Player.PLAYER_STARTING_POS.x, Player.PLAYER_STARTING_POS.y,
                            Player.PLAYER_SPEED, Player.PLAYER_SPRITE);
        inputManager = new InputManager(player, SCREEN_WIDTH, SCREEN_HEIGHT);
        worldCam = new CameraManager(player, RPG.SCREEN_WIDTH, RPG.SCREEN_HEIGHT, true);
        world = new World(player, worldCam);
        ui = new UIManager(player, worldCam);
    }

    /**
     * Update the game state for a frame.
     * @param gc    The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        // player input MUST be prioritized.
        inputManager.update(gc);
        // update world according to it
        world.update(gc, delta);
    }

    /**
     * Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g  The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g) throws SlickException {
        // UI render
        // uiManager.render(g);

        // GameData.World Render
        world.render(g);
        ui.render(g, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    /**
     * Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new RPG());
        app.setShowFPS(true);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }
}
