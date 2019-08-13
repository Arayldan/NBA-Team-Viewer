package ca.cuvillon.teamdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import ca.cuvillon.common.base.BaseFragment
import ca.cuvillon.common.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A [BaseFragment] subclass that will show the team detail.
 */
internal class TeamDetailFragment : BaseFragment() {

    private val viewModel: TeamDetailViewModel by viewModel()
    private val args: TeamDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_teamdetail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadDate(args.teamId)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }
}
