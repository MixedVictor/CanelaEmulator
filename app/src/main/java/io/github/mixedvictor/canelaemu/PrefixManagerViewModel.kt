package io.github.mixedvictor.canelaemu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.mixedvictor.canelaemu.cassia.CassiaApplication
import io.github.mixedvictor.canelaemu.cassia.TermuxX11
import io.github.mixedvictor.canelaemu.cassia.store.Prefix
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class PrefixManagerViewModel : ViewModel() {
    var prefixIsStarting = false
        private set
    var prefixCanBeReset = false
        private set
    var prefixIsStopping = false
        private set
    var prefixIsRunning = false
        private set

    var defaultRuntime: String? = null
        private set
    var prefix: Prefix? = null
        private set

    init {
        viewModelScope.launch {
            defaultRuntime = CassiaApplication.instance.runtimes.getDefault()?.id
            prefix = CassiaApplication.instance.prefixes.getDefault()
        }
    }

    fun prefixCreate(name: String = "Default") {
        defaultRuntime?.let {
            MainScope().launch {
                prefix = CassiaApplication.instance.prefixes.create(name, it)
            }
        }
    }

    fun prefixReset() {
        prefix?.let {
            MainScope().launch {
                prefix = CassiaApplication.instance.prefixes.reset(it.uuid)
                prefixCanBeReset = false
            }
        }
    }

    fun prefixStart() {
        prefix?.let {
            MainScope().launch {
                prefixIsStarting = true
                CassiaApplication.instance.manager.start(it.uuid)
                prefixIsRunning = true
                prefixIsStarting = false
            }
        }
    }

    fun prefixStop() {
        MainScope().launch {
            prefixIsStopping = true
            CassiaApplication.instance.manager.stop()
            prefixIsStopping = false
            prefixIsRunning = false
            prefixCanBeReset = true
        }
    }

    fun launchTermuxX11() {
        MainScope().launch {
            TermuxX11.main(arrayOf(":0"))
        }
    }
}