package com.lequ.main.modules.share.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import cn.dankal.demo.R
import com.lequ.main.modules.share.base.BaseShareDialogFragment

open class MusicShareDialogFragment : BaseShareDialogFragment() {

    companion object {
        fun getInstance(model: ModelForShare): MusicShareDialogFragment {
            return MusicShareDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_MODEL, model)
                }
            }
        }
    }

    var onClickMakeVideo: () -> Unit = {}
    var onClickShareToMoment: () -> Unit = {}
    var onClickDownloadMusic: () -> Unit = {}
    var onClickSaveWallPaper: () -> Unit = {}

    override fun loadParas() {
        super.loadParas()
        model = arguments?.getParcelable(KEY_MODEL) as ModelForShare
    }

    override fun addActionItem(container: LinearLayout) {
        super.addActionItem(container)
        val view = LayoutInflater.from(context).inflate(R.layout.music_share_dialog_lay, container)
        // 目前先注释, 以后开放入口再说
        view.btn_make_video.visibility = View.GONE
        view.btn_make_video.setOnClickListener {
            dismiss()
            onClickMakeVideo()
        }
        view.btn_share_to_moment.setOnClickListener {
            dismiss()
            onClickShareToMoment()
        }
        view.btn_download_music.setOnClickListener {
            dismiss()
            onClickDownloadMusic()
        }
        view.btn_save_wall_paper.setOnClickListener {
            dismiss()
            onClickSaveWallPaper()
        }
    }
}