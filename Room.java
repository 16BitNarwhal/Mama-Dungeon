import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * A room in the entire world
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Room extends World {
    
    private static ArrayList<Room> rooms = new ArrayList<Room>();
    private int id;
    private Room left, right, up, down;
    
    /**
     * Construct room
     */
    public Room() {
        super(Utils.worldWidth, Utils.worldHeight, 1);
        rooms.add(this);
        this.id = rooms.size();
        setFloor();
        setWalls();
    }
    
    /*
     * Lay down structural tiles (floor and wall)
     */
    private void setFloor() {
        int sz = Utils.tileSize;
        for (int x = 2*sz + sz/2; x <= Utils.worldWidth - 2*sz - sz/2; x += sz) {
            for (int y = 2*sz + sz/2; y <= Utils.worldHeight - 2*sz - sz/2;y += sz) {
                FloorTile floorTile = new FloorTile();
                addObject(floorTile, x, y);
            }
        }
    }
    
    private void setWalls() {
        int sz = Utils.tileSize;
        // Top-side wall tiles
        for (int x = sz/2; x <= Utils.worldWidth - sz/2; x += sz) {
            for (int y = sz/2; y <= sz + sz/2; y += sz) {
                WallTile wallTile = new WallTile("top");
                addObject(wallTile, x, y);
            }
        }
    }
    
    /*
     * Getters
     */
    public int getID() { return this.id; }
    
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