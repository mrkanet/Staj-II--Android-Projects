package com.example.digiturkfilms.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.digiturkfilms.model.GenreModel
import com.example.digiturkfilms.ui.ViewPagerInnerFragment


class ViewPagerAdapter(
    manager: FragmentManager,
    private val genreList: List<GenreModel>
) : FragmentStatePagerAdapter(manager) {
    override fun getItem(position: Int): Fragment = ViewPagerInnerFragment.newInstance(genreList[position].id, genreList[position].name)
    override fun getCount(): Int = genreList.size
}