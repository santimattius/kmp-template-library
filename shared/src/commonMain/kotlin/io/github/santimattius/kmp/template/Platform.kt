package io.github.santimattius.kmp.template

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform