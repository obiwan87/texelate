package com.lasagnerd.whack.environments.model;

import com.intellij.openapi.project.Project;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.List;

// Provide in memory representation of currently loaded environments
// The easiest approach is to directly provide the tree model here,
// such that the tree view is updated automatically when the model changes.

public class EnvironmentsModelService implements TreeModelListener {
    private final EnvironmentsConfigPersistentState persistentState;
    private DefaultTreeModel defaultTreeModel;

    public EnvironmentsModelService(Project project) {
        persistentState = project.getService(EnvironmentsConfigPersistentState.class);
    }

    public DefaultTreeModel getTreeModel() {
        if (defaultTreeModel == null) {;
            ConfigNode root = new ConfigNode(persistentState.environmentConfig);
            defaultTreeModel = new DefaultTreeModel(new NodeTreeNode(root));
            defaultTreeModel.addTreeModelListener(this);
        }
        return defaultTreeModel;
    }

    public void addEnvironment(String name) {


    }

    public void addItemToEnvironment(String environmentName, String path) {

    }

    public void removeEnvironment(String name) {

    }

    public void removeItemFromEnvironment(String environmentName, String path) {

    }

    public void renameEnvironment(String oldName, String newName) {

    }

    @Override
    public void treeNodesChanged(TreeModelEvent e) {
        System.out.println("treeNodesChanged");
        updateModel();
    }

    @Override
    public void treeNodesInserted(TreeModelEvent e) {
        System.out.println("treeNodesInserted");
        updateModel();
    }

    @Override
    public void treeNodesRemoved(TreeModelEvent e) {
        System.out.println("treeNodesRemoved");
        updateModel();
    }

    @Override
    public void treeStructureChanged(TreeModelEvent e) {
        System.out.println("treeStructureChanged");
        updateModel();
    }

    private void updateModel() {
        NodeTreeNode root = (NodeTreeNode) getTreeModel().getRoot();
        ConfigNode configNode = root.node();
        readState(configNode);
    }

    private void readState(ConfigNode configNode) {
        EnvironmentsConfig environmentConfig = new EnvironmentsConfig();
        List<Environment> environments = new ArrayList<>();
        for (EnvironmentNode childrenNode : configNode.childrenNodes) {
            Environment environment = new Environment(childrenNode.getName());
            for (PathNode pathNode : childrenNode.childrenNodes) {
                environment.getPaths().add(pathNode.getPath());
            }
            environments.add(environment);
            System.out.println("Adding environment: " + environment.getName());
        }
        environmentConfig.setEnvironments(environments);
        persistentState.environmentConfig = environmentConfig;
    }
}
