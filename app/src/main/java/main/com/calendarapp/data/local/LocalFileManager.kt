package main.com.calendarapp.data.local

import android.content.Context
import io.reactivex.subjects.BehaviorSubject
import java.io.File
import java.net.URI
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousFileChannel
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.CompletableFuture
import java.util.function.Supplier


//TODO(Muss noch eingebunden werden)
class LocalFileManager(private val context: Context) : FileManager {

    private var file: File? = null
    private val fileName: String = "main.json"


    init {
        file = File(context.dataDir, fileName)
    }

    override fun getContent(): String? {
        return file?.readLines(Charset.defaultCharset())?.reduce{a, b -> a.plus(b)}
    }

    override fun appendContent(content:String){
        file?.appendText(content)
    }

    override fun reset() {
        file?.writeText("")
    }

}