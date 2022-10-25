package com.example.yolharakatiqoidalari.models

class User {

    var id:Int = 0
    var image: String = ""
    var name : String = ""
    var about : String = ""
    var type : String = ""
    var like : Int = 0

    constructor(id: Int, image: String, name: String, about: String, type: String, like : Int) {
        this.id = id
        this.image = image
        this.name = name
        this.about = about
        this.type = type
        this.like = like
    }

    constructor(image: String, name: String, about: String, type: String, like: Int) {
        this.image = image
        this.name = name
        this.about = about
        this.type = type
        this.like = like
    }

    constructor()


}