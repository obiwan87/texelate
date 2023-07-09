package com.lasagnerd.whack.environments.model;

import com.intellij.ui.treeStructure.SimpleNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ConfigNode extends SimpleNode {
    public List<EnvironmentNode> childrenNodes;

    public ConfigNode(EnvironmentsConfig environmentsConfig) {
        super();
        var environments = environmentsConfig.getEnvironments();
        this.childrenNodes = new ArrayList<>();
        for (int i = 0; i < environments.size(); i++) {
            Environment environment = environments.get(i);
            childrenNodes.add(new EnvironmentNode(environment));
        }
    }

    @Override
    public SimpleNode @NotNull [] getChildren() {
        return childrenNodes.toArray(new SimpleNode[0]);
    }
}
