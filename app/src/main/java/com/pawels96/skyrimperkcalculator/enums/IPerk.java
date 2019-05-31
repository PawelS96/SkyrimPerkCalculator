package com.pawels96.skyrimperkcalculator.enums;

import com.pawels96.skyrimperkcalculator.models.PerkInfo;

import java.io.Serializable;

/**
 * Interface implemented by all perk enums.
 * Returns an object which contains X and Y coordinates of a perk and required skill levels.
 */

public interface IPerk extends Serializable {
   PerkInfo getPerkInfo();
}
