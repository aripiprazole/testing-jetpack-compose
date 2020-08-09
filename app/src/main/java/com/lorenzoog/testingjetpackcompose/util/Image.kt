package com.lorenzoog.testingjetpackcompose.util

import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import androidx.annotation.Px
import androidx.compose.*
import androidx.core.graphics.drawable.toBitmap
import androidx.ui.core.*
import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.graphics.ImageAsset
import androidx.ui.graphics.RectangleShape
import androidx.ui.graphics.Shape
import androidx.ui.graphics.asImageAsset
import androidx.ui.unit.IntPx.Companion.Infinity
import androidx.ui.unit.IntPxSize.Companion.Zero
import coil.Coil
import coil.request.LoadRequest
import coil.request.LoadRequestBuilder
import coil.size.Scale
import kotlinx.coroutines.*

/**
 * https://github.com/luca992/coil-composable/blob/master/coil-composable/src/androidMain/kotlin/com/luca992/compose/image/CoilImage.kt
 */
@Composable
fun CoilImage(
  model: Any,
  modifier: Modifier = Modifier,
  customize: LoadRequestBuilder.() -> Unit = {}
) {
  WithConstraints(modifier) {
    var width =
      if (constraints.maxWidth > Zero.width && constraints.maxWidth < Infinity) {
        constraints.maxWidth.value
      } else {
        -1
      }

    var height =
      if (constraints.maxHeight > Zero.height && constraints.maxHeight < Infinity) {
        constraints.maxHeight.value
      } else {
        -1
      }

    //if height xor width not able to be determined, make image a square of the determined dimension
    if (width == -1) width = height
    if (height == -1) height = width

    val image = state { ImageAsset(width, height) }
    val context = ContextAmbient.current

    var animationJob: Job? = remember { null }

    onCommit(model) {
      val target = object : coil.target.Target {
        override fun onStart(placeholder: Drawable?) {
          placeholder?.apply {
            animationJob?.cancel()

            if (height != -1 && width != -1) {
              animationJob = image.update(this, width, height)
            } else if (height == -1) {
              val scaledHeight = intrinsicHeight * (width / intrinsicWidth)
              animationJob = image.update(this, width, scaledHeight)
            } else if (width == -1) {
              val scaledWidth = intrinsicWidth * (height / intrinsicHeight)
              animationJob = image.update(this, scaledWidth, height)
            }
          }
        }

        override fun onSuccess(result: Drawable) {
          animationJob?.cancel()
          animationJob = image.update(result)
        }

        override fun onError(error: Drawable?) {
          error?.run {
            animationJob?.cancel()
            animationJob = image.update(error)
          }
        }
      }

      val request = LoadRequest.Builder(context)
        .data(model)
        .size(width, height)
        .scale(Scale.FILL)
        .apply { customize(this) }
        .target(target)

      val requestDisposable = Coil.imageLoader(context).execute(request.build())

      onDispose {
        image.value = ImageAsset(width, height)
        requestDisposable.dispose()
        animationJob?.cancel()
      }
    }

    Image(modifier = modifier, asset = image.value)
  }
}

internal fun MutableState<ImageAsset>.update(
  drawable: Drawable,
  @Px width: Int? = null,
  @Px height: Int? = null
): Job? {
  if (drawable is Animatable) {
    (drawable as Animatable).start()

    return GlobalScope.launch(Dispatchers.Default) {
      while (true) {
        val asset = drawable
          .toBitmap(
            width = width ?: drawable.intrinsicWidth,
            height = height ?: drawable.intrinsicHeight
          )
          .asImageAsset()

        withContext(Dispatchers.Main) {
          value = asset
        }

        delay(16)

        // 1000 ms / 60 fps = 16.666 ms/fps
        // TODO: figure out most efficient way to dispaly a gif
      }
    }
  } else {
    value = drawable
      .toBitmap(
        width = width ?: drawable.intrinsicWidth,
        height = height ?: drawable.intrinsicHeight
      )
      .asImageAsset()

    return null
  }
}
