package com.example.intellifyassignment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.firebase.ui.firestore.paging.LoadingState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_people.*
import java.lang.Exception


class PeopleFragment : Fragment() {

    lateinit var mAdapter:FirestorePagingAdapter<User,UserViewHolder>
    val auth = Firebase.auth
    val database by lazy{
        FirebaseFirestore.getInstance().collection("users")
                .orderBy("name",Query.Direction.ASCENDING)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setAdapter()
        return inflater.inflate(R.layout.fragment_people,container,false)
    }

    private fun setAdapter() {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPrefetchDistance(2)
                .setPageSize(10)
                .build()
        val options = FirestorePagingOptions.Builder<User>()
                .setLifecycleOwner(viewLifecycleOwner)
                .setQuery(database, config, User::class.java)
                .build()
        mAdapter = object : FirestorePagingAdapter<User,UserViewHolder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
               val view = layoutInflater.inflate(R.layout.list_item,parent,false)
                return UserViewHolder(view)
            }



            override fun onLoadingStateChanged(state: LoadingState) {
                super.onLoadingStateChanged(state)
                when(state){
                    LoadingState.LOADING_INITIAL -> {
                    }

                    LoadingState.LOADING_MORE -> {
                    }

                    LoadingState.LOADED -> {
                    }

                    LoadingState.ERROR -> {
                    }

                    LoadingState.FINISHED -> {
                    }

                }
            }

            override fun onError(e: Exception) {
                super.onError(e)
            }


            override fun onBindViewHolder(holder: UserViewHolder, position: Int, model: User) {
                    holder.bind(user=model){ name: String, photo: String,city:String,mailaddr:String, id: String ->
                        val intent = Intent(requireContext(),DisplayUserActivity::class.java)
                        intent.putExtra(UID,id)
                        intent.putExtra(NAME,name)
                        intent.putExtra(IMAGE,photo)
                        intent.putExtra(CITY,city)
                        intent.putExtra(EMAIL,mailaddr)
                        startActivity(intent)
                    }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
    }
}