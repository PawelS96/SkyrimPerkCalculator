package com.pawels96.skyrimperkcalculator.enums;

import com.pawels96.skyrimperkcalculator.models.PerkInfo;

import java.io.Serializable;

public interface IPerk extends Serializable {
   PerkInfo getPerkInfo();
}
