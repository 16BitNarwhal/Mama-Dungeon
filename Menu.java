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

    }
    
    /*
     * Creates a background filled with wall tiles
     */
    private void setBG() {
        for (int x = 0;x <= Utils.worldWidth;x += Utils.tileSize) {
            for (int y = 0;y <= Utils.worldWidth;y += Utils.tileSize) {
                WallTile wallTile = new WallTile("top");
                addObject(wallTile, x, y);
            }
        }
    }
    
    /*
     * Initializes all the menu GUIs
     */
    private void setGUI() {
        Title title = new Title();
        addObject(title, 320, 144);
        StartButton startBtn = new StartButton(); 
        addObject(startBtn, 320, 260);
        MoveHelp moveHelp = new MoveHelp();
        addObject(moveHelp, 166, 265);
        AtkHelp atkHelp = new AtkHelp();
        addObject(atkHelp, 437, 265);
        
    }
    
}
