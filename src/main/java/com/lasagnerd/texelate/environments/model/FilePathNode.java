package com.lasagnerd.texelate.environments.model;

import com.intellij.openapi.fileTypes.FileTypeRegistry;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.treeStructure.SimpleNode;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class FilePathNode extends SimpleNodeWrapper<SimpleNode> {

    private final String filePath;

    public FilePathNode(String filePath) {
        super();
        this.filePath = filePath;

    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public SimpleNode createSimpleNode() {
        SimpleNode simpleNode = new SimpleNode() {
            @Override
            public SimpleNode @NotNull [] getChildren() {
                return new SimpleNode[0];
            }
        };
        simpleNode.getPresentation().addText(filePath, SimpleTextAttributes.REGULAR_ATTRIBUTES);
        Icon fileTypeIcon = FileTypeRegistry.getInstance().getFileTypeByFileName(filePath).getIcon();
        simpleNode.getPresentation().setIcon(fileTypeIcon);
        return simpleNode;
    }


}
