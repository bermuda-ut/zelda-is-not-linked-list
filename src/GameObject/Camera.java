/*
 * Created by Bermuda_UT on 28/08/16.
 * Name      : Max Lee
 * StudentID : 719577
 */

package GameObject;

import Common.Vector2;
import GameManager.MapManager;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Camera extends Entity {
    public boolean followEntity;
    // render padding is required for smoothing offset
    private final int RENDER_PADDING = 2;
    private Entity toFollow;

    private int cameraWidth, cameraHeight;
    private int cameraWidthTile, cameraHeightTile;

    /**
     * @param toFollow Entity to follow
     * @param cameraWidth width, used to render map appropriately
     * @param cameraHeight screen height, used to render map appropriately
     */
    public Camera(Entity toFollow, int cameraWidth, int cameraHeight, boolean followEntity) {
        this.pos = new Vector2(0,0);
        this.toFollow     = toFollow;
        this.cameraHeight = cameraHeight;
        this.cameraWidth  = cameraWidth;
        this.cameraHeightTile = (int) Math.ceil(cameraHeight / MapManager.getMapTileHeight());
        this.cameraWidthTile  = (int) Math.ceil(cameraWidth  / MapManager.getMapTileWidth());
        this.followEntity     = followEntity;
    }

    /**
     * Render relevant parts of the map and center camera to in-game entity
     * @param g Slick2D Graphics
     * @throws SlickException
     */
    public void render(Graphics g) throws SlickException {
        Vector2 camPos;

        if(followEntity) camPos = toFollow.getPos();
        else camPos = this.pos;

        double tileX = camPos.x - cameraWidth / 2,
               tileY = camPos.y - cameraHeight / 2;

        // Render the map
        // Assert = map exists.
        MapManager.getCurrentMap().render(tileX, tileY, cameraWidthTile, cameraHeightTile, RENDER_PADDING);

        // Center to object (camera's job)
        g.translate((float) -tileX, (float) -tileY);
    }

    public void update(int delta) {

    }
}