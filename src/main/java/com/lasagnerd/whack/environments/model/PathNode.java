package com.lasagnerd.whack.environments.model;

import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.treeStructure.SimpleNode;
import org.jetbrains.annotations.NotNull;

public class PathNode extends SimpleNode {

    private final String path;

    public PathNode(String path) {
        super();
        this.path = path;
        this.getPresentation().addText(path, SimpleTextAttributes.REGULAR_ATTRIBUTES);
    }

    @Override
    public SimpleNode @NotNull [] getChildren() {
        return new SimpleNode[0];
    }

    public String getPath() {
        return path;
    }
}
