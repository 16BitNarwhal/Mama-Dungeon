import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player script
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Player extends Entity {       
    
    /*
     * Player constructors
     */
    public Player(Room room) {
        //  room |atk| hp| spd | pos
        this(room, 2.5f, 100, 2, new Vector2(Utils.worldWidth/2, Utils.worldHeight/2));
        this.maxHealth = 100;
    }
    
    public Player(Room room, float atkDmg, float health, float movespeed, Vector2 pos) {
        super(room, atkDmg, health, movespeed, pos); 
        
        this.imgpath = "player/";
        initIdleAnim();
        initRunAnim();
        
        PlayerHealthBar healthBg = new PlayerHealthBar(this, "bg");
        room.addObject(healthBg, 100, 30);
        
        PlayerHealthBar healthBar = new PlayerHealthBar(this, "bar");
        room.addObject(healthBar, 100, 30);
        healthBar.initBasePos();
        
        PlayerWeapon w = new PlayerWeapon(this, 3f, 30);
        room.addObject(w, 0, 0);
    }
    
    public void act() { 
        movement();
        animate();
        updatePos();
        
        health = Math.max(health, 0); 
    }    
    
    /*
     * Player movement + key handling
     */
    private void movement() {
        boolean up = Greenfoot.isKeyDown("w");
        boolean down = Greenfoot.isKeyDown("s");
        boolean left = Greenfoot.isKeyDown("a");
        boolean right = Greenfoot.isKeyDown("d");
        
        // check if player not touching bounds and corresponding key pressed to move
        if (getY() > room.getUpBound() && up) {
            moveY(-movespeed);
        }
        if (getY() < room.getDownBound()-16 && down) {
            moveY(movespeed);
        }
        if (getX() > room.getLeftBound()+8 && left) {
            moveX(-movespeed);
            dir = 1;
        }
        if (getX() < room.getRightBound()-8 && right) {
            moveX(movespeed);
            dir = 0;
        }
        
        if (up || down || left || right) {
            state = "run";
        } else {
            state = "idle";
        }
    } 
    
    /*
     * Player is attacked / loses health
     */
    public void loseHealth(float dmg) {
        super.loseHealth(dmg);
        room.addObject(new PlayerHurt(), Utils.worldWidth/2, Utils.worldHeight/2);
        
        if (health <= 0) {
            System.out.println("You dead!!!");
            // create death world or smht
        }
    }
    
    /*
     * Initialize player animations
     */
    private void initIdleAnim() {
        super.initIdleAnim("knight_m_idle_anim_f", 4);
    }
    
    private void initRunAnim() {
        super.initRunAnim("knight_m_run_anim_f", 4);
    }
    
    /*
     * Help transfer player stats from one room to another
     */ 
    public void setStats(Player p) {
        // copy of stats from player p to this
        this.atkDmg = p.getAtkDmg();
        this.health = p.getHealth();
        this.movespeed = p.getSpeed();
        this.dir = p.getDir(); 
    }
    
    /*
     * Relocate player after entering a room
     */
    public void setRoomLocation(String type) {
        if (type=="right") {
            pos = new Vector2(93, 194); 
        } else if (type=="left") {
            pos = new Vector2(546, 194); 
        } else if (type=="down") {
            pos = new Vector2(320, 95);
        } else if (type=="up") {
            pos = new Vector2(320, 314);
        }
        updatePos();
    }
}

/*
 * Todo:
 * Attack and kill enemies
 * 
 * Idea:
 * invincibility when hit by enemy / entering rooms
 * 
 */
