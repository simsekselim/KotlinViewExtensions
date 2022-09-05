package com.simsekselim.viewextensions

import android.animation.Animator
import android.graphics.drawable.InsetDrawable
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide

/**
 * @Author: Selim Simsek
 * @Date: 5.09.2022
 */
typealias DefaultHandler = () -> Unit

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun LottieAnimationView.addListeners(
    onStart: DefaultHandler? = null,
    onEnd: DefaultHandler? = null
) {
    this.addAnimatorListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) {
            onStart?.invoke()
        }

        override fun onAnimationRepeat(animation: Animator?) {}
        override fun onAnimationEnd(animation: Animator?) {
            onEnd?.invoke()
        }

        override fun onAnimationCancel(animation: Animator?) {}
    })
}

fun Animation.addListener(
    onStart: DefaultHandler? = null,
    onEnd: DefaultHandler? = null
) {
    this.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {
            onEnd?.invoke()
        }

        override fun onAnimationStart(animation: Animation?) {
            onStart?.invoke()
        }
    })
}

fun RecyclerView.addMarginDivider() {
    val attrs = intArrayOf(android.R.attr.listDivider)
    val style = context.obtainStyledAttributes(attrs)
    val divider = style.getDrawable(0)
    val inset = resources.getDimensionPixelSize(R.dimen.dimen_normal_100)
    val insetDivider = InsetDrawable(divider, inset, 0, inset, 0)
    style.recycle()

    val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
    itemDecoration.setDrawable(insetDivider)
    this.addItemDecoration(itemDecoration)
}


fun ImageView.glide(imageUrl: String) {
    Glide.with(this.context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_place_holder)
        .error(R.drawable.ic_place_holder)
        .fallback(R.drawable.ic_place_holder)
        .fitCenter()
        .into(this)
}