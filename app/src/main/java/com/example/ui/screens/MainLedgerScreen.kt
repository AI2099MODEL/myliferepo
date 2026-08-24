package com.example.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.LedgerSection
import com.example.ui.LedgerViewModel
import com.example.ui.components.LedgerBinderBottomBar
import com.example.ui.components.LedgerBinderNavRail

@Composable
fun MainLedgerScreen(
    viewModel: LedgerViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val currentSection by viewModel.selectedSection.collectAsStateWithLifecycle()
    val chatThreads by viewModel.chatThreads.collectAsStateWithLifecycle()
    val activeChatThreadKey by viewModel.activeChatThreadKey.collectAsStateWithLifecycle()
    val activeThreadMessages by viewModel.activeThreadMessages.collectAsStateWithLifecycle()
    val diaryEntries by viewModel.diaryEntries.collectAsStateWithLifecycle()
    val events by viewModel.events.collectAsStateWithLifecycle()
    val vaultDocs by viewModel.vaultDocuments.collectAsStateWithLifecycle()
    val tasks by viewModel.tasks.collectAsStateWithLifecycle()

    // Android 13+ Notification Permission Flow
    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { _ -> }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val isExpanded = maxWidth >= 600.dp

        if (isExpanded) {
            // Adaptive Tablet / Landscape layout with Navigation Rail
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                LedgerBinderNavRail(
                    currentSection = currentSection,
                    onSectionSelected = { viewModel.setSection(it) }
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    ScreenContent(
                        currentSection = currentSection,
                        viewModel = viewModel,
                        chatThreads = chatThreads,
                        activeChatThreadKey = activeChatThreadKey,
                        activeThreadMessages = activeThreadMessages,
                        diaryEntries = diaryEntries,
                        events = events,
                        vaultDocs = vaultDocs,
                        tasks = tasks
                    )
                }
            }
        } else {
            // Compact Mobile layout with Binder Bottom Bar
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    LedgerBinderBottomBar(
                        currentSection = currentSection,
                        onSectionSelected = { viewModel.setSection(it) }
                    )
                },
                containerColor = MaterialTheme.colorScheme.background
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    ScreenContent(
                        currentSection = currentSection,
                        viewModel = viewModel,
                        chatThreads = chatThreads,
                        activeChatThreadKey = activeChatThreadKey,
                        activeThreadMessages = activeThreadMessages,
                        diaryEntries = diaryEntries,
                        events = events,
                        vaultDocs = vaultDocs,
                        tasks = tasks
                    )
                }
            }
        }
    }
}

@Composable
private fun ScreenContent(
    currentSection: LedgerSection,
    viewModel: LedgerViewModel,
    chatThreads: List<com.example.data.model.ChatThreadEntity>,
    activeChatThreadKey: String,
    activeThreadMessages: List<com.example.data.model.ChatMessageEntity>,
    diaryEntries: List<com.example.data.model.DiaryEntryEntity>,
    events: List<com.example.data.model.EventEntity>,
    vaultDocs: List<com.example.data.model.VaultDocumentEntity>,
    tasks: List<com.example.data.model.TaskEntity>
) {
    Crossfade(targetState = currentSection, label = "ledger_section_crossfade") { section ->
        when (section) {
            LedgerSection.CHAT -> {
                ChatScreen(
                    threads = chatThreads,
                    activeThreadKey = activeChatThreadKey,
                    messages = activeThreadMessages,
                    onSelectThread = { viewModel.setActiveChatThread(it) },
                    onSendMessage = { key, text -> viewModel.sendChatMessage(key, text) },
                    onCreateThread = { name, cat, emoji -> viewModel.createChatThread(name, cat, emoji) },
                    onDeleteThread = { viewModel.deleteChatThread(it) }
                )
            }
            LedgerSection.DIARY -> {
                DiaryScreen(
                    entries = diaryEntries,
                    onAddEntry = { title, body, tag, pinned, timestamp ->
                        viewModel.addDiaryEntry(title, body, tag, pinned, timestamp)
                    },
                    onDeleteEntry = { viewModel.deleteDiaryEntry(it) },
                    onUpdateEntry = { viewModel.updateDiaryEntry(it) }
                )
            }
            LedgerSection.EVENTS -> {
                EventsScreen(
                    events = events,
                    onAddEvent = { title, location, timestamp, notify, cat ->
                        viewModel.addEvent(title, location, timestamp, notify, cat)
                    },
                    onDeleteEvent = { viewModel.deleteEvent(it) },
                    onToggleNotification = { viewModel.toggleEventNotification(it) }
                )
            }
            LedgerSection.VAULT -> {
                VaultScreen(
                    documents = vaultDocs,
                    onAddDocument = { title, fileName, uriStr, type, cat, size, notes ->
                        viewModel.addVaultDocument(title, fileName, uriStr, type, cat, size, notes)
                    },
                    onDeleteDocument = { viewModel.deleteVaultDocument(it) }
                )
            }
            LedgerSection.TASKS -> {
                TasksScreen(
                    tasks = tasks,
                    onAddTask = { title, desc, timestamp, priority, notify ->
                        viewModel.addTask(title, desc, timestamp, priority, notify)
                    },
                    onToggleComplete = { viewModel.toggleTaskComplete(it) },
                    onDeleteTask = { viewModel.deleteTask(it) }
                )
            }
        }
    }
}
