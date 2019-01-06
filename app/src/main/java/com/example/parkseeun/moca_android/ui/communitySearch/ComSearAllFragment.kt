package com.example.parkseeun.moca_android.ui.communitySearch

import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parkseeun.moca_android.R
import kotlinx.android.synthetic.main.activity_community_search.*
import kotlinx.android.synthetic.main.fragment_com_sear_all.*
import kotlinx.android.synthetic.main.fragment_com_sear_cafe.*
import org.sopt.kclean.Controller.ComSearAllAdapter

class ComSearAllFragment : Fragment() {

    val searchList = ArrayList<ComSearAllData>()

    val reviewTopList = ArrayList<ComSearAllReviewTopData>()
    val popularList = ArrayList<ComSearAllPopData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_com_sear_all, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setScreenConvert()

        makeList()
        setRecycler()


    }



    private fun setScreenConvert() {
        activity!!.et_act_comm_sear.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.isNullOrEmpty()) {
                    ll_frag_com_sear_all_nothing.visibility = View.VISIBLE
                }else{
                    ll_frag_com_sear_all_nothing.visibility = View.GONE
                }
            }
        })
    }

    private fun makeList() {
        searchList.add(ComSearAllData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "사용자", "안녕하세요", true, null, null, null, 0))
        searchList.add(ComSearAllData(null, null, null, null, "http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "카페명", "과천시", 1))

        reviewTopList.add(ComSearAllReviewTopData("https://s3.ap-northeast-2.amazonaws.com/project-sopt/%25E1%2584%258E%25E1%2585%25AC%25E1%2584%2589%25E1%2585%25A5%25E1%2586%25AB%25E", "카페", 100))
        reviewTopList.add(ComSearAllReviewTopData("https://s3.ap-northeast-2.amazonaws.com/project-sopt/%25E1%2584%258E%25E1%2585%25AC%25E1%2584%2589%25E1%2585%25A5%25E1%2586%25AB%25E", "카페", 200))
        reviewTopList.add(ComSearAllReviewTopData("https://s3.ap-northeast-2.amazonaws.com/project-sopt/%25E1%2584%258E%25E1%2585%25AC%25E1%2584%2589%25E1%2585%25A5%25E1%2586%25AB%25E", "카페", 300))

        popularList.add(ComSearAllPopData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "사용자", true))
        popularList.add(ComSearAllPopData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "사용자", true))
        popularList.add(ComSearAllPopData("http://img.hani.co.kr/imgdb/resize/2017/1222/151381249807_20171222.JPG", "사용자", true))

    }

    private fun setRecycler() {



        rv_frag_comm_sear_all_all.layoutManager = LinearLayoutManager(activity)
        rv_frag_comm_sear_all_all.adapter = ComSearAllAdapter(activity!!, searchList)

        rv_frag_com_sear_all_reviewtop.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_frag_com_sear_all_reviewtop.adapter = ComSearAllReviewTopAdapter(activity!!, reviewTopList)

        rv_frag_comm_sear_all_popularuser.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_frag_comm_sear_all_popularuser.adapter = ComSearAllPopUserAdapter(activity!!, popularList)

    }

}