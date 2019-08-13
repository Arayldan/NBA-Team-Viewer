package ca.cuvillon.teamlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.cuvillon.common.base.BaseFragment
import ca.cuvillon.common.base.BaseViewModel
import ca.cuvillon.teamlist.databinding.FragmentTeamlistBinding
import ca.cuvillon.teamlist.views.TeamListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A [BaseFragment] subclass that will show a list of team.
 */
internal class TeamListFragment : BaseFragment() {

    private val viewModel: TeamListViewModel by viewModel()
    private lateinit var binding: FragmentTeamlistBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTeamlistBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureRecyclerView()
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    private fun configureRecyclerView() {
        binding.fragmentTeamListRv.adapter = TeamListAdapter(viewModel)
    }
}
