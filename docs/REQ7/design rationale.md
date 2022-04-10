## Design Rationale

We reuse the provided `Resettable` interface and `ResetManager` singleton class to implement the partial game reset feature. All classes relevant to the reset implement `Resettable`. This includes `Player` itself, the one who initiated the action, who would reset his own effects and restore to full health.

Since we have already made the classes `Coin` and `Enemy` from other requirements, we can just have them implement `Resettable` instead, reducing connascence with other enemy subclasses, or the parent `Item` class, especially since other items in the world are not affected.

We then add a `ResetAction` extending `Action` to implement the hotkey override feature and present a menu prompt. It then executes run() in `ResetManager` to loop through all the stored `Resettable`s to execute their relevant methods.

Inside `Player`'s `ResetInstance`, we also set a flag in player's attributes to prevent the `ResetAction` from showing up in its `allowableActions` list again, through a conditional check for this flag in `Player`'s `allowableActions` override.

Inside `Tree`, we store its `Location` as an attribute on a call to `tick()`, instead of setting a flag to reset the `Ground` it is standing on to `Dirt` inside `tick()`. This way, we get to reference `Location` and do the logic inside `resetInstance` instead.

This prevents a race condition where the tree may stick around for an extra turn if `tick()` somehow ran before `resetInstance` gets to set the relevant flag, if the logic surrounding the calls to both magic were to be changed in the future. This creates a smaller connascence of timing by omitting the need to keep any of the two methods in mind when modifying the calling logic to the other.
