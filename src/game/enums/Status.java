package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`. It is also useful to give a `state` to abilities
 * or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    SUPER_MUSHROOM_EFFECT_ONGOING, // player has "super mushroom" effect until it gets hit by enemy
    JUMP_FREELY, // player can walk to high ground without jumping
    IMMUNITY, // player does not get damaged
    INSTANT_KILL_ENEMY, // successful attack will instantly kill enemies
    DESTROY_HIGHER_GROUND_TO_$5COIN,
    BREAKS_KOOPA_SHELL, // able to break Koopa's shell in its dormant state (eg. the wrench)
    POWER_STAR_EFFECT_ONGOING, // player has the "power star" effect for the next few turns

    INCREEASE_BASE_DAMAGE_BY_15, // increase actor base damage/intrinsic attack damage by 15
}
