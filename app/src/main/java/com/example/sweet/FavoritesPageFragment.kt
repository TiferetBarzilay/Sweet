package com.example.sweet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class FavoritesPageFragment : Fragment()
{

 var favoritesRecyclerView: RecyclerView?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_page, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(view)
}

    private fun setupUI(view: View)
    {
        favoritesRecyclerView=view.findViewById(R.id.rvFavoritesPageFragment)
        favoritesRecyclerView?.setHasFixedSize(true)//משפר ביצועים
    }


    class FavoritesViewHolder(val itemView: View):RecyclerView.ViewHolder(itemView){

    }
    inner class FavoritesRecyclerAdapter:RecyclerView.Adapter<FavoritesViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

    }
    }
