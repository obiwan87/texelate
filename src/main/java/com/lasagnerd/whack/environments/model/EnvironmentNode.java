package com.lasagnerd.whack.environments.model;

import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.treeStructure.SimpleNode;
import com.lasagnerd.whack.environments.toolWindow.EnvironmentsToolWindowFactory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EnvironmentNode extends SimpleNode {
    public List<PathNode> childrenNodes = new ArrayList<>();

    public EnvironmentNode(Environment environment) {
        super();
        List<String> paths = environment.getPaths();
        for (String path : paths) {
            childrenNodes.add(new PathNode(path));
        }
        this.getPresentation().addText(environment.getName(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
    }


    @Override
    public SimpleNode @NotNull [] getChildren() {
        return childrenNodes.toArray(new SimpleNode[0]);
    }
}
