PrincessPeach extends Actor
- like toad
- single action, if handcuff keys, to end game

Bowser extends Enemy
- reset (enemy override) reset attacktarget, max heal, move to orig pos (save orig pos somehow)
- overrides weapon, no wander, drops handcuff keys
- add attackaction cons for passing in additional custom attack effects

PiranhaPlant extends Enemy
- overrides weapon, no wander follow (but auto attack) - override follow-add method in enemy
- reset (enemy override) +50 maxhp, max heal

FlyingKoopa extends Koopa
- only hp cons override in class

Enemy, Koopa, Goomba
- add new cons arg to Enemy, modify all subclasses incl. Koopa Goomba to use it
- attr is doesWander, usually set to true

HandcuffKeys extends Item
- cons, new status capability

VictoryAction extends Action
- ends the game

Fire
- bowser not immune, spawned on attack target, on successful hit, can stack up (dmg increases)

AttackAction
- spawns fire

Mature
- 5050 flying koopa on successful spawn attempt

Status
- new enums

HighGround (Wall, Tree)
- FlyingKoopa allowed to enter

WarpPipe
- spawn PiranhaPlant once at second turn at start of game
- spawn PiranhaPlant immediately after reset if nonexistent

### todo

Application
- spawn Peach Bowser in close vicinity on second map

bowser move to orig pos
pplant spawned with reset not attackable?

---

1. Princess Peach P 👸🍑  
Princess Peach is held captive by Bowser! Place her somewhere on a Dirt in the Lava zone, and she stands next to Bowser and cannot move, attack, or be attacked. Once you have defeated Bowser and obtained a key, you can interact with her to end the game with a victory message!

2. Bowser B 🐢😈  
Bowser will stand still, waiting for Mario on the second map. Once Mario stands next to him, Bowser will attack and follow Mario! He will attack whenever possible. Whenever Bowser attacks, a fire will be dropped on the ground that lasts for three turns. That fire will deal 20 damage per turn to anyone. When the Bowser is killed, it will drop a key to unlock Princess Peach's handcuffs.  
It has the following stats:  
500 hit points  
"punch" attack with a 50% hit rate and 80 damage.  
Resetting the game (r command) will move Bowser back to the original position, heal it to maximum, and it will stand there until Mario is within Bowser's attack range.

3. Piranha Plant Y 🐟🥀  
As mentioned in the previous requirement, Piranha Plant Y will spawn at the second turn of the game (note: the first turn is when you start the game for the first time). This plant will attack Mario when he stands next to it. Piranha Plant cannot move around. It will not follow anyone when it is engaged in a fight. It has the following stats:  
150 hit points  
"chomps" attack with 50% hit rate and 90 damage.  
Once the player kills it, the corresponding WarpPipe will not spawn Piranha Plant again until the player resets the game (r command). Resetting will increase alive/existing Piranha Plants hit points by an additional 50 hit points and heal to the maximum.

4. Flying Koopa F 💸🐢  
Flying Koopa F has pretty much similar characteristics as the original Koopa, except it has a bigger health bar (150 hit points). Furthermore, it can walk (fly) over the trees and walls when it wanders around. It also applies when Flying Koopa follows Mario. Like Koopa, it will go to a dormant state whenever it is unconscious until Mario brings a wrench to crack the shell (killing it)! Cracking its shell drops a Super Mushroom. The mature tree has a 50:50 chance of spawning either normal Koopa or Flying Koopa (after a successful 15% spawn rate).  
HINT: Please be aware of the LSP violation here. :)

Implementation Expectations
- Place Princess Peach somewhere on the second map, near Bowser.
- In the first map, after killing Piranha Plant, step onto the Wrap Pipe, and teleport to the second map.  
Turn 1 (when the game runs for the first time): C C C  
Turn 2: Y Y Y
- At first, Bowser does nothing until Mario approaches him (standing in Bowser's adjacent squares).
- After Bowser lands an attack, Mario needs to step out from that ground so that we can see a fire symbol v on that ground. Note: Bowser is not immune to its fire (you are welcome to make Bowser immune to its fire, but not against Fire Flower damage).
- Bowser will follow Mario and attack whenever possible. Mario may receive two kinds of damage within one turn: normal hit and fire damage.
- After defeating Bowser, we should be able to see a key k, pick it up, and there should be an action from Princess Peach to unlock and end the game. You are welcome to expand the ending game scenario (e.g., bring back Peach to the Mushroom Kingdom)
