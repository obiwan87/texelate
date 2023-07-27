package com.lasagnerd.whack.diff;

import com.intellij.diff.DiffContext;
import com.intellij.diff.requests.DiffRequest;
import com.intellij.diff.tools.simple.SimpleDiffViewer;
import com.intellij.diff.util.Side;
import com.intellij.lang.Language;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.command.UndoConfirmationPolicy;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.ex.DocumentEx;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.lasagnerd.whack.environments.model.Environment;
import com.lasagnerd.whack.environments.model.EnvironmentsModelService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.yaml.YAMLLanguage;

import java.util.List;

public class WhackDiffViewer extends SimpleDiffViewer {
    public WhackDiffViewer(@NotNull DiffContext context, @NotNull DiffRequest request) {
        super(context, request);
    }

    public static void runDiff(Project eventProject,
                               FileType fileType,
                               DocumentEx originalDocument,
                               DocumentEx preprocessedDocument,
                               String env) {
        Language language = switch (fileType.getName().toUpperCase()) {
            case "XML" -> XMLLanguage.INSTANCE;
            case "YAML", "YML" -> YAMLLanguage.INSTANCE;
            default -> throw new RuntimeException("Unsupported file type: " + fileType.getName());
        };

        PsiFile psiFile = PsiFileFactory.getInstance(eventProject)
                .createFileFromText(language, originalDocument.getText());

        CharSequence preprocessedText = WhackPreprocessor
                .preprocess(env, originalDocument.getText(), psiFile);

        WriteCommandAction.writeCommandAction(eventProject)
                .withUndoConfirmationPolicy(UndoConfirmationPolicy.DO_NOT_REQUEST_CONFIRMATION)
                .shouldRecordActionForActiveDocument(false)
                .run(() -> preprocessedDocument.setText(preprocessedText));
    }

    @Override
    protected @NotNull List<AnAction> createToolbarActions() {
        if (getRequest() instanceof WhackDiffRequest whackDiffRequest) {
            FileType fileType = whackDiffRequest.getFileType();
            List<AnAction> toolbarActions = super.createToolbarActions();

            @NotNull DocumentEx originalDocument = getEditor(Side.LEFT).getDocument();
            @NotNull DocumentEx preprocessedDocument = getEditor(Side.RIGHT).getDocument();

            if (getProject() != null) {
                toolbarActions.add(new WhackProfileSelectionAction(fileType, getProject(), originalDocument, preprocessedDocument));
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
            WhackDiffRequest req = (WhackDiffRequest) getRequest();
            runDiff(project,
                    req.getFileType(),
                    getEditor(Side.LEFT).getDocument(),
                    getEditor(Side.RIGHT).getDocument(),
                    environment);

        }
    }
}
