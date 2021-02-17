package com.example.intellifyassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_display_user.*
import kotlinx.android.synthetic.main.list_item.view.*

const val UID = "uid"
const val NAME = "name"
const val IMAGE = "photo"
const val CITY = "city"
const val EMAIL = "mailaddr"
class DisplayUserActivity : AppCompatActivity() {
    private val mCurrentUid: String? by lazy {
        intent.getStringExtra(UID)
    }
    private val name:String? by lazy {
        intent.getStringExtra(NAME)
    }
    private val image:String? by lazy {
        intent.getStringExtra(IMAGE)
    }
    private val city:String? by lazy {
        intent.getStringExtra(CITY)
    }
    private val mailaddr:String? by lazy {
        intent.getStringExtra(EMAIL)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_user)
        nameTv.text = name.toString()
        cityTv.text = city.toString()
        mailTv.text = mailaddr.toString()
        Picasso.get().load(image).placeholder(R.drawable.defaultavatar).error(R.drawable.defaultavatar).into(userImgView)
    }
}