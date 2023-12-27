package com.riofuad.suitmediakmtest.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riofuad.suitmediakmtest.data.response.DataItem
import com.riofuad.suitmediakmtest.databinding.UserCardBinding
import com.riofuad.suitmediakmtest.ui.activities.SecondScreenActivity


class UserAdapter : PagingDataAdapter<DataItem, UserAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    class ViewHolder(private val binding: UserCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .into(ivUserPhoto)
                tvUserName.text = user.firstName.plus(" ").plus(user.lastName)
                tvUserEmail.text = user.email
                itemView.setOnClickListener {
                    val intent = Intent()
                    intent.putExtra(SecondScreenActivity.CHOOSE_USER_NAME, tvUserName.text)
                    val activity = itemView.context as? Activity
                    activity?.setResult(Activity.RESULT_OK, intent)
                    activity?.finish()
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}