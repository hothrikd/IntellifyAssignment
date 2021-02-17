package com.example.intellifyassignment

data class User(
        val name:String,
        val city:String,
        val mailaddr:String,
        val imageUrl:String,
        val thumbImage:String,
        val uid:String,
        val deviceToken:String,
){
    constructor():this("","","","","","", "")
    constructor(name: String,city: String,mailaddr: String,imageUrl: String,thumbImage: String,uid: String):this(
            name,
            city,
            mailaddr,
            imageUrl,
            thumbImage,
            uid,
            ""
    )
}
