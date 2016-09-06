/*
 * Created by Bermuda_UT on 28/08/16.
 * Name      : Max Lee
 * StudentID : 719577
 */

package GameManager;

import GameObject.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

// Handles all inputs by the user
public class InputManager {
    // the only game entity controllable is a Player class
    private Player player;

    // bindings
    private final int UP     = Input.KEY_W,
                    UPALT    = Input.KEY_UP,
                    DOWN     = Input.KEY_S,
                    DOWNALT  = Input.KEY_DOWN,
                    LEFT     = Input.KEY_A,
                    LEFTALT  = Input.KEY_LEFT,
                    RIGHT    = Input.KEY_D,
                    RIGHTALT = Input.KEY_RIGHT,
                    TOGGLEMOVEMENT = Input.KEY_X;

    /**
     * @param player player to controll
     */
    public void attachPlayer(Player player) {
        this.player = player;
    }


    /**
     * update per frame
     * @param gc game container for slick2d
     * @param screenWidth for mouse input translation
     * @param screenHeight for mouse input translation
     * @throws SlickException
     */
    public void update(GameContainer gc, int screenWidth, int screenHeight) throws SlickException {
        Input input = gc.getInput();

        // Movement Management
        float dx = 0, dy = 0;
        if (input.isKeyDown(DOWN) || input.isKeyDown(DOWNALT))
            dy += player.movementAttribute();
        if (input.isKeyDown(UP) || input.isKeyDown(UPALT))
            dy -= player.movementAttribute();
        if (input.isKeyDown(RIGHT) || input.isKeyDown(RIGHTALT))
            dx += player.movementAttribute();
        if (input.isKeyDown(LEFT) || input.isKeyDown(LEFTALT))
            dx -= player.movementAttribute();

        if (player.basicMovement)
            player.getMove().set(dx, dy);
        else
            player.getMove().add(dx, dy);


        // Mouse Input Movement
        if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && !player.basicMovement) {
            double mouseWorldX = input.getMouseX() - screenWidth/2 + player.getPos().x;
            double mouseWorldY = input.getMouseY() - screenHeight/2 + player.getPos().y;
            player.getMove().set(mouseWorldX - player.getPos().x, mouseWorldY - player.getPos().y);
        }


        // Other Inputs
        if (input.isKeyDown(Input.KEY_ESCAPE))
            gc.exit();

        if (input.isKeyPressed(TOGGLEMOVEMENT)) {
            player.basicMovement = !player.basicMovement;
            System.out.println("BasicMovement = " + player.basicMovement);
        }
    }
}
