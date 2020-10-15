object AndroidDependencies {
    const val APPCOMPAT =
        "androidx.appcompat:appcompat:${AndroidDependenciesVersions.APP_COMPAT_VERSION}"
    const val DESIGN =
        "com.google.android.material:material:${AndroidDependenciesVersions.MATERIAL_DESIGN_VERSION}"
    const val SUPPORT_CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${AndroidDependenciesVersions.CONSTRAINT_LAYOUT_VERSION}"
    const val COORDINATOR_LAYOUT =
        "androidx.coordinatorlayout:coordinatorlayout:${AndroidDependenciesVersions.COORDINATOR_LAYOUT}"
    const val CARDVIEW =
        "androidx.cardview:cardview:${AndroidDependenciesVersions.CARD_VIEW_VERSION}"
    const val RECYCLERVIEW =
        "androidx.recyclerview:recyclerview:${AndroidDependenciesVersions.RECYCLER_VIEW_VERSION}"
    const val MATERIAL =
        "com.google.android.material:material:${AndroidDependenciesVersions.MATERIAL}"
    const val CONSTRAINTLAYOUT =
        "androidx.constraintlayout:constraintlayout:${AndroidDependenciesVersions.CONSTRAINT_LAYOUT_VERSION}"

    const val LIFECYCLE_RUNTIME =
        "androidx.lifecycle:lifecycle-runtime:${AndroidDependenciesVersions.LIFECYCLE_VERSION}"
    const val LIFECYCLE_VIEWMODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${AndroidDependenciesVersions.LIFECYCLE_VERSION}"
    const val LIFECYCLE_LIVEDATA_KTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${AndroidDependenciesVersions.LIFECYCLE_VERSION}"
    const val LIFECYCLE_COMMON_JAVA8 =
        "androidx.lifecycle:lifecycle-common-java8:${AndroidDependenciesVersions.LIFECYCLE_VERSION}"
    const val ANDROID_CORE_CORE_KTX =
        "androidx.core:core-ktx:${AndroidDependenciesVersions.ANDROID_CORE_CORE_KTX_VERSION}"
    const val ANDROID_FRAGMENT_KTX =
        "androidx.fragment:fragment-ktx:${AndroidDependenciesVersions.ANDROID_FRAGMENT_KTX}"

}

private object AndroidDependenciesVersions {
    const val CONSTRAINT_LAYOUT_VERSION = "1.1.3"
    const val COORDINATOR_LAYOUT = "1.0.0"
    const val APP_COMPAT_VERSION = "1.1.0"
    const val MATERIAL_DESIGN_VERSION = "1.0.0"
    const val MATERIAL = "1.1.0-alpha09"
    const val CARD_VIEW_VERSION = "1.0.0"
    const val RECYCLER_VIEW_VERSION = "1.0.0"
    const val LIFECYCLE_VERSION = "2.2.0"
    const val ANDROID_CORE_CORE_KTX_VERSION = "1.3.1"
    const val ANDROID_FRAGMENT_KTX = "1.2.5"
}