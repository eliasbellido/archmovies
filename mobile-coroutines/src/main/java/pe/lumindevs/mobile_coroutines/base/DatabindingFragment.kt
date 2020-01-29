package pe.lumindevs.mobile_coroutines.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * ViewModelFragment is an abstract class for providing [DataBindingUtil].
 * provides implementation of only [ViewDataBinding] from an abstract information.
 * Do not modify this class. This is a first-level abstraction class.
 * If you want to add more specifications, make another class which extends [DataBindinFragment]
 *
 * */
abstract class DatabindingFragment : Fragment() {

    protected inline fun <reified T : ViewDataBinding> binding(
        inflater: LayoutInflater,
        @LayoutRes resId: Int,
        container: ViewGroup?
    ) : T = DataBindingUtil.inflate(inflater, resId, container, false)
}