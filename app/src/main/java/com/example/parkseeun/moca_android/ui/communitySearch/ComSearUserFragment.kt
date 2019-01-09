package com.example.parkseeun.moca_android.ui.communitySearch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.fragment_com_sear_user.*

class ComSearUserFragment : Fragment() {
    val userList = ArrayList<ComSearUserData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_com_sear_user, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setNetwork()

        makeList()

        setRecyclerView()
    }

    private fun setNetwork() {

    }

    private fun makeList() {
        userList.add(ComSearUserData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "하정우", "좋다", true))
        userList.add(ComSearUserData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "하정우", "좋다", false))
        userList.add(ComSearUserData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "하정우", "좋다", true))
        userList.add(ComSearUserData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "하정우", "좋다", false))

    }

    private fun setRecyclerView() {
        rv_frag_com_sear_user.layoutManager = LinearLayoutManager(activity)
//        rv_frag_com_sear_user.adapter = ComSearUserAdapter(activity!!, userList)
    }


}