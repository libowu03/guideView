package com.test.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kit.pagerCard.bean.PagerCardBean
import kotlinx.android.synthetic.main.pagercard_fragment.*

class PagerCarFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pagercard_fragment,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val list = mutableListOf<PagerCardBean>()
        list.add(PagerCardBean("http://hbimg.b0.upaiyun.com/357d23d074c2954d568d1a6f86a5be09d190a45116e95-0jh9Pg_fw658"))
        list.add(PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569591923995&di=23164aeb16b55d649bad5509cb7e3048&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D7d2272098782b9013dadc333438ca97e%2F10dfa9ec8a136327d00e9b1d9c8fa0ec08fac739.jpg"))
        list.add(PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569591923995&di=23164aeb16b55d649bad5509cb7e3048&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D7d2272098782b9013dadc333438ca97e%2F10dfa9ec8a136327d00e9b1d9c8fa0ec08fac739.jpg"))
        list.add(PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569591923995&di=23164aeb16b55d649bad5509cb7e3048&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D7d2272098782b9013dadc333438ca97e%2F10dfa9ec8a136327d00e9b1d9c8fa0ec08fac739.jpg"))
        list.add(PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569591923995&di=23164aeb16b55d649bad5509cb7e3048&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D7d2272098782b9013dadc333438ca97e%2F10dfa9ec8a136327d00e9b1d9c8fa0ec08fac739.jpg"))
        list.add(PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569591923995&di=23164aeb16b55d649bad5509cb7e3048&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D7d2272098782b9013dadc333438ca97e%2F10dfa9ec8a136327d00e9b1d9c8fa0ec08fac739.jpg"))
        list.add(PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569591923995&di=23164aeb16b55d649bad5509cb7e3048&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D7d2272098782b9013dadc333438ca97e%2F10dfa9ec8a136327d00e9b1d9c8fa0ec08fac739.jpg"))
        pagerCardView.setCardContent(list,childFragmentManager,3,2)
    }
}