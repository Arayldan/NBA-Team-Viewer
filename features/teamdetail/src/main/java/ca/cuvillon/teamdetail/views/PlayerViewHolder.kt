package ca.cuvillon.teamdetail.views

import androidx.recyclerview.widget.RecyclerView
import ca.cuvillon.model.entities.Player
import ca.cuvillon.teamdetail.databinding.ItemPlayerlistBinding

internal class PlayerViewHolder(private val binding: ItemPlayerlistBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(player: Player) {
        binding.player = player
    }
}
