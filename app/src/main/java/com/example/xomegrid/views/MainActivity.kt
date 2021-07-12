package com.example.xomegrid.views

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.daggerpoc.viewmodel.MainViewModel
import com.example.xomegrid.R
import com.example.xomegrid.adapter.RecyclerViewAdapter
import com.example.xomegrid.databinding.ActivityMainBinding
import com.example.xomegrid.model.FlikrModel
import com.example.xomegrid.model.GenericError


class MainActivity : AppCompatActivity() {
    private var imageAdapter = RecyclerViewAdapter { onImageSelected(it) }

    private fun onImageSelected(it: Any) {

    }

    private val successObserver = Observer<FlikrModel> {
        onSuccessHandle(it)
    }

    private val errorObserver = Observer<GenericError> {
        onErrorHandle(it)
    }

    private fun onErrorHandle(it: GenericError?) {
        Toast.makeText(applicationContext, it?.error, Toast.LENGTH_SHORT).show()

    }

    private fun onSuccessHandle(flikrModel: FlikrModel) {
        Toast.makeText(applicationContext, "Success pressed", Toast.LENGTH_SHORT).show()
        imageAdapter.refresh(flikrModel.photos.photo)
    }

    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setObservers()
        setListeners()
        setAdapters()
    }

    private fun setAdapters() {
        val layoutManager = GridLayoutManager(this, 3)
        // at last set adapter to recycler view.
        binding.gridImagesReclrvw.layoutManager = layoutManager
        binding.gridImagesReclrvw.adapter = imageAdapter
    }


    private fun setObservers() {
        mainViewModel.success.observe(this, successObserver)
        mainViewModel.errorValidation.observe(this, errorObserver)
    }

    private fun setListeners() {
        binding.inputEditText.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {
              startSearching()
            }
            false
        }
        binding.searchButton.setOnClickListener{
            startSearching()
        }
    }

    private fun startSearching() {
        if (binding.inputEditText.text.isEmpty())
            Toast.makeText(applicationContext, "Invalid input..please type something in input field", Toast.LENGTH_SHORT).show()
        else {
            Toast.makeText(applicationContext, "Searching", Toast.LENGTH_SHORT).show()
            mainViewModel.searchForImages(binding.inputEditText.text.toString())
        }
    }
}