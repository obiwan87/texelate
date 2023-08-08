package com.lasagnerd.texelate.ifblocks;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PreprocessorFileType extends LanguageFileType {
    public static final FileType INSTANCE =new PreprocessorFileType();

    public PreprocessorFileType() {
        super(PreprocessorLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "preprocessor";
    }

    @Override
    public @NlsContexts.Label @NotNull String getDescription() {
        return "A template language for simple preprocessors";
    }

    @Override
    public @NlsSafe @NotNull String getDefaultExtension() {
        return "pre";
    }

    @Override
    public @Nullable Icon getIcon() {
        return AllIcons.FileTypes.Any_type;
    }
}
