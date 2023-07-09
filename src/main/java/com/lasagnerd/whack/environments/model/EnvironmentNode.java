package com.lasagnerd.whack.environments.model;

import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.treeStructure.SimpleNode;
import com.lasagnerd.whack.environments.toolWindow.EnvironmentsToolWindowFactory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EnvironmentNode extends SimpleNode {
    PathNode[] childrenNodes;

    public EnvironmentNode(Environment environment) {
        super();
        this.childrenNodes = new PathNode[environment.getPaths().size()];
        List<String> paths = environment.getPaths();
        for (int i = 0; i < paths.size(); i++) {
            String path = paths.get(i);
            childrenNodes[i] = new PathNode(path);
        }
        this.getPresentation().addText(environment.getName(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
    }

    @Override
    public SimpleNode @NotNull [] getChildren() {
        return childrenNodes;
    }

}
