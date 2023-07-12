package com.lasagnerd.whack.completion;

import com.intellij.lang.properties.PropertiesImplUtil;
import com.intellij.lang.properties.psi.PropertiesFile;
import com.intellij.lang.properties.psi.Property;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.FilenameIndex;
import com.lasagnerd.whack.environments.model.Environment;
import com.lasagnerd.whack.environments.model.EnvironmentsModelService;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PropertiesUtils {
    @NotNull
    static Properties loadProjectProperties(@NotNull PsiElement context) {
        Properties allProperties = new Properties();
        Collection<VirtualFile> propertiesFiles = getPropertiesVirtualFiles(context.getProject());
        for (var propertyFile : propertiesFiles) {
            try {
                Properties properties = new Properties();
                properties.load(propertyFile.getInputStream());

                allProperties.putAll(properties);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return allProperties;
    }

    @NotNull
    public static Collection<VirtualFile> getPropertiesVirtualFiles(@NotNull Project project) {
        return FilenameIndex.getAllFilesByExt(project, "properties");
    }

    public static List<PropertiesFile> getPropertiesFiles(@NotNull Project project) {
        return getPropertiesVirtualFiles(project)
                .stream()
                .map(x -> PropertiesImplUtil.getPropertiesFile(x, project))
                .collect(Collectors.toList());
    }

    public static List<Property> getProjectProperties(Project project) {
        List<Property> properties = new ArrayList<>();
        Collection<VirtualFile> propertiesFiles = getPropertiesVirtualFiles(project);
        for (VirtualFile propertiesFile : propertiesFiles) {
            PropertiesFile propertiesFilePsi = PropertiesImplUtil.getPropertiesFile(propertiesFile, project);

            if (propertiesFilePsi != null) {
                propertiesFilePsi.getProperties().stream()
                        .filter(x -> x instanceof Property).map(x -> (Property) x)
                        .forEach(properties::add);
            }
        }
        return properties;
    }

    public static List<Property> getProjectPropertiesForEnvironment(String environment, Project project) {
        List<Property> projectProperties = getProjectProperties(project);
        List<String> allowedFiles = project.getService(EnvironmentsModelService.class)
                .getEnvironmentsConfig()
                .getEnvironments()
                .stream()
                .filter(x -> x.getName().equals(environment))
                .findFirst()
                .map(Environment::getPaths)
                .orElse(Collections.emptyList());

        return projectProperties.stream()
                .filter(propertyFile -> allowedFiles.stream()
                        .anyMatch(path -> propertyFile.getContainingFile().getVirtualFile().getPath().contains(path)))
                .collect(Collectors.toList());
    }
}
