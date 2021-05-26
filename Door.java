import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Create a door
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class Door extends Environment {
    
    private Room room, target;
    private String type;
    
    public Door(Room room, Room target, String type) {
        this.room = room;
        this.target = target;
        this.type = type;
        GreenfootImage image = new GreenfootImage("null.png");
        if (type=="left") {
            image = new GreenfootImage(Tile.imgpath + "entrance_left.png");
        } else if (type=="right") {
            image = new GreenfootImage(Tile.imgpath + "entrance_left.png");
            image.mirrorHorizontally();
        } else if (type=="up") {
            image = new GreenfootImage(Tile.imgpath + "entrance_top.png");
        } else if (type=="down") {
            image = new GreenfootImage(Tile.imgpath + "entrance_bottom.png");
        }
        image.scale(image.getWidth()*2, image.getHeight()*2);
        setImage(image);
    }
    
    public void act() {
        Actor player = getOneIntersectingObject(Player.class);
        
        if (player != null) {
            target.getPlayer().setStats(room.getPlayer());
            target.getPlayer().setRoomLocation(type);
             
            for (Enemy enemy : target.getEnemies()) {
                enemy.enterRoom();
            }
           
            Greenfoot.setWorld(target);
        }
    }
}
