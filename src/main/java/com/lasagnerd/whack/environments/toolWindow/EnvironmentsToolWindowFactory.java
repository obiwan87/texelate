package com.lasagnerd.whack.environments.toolWindow;

import com.intellij.icons.AllIcons;
import com.intellij.ide.util.treeView.NodeRenderer;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.util.NlsActions;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.treeStructure.Tree;
import com.lasagnerd.whack.environments.model.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            tree.getEmptyText().appendSecondaryText("Add environment", SimpleTextAttributes.LINK_PLAIN_ATTRIBUTES, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Clicked");
                }
            });

            RemoveNodeAction removeNodeAction = new RemoveNodeAction("Remove", "Remove selected node", AllIcons.General.Remove);
            removeNodeAction.tree = tree;

            AddEnvironmentAction addEnvironmentAction = new AddEnvironmentAction("Add",
                    "Add new environment",
                    AllIcons.General.Add,
                    tree);

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

        public static class RemoveNodeAction extends AnAction {
            Tree tree;

            public RemoveNodeAction(@Nullable @NlsActions.ActionText String text, @Nullable @NlsActions.ActionDescription String description, @Nullable Icon icon) {
                super(text, description, icon);
            }

            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {
                Tree tree = this.tree;
                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node != null) {
                    int nodeIndex = node.getParent().getIndex(node);
                    NodeTreeNode parentNode = (NodeTreeNode) node.getParent();

                    if(parentNode.node() instanceof ConfigNode configNode) {
                        configNode.childrenNodes.remove(nodeIndex);
                    } else if(parentNode.node() instanceof EnvironmentNode environmentNode) {
                        environmentNode.childrenNodes.remove(nodeIndex);
                    }

                    model.removeNodeFromParent(node);
                }
            }
        }

        public static class AddEnvironmentAction extends AnAction {
            Tree tree;

            public AddEnvironmentAction(@Nullable @NlsActions.ActionText String text,
                                        @Nullable @NlsActions.ActionDescription String description,
                                        @Nullable Icon icon,
                                        Tree tree) {
                super(text, description, icon);
                this.tree = tree;
            }


            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {
                Tree tree = this.tree;
                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                NodeTreeNode root = (NodeTreeNode) model.getRoot();
                ConfigNode configNode = root.node();

                Environment environment = new Environment("new");
                EnvironmentNode environmentNode = new EnvironmentNode(environment);
                int index = configNode.getChildCount();

                configNode.childrenNodes.add(environmentNode);

                model.insertNodeInto(new NodeTreeNode(environmentNode), root, index);

            }
        }

        @NotNull
        private static EnvironmentsConfig loadConfig() {
            Environment devEnv = new Environment("dev");
            devEnv.getPaths().add("features/features_dev.properties");
            devEnv.getPaths().add("environments/dev/");

            Environment stageEnv = new Environment("stage");
            stageEnv.getPaths().add("features/features_stage.properties");
            stageEnv.getPaths().add("environments/stage/");

            Environment prodEnv = new Environment("prod");
            prodEnv.getPaths().add("features/features_prod.properties");
            prodEnv.getPaths().add("environments/prod/");

            EnvironmentsConfig environmentConfig = new EnvironmentsConfig();
            environmentConfig.getEnvironments().add(devEnv);
            environmentConfig.getEnvironments().add(stageEnv);
            environmentConfig.getEnvironments().add(prodEnv);
            return environmentConfig;
        }

        public JPanel getContentPanel() {
            return contentPanel;
        }

    }

}
