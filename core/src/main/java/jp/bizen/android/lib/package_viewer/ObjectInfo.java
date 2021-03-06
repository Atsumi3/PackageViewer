package jp.bizen.android.lib.package_viewer;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Modifier;
import java.util.Locale;

final class ObjectInfo {
    @NonNull
    private final String mName;
    @NonNull
    private final String mFullPath;
    @NonNull
    private final String mPackageName;
    @NonNull
    private final Type mType;

    /**
     * type default directory
     *
     * @param packageName package name
     */
    ObjectInfo(@NonNull final String packageName) {
        mName = packageName;
        mFullPath = packageName;
        mPackageName = packageName;
        mType = Type.DIRECTORY;
    }

    /**
     * example
     *
     * @param className   View
     * @param basePackage android.view
     */
    ObjectInfo(@NonNull final String className,
               @NonNull final String basePackage) {
        this(className, basePackage, null);
    }

    /**
     * example
     *
     * @param className   View
     * @param basePackage android.view
     * @param customType  Type.OBJECT
     */
    ObjectInfo(@NonNull final String className,
               @NonNull final String basePackage,
               @Nullable final Type customType) {
        mName = className;
        if (TextUtils.isEmpty(basePackage)) {
            mFullPath = className;
        } else {
            mFullPath = basePackage + "." + className;
        }
        mPackageName = basePackage;
        if (customType == null) {
            mType = Type.getClassTypeFromName(mFullPath);
        } else {
            mType = customType;
        }
    }

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public String getFullPath() {
        return mFullPath;
    }

    @NonNull
    public String getPackageName() {
        return mPackageName;
    }

    @NonNull
    public Type getType() {
        return mType;
    }

    @Override
    public int hashCode() {
        return mName.hashCode();
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == hashCode();
    }

    public enum Type {
        INTERFACE,
        ENUM,
        ABSTRACT,
        ANNOTATION,
        DIRECTORY,
        VIEW,
        VIEW_GROUP,
        UNKNOWN,
        OBJECT;

        public static <T> Type getClassType(@NonNull Class<T> clazz) {
            if (clazz.isInterface()) {
                return INTERFACE;
            } else if (clazz.isEnum()) {
                return ENUM;
            } else if (clazz.isAnnotation()) {
                return ANNOTATION;
            } else if (Modifier.isAbstract(clazz.getModifiers())) {
                return ABSTRACT;
            }

            if (clazz == ViewGroup.class) {
                return VIEW_GROUP;
            }

            if (clazz == View.class) {
                return VIEW;
            }

            Log.d("ClassType Error", String.format(Locale.getDefault(), "[%s] %d", clazz.getSimpleName(), clazz.getModifiers()));
            return OBJECT;
        }

        public static Type getClassTypeFromName(String className) {
            try {
                return getClassType(Class.forName(className));
            } catch (Throwable e) {
                e.printStackTrace();
                return UNKNOWN;
            }
        }

        public boolean isClass() {
            switch (this) {
                case DIRECTORY:
                    return false;
                default:
                    return true;
            }
        }

        public String getName() {
            return this.name();
        }
    }
}
