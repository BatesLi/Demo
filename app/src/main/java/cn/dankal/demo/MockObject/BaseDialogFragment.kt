package com.lequ.main.base

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.dankal.demo.R

/**
 * desc: todo Overview
 * createed by liyuzheng on 2018/12/4 16:12
 */
abstract class BaseDialogFragment : DialogFragment() {

    protected abstract fun getLayoutId(): Int
    protected abstract fun setup()
    protected open fun loadParas() {}
    protected open fun getHeight(): Int { // 占据屏幕高度的比例，默认自适应
        return ViewGroup.LayoutParams.WRAP_CONTENT
    }

    protected open fun getWidth(): Int {
        return ViewGroup.LayoutParams.MATCH_PARENT
    }

    protected open fun getGravity(): Int { // 处于屏幕的方位, 默认居中下面
        return Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
    }

    protected open fun getAnimateStyle(): Int? { // 显示动画
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadParas()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val window = dialog.window
        if (window != null) {
            window.decorView.setPadding(0, 0, 0, 0)
            val attr = window.attributes
            if (attr != null) {
                attr.width = getWidth()
                attr.height = getHeight()
                attr.gravity = getGravity()//设置dialog 在布局中的位置
                attr.x = getOffsetX()
                attr.y = getOffsetY()
                window.attributes = attr

                val animate = getAnimateStyle()
                if (animate != null) window.setWindowAnimations(animate)
            }
        }
    }

    protected open fun getOffsetX(): Int = 0
    protected open fun getOffsetY(): Int = 0

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(activity, R.style.CommentDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    open fun show(act: AppCompatActivity,
            trans: Int = FragmentTransaction.TRANSIT_ENTER_MASK): BaseDialogFragment {
        val ft = act.supportFragmentManager.beginTransaction()
        ft.setTransition(trans)
        this.show(ft, javaClass.name)
        return this
    }

    open fun show(frg: Fragment,
            trans: Int = FragmentTransaction.TRANSIT_ENTER_MASK): BaseDialogFragment {
        val ft = frg.activity?.supportFragmentManager?.beginTransaction()
        ft?.setTransition(trans)
        this.show(ft, javaClass.name)
        return this
    }

    override fun dismiss() {
        try {
            super.dismiss()
        } catch (ex: Throwable) {

        }
    }
}