package pe.lumindevs.archmovies.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import kotlinx.android.synthetic.main.fragment_tv.*

import pe.lumindevs.archmovies.R
import pe.lumindevs.archmovies.base.ViewModelFragment
import pe.lumindevs.archmovies.common_ui.adapters.TvListAdapter
import pe.lumindevs.archmovies.common_ui.extensions.shortToast
import pe.lumindevs.archmovies.common_ui.viewholders.TvListViewHolder
import pe.lumindevs.archmovies.databinding.FragmentTvBinding
import pe.lumindevs.archmovies.entity.entities.Tv
import pe.lumindevs.archmovies.ui.details.tv.TvDetailActivity
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class TvListFragment : ViewModelFragment(), TvListViewHolder.Delegate {

    private val viewModel by viewModel<MainActivityViewModel>()
    private lateinit var binding:  FragmentTvBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.fragment_tv, container)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
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
        TvDetailActivity.startActivityModel(requireContext(), tv.id)
    }

    private fun observeMessages() =
        this.viewModel.toastLiveData.observe(this) { context?.shortToast(it)}


}
