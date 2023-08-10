package com.lasagnerd.texelate.environments.model;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "com.lasagnerd.microbool.environments.model.EnvironmentsConfigPersistentState",
        storages = @Storage("TexelateEnvironments.xml")
)
public class EnvironmentsConfigPersistentState implements PersistentStateComponent<EnvironmentsConfigPersistentState> {
    public EnvironmentsConfig environmentConfig = new EnvironmentsConfig();

    @Nullable
    @Override
    public EnvironmentsConfigPersistentState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull EnvironmentsConfigPersistentState state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}
