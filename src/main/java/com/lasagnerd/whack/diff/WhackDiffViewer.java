package com.lasagnerd.whack.diff;

import com.intellij.diff.DiffContext;
import com.intellij.diff.requests.DiffRequest;
import com.intellij.diff.tools.simple.SimpleDiffViewer;
import com.intellij.diff.util.Side;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.editor.ex.DocumentEx;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WhackDiffViewer extends SimpleDiffViewer {
    public WhackDiffViewer(@NotNull DiffContext context, @NotNull DiffRequest request) {
        super(context, request);
    }

    @Override
    protected @NotNull List<AnAction> createToolbarActions() {
        List<AnAction> toolbarActions = super.createToolbarActions();
        @NotNull DocumentEx originalDocument = getEditor(Side.LEFT).getDocument();
        @NotNull DocumentEx preprocessedDocument = getEditor(Side.RIGHT).getDocument();
        toolbarActions.add(new WhackProfileSelectionAction(originalDocument, preprocessedDocument));

        return  toolbarActions;
    }


}
