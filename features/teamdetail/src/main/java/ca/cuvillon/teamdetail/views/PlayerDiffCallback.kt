package ca.cuvillon.teamdetail.views

import androidx.recyclerview.widget.DiffUtil
import ca.cuvillon.model.entities.Player

internal class PlayerDiffCallback : DiffUtil.ItemCallback<Player>() {

    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.firstName == newItem.firstName &&
            oldItem.lastName == newItem.lastName &&
            oldItem.position == newItem.position &&
            oldItem.number == newItem.number
    }
}
