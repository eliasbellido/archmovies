package pe.lumindevs.archmovies.ui.details.person

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.observe
import pe.lumindevs.archmovies.R
import pe.lumindevs.archmovies.base.ViewModelActivity
import pe.lumindevs.archmovies.common_ui.extensions.checkIsMaterialVersion
import pe.lumindevs.archmovies.common_ui.extensions.shortToast
import pe.lumindevs.archmovies.databinding.ActivityPersonDetailBinding
import timber.log.Timber

class PersonDetailActivity : ViewModelActivity() {

    private val vm by viewModel<PersonDetailViewModel>()
    private val binding by binding<ActivityPersonDetailBinding>(R.layout.activity_person_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //post the person id from intent
        vm.postPersonId(intent.getIntExtra(person, 0))
        //binding data into layout view
        with(binding){
            lifecycleOwner = this@PersonDetailActivity
            activity = this@PersonDetailActivity
            viewModel = vm
            person = vm.getPerson()
        }

        observeMessages()

    }

    private fun observeMessages() = vm.toastLiveData.observe(this) { shortToast(it) }

    companion object{
        const val person = "person"
        private const val INTENT_REQUEST_CODE = 1000

        fun startActivity(activity: Activity?, personId: Int, view: View){
            if(activity != null){
                if (checkIsMaterialVersion()) {
                    val intent = Intent(activity, PersonDetailActivity::class.java)
                    ViewCompat.getTransitionName(view)?.let {
                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, it)
                        intent.putExtra(person, personId)
                        activity.startActivityForResult(intent, INTENT_REQUEST_CODE, options.toBundle())
                    }
                } else {
                    val intent = Intent(activity, PersonDetailActivity::class.java)
                    intent.putExtra(person, personId)
                    activity.startActivity(intent)
                }
            }
        }
    }
}