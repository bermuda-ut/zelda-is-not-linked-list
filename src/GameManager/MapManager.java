/*
 * Created by Bermuda_UT on 28/08/16.
 * Name      : Max Lee
 * StudentID : 719577
 */

package GameManager;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

// Handles map io and operations
public class MapManager {
    // goes with world, expected to be instantiated ONCE.
    // private static with public getter to give access to map info to all classes without passing around reference.
    private static MapManager currMap;
    private static int mapWidth, mapHeight, mapTileWidth, mapTileHeight;

    private TiledMap map;

    public MapManager(String ref, String loc) throws SlickException {
        map = new TiledMap(ref, loc);
        mapWidth  = map.getWidth() * map.getTileWidth();
        mapHeight = map.getHeight() * map.getTileHeight();
        mapTileWidth  = map.getTileWidth();
        mapTileHeight = map.getTileHeight();
        currMap = this;
    }

    /**
     * render map
     * @param tileX starting tile-x
     * @param tileY starting tile-y
     * @param screenWidthTile number of tiles for width
     * @param screenHeightTile number of tiles for height
     * @param padding padding to consider
     * @throws SlickException
     */
    public void render(double tileX, double tileY, int screenWidthTile, int screenHeightTile, int padding) throws SlickException {
        // tile index to start rendering
        int tileIndexX = (int) tileX / mapTileWidth,
            tileIndexY = (int) tileY / mapTileHeight;
        // offset amount to spawn in the tileset relative to index
        int tileOffsetX = (int) tileX % mapTileWidth,
            tileOffsetY = (int) tileY % mapTileHeight;

        // Render the tileset
        map.render(-tileOffsetX, -tileOffsetY,
                tileIndexX, tileIndexY,
                screenWidthTile + padding, screenHeightTile + padding);
    }


    // getters

    public static MapManager getCurrentMap() {
        return currMap;
    }

    public static int getMapTileWidth() {
        return mapTileWidth;
    }

    public static int getMapTileHeight() {
        return mapTileHeight;
    }

    public static int getMapWidth() {
        return mapWidth;
    }

    public static int getMapHeight() {
        return mapHeight;
    }

    public int getTileId(double x, double y) {
        return map.getTileId((int) x / mapTileWidth, (int) y / mapTileHeight, 0);
    }

    /**
     * get map tile property at x,y coordinate
     * @param x x-coordinate
     * @param y y-coordinate
     * @param property property name to get
     * @return result string
     */
    public String getTileProperty(double x, double y, String property) {
        // handle limits
        if(x < 0) x = 0;
        else if(x >= mapWidth) x = mapWidth-1;

        if(y < 0) y = 0;
        else if(y >= mapWidth) y = mapWidth-1;

        return map.getTileProperty(map.getTileId((int) x / mapTileWidth, (int) y / mapTileHeight, 0), property, "");
    }
}
