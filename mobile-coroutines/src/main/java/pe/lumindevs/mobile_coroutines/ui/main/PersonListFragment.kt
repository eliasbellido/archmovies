package pe.lumindevs.mobile_coroutines.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import kotlinx.android.synthetic.main.fragment_person.*
import org.koin.android.viewmodel.ext.android.viewModel
import pe.lumindevs.archmovies.common_ui.adapters.PeopleAdapter
import pe.lumindevs.archmovies.common_ui.bindings.observeFavourite
import pe.lumindevs.archmovies.common_ui.extensions.shortToast
import pe.lumindevs.archmovies.common_ui.viewholders.PeopleViewHolder
import pe.lumindevs.archmovies.entity.entities.Person

import pe.lumindevs.mobile_coroutines.R
import pe.lumindevs.mobile_coroutines.base.DatabindingFragment
import pe.lumindevs.mobile_coroutines.databinding.FragmentPersonBinding
import pe.lumindevs.mobile_coroutines.ui.details.person.PersonDetailActivity
import timber.log.Timber


class PersonListFragment : DatabindingFragment(), PeopleViewHolder.Delegate {

    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentPersonBinding>(
            inflater, R.layout.fragment_person, container
        ).apply {
            viewModel = this@PersonListFragment.viewModel
            lifecycleOwner = this@PersonListFragment
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        loadMore(page = 1)
        observeMessages()
    }

    private fun initUI(){
        recyclerView.adapter = PeopleAdapter(this)
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

    private fun loadMore(page: Int) = viewModel.postPeoplePage(page)

    override fun onItemClick(person: Person, view: View) {
        Timber.d("click on person: ${person.name}")
        context?.shortToast("click on person: ${person.name}")
        PersonDetailActivity.startActivity(activity, person.id, view)
    }

    private fun observeMessages() =
        viewModel.toastLiveData.observe(this) { context?.shortToast(it)}


}
