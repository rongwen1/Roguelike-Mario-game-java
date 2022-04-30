## Requirements

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

## Design Rationale

Per the requirements, enemies may not enter `Floor` as part of any of their actions. Thus, a new layer of abstraction `Enemy` was added, so `Floor` would not have to depend on each individual enemy in its checks for `canActorEnter`, creating high substitutability and lower coupling. The additional `Enemy` inheritance layer also helps for code reuse, as many of the enemy classes share the same features.

We also refactor the `target.isConscious()` conditional in `AttackAction` into its own class, `DefeatAction`. The first reason is that there is no reason for the attacker that called the `Action` to be the one to handle the logic for handling unconscious actors, breaking segregation. The second is that `AttackAction` should not be responsible for this to begin with, breaking single responsibility on top of its now misleading name. The third, used for the requirement, is that it allows for us to create a behavioral override to what happens to an actor when it is unconscious.

In its place, we might create a behavior which conditionally executes `DefeatAction`, a behavior which all relevant `Actor` subclasses should populate their behaviors with in their constructors. There is still a significant issue with this method - there is a time lag between an actor being killed and it being considered dead. This is due to a limitation with the engine - there isn't a good way to force a `playTurn` event for an arbitrary actor.

The solution ultimately used is to keep the resultant `Action` inside `AttackAction`'s attributes, such as `DefeatAction` or `DormantAction`, and have the correct action to perform on `target`'s death be passed into `AttackAction` through its constructor. This would result in the need to pass in the action on defeat to all actions potentially defeating an actor, but allows only the `Enemy` subclasses overriding the on-defeat action to have the need for modification.

By having the `Enemy` control its own behavior on defeat, we would be able to override the default behavior, and replace it with ie. `DormantAction` for `Koopa`s with minimal coupling. The `DefeatAction` refactor also allows us to create `SuicideBehavior`, extending `DefeatAction`, which calls its super conditionally. This may be undesirable if the goal is to delete `Goomba` without having it drop its items, but it should be easy to override its `execute()` to eg. delete its inventory before calling `super()` on itself.

We create a new `DormantKoopa` class in addition to `Koopa`, because both actors have vastly different behaviors, most of which are inherited from `Enemy` in `Koopa`, so it would be easier to think of them as separate entities. The `SuperMushroom` drop is also kept inside the dormant koopa, dropped upon defeat by `DestroyShellAction`, which refers to `DefeatAction` with a different menu description.

---

## Notes

- direct call to getIntrinsicWeapon which uses IW's constructor
  - only damage and verb explicitly defined, not hit rate
  - cons uses hitrate 50, both attacks have the same value in req
- wrench provides BREAKS_KOOPA_SHELL status/capability (also used in req6)
  - comparatively difficult to iterate items + capabilities
  - killing koopa is only allowed if this status exists
- add another inheritance layer Emeny - share many of the same logic
- refactor AttackAction logic into DefeatAction - for reuse
- refactor Goomba logic into Enemy - for reuse
- Goomba.playTurn - HashMaps have no ordering, priority is useless
  - replace it with a SparseArray - functionally similar
- refactor AttackAction to not perform the conscious check at all
  - do it in DefeatAction
  - actor specific behaviors are split into classes to prevent coupling
  - keep result builder inside AttackAction.execute()
- DormantKoopa is not under Enemy, as it does not contain enemy AI

Floor:
- add canActorEnter override, block all enemies

DefeatAction:
- extends Action
- execute: logic lifted from AttackAction

DestroyShellAction:
- extends DefeatAction
- only one override, to menuDescription

SuicideBehavior:
- impls Behavior
- getAction notnull-condition: Random rolls 10%
  - returns DefeatAction

DormantAction:
- extends Action
- execute -> DormantKoopa (replaces itself with)

AttackBehavior:
- impls Behavior
- getAction notnull-condition: is tracking + close to player
  - returns AttackAction

AttackAction:
- extends Action
- attribute/cons: defeatedAction
- execute: isConscious conditional block replaced with call to attr
- both extends and owns an Action

WanderBehavior, FollowBehavior: no changes

Enemy:
- abstract class for Goomba and Koopa, extends Actor
- attributes: Behavior
- cons, playTurn, allowableActions: same as in Goomba
  - except hp/icon/name for cons
  - creates AttackAction with DefeatAction
  - and fix the behavior logic for playTurn (see above)

Goomba:
- extends Enemy
- cons: add SuicideBehavior behavior
- getIntrinsWep: override to add Kick

Koopa:
- extends Enemy
- getIntrinsWep: override to add Punch
- override allowableActions' AttackAction with DormantAction

DormantKoopa:
- extends Actor
- no behavior
- has SuperMushroom in inventory (as a drop upon defeat)
  - creates it in cons
- allowableActions: DestroyShellAction
  - checks if player has this capability
  - allowableActions -> Actor, Status

DestroyShellAction:
- extends DefeatAction
- changes only menuDescription

Wrench:
- new relevant Status enum entry

Mature, Sprout:
- create new Goomba, Koopa instances respectively at random
