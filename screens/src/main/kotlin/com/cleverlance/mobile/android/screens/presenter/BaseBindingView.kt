package com.cleverlance.mobile.android.screens.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

abstract class BaseBindingView<BINDING : ViewBinding> : BasePresenterView() {

    private var internalBinding: ViewBinding? = null

    abstract fun initBindings(inflater: LayoutInflater): BINDING
    abstract fun presenterBindings(): Disposable

    @Suppress("UNCHECKED_CAST")
    protected val binding: BINDING
        get() = internalBinding()

    override fun createView(container: ViewGroup): View {
        internalBinding = initBindings(LayoutInflater.from(container.context))
        return binding.root
    }

    @Deprecated(
        message = "override presenter bindings() function",
        level = DeprecationLevel.ERROR
    )
    override fun bindPresenter(): Disposable = CompositeDisposable(
        presenterBindings(),
        Disposables.fromAction { internalBinding = null }
    )

    private fun internalBinding(): BINDING {
        @Suppress("UNCHECKED_CAST")
        return when {
            internalBinding != null -> internalBinding as BINDING
            else -> throw IllegalStateException("Do not use binding before initBindings was called!")
        }
    }
}
