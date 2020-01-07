package pe.lumindevs.archmovies.common_ui.adapters

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow
import pe.lumindevs.archmovies.common_ui.R
import pe.lumindevs.archmovies.common_ui.viewholders.MovieListViewHolder
import pe.lumindevs.archmovies.entity.entities.Movie

class MovieListAdapter (
    private val delegate: MovieListViewHolder.Delegate
): BaseAdapter(){

    init {
        addSection(ArrayList<Movie>())
    }

    fun addMovieList(movies: List<Movie>){
        val section = sections()[0]
        section.addAll(movies)
        notifyItemRangeInserted(section.size - movies.size +1, movies.size )
    }

    override fun layout(sectionRow: SectionRow) = R.layout.item_movie

    override fun viewHolder(layout: Int, view: View) = MovieListViewHolder(view, delegate)
}