package com.pawelsobieszczuk.skyrimperkcalculatorforordinator.models;

public class PerkInfo {

    private int[] skillLevel;
    private float x, y;

    public PerkInfo(int[] skill, float x, float y){
        skillLevel = skill;
        this.x = x;
        this.y = y;
    }

    public int[] getSkillLevel() {
        return skillLevel;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }
}
