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
    private float atkWait, atkTimer; // time until next attack (in frames/cycles)
    protected String atkType; // 'melee' or 'range'
    private static Vector2 playerOffset = new Vector2(0, 10);
    
    /*
     * Enemy constructor
     */     
    public Enemy(Room room, float atkDmg, float health, int movespeed, Vector2 pos) {
        this(room, atkDmg, health, movespeed, pos, 50, 70, 200, 3); // default
    }
    
    public Enemy(Room room, float atkDmg, float health, float movespeed, Vector2 pos, 
                        float distClose, float distFar, float detectRange, float waitTime) {
        super(room, atkDmg, health, movespeed, pos);
        setSpeed(Utils.random(movespeed-0.2f, movespeed+0.2f));
        this.maxHealth = health;
        this.imgpath = "enemy/";
        this.distClose = distClose;
        this.distFar = distFar; 
        this.detectRange = detectRange;
        this.atkWait = waitTime;
        this.atkTimer = this.atkWait;
    }
    
    public void act() {
        movement(); 
        animate();

        updateTime();
        updatePos();
    }
    
    /*
     * Method to move (moves within a distance of the player)
     */
    protected void movement() {
        Player player = room.getPlayer();
        if (player.getX() > getX()) dir = 0;
        else if (player.getX() < getX()) dir = 1;
        
        if (state == "follow") { 
            Vector2 playerPos = Vector2.add(player.getPos(), playerOffset);
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
            
            if (atkTimer <= 0) {
                state = "attack";
            }
        } else if (state=="idle") { 
            Vector2 playerPos = Vector2.add(player.getPos(), playerOffset);
            float dist = Vector2.distance(pos, playerPos); 
            
            atkTimer = 100;
            if (dist <= detectRange) {
                atkTimer = Utils.random(0.7f*atkWait, 1.3f*atkWait);
                state = "follow";
            }
        } else if (state=="attack") {
            attack();
        }
    }
    
    private void attack() {
        if (atkType=="melee") {
            Vector2 playerPos = Vector2.add(room.getPlayer().getPos(), playerOffset);
            Vector2 nextMove = Vector2.sub(playerPos, pos);
            nextMove = nextMove.mult(1.7f * movespeed / nextMove.mag());
            move(nextMove);
            
            float dist = Vector2.distance(pos, playerPos);
            if (dist <= 2f) { // some threshold for "collision"
                state = "follow";
                room.getPlayer().loseHealth(atkDmg);
            }
        } else if (atkType=="shoot") {
            // spawn projectile
        } else {
            System.out.println(this + " | Attack type not specified");
        }
        
        // only if attack is finished
        if (state == "follow") {
            atkTimer = Utils.random(0.7f*atkWait, 1.3f*atkWait);

        }
    }
    
    /*
     * Update timer(s) based on FPS
     */
    private void updateTime() {
        if (state=="follow") atkTimer -= 1f / Utils.FPS;
        
    }
    
    /*
     * Called when room changes
     */
    public void enterRoom() {
        this.state = "idle";
    }
    
}

/*
 * Ideas:
 * melee have a "homing attack" and a "straight line attack" (randomized)
 * homing atk (already made) follows player during atk
 * straight atk (not made) follows player's old position when atk started
 * 
 * todo:
 * ranged attack
 * 
 */