package pe.lumindevs.archmovies.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import kotlinx.android.synthetic.main.fragment_person.*
import kotlinx.android.synthetic.main.fragment_person.recyclerView
import kotlinx.android.synthetic.main.fragment_tv.*

import pe.lumindevs.archmovies.R
import pe.lumindevs.archmovies.base.ViewModelFragment
import pe.lumindevs.archmovies.common_ui.adapters.PeopleAdapter
import pe.lumindevs.archmovies.common_ui.extensions.shortToast
import pe.lumindevs.archmovies.common_ui.viewholders.PeopleViewHolder
import pe.lumindevs.archmovies.databinding.FragmentPersonBinding
import pe.lumindevs.archmovies.entity.entities.Person
import timber.log.Timber


class PersonListFragment : ViewModelFragment(), PeopleViewHolder.Delegate {
    
    private val viewModel by viewModel<MainActivityViewModel>()
    private lateinit var binding: FragmentPersonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.fragment_person, container)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        loadMore(page = 1)
        observerMessages()
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
    }

    private fun observerMessages() =
        this.viewModel.toastLiveData.observe(this) { context?.shortToast(it) }


}
