package com.lasagnerd.texelate.diff;

import com.intellij.diff.contents.DiffContent;
import com.intellij.diff.requests.SimpleDiffRequest;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.util.NlsContexts;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class TexelateDiffRequest extends SimpleDiffRequest {
    private final FileType fileType;

    public TexelateDiffRequest(@Nullable @NlsContexts.DialogTitle String title,
                               @NotNull DiffContent content1,
                               @NotNull DiffContent content2,
                               @Nullable @NlsContexts.Label String title1,
                               @Nullable @NlsContexts.Label String title2,
                               @NotNull FileType fileType) {
        super(title, content1, content2, title1, title2);
        this.fileType = fileType;
    }

}
