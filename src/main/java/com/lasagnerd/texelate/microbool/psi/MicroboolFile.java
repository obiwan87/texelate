package com.lasagnerd.texelate.microbool.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.lasagnerd.texelate.microbool.MicroboolFileType;
import com.lasagnerd.texelate.microbool.MicroboolLanguage;
import org.jetbrains.annotations.NotNull;

public class MicroboolFile extends PsiFileBase {

    public static final String MICROBOOL_FILE = "Microbool File";

    public MicroboolFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, MicroboolLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return MicroboolFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return MICROBOOL_FILE;
    }
}
