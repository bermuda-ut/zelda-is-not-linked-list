package GameObject;

import Common.Character;
import Common.Entity;
import Common.Vector2;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by noxm on 25/09/16.
 */
public class Mob extends Character {
    private int id;
    private boolean isAggro, attacked;
    private int actionTime;
    private int MOVE_TIME = 3000,
                RUN_TIME = 5000,
                VISION = 150;
    private Vector2 prevMove, prevPos;

    public Mob(int id, String name, Vector2 pos, Image sprite, int collisionRadius, double speed, int cooldown,
               int HP, int damage, boolean isBasic, boolean isAggro) {
        super(name, pos, sprite, collisionRadius, speed, cooldown, HP, damage, isBasic);
        this.id = id;
        this.isAggro = isAggro;
        this.actionTime = 0;
        this.attacked = false;
        rerollPrevMove();
    }

    private void rerollPrevMove() {
        prevMove = new Vector2(Math.random()-0.5, Math.random()-0.5);
        prevMove.setDistance(movementAttribute());
    }

    @Override
    public void innerUpdate(int delta) throws SlickException {
        handleDeath();
        Vector2 playerPos = Player.getCurrPlayer().getPos();
        if(isAggro) {
            double dist = playerPos.distance(getPos());
            if(dist <= VISION) {
                if(dist > getCollisionRadius()) {
                    setMove(getPos().vectorTo(playerPos));
                } else {
                    getMove().set(0, 0);
                }
            } else {
                getMove().set(0, 0);
            }
        } else {
            if(attacked) {
                if(actionTime < RUN_TIME) {
                    //run
                    setMove(getPos().vectorTo(playerPos).multiply(-1));
                } else {
                    attacked = false;
                    actionTime = 0;
                }
            } else {
                if(actionTime < MOVE_TIME) {
                    setMove(prevMove);
                } else {
                    actionTime = 0;
                    rerollPrevMove();
                }
            }
        }

        handleMovement(delta);

        actionTime += delta;
        updateCooldown(delta);
    }

    @Override
    public void handleDeath() {
        if(this.getCurrHP() <= 0)
            this.destroy();
    }

    @Override
    public void handleCollision(Entity[] entities) {
        for (Entity entity : entities) {
            if(entity instanceof Player) {
                Player player = (Player)entity;
                if(player.isAttacking()) {
                    attacked = true;
                    this.getDamaged(player.getDamage());
                    System.out.println("HP: " + getCurrHP());
                }

                if(isAggro && getCurrCooldown() == 0) {
                    player.getDamaged(this.getDamage());
                    resetCurrCooldown();
                }
            }
        }
    }
}
