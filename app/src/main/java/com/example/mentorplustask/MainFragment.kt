package com.example.mentorplustask

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mentorplustask.repository.Repository
import com.example.mentorplustask.retrofitservices.ApiService
import com.example.mentorplustask.retrofitservices.RetrofitHelper
import com.example.mentorplustask.viewmodel.MainViewModel
import com.example.mentorplustask.viewmodel.MainViewModelFactory
import java.lang.ClassCastException

class MainFragment : Fragment() {

    lateinit var viewModel : MainViewModel
    lateinit var someEventListener : onSomeEventListener
    private var rv: RecyclerView?=null
    interface onSomeEventListener {
        fun someEvent(s: Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =inflater.inflate(R.layout.fragment_main, container, false)
        v.findViewById<TextView>(R.id.coupon).setText(Html.fromHtml(getString(R.string.save_144_with_party)));
        rv = v.findViewById(R.id.dishRV)
        return v
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            someEventListener = activity as onSomeEventListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$activity must implement onSomeEventListener")
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = RetrofitHelper.getInstance().create(ApiService::class.java)
        val repository = Repository(service, requireActivity())
        viewModel = ViewModelProvider(this, MainViewModelFactory(repository,requireActivity())).get(
            MainViewModel::class.java)
         viewModel.dishes.observe(requireActivity(),{data->
             if(data.size>0)
                 rv?.visibility=View.VISIBLE
             // dataRegister to keep the amount of dishes selected currently
             val dataRegister =ArrayList<Int>()
             //provides mainActivity with first price every item selected once
             var firstPrice = 0
             for(values in data){
                 firstPrice+=values.price
                 dataRegister.add(1)
             }
             someEventListener.someEvent(firstPrice)


             rv!!.layoutManager= LinearLayoutManager(requireContext())
             rv!!.setHasFixedSize(true)
             var adaptr = MyAdapter(data,dataRegister)
             rv!!.adapter= adaptr


             adaptr.setOnClickListener(
                 object : MyAdapter.OnClickListener{
                     @SuppressLint("NotifyDataSetChanged")
                     override fun onClick(price : Int, position: Int, isDelete : Boolean) {
                         someEventListener.someEvent(price)
                         if(isDelete){
                             data.removeAt(position)
                             dataRegister.removeAt(position)
                             rv!!.adapter!!.notifyDataSetChanged()
                             if(data.size==0)
                                 rv?.visibility=View.GONE
                         }

                     }

                 })
        })
    }
}