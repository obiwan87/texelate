package com.lasagnerd.whack.environments.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.lasagnerd.whack.environments.model.EnvironmentsModelService;
import org.jetbrains.annotations.NotNull;

public class AddToExistingEnvironmentAction extends AnAction {
    private final String environmentName;

    public AddToExistingEnvironmentAction(String environmentName) {
        super(environmentName);
        this.environmentName = environmentName;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
                DataContext dataContext = event.getDataContext();
        final Project project = CommonDataKeys.PROJECT.getData(dataContext);
        if (project == null) {
            return;
        }
        final Editor editor = CommonDataKeys.EDITOR.getData(dataContext);
        final VirtualFile[] files = CommonDataKeys.VIRTUAL_FILE_ARRAY.getData(dataContext);

        if (files == null)
            return;

        for (VirtualFile file : files) {
            project.getService(EnvironmentsModelService.class)
                    .addItemToEnvironment(environmentName, file.getPath());

        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        super.update(e);

    }
}
