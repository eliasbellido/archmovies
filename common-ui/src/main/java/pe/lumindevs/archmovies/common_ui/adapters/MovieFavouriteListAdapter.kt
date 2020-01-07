package pe.lumindevs.archmovies.common_ui.adapters

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import pe.lumindevs.archmovies.common_ui.R
import pe.lumindevs.archmovies.common_ui.viewholders.MovieFavouriteListViewHolder
import pe.lumindevs.archmovies.entity.entities.Movie

/** MovieFavouriteListAdapter is an adapter class for binding favourite [Movie] items.*/
class MovieFavouriteListAdapter (
    private val delegate: MovieFavouriteListViewHolder.Delegate
): BaseAdapter(){

    init {
        addSection(ArrayList<Movie>())
    }

    fun addMovieList(movies: List<Movie>){
        val section = sections()[0]
        val callback = MovieFavouriteDiffCallback(section as List<Movie>, movies)
        val result = DiffUtil.calculateDiff(callback)
        section.clear()
        section.addAll(movies)
        result.dispatchUpdatesTo(this)
    }

    override fun layout(sectionRow: SectionRow) = R.layout.item_movie_favourite

    override fun viewHolder(layout: Int, view: View) = MovieFavouriteListViewHolder(view, delegate)

}