package pe.lumindevs.mobile_coroutines.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.android.viewmodel.ext.android.viewModel
import pe.lumindevs.archmovies.common_ui.adapters.MovieListAdapter
import pe.lumindevs.archmovies.common_ui.extensions.shortToast
import pe.lumindevs.archmovies.common_ui.viewholders.MovieListViewHolder
import pe.lumindevs.archmovies.entity.entities.Movie

import pe.lumindevs.mobile_coroutines.R
import pe.lumindevs.mobile_coroutines.base.DatabindingFragment
import pe.lumindevs.mobile_coroutines.databinding.FragmentMovieBinding
import pe.lumindevs.mobile_coroutines.ui.details.movie.MovieDetailActivity
import pe.lumindevs.mobile_coroutines.ui.details.movie.MovieDetailViewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : DatabindingFragment(), MovieListViewHolder.Delegate {

    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentMovieBinding>(
            inflater, R.layout.fragment_movie, container).apply {
            viewModel = this@MovieListFragment.viewModel
            lifecycleOwner = this@MovieListFragment
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        loadMore(page = 1)
        observeMessages()
    }

    private fun initUI(){
        recyclerView.adapter = MovieListAdapter(this)
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

    private fun loadMore(page: Int) = viewModel.postMoviePage(page)

    override fun onItemClick(movie: Movie) {
        Timber.d("click on movie: ${movie.original_title}")
        context?.shortToast("click on movie: ${movie.original_title}")
        MovieDetailActivity.startActivityModel(requireContext(), movie.id)
    }

    private fun observeMessages() = viewModel.toastLiveData.observe(this) { context?.shortToast(it)}


}
