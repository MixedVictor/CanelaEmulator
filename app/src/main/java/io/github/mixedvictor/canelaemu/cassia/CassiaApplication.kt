package io.github.mixedvictor.canelaemu.cassia

import android.app.Application
//import io.github.mixedvictor.canelaemu.cassia.store.CassiaExtStore
import io.github.mixedvictor.canelaemu.cassia.store.PrefixStore
import io.github.mixedvictor.canelaemu.cassia.store.RuntimeStore
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.nio.file.Path
import java.nio.file.Paths

class CassiaApplication : Application() {
    companion object {
        lateinit var instance: CassiaApplication
            private set
    }

    init {
        instance = this
    }

    lateinit var cassiaExtPath: Path
        private set
    lateinit var runtimes: RuntimeStore
        private set
    lateinit var prefixes: PrefixStore
        private set
    lateinit var manager: CassiaManager
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

//        cassiaExt = CassiaExtStore(Paths.get(filesDir.absolutePath, "cassiaext"))
        cassiaExtPath = Paths.get(filesDir.absolutePath, "cassiaext")
        runtimes = RuntimeStore(Paths.get(filesDir.absolutePath, "runtimes"))
        prefixes = PrefixStore(Paths.get(filesDir.absolutePath, "prefixes"))
        MainScope().launch {
            runtimes.scan()
            prefixes.scan()
        }
        manager = CassiaManager()
    }
}
