package com.lasagnerd.whack.environments.model;

import com.jgoodies.common.collect.ArrayListModel;

import java.util.List;

public class EnvironmentsConfig {
    List<Environment> environments = new ArrayListModel<>();

    public EnvironmentsConfig() {
    }

    public void setEnvironments(List<Environment> environments) {
        this.environments = environments;
    }

    public List<Environment> getEnvironments() {
        return environments;
    }
}

