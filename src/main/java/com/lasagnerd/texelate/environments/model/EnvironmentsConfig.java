package com.lasagnerd.texelate.environments.model;

import java.util.ArrayList;
import java.util.List;

public class EnvironmentsConfig {
    List<Environment> environments = new ArrayList<>();

    public EnvironmentsConfig() {
    }

    public void setEnvironments(List<Environment> environments) {
        this.environments = environments;
    }

    public List<Environment> getEnvironments() {
        return environments;
    }
}

