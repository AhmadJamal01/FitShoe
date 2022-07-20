package com.udacity.shoestore

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel: ViewModel() {

    //the shoeList field will contain the list of shoes we want to display
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    //a shoe object which will hold the data the user enters
    var newShoe = Shoe("", 0.0, "", "")

    init{
        //adding initial values to the shoe list
        _shoeList.value = mutableListOf(
            Shoe("Air Force 1", 40.0,"Nike","The “Nike Air Force 1 comes out on top in every single state as the most popular and searched for iconic sneaker in 2021"),
            Shoe("Chuck Taylor All Star",41.0,"Converse", "eBay notes that across “42 states, Converse is one of the sneaker brands most on the rise in search"),
            Shoe("Canvas Sk8-HI",42.0,"Vans","This deconstructed iteration of a lace-up high top includes a fitted silhouette made with canvas and Van’s signature rubber waffle soles"),
            Shoe("Fresh Foam Cruzv1 Reissue",43.0,"New Balance","Comfort meets style with this sneaker")
        )
    }

    //adds the new shoe to the list of shoes, then clears the newShoe object to clear the input fields
    fun addNewShoe(shoe: Shoe){
        _shoeList.value?.add(shoe)
        newShoe = Shoe("", 0.0, "", "")
    }
}