package com.example.mag.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.mapbox.common.OnValueChanged

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
        visualTransformation = PasswordVisualTransformation(),
        onValueChange = {
            text = it
            onValueChanged(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Done
            else Icons.Filled.Close

            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
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

@Preview
@Composable
fun PreviewInputs() {
    Column {
        TextInputs()
    }
}