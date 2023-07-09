package com.lasagnerd.whack.environments.model;

import com.intellij.ui.treeStructure.SimpleNode;
import com.lasagnerd.whack.environments.toolWindow.EnvironmentsToolWindowFactory;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Vector;

public class NodeTreeNode extends DefaultMutableTreeNode {
    public NodeTreeNode(SimpleNode userObject) {
        super(userObject);

        this.children = new Vector<>(userObject.getChildren().length);
        for (SimpleNode child : userObject.getChildren()) {
            NodeTreeNode e = new NodeTreeNode(child);
            e.setParent(this);
            this.children.add(e);
        }
    }

    public <T> T node() {
        return (T) super.getUserObject();
    }
}
