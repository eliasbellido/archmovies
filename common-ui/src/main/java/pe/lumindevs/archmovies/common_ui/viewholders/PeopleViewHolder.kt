package pe.lumindevs.archmovies.common_ui.viewholders

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_person.view.*
import pe.lumindevs.archmovies.common_ui.databinding.ItemPersonBinding
import pe.lumindevs.archmovies.entity.entities.Person

/** PeopleViewHolder is a a viewholder class for binding a [Person] item.*/
class PeopleViewHolder (
    view: View,
    private val delegate: Delegate
) : BaseViewHolder(view){

    interface Delegate{
        fun onItemClick(person: Person, view: View)
    }

    private lateinit var person: Person
    private val binding by bindings<ItemPersonBinding>(view)

    override fun bindData(data: Any) {
        if(data is Person){
            person = data
            binding.apply {
                person = data
                executePendingBindings()
            }
        }
    }

    override fun onClick(v: View?) =
        delegate.onItemClick(person, itemView.item_person_profile)

    override fun onLongClick(v: View?) = false
}