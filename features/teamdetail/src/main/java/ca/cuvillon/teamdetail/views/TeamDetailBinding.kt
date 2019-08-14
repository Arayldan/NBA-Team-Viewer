package ca.cuvillon.teamdetail.views

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ca.cuvillon.model.entities.Player

internal object TeamDetailBinding {

    @BindingAdapter("items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, resource: List<Player>?) {
        resource?.let((recyclerView.adapter as PlayerListAdapter)::submitList)
    }
}
