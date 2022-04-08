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

- both enemies:
  - cannot enter the Floor (_)
  - become engaged in a fight when either:
    - player approaches and enemy attacks
    - player initiates the attack
  - will follow the player when engaged in a fight

---

draft

- direct call to getIntrinsicWeapon which uses IW's constructor
  - only damage and verb explicitly defined, not hit rate
  - cons uses hitrate 50, both attacks have the same value in req
- wrench provides CAN_KILL_KOOPA status/capability (also used in req6)
  - comparatively difficult to iterate items + capabilities
- add another inheritance layer Emeny - share many of the same logic
- refactor AttackAction logic into DefeatAction - for reuse
- refactor Goomba logic into Enemy - for reuse
- logic in Goomba.playTurn will not work as intended
  - HashMaps (as used in behavior) have no ordering, thus priority is useless
  - replace it with a SparseArray - functionally similar but is ordered
- refactor AttackAction to not perform the conscious check at all
  - do it in UnconsciousBehavior
  - rationale: so checks can be made in Enemy *after* the turn
  - allows for higher priority behaviors as overrides, ie. DormantBehavior
- Enemy behavior priority:
  - Suicide / Dormant -> Unconscious -> Attack -> Follow -> Wander

Floor:
- add canActorEnter override, block all enemies

SuicideBehavior:
- extends Action, impls Behavior
- execute -> DefeatAction (on itself)
- getAction notnull-condition: Random rolls 10%

DefeatAction:
- extends Action
- execute
  - same logic as from AttackAction
  - if Koopa, use GameMap to replace with DormantKoopa

UnconsciousBehavior:
- extends Action, impls Behavior
- getAction notnull-condition: Actor -> isConscious
- execute -> DefeatAction (on itself)

DormantBehavior:
- extends Action, impls Behavior
- getAction notnull-condition: Actor -> isConscious
- execute -> DormantKoopa (replace itself with)

AttackBehavior:
- extends Action, impls Behavior
- getAction notnull-condition: is tracking + close to player
- execute -> AttackAction

WanderBehavior, FollowBehavior: no changes

DestroyShellAction:
- extends Action
- execute -> DefeatAction

Enemy:
- abstract class for Goomba and Koopa, extends Actor
- attributes: Behavior
- cons, playTurn, allowableActions: same as in Goomba
  - except hp/icon/name for cons
  - and fix the behavior logic for playTurn (see above)
  - also has UnconsciousBehavior

Goomba:
- extends Enemy
- cons: add SuicideBehavior behavior
- getIntrinsWep: override to add Punch

Koopa:
- extends Enemy
- getIntrinsWep: override to add Kick

DormantKoopa:
- extends Actor
- no behavior
- has SuperMushroom in inventory (as a drop upon defeat)
- allowableActions: DestroyShellAction

Wrench:
- new relevant Status enum entry

Mature, Sprout:
- create new Goomba, Koopa instances respectively at random
