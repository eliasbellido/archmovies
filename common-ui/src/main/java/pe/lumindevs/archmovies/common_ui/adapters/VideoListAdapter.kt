package pe.lumindevs.archmovies.common_ui.adapters

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow
import pe.lumindevs.archmovies.common_ui.R
import pe.lumindevs.archmovies.common_ui.viewholders.VideoListViewHolder
import pe.lumindevs.archmovies.entity.Video

class VideoListAdapter : BaseAdapter(){

    init {
        addSection(ArrayList<Video>())
    }

    fun addVideoList(videos: List<Video>){
        val section = sections()[0]
        section.addAll(videos)
        notifyItemRangeInserted(section.size - videos.size +1, videos.size)
    }

    override fun layout(sectionRow: SectionRow) = R.layout.item_video

    override fun viewHolder(layout: Int, view: View) = VideoListViewHolder(view)
}