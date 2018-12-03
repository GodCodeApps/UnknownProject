package com.unknownproject

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.lib_common.base.BaseActivity
import com.lib_common.base.BaseFragment
import com.lib_common.base.BaseViewModel
import com.lib_common.utils.PreferencesUtils
import com.lib_common.utils.constant.MemoryConstants
import com.lib_common.utils.constant.RouteConstants
import com.lib_common.utils.constant.SharePreferencesConstant
import com.unknownproject.databinding.ActivityMainBinding
import com.zhihu.matisse.internal.utils.qmuihelper.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.activity_main.*
import q.rorbin.badgeview.Badge
import q.rorbin.badgeview.QBadgeView
import java.util.*


class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {
    private val mFragments = ArrayList<Fragment>()
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

    override fun initVariableId(): Int {
        return BR.vm
    }

    override fun initViewModel(): BaseViewModel {
        return BaseViewModel(this)
    }

    override fun initParam() {
        //获取留海高度
        MemoryConstants.NOTCH_HEIGHT = PreferencesUtils.getInt(this, SharePreferencesConstant.NOTCH_HEIGHT, 0)
    }

    override fun initViewObservable() {
        super.initViewObservable()
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {

        navigationView.enableAnimation(false)
        navigationView.enableShiftingMode(false)
        navigationView.enableItemShiftingMode(false)
        //去掉底部菜单点击水波纹动画
//        for (i in 0 until navigationView.getItemCount()) {
//            binding.navigationView.setItemBackground(i, 0)
//        }
        navigationView.setIconsMarginTop(resources.getDimensionPixelSize(R.dimen.sm_px_10))
        val messageFragment = ARouter.getInstance().build(RouteConstants.Message.MESSAGE_MAIN).navigation() as BaseFragment<*, *>
        val communityFragment = ARouter.getInstance().build(RouteConstants.Community.COMMUNITY_MAIN).navigation() as BaseFragment<*, *>
        mFragments.add(messageFragment)
        mFragments.add(communityFragment)
        val mFragmentTransaction = supportFragmentManager.beginTransaction()
        mFragmentTransaction.add(R.id.container, messageFragment)
        mFragmentTransaction.add(R.id.container, communityFragment)

        mFragmentTransaction.hide(communityFragment)
        mFragmentTransaction.show(messageFragment)
        mFragmentTransaction.commitAllowingStateLoss()

        navigationView.currentItem = 0

        QMUIStatusBarHelper.setStatusBarLightMode(this@MainActivity)

        navigationView.setOnNavigationItemSelectedListener { item ->
            //            var colorStateList: ColorStateList? = null
//            if (item.getItemId() === R.id.item_msg && (ServiceFactory.getInstance().getAccountService().getLoginUser() == null || !ServiceFactory.getInstance().getAccountService().isLogin())) {
//                return@binding.navigationView.setOnNavigationItemSelectedListener false
//            }
            when (item.itemId) {
                R.id.item_community -> {
                    changeFragment(communityFragment)
                    QMUIStatusBarHelper.setStatusBarLightMode(this@MainActivity)
                }
                R.id.item_msg -> {
                    changeFragment(messageFragment)
                    QMUIStatusBarHelper.setStatusBarLightMode(this@MainActivity)
                }
            }
            true
        }

//        /**
//         * Menu菜单重复选中处理
//         */
//        binding.navigationView.setOnNavigationItemReselectedListener({ item ->
//            when (item.getItemId()) {
//            //重复点击社区按钮，刷新社区列表
//                R.id.item_community -> RxBus.getDefault().post(BusEvent(CommonEventConstants.CommunityEvent.DYNAMIC_REFRESH))
//                R.id.item_fight -> SelectPlayDialog(this).setAccompanyListener({ v1 -> PageJumpUtils.jumpPageWithString(RouteConstants.PLAY_MAIN, "0", "currentItem") })
//                        .setImmediatePlayListener({ v12 -> PageJumpUtils.jumpPageWithString(RouteConstants.PLAY_MAIN, "1", "currentItem") }).show()
//            }
//        })

//        addBadgeAt(0, 1)
    }

    private fun changeFragment(currentFragment: Fragment) {
        val mFragmentTransaction = supportFragmentManager.beginTransaction()
        for (i in mFragments.indices) {
            val fragment = mFragments[i]
            if (fragment !== currentFragment) {
                mFragmentTransaction.hide(fragment)
            }
        }
        mFragmentTransaction.show(currentFragment)
        mFragmentTransaction.commitAllowingStateLoss()
    }

    private fun addBadgeAt(position: Int, number: Int): Badge {
        val badge1 = QBadgeView(this)
                .setBadgeNumber(number)
                .setBadgeTextColor(Color.RED)
                .setBadgeTextSize(2f, true)
                .setGravityOffset(48f, 6f, true)
                .bindTarget(navigationView.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener { dragState, badge, targetView ->
                    if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState) {
                    }
                }
                .setShowShadow(true)
        return badge1
    }

}
