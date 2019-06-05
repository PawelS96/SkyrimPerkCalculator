package com.pawels96.skyrimperkcalculator.enums.skills_vokrii;

import com.pawels96.skyrimperkcalculator.enums.IPerk;
import com.pawels96.skyrimperkcalculator.models.PerkInfo;

public enum Vok_Smithing implements IPerk {

    VOK_SMT_BASIC_SMITHING (0.4f, 0.95f, 0),
    VOK_SMT_ARMOR_PADDING (0.3f, 0.7f, 20),
    VOK_SMT_MERIC_SMITHING (0.7f, 0.75f, 30),
    VOK_SMT_DWARVEN_SMITHING (0.125f, 0.75f, 30),
    VOK_SMT_ARCANE_BLACKSMITH (0.55f, 0.6f, 40),
    VOK_SMT_ENGRAVED_SMITHING (0.2f, 0.4f, 50),
    VOK_SMT_PRIMAL_SMITHING (0.75f, 0.4f, 50),
    VOK_SMT_LAYERED_PLATES (0.35f, 0.45f, 60),
    VOK_SMT_CRYSTALLINE_SMITHING (0.9f, 0.25f, 70),
    VOK_SMT_EXOTIC_SMITHING (0.15f, 0.15f, 80),
    VOK_SMT_HIGH_YIELD_MINING (0.5f, 0.2f, 80),
    VOK_SMT_CONCEALED_ENCHANTMENTS (0.5f, 0.35f, 90),
    VOK_SMT_DAEDRIC_SMITHING (0.375f, 0.1f, 90),
    VOK_SMT_DRAGON_SMITHING (0.625f, 0.075f, 100);

    Vok_Smithing(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
    }
