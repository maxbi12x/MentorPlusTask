package com.example.mentorplustask

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mentorplustask.databinding.ActivityMainBinding
import com.example.mentorplustask.model.DishModel
//import com.example.mentorplustask.repository.Repository
import com.example.mentorplustask.viewmodel.MainViewModel
import com.example.mentorplustask.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity(),MainFragment.onSomeEventListener {
    private lateinit var binding : ActivityMainBinding
    private var amount =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragment : Fragment = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fl,fragment).commit()
        binding.backButton.setOnClickListener{
            onBackPressed()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun someEvent(s: Int) {
        amount+=s
        binding.totalPrice.text= "₹ $amount"
    }

}