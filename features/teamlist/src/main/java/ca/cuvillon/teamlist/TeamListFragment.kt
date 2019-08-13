package ca.cuvillon.teamlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import ca.cuvillon.common.base.BaseFragment
import ca.cuvillon.common.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * A [BaseFragment] subclass that will show a list of team.
 */
internal class TeamListFragment : BaseFragment() {

    private val viewModel: TeamListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_teamlist, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.teams.observe(this, Observer { Timber.e("TEST %s", it) })
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }
}
