package com.nepenthx.papercut

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nepenthx.papercut.packageManager.PaperCutPackageManager
import com.nepenthx.papercut.papercutUI.MainUI
import com.nepenthx.papercut.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    val paperCut = PaperCut()

    override fun onCreate(savedInstanceState: Bundle?) {
        paperCut.init(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  MainUI(appList = PaperCutPackageManager.getInstance().aLlApp,modifier = Modifier.padding(innerPadding) )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
    }
}