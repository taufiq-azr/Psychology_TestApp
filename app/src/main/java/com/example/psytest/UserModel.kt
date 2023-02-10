package com.example.psytest
import java.util.*
data class UserModel (
    var id: Int = getAutoId(),
    var email: String = "",
    var nama: String = "",
    var username: String = "",
    var session: String = "",
    var password: String = "",
) {
    companion object {
        fun getAutoId() : Int {
            val random = Random()
            return random.nextInt(100)
        }
    }

}