package com.lasagnerd.whack.diff;

import com.intellij.diff.tools.util.base.TextDiffViewerUtil;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.command.UndoConfirmationPolicy;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.ex.DocumentEx;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WhackProfileSelectionAction extends TextDiffViewerUtil.ComboBoxSettingAction<String> {
    private String value = "dev";

    private final DocumentEx originalDocument;
    private final DocumentEx preprocessedDocument;

    public WhackProfileSelectionAction(DocumentEx originalDocument, DocumentEx preprocessedDocument) {
        this.originalDocument = originalDocument;
        this.preprocessedDocument = preprocessedDocument;
    }

    @Override
    protected @NotNull List<String> getAvailableOptions() {
        return List.of("dev", "stage", "test", "prod");
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
                .map(MyAction::new)
                .forEach(group::addAction);
        return group;
    }

    @EqualsAndHashCode(callSuper = true)
    @Value
    private class MyAction extends AnAction {
        @NotNull String option;

        MyAction(@NotNull String option) {
            super(getText(option));
            this.option = option;
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {
            setValue(option);

            if (e.getProject() == null)
                return;

            PsiFile psiFile = PsiFileFactory.getInstance(e.getProject()).createFileFromText(XMLLanguage.INSTANCE, originalDocument.getText());
            CharSequence preprocessedText = WhackPreprocessor.preprocess(originalDocument.getText(), psiFile);
            WriteCommandAction.writeCommandAction(e.getProject())
                    .withUndoConfirmationPolicy(UndoConfirmationPolicy.DO_NOT_REQUEST_CONFIRMATION)
                    .shouldRecordActionForActiveDocument(false)
                    .run(() -> preprocessedDocument.setText(preprocessedText));
        }
    }
}
