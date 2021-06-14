------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Mama's Dungeon
PURPOSE OF PROJECT: Grade 11 Computer Science Final Project
VERSION or DATE: June 11 2021
HOW TO START THIS PROJECT: Open it with greenfoot
AUTHORS: Eric Zhang
USER INSTRUCTIONS: WASD to move, left-click to attack

[May 13 2021]
I grabbed some sprite packs from itch.io
-https://andelrodis.itch.io/simple-ui-asset-pack-57
-https://0x72.itch.io/dungeontileset-ii
Then I created a main menu with start button that links to my so-far blank starter room. 

[May 18 2021]
For some reason I didn't work on the game for five days. Anyways, this day I finished basic room layout and got to spawning in a player with  WASD movement and an idle and run animation. I also added a Vector2 class which acts similar to the one in Unity for a little bit of simplicity in my code.

[May 20 2021]
I created some door sprites with photoshop to move the player between different rooms which were actually different worlds. The player glitched and repeatedly went back and to a room. I also ran into a few other issues with moving the rooms. I had to manually set all of the player's stats each time the room moved, but now that I'm writing this I think using some static variables may have been better. Also, I added an entity class and redid the player to fit under the entity class. 
I had to spend a bit of time thinking of how I was going to generate rooms without crashing my computer (millions of rooms by chance). I ended up using a 'max distance' so that the total layout of the room would be limited to something in the shape of a circle. Also had a problem the starter room would have no neighbours by a small chance. To fix this, I just repeated the room generation until there was more than 1 room. Then I created a healthbar and got started on enemies. 

[May 25 2021]
After my player is within a certain range of the enemy, the enemy would follow the player and try to keep a certain distance from the player to make the enemy movement and attacks feel more natural. I easily added a zombie subclass with object oriented programming because I only needed to call the parent functions with the zombie's image files. 

[May 28 2021]
Since I based an entity's position based off it's Greenfoot location ( getX() and get() ), I had trouble keeping track of movement so I reworked all of the movement in the game to base Greenfoot location off of entity position variable. Then I used floats instead of integers then rounded the floats whenever they were used with setLocation(). 
My enemies could not attack the player, I made a section for both melee and ranged, but I didn't have enough time to make ranged attacks (totally not because I got lazy and forgot until now). So the enemies would run towards the player, attack it, then run back to their previous position. 

[May 29 2021]
I created a weapon that follows its user and animates. I say user because I wanted to make weapons for both the player and enemies, but I eventually didn't get to that because the enemies didn't really need weapons. The weapon for the player would try to follow the mouse but be limited to a certain location around the plyer. It didn't do damage because I didn't add collision yet. 

[May 31 2021]
I made the enemies able to die after being defeated and added some semi-balanced stats to the zombies. I created an enemy room subclass of room so that some rooms would specifically have enemies and others would be empty or having something else. I originally planned on making multiple types of enemy rooms (some for undead, fire, etc.) based on the assets I got from itch.io, but I ended up deciding there would be too much balance testing and I didn't have that kind of time (totally not lazy). 

[June 1 2021]
I ran into an error with my mouse where moving my mouse offscreen would cause Greenfoot to yell at me. The solution ended up with checking if the mouse was null when I moved my weapon. Then I created an boss room with a fightable boss that follows you and spawns mobs in different phases. 

[June 2 2021]
I finished the game! Well, technically. I had to fix the player's weapon because whenever it switched from one side to the other side it would look like a glitch, so I photoshopped it to keep the sword in the middle. But, this problem came with a large attack collision where enemies behind the weapon would still take damage from the weapon's collision box. I grabbed some visual-based collision code from the internet (https://www.greenfoot.org/topics/60742/0) and felt pretty good with the results. Then I put some things together in photoshop and created a death and win screen. The player could die and lose, and also defeat the boss and win. When the player wins, it would play something like an animation so I created a WinWorld and WinPlayer for this. 

[June 7 2021]
I didn't feel too much motivation to work on the game after finishing (I totally didn't skip 5 days of working on this). This day, I added two new enemies and play tested their stats. Then I also added an introduction or story which talks about potatoes. I also made enemies turn over on their side and wait a few seconds before they were deleted to give off a "death animation" feel. 
When I was going through images in the pixel art dungeon asset pack, I saw some floor spike images and decided to create a trap-room which would spawn floor spikes that turn on and off. I had to use the collision code I found earlier because the spikes it hurt the player even though the player wasn't visually touching the spikes. Next, I added health potions and coins which would increase the player's health and attack damage respectively. They could be collected after an enemy dies. 
I don't remember if it was this day that I fixed this issue, but I had an issue where the player's health would somehow go back to 100 in the boss room. I also don't remember how I fixed it, but I think it had something to do with a typo or using the wrong variable somewhere. 

[June 11 2021]
I honestly didn't want to add anything else but decided to add a scoreboard for replayability. The scoreboard would be shown after the player beats the game giving their time, kills, coins and health potions collected. I had trouble with getting the text to move along with the scroll I pulled from the internet but I worked it out with an offset vector. 