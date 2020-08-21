package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = Date(),
    var isOnline: Boolean = false
) {
    constructor(id: String, firstName: String?, lastName: String?) : this(
        id,
        firstName,
        lastName,
        null
    )

    constructor(id: String) : this(id, "John", "Doe $id")

    init {
        println("Hello $firstName $lastName")
    }

    fun printMe() = println(
        """
        id: $id 
        firstName: $firstName 
        lastName: $lastName 
        avatar: $avatar 
        rating: $rating 
        respect: $respect 
        lastVisit: $lastVisit 
        isOnline: $isOnline             
    """.trimIndent()
    )

    class Builder {
        private lateinit var id: String
        private var firstName: String? = null
        private var lastName: String? = null
        private var avatar: String? = null
        private var rating: Int = 0
        private var respect: Int = 0
        private var lastVisit: Date? = Date()
        private var isOnline: Boolean = false

        fun id(value: String): Builder {
            id = value
            return this
        }

        fun build(): User {
            val user = User(id)
            user.firstName = firstName
            return user
        }

        fun firstName(value: String): Builder {
            firstName = value
            return this
        }

        fun lastName(value: String): Builder {
            lastName = value
            return this
        }

        fun avatar(value: String): Builder {
            avatar = value
            return this
        }

        fun rating(value: Int): Builder {
            rating = value
            return this
        }

        fun respect(value: Int): Builder {
            respect = value
            return this
        }

        fun lastVisit(value: Date): Builder {
            lastVisit = value
            return this
        }

        fun isOnline(value: Boolean): Builder {
            isOnline = value
            return this
        }
    }

    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String?): User {
            lastId++
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }
}

