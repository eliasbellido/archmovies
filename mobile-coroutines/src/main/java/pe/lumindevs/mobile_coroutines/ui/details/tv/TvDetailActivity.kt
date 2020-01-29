package pe.lumindevs.mobile_coroutines.ui.details.tv

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
import pe.lumindevs.mobile_coroutines.databinding.ActivityTvDetailBinding

class TvDetailActivity : DatabindingActivity() {

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
        observeMessages()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == android.R.id.home) onBackPressed()
        return false
    }

    private fun observeMessages() =
        vm.toastLiveData.observe(this) { shortToast(it) }

    companion object{
        private const val tv = "tv"
        fun startActivitymodel(context: Context?, tvId: Int){
            val intent = Intent(context, TvDetailActivity::class.java)
            intent.putExtra(tv, tvId)
            context?.startActivity(intent)
        }
    }
}
