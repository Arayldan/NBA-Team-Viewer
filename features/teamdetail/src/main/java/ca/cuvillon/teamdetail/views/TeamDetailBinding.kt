package ca.cuvillon.teamdetail.views

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

internal object TeamDetailBinding {

    @BindingAdapter("showWhenLoading")
    @JvmStatic
    fun showWhenLoading(view: SwipeRefreshLayout, isLoading: Boolean?) {
        if (isLoading != null) {
            view.isRefreshing = isLoading
        }
    }
}
