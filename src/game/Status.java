package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    SUPER_MUSHROOM_WILLBECHANGEDLATER,
    JUMP_FREELY, // player can walk to high ground without jumping
    INVINSIBLE, // player does not get damaged
    BREAKS_KOOPA_SHELL, // able to break Koopa's shell in its dormant state (eg. the wrench)
    POWER_STAR_EFFECT_ONGOING, // player has the "power star" effect for the next few turns
}
