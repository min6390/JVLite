//package com.example.jvlite
//
//import android.content.Intent
//import android.os.Bundle
//import android.support.R
//import android.support.core.event.NetworkChangeEvent
//import android.support.core.extensions.dispatchHidden
//import android.support.core.extensions.isVisibleOnScreen
//import android.support.core.functional.Backable
//import android.support.core.functional.Dispatcher
//import android.support.core.functional.Navigable
//import android.support.core.lifecycle.LifeRegister
//import android.support.core.lifecycle.ResultLifecycle
//import android.support.core.lifecycle.ResultRegistry
//import android.support.core.lifecycle.ViewLifecycleOwner
//import androidx.fragment.app.Fragment
//import android.util.Log
//import android.view.View
//import android.view.animation.Animation
//import android.view.animation.AnimationUtils
//import org.greenrobot.eventbus.EventBus
//import org.greenrobot.eventbus.Subscribe
//import org.greenrobot.eventbus.ThreadMode
//
///**
// * Custom for lifecycle
// */
//abstract class BaseFragment : Fragment(), Backable, Dispatcher, Navigable {
//    private val TAG: String = this.javaClass.simpleName
//
//    companion object {
//        private const val STATE_INVISIBLE = 1
//        private const val STATE_VISIBLE = 0
//        private const val STATE_NONE = -1
//    }
//
//    /**
//     * use or not EventBus
//     */
//    open fun useEventBus(): Boolean = false
//
//    /**
//     * check animation
//     */
//    var isInLeft: Boolean = false
//    var isOutLeft: Boolean = false
//    var isCurrentScreen: Boolean = false //@FragmentHelper
//
//    val resultLife: ResultLifecycle = ResultRegistry()
//    val viewLife = ViewLifecycleOwner()
//    val lifeRegister by lazy { LifeRegister.of(viewLife) }
//
//    private val mLifeRegistry get() = viewLife.lifecycle
//    private var mVisibleState = STATE_NONE
//
//    /**
//     * No network status -> automatic reconnection operation with network status, subclass can override this method
//     */
//    open fun doReConnected() {
//
//    }
//
//    /**
//     * Network Change
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onNetworkChangeEvent(event: NetworkChangeEvent) {
//        if(event.isConnected) {
//            doReConnected()
//        }
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        mLifeRegistry.create()
//        if (useEventBus()) {
//            EventBus.getDefault().register(this)
//        }
//
//        initView()
//        loadData()
//        observeData()
//        setListener()
//        Log.i(TAG, "Created")
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        mLifeRegistry.destroy()
//        Log.i(TAG, "Destroy")
//    }
//
//    override fun onStart() {
//        super.onStart()
//        if (isVisibleOnScreen()) {
//            performStartFragment()
//            mLifeRegistry.start()
//            Log.i(TAG, "Start")
//        }
//    }
//
//    override fun onStop() {
//        super.onStop()
//        if (isVisibleOnScreen()) {
//            performStopFragment()
//            mLifeRegistry.stop()
//            Log.i(TAG, "Stop")
//        }
//    }
//
//    final override fun onResume() {
//        super.onResume()
//        if (isVisibleOnScreen()) {
//            onFragmentResumed()
//            mLifeRegistry.resume()
//            Log.i(TAG, "Resume")
//        }
//    }
//
//    final override fun onPause() {
//        super.onPause()
//        if (isVisibleOnScreen()) {
//            onFragmentPaused()
//            mLifeRegistry.pause()
//            Log.i(TAG, "Pause")
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        if (useEventBus()) {
//            EventBus.getDefault().unregister(this)
//        }
//    }
//
//    final override fun onHiddenChanged(hidden: Boolean) {
//        if (mVisibleState == (if (hidden) STATE_INVISIBLE else STATE_VISIBLE)) return
//
//        if (hidden) {
//            onFragmentPaused()
//            mLifeRegistry.pause()
//            performStopFragment()
//            mLifeRegistry.stop()
//            Log.i(TAG, "Hide")
//        } else {
//            performStartFragment()
//            mLifeRegistry.start()
//            onFragmentResumed()
//            mLifeRegistry.resume()
//            Log.i(TAG, "Show")
//        }
//        dispatchHidden(hidden)
//    }
//
//    private fun performStopFragment() {
//        onFragmentStopped()
//        mVisibleState = STATE_INVISIBLE
//    }
//
//    private fun performStartFragment() {
//        onFragmentStarted()
//        mVisibleState = STATE_VISIBLE
//        arguments?.apply { handleNavigateArguments(this) }
//    }
//
//    protected open fun onFragmentStarted() {
//    }
//
//    protected open fun onFragmentStopped() {
//    }
//
//    protected open fun onFragmentResumed() {
//    }
//
//    protected open fun onFragmentPaused() {
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        (resultLife as ResultRegistry).handleActivityResult(requestCode, resultCode, data)
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        (resultLife as ResultRegistry).handlePermissionsResult(requestCode, permissions, grantResults)
//    }
//
//    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
//        val animation: Animation?
//        animation = if (enter) {
//            val left = getLeftInAnimId()
//            val right = getRightInAnimId()
//            AnimationUtils.loadAnimation(activity, if (isInLeft) left else right)
//        } else {
//            val left = getLeftOutAnimId()
//            val right = getRightOutAnimId()
//            AnimationUtils.loadAnimation(activity, if (isOutLeft) left else right)
//        }
//
//        animation?.setAnimationListener(object : Animation.AnimationListener {
//            override fun onAnimationStart(animation: Animation) {}
//
//            override fun onAnimationEnd(animation: Animation) {
//
//            }
//
//            override fun onAnimationRepeat(animation: Animation) {}
//        })
//        return animation
//    }
//
//    /**
//     * animation
//     */
//    private fun getLeftInAnimId(): Int {
//        return R.anim.slide_in_left
//    }
//
//    private fun getRightInAnimId(): Int {
//        return R.anim.slide_in_right
//    }
//
//    private fun getLeftOutAnimId(): Int {
//        return R.anim.slide_out_left
//    }
//
//    private fun getRightOutAnimId(): Int {
//        return R.anim.slide_out_right
//    }
//
//    /**
//     * goto @baseFragment
//     */
//    fun pushFragment(baseFragment: BaseFragment) {
//        (activity as BaseActivity).pushFragment(baseFragment)
//    }
//
//    /**
//     * remove fragment in back stack
//     */
//    fun popFragment() {
//        (activity as BaseActivity).popFragment()
//    }
//
//    /**
//     * goto @baseFragment fragment in back stack (it will remove back fragment in back stack)
//     * ex: backstack[0,1,2,3] -> popToFragment(1) --> backstack[0,1] (fragment 2,3 will be removed)
//     */
//    fun popToFragment(baseFragment: BaseFragment) {
//        (activity as BaseActivity).popToFragment(baseFragment)
//    }
//
//    /**
//     * remove all fragment in back stack (except main fragments)
//     */
//    fun popToRootFragment() {
//        (activity as BaseActivity).popToRootFragment()
//    }
//
//    open fun initView() {}
//    open fun loadData() {}
//    open fun setListener() {}
//    open fun observeData() {}
//}