package io.github.landarskiy.sample;

import com.android.tools.idea.gradle.AndroidGradleModel;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

/**
 * Created by landarskiyauhen on 12.06.17.
 * SampleAction
 */
public class SampleAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent event) {
        final Project project = event.getData(PlatformDataKeys.PROJECT);
        final VirtualFile file = CommonDataKeys.VIRTUAL_FILE.getData(event.getDataContext());
        if (project == null || file == null) {
            showWarningMessage("Project or file not found");
            return;
        }
        Module module = ModuleUtil.findModuleForFile(file, project);
        if (module == null) {
            showWarningMessage("Module not found");
            return;
        }
        AndroidGradleModel gradleModel = AndroidGradleModel.get(module);
        if (gradleModel == null) {
            showWarningMessage("Android gradle model not found");
            return;
        }
        showInfoMessage(gradleModel.getModuleName());
    }

    private void showWarningMessage(@NotNull String message) {
        Messages.showMessageDialog(message, "Warning", Messages.getErrorIcon());
    }

    private void showInfoMessage(@NotNull String message) {
        Messages.showMessageDialog(message, "Info", Messages.getInformationIcon());
    }
}
