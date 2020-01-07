package pe.lumindevs.archmovies.common_ui.viewholders

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_movie_favourite.view.*
import pe.lumindevs.archmovies.common_ui.databinding.ItemMovieBinding
import pe.lumindevs.archmovies.entity.entities.Movie

/** MovieListViewHolder is a viewHolder class for binding a [Movie] item.*/
class MovieListViewHolder(
    view: View,
    private val delegate: Delegate
): BaseViewHolder(view){

    interface Delegate{
        fun onItemClick(movie: Movie)
    }

    private lateinit var movie: Movie
    private val binding by bindings<ItemMovieBinding>(view)

    override fun bindData(data: Any) {
        if(data is Movie){
            movie = data
            binding.apply {
                movie = data
                palette = itemView.item_poster_title
                executePendingBindings()
            }
        }
    }

    override fun onClick(v: View?) = delegate.onItemClick(movie)

    override fun onLongClick(v: View?): Boolean = false
}