package com.lasagnerd.texelate.diff;

import com.intellij.diff.DiffContext;
import com.intellij.diff.FrameDiffTool;
import com.intellij.diff.requests.DiffRequest;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class TexelateDiffTool implements FrameDiffTool {
    @Override
    public @NotNull
    @Nls(capitalization = Nls.Capitalization.Sentence) String getName() {
        return "WhackDiffTool";
    }

    @Override
    public boolean canShow(@NotNull DiffContext context, @NotNull DiffRequest request) {
        return request instanceof TexelateDiffRequest;
    }

    @Override
    public @NotNull DiffViewer createComponent(@NotNull DiffContext context, @NotNull DiffRequest request) {
        return new TexelateDiffViewer(context, request);
    }
}
