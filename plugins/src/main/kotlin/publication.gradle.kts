import java.util.Properties

plugins {
    `maven-publish`
    signing
}

val gradleProperties = gradleProperties()
val localProperties = gradleProperties("local.properties")

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

fun getExtraString(name: String) = localProperties[name]?.toString()

publishing {
    // Configure maven central repository
    repositories {
        maven {
            name = "sonatype"
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = getExtraString("ossrhUsername")
                password = getExtraString("ossrhPassword")
            }
        }

        maven {
            name = "Snapshot"
            setUrl { "https://oss.sonatype.org/content/repositories/snapshots/" }
            credentials {
                username = getExtraString("ossrhUsername")
                password = getExtraString("ossrhPassword")
            }
        }
    }

    // Configure all publications
    publications.withType<MavenPublication> {

        // Stub javadoc.jar artifact
        artifact(javadocJar.get())

        groupId = gradleProperties["groupId"].toString()
        version = gradleProperties["version"].toString()

        // Provide artifacts information requited by Maven Central
        pom {
            name.set("KMP Template Library")
            description.set("Sample Kotlin Multiplatform library template")
            url.set("https://github.com/santimattius/kmp-template-library")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("santimattius")
                    name.set("Santiago Mattiauda")
                    email.set("santimattius.developer@gmail.com")
                }
            }
            scm {
                url.set("https://github.com/santimattius/kmp-template-library")
            }
        }
    }
}

// Signing artifacts. Signing.* extra properties values will be used

signing {
//    useInMemoryPgpKeys(
//        localProperties["signing.privateKey"].toString(),
//        localProperties["signing.password"].toString()
//    )
    sign(publishing.publications)
}

fun gradleProperties(fileName: String = "gradle.properties"): MutableMap<String, Any> {
    val properties = mutableMapOf<String, Any>()
    val file = project.rootProject.file(fileName)
    if (file.exists()) {
        file.reader().use {
            Properties().apply {
                load(it)
            }
        }.onEach { (name, value) ->
            properties[name.toString()] = value
        }
    }
    return properties
}