package pe.lumindevs.archmovies.common_ui.adapters

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import pe.lumindevs.archmovies.common_ui.R
import pe.lumindevs.archmovies.common_ui.viewholders.ReviewListViewHolder
import pe.lumindevs.archmovies.entity.Review

class ReviewListAdapter : BaseAdapter(){

    init {
        addSection(ArrayList<Review>())
    }

    fun addReviewList(reviews: List<Review>){
        val section = sections()[0]
        section.addAll(reviews)
        notifyItemRangeInserted(section.size - reviews.size + 1, reviews.size)
    }

    override fun layout(sectionRow: SectionRow) = R.layout.item_review

    override fun viewHolder(layout: Int, view: View) = ReviewListViewHolder(view)
}