package com.lasagnerd.whack.environments.model;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "com.lasagnerd.whack.environments.model.PropertiesProfilesState",
        storages = @Storage("WhackPropertiesProfiles.xml")
)
public class EnvironmentsConfigState implements PersistentStateComponent<EnvironmentsConfigState> {
    public String userId = "John Q. Public";
    public boolean ideaStatus = false;

    public static EnvironmentsConfigState getInstance(Project project) {
        return project.getService(EnvironmentsConfigState.class);
    }

    @Nullable
    @Override
    public EnvironmentsConfigState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull EnvironmentsConfigState state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}
