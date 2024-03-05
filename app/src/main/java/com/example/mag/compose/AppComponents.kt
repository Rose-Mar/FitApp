package com.example.mag.compose

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

private lateinit var auth: FirebaseAuth

@Composable
fun NormalTextComponent(value:String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        )
    )
}



@Composable
fun TitleTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        style = TextStyle(
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    )
}



@Composable
fun PasswordTextInput(value: String, onValueChanged: (String) -> Unit){
    var text by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = text,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        label = { Text(text = value) },
        placeholder = { Text(text = "") },
        visualTransformation = if (passwordVisible) VisualTransformation.None
        else PasswordVisualTransformation(),
        onValueChange = {
            text = it
            onValueChanged(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible)
                painterResource(com.firebase.ui.auth.R.drawable.design_ic_visibility)
            else
                painterResource(com.firebase.ui.auth.R.drawable.design_ic_visibility_off)


            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(painter = image, contentDescription = description)
            }
        }

    )
}


@Composable
fun NormalTextInput(value: String,onValueChanged: (String) -> Unit, keyboardOptions: KeyboardOptions){
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        label = { Text(text = value) },
        placeholder = { Text(text = "") },
//        visualTransformation = PasswordVisualTransformation(),
        onValueChange = {
            text = it
            onValueChanged(it)
        },
        keyboardOptions = keyboardOptions,

    )

}
@Preview
@Composable
fun PreviewBasicInput() {
    Column {
//        NormalTextInput("Email","tej" ,KeyboardOptions(keyboardType = KeyboardType.Email))
    }
}

@Composable
fun TextInputs() {
    Column {

        Text(text = "Text Inputs",
//            style = typography.h6,
            modifier = Modifier.padding(8.dp))
        var text by remember { mutableStateOf(TextFieldValue("")) }
        // for preview add same text to all the fields

        // Normal Text Input field with floating label
        // placeholder is same as hint in xml of edit text
        TextField(
            value = text,
            onValueChange = { newValue -> text = newValue },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = { Text("label") },
            placeholder = { Text("placeholder") },
        )

        // Outlined Text Input Field


        // Outlined Input text with icon on the left
        // inside leadingIcon property add the icon
        OutlinedTextField(
            value = text,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Email address") },
            placeholder = { Text(text = "Your email") },
            onValueChange = {
                text = it
            }
        )

        // Outlined Input text with icon on the left and right
        // inside leadingIcon property add the left icon
        // inside trailingIcon property add right icon
        OutlinedTextField(
            value = text,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            trailingIcon = { Icon(imageVector = Icons.Default.Edit, contentDescription = null) },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Email address") },
            placeholder = { Text(text = "Your email") },
            onValueChange = {
                text = it
            }
        )

        var numberText by remember { mutableStateOf(TextFieldValue("")) }
        // Outlined Text input field with input type number
        // It will open the number keyboard
        OutlinedTextField(value = numberText,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(text = "Phone number") },
            placeholder = { Text(text = "88888888") },
            onValueChange = {
                numberText = it
            }
        )
    }
}

@Composable
fun SignUpButton(navController: NavController, email: String, password: String) {
    Button(onClick = {

        auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    val user = auth.currentUser


                    navController.navigate("ExtraQuestions")
                } else {

                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(
//                        applicationContext,
//                        "Authentication failed: ${task.exception?.message}",
//                        Toast.LENGTH_SHORT
//                    ).show()
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


@Composable
fun BasicNavigationButton(navController: NavController, value: String, route: String){
    Button(onClick = {

        navController.navigate(route)

    },
    modifier = Modifier
    .fillMaxWidth()
    .padding(horizontal = 16.dp),
    colors = ButtonDefaults.buttonColors(
    contentColor = Color.White
    )
    ) {


        Text(text = value)

    }
}


