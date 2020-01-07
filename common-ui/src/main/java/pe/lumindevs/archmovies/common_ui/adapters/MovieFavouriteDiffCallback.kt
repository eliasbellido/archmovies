package pe.lumindevs.archmovies.common_ui.adapters

import androidx.recyclerview.widget.DiffUtil
import pe.lumindevs.archmovies.entity.entities.Movie

class MovieFavouriteDiffCallback(
    private val oldList: List<Movie>,
    private val newList: List<Movie>
): DiffUtil.Callback(){

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}