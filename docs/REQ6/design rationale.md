> Toad (O) is a friendly actor

> and he stands in the middle of the map

> You will have an action to speak with Toad.

> When you interact with Toad, you should see a random sentence printed on the console.
> Other than these two scenarios, he will randomly pick a monologue above.
> "The Princess is depending on you! You are our only hope."
> "Being imprisoned in these walls can drive a fungus crazy :("

> "You might need a wrench to smash Koopa's hard shells."
> Once the player holds a Wrench, he won't' say the first sentence.
> Get wrench, and try to talk with Toad several times. It must not print the first sentence.

> "You better get back to finding the Power Stars."
> When the Power Star effect is active, the second monologue must not be printed in the console.
> Consume Power Star, and try to talk with Toad several times. It must not print the second sentence.

---

- new toad class that extends actor
    - playturn is donothingaction
    - super-override allowable-actions to add the talk action
- a new toad-converse-action class that extends action
    - menudesc shows what prompt to show
    - exec runs the conditional logic laid out above
    - contains the player obj to help determine exec
      - actor involves
    - uses capabilityset and items of actor of player
- not named more general converse-action
    - exec logic is exclusive to toad
- floor class adds toad through gamemap
    - involves toad/gamemap in floor, as would be for goomba
- follows conventions of current player/goomba/attackaction

