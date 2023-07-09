package com.lasagnerd.whack.environments.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class AddToExistingEnvironmentAction extends AnAction {
    private final String environmentName;

    public AddToExistingEnvironmentAction(String environmentName) {
        super(environmentName);
        this.environmentName = environmentName;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        System.out.println("AddEnvironmentAction.actionPerformed() with environmentName: " + environmentName);
    }
}
