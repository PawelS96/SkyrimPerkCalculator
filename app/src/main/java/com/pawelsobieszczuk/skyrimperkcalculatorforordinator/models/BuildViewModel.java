package com.pawelsobieszczuk.skyrimperkcalculatorforordinator.models;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.DatabaseHelper;
import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.PerkSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static com.pawelsobieszczuk.skyrimperkcalculatorforordinator.Utils.DEFAULT_BUILD_NAME;
import static com.pawelsobieszczuk.skyrimperkcalculatorforordinator.Utils.listToMap;

public class BuildViewModel extends ViewModel {

    private MutableLiveData<HashMap<String, Build>> allBuilds;
    private DatabaseHelper helper;

    public void setHelper(DatabaseHelper helper) {
        this.helper = helper;
    }

    private PerkSystem system;

    public void setSystem(PerkSystem system) {
        this.system = system;
    }

    public Build getBuild(String name) {

        if (allBuilds == null)
            load(system);

        if (allBuilds.getValue().containsKey(name))
            return allBuilds.getValue().get(name);
        else
            return getRandom();
    }

    public void load(PerkSystem system) {
        allBuilds = new MutableLiveData<>();

        List<Build> savedBuilds = helper.getAllBuilds(system);

        if (savedBuilds.isEmpty()) {

            Build newBuild = new Build(system);
            newBuild.setName(DEFAULT_BUILD_NAME);
            savedBuilds.add(newBuild);
        }
        allBuilds.setValue(listToMap(savedBuilds));
    }

    public HashMap<String, Build> getBuilds() {

        if (allBuilds == null)
            load(system);

        return allBuilds.getValue();
    }

    public Build getRandom(){
        List<String> keys = new ArrayList<>(allBuilds.getValue().keySet());

        int index = 0;
        if (keys.size() > 1)
        index = new Random().nextInt(keys.size()-1);
        return allBuilds.getValue().get(keys.get(index));
    }

    public void addToMap(Build build) {
        allBuilds.getValue().put(build.getName(), build);
    }

    public void deleteFromMap(Build build) {
        allBuilds.getValue().remove(build.getName());
    }

}
