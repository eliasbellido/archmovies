package pe.lumindevs.archmovies.common_ui.viewholders

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_video.view.*
import pe.lumindevs.archmovies.common_ui.databinding.ItemVideoBinding
import pe.lumindevs.archmovies.entity.Video

class VideoListViewHolder(val view: View) : BaseViewHolder(view){

    private lateinit var video: Video
    private val binding by bindings<ItemVideoBinding>(view)

    override fun bindData(data: Any) {
        if(data is Video){
            video = data
            binding.apply {
                video = data
                palette = itemView.item_video_title
                executePendingBindings()
            }

        }
    }

    override fun onClick(v: View?) = Unit

    override fun onLongClick(v: View?) = false
}