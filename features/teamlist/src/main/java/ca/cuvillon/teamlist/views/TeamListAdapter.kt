package ca.cuvillon.teamlist.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ca.cuvillon.model.Team
import ca.cuvillon.teamlist.R
import ca.cuvillon.teamlist.TeamListViewModel

internal class TeamListAdapter(private val viewModel: TeamListViewModel) : RecyclerView.Adapter<TeamListViewHolder>() {

    private val teams: MutableList<Team> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamListViewHolder {
        return TeamListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_teamlist, parent, false))
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: TeamListViewHolder, position: Int) {
        holder.bindTo(teams[position], viewModel)
    }

    fun updateData(items: List<Team>) {
        val diffCallback = TeamItemDiffCallback(teams, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        teams.clear()
        teams.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }
}
