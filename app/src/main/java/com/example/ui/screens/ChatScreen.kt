package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ChatMessageEntity
import com.example.data.model.ChatThreadEntity
import com.example.ui.components.LedgerEmptyState
import com.example.ui.components.LedgerTopHeader
import com.example.ui.theme.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ChatScreen(
    threads: List<ChatThreadEntity>,
    activeThreadKey: String,
    messages: List<ChatMessageEntity>,
    onSelectThread: (String) -> Unit,
    onSendMessage: (threadKey: String, text: String) -> Unit,
    onCreateThread: (name: String, category: String, iconEmoji: String) -> Unit,
    onDeleteThread: (threadKey: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var messageInput by remember { mutableStateOf("") }
    var showCreateThreadDialog by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Auto-scroll to bottom on new message
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    val activeThread = threads.find { it.threadKey == activeThreadKey } ?: threads.firstOrNull()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top Header
        LedgerTopHeader(
            title = "Ledger Threads",
            subtitle = "Encrypted local messaging • Room SQLite",
            actionButton = {
                IconButton(
                    onClick = { showCreateThreadDialog = true },
                    modifier = Modifier.testTag("add_thread_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.AddComment,
                        contentDescription = "New Thread",
                        tint = LedgerBrass
                    )
                }
            }
        )

        // Sub-navigation: Thread Tabs (Family, Work, Personal, General, + Custom)
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(threads, key = { it.threadKey }) { thread ->
                    val isSelected = thread.threadKey == activeThreadKey
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { onSelectThread(thread.threadKey) }
                            .testTag("thread_tab_${thread.threadKey}"),
                        shape = RoundedCornerShape(20.dp),
                        color = if (isSelected) LedgerBrass else MaterialTheme.colorScheme.surfaceVariant,
                        border = BorderStroke(
                            1.dp,
                            if (isSelected) LedgerBrass else MaterialTheme.colorScheme.outline
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(text = thread.iconEmoji, fontSize = 13.sp)
                            Text(
                                text = thread.name,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                fontFamily = if (isSelected) FontFamily.Serif else FontFamily.SansSerif,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                item {
                    // Quick add thread button pill
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { showCreateThreadDialog = true },
                        shape = RoundedCornerShape(20.dp),
                        color = Color.Transparent,
                        border = BorderStroke(1.dp, LedgerBrass.copy(alpha = 0.5f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Add Thread", tint = LedgerBrass, modifier = Modifier.size(14.dp))
                            Text("New Thread", fontSize = 11.sp, color = LedgerBrass, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }

        // Active Thread Info Banner
        if (activeThread != null) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.background,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Active: ${activeThread.name} (${activeThread.category})",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "${messages.size} msgs",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        color = LedgerBrass
                    )
                }
            }
        }

        // Messages List or Empty State
        if (messages.isEmpty()) {
            Box(modifier = Modifier.weight(1f)) {
                LedgerEmptyState(
                    icon = Icons.Outlined.ChatBubbleOutline,
                    title = "No Messages Yet",
                    subtitle = "Send a note or message into this private thread to start the conversation.",
                    actionLabel = "Say Hello",
                    onAction = {
                        activeThread?.let {
                            onSendMessage(it.threadKey, "Hello! Starting this ledger thread.")
                        }
                    }
                )
            }
        } else {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                items(messages, key = { it.id }) { message ->
                    ChatMessageBubble(message = message)
                }
            }
        }

        // Message Input Field & Send Button
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            shadowElevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = messageInput,
                    onValueChange = { messageInput = it },
                    placeholder = {
                        Text(
                            text = "Write in ${activeThread?.name ?: "thread"}...",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("chat_input_field"),
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedBorderColor = LedgerBrass,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    ),
                    maxLines = 3
                )

                IconButton(
                    onClick = {
                        if (messageInput.isNotBlank() && activeThread != null) {
                            onSendMessage(activeThread.threadKey, messageInput)
                            messageInput = ""
                            coroutineScope.launch {
                                if (messages.isNotEmpty()) {
                                    listState.animateScrollToItem(messages.size)
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(if (messageInput.isNotBlank()) LedgerBrass else LedgerBrass.copy(alpha = 0.4f))
                        .testTag("send_message_button"),
                    enabled = messageInput.isNotBlank() && activeThread != null
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }

    // Dialog for creating new thread
    if (showCreateThreadDialog) {
        CreateThreadDialog(
            onDismiss = { showCreateThreadDialog = false },
            onConfirm = { name, category, emoji ->
                onCreateThread(name, category, emoji)
                showCreateThreadDialog = false
            }
        )
    }
}

@Composable
private fun ChatMessageBubble(message: ChatMessageEntity) {
    val timeFmt = remember { SimpleDateFormat("h:mm a", Locale.getDefault()) }
    val formattedTime = remember(message.timestamp) { timeFmt.format(Date(message.timestamp)) }

    val isUser = message.isSentByUser

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isUser) Alignment.End else Alignment.Start
    ) {
        // Sender name for received messages
        if (!isUser) {
            Text(
                text = message.senderName,
                fontSize = 11.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = LedgerSlateBlue,
                modifier = Modifier.padding(start = 6.dp, bottom = 2.dp)
            )
        }

        // Bubble Surface
        Surface(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .shadow(elevation = 1.dp, shape = RoundedCornerShape(
                    topStart = 14.dp,
                    topEnd = 14.dp,
                    bottomStart = if (isUser) 14.dp else 2.dp,
                    bottomEnd = if (isUser) 2.dp else 14.dp
                )),
            shape = RoundedCornerShape(
                topStart = 14.dp,
                topEnd = 14.dp,
                bottomStart = if (isUser) 14.dp else 2.dp,
                bottomEnd = if (isUser) 2.dp else 14.dp
            ),
            color = if (isUser) LedgerBrass else MaterialTheme.colorScheme.surface,
            border = BorderStroke(
                1.dp,
                if (isUser) LedgerBrassLight.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outline
            )
        ) {
            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                Text(
                    text = message.content,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = if (isUser) Color.White else MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp
                    )
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = formattedTime,
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    color = if (isUser) Color.White.copy(alpha = 0.75f) else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@Composable
private fun CreateThreadDialog(
    onDismiss: () -> Unit,
    onConfirm: (name: String, category: String, emoji: String) -> Unit
) {
    var threadName by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Personal") }
    var selectedEmoji by remember { mutableStateOf("🌿") }

    val categories = listOf("Personal", "Work", "Family", "Projects", "Finance", "Study")
    val emojis = listOf("🌿", "💼", "🏡", "🚀", "💡", "☕", "📚", "🎯", "🎨", "⚓")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "New Ledger Thread",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = threadName,
                    onValueChange = { threadName = it },
                    label = { Text("Thread Name") },
                    placeholder = { Text("e.g. Architecture Notes") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Text(
                    text = "Category:",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(categories) { cat ->
                        val isSel = selectedCategory == cat
                        Surface(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { selectedCategory = cat },
                            color = if (isSel) LedgerBrass else MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = cat,
                                fontSize = 11.sp,
                                color = if (isSel) Color.White else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                            )
                        }
                    }
                }

                Text(
                    text = "Thread Icon:",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(emojis) { emoji ->
                        val isSel = selectedEmoji == emoji
                        Surface(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .clickable { selectedEmoji = emoji },
                            color = if (isSel) LedgerBrass.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant,
                            border = if (isSel) BorderStroke(2.dp, LedgerBrass) else null,
                            shape = CircleShape
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(text = emoji, fontSize = 16.sp)
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (threadName.isNotBlank()) {
                        onConfirm(threadName.trim(), selectedCategory, selectedEmoji)
                    }
                },
                enabled = threadName.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = LedgerBrass)
            ) {
                Text("Create Thread", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
