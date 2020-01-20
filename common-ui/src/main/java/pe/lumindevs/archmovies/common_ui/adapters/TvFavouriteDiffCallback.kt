package pe.lumindevs.archmovies.common_ui.adapters

import androidx.recyclerview.widget.DiffUtil
import pe.lumindevs.archmovies.entity.entities.Tv

class TvFavouriteDiffCallback (
    private val oldList: List<Tv>,
    private val newList: List<Tv>
): DiffUtil.Callback(){

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}