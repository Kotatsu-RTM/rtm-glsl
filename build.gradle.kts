import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.10"
    `maven-publish`
    signing
}

group = "dev.siro256.forgelib"
version = "0.1.0-SNAPSHOT"

description = "The library for easy use of GLSL with RealTrainMod"

repositories {
    maven { url = uri("https://repo.siro256.dev/repository/maven-public/") }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.lwjgl.lwjgl:lwjgl:2.9.3")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
}

kotlin {
    jvmToolchain(8)
}


tasks {
    processResources {
        from(project.file("LICENSE")) {
            rename { "LICENSE_${project.name}" }
        }
    }

    create("sourcesJar", Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }

    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            allWarningsAsErrors = true
        }
    }
}

publishing.publications {
    create("publication", MavenPublication::class.java) {
        groupId = rootProject.group.toString()
        artifactId = rootProject.name
        version = rootProject.version.toString()

        from(components.getByName("java"))
        artifact(tasks.getByName("sourcesJar"))

        pom {
            name.set(rootProject.name)
            description.set(rootProject.description)
            url.set("https://github.com/Kotatsu-RTM/rtm-glsl")

            licenses {
                license {
                    name.set("MIT License")
                    url.set("https://opensource.org/licenses/mit-license.php")
                }
            }

            developers {
                developer {
                    id.set("Siro256")
                    name.set("Siro_256")
                    email.set("siro@siro256.dev")
                    url.set("https://github.com/Siro256")
                }
            }

            scm {
                connection.set("scm:git:git://github.com/Kotatsu-RTM/rtm-glsl.git")
                developerConnection.set("scm:git:ssh://github.com/Kotatsu-RTM/rtm-glsl.git")
                url.set("https://github.com/Kotatsu-RTM/rtm-glsl")
            }
        }
    }

    repositories {
        maven {
            url = if (version.toString().endsWith("SNAPSHOT"))
                uri("https://repo.siro256.dev/repository/maven-snapshots/")
            else
                uri("https://repo.siro256.dev/repository/maven-public/")

            credentials {
                username = System.getenv("NexusUsername")
                password = System.getenv("NexusPassword")
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(System.getenv("SigningKey"), System.getenv("SigningKeyPassword"))
    sign(publishing.publications.getByName("publication"))
}
