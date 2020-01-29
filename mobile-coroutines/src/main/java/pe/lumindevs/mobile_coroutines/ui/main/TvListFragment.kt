package pe.lumindevs.mobile_coroutines.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import kotlinx.android.synthetic.main.fragment_tv.*
import org.koin.android.viewmodel.ext.android.viewModel
import pe.lumindevs.archmovies.common_ui.adapters.TvListAdapter
import pe.lumindevs.archmovies.common_ui.extensions.shortToast
import pe.lumindevs.archmovies.common_ui.viewholders.TvListViewHolder
import pe.lumindevs.archmovies.entity.entities.Tv
import pe.lumindevs.mobile_coroutines.R
import pe.lumindevs.mobile_coroutines.base.DatabindingFragment
import pe.lumindevs.mobile_coroutines.databinding.FragmentTvBinding
import pe.lumindevs.mobile_coroutines.ui.details.tv.TvDetailActivity
import timber.log.Timber


class TvListFragment : DatabindingFragment(), TvListViewHolder.Delegate {

    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentTvBinding>(
            inflater,
            R.layout.fragment_tv, container).apply {
            viewModel = this@TvListFragment.viewModel
            lifecycleOwner = this@TvListFragment
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        loadMore(page = 1)
        observeMessages()
    }

    private fun initUI(){
        recyclerView.adapter = TvListAdapter(this)
        RecyclerViewPaginator(
            recyclerView = recyclerView,
            isLoading = { viewModel.isLoading() },
            loadMore = { loadMore(it) },
            onLast = { false }
        ).apply {
            threshold = 4
            currentPage = 1
        }
    }

    private fun loadMore(page: Int) = viewModel.postTvPage(page)

    override fun onItemClick(tv: Tv) {
        Timber.d("click on tv: ${tv.name}")
        context?.shortToast("click on tv: ${tv.name}")
        TvDetailActivity.startActivitymodel(requireContext(), tv.id)
    }

    private fun observeMessages() =
        viewModel.toastLiveData.observe(this) { context?.shortToast(it) }


}
