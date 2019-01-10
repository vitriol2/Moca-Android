package com.example.parkseeun.moca_android.ui.communitySearch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parkseeun.moca_android.R
import com.example.parkseeun.moca_android.model.get.GetCommunitySearchListResponse
import com.example.parkseeun.moca_android.model.get.SearchUserData
import com.example.parkseeun.moca_android.network.ApplicationController
import com.example.parkseeun.moca_android.util.User
import kotlinx.android.synthetic.main.activity_community_search.*
import kotlinx.android.synthetic.main.fragment_com_sear_user.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComSearUserFragment : Fragment() {
    // 통신
    private val networkService  = ApplicationController.instance.networkService
    private lateinit var getCommunitySearchListResponse : Call<GetCommunitySearchListResponse>
    private var searchUserList = ArrayList<SearchUserData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_com_sear_user, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setScreenConvert()
    }

    private fun setScreenConvert() {
        activity!!.et_act_comm_sear.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                getAllResult(s.toString())

            }
        })
    }

    private fun setRecyclerView() {
        val RvUser : RecyclerView = view!!.findViewById(R.id.rv_frag_com_sear_user)
        RvUser.layoutManager =  LinearLayoutManager(activity)
        RvUser.adapter = ComSearUserAdapter(activity!!, searchUserList)
    }


    // 통신 (카페 탭)
    private fun getAllResult(searchString : String) {
        getCommunitySearchListResponse = networkService.getCommunitySearchResult(User.token!!, searchString)
        getCommunitySearchListResponse.enqueue(object : Callback<GetCommunitySearchListResponse> {
            override fun onFailure(call: Call<GetCommunitySearchListResponse>, t: Throwable) {
                toast(t.message.toString())
            }

            override fun onResponse(call: Call<GetCommunitySearchListResponse>, response: Response<GetCommunitySearchListResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        var communitySearchData = response.body()!!.data

                        searchUserList = communitySearchData.searchUserList

                        setRecyclerView()
                    }
                }
                else {
                    toast("response failed")
                }
            }
        })
    }
}