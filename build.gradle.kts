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

    implementation("org.lwjgl:lwjgl:+")
    implementation("org.lwjgl:lwjgl-glfw:+")
    implementation("org.lwjgl:lwjgl-opengl:+")
    implementation("org.lwjgl:lwjgl-assimp:+")
    implementation("org.lwjgl:lwjgl-stb:+")
    implementation("org.lwjgl:lwjgl-nanovg:+")

    runtimeOnly("org.lwjgl:lwjgl:+:natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-glfw:+:natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-opengl:+:natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-assimp:+:natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-stb:+:natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-nanovg:+:natives-windows")
}

//implementation "org.lwjgl:lwjgl-nanovg"
//runtimeOnly "org.lwjgl:lwjgl::natives-windows"
//runtimeOnly "org.lwjgl:lwjgl-nanovg::natives-windows"