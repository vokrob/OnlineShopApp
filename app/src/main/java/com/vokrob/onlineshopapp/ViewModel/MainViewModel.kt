package com.vokrob.onlineshopapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.vokrob.onlineshopapp.Model.CategoryModel
import com.vokrob.onlineshopapp.Model.SliderModel

class MainViewModel() : ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _category = MutableLiveData<MutableList<CategoryModel>>()

    val banners: LiveData<List<SliderModel>> = _banner
    val categories: LiveData<MutableList<CategoryModel>> = _category

    fun loadBanners() {
        val Ref = firebaseDatabase.getReference("Banner")

        Ref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val lists = mutableListOf<SliderModel>()

                    for (childSnapshot in snapshot.children) {
                        val list = childSnapshot.getValue(SliderModel::class.java)
                        if (list != null) lists.add(list)
                    }
                    _banner.value = lists
                }

                override fun onCancelled(error: DatabaseError) {}
            }
        )
    }

    fun loadCategory() {
        val Ref = firebaseDatabase.getReference("Category")

        Ref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val lists = mutableListOf<CategoryModel>()

                    for (childSnapshot in snapshot.children) {
                        val list = childSnapshot.getValue(CategoryModel::class.java)
                        if (list != null) lists.add(list)
                    }
                    _category.value = lists
                }

                override fun onCancelled(error: DatabaseError) {}
            }
        )
    }
}

























