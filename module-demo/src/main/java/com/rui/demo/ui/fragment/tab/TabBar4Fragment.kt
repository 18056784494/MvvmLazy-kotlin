package com.rui.demo.ui.fragment.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rui.demo.BR
import com.rui.demo.R
import com.rui.demo.databinding.TestFragmentTabBar4Binding
import com.rui.mvvmlazy.base.fragment.BaseVmDbFragment
import com.rui.mvvmlazy.base.BaseViewModel

/**
 * Created by zjr on 2018/7/18.
 */
class TabBar4Fragment : BaseVmDbFragment<TestFragmentTabBar4Binding, BaseViewModel>() {
    override fun initContentView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Int {
        return R.layout.test_fragment_tab_bar_4
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    companion object {
        fun newInstance(): TabBar4Fragment {
            val args = Bundle()
            val fragment = TabBar4Fragment()
            fragment.arguments = args
            return fragment
        }
    }
}