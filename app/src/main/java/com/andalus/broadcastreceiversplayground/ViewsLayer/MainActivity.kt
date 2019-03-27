package com.andalus.broadcastreceiversplayground.ViewsLayer

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
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
        tabLayout.apply {
            setupWithViewPager(viewPager, true)
            setBackgroundColor(ContextCompat.getColor(applicationContext,R.color.colorPrimary))
            // TODO setSelectedTabIndicatorColor()
            // TODO setSelectedTabIndicator()
            getTabAt(0)?.text = "Contacts"
            getTabAt(1)?.text = "Blocked"
            getTabAt(2)?.text = "History"
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    private class ContactsPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> ContactsFragment()
                1 -> BlockedFragment()
                2 -> HistoryFragment()
                else -> ContactsFragment()
            }
        }

        override fun getCount(): Int {
            return 3
        }

    }
}
