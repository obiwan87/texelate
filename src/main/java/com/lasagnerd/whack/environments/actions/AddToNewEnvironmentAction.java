package com.lasagnerd.whack.environments.actions;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.lasagnerd.whack.environments.model.EnvironmentNode;
import com.lasagnerd.whack.environments.model.EnvironmentsModelService;
import org.jetbrains.annotations.NotNull;

import static com.lasagnerd.whack.environments.toolWindow.EnvironmentsToolWindowFactory.AddEnvironmentAction;

public class AddToNewEnvironmentAction extends AnAction {
    public AddToNewEnvironmentAction() {
        super("Add To New Environment",
                "Add to new Environment",
                AllIcons.General.Add);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        
        DataContext dataContext = event.getDataContext();
        final Project project = CommonDataKeys.PROJECT.getData(dataContext);
        if (project == null) {
            return;
        }

        EnvironmentsModelService service = project.getService(EnvironmentsModelService.class);
        EnvironmentNode environmentNode = AddEnvironmentAction.addEnvironmentWithDialog(service.getTreeModel());

        final VirtualFile[] files = CommonDataKeys.VIRTUAL_FILE_ARRAY.getData(dataContext);

        if (files == null)
            return;

        if (environmentNode == null)
            return;

        String environmentName = environmentNode.getEnvironmentName();
        for (VirtualFile file : files) {
            project.getService(EnvironmentsModelService.class)
                    .addItemToEnvironment(environmentName, file.getPath());

        }
    }
}
