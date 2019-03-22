package com.andalus.broadcastreceiversplayground.ViewsLayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.andalus.broadcastreceiversplayground.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpTabLayout()
    }

    private fun setUpTabLayout() {
        val tabLayout = mainActivityTabLayout
        val viewPager = mainActivityViewPager
        val pagerAdapter =
            ContactsPagerAdapter(supportFragmentManager)

        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager, true)
    }

    private class ContactsPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> ContactsListFragment()
                1 -> BlockedContactsFragment()
                else -> ContactsListFragment()
            }
        }

        override fun getCount(): Int {
            return 2
        }

    }
}
