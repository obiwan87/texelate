package com.lasagnerd.whack.environments.actions;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AddToEnvironmentActionGroup extends DefaultActionGroup {
    @Override
    public AnAction @NotNull [] getChildren(@Nullable AnActionEvent e) {
        return new AnAction[] {
                new AddToExistingEnvironmentAction("dev"),
                new AddToExistingEnvironmentAction("stage"),
                new AddToExistingEnvironmentAction("prod"),
                new Separator(),
                new AddToNewEnvironmentAction("Add To New Environment",
                        "Add to new Environment",
                        AllIcons.General.Add),
        };
    }
}
