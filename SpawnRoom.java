import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Room that player spawns in
 * 
 * @author Eric Zhang
 * @version (a version number or a date)
 */
public class SpawnRoom extends Room {

    /**
     * Construct StartRoom
     */
    public SpawnRoom() {
        super();
        
        // called after all rooms created
        initBossRoom();
        
        addObject(new MoveHelp(), 170, 280);
        addObject(new AtkHelp(), 470, 290);
        
        if (!themeSong.isPlaying()) {
            themeSong.setVolume(30);
            themeSong.playLoop();
        }
    }
    
    /*
     * initialize boss/end room
     */
    private void initBossRoom() { 
        ArrayList<Room> endRooms = new ArrayList<Room>();
        for (Room room : Room.rooms) {
            if (room.getID() != 1 && room.getNeighbours() == 1) {
                endRooms.add(room);
            }
        }
        
        int idx = Utils.random(endRooms.size() - 1);
        Room room = endRooms.get(idx);
        Room bossRoom = null;
        // debug
        System.out.println("Room by boss room: " + room.getID());
        while (bossRoom == null) {
            float x = Utils.random();
            if (x <= 0.25f && room.getLeftRoom() == null) {
                bossRoom = new BossRoom(room.getRad()+1, null, room, null, null);
                room.setLeftRoom(bossRoom);
                room.addObject(new Door(room, bossRoom, "left"), 42, 200);
            } else if (x <= 0.5f && room.getRightRoom() == null) {
                bossRoom = new BossRoom(room.getRad()+1, room, null, null, null);
                room.setRightRoom(bossRoom);
                room.addObject(new Door(room, bossRoom, "right"), 598, 200);
            } else if (x <= 0.75f && room.getUpRoom() == null) {
                bossRoom = new BossRoom(room.getRad()+1, null, null, null, room);
                room.setUpRoom(bossRoom);
                room.addObject(new Door(room, bossRoom, "up"), 320, 32);
            } else if (x <= 1f && room.getDownRoom() == null) {
                bossRoom = new BossRoom(room.getRad()+1, null, null, room, null);
                room.setDownRoom(bossRoom);
                room.addObject(new Door(room, bossRoom, "down"), 320, 376);
            }
        } 
                
    } 
    
}
