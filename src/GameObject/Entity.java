/*
 * Created by Bermuda_UT on 28/08/16.
 * Name      : Max Lee
 * StudentID : 719577
 */

package GameObject;

import Common.Vector2;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Parent class for all in-game characters (player, NPC etc)
 * NOT interface/abstract so that render and update can be overloaded
 * Being able to overload update and render is extremely important in Slick
 * for optimization and linking objects effectively
 */
public abstract class Entity {
    protected Vector2 pos;
    protected Vector2 move;

    public Vector2 getPos() {
        return pos;
    }
    public abstract void render(Graphics g) throws SlickException;
    public abstract void update(int delta) throws SlickException;
}
