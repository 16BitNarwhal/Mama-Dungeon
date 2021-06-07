import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * A room in the entire world
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Room extends World {
    protected static ArrayList<Room> rooms = new ArrayList<Room>(); // all rooms
    protected final int maxRad = 4; // max radius (distance of rooms from start)
    protected int rad; // current distance / radius from start
    
    private Player player;
    private int id; // identity of room
    protected Room leftRoom, rightRoom, upRoom, downRoom; // neighbouring rooms
    private int x1, x2, y1, y2; // room boundaries
    
    protected ArrayList<Enemy> enemies;
    
    public static GreenfootSound themeSong = new GreenfootSound("main_theme.wav");
    
    /**
     * Construct room with no given neighbours
     */ 
    public Room() {
        this(0, null, null, null, null);
    }
    
    /**
     * Construct room with given neighbours
     */
    public Room(int rad, Room left, Room right, Room up, Room down) {
        super(Utils.worldWidth, Utils.worldHeight, 1);
        // create room bounds based on tile size
        int sz = Utils.tileSize;
        x1 = 2*sz + sz/2; 
        x2 = Utils.worldWidth - 2*sz - sz/2;
        y1 = sz + sz/2;
        y2 = Utils.worldHeight - 2*sz - sz/2;
        setBackground(new GreenfootImage("background.PNG"));
        rooms.add(this);
        this.id = rooms.size();
        
        // create floor and wall tiles
        setFloor();
        setWalls();
        
        this.leftRoom = left;
        this.rightRoom = right;
        this.upRoom = up;
        this.downRoom = down;
        
        // create new rooms & add doors for each neighbour room
        this.rad = rad;
        if (!(this instanceof BossRoom)) {
            initRooms();
        }
        initDoors();
        
        // debug room id
        addObject(new Text(String.valueOf(this.id), 50, Color.RED, Color.BLACK), 100, 100);
        
        // create player
        player = new Player(this);
        addObject(player, Utils.worldWidth/2, Utils.worldHeight/2);
        
        enemies = new ArrayList<Enemy>();
        
    }
    
    public void act() {
        setPaintOrder(GUI.class, Weapon.class, Enemy.class, Player.class, MoveHelp.class, AtkHelp.class);
    }
    
    /*
     * Initialize new neighbouring rooms
     */
    private void initRooms() {
        if (rad < maxRad) { // limit distance of farthest room to start
            // probability of creating a new room on each side
            float prob = 0.5f - rad/(2f*maxRad);
            if (leftRoom==null && Utils.random() <= prob) {
                // right of new room is this
                leftRoom = newRoom(null, this, null, null);
            }
            if (rightRoom==null && Utils.random() <= prob) {
                // left of new room is this
                rightRoom = newRoom(this, null, null, null); 
            }
            if (upRoom==null && Utils.random() <= prob) {
                // down of new room is this
                upRoom = newRoom(null, null, null, this);
            }
            if (downRoom==null && Utils.random() <= prob) {
                // up of new room is this
                downRoom = newRoom(null, null, this, null);
            }
            // if first room and no neighbour rooms, restart
            if (id==1 && leftRoom==null && rightRoom==null &&
                upRoom==null && downRoom==null) {
                initRooms();
            }
        }
    }
    
    /*
     * Create new random room
     */
    private Room newRoom(Room a, Room b, Room c, Room d) {
        // init probabilites here
        float enemy = 1; 
        
        float empty = enemy + 1;
        
        float prob = Utils.random();
        if (prob <= enemy / empty) {
            return new EnemyRoom(rad+1, a, b, c, d);
        }
        
        return new Room(rad+1, a, b, c, d);
    }
    
    /*
     * Initialize doors
     */
    private void initDoors() {
        if (leftRoom != null) {
            addObject(new Door(this, leftRoom, "left"), 42, 200);
        }
        if (rightRoom != null) {
            addObject(new Door(this, rightRoom, "right"), 598, 200);
        }
        if (upRoom != null) {
            addObject(new Door(this, upRoom, "up"), 320, 32);
        }
        if (downRoom != null) {
            addObject(new Door(this, downRoom, "down"), 320, 376);
        }
    
    }
    
    /*
     * Lay down structural tiles (floor and wall)
     */
    private void setFloor() { 
        int sz = Utils.tileSize; 
        for (int x = x1; x <= x2; x += sz) {
            for (int y = y1; y <= y2; y += sz) {
                addObject(new FloorTile(), x, y);
            }
        }
    }
    
    private void setWalls() { 
        int sz = Utils.tileSize; 
        // horizontally placed tiles
        for (int x = x1; x <= x2; x += sz) { 
            // Top-side wall tiles
            addObject(new WallTile("top"), x, sz/2);
            addObject(new WallTile(), x, sz + sz/2);      
            
            // Bottom-side wall tiles
            addObject(new WallTile("bottom"), x, Utils.worldHeight - 2 * sz - sz/2);
            addObject(new WallTile(), x, Utils.worldHeight - sz - sz/2);
        }
        // vertically placed tiles
        for (int y = y1; y <= y2; y += sz) {
            // Left-side wall tiles
            addObject(new WallTile("left"), 2*sz + sz/2, y);
            
            // Right-side wall tiles
            addObject(new WallTile("right"), Utils.worldWidth - 2*sz - sz/2, y);
        }
        
    }
    
    /*
     * Reset / clear any existing rooms for 'new game'
     */
    public static void resetRooms() { 
        for (Room room : rooms) {
            room.clearRoom();
        }
        rooms.clear();
    }
    public void clearRoom() {
        leftRoom = null; rightRoom = null; upRoom = null; downRoom = null; 
    }
    
    /*
     * Getters & setters
     */
    public int getID() { return this.id; }
    public static ArrayList<Room> getRooms() { return rooms; }
    public Player getPlayer() { return this.player; }
    public ArrayList<Enemy> getEnemies() { return this.enemies; }
    
    public int getNeighbours() {
        int cnt = 0;
        cnt += (this.leftRoom != null) ? 1 : 0;
        cnt += (this.rightRoom != null) ? 1 : 0;
        cnt += (this.upRoom != null) ? 1 : 0;
        cnt += (this.downRoom != null) ? 1 : 0;
        return cnt;
    }
    public int getRad() { return this.rad; }
    
    public int getLeftBound() { return this.x1; }
    public int getRightBound() { return this.x2; }
    public int getUpBound() { return this.y1; }
    public int getDownBound() { return this.y2; }

    public Room getLeftRoom() { return leftRoom; }
    public Room getRightRoom() { return rightRoom; }
    public Room getUpRoom() { return upRoom; }
    public Room getDownRoom() { return downRoom; }
    
    public void setLeftRoom(Room r) { this.leftRoom = r; }
    public void setRightRoom(Room r) { this.rightRoom = r; }
    public void setUpRoom(Room r) { this.upRoom = r; }
    public void setDownRoom(Room r) { this.downRoom = r; }
    
}

/* 
 * Todo: types of rooms
 *  trap room - spikes etc. 
 *  enemy rooms - undead, fire, etc.
 *  support rooms - gold, healing, etc.
 *  empty rooms
 *  boss room 
 * 
 * extras:
 * difficulty (cur diff + random[0,1])
 * 
 */