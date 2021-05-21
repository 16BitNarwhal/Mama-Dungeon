import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Title screen
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Menu extends World {
    
    /*
     * Construct main menu 
     */ 
    public Menu() {
        super(Utils.worldWidth, Utils.worldHeight, 1);
        setBG();
        setGUI();
        // reset rooms
        Room.resetRooms();
    }
    
    /*
     * Creates a background filled with wall tiles
     */
    private void setBG() {
        for (int x = 0;x <= Utils.worldWidth;x += Utils.tileSize) {
            for (int y = 0;y <= Utils.worldWidth;y += Utils.tileSize) {
                WallTile wallTile = new WallTile();
                addObject(wallTile, x, y);
            }
        }
    }
    
    /*
     * Initializes all the menu GUIs
     */
    private void setGUI() {
        addObject(new Title(), 320, 144);  
        addObject(new StartButton(), 320, 260); 
        addObject(new MoveHelp(), 166, 265); 
        addObject(new AtkHelp(), 437, 265);
        
    }
    
}
