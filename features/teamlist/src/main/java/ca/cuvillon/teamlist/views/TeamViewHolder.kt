package ca.cuvillon.teamlist.views

import androidx.recyclerview.widget.RecyclerView
import ca.cuvillon.model.entities.Team
import ca.cuvillon.teamlist.TeamListViewModel
import ca.cuvillon.teamlist.databinding.ItemTeamlistBinding

internal class TeamViewHolder(private val binding: ItemTeamlistBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(team: Team, viewModel: TeamListViewModel) {
        binding.team = team
        binding.viewmodel = viewModel
    }
}
