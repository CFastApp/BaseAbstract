package com.cfastapp.baseabstract.dialog

import com.cfastapp.baseabstract.R
import com.cfastapp.baseabstract.base.dialogfragment.BaseDialogFragment
import com.cfastapp.baseabstract.databinding.DialogSinInternetBinding

class SinInternetDialog : BaseDialogFragment<DialogSinInternetBinding>(false) {
    override fun getLayout(): Int {
        return R.layout.dialog_sin_internet
    }

    override fun initOnClick() {
        binding.reintentar.setOnClickListener {
            dismiss()
            (activity as SinInternetListener).onClickReintentarConexion()
        }
    }

    interface SinInternetListener {
        fun onClickReintentarConexion()
    }
}