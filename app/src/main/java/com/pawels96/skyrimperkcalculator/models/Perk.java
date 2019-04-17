package com.pawels96.skyrimperkcalculator.models;

import com.pawels96.skyrimperkcalculator.enums.IPerk;

import java.util.ArrayList;
import java.util.List;

public class Perk {

    public List<Perk> children = new ArrayList<>();
    public List<Perk> parents = new ArrayList<>();

    private IPerk perk;

    public Perk(IPerk iPerk) {
        perk = iPerk;
        maxState = perk.getPerkInfo().getSkillLevel().length;
    }

    public IPerk getPerk() {
        return perk;
    }

    private int state = 0;
    private int maxState;
    private boolean stateIncreasing = true;

    public boolean isSelected() {
        return state != 0;
    }

    public boolean isMultiState() {
        return maxState > 1;
    }

    public int getSkillLevel() {
        return perk.getPerkInfo().getSkillLevel()[state - 1];
    }

    public String getAllSkillLevels() {
        StringBuilder sb = new StringBuilder();

        int levels = perk.getPerkInfo().getSkillLevel().length;

        for (int i = 0; i < levels; i++) {
            sb.append(Integer.toString(perk.getPerkInfo().getSkillLevel()[i]));
            if (i < levels - 1)
                sb.append(", ");
        }
        return sb.toString();
    }

    public int getState() {
        return state;
    }

    public int getMaxState() {
        return maxState;
    }

    public String getStateAsString() {
        return " (" + Integer.toString(state) + "/" + Integer.toString(maxState) + ")";
    }

    private boolean hasParent() {
        return !parents.isEmpty();
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public static void connectPerks(Perk start, Perk end) {
        start.children.add(end);
        end.parents.add(start);
    }

    private boolean isNoParentConnected() {
        for (Perk n : parents)
            if (n.isSelected())
                return false;
        return true;
    }

    public void setState(int st) {
        state = st;
        onStateChanged();
    }

    private void onStateChanged() {

        if (state == 0)
            stateIncreasing = true;
        else if (state == maxState)
            stateIncreasing = false;

        if (isSelected()) {
            if (!hasParent())
                return;
            if (isNoParentConnected())
                parents.get(0).setState(1);
        }
        else {

            if (!hasChildren())
                return;

            for (Perk n : children) {
                if (n.isNoParentConnected())
                    n.setState(0);
            }
        }
    }

    public void nextState() {

        if (stateIncreasing)
            state++;
        else
            state--;

        onStateChanged();
    }

    public static boolean areNodesSelected(Perk start, Perk end) {
        return start.isSelected() && end.isSelected();
    }

}
