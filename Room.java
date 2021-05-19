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
    
    private int id;
    private Room left, right, up, down; // neighbouring rooms
    private int x1, x2, y1, y2; // room boundaries
    
    /**
     * Construct room
     */
    public Room() {
        super(Utils.worldWidth, Utils.worldHeight, 1);
        int sz = Utils.tileSize;
        x1 = 2*sz + sz/2; x2 = Utils.worldWidth - 2*sz - sz/2;
        y1 = sz + sz/2; y2 = Utils.worldHeight - 2*sz - sz/2;
        setBackground(new GreenfootImage("background.PNG"));
        rooms.add(this);
        this.id = rooms.size();
        setFloor();
        setWalls();
        
        if (rooms.size() < 3) {
            left = new Room();
        }
        
        addObject(new Text(String.valueOf(this.id), 50, Color.RED, Color.BLACK), 100, 100);
        
        addObject(new Player(), Utils.worldWidth/2, Utils.worldHeight/2);
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
     * Getters
     */
    public int getID() { return this.id; }
    public static ArrayList<Room> getRooms() { return rooms; }
}

/* 
 * Pseudo-code
 * 
 * static arraylist<room> list
 * int id
 * room left, right, up, down
 * 
 * init()
 *   start = new room(%50 chance left/right/up/down room)
 *   list.add(start) 
 *   
 */

/*
 * Things to/can add
 * 
 * boss room to end game
 * Id affects difficulty
 * 
 */