package pe.lumindevs.archmovies.common_ui.adapters

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow
import pe.lumindevs.archmovies.common_ui.R
import pe.lumindevs.archmovies.common_ui.viewholders.PeopleViewHolder
import pe.lumindevs.archmovies.entity.entities.Person

class PeopleAdapter(
    private val delegate: PeopleViewHolder.Delegate
) : BaseAdapter() {

    init{
        addSection(ArrayList<Person>())
    }

    fun addPerson(people: List<Person>){
        sections()[0].addAll(people)
        notifyDataSetChanged()
    }

    override fun layout(sectionRow: SectionRow) = R.layout.item_person

    override fun viewHolder(layout: Int, view: View) = PeopleViewHolder(view, delegate)
}