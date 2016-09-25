package GameManager;

import Common.Vector2;
import GameObject.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 */
public class UIManager {
    public static final int PANEL_HEIGHT = 70;
    public static final String PANEL_DIR = "assets/panel.png";
    CameraManager worldCam;
    Player player;
    Image panel;

    public UIManager(Player player, CameraManager worldCam) {
        try {
            panel = new Image(PANEL_DIR);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        this.worldCam = worldCam;
        this.player = player;
    }


    public void render(Graphics g, int screenwidth, int screenheight) throws SlickException{
        Vector2 tile = worldCam.getRenderPos();
        g.translate((float) tile.x, (float) tile.y);

        // Panel colours
        Color LABEL = new Color(0.9f, 0.9f, 0.4f);          // Gold
        Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
        Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
        Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f);      // Red, transp

        // Variables for layout
        String text;                // Text to display
        int text_x, text_y;         // Coordinates to draw text
        int bar_x, bar_y;           // Coordinates to draw rectangles
        int bar_width, bar_height;  // Size of rectangle to draw
        int hp_bar_width;           // Size of red (HP) rectangle
        int inv_x, inv_y;           // Coordinates to draw inventory item

        float health_percent;       // Player's health, as a percentage

        // Panel background image
        panel.draw(0, screenheight - PANEL_HEIGHT);

        // Display the player's health
        text_x = 15;
        text_y = screenheight - PANEL_HEIGHT + 25;
        g.setColor(LABEL);
        g.drawString("Health:", text_x, text_y);
        text = "??/??";                                 // TODO: HP / Max-HP

        bar_x = 90;
        bar_y = screenheight - PANEL_HEIGHT + 20;
        bar_width = 90;
        bar_height = 30;
        health_percent = 0.75f;                         // TODO: HP / Max-HP
        hp_bar_width = (int) (bar_width * health_percent);
        text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        g.setColor(BAR);
        g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's damage and cooldown
        text_x = 200;
        g.setColor(LABEL);
        g.drawString("Damage:", text_x, text_y);
        text_x += 80;
        text = "??";                                    // TODO: Damage
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);
        text_x += 40;
        g.setColor(LABEL);
        g.drawString("Rate:", text_x, text_y);
        text_x += 55;
        text = "??";                                    // TODO: Cooldown
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's inventory
        g.setColor(LABEL);
        g.drawString("Items:", 420, text_y);
        bar_x = 490;
        bar_y = screenheight - PANEL_HEIGHT + 10;
        bar_width = 288;
        bar_height = bar_height + 20;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);

        inv_x = 490;
        inv_y = screenheight - PANEL_HEIGHT + ((PANEL_HEIGHT - 72) / 2);
        // for (each item in the player's inventory)                // TODO
        {
            // Render the item to (inv_x, inv_y)
            inv_x += 72;
        }

    }
}
