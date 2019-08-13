package ca.cuvillon.teamlist.views

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ca.cuvillon.model.entities.Team
import ca.cuvillon.repository.utils.Resource

object TeamListBinding {

    @BindingAdapter("app:items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, resource: Resource<List<Team>>?) {
        resource?.data?.let((recyclerView.adapter as TeamListAdapter)::submitList)
    }
}
