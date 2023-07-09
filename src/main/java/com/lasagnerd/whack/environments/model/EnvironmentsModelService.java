package com.lasagnerd.whack.environments.model;

import com.intellij.psi.PsiElement;

import javax.swing.tree.TreeModel;

// Provide in memory representation of currently loaded environments
// The easiest approach is to directly provide the tree model here,
// such that the tree view is updated automatically when the model changes.
public class EnvironmentsModelService {
    public EnvironmentsModelService() {
    }

    public TreeModel getTreeModel() {
        return null;
    }

    public void addEnvironment(String name) {


    }

    public void addItemToEnvironment(String environmentName, String path) {

    }

    public void removeEnvironment(String name) {

    }

    public void removeItemFromEnvironment(String environmentName,  String path) {

    }

    public void renameEnvironment(String oldName, String newName) {

    }
}
