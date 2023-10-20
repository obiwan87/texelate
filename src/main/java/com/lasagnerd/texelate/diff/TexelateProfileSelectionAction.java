package com.lasagnerd.texelate.diff;

import com.intellij.diff.tools.util.base.TextDiffViewerUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.editor.ex.DocumentEx;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.lasagnerd.texelate.environments.model.Environment;
import com.lasagnerd.texelate.environments.model.EnvironmentsModelService;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TexelateProfileSelectionAction extends TextDiffViewerUtil.ComboBoxSettingAction<String> {
    private String value = "dev";

    private final FileType fileType;
    private final Project project;
    private final String originalFilePath;
    private final DocumentEx originalDocument;
    private final DocumentEx preprocessedDocument;

    public TexelateProfileSelectionAction(FileType fileType,
                                          @NotNull Project project,
                                          String originalFilePath,
                                          DocumentEx originalDocument,
                                          DocumentEx preprocessedDocument) {
        this.fileType = fileType;

        this.project = project;
        this.originalFilePath = originalFilePath;
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

            TexelateDiffViewer.runDiff(
                    eventProject,
                    fileType,
                    originalFilePath,
                    originalDocument,
                    preprocessedDocument,
                    option);
        }

    }
}
