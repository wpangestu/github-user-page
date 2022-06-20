package com.example.submission1_github_user_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class UserDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        var imgPhoto:ImageView = findViewById(R.id.imgPhoto)
        var tvName:TextView = findViewById(R.id.tv_name)
        var tvUsername:TextView = findViewById(R.id.tv_username_detail)
        var tvFollower:TextView = findViewById(R.id.tv_count_follower_detail)
        var tvFollowing:TextView = findViewById(R.id.tv_count_following_detail)
        var tvRepo:TextView = findViewById(R.id.tv_count_repo_detail)
        var tvLocation:TextView = findViewById(R.id.tv_location)
        var tvCompany:TextView = findViewById(R.id.tv_company_detail)

        val user = intent.getParcelableExtra<UserGithub>(EXTRA_USER)
        tvName.text = user?.name.toString()
        tvUsername.text = "@"+user?.username.toString()
        tvFollower.text = user?.follower.toString()
        tvFollowing.text= user?.following.toString()
        tvRepo.text = user?.repository.toString()
        tvLocation.text = user?.location.toString()
        tvCompany.text = user?.company.toString()

        val url = user?.avatar
        val imgResource:Int = applicationContext.resources.getIdentifier(url,null,applicationContext.packageName)
        Glide.with(applicationContext)
            .load(imgResource).circleCrop().into(imgPhoto)
    }
}