package com.company.appfood.view.manage.plat

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.company.appfood.component.PageIndicator
import com.company.appfood.component.TopAppBarWithBack
import com.company.appfood.di.DBHandler
import com.company.appfood.navigation.Screen
import com.company.appfood.ui.theme.*
import com.company.appfood.view.manage.menu.MenuEditImagesSlider
import com.company.appfood.view.manage.menu.UpdateMenu
import com.company.appfood.view.manage.menu.ViewEditMenu
import com.company.appfood.view.manage.menu.navControllerEditMenu


@SuppressLint("StaticFieldLeak")
lateinit var navControllerEditPlat: NavController

@Composable
fun EditPlatScreen(navController: NavController) {
    navControllerEditPlat = navController
    UpdatePlat()
}



class UpdatePlat : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppFoodTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val pageCount by remember { mutableStateOf(2) }

                    Scaffold(
                        topBar = {
                            TopAppBarWithBack(onBackClick = { navControllerEditPlat.popBackStack()})
                        },
                        backgroundColor = primary,
                        content = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                ConstraintLayout {
                                    val (imagesliderref, addtocartref) = createRefs()

                                    Box(modifier = Modifier
                                        .height(280.dp)
                                        .constrainAs(imagesliderref) {
                                            top.linkTo(imagesliderref.top)
                                            bottom.linkTo(imagesliderref.top)
                                            start.linkTo(parent.start)
                                            end.linkTo(parent.end)
                                        }) {
//                        defined slider image
                                        MenuEditImagesSlider(pageCount)

                                    } //End of Box

                                    Surface(
                                        color = bgwhitelight,
                                        shape = RoundedCornerShape(40.dp).copy(
                                            bottomStart = ZeroCornerSize,
                                            bottomEnd = ZeroCornerSize
                                        ), modifier = Modifier
                                            .fillMaxSize()
                                            .padding(top = 70.dp)
                                            .constrainAs(addtocartref) {
                                                bottom.linkTo(parent.bottom)
                                                start.linkTo(parent.start)
                                                end.linkTo(parent.end)
                                            }
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(20.dp)
                                        ) {

                                            ViewEditPlat(
                                                LocalContext.current,
                                                intent.getStringExtra("label"),
                                                intent.getStringExtra("type_menu"),
                                                intent.getStringExtra("prix"),
                                                intent.getStringExtra("image"),
                                                intent.getStringExtra("description")
                                            )

                                        } //End of Column
                                    } //End of Surface
                                }
                            }
                        }
                    )

                }
            }
        }
    }
}


@Composable
fun ViewEditPlat (context: Context,
                  cName: String?,
                  cCategory: String?,
                  cPrice: String?,
                  cImage: String?,
                  cDescription: String?
) {


    val activity = context as Activity
    // on below line creating a variable for battery status
    var courseName = remember {
        mutableStateOf(cName)
    }
    val courseCategory= remember {
        mutableStateOf(cCategory)
    }
    val coursePrice= remember {
        mutableStateOf(cPrice)
    }
    val courseImage= remember {
        mutableStateOf(cImage)
    }
    val courseDescription = remember {
        mutableStateOf(cDescription)
    }
//    call instance of DataBase
    val dbHandler: DBHandler = DBHandler(context)
//    Form begin

    Column(
        // on below line we are adding a modifier to it,
        modifier = Modifier
            .fillMaxSize()
            // on below line we are adding a padding.
            .padding(all = 5.dp),

//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
    ) {

        Text(

            text = "Nom du plat",
            style  = TextStyle(
                textAlign = TextAlign.Left,
            ),
//            style = MaterialTheme.typography.subtitle1,
            color = dark_gray,
            modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
        )

        TextField(
            value = courseName.value!!,
            onValueChange = { courseName.value = it },
            leadingIcon = {
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = primary
                        )
                        Canvas(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(start = 10.dp)
                        ) {
                            drawLine(
                                color = light_gray,
                                start = Offset(0f, 0f),
                                end = Offset(0f, size.height),
                                strokeWidth = 2.0F
                            )
                        }
                    }
                )
            },
            placeholder = { Text(text = "Entrer le nom du plat") },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = white,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Nom plat") },
            shape = RoundedCornerShape(8.dp),

            )
        //End of TextField Name

        Text(
            text = "Categorie du menu",
//            style = MaterialTheme.typography.subtitle1,
            style  = TextStyle(
                textAlign = TextAlign.Left,
            ),
            color = dark_gray,
            modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
        )
        TextField(
            value = courseCategory.value!!,
            onValueChange = { courseCategory.value = it },
            leadingIcon = {
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = primary
                        )
                        Canvas(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(start = 10.dp)
                        ) {
                            drawLine(
                                color = light_gray,
                                start = Offset(0f, 0f),
                                end = Offset(0f, size.height),
                                strokeWidth = 2.0F
                            )
                        }
                    }
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = white,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Type de menu") },
            shape = RoundedCornerShape(8.dp),

            ) //End of TextField categorie
        Text(

            text = "Description du menu",
//            style = MaterialTheme.typography.subtitle1,
            style  = TextStyle(
                textAlign = TextAlign.Left,
            ),
            color = dark_gray,
            modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
        )
        TextField(
            value = courseCategory.value!!,
            onValueChange = { courseCategory.value = it },
            leadingIcon = {
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = primary
                        )
                        Canvas(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(start = 10.dp)
                        ) {
                            drawLine(
                                color = light_gray,
                                start = Offset(0f, 0f),
                                end = Offset(0f, size.height),
                                strokeWidth = 2.0F
                            )
                        }
                    }
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = white,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Description du menu") },
            shape = RoundedCornerShape(8.dp),

            ) //End of TextField categorie
        Text(

            text = "Prix du menu",
            style  = TextStyle(
                textAlign = TextAlign.Left,

                ),
//            style = MaterialTheme.typography.subtitle1,
            color = dark_gray,
            modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
        )
        TextField(
            value = courseDescription.value!!,
            onValueChange = { courseDescription.value = it },
            leadingIcon = {
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = primary
                        )
                        Canvas(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(start = 10.dp)
                        ) {
                            drawLine(
                                color = light_gray,
                                start = Offset(0f, 0f),
                                end = Offset(0f, size.height),
                                strokeWidth = 2.0F
                            )
                        }
                    }
                )
            },

            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = white,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Prix") },
            shape = RoundedCornerShape(8.dp),

            ) //End of TextField categorie

        Spacer(modifier = Modifier.padding(10.dp))

        TextField(
            value = courseImage.value!!,
            onValueChange = { courseImage.value = it },
            leadingIcon = {
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = primary
                        )
                        Canvas(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(start = 10.dp)
                        ) {
                            drawLine(
                                color = light_gray,
                                start = Offset(0f, 0f),
                                end = Offset(0f, size.height),
                                strokeWidth = 2.0F
                            )
                        }
                    }
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = white,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Image") },
            shape = RoundedCornerShape(8.dp),
        )

// End of TextField
        Button(
            onClick = {
                dbHandler.updatePlat(
                    cName!!,
                    courseName.value,
                    courseCategory.value,
                    coursePrice.value,
                    courseDescription.value,
                    courseImage.value
                )
                navControllerEditPlat.navigate(Screen.ListPlatDashScreen.route)
                Toast.makeText(context, "Mise a jour éffectue avec succès", Toast.LENGTH_SHORT).show()
            },

            colors = ButtonDefaults.buttonColors(backgroundColor = secondary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 34.dp),

            ) {
            Text(
                text = "Modifier",
                color = white,
                style = MaterialTheme.typography.button,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )

        } //End of login Button
    }

}


@Composable
fun updatePlatToDatabase(
    context: Context,
    cName: String?,
    cTracks: String?,
    cPrice: String?,
    cDuration: String?,
    cDescription: String?
) {

    val activity = context as Activity

    var courseName = remember {
        mutableStateOf(cName)
    }
    val courseDuration = remember {
        mutableStateOf(cDuration)
    }
    val courseTracks = remember {
        mutableStateOf(cTracks)
    }

    val coursePrice = remember {
        mutableStateOf(cPrice)
    }
    val courseDescription = remember {
        mutableStateOf(cDescription)
    }

    // on below line we are creating a column,
    Column(
        // on below line we are adding a modifier to it,
        modifier = Modifier.fillMaxSize()
            // on below line we are adding a padding.
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        var dbHandler: DBHandler = DBHandler(context)
        // on below line we are adding a text for heading.
        Text(
            // on below line we are specifying text
            text = "Formulaire de modification",
            // on below line we are specifying text color, font size and font weight
            color = primary, fontSize = 20.sp, fontWeight = FontWeight.Bold
        )
        // on below line adding a spacer.
        Spacer(modifier = Modifier.height(20.dp))

        // on below line we are creating a text field.
        TextField(

            value = courseName.value!!,

            onValueChange = { courseName.value = it },
            placeholder = { Text(text = "Entrer le nom plat") },

            modifier = Modifier.fillMaxWidth(),

            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

            singleLine = true,
        )
        // on below line we are adding spacer
        Spacer(modifier = Modifier.height(20.dp))

        // on below line we are creating a text field.
        TextField(
            // on below line we are specifying value for our email text field.
            value = courseDuration.value!!,
            // on below line we are adding on value change for text field.
            onValueChange = { courseDuration.value = it },
            // on below line we are adding place holder as text as "Enter your email"
            placeholder = { Text(text = "votre type de menu") },
            // on below line we are adding modifier to it
            // and adding padding to it and filling max width
            modifier = Modifier.fillMaxWidth(),
            // on below line we are adding text style
            // specifying color and font size to it.
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            // on below line we are adding single line to it.
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(

            value = coursePrice.value!!,

            onValueChange = { coursePrice.value = it },

            placeholder = { Text(text = "Entrer le Prix du plat") },

            modifier = Modifier.fillMaxWidth(),

            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

            singleLine = true,
        )

        Spacer(modifier = Modifier.height(15.dp))

        // on below line we are creating a text field.
        TextField(
            // on below line we are specifying value for our email text field.
            value = courseTracks.value!!,
            // on below line we are adding on value change for text field.
            onValueChange = { courseTracks.value = it },

            placeholder = { Text(text = "Entrer une image") },

            modifier = Modifier.fillMaxWidth(),

            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

            singleLine = true,
        )
        // on below line we are adding spacer
        Spacer(modifier = Modifier.height(20.dp))

        // on below line we are creating a text field.
        TextField(
            // on below line we are specifying value for our email text field.
            value = courseDescription.value!!,
            // on below line we are adding on value change for text field.
            onValueChange = { courseDescription.value = it },

            placeholder = { Text(text = "Entrer une description") },

            modifier = Modifier.fillMaxWidth(),

            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

            singleLine = true,
        )
        // on below line we are adding spacer
        Spacer(modifier = Modifier.height(15.dp))

        // on below line creating a button to check battery charging status
        Button(onClick = {
            // on below line we are passing data to data base for updating our course.
            dbHandler.updatePlat(
                cName!!,
                courseName.value,
                courseDescription.value,
                courseTracks.value,
                courseDuration.value,
                coursePrice.value
            )
            // on below line we are displaying a toast message and opening our main activity.
            Toast.makeText(context, "Plat mis a jour avec succes..", Toast.LENGTH_SHORT).show()

        }) {
            // on below line adding a text for our button.
            Text(text = "Update Course", color = Color.White)
        }
    }
}

@Composable
fun PlatEditImagesSlider(pageCount: Int) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 10.dp)
    ) {
        Box(modifier = Modifier.fillMaxHeight(),
        ) {
            Text(
                text = "Edition d'un plat",
                color = white,
                fontSize = 35.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 2.sp
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))

    }

    val modifier = Modifier.fillMaxWidth().padding(top = 250.dp)
    PageIndicator(pageCount,modifier)
}