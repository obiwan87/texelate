package com.lasagnerd.whack.environments.model;

import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.treeStructure.SimpleNode;
import com.lasagnerd.whack.Icons;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Vector;

public class EnvironmentNode extends SimpleNodeWrapper<SimpleNode> {
    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    private String environmentName;
    public EnvironmentNode(Environment environment) {
        super();
        this.setEnvironmentName(environment.getName());
        children = new Vector<>();
        List<String> paths = environment.getPaths();
        for (String path : paths) {
            FilePathNode childNode = new FilePathNode(path);
            childNode.setParent(this);
            children.add(childNode);
        }
    }

    @Override
    public SimpleNode createSimpleNode() {
        SimpleNode simpleNode = new SimpleNode() {
            @Override
            public SimpleNode @NotNull [] getChildren() {
                return children.stream()
                        .map(c -> (SimpleNodeWrapper<?>) c)
                        .map(simpleNodeWrapper -> simpleNodeWrapper.createSimpleNode())
                        .toArray(SimpleNode[]::new);
            }
        };

        simpleNode.getPresentation().addText(getEnvironmentName(),
                SimpleTextAttributes.REGULAR_ATTRIBUTES);
        simpleNode.getPresentation().setIcon(Icons.environmentIcon);
        return simpleNode;
    }
}
