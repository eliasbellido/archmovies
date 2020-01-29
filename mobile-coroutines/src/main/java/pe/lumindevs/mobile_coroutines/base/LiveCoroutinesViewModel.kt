package pe.lumindevs.mobile_coroutines.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers

open class LiveCoroutinesViewModel : ViewModel() {
    internal fun <T> launchOnViewModelScope(block: suspend () -> LiveData<T>): LiveData<T>{
        return liveData(viewModelScope.coroutineContext + Dispatchers.IO){
            emitSource(block())
        }
    }
}