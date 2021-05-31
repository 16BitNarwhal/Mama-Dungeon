import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
// https://adwitr.itch.io/pixel-health-bar-asset-pack-2/devlog/93170/even-more-healthbars-dark-themed
/**
 * Entity health bar class
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class HealthBar extends GUI { 
    private String imgpath = "health/"; 
    private GreenfootImage img;
    private GreenfootImage baseImg; // original image without scale
    private int imgScale;
    
    private Vector2 basePos; // original position (for bar, not bg)
    private String type;
    protected Entity entity;
    
    /*
     * Healthbar constructors
     */
    public HealthBar(Entity e, int scale, String barImg, String bgImg) {
        this(e, "bar", scale, barImg, bgImg);
    }
    
    public HealthBar(Entity e, String type, int scale, String barImg, String bgImg) {
        this.entity = e;
        this.type = type;
        this.imgScale = scale;
        if (type=="bg" || type=="background") { // use bg image
            baseImg = new GreenfootImage(imgpath + bgImg + ".png");
            img = new GreenfootImage(baseImg);
        } else if (type=="bar") { // use bar image
            baseImg = new GreenfootImage(imgpath + barImg + ".png"); 
            img = new GreenfootImage(baseImg); 
        }
        img.scale(img.getWidth()*imgScale, img.getHeight()*imgScale);
    }
    
    /*
     * Init base position
     */
    protected void initBasePos() {
        basePos = new Vector2(getX(), getY());
    }
    
    /*
     * Update healthbar according to entity health
     */
    protected void updateBar() {
        if (type=="bar") {
            float width = entity.getHealth()/entity.getMaxHealth();
            width *= baseImg.getWidth();
            if (Math.round(width)<=0) {
                img.setTransparency(0);
            } else {
                img.setTransparency(255);
                img.scale(Math.round(width) * imgScale, 
                            baseImg.getHeight() * imgScale);
            }
            setLocation(Math.round(basePos.getX()-baseImg.getWidth()+width), 
                            basePos.getY());
        }
        setImage(img);
    }
    
}
