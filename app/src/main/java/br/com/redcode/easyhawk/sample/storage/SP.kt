package br.com.redcode.easyhawk.sample.storage

import br.com.redcode.easyhawk.library.EasyHawk
import br.com.redcode.easyhawk.sample.model.User
import br.com.redcode.easyhawk.sample.extensions.toLogcat

/**
 * Created by pedrofsn on 17/10/2017.
 */
object SP : EasyHawk() {

    const val REMEMBER: String = "REMEMBER"
    const val DESCRIPTION: String = "DESCRIPTION"
    const val USER: String = "USER"

    override fun clear() = delete(USER, DESCRIPTION, REMEMBER)

    override fun delete(key: String) {
        super.delete(key)
        "Key '$key' deleted".toLogcat()
    }

    override fun save(key: String, value: Any?) {
        if (key.isNotBlank()) {
            when (value) {
                is User -> persist(key, value)
                else -> super.save(key, value)
            }
        }
    }

    override fun persist(key: String, value: Any?) {
        super.persist(key, value)
        "Key '$key' saved with '$value'".toLogcat()
    }
}