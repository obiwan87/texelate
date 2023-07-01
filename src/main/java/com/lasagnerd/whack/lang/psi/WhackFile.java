package com.lasagnerd.whack.lang.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.lasagnerd.whack.lang.WhackFileType;
import com.lasagnerd.whack.lang.WhackLanguage;
import org.jetbrains.annotations.NotNull;

public class WhackFile extends PsiFileBase {

    public static final String WHACK_FILE = "Whack File";

    public WhackFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, WhackLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return WhackFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return WHACK_FILE;
    }
}
