package com.lasagnerd.whack.environments.model;

import com.intellij.ui.treeStructure.SimpleNode;
import org.jetbrains.annotations.NotNull;

public class ConfigNode extends SimpleNode {
    EnvironmentNode[] childrenNodes;

    public ConfigNode(EnvironmentsConfig environmentsConfig) {
        super();
        var environments = environmentsConfig.getEnvironments();
        this.childrenNodes = new EnvironmentNode[environments.size()];
        for (int i = 0; i < environments.size(); i++) {
            Environment environment = environments.get(i);
            childrenNodes[i] = new EnvironmentNode(environment);
        }
    }

    @Override
    public SimpleNode @NotNull [] getChildren() {
        return childrenNodes;
    }
}
