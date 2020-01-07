package pe.lumindevs.archmovies.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_favourites.view.*
import kotlinx.android.synthetic.main.toolbar_home.view.*
import pe.lumindevs.archmovies.R
import pe.lumindevs.archmovies.base.ViewModelActivity
import pe.lumindevs.archmovies.common_ui.adapters.MovieFavouriteListAdapter
import pe.lumindevs.archmovies.common_ui.customs.FlourishFactory
import pe.lumindevs.archmovies.common_ui.viewholders.MovieFavouriteListViewHolder
import pe.lumindevs.archmovies.common_ui.viewholders.TvFavouriteListViewHolder
import pe.lumindevs.archmovies.entity.entities.Movie
import pe.lumindevs.archmovies.entity.entities.Tv
import javax.inject.Inject

class MainActivity : ViewModelActivity(), HasSupportFragmentInjector,
    MovieFavouriteListViewHolder.Delegate, TvFavouriteListViewHolder.Delegate
{

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    private val viewModel by viewModel<MainActivityViewModel>()

    private val adapterMovieList = MovieFavouriteListAdapter(this)
    //private val adapterTvList = TvFavouriteListAdapter(this)

    private val flourish by lazy {
        FlourishFactory.create(parentView, R.layout.layout_favourites)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI(){

        flourish.flourishView.rcvMovies.adapter = adapterMovieList
        //flourish.flourishView.rcvTvs.adapter = adapterTvList
        flourish.flourishView.back.setOnClickListener { flourish.dismiss() }
        main_toolbar.toolbar_favourite.setOnClickListener {
            refreshFavourites()
            flourish.show()
        }
    }

    override fun onResume() {
        super.onResume()
        refreshFavourites()
    }

    private fun refreshFavourites(){
        adapterMovieList.addMovieList(viewModel.getFavouriteMovieList())
        //agregar tv
    }

    override fun onItemClick(movies: Movie) {

    }

    override fun onItemClick(tv: Tv) {

    }

    override fun onBackPressed() {
        if (flourish.isShowing()){
            flourish.dismiss()
        }else{
            super.onBackPressed()
        }
    }

    override fun supportFragmentInjector() = fragmentInjector
}