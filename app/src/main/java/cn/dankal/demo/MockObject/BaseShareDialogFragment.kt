package com.lequ.main.modules.share.base

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.LinearLayout
import cn.dankal.demo.R
import com.lequ.main.base.BaseDialogFragment

/**
 * 基础的分享类, 包含音乐, 视频, 动态
 */
abstract class BaseShareDialogFragment : BaseDialogFragment() {

    override fun getLayoutId(): Int = R.layout.base_share_dialog_fragment_lay

    // 父类定义, 子类实现
    protected lateinit var model: ModelForShare

    companion object {
        const val KEY_MODEL = "model"
    }

    override fun setup() {
        // 标题
        setupTitle()
        // 埋点
        setupGIO()
        // 分享至各个平台
        setupShare()
        // 复制链接
        setupCopy()
        // 添加 action
        addActionItem(layout_action_container)
    }

    private fun setupGIO() {
        GrowingIO.setViewID(btn_share_we_chat, "share_wechat_button_tag")
        GrowingIO.setViewID(btn_share_we_chat_moment, "share_moment_button_tag")
        GrowingIO.setViewID(btn_share_wei_bo, "share_weibo_button_tag")
        GrowingIO.setViewID(btn_copy_url, "share_copy_link_button_tag")
    }

    private fun setupTitle() {
        tv_title.text = model.uiTitle
    }

    private fun setupShare() {
        btn_share_we_chat.setOnClickListener { share(SHARE_MEDIA.WEIXIN) }
        btn_share_we_chat_moment.setOnClickListener { share(SHARE_MEDIA.WEIXIN_CIRCLE) }
        btn_share_qq.setOnClickListener { share(SHARE_MEDIA.QQ) }
        btn_share_qq_zone.setOnClickListener { share(SHARE_MEDIA.QZONE) }
        btn_share_wei_bo.setOnClickListener { share(SHARE_MEDIA.SINA) }
    }

    private fun setupCopy() {
        btn_copy_url.setOnClickListener {
            val cmb = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cmb.primaryClip = ClipData.newPlainText(null, model.shareLink)
            showToast("复制链接成功")
        }
    }

    private fun share(shareType: SHARE_MEDIA) {
        dismiss()
        activity?.run {
            val isInstall = UMShareAPI.get(this).isInstall(this, shareType)
            if (isInstall) {
                var action = ShareAction(this).setPlatform(shareType)//传入平台
                action = if (model.type == ModelForShare.T_MUSIC) action.withMedia(
                        createMedia(shareType) as UMusic)
                else action.withMedia(createMedia(shareType) as UMWeb)
                action.setCallback(shareListener).share()
            } else {
                showToast("${shareType.getString()}客户端没有安装")
            }
        }
    }

    private fun createMedia(type: SHARE_MEDIA): BaseMediaObject {
        return if (model.type == ModelForShare.T_MUSIC) {
            createUMusic(type)
        } else {
            createUMWeb(type)
        }
    }

    private fun createUMusic(type: SHARE_MEDIA): UMusic {
        return UMusic(model.mediaUrl).apply {
            title = model.title
            setThumb(UMImage(context, model.imgUrl))
            description = model.getDescByType(type)
            setmTargetUrl(model.getShareLinkByType(type))
        }
    }

    private fun createUMWeb(type: SHARE_MEDIA): UMWeb {
        return UMWeb(model.getShareLinkByType(type)).apply {
            setThumb(UMImage(context, model.imgUrl))
            title = model.getDescByType(type)
            description = model.title
        }
    }

    private val shareListener = object : UMShareListener {
        override fun onResult(p0: SHARE_MEDIA?) {
            dismiss()
            showToast("分享成功")
        }

        override fun onCancel(p0: SHARE_MEDIA?) {
            dismiss()
        }

        override fun onError(p0: SHARE_MEDIA?, p1: Throwable?) {
            dismiss()
        }

        override fun onStart(p0: SHARE_MEDIA?) {
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.run {
            UMShareAPI.get(this).release()
        }
    }

    /**
     * 子类根据自己的需求, 添加第二层 action
     */
    protected open fun addActionItem(container: LinearLayout) {}
}