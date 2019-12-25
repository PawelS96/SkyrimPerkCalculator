package com.pawels96.skyrimperkcalculator.models;

import com.pawels96.skyrimperkcalculator.enums.IPerk;
import com.pawels96.skyrimperkcalculator.enums.PerkSystem;
import com.pawels96.skyrimperkcalculator.enums.SkillEnum;
import com.pawels96.skyrimperkcalculator.enums.SkillType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pawels96.skyrimperkcalculator.enums.SkillType.COMBAT;
import static com.pawels96.skyrimperkcalculator.enums.SkillType.MAGIC;
import static com.pawels96.skyrimperkcalculator.enums.SkillType.STEALTH;
import static com.pawels96.skyrimperkcalculator.models.Skill.buildSkill;

public class Build {

    private String name;
    private String description = null;
    private PerkSystem perkSystem;

    private Map<SkillEnum, Skill> skills;

    public void setPerkSystem(PerkSystem perkSystem) {
        this.perkSystem = perkSystem;
    }

    public PerkSystem getPerkSystem() {
        return perkSystem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Skill getSkill(SkillEnum name){
        return skills.get(name);
    }

    public Build(PerkSystem perkSystem){

        skills = new HashMap<>();

        setPerkSystem(perkSystem);

        for (SkillEnum s : SkillEnum.values())
            skills.put(s, buildSkill(s, perkSystem));

    }

    public List<Skill> getSkills(){

        List<Skill> list = new ArrayList<>();

        for (SkillEnum s : SkillEnum.values()) {
            list.add(getSkill(s));
        }

        return list;
    }

    public Map<SkillType, Integer> getPerkDistribution(){

        int stealth = 0;
        int magic = 0;
        int combat = 0;

        for (Skill s : skills.values()){

            switch (s.getType()){

                case MAGIC:
                    magic += s.getSelectedPerksCount();
                    break;
                case COMBAT:
                    combat += s.getSelectedPerksCount();
                    break;
                case STEALTH:
                    stealth += s.getSelectedPerksCount();
                    break;
            }
        }

        Map<SkillType, Integer> map = new HashMap<>();

        map.put(STEALTH, stealth);
        map.put(MAGIC, magic);
        map.put(COMBAT, combat);

        return map;
    }

    public int getSelectedPerksCount(){

        int count = 0;

        for (Skill s : skills.values())
            count += s.getSelectedPerksCount();

        return count;
    }

    public int getRequiredLevel(float multiplier){

        int lvl = 1;
        float perks = 0;

        while (perks < getSelectedPerksCount()){
            perks += multiplier;
            lvl++;
        }
        return lvl;
    }

    public static Build clone(Build objToClone){

        Build build = new Build(objToClone.getPerkSystem());

        for (SkillEnum s : SkillEnum.values()) {
            for (IPerk p : objToClone.getSkill(s).getPerks().keySet()) {
                int state = objToClone.getSkill(s).get(p).getState();
                build.getSkill(s).get(p).setState(state);
            }
        }
        return build;
    }
}
