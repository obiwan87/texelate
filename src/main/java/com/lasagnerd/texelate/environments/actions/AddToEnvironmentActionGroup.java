package com.lasagnerd.texelate.environments.actions;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.lasagnerd.texelate.environments.model.EnvironmentsConfig;
import com.lasagnerd.texelate.environments.model.EnvironmentsModelService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
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
        actions.add(new AddToNewEnvironmentAction(
        ));


        return actions.toArray(new AnAction[0]);
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        super.update(e);
        Project project = e.getProject();

        if (project == null) {
            e.getPresentation().setEnabled(false);
            return;
        }

        DataContext dataContext = e.getDataContext();

        final VirtualFile[] files = CommonDataKeys.VIRTUAL_FILE_ARRAY.getData(dataContext);

        if (files == null)
            return;

        boolean enabled = Arrays.stream(files).anyMatch(f -> f.isDirectory()
                || "properties".equals(f.getExtension())
                || "texelate".equals(f.getExtension()));
        e.getPresentation().setEnabled(enabled);
    }
}
