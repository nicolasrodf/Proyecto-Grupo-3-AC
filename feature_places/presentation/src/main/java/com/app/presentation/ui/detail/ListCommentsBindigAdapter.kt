package com.app.presentation.ui.detail

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.domain.Comment

@BindingAdapter("itemsComments")
fun RecyclerView.setItemsComments(comments: List<Comment>?) {
    if (comments != null) {
        (adapter as? CommentsAdapter)?.submitList(comments)
    }
}