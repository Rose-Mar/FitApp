package com.example.mag.compose.signComponents

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mag.database.PreferencesManager
import com.google.firebase.auth.FirebaseAuth


private lateinit var auth: FirebaseAuth


@Composable
fun SignUpButton(navController: NavController, email: String, password: String, context: Context) {
    Button(onClick = {

        val sp = PreferencesManager(context)


        auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    val user = auth.currentUser

                    sp.loginData(email, password, true)

                    Log.d("SharedPreferences", "Username: ${sp.getData("username", "")}")
                    Log.d("SharedPreferences", "IsLogged: ${sp.getLogin(false)}")



                    navController.navigate("ExtraQuestions")
                } else {

                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        context,
                        "Authentication failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White
        )
    ) {


        Text(text = "Sign up")

    }
}