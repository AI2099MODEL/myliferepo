package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.ui.LedgerViewModel
import com.example.ui.screens.MainLedgerScreen
import com.example.ui.theme.LedgerTheme
import com.example.util.NotificationHelper

class MainActivity : ComponentActivity() {
    private val viewModel: LedgerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize notification channel
        NotificationHelper.createNotificationChannel(this)

        setContent {
            LedgerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainLedgerScreen(viewModel = viewModel)
                }
            }
        }
    }
}
