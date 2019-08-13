package ca.cuvillon.common.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun getViewModel(): BaseViewModel
}
