package com.example.testmap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.testmap.databinding.ActivityMapsBinding
import kotlin.properties.Delegates


class MapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapsBinding
    private var locationPermissionGranted by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //중앙 프래그먼트 이동명령
        initNavigationBar()
    }

    //하단 네비게이션바 선택에따른 프래그먼트 이동코드
    fun initNavigationBar() {
        binding.bottomNavigation.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.item_list -> {
                        changeFragment(restaurant_list())
                    }
                    R.id.item_map -> {
                        changeFragment(GmapViewFragment())
                    }
                }
                true
            }
            selectedItemId = R.id.item_map
        }
    }

    //실제로 프래그먼트를 바꾸는 코드
    fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.mainFrame.id, fragment).commit()
    }
}