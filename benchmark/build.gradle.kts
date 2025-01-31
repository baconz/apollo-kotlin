buildscript {
  repositories {
    mavenCentral()
    google()
    maven {
      url = uri("../build/localMaven")
    }
  }
  dependencies {
    classpath("com.apollographql.apollo3.benchmark:build-logic")
  }
}

plugins {
  id("net.mbonnin.golatac").version("0.0.3")
}

golatac.init(file("../gradle/libraries.toml"))

allprojects {
  repositories {
    mavenCentral()
    google()
    maven {
      url = rootProject.uri("../build/localMaven")
    }
  }
}
