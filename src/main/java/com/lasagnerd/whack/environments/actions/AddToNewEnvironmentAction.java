package com.lasagnerd.whack.environments.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.util.NlsActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class AddToNewEnvironmentAction extends AnAction {
    public AddToNewEnvironmentAction(@Nullable @NlsActions.ActionText String text,
                                     @Nullable @NlsActions.ActionDescription String description,
                                     @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        System.out.println("AddToNewEnvironmentAction.actionPerformed()");
    }
}
