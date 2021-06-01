import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerWeapon here.
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class PlayerWeapon extends Weapon { 
    
    private boolean mouseDown = false;
    
    public PlayerWeapon(Player user, float atkDmg, float dist) {
        super(user, atkDmg, dist);
        
        this.imgpath = "weapon/regular_sword/";
        initIdleAnim("weapon_regular_sword", 1);
        initAtkAnim("weapon_regular_sword_attack", 4);
    }
    
    public void act() {
        super.act();
        
        followMouse();
        updateState();
        handleAttack(Enemy.class);
    }
    
    private void followMouse() {        
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            target = new Vector2(mouse.getX(), mouse.getY());
        } else {
            target = user.getPos();
        }
        updatePos(new Vector2(target.getX(), target.getY()));
    }
    
    private void updateState() {        
        if (Greenfoot.mousePressed(null)) {
            this.state = "attack";
        } else if (Greenfoot.mouseClicked(null)) {
            this.state = "idle";
        }
    }
    
    protected boolean willHurt(int frame, int fr) { 
        if (frame/fr > 0 && frame/fr < atkAnim[0].size() - 1) {
            return true;
        } else {
            return false;
        }
    }
}
