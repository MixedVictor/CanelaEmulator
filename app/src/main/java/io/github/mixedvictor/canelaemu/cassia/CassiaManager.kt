package io.github.mixedvictor.canelaemu.cassia

import android.view.Surface
import io.github.mixedvictor.canelaemu.cassia.store.Prefix
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class CassiaManager {
    companion object {
        init {
            System.loadLibrary("cassia")
        }
    }

    private external fun startServer(runtimePath: String, prefixPath: String, cassiaExtPath: String)

    private external fun stopServer()

    external fun setSurface(surface: Surface?)

    private val mutex = Mutex()

    var runningPrefix: Prefix? = null
        private set

    suspend fun start(prefixUUID: String) {
        mutex.withLock {
            if (runningPrefix != null)
                throw IllegalStateException("A prefix is already running")
            val prefix = CassiaApplication.instance.prefixes.updateLinks(prefixUUID)
            runningPrefix = prefix

            withContext(Dispatchers.IO) {
                startServer(prefix.runtimePath.toString(), prefix.path.toString(), CassiaApplication.instance.cassiaExtPath.toString())
            }
        }
    }

    suspend fun stop() {
        mutex.withLock {
            if (runningPrefix == null)
                throw IllegalStateException("No prefix is running")
            runningPrefix = null

            withContext(Dispatchers.IO) {
                stopServer()
            }
        }
    }
}
