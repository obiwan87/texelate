package com.lasagnerd.texelate.microbool;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

@SuppressWarnings("UnstableApiUsage")
public class MicroboolFileType extends LanguageFileType {

    public static final MicroboolFileType INSTANCE = new MicroboolFileType();

    protected MicroboolFileType() {
        super(MicroboolLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "whack";
    }

    @Override
    public @NlsContexts.Label @NotNull String getDescription() {
        return "Whack templating language";
    }

    @Override
    public @NlsSafe @NotNull String getDefaultExtension() {
        return "whack";
    }

    @Override
    public @Nullable Icon getIcon() {
        return AllIcons.Ide.ConfigFile;
    }
}
