package com.example.submission1_github_user_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private lateinit var rvUser:RecyclerView
    private val list = ArrayList<UserGithub>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUser = findViewById(R.id.rv_github_user)
        rvUser.setHasFixedSize(true)

        list.addAll(listUserGithub)
        showRecyclerList()
    }

    private val listUserGithub: ArrayList<UserGithub>
    get() {
        val listUser: ArrayList<UserGithub> = ArrayList()
        val obj = JSONObject(getJSONFromAsset()!!)
        val userArray = obj.getJSONArray("users")
        for (i in 0 until userArray.length()) {
            val user = userArray.getJSONObject(i)

            val username = user.getString("username")
            val name = user.getString("name")
            val location = user.getString("location")
            val company = user.getString("company")
            val avatar = user.getString("avatar")
            val follower = user.getInt("follower")
            val following = user.getInt("following")
            val repository = user.getInt("repository")

            val userDetail = UserGithub(avatar,name,username,company,location,repository,follower, following)
            listUser.add(userDetail)
        }
        return listUser
    }

    private fun showRecyclerList(){
        rvUser.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(list)
        rvUser.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UserGithub) {
                val intentToDetail = Intent(this@MainActivity,UserDetailActivity::class.java)
                intentToDetail.putExtra(UserDetailActivity.EXTRA_USER,data)
                startActivity(intentToDetail)
            }
        })
    }

    private fun getJSONFromAsset(): String?{
        var json: String? = null
        var charset: Charset = Charsets.UTF_8

        try {
            val myUserJsonFile = assets.open("githubuser.json")
            val size = myUserJsonFile.available()
            val buffer = ByteArray(size)
            myUserJsonFile.read(buffer)
            myUserJsonFile.close()
            json = String(buffer, charset)
        }catch (ex:IOException){
            ex.printStackTrace()
            return null
        }
        return json
    }

}