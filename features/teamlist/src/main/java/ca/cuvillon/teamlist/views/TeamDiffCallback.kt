package ca.cuvillon.teamlist.views

import androidx.recyclerview.widget.DiffUtil
import ca.cuvillon.model.entities.Team

internal class TeamDiffCallback : DiffUtil.ItemCallback<Team>() {

    override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
        return oldItem.name == newItem.name
    }
}
