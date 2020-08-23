package com.pawels96.skyrimperkcalculator.domain.skills_vokrii;

import com.pawels96.skyrimperkcalculator.domain.IPerk;
import com.pawels96.skyrimperkcalculator.domain.PerkInfo;

public enum Vok_Block implements IPerk {

    VOK_BLC_BLOCK_MASTERY (0.5f, 0.95f, 0, 20, 40, 60, 80),
    VOK_BLC_DEFLECT_ARROWS (0.5f, 0.65f, 20),
    VOK_BLC_POWER_BASH (0.8f, 0.8f, 20),
    VOK_BLC_UNWAVERING_DEFENSE (0.1f, 0.6f, 30),
    VOK_BLC_WEAPON_BLOCK (0.2f, 0.85f, 30),
    VOK_BLC_QUICK_REFLEXES (0.7f, 0.6f, 40),
    VOK_BLC_TORCH_BASH (0.9f, 0.65f, 40),
    VOK_BLC_DEADLY_BASH (0.875f, 0.5f, 50, 80),
    VOK_BLC_ELEMENTAL_PROTECTION (0.3f, 0.45f, 50),
    VOK_BLC_POKE_THE_DRAGON (0.225f, 0.25f, 60),
    VOK_BLC_BLOCK_RUNNER (0.45f, 0.4f, 70),
    VOK_BLC_MOCKING_BLOW (0.85f, 0.3f, 80),
    VOK_BLC_DRAGON_TAIL (0.7f, 0.25f, 90),
    VOK_BLC_SHIELD_CHARGE (0.5f, 0.25f, 90),
    VOK_BLC_STONEHEART (0.45f, 0.1f, 100);

    Vok_Block(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
    }