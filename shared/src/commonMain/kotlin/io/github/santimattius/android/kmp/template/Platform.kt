package io.github.santimattius.android.kmp.template

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform