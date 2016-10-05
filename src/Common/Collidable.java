package Common;

/**
 * Every collidable class in the game implements this class
 * @author MaxLee
 */
public interface Collidable {
    /**
     * check if collided
     * @param entity entity to consider
     * @return
     */
    boolean hasCollided(Entity entity);

    /**
     * handle collision
     * @param entities entitiesto consider
     */
    void doCollision(Entity[] entities);

    // getters and setters
    void setCollisionRadius(int radius);
    int getCollisionRadius();
}
