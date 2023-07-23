package com.lasagnerd.whack.environments.toolWindow;

import com.intellij.icons.AllIcons;
import com.intellij.ide.util.treeView.NodeRenderer;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.treeStructure.Tree;
import com.lasagnerd.whack.environments.model.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EnvironmentsToolWindowFactory implements ToolWindowFactory, DumbAware {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        EnvironmentsToolWindow environmentsToolWindowContent = new EnvironmentsToolWindow(project);
        Content content = ContentFactory.getInstance().createContent(environmentsToolWindowContent.getContentPanel(),
                "",
                false);
        toolWindow.getContentManager().addContent(content);
    }

    private static class EnvironmentsToolWindow {
        SimpleToolWindowPanel contentPanel = new SimpleToolWindowPanel(true, false);

        private EnvironmentsToolWindow(Project project) {
            DefaultTreeModel model = project.getService(EnvironmentsModelService.class).getTreeModel();

            Tree tree = new Tree(model);

            tree.setRootVisible(false);
            tree.setShowsRootHandles(true);
            tree.setCellRenderer(new NodeRenderer());
            tree.getEmptyText().appendText("No environments configured", SimpleTextAttributes.GRAYED_ATTRIBUTES);
            tree.getEmptyText().appendSecondaryText("Add environment", SimpleTextAttributes.LINK_PLAIN_ATTRIBUTES,
                    e -> AddEnvironmentAction.addEnvironmentWithDialog(model));

            tree.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        Tree tree = (Tree) e.getSource();
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                        if (node != null) {
                            if (node instanceof FilePathNode filePathNode) {
                                FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
                                // Get system path separator
                                VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByPath(filePathNode.getFilePath());

                                if (virtualFile != null) {
                                    fileEditorManager.openFile(virtualFile, true, true);
                                }
                            }
                        }
                    }
                }
            });

            RemoveNodeAction removeNodeAction = new RemoveNodeAction(tree);

            AddEnvironmentAction addEnvironmentAction = new AddEnvironmentAction(tree);

            AnAction[] actions = new AnAction[]{
                    addEnvironmentAction,
                    removeNodeAction,
                    new Separator()
            };
            ActionGroup actionGroup = new ActionGroup() {
                @Override
                public AnAction @NotNull [] getChildren(@Nullable AnActionEvent e) {
                    return actions;
                }
            };

            ActionToolbar environmentsToolbar = ActionManager.getInstance()
                    .createActionToolbar("Environments", actionGroup, true);
            environmentsToolbar.setTargetComponent(contentPanel);
            contentPanel.setToolbar(environmentsToolbar.getComponent());

            contentPanel.setContent(tree);
        }


        public JComponent getContentPanel() {

            return new JBScrollPane(contentPanel);
        }

    }

    public static class NewEnvironmentDialog extends DialogWrapper {

        private JBTextField textField;

        public NewEnvironmentDialog() {
            super(true); // use current window as parent
            init();
            setTitle("New Environment");
        }

        @Override
        protected @Nullable JComponent createCenterPanel() {
            // Simple text field for entering the environment name.

            textField = new JBTextField();
            textField.setTextToTriggerEmptyTextStatus("environment");

            return textField;
        }
    }

    public static class RemoveNodeAction extends AnAction {
        private final Tree tree;

        public RemoveNodeAction(Tree tree) {
            super("Remove", "Remove selected node", AllIcons.General.Remove);
            this.tree = tree;
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {
            Tree tree = this.tree;
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (node != null) {
                model.removeNodeFromParent(node);
            }
        }
    }

    public static class AddEnvironmentAction extends AnAction {
        Tree tree;

        public AddEnvironmentAction(Tree tree) {
            super("Add", "Add new environment", AllIcons.General.Add);
            this.tree = tree;
        }


        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {
            Tree tree = this.tree;
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            addEnvironmentWithDialog(model);
        }

        public static EnvironmentNode addEnvironmentWithDialog(DefaultTreeModel model) {
            NewEnvironmentDialog dialog = new NewEnvironmentDialog();
            if (dialog.showAndGet()) {
                String text = dialog.textField.getText();
                Environment environment = new Environment(text);
                EnvironmentNode environmentNode = new EnvironmentNode(environment);
                SimpleNodeWrapper<?> root = (SimpleNodeWrapper<?>) model.getRoot();
                int index = root.getChildCount();
                root.add(environmentNode);
                model.nodesWereInserted(root, new int[]{index});
                model.reload();

                return environmentNode;
            }
            return null;
        }

    }
}
