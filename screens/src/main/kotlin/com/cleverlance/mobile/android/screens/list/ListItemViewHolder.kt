package com.cleverlance.mobile.android.screens.list

 import android.view.View
 import androidx.recyclerview.widget.RecyclerView
 import io.reactivex.disposables.Disposables

class ListItemViewHolder<out V : ListItemView<P>, P : Any>(
        view: View,
        val listItem: V
) : RecyclerView.ViewHolder(view) {
    private var bindings = Disposables.disposed()

    internal fun bind() {
        bindings = listItem.bindPresenter(listItem.presenter)
    }

    internal fun unbind() = bindings.dispose()
}
