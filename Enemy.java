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
    private float hurtWait, hurtTimer; // short invinicibility, hurt by player
    protected String atkType; // 'melee' or 'range'
    private static Vector2 playerOffset = new Vector2(0, 10);
    private float idleTimer; // when player dies, start random timer for idle (for variance)
    private float deathTimer; // time it takes to die after losing health
    
    /*
     * Enemy constructor
     */
    public Enemy(Room room, float atkDmg, float health, int movespeed, Vector2 pos) {
        this(room, atkDmg, health, movespeed, pos, 50, 70, 200, 3); // default
    }
    
    public Enemy(Room room, float atkDmg, float health, float movespeed, Vector2 pos, 
                        float distClose, float distFar, float detectRange, float atkWait) {
        super(room, atkDmg, health, movespeed, pos);
        setSpeed(Utils.random(movespeed-0.2f, movespeed+0.2f));
        this.maxHealth = health;
        
        this.imgpath = "enemy/";
        
        this.distClose = distClose;
        this.distFar = distFar; 
        this.detectRange = detectRange;
        
        this.atkWait = atkWait;
        this.atkTimer = this.atkWait;
        
        this.hurtWait = 0.5f;
        
        EnemyHealthBar healthBar = new EnemyHealthBar(this, "bar");
        room.addObject(healthBar, 100, 30);
        healthBar.initBasePos();
        
        this.idleTimer = -1;
    }
    
    public void act() {
        if (isDead()) {
            deathTimer -= 1f / Utils.FPS;
            if (deathTimer <= 0) {
                dropItem();
                Player.kills++;
                room.removeObject(this);
            }
            return;
        } 
        
        if (!room.getPlayer().isDead()) {
            action();
            updateTime();
            updatePos();
        } else {
            if (idleTimer == -1) {
                idleTimer = Utils.random(1f);
                
            } else if (idleTimer <= 0) {
                state = "idle";
                
            } else {
                idleTimer -= 1f / Utils.FPS;
                
            }
            
        }
        
        animate();

    }
    
    /*
     * Drop random item on death
     */
    protected void dropItem() {
        float healthPot = 2;
        float coin = healthPot + 5;
        
        float none = coin + 5;
        float prob = Utils.random();
        if (prob <= healthPot / none) {
            room.addObject(new HealthPotion(), pos.getX(), pos.getY());
        } else if (prob <= coin / none) {
            room.addObject(new Coin(), pos.getX(), pos.getY());
        } else {
            // oh nose! enemy didn't drop anything
        }
    }
    
    /*
     * Manages attacks + move
     */
    protected void action() {
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
            System.out.println("Shooting enemies don't exist yet :/");
        } else {
            System.out.println(this + " | Attack type not specified");
        }
        
        // only if attack is finished
        if (state == "follow") {
            atkTimer = Utils.random(0.7f*atkWait, 1.3f*atkWait);
            pos = Vector2.add(pos, new Vector2(Utils.random(-5, 5), Utils.random(-5, 5)));
        }
    }
    
    /*
     * Update timers based on FPS
     */
    protected void updateTime() {
        if (state=="follow") atkTimer -= 1f / Utils.FPS;
        if (hurtTimer > 0) hurtTimer -= 1f / Utils.FPS;
    }
    
    /*
     * When enemy loses health
     */
    public void loseHealth(float dmg) {
        if (hurtTimer <= 0) {
            super.loseHealth(dmg);        
            if (health <= 0 && health != -100) {
                health = -100;
                
                if (dir == 0) setRotation(90);
                else setRotation(-90);
                
                deathTimer = Utils.random(1f, 3f);
            }
            hurtTimer = hurtWait;
        } 
    }
    
    /*
     * Called when room changes
     */
    public void enterRoom() {
        this.state = "idle";
    }
    
    /*
     * Max detect range for boss fights
     */
    public void maxDetect() {
        detectRange = Utils.worldWidth + Utils.worldHeight;
    }
    
    /*
     * Getter
     */
    public boolean isDead() { return health <= 0; }
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