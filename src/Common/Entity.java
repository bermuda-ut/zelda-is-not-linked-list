/*
 * Created by Bermuda_UT on 28/08/16.
 * Name      : Max Lee
 * StudentID : 719577
 */

package Common;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Parent class for all in-game characters (player, NPC etc)
 */
public abstract class Entity implements Collidable{
    public final int DISPLAY_OFFSET = 10;
    public final  int HALF_FONT_SIZE = 9;
    private Vector2 pos;
    private int collisionRadius;
    private String name;
    private Image sprite;
    private Image currSprite;
    private boolean destroyed;

    // abstract methods to implement
    // protected so that they cannot be called in World
    protected abstract void innerUpdate(int delta) throws SlickException;
    protected abstract void handleCollision(Entity[] entities);

    /**
     * Create entity
     * @param name name of the entity
     * @param pos starting position
     * @param sprite starting sprite
     * @param collisionRadius starting collision radius
     */
    public Entity(String name, Vector2 pos, Image sprite, int collisionRadius) {
        this.sprite = sprite;
        this.currSprite = sprite;
        this.collisionRadius = collisionRadius;
        this.pos = pos;
        this.name = name;
        this.destroyed = false;
    }

    /**
     * check if collidied with the entity
     * @param entity entity to check
     * @return true if collidied, false otherwise
     */
    @Override
    public boolean hasCollided(Entity entity) {
        if(!destroyed) {
            double p = pos.distance(entity.getPos());
            int totalRad = this.collisionRadius;//entity.getCollisionRadius() + this.collisionRadius;

            return (p < totalRad);
        }
        return false;
    }

    /**
     * Render for slick2d
     * @throws SlickException
     */
    public void render(Graphics g) throws SlickException {
        if(!destroyed)
            getCurrSprite().drawCentered((float) getPos().x, (float) getPos().y);
    }

    /**
     * update for slick2d
     * @param delta milliseconds passed since previous frame
     * @throws SlickException
     */
    public void update(int delta) throws SlickException {
        if(!destroyed)
            innerUpdate(delta);
    }

    /**
     * handle permanent destruction of object from the world
     */
    public final void destroy() {
        this.destroyed = true;
    }

    /**
     * handle collision call for the world
     * @param entities array of entities that has collided
     */
    public final void doCollision(Entity[] entities) {
        if(!destroyed)
            handleCollision(entities);
    }


    /**
     * render status for the entity for the world
     * @param g slick graphics
     */
    public final void renderStatus(Graphics g) {
        if(!destroyed) {
            displayStatus(g);
        }
    }

    /**
     * function called for display status
     * protected so that it cannot be called from World
     * @param g slick graphics
     */
    protected void displayStatus(Graphics g) {
        int height = sprite.getHeight() + DISPLAY_OFFSET;
        Vector2 size = calcBarSize(name);
        Vector2 loc = calcBarLoc(name, height);
        Vector2 strLoc = calcString(name, height);

        g.setColor(Color.black);
        g.fillRect((float)loc.x, (float)loc.y, (float)size.x, (float)size.y);
        g.setColor(Color.white);
        g.drawString(getName(), (float)strLoc.x, (float)strLoc.y);
    }

    // For UI

    /**
     * calculate background bar size required for the string
     * @param str string to consider
     * @return
     */
    public Vector2 calcBarSize(String str) {
        int barWidth = HALF_FONT_SIZE*str.length() + DISPLAY_OFFSET,
            barHeight = HALF_FONT_SIZE*2;
        return new Vector2(barWidth, barHeight);
    }

    /**
     * calculate background bar location required for the string
     * @param str string to consider
     * @return
     */
    public Vector2 calcBarLoc(String str, int height) {
        float barLocX = (float)getPos().x - str.length()* HALF_FONT_SIZE/2 - DISPLAY_OFFSET/2,
              barLocY = (float)getPos().y - (float)height/2 - DISPLAY_OFFSET;
        return new Vector2(barLocX, barLocY);
    }

    /**
     * calcualte string position for display
     * @param str string to consider
     * @param height height of sprite
     * @return
     */
    public Vector2 calcString(String str, int height) {
        float xPos = (float) pos.x - (float) str.length() * HALF_FONT_SIZE / 2,
              yPos = (float) pos.y - (float) height / 2 - DISPLAY_OFFSET;
        return new Vector2(xPos, yPos);
    }

    // getters and setters
    public Image getCurrSprite() {
        return currSprite;
    }

    public void setCurrSprite(Image currSprite) {
        this.currSprite = currSprite;
    }

    public int getCollisionRadius() {
        return collisionRadius;
    }

    public void setCollisionRadius(int radius){
        this.collisionRadius = radius;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 p) {
        this.pos = p;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

}
