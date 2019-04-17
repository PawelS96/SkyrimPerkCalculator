package com.pawelsobieszczuk.skyrimperkcalculatorforordinator.models;

import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.IPerk;
import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.PerkSystem;
import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.SkillEnum;
import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.SkillType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.pawelsobieszczuk.skyrimperkcalculatorforordinator.models.Perk.connectPerks;

public class Skill {

    private SkillEnum skillEnum;

    private HashMap<IPerk, Perk> perks = new HashMap<>();
    private int maxPerks;

    public HashMap<IPerk, Perk> getPerks() {
        return perks;
    }

    public SkillEnum getSkillEnum() {
        return skillEnum;
    }

    public SkillType getType() {
        return skillEnum.getType();
    }

    public Skill(SkillEnum skillEnum){
        this.skillEnum = skillEnum;
    }

    public int getSelectedPerksCount() {

        int count = 0;

        for (Perk p : perks.values()) {
            count += p.getState();
        }
        return count;
    }

    public int getRequiredSkillLevel() {

        int level = 0;

        for (Perk p : perks.values()) {
            if (!p.isSelected())
                continue;

            int perkSkillLevel = p.getSkillLevel();

            if (perkSkillLevel > level)
                level = perkSkillLevel;
        }
        return level;
    }

    public void setPerks(HashMap<IPerk, Perk> hashMap) {
        perks = hashMap;
        int count = 0;

        for (Perk p : perks.values())
            count += p.getMaxState();

        maxPerks = count;
    }

    public String getPerksCount() {
        return Integer.toString(getSelectedPerksCount()) + "/" + Integer.toString(maxPerks);
    }


    public List<Perk> getChildrenList() {

        ArrayList<Perk> list = new ArrayList<>();

        for (IPerk s : perks.keySet())
            list.add(perks.get(s));

        return list;
    }

    public void add(Perk n) {
        perks.put(n.getPerk(), n);
    }

    public Perk get(IPerk name) {
        return perks.get(name);
    }

    public static Skill buildSkill(SkillEnum skillEnum, PerkSystem perkSystem) {

        Skill skill = new Skill(skillEnum);

        HashMap<IPerk, Perk> perks = new HashMap<>();

        for (int i = 0; i < skillEnum.getPerks(perkSystem).length; i++) {
            Perk perk = new Perk(skillEnum.getPerks(perkSystem)[i]);
            perks.put(perk.getPerk(), perk);
        }

        for (IPerk start : skillEnum.getConnectionsMap(perkSystem).keySet()) {
            for (IPerk end : skillEnum.getConnectionsMap(perkSystem).get(start))
                connectPerks(perks.get(start), perks.get(end));
        }

        skill.setPerks(perks);
        return skill;
    }

    public static HashMap<IPerk, FPoint> getCoordinates(SkillEnum skill, PerkSystem system){

        HashMap<IPerk, FPoint> coordinates = new HashMap<>();

        for (IPerk p : skill.getPerks(system))
            coordinates.put(p, new FPoint(p.getPerkInfo().getX(), p.getPerkInfo().getY()));

        return coordinates;
    }
}
