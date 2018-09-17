package br.com.redcode.easyhawk.sample.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.redcode.easyhawk.sample.R
import br.com.redcode.easyhawk.sample.model.User
import br.com.redcode.easyhawk.sample.storage.SP
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        populate()
        triggers()
    }

    private fun populate() {
        // Populating with data saved with EasyHawk
        editTextDescription.setText(SP.get(SP.DESCRIPTION))
        switchCompat.isChecked = SP.getBoolean(SP.REMEMBER)

        val userSaved = SP.get<User>(SP.USER)
        userSaved?.let {
            editTextName.setText(it.name)
            editTextEmail.setText(it.email)
        }
    }

    private fun triggers() {
        // Saving string DESCRIPTION when user press SAVE button
        buttonSaveDescription.setOnClickListener {
            val description = editTextDescription.editableText.toString().trim()
            SP.save(SP.DESCRIPTION, description)
        }

        // Saving boolean REMEMBER when user change switchcompat status
        switchCompat.setOnCheckedChangeListener { _, isChecked -> SP.save(SP.REMEMBER, isChecked) }

        // Saving object USER when user press SAVE button
        buttonSaveUserModel.setOnClickListener {
            val name = editTextName.editableText.toString().trim()
            val email = editTextEmail.editableText.toString().trim()

            if (name.isBlank()) {
                editTextName.error = "Empty"
            }

            if (email.isBlank()) {
                editTextEmail.error = "Empty"
            }

            val user = User(
                    name = name,
                    email = email
            )

            SP.save(SP.USER, user)
        }
    }
}
