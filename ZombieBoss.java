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
        // room | atk|  hp |  spd | pos |dst1 |dst2|detect | wait | # phases
        super(room, 15f, 100, 2.5f, pos, 60,   80,    300,    2f,   2);
        
        this.atkType = "melee";
        this.imgpath += "big zombie/";
        initIdleAnim("big_zombie_idle_anim_f", 4);
        initRunAnim("big_zombie_run_anim_f", 4);
        
    }
    
    protected void action() {
        if (phase == 0) {
            super.action();
        } if (phase == 1) {
            state = "idle";
            if (spawnTimer <= 0) { 
                spawnTimer = Utils.random(spawnWait*1/2, spawnWait*3/2);
                if (room.enemies.size() < 100) { // just in case!
                    Enemy e = Utils.newEnemy(room);
                    e.maxDetect();
                    room.enemies.add(e);
                    room.addObject(e, 0, 0);
                }
            }
        }
    }

}
