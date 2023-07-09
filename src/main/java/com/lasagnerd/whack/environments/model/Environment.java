package com.lasagnerd.whack.environments.model;

import java.util.ArrayList;
import java.util.List;

public class Environment {
    String name;
    List<String> paths = new ArrayList<>();

    public Environment() {
    }

    public Environment(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }
}
