package pe.lumindevs.archmovies.common_ui.viewholders

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_tv.view.*
import pe.lumindevs.archmovies.common_ui.databinding.ItemTvBinding
import pe.lumindevs.archmovies.entity.entities.Tv

class TvListViewHolder (
    view: View,
    private val delegate: Delegate
): BaseViewHolder(view){

    interface Delegate{
        fun onItemClick(tv: Tv)
    }

    private lateinit var tv: Tv
    private val binding by bindings<ItemTvBinding>(view)

    override fun bindData(data: Any) {
        if (data is Tv){
            tv = data
            binding.apply {
               tv = data
               palette = itemView.item_poster_title
               executePendingBindings()
            }
        }
    }

    override fun onClick(v: View?) = delegate.onItemClick(tv)

    override fun onLongClick(v: View?) = false
}