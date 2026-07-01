package com.cyberkidstudios.smartreps

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.gif.GifDecoder
import coil3.PlatformContext

@HiltAndroidApp
class SmartRepsApplication : Application(), SingletonImageLoader.Factory {
    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader.Builder(context)
            .components {
                add(GifDecoder.Factory())
            }
            .build()
    }
}
