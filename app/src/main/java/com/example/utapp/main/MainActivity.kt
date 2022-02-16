package com.example.utapp.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.utapp.UTApp
import com.example.utapp.data.Dog
import com.example.utapp.ui.theme.UTAppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as UTApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            UTAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ListOfDogs(viewModel.listOfDogs, { code -> viewModel.tappedOn(code) })
                }
            }
        }

        viewModel.showToast.observe(this) {
            if (it.isNotEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.start()
    }
}

@Composable
fun ListOfDogs(dogs: List<String>, tappedOn: (Int) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = dogs, itemContent = { item ->
            Box(modifier = Modifier
                .padding(8.dp)
                .clickable { tappedOn(1007) }) {
                Text(item)
            }
        })
    }
}