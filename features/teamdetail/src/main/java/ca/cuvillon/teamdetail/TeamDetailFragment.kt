package ca.cuvillon.teamdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import ca.cuvillon.common.base.BaseFragment
import ca.cuvillon.common.base.BaseViewModel
import ca.cuvillon.teamdetail.databinding.FragmentTeamdetailBinding
import ca.cuvillon.teamdetail.views.PlayerListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A [BaseFragment] subclass that will show the team detail.
 */
internal class TeamDetailFragment : BaseFragment() {

    private val viewModel: TeamDetailViewModel by viewModel()
    private val args: TeamDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentTeamdetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTeamdetailBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureRecyclerView()
        viewModel.loadData(args.teamId)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    private fun configureRecyclerView() {
        binding.fragmentTeamDetailRv.adapter = PlayerListAdapter()
    }
}
