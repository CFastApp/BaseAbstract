package com.cfastapp.baseabstract.base.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.cfastapp.baseabstract.dialog.ProgressDialog
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {

    lateinit var binding: B
    private var progressDialog = ProgressDialog()
    private var progressIniciado = false

    abstract fun getLayout(): Int
    abstract fun initOnClick()

    fun showMessageSnack(message: String) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT
        ).show()
    }

    fun showMessageToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        initOnClick()
        return binding.root
    }

    fun showProgressDialog() {
        if (!progressIniciado) {
            progressIniciado = true
            progressDialog.isCancelable = false
            progressDialog.show(requireFragmentManager(), "")
        }
    }

    fun hideProgressDialog() {
        if (progressIniciado) {
            progressIniciado = false
            progressDialog.dismiss()
        }
    }

    fun hideKeyboardFrom(
        context: Context,
        view: View
    ) {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    fun <T> goToOtherActivity(cls: Class<T>, finishActual: Boolean = false) {
        val intent = Intent(requireContext(), cls)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME)
        startActivity(intent)
        if (finishActual) requireActivity().finish()
    }
}