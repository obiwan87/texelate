package com.lasagnerd.whack.lang;

import com.intellij.icons.AllIcons;
import com.intellij.lang.Language;
import com.intellij.lang.properties.PropertiesIcons;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.ui.CoreIconManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

@SuppressWarnings("UnstableApiUsage")
public class WhackFileType extends LanguageFileType {

    public static final WhackFileType INSTANCE = new WhackFileType();

    protected WhackFileType() {
        super(WhackLanguage.INSTANCE);
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
