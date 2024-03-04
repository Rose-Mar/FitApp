package com.example.mag.startingScreens.sign_in

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.mag.R
import com.example.mag.compose.NormalTextComponent
import com.example.mag.compose.PasswordTextInput
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


private lateinit var auth: FirebaseAuth
@Composable
fun SignUpEmailPassword(){
    
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        NormalTextComponent(value = stringResource(id = R.string.start_screen_text))

//        PasswordTextInput(value = stringResource(id = R.string.enter_password))

    }

    auth = Firebase.auth

//    auth.createUserWithEmailAndPassword(email, password)
//        .addOnCompleteListener(this) { task ->
//            if (task.isSuccessful) {
//                // Sign in success, update UI with the signed-in user's information
//                Log.d(TAG, "createUserWithEmail:success")
//                val user = auth.currentUser
//                updateUI(user)
//            } else {
//                // If sign in fails, display a message to the user.
//                Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                Toast.makeText(
//                    baseContext,
//                    "Authentication failed.",
//                    Toast.LENGTH_SHORT,
//                ).show()
//                updateUI(null)
//            }
//        }

}