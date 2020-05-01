package br.com.redcode.easyhawk.sample.view

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.redcode.easyhawk.sample.R
import br.com.redcode.easyhawk.sample.model.User
import br.com.redcode.easyhawk.sample.storage.SP
import br.com.redcode.easyhawk.sample.storage.SP.DESCRIPTION
import br.com.redcode.easyhawk.sample.storage.SP.REMEMBER
import br.com.redcode.easyhawk.sample.storage.SP.USER
import br.com.redcode.easyhawk.sample.storage.SP.getStatusOperation
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
            save(DESCRIPTION, description)
        }

        // Saving boolean REMEMBER when user change switchcompat status
        switchCompat.setOnCheckedChangeListener { _, isChecked -> save(REMEMBER, isChecked) }

        // Saving object USER when user press SAVE button
        buttonSaveUserModel.setOnClickListener {
            val name = editTextName.editableText.toString().trim()
            val email = editTextEmail.editableText.toString().trim()
            val function: (String, EditText) -> Unit = { text, view ->
                if (text.isBlank()) {
                    view.error = "Empty field"
                }
            }

            function(name, editTextName)
            function(email, editTextEmail)

            val user = User(
                name = name,
                email = email
            )

            save(USER, user)
        }
    }

    private fun save(tag: String, data: Any?) {
        val isOperationSuccessful = SP.save(tag, data)
        val statusOperation = getStatusOperation(isOperationSuccessful)
        val text = "$statusOperation\nKey: $tag\nData: $data"
        textViewResult.text = text
    }

}
