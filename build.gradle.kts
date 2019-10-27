plugins {
    kotlin("jvm") version "1.3.50"
}

repositories {
    mavenCentral()
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")

    implementation("org.lwjgl.osgi", "org.lwjgl.lwjgl", "+")
    implementation("org.lwjgl.osgi", "org.lwjgl.assimp", "+")
    implementation("org.lwjgl.osgi", "org.lwjgl.glfw", "+")
    implementation("org.lwjgl.osgi", "org.lwjgl.nanovg", "+")
    implementation("org.lwjgl.osgi", "org.lwjgl.opengl", "+")
    implementation("org.lwjgl.osgi", "org.lwjgl.stb", "+")
}