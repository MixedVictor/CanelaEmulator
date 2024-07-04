package io.github.mixedvictor.canelaemu

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.github.mixedvictor.canelaemu.cassia.CassiaApplication
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream
import java.io.File
import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.UUID
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.deleteIfExists
import kotlin.io.path.deleteRecursively
import kotlin.io.path.exists
import kotlin.io.path.writeText

class SetupActivity : AppCompatActivity() {
    private val TAG = "canola.kt.Setup"

    private val PICK_EXTERNALS_REQUEST_CODE = 1
    private val PICK_RUNTIME_REQUEST_CODE = 2

    private lateinit var RUNTIME_UUID_FOLDER_NAME: Path

    private var cassiaExtSetUp = false
    private var cassiaRuntimeSetUp = false

    private lateinit var selectExternalsButton: Button
    private lateinit var removeExternalsButton: Button
    private lateinit var selectRuntimeButton: Button
    private lateinit var removeRuntimeButton: Button
    private lateinit var finishSetupButton: Button

    private lateinit var externalsStatusText: TextView
    private lateinit var runtimeStatusText: TextView

    private fun pickGzipFile(requestCode: Int) {
        val intent = Intent(Intent.ACTION_GET_CONTENT).addCategory(Intent.CATEGORY_OPENABLE)
            .setType("application/gzip")

        startActivityForResult(
            Intent.createChooser(intent, getString(R.string.select_externals)), requestCode
        )
    }

    private fun extractGzipFile(filePath: String, fileOutputPath: Path) {
        FileInputStream(filePath).use { streamInput ->
            GzipCompressorInputStream(streamInput).use { gzipInput ->
                TarArchiveInputStream(gzipInput).use { tarInput ->
                    while (true) {
                        val entry = tarInput.nextTarEntry ?: break
                        val entryPath =
                            fileOutputPath.resolve(entry.name)
                        if (entry.isSymbolicLink) {
                            val target = Paths.get(entry.linkName)
                            entryPath.deleteIfExists()
                            Files.createSymbolicLink(entryPath, target)
                        } else if (entry.isFile) {
                            entryPath.parent?.let { Files.createDirectories(it) }
                            val entryFile = entryPath.toFile()
                            entryFile.outputStream().use { output ->
                                tarInput.copyTo(output)
                            }
                            entryFile.setExecutable(entry.mode and 0b111 != 0, false)
                        }
                    }
                }
            }
        }
    }

    @Deprecated("DEPRECATED")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == PICK_EXTERNALS_REQUEST_CODE || requestCode == PICK_RUNTIME_REQUEST_CODE) && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                val file = File.createTempFile("tmp", ".tar.gz", cacheDir)
                file.outputStream().use {
                    contentResolver.openInputStream(uri)?.copyTo(it)
                }
                Log.i(TAG, "File copied to cache: '${file.path}'")

                if (requestCode == PICK_EXTERNALS_REQUEST_CODE) {
                    val cassiaExtFilePath =
                        Paths.get(CassiaApplication.instance.cassiaExtPath.toString(), file.name)

                    selectExternalsButton.setEnabled(false)

                    if (!CassiaApplication.instance.cassiaExtPath.exists())
                        Files.createDirectories(CassiaApplication.instance.cassiaExtPath)

                    Files.copy(
                        file.toPath(),
                        cassiaExtFilePath,
                        StandardCopyOption.COPY_ATTRIBUTES
                    )

                    Log.i(
                        TAG,
                        "File successfully copied to '${CassiaApplication.instance.cassiaExtPath}', extracting cassiaext it..."
                    )
                    setStatusText(requestCode, R.string.file_selection_extracting, Color.GREEN)

                    extractGzipFile(cassiaExtFilePath.toString(), CassiaApplication.instance.cassiaExtPath)
                    cassiaExtFilePath.toFile().delete()

                    CassiaApplication.instance.cassiaExtPath.resolve("cassiaext.id")
                        .writeText(UUID.randomUUID().toString())

                    Files.write(
                        CassiaApplication.instance.cassiaExtPath.resolve("README.txt"), """
                        This directory contains Cassia's external dependencies, do NOT touch this unless you know what you're doing.
                        It is managed by the Cassia app, any modifications will be wiped out when a new APK is installed.
                        """.trimIndent().toByteArray()
                    )

                    Log.i(TAG, "ID successfully created, cassiaext is now ready to be used")

                    switchSetUpsToInstalled(true, requestCode)
                    setStatusText(requestCode, R.string.file_selection_ok, Color.GREEN)
                } else if (requestCode == PICK_RUNTIME_REQUEST_CODE) {
                    val cassiaRuntimeFilePath =
                        Paths.get(CassiaApplication.instance.runtimes.path.toString(), file.name)

                    selectRuntimeButton.setEnabled(false)

                    Files.copy(
                        file.toPath(),
                        cassiaRuntimeFilePath,
                        StandardCopyOption.COPY_ATTRIBUTES
                    )

                    Log.i(
                        TAG,
                        "File successfully copied to '${CassiaApplication.instance.cassiaExtPath}', extracting cassiaext it..."
                    )
                    setStatusText(requestCode, R.string.file_selection_extracting, Color.GREEN)

                    extractGzipFile(cassiaRuntimeFilePath.toString(), CassiaApplication.instance.runtimes.path)
                    cassiaRuntimeFilePath.toFile().delete()

                    Files.move(
                        Paths.get(CassiaApplication.instance.runtimes.path.toString(), "prefix"),
                        Paths.get(RUNTIME_UUID_FOLDER_NAME.toString()),
                        StandardCopyOption.ATOMIC_MOVE
                    )

                    switchSetUpsToInstalled(true, requestCode)
                    setStatusText(requestCode, R.string.file_selection_ok, Color.GREEN)
                }
            }
        }
    }

    private fun switchSetUpsToInstalled(installed: Boolean, requestCode: Int) {
        if (installed) {
            if (requestCode == PICK_EXTERNALS_REQUEST_CODE) {
                removeExternalsButton.setEnabled(true)
                selectExternalsButton.setEnabled(false)
                finishSetupButton.setEnabled(true)
                cassiaExtSetUp = true
            } else if (requestCode == PICK_RUNTIME_REQUEST_CODE) {
                removeRuntimeButton.setEnabled(true)
                selectRuntimeButton.setEnabled(false)
                cassiaRuntimeSetUp = true
                setStatusText(PICK_RUNTIME_REQUEST_CODE, R.string.file_already_installed, Color.GRAY)
            }
        } else {
            if (requestCode == PICK_EXTERNALS_REQUEST_CODE) {
                finishSetupButton.setEnabled(false)
                removeExternalsButton.setEnabled(false)
                selectExternalsButton.setEnabled(true)
                cassiaExtSetUp = false
                setStatusText(PICK_EXTERNALS_REQUEST_CODE, R.string.file_selection, Color.BLACK)
            } else if (requestCode == PICK_RUNTIME_REQUEST_CODE) {
                removeRuntimeButton.setEnabled(false)
                selectRuntimeButton.setEnabled(true)
                cassiaRuntimeSetUp = false
                setStatusText(PICK_RUNTIME_REQUEST_CODE, R.string.file_selection, Color.BLACK)
            }
        }
    }

    @OptIn(ExperimentalPathApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)

        RUNTIME_UUID_FOLDER_NAME = Paths.get(CassiaApplication.instance.runtimes.path.toString(), "00000000-0000-0000-0000-000000000000v1")

        selectExternalsButton = findViewById(R.id.select_externals_button)
        removeExternalsButton = findViewById(R.id.remove_externals_button)
        selectRuntimeButton = findViewById(R.id.select_runtime_button)
        removeRuntimeButton = findViewById(R.id.remove_runtime_button)
        finishSetupButton = findViewById(R.id.finish_setup_button)

        externalsStatusText = findViewById(R.id.externals_status_text)
        runtimeStatusText = findViewById(R.id.runtime_status_text)

        selectExternalsButton.setOnClickListener {
            pickGzipFile(PICK_EXTERNALS_REQUEST_CODE)
        }

        selectRuntimeButton.setOnClickListener {
            pickGzipFile(PICK_RUNTIME_REQUEST_CODE)
        }

        removeExternalsButton.setOnClickListener {
            Paths.get(filesDir.absolutePath, "cassiaext").deleteRecursively()
            switchSetUpsToInstalled(false, PICK_EXTERNALS_REQUEST_CODE)
        }

        removeRuntimeButton.setOnClickListener {
            RUNTIME_UUID_FOLDER_NAME.deleteRecursively()
            switchSetUpsToInstalled(false, PICK_EXTERNALS_REQUEST_CODE)
        }

        if (CassiaApplication.instance.cassiaExtPath.exists())
            cassiaExtSetUp = true

        if (RUNTIME_UUID_FOLDER_NAME.exists())
            cassiaRuntimeSetUp = true

        if (cassiaExtSetUp) {
            switchSetUpsToInstalled(true, PICK_EXTERNALS_REQUEST_CODE)
            setStatusText(PICK_EXTERNALS_REQUEST_CODE, R.string.file_already_installed, Color.GRAY)
        }

        if (cassiaRuntimeSetUp) {
            switchSetUpsToInstalled(true, PICK_RUNTIME_REQUEST_CODE)
            setStatusText(PICK_RUNTIME_REQUEST_CODE, R.string.file_already_installed, Color.GRAY)
        }

        finishSetupButton.setOnClickListener {
            finish()
        }
    }

    private fun setStatusText(requestCode: Int, string: Int, color: Int) {
        if (requestCode == PICK_EXTERNALS_REQUEST_CODE) {
            externalsStatusText.setText(string)
            externalsStatusText.setTextColor(color)
        } else if (requestCode == PICK_RUNTIME_REQUEST_CODE) {
            runtimeStatusText.setText(string)
            runtimeStatusText.setTextColor(color)
        }
    }
}