Talkable interface
- getDialog - what's currently in TCA.execute(), actor to this
- random choice, but always once after %2 turn, tracked in implementer itself (instantiation)

PrincessPeach, Toad, Enemy implements Talkable
- add the tick attr and if(tick)process logic

Bowser, Goomba, Koopa, FlyingKoopa, PiranhaPlant extends Enemy
- impls getDialog, return a pvsf immutable List.of vals on call, or builds it if Toad
- for FlyingKoopa, create a mutable copy of pvsf list, add new vals, ret new list on call

Application
- static attr for Player

ToadConverseAction -> ConverseAction
- generalize action to work with all Talkables, but is still used only for Toad
- name also changed in a1_req6
