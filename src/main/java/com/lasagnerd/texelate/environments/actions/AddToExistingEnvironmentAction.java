package com.lasagnerd.texelate.environments.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.lasagnerd.texelate.environments.model.EnvironmentNode;
import com.lasagnerd.texelate.environments.model.EnvironmentsModelService;
import com.lasagnerd.texelate.environments.model.FilePathNode;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.TreePath;

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
        final VirtualFile[] files = CommonDataKeys.VIRTUAL_FILE_ARRAY.getData(dataContext);

        if (files == null)
            return;


        FilePathNode filePathNode = null;
        EnvironmentsModelService service = project.getService(EnvironmentsModelService.class);
        for (VirtualFile file : files) {
            filePathNode = service
                    .addItemToEnvironment(environmentName, file.getPath());

        }
        if (filePathNode != null) {
            // Select the new node and expand its parent
            service.getTree().setSelectionPath(new TreePath(filePathNode.getPath()));
            EnvironmentNode parent = (EnvironmentNode) filePathNode.getParent();
            service.getTree().expandPath(new TreePath(parent.getPath()));

        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        super.update(e);

    }
}
