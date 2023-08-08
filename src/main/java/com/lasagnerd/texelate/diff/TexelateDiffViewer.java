package com.lasagnerd.texelate.diff;

import com.intellij.diff.DiffContext;
import com.intellij.diff.requests.DiffRequest;
import com.intellij.diff.tools.simple.SimpleDiffViewer;
import com.intellij.diff.util.Side;
import com.intellij.lang.Language;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.command.UndoConfirmationPolicy;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.ex.DocumentEx;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.lasagnerd.texelate.environments.model.Environment;
import com.lasagnerd.texelate.environments.model.EnvironmentsModelService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TexelateDiffViewer extends SimpleDiffViewer {
    public TexelateDiffViewer(@NotNull DiffContext context, @NotNull DiffRequest request) {
        super(context, request);
    }

    public static void runDiff(Project eventProject,
                               FileType fileType,
                               DocumentEx originalDocument,
                               DocumentEx preprocessedDocument,
                               String env) {

        Language language = null;
        String defaultExtension = fileType.getDefaultExtension();
        for (Language l : Language.getRegisteredLanguages().stream().toList()) {
            LanguageFileType associatedFileType = l.getAssociatedFileType();
            if(associatedFileType == null) {
                continue;
            }
            String languageExtension = associatedFileType.getDefaultExtension();
            if (languageExtension.equals(defaultExtension)) {
                language = l;
                break;
            }
        }

        if(language == null) {
            language = Language.ANY;
        }

        PsiFile psiFile = PsiFileFactory.getInstance(eventProject)
                .createFileFromText(language, originalDocument.getText());

        CharSequence preprocessedText = TexelatePreprocessor
                .preprocess(env, originalDocument.getText(), psiFile);

        WriteCommandAction.writeCommandAction(eventProject)
                .withUndoConfirmationPolicy(UndoConfirmationPolicy.DO_NOT_REQUEST_CONFIRMATION)
                .shouldRecordActionForActiveDocument(false)
                .run(() -> preprocessedDocument.setText(preprocessedText));
    }

    @Override
    protected @NotNull List<AnAction> createToolbarActions() {
        if (getRequest() instanceof TexelateDiffRequest texelateDiffRequest) {
            FileType fileType = texelateDiffRequest.getFileType();
            List<AnAction> toolbarActions = super.createToolbarActions();

            @NotNull DocumentEx originalDocument = getEditor(Side.LEFT).getDocument();
            @NotNull DocumentEx preprocessedDocument = getEditor(Side.RIGHT).getDocument();

            if (getProject() != null) {
                toolbarActions.add(new TexelateProfileSelectionAction(fileType, getProject(), originalDocument, preprocessedDocument));
            }

            return toolbarActions;
        }

        throw new RuntimeException("WhackDiffViewer can only be used with WhackDiffRequest");
    }

    @Override
    protected void onInit() {
        super.onInit();
        Project project = getProject();
        if(project == null) {
            return;
        }

        String environment = project.getService(EnvironmentsModelService.class).getEnvironmentsConfig()
                .getEnvironments().stream()
                .map(Environment::getName)
                .findFirst()
                .orElse(null);

        if (environment != null) {
            TexelateDiffRequest req = (TexelateDiffRequest) getRequest();
            runDiff(project,
                    req.getFileType(),
                    getEditor(Side.LEFT).getDocument(),
                    getEditor(Side.RIGHT).getDocument(),
                    environment);

        }
    }
}
