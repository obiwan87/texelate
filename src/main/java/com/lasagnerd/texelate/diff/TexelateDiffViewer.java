package com.lasagnerd.texelate.diff;

import com.intellij.codeInsight.actions.ReformatCodeProcessor;
import com.intellij.diff.DiffContext;
import com.intellij.diff.requests.DiffRequest;
import com.intellij.diff.tools.simple.SimpleDiffViewer;
import com.intellij.diff.tools.util.StatusPanel;
import com.intellij.diff.util.Side;
import com.intellij.lang.Language;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.command.UndoConfirmationPolicy;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.ex.DocumentEx;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.lasagnerd.texelate.environments.model.Environment;
import com.lasagnerd.texelate.environments.model.EnvironmentsModelService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TexelateDiffViewer extends SimpleDiffViewer {

    static class MyStatusPanel extends StatusPanel {
        public MyStatusPanel(@NotNull DiffContext context) {
            super();
        }

        @Override
        protected @NlsContexts.Label @Nullable String getMessage() {
            return "Hallo from TexelateDiffViewer/StatusPanel";
        }
    }

    public TexelateDiffViewer(@NotNull DiffContext context, @NotNull DiffRequest request) {
        super(context, request);

    }

    public static void runDiff(Project eventProject,
                               FileType fileType,
                               String originalFilePath,
                               DocumentEx originalDocument,
                               DocumentEx preprocessedDocument,
                               String env) {

        Language language = null;
        String defaultExtension = fileType.getDefaultExtension();
        for (Language l : Language.getRegisteredLanguages().stream().toList()) {
            LanguageFileType associatedFileType = l.getAssociatedFileType();
            if (associatedFileType == null) {
                continue;
            }
            String languageExtension = associatedFileType.getDefaultExtension();
            if (languageExtension.equals(defaultExtension)) {
                language = l;
                break;
            }
        }

        if (language == null) {
            language = Language.ANY;
        }

        Language docLanguage = language;

        PsiFile psiFile = PsiFileFactory.getInstance(eventProject)
                .createFileFromText(language, originalDocument.getText());

        CharSequence preprocessedText = TexelatePreprocessor
                .preprocess(originalFilePath, env, originalDocument.getText(), psiFile);

        WriteCommandAction.writeCommandAction(eventProject)
                .withUndoConfirmationPolicy(UndoConfirmationPolicy.DO_NOT_REQUEST_CONFIRMATION)
                .shouldRecordActionForActiveDocument(false)
                .run(() -> {
                    // format preprocessed document
                    if (docLanguage.equals(XMLLanguage.INSTANCE)) {
                        PsiFile fileFromText = PsiFileFactory.getInstance(eventProject)
                                .createFileFromText(docLanguage, preprocessedText);
                        ReformatCodeProcessor reformatCodeProcessor = new ReformatCodeProcessor(fileFromText, false);
                        reformatCodeProcessor.runWithoutProgress();
                        preprocessedDocument.setText(fileFromText.getText());
                    } else {
                        preprocessedDocument.setText(preprocessedText);
                    }
                });


    }

    @Override
    protected @NotNull List<AnAction> createToolbarActions() {
        if (getRequest() instanceof TexelateDiffRequest texelateDiffRequest) {
            FileType fileType = texelateDiffRequest.getFileType();
            List<AnAction> toolbarActions = super.createToolbarActions();

            @NotNull DocumentEx originalDocument = getEditor(Side.LEFT).getDocument();
            @NotNull String originalFilePath = getEditor(Side.LEFT).getVirtualFile().getPath();
            @NotNull DocumentEx preprocessedDocument = getEditor(Side.RIGHT).getDocument();

            if (getProject() != null) {
                toolbarActions.add(new TexelateProfileSelectionAction(fileType, getProject(), originalFilePath, originalDocument, preprocessedDocument));
            }

            return toolbarActions;
        }

        throw new RuntimeException("MicroboolDiffViewer can only be used with MicroboolDiffRequest");
    }

    @Override
    protected void onInit() {
        super.onInit();
        Project project = getProject();
        if (project == null) {
            return;
        }

        String environment = project.getService(EnvironmentsModelService.class).getEnvironmentsConfig()
                .getEnvironments().stream()
                .map(Environment::getName)
                .findFirst()
                .orElse(null);

        if (environment != null) {
            TexelateDiffRequest req = (TexelateDiffRequest) getRequest();

            EditorEx editor = getEditor(Side.LEFT);
            runDiff(
                    project,
                    req.getFileType(),
                    editor.getVirtualFile().getPath(),
                    editor.getDocument(),
                    getEditor(Side.RIGHT).getDocument(),
                    environment);

        }
    }
}
