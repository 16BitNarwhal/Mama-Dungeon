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
        this(room, 3, 100, 2, new Vector2(Utils.worldWidth/2, Utils.worldHeight/2));
        this.maxHealth = 100;
    }
    
    public Player(Room room, float atkDmg, float health, int movespeed, Vector2 pos) {
        super(room, atkDmg, health, movespeed, pos); 
        
        this.imgpath = "player/";
        initIdleAnim();
        initRunAnim();
        
        PlayerHealthBar healthBg = new PlayerHealthBar(this, "bg");
        room.addObject(healthBg, 100, 30);
        
        PlayerHealthBar healthBar = new PlayerHealthBar(this, "bar");
        room.addObject(healthBar, 100, 30);
        healthBar.initBasePos();
    }
    
    public void act() {
        move();
        animate();
        
        health -= 0.05;
        health = Math.max(health, 0);
        // System.out.println(Room.getRooms().size());
    }    
    
    /*
     * Player movement + key handling
     */
    private void move() {
        boolean up = Greenfoot.isKeyDown("w");
        boolean down = Greenfoot.isKeyDown("s");
        boolean left = Greenfoot.isKeyDown("a");
        boolean right = Greenfoot.isKeyDown("d");
        
        // check if player not touching bounds and corresponding key pressed to move
        if (getY() > room.getUpBound() && up) {
            setLocation(getX(), getY() - movespeed);
        }
        if (getY() < room.getDownBound()-16 && down) {
            setLocation(getX(), getY() + movespeed);
        }
        if (getX() > room.getLeftBound()+8 && left) {
            setLocation(getX() - movespeed, getY());
            dir = 1;
        }
        if (getX() < room.getRightBound()-8 && right) {
            setLocation(getX() + movespeed, getY());
            dir = 0;
        }
        
        if (up || down || left || right) {
            state = "run";
        } else {
            state = "idle";
        }
    } 
    
    /*
     * Player animate function
     */
    private void animate() { 
        if (state != prevState) {
            frame = 0;
        }
        if (state=="idle") {
            int fr = 60 / 4; // framerate
            frame %= fr * idleAnim[dir].size();
            setImage(idleAnim[dir].get(frame / fr));
        } else if (state=="run") {
            int fr = 60 / 12; // framerate
            frame %= fr * runAnim[dir].size();
            setImage(runAnim[dir].get(frame / fr));
        }
        prevState = state;
        frame++;
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
     * Getters & Setters
     */
    public float getAtkDmg() { return this.atkDmg; }
    public float getHealth() { return this.health; }
    public int getSpeed() { return this.movespeed; }
    public int getDir() { return this.dir; }
    
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
            setLocation(93, 194);
        } else if (type=="left") {
            setLocation(546, 194);
        } else if (type=="down") {
            setLocation(320, 95);
        } else if (type=="up") {
            setLocation(320, 314);
        }
    }
}

/*
 * Todo:
 * invincibility when hit by enemy / entering rooms
 * decrease health when hit by enemy
 * play hit animation when hit by enemy
 * 
 */
