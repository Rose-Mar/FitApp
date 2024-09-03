package com.example.mag.compose.signComponents

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mag.database.PreferencesManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

private lateinit var auth: FirebaseAuth

@Composable
fun SignInButton(navController: NavController, email: String, password: String, context: Context) {

    val sp = PreferencesManager(context)





    Button(onClick = {

        if (email.isNotEmpty() && password.isNotEmpty()){


            auth = FirebaseAuth.getInstance()


            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(ContentValues.TAG, "signInWithEmail:success")
                        val user = auth.currentUser



//                        sp.loginData(email, password, true)
//
//                        Log.d("SharedPreferences", "Username: ${sp.getData("username", "")}")
//                        Log.d("SharedPreferences", "IsLogged: ${sp.getLogin(false)}")


                    } else {


                        Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)

                        when ((task.exception as FirebaseAuthException).errorCode) {
                            "ERROR_INVALID_EMAIL" -> {

                                Toast.makeText(
                                    context,
                                    "Invalid email address",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            "ERROR_WRONG_PASSWORD" -> {
                                Toast.makeText(
                                    context,
                                    "Incorrect password",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            "ERROR_USER_NOT_FOUND" -> {

                                Toast.makeText(
                                    context,
                                    "User not found",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {
                                Toast.makeText(
                                    context,
                                    "Authentication failed",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                    }
                }
        }else {
            Toast.makeText(
                context,
                "Enter email or password",
                Toast.LENGTH_SHORT,
            ).show()

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



@Composable
fun LoginViaMail(navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Already have an account?",
            modifier = Modifier.padding(end = 8.dp)
        )
        Button(
            onClick = {
                navController.navigate("SignIn")



            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor =Color.Blue
            ),
            contentPadding = PaddingValues(horizontal = 0.dp)
        ) {
            Text(
                text = "Login",
                color = Color.Blue
            )
        }
    }
}

@Composable
fun LoginButton(
    appName: String,
    icon: Painter,
    onClick: () -> Unit
){
    Row (
        modifier = Modifier
            .padding(16.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = icon,
            contentDescription = "Login with $appName",
            modifier = Modifier.size(48.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Log in with $appName",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}