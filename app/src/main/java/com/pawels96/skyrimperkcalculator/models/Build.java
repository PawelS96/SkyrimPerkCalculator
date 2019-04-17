package com.pawelsobieszczuk.skyrimperkcalculatorforordinator.models;

import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.IPerk;
import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.PerkSystem;
import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.SkillEnum;
import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.SkillType;

import java.util.HashMap;

import static com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.SkillType.COMBAT;
import static com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.SkillType.MAGIC;
import static com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.SkillType.STEALTH;
import static com.pawelsobieszczuk.skyrimperkcalculatorforordinator.models.Skill.buildSkill;

public class Build {

    private PerkSystem perkSystem;

    public void setPerkSystem(PerkSystem perkSystem) {
        this.perkSystem = perkSystem;
    }

    public PerkSystem getPerkSystem() {
        return perkSystem;
    }

    private HashMap<SkillEnum, Skill> skills;

    private String name;

    public String getName() {
        return name;
    }

    private String description = null;

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

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<SkillType, Integer> getPerkDistribution(){

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

        HashMap<SkillType, Integer> map = new HashMap<>();

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

    public static Build cloneB(Build objToClone){

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
