## Requirements

- Toad (O):
  - friendly actor
  - he stands in the middle of the map (on floor, between brick walls)
  - player has an action to speak with Toad
    - a random sentence printed to the console
- random dialog selection:
  - "The Princess is depending on you! You are our only hope."
  - "Being imprisoned in these walls can drive a fungus crazy :("
  - "You might need a wrench to smash Koopa's hard shells."
    - never printed if the player holds a Wrench (in inventory)
  - "You better get back to finding the Power Stars."
    - never printed when the Power Star effect is active (item consumed)

---

## Design Rationale

For this requirement, we follow the conventions already laid out in the other `game` classes, and we conform to the design decisions made in the other requirements.

We create a new `Toad` class, extending `Actor`, as had been done in requirement 5, as the toad acts very much like an actor with no behavior (self-initiated actions), which we could just leave as a no-op with the `DoNothingAction` class, and otherwise functions very much like any other NPC. Thus, it is best implemented as a subclass of `Actor` for reusability and substitutability.

We add a new (subclass of) `Action` to the list of `allowableActions` in `Toad`, named `ToadConverseAction`. This action adds the "talk" feature to toad, and its addition into the list adds another option with which to interact with the actor.

The action is not made more general, ie. as `ConverseAction`, because may of the conditional logic and dialog strings are not reusable. We also do not create another inheritance layer or interface as an abstraction for dialog-only actions, because it would not allow for much additional code reuse in terms of raw logic, and would not decrease coupling any further, as toad would still need a direct reference to its own `ToadConverseAction`, and there is no justifiable need to iterate between dialog-related actions in any class for the forseeable future.

The toad character is then added to the game through an `addActor` call to `GameMap` inside `Application`. The call is done in the aforementioned class, because Toad is a unique NPC with a lifetime that lasts for the entire length of the game. Thus, there is no need for any more specific class to manage the one `Toad` instance created at the start of the game. There may be need in the future to refactor the initial population of actors into its own manager class, for further logic, or multiple toad merchants in different areas or maps for in-game accessibility, but it should not be difficult as only the change of a single, atomic line in `Application` is necessary.

We also create a new `Status` value for the special effect of `Wrench` to destroy Koopa shells, used not only for the `DestroyShellAction` from requirement 3, but also for one of the checks inside `ToadConverseAction`. An alternative for both cases is to iterate through the `Actor`'s inventory and compare each `Item` to an instance of `Wrench`. This requires a lot more logic and code to perform, does not reuse the `hasCapability` of Actor.

---

## Notes

- follows conventions of current player/attackaction
- new toad class that extends actor
  - has no behavior - donothingaction
  - super-override allowableactions to add the talk action
  - toad will provide action when adjacent to player (any dir)
- a new toad-converse-action class that extends action
  - menudesc shows what prompt to show
  - exec runs the conditional logic laid out above
  - has attribute of actor, assigned in cons
    - uses capabilityset and items of actor
  - could use player, but no need for subclass specificity
- not named more general converse-action
  - exec logic is exclusive to toad
- application class adds toad through gamemap
  - like how goomba was added - fine because static/single spawn
- use new status from req4 for mushroom effect
  - player effects use cap-set
- also create new status for wrench
  - req says *hold*, but no concept of held item in engine
  - getweapon returns the first, might not be wrench
  - would need to get+iterate items, then get+iterate their actions
    - then compare equality of actions
  - adds a new layer of abstraction
  - adding a capability tag to wrench for req6 specifically solves this
- toad.allowableactions
  - calls super, collects return, appends to list, returns result itself
  - allows allowableactions in parent class to remain extensible
- hotkeys: optional, no good hotkey for dialog - don't assign
- no impl for behavior - not part of requirements
  - no good reason to perform converse as npc - only prints messages

Toad:
- cons -> actor.cons
- playturn override --> DoNothingAction
- allowableActions -> ToadConverseAction --> actionlist

ToadConverseAction:
- cons -> action.cons
- attribute -> Actor
- menuDescription --> String
- execute -> Player -> Actor hasCapability -> Item CapabilitySet / CapabilitySet -> Capable -> Status --> String

Application:
- GameMap, Toad -> Location -> GameMap -> ActorLocationsIterator -> Location, Actor

Wrench:
- Item -> addCapability -> Status
