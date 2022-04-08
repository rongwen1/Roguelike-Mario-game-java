- Goomba (g):
  - 20 HP - attack: Kick, 10 damage, 50% hit rate
  - 10% chance: suicide (removed from the map) - to clean up the map
  - spawns randomly from sprouts (Sprout, req 1)
  - removed from map if defeated/unconscious

- Koopa (K):
  - 100 HP - attack: Punch, 30 damage, 50% hit rate
  - spawns randomly from trees (Mature, req 1)
  - Is not removed upon defeat (unconscious)
    - no attack action
    - goes into dormant state, with display character (D)
    - stays on the ground, can not attack nor move (follow/wander)
  - Shell can only be destroyed with a Wrench
    - must stand next to (D), no action shown otherwise
    - Wrench: 80% hit rate, 50 DMG
    - drops Super Mushroom after destroying (D replaced with ^)

- all enemies:
  - cannot enter the Floor (_)
  - is engaged in a fight when player approaches and enemy attacks, or player initiates the attack
  - will follow the player when engaged in a fight

---

draft

- shell is destroyed on its own wrench action
- direct call to getIntrinsicWeapon which uses constructor
  - only damage and verb defined
  - IW cons uses hitrate 50, both attacks have the same value in req

Floor:
- add canActorEnter override, block all actors

SuicideAction:
- 

ShellDestroyAction:
- 

Goomba:
- getIntrinsWep override, to impl Kick
- 

Koopa:
- getIntrinsWep override, to impl Punch

WrenchWeapon:

