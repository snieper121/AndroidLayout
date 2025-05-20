package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the {@code libs} extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final AndroidxLibraryAccessors laccForAndroidxLibraryAccessors = new AndroidxLibraryAccessors(owner);
    private final ColorLibraryAccessors laccForColorLibraryAccessors = new ColorLibraryAccessors(owner);
    private final GoogleLibraryAccessors laccForGoogleLibraryAccessors = new GoogleLibraryAccessors(owner);
    private final GroupLibraryAccessors laccForGroupLibraryAccessors = new GroupLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Dependency provider for <b>androidsvg</b> with <b>com.caverock:androidsvg-aar</b> coordinates and
     * with version <b>1.4</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getAndroidsvg() {
        return create("androidsvg");
    }

    /**
     * Dependency provider for <b>lottie</b> with <b>com.airbnb.android:lottie</b> coordinates and
     * with version <b>6.0.0</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getLottie() {
        return create("lottie");
    }

    /**
     * Dependency provider for <b>picasso</b> with <b>com.squareup.picasso:picasso</b> coordinates and
     * with version <b>2.8</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getPicasso() {
        return create("picasso");
    }

    /**
     * Dependency provider for <b>zip4j</b> with <b>net.lingala.zip4j:zip4j</b> coordinates and
     * with version <b>2.11.5</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getZip4j() {
        return create("zip4j");
    }

    /**
     * Group of libraries at <b>androidx</b>
     */
    public AndroidxLibraryAccessors getAndroidx() {
        return laccForAndroidxLibraryAccessors;
    }

    /**
     * Group of libraries at <b>color</b>
     */
    public ColorLibraryAccessors getColor() {
        return laccForColorLibraryAccessors;
    }

    /**
     * Group of libraries at <b>google</b>
     */
    public GoogleLibraryAccessors getGoogle() {
        return laccForGoogleLibraryAccessors;
    }

    /**
     * Group of libraries at <b>group</b>
     */
    public GroupLibraryAccessors getGroup() {
        return laccForGroupLibraryAccessors;
    }

    /**
     * Group of versions at <b>versions</b>
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Group of bundles at <b>bundles</b>
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Group of plugins at <b>plugins</b>
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class AndroidxLibraryAccessors extends SubDependencyFactory {
        private final AndroidxAnimatedLibraryAccessors laccForAndroidxAnimatedLibraryAccessors = new AndroidxAnimatedLibraryAccessors(owner);
        private final AndroidxNavLibraryAccessors laccForAndroidxNavLibraryAccessors = new AndroidxNavLibraryAccessors(owner);

        public AndroidxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>annotation</b> with <b>androidx.annotation:annotation</b> coordinates and
         * with version <b>1.8.1</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAnnotation() {
            return create("androidx.annotation");
        }

        /**
         * Dependency provider for <b>appcompat</b> with <b>androidx.appcompat:appcompat</b> coordinates and
         * with version <b>1.7.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAppcompat() {
            return create("androidx.appcompat");
        }

        /**
         * Dependency provider for <b>cardview</b> with <b>androidx.cardview:cardview</b> coordinates and
         * with version <b>1.0.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCardview() {
            return create("androidx.cardview");
        }

        /**
         * Dependency provider for <b>constraintlayout</b> with <b>androidx.constraintlayout:constraintlayout</b> coordinates and
         * with version <b>2.1.4</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getConstraintlayout() {
            return create("androidx.constraintlayout");
        }

        /**
         * Dependency provider for <b>coordinatorlayout</b> with <b>androidx.coordinatorlayout:coordinatorlayout</b> coordinates and
         * with version <b>1.2.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCoordinatorlayout() {
            return create("androidx.coordinatorlayout");
        }

        /**
         * Dependency provider for <b>core</b> with <b>androidx.core:core</b> coordinates and
         * with version <b>1.9.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("androidx.core");
        }

        /**
         * Dependency provider for <b>drawer</b> with <b>androidx.drawerlayout:drawerlayout</b> coordinates and
         * with version <b>1.2.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getDrawer() {
            return create("androidx.drawer");
        }

        /**
         * Dependency provider for <b>grid</b> with <b>androidx.gridlayout:gridlayout</b> coordinates and
         * with version <b>1.0.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getGrid() {
            return create("androidx.grid");
        }

        /**
         * Dependency provider for <b>preference</b> with <b>androidx.preference:preference</b> coordinates and
         * with version <b>1.2.1</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getPreference() {
            return create("androidx.preference");
        }

        /**
         * Dependency provider for <b>recyclerview</b> with <b>androidx.recyclerview:recyclerview</b> coordinates and
         * with version <b>1.3.2</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRecyclerview() {
            return create("androidx.recyclerview");
        }

        /**
         * Dependency provider for <b>splashscreen</b> with <b>androidx.core:core-splashscreen</b> coordinates and
         * with version <b>1.0.1</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSplashscreen() {
            return create("androidx.splashscreen");
        }

        /**
         * Dependency provider for <b>swiperefreshlayout</b> with <b>androidx.swiperefreshlayout:swiperefreshlayout</b> coordinates and
         * with version <b>1.1.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSwiperefreshlayout() {
            return create("androidx.swiperefreshlayout");
        }

        /**
         * Dependency provider for <b>transition</b> with <b>androidx.transition:transition-ktx</b> coordinates and
         * with version <b>1.4.1</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTransition() {
            return create("androidx.transition");
        }

        /**
         * Dependency provider for <b>vectors</b> with <b>androidx.vectordrawable:vectordrawable</b> coordinates and
         * with version reference <b>androidx.vectordrawable</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getVectors() {
            return create("androidx.vectors");
        }

        /**
         * Dependency provider for <b>viewpager2</b> with <b>androidx.viewpager2:viewpager</b> coordinates and
         * with version <b>2:1.1.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getViewpager2() {
            return create("androidx.viewpager2");
        }

        /**
         * Group of libraries at <b>androidx.animated</b>
         */
        public AndroidxAnimatedLibraryAccessors getAnimated() {
            return laccForAndroidxAnimatedLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.nav</b>
         */
        public AndroidxNavLibraryAccessors getNav() {
            return laccForAndroidxNavLibraryAccessors;
        }

    }

    public static class AndroidxAnimatedLibraryAccessors extends SubDependencyFactory {

        public AndroidxAnimatedLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>vectors</b> with <b>androidx.vectordrawable:vectordrawable-animated</b> coordinates and
         * with version reference <b>androidx.vectordrawable</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getVectors() {
            return create("androidx.animated.vectors");
        }

    }

    public static class AndroidxNavLibraryAccessors extends SubDependencyFactory {
        private final AndroidxNavDynamicLibraryAccessors laccForAndroidxNavDynamicLibraryAccessors = new AndroidxNavDynamicLibraryAccessors(owner);

        public AndroidxNavLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>fragment</b> with <b>androidx.navigation:navigation-fragment-ktx</b> coordinates and
         * with version reference <b>androidx.navigation</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getFragment() {
            return create("androidx.nav.fragment");
        }

        /**
         * Dependency provider for <b>ui</b> with <b>androidx.navigation:navigation-ui-ktx</b> coordinates and
         * with version reference <b>androidx.navigation</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getUi() {
            return create("androidx.nav.ui");
        }

        /**
         * Group of libraries at <b>androidx.nav.dynamic</b>
         */
        public AndroidxNavDynamicLibraryAccessors getDynamic() {
            return laccForAndroidxNavDynamicLibraryAccessors;
        }

    }

    public static class AndroidxNavDynamicLibraryAccessors extends SubDependencyFactory {

        public AndroidxNavDynamicLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>features</b> with <b>androidx.navigation:navigation-dynamic-features-fragment</b> coordinates and
         * with version reference <b>androidx.navigation</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getFeatures() {
            return create("androidx.nav.dynamic.features");
        }

    }

    public static class ColorLibraryAccessors extends SubDependencyFactory {

        public ColorLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>picker</b> with <b>com.github.martin-stone:hsv-alpha-color-picker-android</b> coordinates and
         * with version <b>3.1.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getPicker() {
            return create("color.picker");
        }

    }

    public static class GoogleLibraryAccessors extends SubDependencyFactory {
        private final GoogleJavaLibraryAccessors laccForGoogleJavaLibraryAccessors = new GoogleJavaLibraryAccessors(owner);

        public GoogleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>flexbox</b> with <b>com.google.android.flexbox:flexbox</b> coordinates and
         * with version <b>3.0.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getFlexbox() {
            return create("google.flexbox");
        }

        /**
         * Dependency provider for <b>material</b> with <b>com.google.android.material:material</b> coordinates and
         * with version <b>1.9.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getMaterial() {
            return create("google.material");
        }

        /**
         * Group of libraries at <b>google.java</b>
         */
        public GoogleJavaLibraryAccessors getJava() {
            return laccForGoogleJavaLibraryAccessors;
        }

    }

    public static class GoogleJavaLibraryAccessors extends SubDependencyFactory {

        public GoogleJavaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>format</b> with <b>com.google.googlejavaformat:google-java-format</b> coordinates and
         * with version <b>1.17.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getFormat() {
            return create("google.java.format");
        }

    }

    public static class GroupLibraryAccessors extends SubDependencyFactory {
        private final GroupBoxLibraryAccessors laccForGroupBoxLibraryAccessors = new GroupBoxLibraryAccessors(owner);

        public GroupLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>group.box</b>
         */
        public GroupBoxLibraryAccessors getBox() {
            return laccForGroupBoxLibraryAccessors;
        }

    }

    public static class GroupBoxLibraryAccessors extends SubDependencyFactory {

        public GroupBoxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>layout</b> with <b>com.github.homayoonahmadi:GroupBoxLayout</b> coordinates and
         * with version <b>1.2.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getLayout() {
            return create("group.box.layout");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final AndroidxVersionAccessors vaccForAndroidxVersionAccessors = new AndroidxVersionAccessors(providers, config);
        private final KotlinVersionAccessors vaccForKotlinVersionAccessors = new KotlinVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>agp</b> with value <b>8.2.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAgp() { return getVersion("agp"); }

        /**
         * Group of versions at <b>versions.androidx</b>
         */
        public AndroidxVersionAccessors getAndroidx() {
            return vaccForAndroidxVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.kotlin</b>
         */
        public KotlinVersionAccessors getKotlin() {
            return vaccForKotlinVersionAccessors;
        }

    }

    public static class AndroidxVersionAccessors extends VersionFactory  {

        public AndroidxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>androidx.navigation</b> with value <b>2.5.3</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getNavigation() { return getVersion("androidx.navigation"); }

        /**
         * Version alias <b>androidx.vectordrawable</b> with value <b>1.1.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getVectordrawable() { return getVersion("androidx.vectordrawable"); }

    }

    public static class KotlinVersionAccessors extends VersionFactory  implements VersionNotationSupplier {

        public KotlinVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>kotlin</b> with value <b>1.9.20</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> asProvider() { return getVersion("kotlin"); }

        /**
         * Version alias <b>kotlin.coroutines</b> with value <b>1.7.3</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getCoroutines() { return getVersion("kotlin.coroutines"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {
        private final AndroidPluginAccessors paccForAndroidPluginAccessors = new AndroidPluginAccessors(providers, config);

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Plugin provider for <b>kotlin</b> with plugin id <b>org.jetbrains.kotlin.android</b> and
         * with version reference <b>kotlin</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getKotlin() { return createPlugin("kotlin"); }

        /**
         * Group of plugins at <b>plugins.android</b>
         */
        public AndroidPluginAccessors getAndroid() {
            return paccForAndroidPluginAccessors;
        }

    }

    public static class AndroidPluginAccessors extends PluginFactory {

        public AndroidPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Plugin provider for <b>android.application</b> with plugin id <b>com.android.application</b> and
         * with version reference <b>agp</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getApplication() { return createPlugin("android.application"); }

        /**
         * Plugin provider for <b>android.library</b> with plugin id <b>com.android.library</b> and
         * with version reference <b>agp</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getLibrary() { return createPlugin("android.library"); }

    }

}
