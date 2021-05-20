import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * A room in the entire world
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Room extends World {
    private static ArrayList<Room> rooms = new ArrayList<Room>(); // all rooms
    public static int cnt = 0; // debug
    
    private Player player;
    private int id; // identity of room
    private Room leftRoom, rightRoom, upRoom, downRoom; // neighbouring rooms
    private int x1, x2, y1, y2; // room boundaries
    
    /**
     * Construct room with no given neighbours
     */ 
    public Room() {
        this(null, null, null, null);
    }
    
    /**
     * Construct room with given neighbours
     */
    public Room(Room left, Room right, Room up, Room down) {
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
        initRooms();
        initDoors();
        
        // debug room id
        addObject(new Text(String.valueOf(this.id), 50, Color.RED, Color.BLACK), 100, 100);
        
        // create player
        player = new Player(this);
        addObject(player, Utils.worldWidth/2, Utils.worldHeight/2);
    }
    
    /*
     * Initialize new neighbouring rooms
     */
    private void initRooms() {
        if (cnt < 1) {
            cnt++;
            if (leftRoom==null) {
                // right of new room is this
                leftRoom = new Room(null, this, null, null);
            }
            if (rightRoom==null) {
                // left of new room is this
                rightRoom = new Room(this, null, null, null); 
            }
            if (upRoom==null) {
                // down of new room is this
                upRoom = new Room(null, null, null, this);
            }
            if (downRoom==null) {
                // up of new room is this
                downRoom = new Room(null, null, this, null);
            }
        }
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
    
    private void addEnemies() { }
    private void addCoins() { }
    
    /*
     * Getters & setters
     */
    public int getID() { return this.id; }
    public static ArrayList<Room> getRooms() { return rooms; }
    public Player getPlayer() { return this.player; }
    
    public int getLeftBound() { return this.x1; }
    public int getRightBound() { return this.x2; }
    public int getUpBound() { return this.y1; }
    public int getDownBound() { return this.y2; }

    public void setLeftRoom(Room r) { this.leftRoom = r; }
    public void setRightRoom(Room r) { this.rightRoom = r; }
    public void setUpRoom(Room r) { this.upRoom = r; }
    public void setDownRoom(Room r) { this.downRoom = r; }
}

/* 
 * Todo:
 * Top door, bottom door, room/map generation
 * 
 * 
 * Things to/can add:
 * 
 * boss room to end game
 * difficulty (cur diff + random[0,1])
 * 
 */