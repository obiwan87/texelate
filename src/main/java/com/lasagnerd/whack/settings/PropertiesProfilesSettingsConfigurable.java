package com.lasagnerd.whack.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import com.lasagnerd.whack.environments.model.EnvironmentsConfigState;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PropertiesProfilesSettingsConfigurable implements Configurable {

    private final Project project;
    private PropertiesProfilesComponent mySettingsComponent;

    public PropertiesProfilesSettingsConfigurable(Project project) {
        this.project = project;
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "Whack Properties Profiles";
    }

    @Override
    public @Nullable JComponent createComponent() {
        mySettingsComponent = new PropertiesProfilesComponent();
        return mySettingsComponent.getPanel();
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return mySettingsComponent.getPreferredFocusedComponent();
    }

    @Override
    public boolean isModified() {
        EnvironmentsConfigState settings = EnvironmentsConfigState.getInstance(project);
        boolean modified = !mySettingsComponent.getUserNameText().equals(settings.userId);
        modified |= mySettingsComponent.getIdeaUserStatus() != settings.ideaStatus;
        return modified;
    }

    @Override
    public void apply() {
        EnvironmentsConfigState settings = EnvironmentsConfigState.getInstance(project);
        settings.userId = mySettingsComponent.getUserNameText();
        settings.ideaStatus = mySettingsComponent.getIdeaUserStatus();
    }

    @Override
    public void reset() {
        EnvironmentsConfigState settings = EnvironmentsConfigState.getInstance(project);
        mySettingsComponent.setUserNameText(settings.userId);
        mySettingsComponent.setIdeaUserStatus(settings.ideaStatus);
    }

    @Override
    public void disposeUIResources() {
        mySettingsComponent = null;
    }
}
