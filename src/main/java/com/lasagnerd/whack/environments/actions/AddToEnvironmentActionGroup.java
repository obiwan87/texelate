package com.lasagnerd.whack.environments.actions;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.lasagnerd.whack.environments.model.EnvironmentsConfig;
import com.lasagnerd.whack.environments.model.EnvironmentsModelService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AddToEnvironmentActionGroup extends DefaultActionGroup {
    @Override
    public AnAction @NotNull [] getChildren(@Nullable AnActionEvent e) {
        List<AnAction> actions = new ArrayList<>();
        if (e != null) {
            Project project = e.getProject();
            if (project != null) {
                EnvironmentsConfig environmentsConfig = project.getService(EnvironmentsModelService.class).getEnvironmentsConfig();
                for (var environment : environmentsConfig.getEnvironments()) {
                    actions.add(new AddToExistingEnvironmentAction(environment.getName()));
                }
                actions.add(new Separator());
            }
        }
        actions.add(new AddToNewEnvironmentAction("Add To New Environment",
                "Add to new Environment",
                AllIcons.General.Add));


        return actions.toArray(new AnAction[0]);
    }
}
