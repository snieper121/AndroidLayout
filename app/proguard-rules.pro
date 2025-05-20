# Essential Settings (Remove warnings and prevent shrinking)
-dontwarn
-dontshrink

# Preserve WebView JavaScript Interface
-keepclassmembers class fqcn.of.javascript.interface.for.webview { public *; }

# Keep necessary R classes for Material and AppCompat
-keepclassmembers class com.google.android.material.R$* { *; }
-keepclassmembers class androidx.appcompat.R$* { *; }
-keepclassmembers class android.R$* { *; }

# Preserve UI Components and WebView (Necessary for your app's UI)
-keep class android.webkit.** { *; }
-keep class android.view.** { *; }
-keep class android.widget.** { *; }

# Restricting Keep Rules to Only Required Components
-keep public class androidx.** { public <fields>; public <methods>; }
-keep public class com.google.android.material.** { public <fields>; public <methods>; }
-keep public class jkas.androidpe.resourcesUtils.attrs.layout.** { public <fields>; public <methods>; }

# Preserve Debugging Information (Optional but useful)
-keepattributes Signature, SourceFile, LineNumberTable
-renamesourcefileattribute SourceFile

# Suppress Warnings for Specific Libraries (Only the essentials)
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-dontwarn org.conscrypt.**
-dontwarn java.beans.**
-dontwarn androidx.databinding.**
-dontwarn androidx.window.extensions.**
-dontwarn androidx.window.sidecar.**
-dontwarn java.lang.reflect.AnnotatedType
-dontwarn javax.lang.model.element.Modifier
-dontwarn com.ibm.oti.shared.**
-dontwarn java.lang.management.**
-dontwarn javax.lang.model.SourceVersion
-dontwarn org.osgi.service.event.**
-dontwarn org.eclipse.jdt.internal.compiler.util.Util