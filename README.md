# KMP Template Library
Cross-platform library template with base configuration to publish it in maven central.

## Registering a Sonatype account

If this is your first library, or you’ve only ever used Bintray to do this before, you will need to
first register a Sonatype account.

There are many articles describing the registration process on the
internet. [The one from GetStream](https://getstream.io/blog/publishing-libraries-to-mavencentral-2021/#overview)
is exhaustive and up to date. You can start by following the first four steps, including "Generating
a GPG key pair":

 - Register a Jira account with Sonatype (you can use my issue as an example). ✅
 - Verify your ownership of the group ID you want to use to publish your artifact by creating a GitHub repo. ✅
 - Generate a GPG key pair for signing your artifacts. ✅
 - Publish your public key. ✅
 - Export your private key. ✅

When the Maven repository and signing keys for your library are prepared, we are ready to move
forward and set up our build to upload the library artifacts to a staging repository and then
release them!

## Publishing multiplatform libraries

You can publish a multiplatform library to a local Maven repository with the maven-publish Gradle
plugin. Specify the group, version, and the repositories where the library should be published. The
plugin creates publications automatically.

Official documentation: https://kotlinlang.org/docs/multiplatform-publish-lib.html

## Settings

Create this variable on local.properties file:

```properties
signing.keyId={your key id}
signing.password={your key password}
signing.key={your key on base64}
ossrhUsername={oss username}
ossrhPassword={oss password}
```

## Github actions

Configure this variable on actions secrets file:

```shell
KEY_ID:
GPG_PRIVATE_KEY: 
GPG_PRIVATE_PASSWORD:
SONATYPE_USERNAME
SONATYPE_PASSWORD:
```

## References
- [Publishing multiplatform libraries | Kotlin](https://kotlinlang.org/docs/multiplatform-publish-lib.html)
- [Publish a Kotlin/Multiplatform library on Maven Central](https://medium.com/kodein-koders/publish-a-kotlin-multiplatform-library-on-maven-central-6e8a394b7030)
- [Setting up a KMP shared library](https://medium.com/teamsnap-engineering/setting-up-a-kmp-shared-library-5f596afc6e09)
- [How to build and publish a Kotlin Multiplatform library Series' Articles](https://dev.to/kathrinpetrova/series/11926)
- [Kotlin Multiplatform by Tutorials, Chapter 14: Creating Your KMP Library](https://www.kodeco.com/books/kotlin-multiplatform-by-tutorials/v1.0/chapters/14-creating-your-kmp-library)
- [GitHub - KaterinaPetrova/mpp-sample-lib: Sample Kotlin Multiplatform library (jvm + ios + js)](https://www.kodeco.com/books/kotlin-multiplatform-by-tutorials/v1.0/chapters/14-creating-your-kmp-library)
- [Publishing Android libraries to MavenCentral in 2021](https://getstream.io/blog/publishing-libraries-to-mavencentral-2021/#your-first-release)
