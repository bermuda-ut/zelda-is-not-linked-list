/*
 * Created by Bermuda_UT on 28/08/16.
 * Name      : Max Lee
 * StudentID : 719577
 */

package GameManager;

import Common.Vector2;
import Common.Entity;

public class CameraManager {
    public boolean followEntity;
    // render padding is required for smoothing offset
    public final int RENDER_PADDING = 2;

    private Entity toFollow;
    private Vector2 pos;
    private int cameraWidth, cameraHeight;
    //private int cameraWidthTile, cameraHeightTile;

    /**
     * @param toFollow Entity to follow
     * @param cameraWidth width, used to render map appropriately
     * @param cameraHeight screen height, used to render map appropriately
     */
    public CameraManager(Entity toFollow, int cameraWidth, int cameraHeight, boolean followEntity) {
        this.pos = new Vector2();
        this.toFollow     = toFollow;
        this.cameraHeight = cameraHeight;
        this.cameraWidth  = cameraWidth;
        this.followEntity     = followEntity;
    }

    /**
     * calculate render position
     * @return tile position to render for the mab
     */
    public Vector2 getRenderPos() {
        Vector2 camPos = (followEntity) ? toFollow.getPos() : pos;

        double tileX = camPos.x - cameraWidth / 2,
               tileY = camPos.y - cameraHeight / 2;

        return new Vector2(tileX, tileY);
    }

    // getters and setters

    public int getCameraWidth() {
        return cameraWidth;
    }

    public int getCameraHeight() {
        return cameraHeight;
    }
}
