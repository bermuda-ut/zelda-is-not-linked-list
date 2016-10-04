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
    private int screenWidth;
    private int screenHeight;

    // bindings
    private final int UP     = Input.KEY_W,
                    DOWN     = Input.KEY_S,
                    LEFT     = Input.KEY_A,
                    RIGHT    = Input.KEY_D,
                    TOGGLEMOVEMENT = Input.KEY_X,
                    ATTACK = Input.KEY_J,
                    TALK = Input.KEY_K;

    public InputManager(Player player, int screenWidth, int screenHeight) {
        this.player = player;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    private void handleMouseMovement(Input input) {
        if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && !player.isBasicMovement()) {
            double mouseWorldX = input.getMouseX() - screenWidth/2 + player.getPos().x;
            double mouseWorldY = input.getMouseY() - screenHeight/2 + player.getPos().y;
            player.getMove().set(mouseWorldX - player.getPos().x, mouseWorldY - player.getPos().y);
        }
    }

    private void handleKeyboardMovement(Input input) {
        // Movement Management
        float dx = 0, dy = 0;
        if (input.isKeyDown(DOWN))
            dy += player.movementAttribute();
        if (input.isKeyDown(UP))
            dy -= player.movementAttribute();
        if (input.isKeyDown(RIGHT))
            dx += player.movementAttribute();
        if (input.isKeyDown(LEFT))
            dx -= player.movementAttribute();

        if (player.isBasicMovement())
            player.getMove().set(dx, dy);
        else
            player.getMove().add(dx, dy);
    }

    private void handleAttack(Input input) {
        if(input.isKeyDown(ATTACK)) {
            if(player.getCurrCooldown() == 0) {
                player.setAttacking(true);
                player.resetCurrCooldown();
                return;
            }
        }

        player.setAttacking(false);
    }
    /**
     * update per frame
     * @param gc game container for slick2d
     * @throws SlickException
     */
    public void update(GameContainer gc) throws SlickException {
        Input input = gc.getInput();

        handleKeyboardMovement(input);
        handleMouseMovement(input);
        handleAttack(input);

        // Other minor Inputs
        if (input.isKeyDown(Input.KEY_ESCAPE))
            gc.exit();

        if (input.isKeyPressed(TOGGLEMOVEMENT)) {
            player.toggleMovement();
            System.out.println("BasicMovement = " + player.isBasicMovement());
        }
    }
}
