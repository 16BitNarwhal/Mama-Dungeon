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
     * Creates a background
     */
    private void setBG() {
        GreenfootImage bg = new GreenfootImage("menu_background.png");
        bg.scale((int)(bg.getWidth()*2.6), (int)(bg.getHeight()*2.6));
        setBackground(bg);
        /*
        // fill background with wall tiles (when I had no image)
        for (int x = 0;x <= Utils.worldWidth;x += Utils.tileSize) {
            for (int y = 0;y <= Utils.worldWidth;y += Utils.tileSize) {
                WallTile wallTile = new WallTile();
                addObject(wallTile, x, y);
            }
        }
        */
    }

    /*
     * Initializes all the menu GUIs
     */
    private void setGUI() {
        addObject(new Title(), 320, 144);  
        addObject(new StartButton(), 320, 260); 
    }

}
