package Common;

/**
 * Created by noxm on 22/09/16.
 */
public interface Collidable {
    boolean hasCollided(Entity entity);
    void handleCollision(Entity[] entities);
    void setCollisionRadius(int radius);
    int getCollisionRadius();
}
