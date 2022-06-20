package com.example.submission1_github_user_page

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListUserAdapter(private val listUsers: ArrayList<UserGithub>): RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagePhoto: ImageView = itemView.findViewById(R.id.img_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_user)
        var tvUsername: TextView = itemView.findViewById(R.id.tv_username)
        var tvCountFollower: TextView = itemView.findViewById(R.id.tv_num_of_follower)
        var tvCountFollowing: TextView = itemView.findViewById(R.id.tv_num_following)
        var tvCountRepo: TextView = itemView.findViewById(R.id.tv_num_repo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_user,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val listUser = listUsers.get(position)

        holder.tvName.text = listUser.name
        holder.tvUsername.text = "@"+listUser.username
        holder.tvCountFollower.text = listUser.follower.toString()
        holder.tvCountFollowing.text = listUser.following.toString()
        holder.tvCountRepo.text = listUser.repository.toString()
        val uri = listUser.avatar

        val imageResource: Int = holder.itemView.context.resources.getIdentifier(uri,null,holder.itemView.context.packageName)
        val res:Drawable = holder.itemView.context.resources.getDrawable(imageResource)
        Glide.with(holder.itemView.context)
            .load(res)
            .circleCrop()
            .into(holder.imagePhoto)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser) }
    }

    override fun getItemCount(): Int = listUsers.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data:UserGithub)
    }
}