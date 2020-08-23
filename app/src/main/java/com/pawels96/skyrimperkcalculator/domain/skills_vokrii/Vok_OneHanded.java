package com.pawels96.skyrimperkcalculator.domain.skills_vokrii;

import com.pawels96.skyrimperkcalculator.domain.IPerk;
import com.pawels96.skyrimperkcalculator.domain.PerkInfo;

public enum Vok_OneHanded implements IPerk {

    VOK_ONH_ONE_HANDED_MASTERY (0.5f, 0.95f, 0, 20, 40, 60, 80),
    VOK_ONH_DISCIPLINED_FIGHTER (0.9f, 0.75f, 20),
    VOK_ONH_OVERPOWERING_ASSAULT (0.275f, 0.55f, 30, 60),
    VOK_ONH_GRIEVOUS_WOUNDS (0.425f, 0.625f, 30, 60),
    VOK_ONH_DENTING_BLOWS (0.55f, 0.5f, 30, 60),
    VOK_ONH_FANGS (0.67f, 0.6f, 30, 60),
    VOK_ONH_DUAL_FLURRY (0.2f, 0.75f, 30, 60),
    VOK_ONH_FURIOUS_STRENGTH (0.85f, 0.5f, 40),
    VOK_ONH_VALOROUS_CHARGE (0.75f, 0.4f, 50),
    VOK_ONH_DUAL_SAVAGERY (0.1f, 0.5f, 60),
    VOK_ONH_CRATER_MAKER (0.875f, 0.35f, 60),
    VOK_ONH_EXECUTE (0.3f, 0.225f, 70),
    VOK_ONH_SHIELDBITER (0.425f, 0.275f, 70),
    VOK_ONH_DISRUPTING_STRIKE (0.525f, 0.2f, 70),
    VOK_ONH_SPITTING_COBRA (0.625f, 0.275f, 70),
    VOK_ONH_DISARMING_SLASH (0.75f, 0.2f, 80),
    VOK_ONH_BLADEDANCER (0.175f, 0.3f, 90),
    VOK_ONH_VICTORY_RUSH (0.475f, 0.05f, 100);

    Vok_OneHanded(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
    }