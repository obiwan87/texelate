package com.lasagnerd.texelate.ifblocks;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class PreprocessorFile extends PsiFileBase {
    public static final String PREPROCESSOR_FILE = "Preprocessor File";

    public PreprocessorFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, PreprocessorLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return PreprocessorFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return PREPROCESSOR_FILE;
    }

    @Override
    public void accept(@NotNull PsiElementVisitor visitor) {
        super.accept(visitor);
    }
}
