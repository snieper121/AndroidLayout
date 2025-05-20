package jkas.androidpe.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import com.google.android.material.elevation.SurfaceColors;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.IntDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.splashscreen.SplashScreen;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jkas.androidpe.logger.Logger;
import jkas.androidpe.databinding.ActivityMainBinding;
import jkas.androidpe.projectAnalyzer.NewProject;
import jkas.androidpe.projectAnalyzer.ProjectView;
import jkas.androidpe.resources.R;
import jkas.androidpe.explorer.SelectFF;
import jkas.androidpe.project.Project;
import jkas.androidpe.projectAnalyzer.ProjectLoader;
import jkas.androidpe.resourcesUtils.dataInitializer.DataRefManager;
import jkas.androidpe.resourcesUtils.utils.ResourcesValuesFixer;
import jkas.codeUtil.CodeUtil;
import jkas.codeUtil.Files;

public class MainActivity extends AppCompatActivity {
    public static final int GRANTED = 0;
    public static final int DENIED = 1;
    public static final int BLOCKED_OR_NEVER_ASKED = 2;

    private AppCompatActivity C = this;
    private ActivityMainBinding binding;
    private ProjectLoader projectLoader;
    private NewProject newProject;

    private ActivityResultLauncher<String[]> requestPermissionsLauncher;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({GRANTED, DENIED, BLOCKED_OR_NEVER_ASKED})
    public @interface PermissionStatus {}

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface attr {
        String name();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initInstances();
        initTheme();
        setupPermissionLauncher();
        loadListeners();
        allOnClick();
        checkAndRequestPermissions();
    }

    private void setupPermissionLauncher() {
        requestPermissionsLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                result -> {
                    boolean allGranted = result.values().stream().allMatch(granted -> granted);
                    if (allGranted) {
                        loadProjectView();
                    } else {
                        showPermissionError();
                    }
                });
    }

    private void checkAndRequestPermissions() {
        if (!checkIfPermissionGranted()) {
            requestPermissionsLauncher.launch(getRequiredPermissions());
        } else {
            loadProjectView();
        }
    }

    private String[] getRequiredPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE};
        } else {
            return new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }
    }

    private boolean checkIfPermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return CodeUtil.checkIfPermissionAccessStorageManagerGranted();
        } else {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void showPermissionError() {
        new MaterialAlertDialogBuilder(C)
                .setTitle(R.string.warning)
                .setMessage(R.string.permission_denied_blocked)
                .setPositiveButton(R.string.yes, (dialog, which) -> CodeUtil.showApplicationSettingsDetails(C))
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadProjectView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.initFromZero();
    }

    private void loadProjectView() {
        if (!checkIfPermissionGranted()) {
            binding.tvStatusPermission.setText("DENIED");
            showPermissionError();
            binding.viewSwitcher.setDisplayedChild(0);
            return;
        }

        binding.tvStatusPermission.setText("GRANTED");
        binding.gridLayoutListProjects.removeAllViews();
        projectLoader.load();
    }

    private void allOnClick() {
        binding.icSettings.setOnClickListener(v -> CodeUtil.startActivity(C, PreferencesActivity.class));

        binding.icOpenProject.setOnClickListener(v -> {
            new MaterialAlertDialogBuilder(C)
                    .setTitle(R.string.warning)
                    .setMessage(R.string.opening_project_description_1)
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton(R.string.open, (v1, v2) -> openProjectSelector())
                    .show();
        });

        binding.fab.setOnClickListener(v -> {
            if (checkIfPermissionGranted()) newProject.show();
        });
    }

    private void openProjectSelector() {
        SelectFF selector = new SelectFF(C);
        selector.setSelectorType(SelectFF.FOLDER_SELECTOR);
        selector.setSelectMode(SelectFF.SELECT_SINGLE);
        selector.lookRoot(Files.getExternalStorageDir());
        selector.setOnSaveListener(list -> openProject(list.get(0)));
        selector.showView();
        selector.loadData();
    }

    private void openProject(String data) {
        String folderName = new File(data).getName();
        Project project = new Project(data, folderName);
        project.setPackageName(ProjectLoader.tryFindPkg(project));
        new ProjectView(C, project);
        DataRefManager.getInstance().P = project;
        CodeUtil.startActivity(C, ProjectEditorActivity.class);
    }

    private void loadListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener(() -> loadProjectView());

        newProject.setOnCreateProjectListener(path -> {
            newProject.dismiss();
            openProject(path);
            loadProjectView();
        });

        projectLoader.setOnProjectFound(new ProjectLoader.OnProjectFoundListener() {
            @Override
            public void onFound(View view) {
                binding.gridLayoutListProjects.addView(view);
                binding.viewSwitcher.setDisplayedChild(1);
            }

            @Override
            public void onFinish() {
                binding.viewSwitcher.setDisplayedChild(binding.gridLayoutListProjects.getChildCount() > 0 ? 1 : 0);
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initTheme() {
        int color = CodeUtil.isTheSystemThemeDark(C) ? getColor(R.color.roseDark) : getColor(R.color.roseLight);
        color = MaterialColors.harmonizeWithPrimary(C, color);
        binding.fab.setBackgroundTintList(ColorStateList.valueOf(color));

        viewGradientTop();
        viewGradientBottom();
    }

    private void viewGradientTop() {
        GradientDrawable gradient = new GradientDrawable();
        gradient.setColors(new int[]{ResourcesValuesFixer.getColor(C, "?colorSurface"), Color.TRANSPARENT});
        gradient.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
        binding.viewGradientTop.setBackground(gradient);
    }

    private void viewGradientBottom() {
        GradientDrawable gradient = new GradientDrawable();
        gradient.setColors(new int[]{Color.TRANSPARENT, ResourcesValuesFixer.getColor(C, "?colorSurface")});
        gradient.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
        binding.viewGradientBottom.setBackground(gradient);
    }

    private void initInstances() {
        projectLoader = new ProjectLoader(C);
        newProject = new NewProject(C);
    }
}