package com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums;

import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.models.PerkInfo;

import java.io.Serializable;

public interface IPerk extends Serializable {
   PerkInfo getPerkInfo();
}
