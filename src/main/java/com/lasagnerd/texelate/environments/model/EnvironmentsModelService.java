package com.lasagnerd.texelate.environments.model;

import com.intellij.openapi.project.Project;
import com.intellij.ui.treeStructure.Tree;
import lombok.Getter;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

// Provide in memory representation of currently loaded environments
// The easiest approach is to directly provide the tree model here,
// such that the tree view is updated automatically when the model changes.

public class EnvironmentsModelService implements TreeModelListener {
    private final EnvironmentsConfigPersistentState persistentState;
    private DefaultTreeModel defaultTreeModel;

    @Getter
    private Tree tree;


    public EnvironmentsModelService(Project project) {
        persistentState = project.getService(EnvironmentsConfigPersistentState.class);
    }

    public DefaultTreeModel getTreeModel() {
        if (defaultTreeModel == null) {
            defaultTreeModel = new DefaultTreeModel(new EnvironmentsConfigNode(persistentState.environmentConfig));
            defaultTreeModel.addTreeModelListener(this);
        }
        return defaultTreeModel;
    }

    public EnvironmentsConfig getEnvironmentsConfig() {
        return persistentState.environmentConfig;
    }

    public void addEnvironment(String name) {

    }

    public FilePathNode addItemToEnvironment(String environmentName, final String path) {
        EnvironmentsConfigNode environmentsConfigNode = (EnvironmentsConfigNode) getTreeModel().getRoot();
        Optional<EnvironmentNode> environmentNode = environmentsConfigNode.environmentNodes().stream()
                .filter(node -> node.getEnvironmentName().equals(environmentName))
                .findFirst();
        if (environmentNode.isPresent()) {
            FilePathNode filePathNode = new FilePathNode(path);
            environmentNode.get().add(filePathNode);
            getTreeModel().reload(environmentNode.get());

            return filePathNode;
        }
        return null;
    }

    public void removeItemFromEnvironment(String environmentName, String path) {

    }

    public void renameEnvironment(String oldName, String newName) {

    }

    @Override
    public void treeNodesChanged(TreeModelEvent e) {
        updateModel();
    }

    @Override
    public void treeNodesInserted(TreeModelEvent e) {
        updateModel();
    }

    @Override
    public void treeNodesRemoved(TreeModelEvent e) {
        updateModel();
    }

    @Override
    public void treeStructureChanged(TreeModelEvent e) {
        updateModel();
    }

    private void updateModel() {
        EnvironmentsConfigNode environmentsConfigNode = (EnvironmentsConfigNode) getTreeModel().getRoot();
        readState(environmentsConfigNode);
    }

    private void readState(EnvironmentsConfigNode configNode) {
        EnvironmentsConfig environmentConfig = new EnvironmentsConfig();
        List<Environment> environments = new ArrayList<>();
        configNode.children().asIterator().forEachRemaining(child -> {
            EnvironmentNode environmentNode = (EnvironmentNode) child;
            Environment environment = new Environment(environmentNode.getEnvironmentName());
            environmentNode.children().asIterator().forEachRemaining(filePathNode -> {
                environment.getPaths().add(((FilePathNode) filePathNode).getFilePath());
            });
            environments.add(environment);
        });
        environmentConfig.setEnvironments(environments);
        persistentState.environmentConfig = environmentConfig;
    }

    public Enumeration<TreePath> getExpandedDescendants(TreePath path) {
        if (tree == null)
            return null;
        return tree.getExpandedDescendants(path);
    }

    public void restoreExpandedDescendants(Enumeration<TreePath> expandedDescendants) {
        if (expandedDescendants == null)
            return;
        if (tree == null)
            return;
        while (expandedDescendants.hasMoreElements()) {
            TreePath path = expandedDescendants.nextElement();
            tree.expandPath(path);
        }
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }
}
