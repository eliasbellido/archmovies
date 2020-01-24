package pe.lumindevs.archmovies.ui.details.tv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.observe
import pe.lumindevs.archmovies.R
import pe.lumindevs.archmovies.base.ViewModelActivity
import pe.lumindevs.archmovies.common_ui.extensions.shortToast
import pe.lumindevs.archmovies.databinding.ActivityTvDetailBinding


class TvDetailActivity : ViewModelActivity() {

    private val vm by viewModel<TvDetailViewModel>()
    private val binding by binding<ActivityTvDetailBinding>(R.layout.activity_tv_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //post the tv id from intent
        vm.postTvId(intent.getIntExtra(tv, 0))
        //binding data into layout view
        with(binding){
            lifecycleOwner = this@TvDetailActivity
            activity = this@TvDetailActivity
            viewModel = vm
            tv = vm.getTv()
        }
        //observe error messages
        observerMessages()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == android.R.id.home) onBackPressed()
        return false
    }

    private fun observerMessages() = vm.toastLiveData.observe(this) { shortToast(it) }

    companion object{
        private const val tv = "tv"
        fun startActivityModel(context: Context?, tvId: Int){
            val intent = Intent(context, TvDetailActivity::class.java)
            intent.putExtra(tv, tvId)
            context?.startActivity(intent)
        }
    }
}