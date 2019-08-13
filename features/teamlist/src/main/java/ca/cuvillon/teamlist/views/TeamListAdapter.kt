package ca.cuvillon.teamlist.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import ca.cuvillon.model.entities.Team
import ca.cuvillon.teamlist.R
import ca.cuvillon.teamlist.TeamListViewModel
import ca.cuvillon.teamlist.databinding.ItemTeamlistBinding

internal class TeamListAdapter(private val viewModel: TeamListViewModel) :
    ListAdapter<Team, TeamViewHolder>(TeamDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemTeamlistBinding>(
            layoutInflater, R.layout.item_teamlist, parent, false
        )
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindTo(getItem(position), viewModel)
    }
}
