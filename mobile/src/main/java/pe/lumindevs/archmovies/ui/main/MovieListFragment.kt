package pe.lumindevs.archmovies.ui.main


import android.os.Bundle
import androidx.lifecycle.observe
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import kotlinx.android.synthetic.main.fragment_movie.*

import pe.lumindevs.archmovies.R
import pe.lumindevs.archmovies.base.ViewModelFragment
import pe.lumindevs.archmovies.common_ui.adapters.MovieListAdapter
import pe.lumindevs.archmovies.common_ui.extensions.shortToast
import pe.lumindevs.archmovies.common_ui.viewholders.MovieListViewHolder
import pe.lumindevs.archmovies.databinding.FragmentMovieBinding
import pe.lumindevs.archmovies.entity.entities.Movie
import pe.lumindevs.archmovies.ui.details.movie.MovieDetailActivity
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : ViewModelFragment(), MovieListViewHolder.Delegate {

    private val viewModel by viewModel<MainActivityViewModel>()
    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.fragment_movie, container)
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

    private fun observeMessages() =
        this.viewModel.toastLiveData.observe(this){
            Timber.d("something changed while observing")
            context?.shortToast(it)
        }


}
