package pe.lumindevs.mobile_coroutines.ui.details.movie

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.observe
import org.koin.android.viewmodel.ext.android.viewModel
import pe.lumindevs.archmovies.common_ui.extensions.shortToast
import pe.lumindevs.mobile_coroutines.R
import pe.lumindevs.mobile_coroutines.base.DatabindingActivity
import pe.lumindevs.mobile_coroutines.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : DatabindingActivity() {

    private val vm by viewModel<MovieDetailViewModel>()
    private val binding by binding<ActivityMovieDetailBinding>(R.layout.activity_movie_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //post the movie id from intent
        vm.postMovieId(intent.getIntExtra(movie, 0))
        // binding data into layout view
        with(binding){
            lifecycleOwner = this@MovieDetailActivity
            activity = this@MovieDetailActivity
            viewModel = vm
            movie = vm.getMovie()
        }
        //observe error messages
        observeMessages()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == android.R.id.home) onBackPressed()
        return false
    }

    private fun observeMessages() = vm.toastLiveData.observe(this){ shortToast(it) }

    companion object{
        private const val movie = "movie"
        fun startActivityModel(context: Context?, movieId: Int){
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(movie, movieId)
            context?.startActivity(intent)
        }
    }
}
