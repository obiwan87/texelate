package com.lasagnerd.whack.diff;

import com.intellij.diff.DiffContentFactory;
import com.intellij.diff.DiffDialogHints;
import com.intellij.diff.DiffManager;
import com.intellij.diff.contents.DiffContent;
import com.intellij.diff.requests.SimpleDiffRequest;
import com.intellij.ide.highlighter.XmlFileType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class OpenDiffViewAction extends AnAction {

    /**
     * Clones a new caret at a higher Logical Position line number.
     *
     * @param e Event related to this action
     */
    @Override
    public void actionPerformed(@NotNull final AnActionEvent e) {
        // Editor is known to exist from update, so it's not null
        final VirtualFile virtualFile = e.getRequiredData(CommonDataKeys.VIRTUAL_FILE);
        // This is my difference

        Document document = ReadAction.compute(() -> FileDocumentManager.getInstance().getDocument(virtualFile));

        Project project = e.getProject();
        DiffContent diffContentOriginal = DiffContentFactory.getInstance().create(project, document);
        DiffContent diffContentPreprocessed = createDiffContent(project, document.getText(),
                virtualFile.getFileType());

        SimpleDiffRequest simpleDiffRequest = createSimpleRequest(diffContentOriginal, diffContentPreprocessed);
        DiffManager.getInstance().showDiff(project, simpleDiffRequest, DiffDialogHints.FRAME);
    }

    @NotNull
    private DiffContent createDiffContent(Project project, String documentContent, FileType fileType) {
        return DiffContentFactory.getInstance().create(project, EditorFactory.getInstance()
                        .createDocument(documentContent),
                fileType);
    }

    @NotNull
    private SimpleDiffRequest createSimpleRequest(DiffContent document1, DiffContent document2) {
        return new WhackDiffRequest("Window Title",
                document1,
                document2,
                "Title 1",
                "Title 2");
    }

    /**
     * Enables and sets visibility of this action menu item if:
     * <ul>
     *   <li>a project is open</li>
     *   <li>an editor is active</li>
     *   <li>at least one caret exists</li>
     * </ul>
     *
     * @param e Event related to this action
     */
    @Override
    public void update(@NotNull final AnActionEvent e) {
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        // Make sure at least one caret is available
        boolean menuAllowed = false;
        if (editor != null && project != null) {
            // Ensure the list of carets in the editor is not empty
            menuAllowed = !editor.getCaretModel().getAllCarets().isEmpty();
        }
        e.getPresentation().setEnabledAndVisible(menuAllowed);
    }

}