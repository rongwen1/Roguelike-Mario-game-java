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

draft

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
- GameMap -> addActor -> Toad
