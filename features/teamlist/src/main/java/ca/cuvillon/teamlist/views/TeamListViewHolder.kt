package ca.cuvillon.teamlist.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ca.cuvillon.model.Team
import ca.cuvillon.teamlist.TeamListViewModel
import ca.cuvillon.teamlist.databinding.ItemTeamlistBinding

internal class TeamListViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    private val binding = ItemTeamlistBinding.bind(parent)

    fun bindTo(team: Team, viewModel: TeamListViewModel) {
        binding.team = team
        binding.viewmodel = viewModel
    }
}
