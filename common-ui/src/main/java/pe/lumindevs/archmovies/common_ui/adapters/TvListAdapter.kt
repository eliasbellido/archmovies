package pe.lumindevs.archmovies.common_ui.adapters

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import pe.lumindevs.archmovies.common_ui.R
import pe.lumindevs.archmovies.common_ui.viewholders.TvListViewHolder
import pe.lumindevs.archmovies.entity.entities.Tv

class TvListAdapter(
    private val delegate: TvListViewHolder.Delegate
) : BaseAdapter() {

    init{
        addSection(ArrayList<Tv>())
    }

    fun addTvList(tvs: List<Tv>){
        val section = sections()[0]
        section.addAll(tvs)
        notifyItemRangeInserted(section.size - tvs.size + 1, tvs.size)
    }

    override fun layout(sectionRow: SectionRow) = R.layout.item_tv

    override fun viewHolder(layout: Int, view: View) = TvListViewHolder(view, delegate)
}