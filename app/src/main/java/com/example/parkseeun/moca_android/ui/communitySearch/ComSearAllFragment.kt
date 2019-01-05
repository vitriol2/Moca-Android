package com.example.parkseeun.moca_android.ui.communitySearch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_community_search.*
import kotlinx.android.synthetic.main.fragment_com_sear_all.*
import org.sopt.kclean.Controller.ComSearAllAdapter

class ComSearAllFragment : Fragment() {

    val searchList = ArrayList<ComSearAllData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_com_sear_all, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        makeList()
        setRecycler()

    }

    private fun setScreenConvert() {
    }

    private fun makeList() {
        searchList.add(ComSearAllData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "사용자", "안녕하세요", true, null, null, null, 0))
        searchList.add(ComSearAllData(null, null, null, null, "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페명", "과천시", 1))

    }

    private fun setRecycler() {
        rv_frag_comm_sear_all_all.layoutManager = LinearLayoutManager(activity)
        rv_frag_comm_sear_all_all.adapter = ComSearAllAdapter(activity!!, searchList)




    }

}