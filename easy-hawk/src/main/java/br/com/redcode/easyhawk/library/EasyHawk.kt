package br.com.redcode.easyhawk.library

import com.orhanobut.hawk.Hawk

abstract class EasyHawk {

    abstract fun clear()
    fun get(key: String): String? = Hawk.get<String>(key, null)
    fun <T> get(key: String): T? = Hawk.get<T>(key, null)
    fun getBoolean(key: String, default: Boolean = false) = Hawk.get(key, default)

    open fun delete(key: String): Boolean {
        return Hawk.delete(key)
    }

    fun delete(vararg keys: String) {
        for (key in keys) {
            delete(key)
        }
    }

    open fun save(key: String, value: Any?): Boolean {
        if (key.isNotBlank()) {
            return if (value == null) {
                persist(key, value)
            } else {
                when (value) {
                    is String -> persist(key, value)
                    is Long -> persist(key, value)
                    is Boolean -> persist(key, value)
                    is Int -> persist(key, value)
                    else -> throw RuntimeException("This object isn't mapped by EasyHawk, override save and call persist. After you can try save key '$key' and value '$value'")
                }
            }
        }

        return false
    }

    fun has(vararg keys: String): Boolean {
        for (key in keys) {
            if (!has(key)) {
                return false
            }
        }

        return true
    }

    private fun has(key: String) = Hawk.contains(key) && get(key) != null

    open fun persist(key: String, value: Any?): Boolean {
        return Hawk.put(key, value)
    }

}