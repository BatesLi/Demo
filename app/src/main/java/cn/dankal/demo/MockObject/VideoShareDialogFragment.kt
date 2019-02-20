package com.lequ.main.modules.share.fragment

import android.os.Bundle
import com.lequ.main.modules.share.base.BaseShareDialogFragment

class VideoShareDialogFragment : BaseShareDialogFragment() {

    companion object {
        fun getInstance(model: ModelForShare): VideoShareDialogFragment {
            return VideoShareDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_MODEL, model)
                }
            }
        }
    }

    override fun loadParas() {
        super.loadParas()
        arguments?.run {
            model = getParcelable(KEY_MODEL) as ModelForShare
        }
    }
}