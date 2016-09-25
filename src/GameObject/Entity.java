/*
 * Created by Bermuda_UT on 28/08/16.
 * Name      : Max Lee
 * StudentID : 719577
 */

package GameObject;

import Common.Vector2;
import Common.Collidable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Parent class for all in-game characters (player, NPC etc)
 */
public abstract class Entity implements Collidable{
    private Vector2 pos;
    private int collisionRadius;
    private String name;
    private Image sprite;
    private Image currSprite;

    public Entity(String name, Vector2 pos, Image sprite, int collisionRadius) {
        this.sprite = sprite;
        this.currSprite = sprite;
        this.collisionRadius = collisionRadius;
        this.pos = pos;
        this.name = name;
    }

    // abstract methods
    public abstract void update(int delta) throws SlickException;
    public abstract void handleCollision(Entity[] entities);

    // collidable partial implementation
    @Override
    public boolean hasCollided(Entity entity) {
        double p = pos.distance(entity.getPos());
        int totalRad = entity.getCollisionRadius() + this.collisionRadius;

        return (p < totalRad);
    }

    /**
     * Render for slick2d
     * @throws SlickException
     */
    public void render(Graphics g) throws SlickException {
        getCurrSprite().drawCentered((float) getPos().x, (float) getPos().y);
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

}
