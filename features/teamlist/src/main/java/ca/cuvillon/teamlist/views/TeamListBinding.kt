package ca.cuvillon.teamlist.views

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ca.cuvillon.model.entities.Team
import ca.cuvillon.repository.utils.Resource

object TeamListBinding {

    @BindingAdapter("app:items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, resource: Resource<List<Team>>?) {
        resource?.data?.let((recyclerView.adapter as TeamListAdapter)::submitList)
    }

    @BindingAdapter("app:showWhenLoading")
    @JvmStatic
    fun <T> showWhenLoading(view: SwipeRefreshLayout, resource: Resource<T>?) {
        if (resource != null) {
            view.isRefreshing = resource is Resource.Loading
        }
    }

}
