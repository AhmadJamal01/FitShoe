package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding

class ShoeListFragment : Fragment() {
    //the viewModel is shared across all fragments belonging to the activity
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list,container,false
        )

        binding.shoeListFab.setOnClickListener(
            Navigation.createNavigateOnClickListener(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        )

        //add an options menu to the list fragment for the logout button
        setHasOptionsMenu(true)

        //observe the shoeList from viewModel
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { newList ->
            //for each item in the list, we need to inflate a shoeItem layout and set its text views using the attributes from the Shoe model
            newList.forEach{shoe ->
                //first inflate the layout
                val shoeItemBinding = ShoeItemBinding.inflate(LayoutInflater.from(requireContext()))
                //then set the shoe attributes
                shoeItemBinding.nameTextView.text = shoe.name
                shoeItemBinding.companyTextView.text = shoe.company
                shoeItemBinding.sizeTextView.text = shoe.size.toString()
                shoeItemBinding.descriptionTextView.text = shoe.description
                //the following was done only to add margins between cards
                //for some reason adding the margins from the xml did not work
                val layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                layoutParams.setMargins(16)
                shoeItemBinding.root.layoutParams = layoutParams
                binding.shoeListLinearLayout.addView(shoeItemBinding.root)
            }
        })

        return binding.root
    }

    //inflating the menu for the logout button
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.user_menu,menu)
    }

    //overriding the function and adding a navigation in order to properly control the popUpto behavior
    //this could have been done using the commented line, but then pressing back would return to the login fragment
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.loginFragment -> findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        }
        return super.onOptionsItemSelected(item)
//        return NavigationUI.onNavDestinationSelected(item,view!!.findNavController())||super.onOptionsItemSelected(item)
    }
}