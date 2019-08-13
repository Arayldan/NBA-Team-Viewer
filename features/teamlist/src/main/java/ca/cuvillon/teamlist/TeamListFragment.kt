package ca.cuvillon.teamlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.cuvillon.common.base.BaseFragment
import ca.cuvillon.common.base.BaseViewModel

/**
 * A [BaseFragment] subclass that will show a list of team.
 */
class TeamListFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_teamlist, container, false)
    }

    override fun getViewModel(): BaseViewModel {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}
