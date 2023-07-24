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
            tree.registerKeyboardAction(e -> RemoveNodeAction.removeSelectedNode(tree), KeyStroke.getKeyStroke("DELETE"),
                    JComponent.WHEN_FOCUSED);
            tree.registerKeyboardAction(e -> {
                        // get selected node
                        if (tree.getLastSelectedPathComponent() instanceof EnvironmentNode environmentNode) {
                            EnvironmentDialog environmentDialog = new EnvironmentDialog("Edit environment", environmentNode.getEnvironmentName());
                            System.out.println("Edit environment");
                            if (environmentDialog.showAndGet()) {
                                String text = environmentDialog.textField.getText();
                                environmentNode.setEnvironmentName(text);
                                model.reload();
                            }
                        }
                    }, KeyStroke.getKeyStroke("shift F6"),
                    JComponent.WHEN_FOCUSED
            );
            // Get shortcut for renaming from Shortcut register

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
            EditEnvironmentAction editEnvironmentAction = new EditEnvironmentAction(tree);
            MoveNodeAction moveNodeDownAction = new MoveNodeAction(tree, 1);
            MoveNodeAction moveNodeUpAction = new MoveNodeAction(tree, -1);

            AnAction[] actions = new AnAction[]{
                    addEnvironmentAction,
                    removeNodeAction,
                    editEnvironmentAction,
                    new Separator(),
                    moveNodeDownAction,
                    moveNodeUpAction,
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

    public static class EnvironmentDialog extends DialogWrapper {

        private JBTextField textField;
        private final String initialValue;

        public EnvironmentDialog(String title, String initialValue) {
            super(true); // use current window as parent
            this.initialValue = initialValue;
            init();
            setTitle(title);
        }

        @Override
        protected @Nullable JComponent createCenterPanel() {
            // Simple text field for entering the environment name.
            textField = new JBTextField();
            textField.setText(initialValue);
            textField.setTextToTriggerEmptyTextStatus("environment");

            return textField;
        }
    }

    public static class RemoveNodeAction extends AnAction {
        private final Tree tree;

        public RemoveNodeAction(Tree tree) {
            super("Remove", "Remove selected node", AllIcons.General.Remove);
            this.tree = tree;
            this.setShortcutSet(CustomShortcutSet.fromString("DELETE"));
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {
            Tree tree = this.tree;
            removeSelectedNode(tree);
        }

        static void removeSelectedNode(Tree tree) {
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
            EnvironmentDialog dialog = new EnvironmentDialog("New Environment", "");
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

    public static class EditEnvironmentAction extends AnAction {
        Tree tree;

        public EditEnvironmentAction(Tree tree) {
            super("Edit", "Edit environment", AllIcons.Actions.Edit);
            this.tree = tree;

        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {

        }
    }

    private static class MoveNodeAction extends AnAction {
        private final Tree tree;
        private final int direction;

        public MoveNodeAction(Tree tree, int direction) {
            super(direction > 0 ? "Move Down" : "Move Up", "Move selected node", direction > 0 ? AllIcons.Actions.MoveDown : AllIcons.Actions.MoveUp);
            this.tree = tree;
            this.direction = direction;
        }
        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {
            Tree tree = this.tree;
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            if (node instanceof EnvironmentNode) {
                int index = node.getParent().getIndex(node);
                int newIndex = index + direction;
                if (newIndex >= 0 && newIndex < node.getParent().getChildCount()) {
                    model.removeNodeFromParent(node);
                    model.insertNodeInto(node, (DefaultMutableTreeNode) tree.getModel().getRoot(), newIndex);
                    model.reload();
                }
            }
        }
    }
}