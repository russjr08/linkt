# Linkt — A Kotlin/Java wrapper for the LinkAce API

### About this project

Welcome to Linkt! The goal of this project is to be a wrapper library for
the [self-hosted bookmark archive, LinkAce](https://www.linkace.org/).

LinkAce is quite handy! It is a utility that I've come to appreciate quite a bit,
as it helps me keep a bunch of links and bookmarks organized. I personally prefer to use
it as more of a "Read It Later" tool, though it works great for general bookmarks as well!

Since LinkAce is a web application, by its nature it is cross-platform. However, my goal is to
eventually create an Android app for it, along with some other utilities. To be able to do this,
I wanted to make sure I had a library that I understood how to use out-of-the-box to create those.

Although, I haven't actually looked to see if there are any existing libraries for LinkAce out there...
If there are, I'm sure they're fantastic libraries!

I'm by no means a professional, so part of the reason this project exists is to just give me some generalized
experience in using [Retrofit,](https://square.github.io/retrofit/) which is an HTTP client for Java and Android.

### Utilizing this library

Eventually I'd like to make sure this library is published, probably through GitHub's [Maven Package Repository]
(https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry)
however, I'll determine what is most likely best to work out once I get a bit closer to having a release ready.

For now, while I work on getting things ready, if you'd like to test out this library, you can build the project with
[Gradle](https://gradle.org) through the project's gradle wrapper. To do this, run the following:

```shell
./gradlew build
```

If you are on a Unix based system, you may need to make the gradle wrapper executable with `chmod +x gradlew`. Windows
users can use the same command, but it would most likely be `.\gradlew.bat build` instead.

If you already have Gradle installed and wish to use that instead, please make sure you're using Gradle 8.2!

This process probably won't include the libraries used by this project, so please be sure that your own project includes
the libraries listed in [the build.gradle file](./build.gradle).

After you've connected the library to your project, you'll want to start by having a look at 
the [LinktClient class](./src/main/kotlin/com/divbyruss/libraries/linkt/LinktClient.kt) which should get you started.

It also has some example code listed in its `main` function as well - if you use this, be sure to pass in the
`LA_HOST` and `LA_API_KEY` environmental variables (for the base URL of the target LinkAce server, and API key,
respectively).