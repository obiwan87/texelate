package com.lasagnerd.whack.diff;

import com.intellij.diff.tools.util.base.TextDiffViewerUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.editor.ex.DocumentEx;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.lasagnerd.whack.environments.model.Environment;
import com.lasagnerd.whack.environments.model.EnvironmentsModelService;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WhackProfileSelectionAction extends TextDiffViewerUtil.ComboBoxSettingAction<String> {
    private String value = "dev";

    private final FileType fileType;
    private final Project project;
    private final DocumentEx originalDocument;
    private final DocumentEx preprocessedDocument;

    public WhackProfileSelectionAction(FileType fileType,
                                       @NotNull Project project,
                                       DocumentEx originalDocument,
                                       DocumentEx preprocessedDocument) {
        this.fileType = fileType;

        this.project = project;
        this.originalDocument = originalDocument;
        this.preprocessedDocument = preprocessedDocument;
    }

    @Override
    protected @NotNull List<String> getAvailableOptions() {

        return project.getService(EnvironmentsModelService.class).getEnvironmentsConfig()
                .getEnvironments().stream()
                .map(Environment::getName)
                .toList();
    }

    @Override
    protected @NotNull String getValue() {
        return value;
    }

    @Override
    protected void setValue(@NotNull String option) {
        value = option;
    }

    @Override
    protected @NotNull
    @Nls
    String getText(@NotNull String option) {
        return option;
    }

    @Override
    public @NotNull DefaultActionGroup getActions() {
        DefaultActionGroup group = new DefaultActionGroup();
        getAvailableOptions().stream()
                .map(PreprocessFileWithEnvironmentAction::new)
                .forEach(group::addAction);
        return group;
    }

    @EqualsAndHashCode(callSuper = true)
    @Value
    public class PreprocessFileWithEnvironmentAction extends AnAction {
        @NotNull String option;

        PreprocessFileWithEnvironmentAction(@NotNull String option) {
            super(getText(option));
            this.option = option;
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {
            setValue(option);

            Project eventProject = e.getProject();
            if (eventProject == null)
                return;

            WhackDiffViewer.runDiff(eventProject,
                    fileType,
                    originalDocument,
                    preprocessedDocument,
                    option);
        }

    }
}
