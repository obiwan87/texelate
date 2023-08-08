package com.lasagnerd.texelate.environments.model;

import com.intellij.ui.treeStructure.SimpleNode;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Vector;

public class EnvironmentsConfigNode extends SimpleNodeWrapper<SimpleNode> {
    public EnvironmentsConfigNode(EnvironmentsConfig environmentsConfig) {
        super();

        var environments = environmentsConfig.getEnvironments();
        this.children = new Vector<>();
        for (Environment environment : environments) {
            EnvironmentNode environmentNode = new EnvironmentNode(environment);
            environmentNode.setParent(this);
            children.add(environmentNode);
        }
    }

    @Override
    public SimpleNode createSimpleNode() {
        return new SimpleNode() {
            @Override
            public SimpleNode @NotNull [] getChildren() {
                return children.stream()
                        .map(c -> (SimpleNodeWrapper<?>) c)
                        .map(SimpleNodeWrapper::createSimpleNode)
                        .toArray(SimpleNode[]::new);
            }
        };
    }

    public List<EnvironmentNode> environmentNodes() {
        return children.stream()
                .map(c -> (EnvironmentNode) c)
                .toList();
    }
}
