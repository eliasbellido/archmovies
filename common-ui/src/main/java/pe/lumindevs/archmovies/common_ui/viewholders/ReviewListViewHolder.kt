package pe.lumindevs.archmovies.common_ui.viewholders

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import pe.lumindevs.archmovies.common_ui.databinding.ItemReviewBinding
import pe.lumindevs.archmovies.entity.Review

class ReviewListViewHolder(val view: View): BaseViewHolder(view){

    private lateinit var review: Review
    private val binding by bindings<ItemReviewBinding>(view)

    override fun bindData(data: Any) {
        if (data is Review){
            review = data
            binding.apply{
                review = data
                executePendingBindings()
            }
        }
    }

    override fun onClick(v: View?) = Unit

    override fun onLongClick(v: View?) = false
}