import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Generaly enemy / mob, attacks player
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Enemy extends Entity {
    
    private boolean followPlayer;
    private float distClose, distFar; // distance enemy tries to keep from player
    private float detectRange; // check when enemy sees player
    private float atkWait, attackTimer; // time until next attack (in frames/cycles)
    
    /*
     * Enemy constructor
     */     
    public Enemy(Room room, float atkDmg, float health, int movespeed, Vector2 pos) {
        this(room, atkDmg, health, movespeed, pos, 50, 70, 200);
    }
    
    public Enemy(Room room, float atkDmg, float health, float movespeed, Vector2 pos, 
                        float distClose, float distFar, float detectRange) {
        super(room, atkDmg, health, movespeed, pos);
        setSpeed(Utils.random(movespeed-0.2f, movespeed+0.2f));
        this.maxHealth = health;
        this.imgpath = "enemy/";
        this.distClose = distClose;
        this.distFar = distFar; 
        this.detectRange = detectRange;
    }
    
    public void act() {        
        movement();
        animate(); 
        
        updatePos();
    }
    
    /*
     * Method to move (moves within a distance of the player)
     */
    protected void movement() {
        if (followPlayer) {
            state = "run";
            Vector2 playerPos = room.getPlayer().pos;
            // current distance
            float dist = Vector2.distance(pos, playerPos);
            
            Vector2 nextMove = new Vector2();
            if (dist > distFar) {
                nextMove = Vector2.sub(playerPos, pos); 
            } else if (dist < distClose) {
                nextMove = Vector2.sub(pos, playerPos); 
            }
            if (nextMove.mag() > 0)
                nextMove = nextMove.mult(movespeed / nextMove.mag()); 
                
            move(nextMove);
        } else {
            state = "idle";
            Vector2 playerPos = room.getPlayer().pos;
            float dist = Vector2.distance(pos, playerPos); 
            
            if (dist <= detectRange) {
                followPlayer = true;
            }
        }
    }
    
    /*
     * Called when room changes
     */
    public void enterRoom() {
        this.followPlayer = false;
    }
    
}

/*
 * Todo:
 * initializers
 * movement
 * attack
 * 
 * First mob
 * 
 */