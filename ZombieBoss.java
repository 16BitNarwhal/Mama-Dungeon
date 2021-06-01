import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Boss Zombie class
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class ZombieBoss extends Boss { 
    
    /*
     * ZombieBoss constructor
     */
    public ZombieBoss(Room room, Vector2 pos) {
        super(room, 15, 100, 1.5f, pos, 60,   80,  300,  2f, 2);
        
        this.atkType = "melee";
        this.imgpath += "big zombie/";
        initIdleAnim("big_zombie_idle_anim_f", 4);
        initRunAnim("big_zombie_run_anim_f", 4);
        
    }
    
    protected void phases() {
        if (phase == 0) {
            super.phases();
        } if (phase == 1) {
            state = "idle";
            if (spawnTimer <= 0) {
                System.out.println("spawn");
                spawnTimer = Utils.random(spawnWait*1/2, spawnWait*3/2);
                if (room.enemies.size() < 100) { // just in case!
                    Zombie e = new Zombie(room);
                    room.enemies.add(e);
                    room.addObject(e, 0, 0);
                }
            }
        }
    }

}
