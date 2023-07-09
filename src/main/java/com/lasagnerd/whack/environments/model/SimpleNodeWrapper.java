package com.lasagnerd.whack.environments.model;

import com.intellij.ui.treeStructure.SimpleNode;

import javax.swing.tree.DefaultMutableTreeNode;

public abstract class SimpleNodeWrapper<T extends SimpleNode> extends DefaultMutableTreeNode {
    public SimpleNodeWrapper() {
        super();
    }

    public abstract T createSimpleNode();

    @Override
    public Object getUserObject() {
        return createSimpleNode();
    }
}
