package pe.lumindevs.archmovies.common_ui.adapters

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import pe.lumindevs.archmovies.common_ui.R
import pe.lumindevs.archmovies.common_ui.viewholders.TvFavouriteListViewHolder
import pe.lumindevs.archmovies.entity.entities.Tv

class TvFavouriteListAdapter (
    private val delegate: TvFavouriteListViewHolder.Delegate
): BaseAdapter(){

    init {
        addSection(ArrayList<Tv>())
    }

    fun addTvList(tvList: List<Tv>){
        val section = sections()[0]
        val callback = TvFavouriteDiffCallback(section as List<Tv>, tvList)
        val result = DiffUtil.calculateDiff(callback)
        section.clear()
        section.addAll(tvList)
        result.dispatchUpdatesTo(this)
    }

    override fun layout(sectionRow: SectionRow) = R.layout.item_tv_favourite

    override fun viewHolder(layout: Int, view: View) = TvFavouriteListViewHolder(view, delegate)
}