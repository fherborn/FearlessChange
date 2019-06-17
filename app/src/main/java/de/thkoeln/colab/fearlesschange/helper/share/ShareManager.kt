package de.thkoeln.colab.fearlesschange.helper.share

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import android.view.View.MeasureSpec.*
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.core.content.FileProvider
import de.thkoeln.colab.fearlesschange.R
import de.thkoeln.colab.fearlesschange.data.persistance.pattern.Pattern
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


class ShareManager(private val activity: Activity) {

    fun sharePattern(pattern: Pattern) {
        val adapter = PrintPatternAdapter()
        val printView = adapter.inflate(null, activity)
        adapter.bind(pattern)
        shareFile(ShareType.JPEG, getImageFileFrom(pattern.title, printView), R.string.share_text)

    }

    private fun shareFile(type: ShareType, file: File, shareMessage: Int) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = when (type) {
            ShareType.JPEG -> "image/jpeg"
        }

        shareIntent.putExtra(Intent.EXTRA_STREAM, getFileUri(file))
        shareIntent.putExtra(Intent.EXTRA_TEXT, activity.getString(shareMessage))
        activity.startActivity(shareIntent)

    }

    private fun getFileUri(file: File) = FileProvider.getUriForFile(activity, PROVIDER, file)

    private fun getImageFileFrom(title: String, vararg views: View) = getImageFile(mergeBitmap(views.map { getBitmapFromView(it) }), title)
    private fun getImageFile(image: Bitmap, title: String): File {
        val fullName = "$title.jpeg"
        val cachePath = File(activity.cacheDir, IMAGE_CASH_PATH).apply { mkdirs() }
        val stream = FileOutputStream("$cachePath/$fullName")
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        stream.close()

        val bytes = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, bytes)

        return File(cachePath, fullName)
    }

    private fun mergeBitmap(bitmaps: List<Bitmap>, space: Int = 0): Bitmap {
        val height = bitmaps.maxBy { it.height }?.height ?: 0
        val width = bitmaps.sumBy { it.width } + bitmaps.size + 1 * space
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        var currentOffset = space.toFloat()
        bitmaps.forEach {
            canvas.drawBitmap(it, currentOffset, 0f, null)
            currentOffset += it.width + space.toFloat()
        }
        return bitmap
    }

    private fun getMeasureSpecFor(value: Int) = when (value) {
        WRAP_CONTENT, MATCH_PARENT -> makeMeasureSpec(0, UNSPECIFIED)
        else -> makeMeasureSpec(value, EXACTLY)
    }

    private fun setMeasureSpecs(view: View) = view.apply {
        measure(getMeasureSpecFor(layoutParams.width), getMeasureSpecFor(layoutParams.height))
        layout(0, 0, measuredWidth, measuredHeight)
    }

    private fun getBitmapFromView(view: View): Bitmap {

        setMeasureSpecs(view)

        val bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        val bgDrawable = view.background

        if (bgDrawable != null) bgDrawable.draw(canvas)
        else canvas.drawColor(Color.WHITE)

        view.layout(view.left, view.top, view.right, view.bottom)
        view.draw(canvas)
        return bitmap
    }


    companion object {
        private const val IMAGE_CASH_PATH = "images/"
        private const val PROVIDER = "de.thkoeln.fearlesschange.fileprovider"

        private enum class ShareType {
            JPEG
        }
    }
}