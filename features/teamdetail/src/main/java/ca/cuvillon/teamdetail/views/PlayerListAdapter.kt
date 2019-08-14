package ca.cuvillon.teamdetail.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ca.cuvillon.model.entities.Player
import ca.cuvillon.teamdetail.databinding.ItemPlayerlistBinding

internal class PlayerListAdapter : ListAdapter<Player, PlayerViewHolder>(PlayerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPlayerlistBinding.inflate(layoutInflater, parent, false)
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}
