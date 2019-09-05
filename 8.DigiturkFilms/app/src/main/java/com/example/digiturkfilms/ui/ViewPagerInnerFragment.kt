package com.example.digiturkfilms.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.digiturkfilms.R
import com.example.digiturkfilms.adapters.RecyclerViewAdapter
import com.example.digiturkfilms.model.ResultModel
import com.example.digiturkfilms.network.ApiClient
import com.example.digiturkfilms.network.ApiService
import com.example.digiturkfilms.network.SendRequest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.vp_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.lang.NullPointerException

class ViewPagerInnerFragment : Fragment() {
    private lateinit var mAdapter: RecyclerViewAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.vp_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        genre_name.text = arguments?.get("genName").toString()

        SendRequest.getGenreList(arguments?.getInt("genId")!!,
                { _, _response ->
                    mAdapter = RecyclerViewAdapter(_response.body()?.results!!)
                    recyclerView.apply {
                        layoutManager = GridLayoutManager(context, 4)
                        adapter = mAdapter
                    }
                },
                { _, _failure ->
                    _failure.message
                }
        )

    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int, name: String): ViewPagerInnerFragment {
            val mainFragment = ViewPagerInnerFragment()
            mainFragment.arguments = bundleOf("genId" to id, "genName" to name)
            return mainFragment
        }
    }


}

