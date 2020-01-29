package pe.lumindevs.mobile_coroutines.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_favourites.view.*
import kotlinx.android.synthetic.main.toolbar_home.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import pe.lumindevs.archmovies.common_ui.adapters.MovieFavouriteListAdapter
import pe.lumindevs.archmovies.common_ui.adapters.TvFavouriteListAdapter
import pe.lumindevs.archmovies.common_ui.customs.FlourishFactory
import pe.lumindevs.archmovies.common_ui.extensions.shortToast
import pe.lumindevs.archmovies.common_ui.viewholders.MovieFavouriteListViewHolder
import pe.lumindevs.archmovies.common_ui.viewholders.TvFavouriteListViewHolder
import pe.lumindevs.archmovies.entity.entities.Movie
import pe.lumindevs.archmovies.entity.entities.Tv
import pe.lumindevs.mobile_coroutines.R
import pe.lumindevs.mobile_coroutines.ui.details.movie.MovieDetailActivity
import pe.lumindevs.mobile_coroutines.ui.details.tv.TvDetailActivity
import timber.log.Timber

class MainActivity : AppCompatActivity(),
    MovieFavouriteListViewHolder.Delegate, TvFavouriteListViewHolder.Delegate{

    private val viewModel by viewModel<MainActivityViewModel>()

    private val adapterMovieList = MovieFavouriteListAdapter(this)
    private val adapterTvList = TvFavouriteListAdapter(this)

    private val flourish by lazy {
        FlourishFactory.create(parentView, R.layout.layout_favourites)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI(){
        with(main_viewpager){
            adapter = MainPagerAdapter(supportFragmentManager)
            offscreenPageLimit = 3
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) = Unit
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) = Unit

                override fun onPageSelected(position: Int) {
                    main_bottom_navigation.menu.getItem(position).isChecked = true
                }
            })
            main_bottom_navigation.setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.action_one -> currentItem = 0
                    R.id.action_two -> currentItem = 1
                    R.id.action_three -> currentItem = 2
                }
                true
            }

        }

        flourish.flourishView.rcvMovies.adapter = adapterMovieList
        flourish.flourishView.rcvTvs.adapter = adapterTvList
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
        adapterTvList.addTvList(viewModel.getFavouriteTvList())
    }

    override fun onItemClick(movie: Movie) {
        Timber.d("click on favourite movie: ${movie.original_title}")
        this.shortToast("click on favourite movie: ${movie.original_title}")
        MovieDetailActivity.startActivityModel(this, movie.id)

    }

    override fun onItemClick(tv: Tv) {
        Timber.d("click on favourite tv: ${tv.name}")
        this.shortToast("click on favourite tv: ${tv.name}")
        TvDetailActivity.startActivitymodel(this, tv.id)

    }

    override fun onBackPressed() {
        if(flourish.isShowing()){
            flourish.dismiss()
        }else{
            super.onBackPressed()
        }
    }

}
