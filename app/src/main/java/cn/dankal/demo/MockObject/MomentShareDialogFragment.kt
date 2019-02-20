package com.lequ.main.modules.share.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.lequ.main.modules.share.base.BaseShareDialogFragment
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction

class MomentShareDialogFragment : BaseShareDialogFragment() {

    companion object {
        fun getInstance(model: ModelForShare): MomentShareDialogFragment {
            return MomentShareDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_MODEL, model)
                }
            }
        }
    }

    var onClickDelete: () -> Unit = {}
    private var momentId = ""
    private var isMine: Boolean = false

    override fun loadParas() {
        super.loadParas()
        arguments?.run {
            model = getParcelable(KEY_MODEL) as ModelForShare
            momentId = model.extras?.getString("mid") ?: ""
            isMine = LoginContext.getInstance().validateSelf(model.extras?.getString("uid") ?: "")
        }
    }

    override fun addActionItem(container: LinearLayout) {
        super.addActionItem(container)
        val view = LayoutInflater.from(context).inflate(R.layout.moment_share_dialog_lay, container)
        view.btn_share_delete.visibility = if (isMine) View.VISIBLE else View.GONE
        view.btn_share_delete.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        QMUIDialog.MessageDialogBuilder(context)
                .setMessage("确认删除动态？")
                .addAction("取消") { dialog, _ ->
                    dialog.dismiss()
                }
                .addAction(0, "确定", QMUIDialogAction.ACTION_PROP_NEGATIVE) { dialog, _ ->
                    onClickDelete()
                    dialog.dismiss()
                }
                .show()
    }
}