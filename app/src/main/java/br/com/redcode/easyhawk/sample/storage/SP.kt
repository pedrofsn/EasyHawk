package br.com.redcode.easyhawk.sample.storage

import br.com.redcode.easyhawk.library.EasyHawk
import br.com.redcode.easyhawk.sample.extensions.toLogcat
import br.com.redcode.easyhawk.sample.model.User

/**
 * Created by pedrofsn on 17/10/2017.
 */
object SP : EasyHawk() {

    const val REMEMBER: String = "REMEMBER"
    const val DESCRIPTION: String = "DESCRIPTION"
    const val USER: String = "USER"

    override fun clear() = delete(USER, DESCRIPTION, REMEMBER)

    override fun delete(key: String): Boolean {
        val deleted = super.delete(key)
        "${getStatusOperation(deleted)} | Key '$key' deleted".toLogcat()
        return deleted
    }

    override fun save(key: String, value: Any?): Boolean {
        if (key.isNotBlank()) {
            return when (value) {
                is User -> persist(key, value)
                else -> super.save(key, value)
            }
        }

        return false
    }

    override fun persist(key: String, value: Any?): Boolean {
        val saved = super.persist(key, value)
        "${getStatusOperation(saved)} | Key '$key' saved with '$value'".toLogcat()
        return saved
    }

    fun getStatusOperation(success: Boolean): String {
        return "[Operation is successful: ${if (success) "Yes]" else "Noooooo!]"}"
    }
}