package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DiaryEntryEntity
import com.example.ui.components.LedgerEmptyState
import com.example.ui.components.LedgerPaperCard
import com.example.ui.components.LedgerTopHeader
import com.example.ui.components.showNativeDatePicker
import com.example.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DiaryScreen(
    entries: List<DiaryEntryEntity>,
    onAddEntry: (title: String, body: String, moodOrTag: String, isPinned: Boolean, timestamp: Long) -> Unit,
    onDeleteEntry: (DiaryEntryEntity) -> Unit,
    onUpdateEntry: (DiaryEntryEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTagFilter by remember { mutableStateOf("All") }
    var showAddDialog by remember { mutableStateOf(false) }
    var editingEntry by remember { mutableStateOf<DiaryEntryEntity?>(null) }
    var viewingEntry by remember { mutableStateOf<DiaryEntryEntity?>(null) }

    val tags = listOf("All", "Reflection", "Milestone", "Deep Thought", "Gratitude", "Ideas", "Daily")

    val filteredEntries = remember(entries, searchQuery, selectedTagFilter) {
        entries.filter { entry ->
            val matchesTag = selectedTagFilter == "All" || entry.moodOrTag.equals(selectedTagFilter, ignoreCase = true)
            val matchesQuery = searchQuery.isBlank() ||
                    entry.title.contains(searchQuery, ignoreCase = true) ||
                    entry.body.contains(searchQuery, ignoreCase = true)
            matchesTag && matchesQuery
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top Header
        LedgerTopHeader(
            title = "Personal Diary",
            subtitle = "${entries.size} journal entries • Room persisted",
            actionButton = {
                FloatingActionButton(
                    onClick = { showAddDialog = true },
                    modifier = Modifier
                        .size(40.dp)
                        .testTag("add_diary_entry_fab"),
                    containerColor = LedgerBrass,
                    contentColor = Color.White,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.EditNote, contentDescription = "Write Entry", modifier = Modifier.size(22.dp))
                }
            }
        )

        // Search bar & Filter pills
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search journal entries...", fontSize = 12.sp) },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null, tint = LedgerBrass, modifier = Modifier.size(18.dp))
                    },
                    trailingIcon = {
                        if (searchQuery.isNotBlank()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Close, contentDescription = "Clear", modifier = Modifier.size(16.dp))
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("diary_search_field"),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedBorderColor = LedgerBrass,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(tags) { tag ->
                        val isSelected = selectedTagFilter == tag
                        Surface(
                            modifier = Modifier
                                .clip(RoundedCornerShape(14.dp))
                                .clickable { selectedTagFilter = tag },
                            shape = RoundedCornerShape(14.dp),
                            color = if (isSelected) LedgerBrass else MaterialTheme.colorScheme.surfaceVariant,
                            border = BorderStroke(1.dp, if (isSelected) LedgerBrass else MaterialTheme.colorScheme.outline)
                        ) {
                            Text(
                                text = tag,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }

        // Entries List or Empty State
        if (filteredEntries.isEmpty()) {
            Box(modifier = Modifier.weight(1f)) {
                LedgerEmptyState(
                    icon = Icons.Outlined.MenuBook,
                    title = if (searchQuery.isNotBlank()) "No Matching Entries" else "Your Journal is Empty",
                    subtitle = if (searchQuery.isNotBlank()) "Try a different search keyword or tag filter." else "Record your daily thoughts, milestones, and reflections in Ledger.",
                    actionLabel = "Write First Entry",
                    onAction = { showAddDialog = true }
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                items(filteredEntries, key = { it.id }) { entry ->
                    DiaryEntryCard(
                        entry = entry,
                        onClick = { viewingEntry = entry },
                        onEdit = { editingEntry = entry },
                        onDelete = { onDeleteEntry(entry) }
                    )
                }
            }
        }
    }

    // Add Entry Dialog
    if (showAddDialog) {
        DiaryEntryEditorDialog(
            entry = null,
            onDismiss = { showAddDialog = false },
            onSave = { title, body, tag, isPinned, timestamp ->
                onAddEntry(title, body, tag, isPinned, timestamp)
                showAddDialog = false
            }
        )
    }

    // Edit Entry Dialog
    editingEntry?.let { entry ->
        DiaryEntryEditorDialog(
            entry = entry,
            onDismiss = { editingEntry = null },
            onSave = { title, body, tag, isPinned, timestamp ->
                onUpdateEntry(entry.copy(title = title, body = body, moodOrTag = tag, isPinned = isPinned, dateTimestamp = timestamp))
                editingEntry = null
            }
        )
    }

    // Full Read Dialog
    viewingEntry?.let { entry ->
        DiaryEntryViewDialog(
            entry = entry,
            onDismiss = { viewingEntry = null },
            onEdit = {
                viewingEntry = null
                editingEntry = entry
            },
            onDelete = {
                onDeleteEntry(entry)
                viewingEntry = null
            }
        )
    }
}

@Composable
private fun DiaryEntryCard(
    entry: DiaryEntryEntity,
    onClick: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val dateFmt = remember { SimpleDateFormat("EEEE, MMMM d, yyyy • h:mm a", Locale.getDefault()) }
    val formattedDate = remember(entry.dateTimestamp) { dateFmt.format(Date(entry.dateTimestamp)) }

    LedgerPaperCard(
        onClick = onClick,
        borderColor = if (entry.isPinned) LedgerBrass else MaterialTheme.colorScheme.outline
    ) {
        // Date & Pin header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if (entry.isPinned) {
                    Icon(
                        imageVector = Icons.Default.PushPin,
                        contentDescription = "Pinned",
                        tint = LedgerBrass,
                        modifier = Modifier.size(14.dp)
                    )
                }
                Text(
                    text = formattedDate,
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Surface(
                shape = RoundedCornerShape(8.dp),
                color = LedgerBrass.copy(alpha = 0.15f),
                border = BorderStroke(1.dp, LedgerBrass.copy(alpha = 0.3f))
            ) {
                Text(
                    text = entry.moodOrTag,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = LedgerBrass,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Title in Serif
        Text(
            text = entry.title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = MaterialTheme.colorScheme.onSurface
            )
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Body Preview
        Text(
            text = entry.body,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                lineHeight = 20.sp
            ),
            maxLines = 3
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Card footer actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onEdit,
                modifier = Modifier.size(28.dp)
            ) {
                Icon(Icons.Outlined.Edit, contentDescription = "Edit", tint = LedgerSlateBlue, modifier = Modifier.size(16.dp))
            }
            Spacer(modifier = Modifier.width(4.dp))
            IconButton(
                onClick = onDelete,
                modifier = Modifier.size(28.dp)
            ) {
                Icon(Icons.Outlined.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f), modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
private fun DiaryEntryEditorDialog(
    entry: DiaryEntryEntity?,
    onDismiss: () -> Unit,
    onSave: (title: String, body: String, tag: String, isPinned: Boolean, timestamp: Long) -> Unit
) {
    val context = LocalContext.current
    var title by remember { mutableStateOf(entry?.title ?: "") }
    var body by remember { mutableStateOf(entry?.body ?: "") }
    var tag by remember { mutableStateOf(entry?.moodOrTag ?: "Reflection") }
    var isPinned by remember { mutableStateOf(entry?.isPinned ?: false) }
    var timestamp by remember { mutableStateOf(entry?.dateTimestamp ?: System.currentTimeMillis()) }

    val dateFmt = remember { SimpleDateFormat("MMM d, yyyy", Locale.getDefault()) }
    val tagOptions = listOf("Reflection", "Milestone", "Deep Thought", "Gratitude", "Ideas", "Daily", "Plans")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (entry == null) "New Diary Entry" else "Edit Entry",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Date picker trigger button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Date: ${dateFmt.format(Date(timestamp))}",
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    TextButton(
                        onClick = {
                            val cal = Calendar.getInstance().apply { timeInMillis = timestamp }
                            showNativeDatePicker(context, cal) { year, month, day ->
                                val newCal = Calendar.getInstance().apply {
                                    set(Calendar.YEAR, year)
                                    set(Calendar.MONTH, month)
                                    set(Calendar.DAY_OF_MONTH, day)
                                }
                                timestamp = newCal.timeInMillis
                            }
                        }
                    ) {
                        Text("Change Date", fontSize = 12.sp, color = LedgerBrass)
                    }
                }

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title", fontFamily = FontFamily.Serif) },
                    placeholder = { Text("Entry Heading") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("diary_title_input"),
                    singleLine = true
                )

                OutlinedTextField(
                    value = body,
                    onValueChange = { body = it },
                    label = { Text("Body") },
                    placeholder = { Text("Write your reflections, notes, or memories...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .testTag("diary_body_input"),
                    maxLines = 10
                )

                Text(
                    text = "Category / Tag:",
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(tagOptions) { opt ->
                        val isSel = tag == opt
                        Surface(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { tag = opt },
                            color = if (isSel) LedgerBrass else MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = opt,
                                fontSize = 10.sp,
                                color = if (isSel) Color.White else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Checkbox(
                        checked = isPinned,
                        onCheckedChange = { isPinned = it },
                        colors = CheckboxDefaults.colors(checkedColor = LedgerBrass)
                    )
                    Text("Pin this entry to the top", fontSize = 13.sp)
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank() || body.isNotBlank()) {
                        onSave(title.ifBlank { "Untitled Entry" }, body, tag, isPinned, timestamp)
                    }
                },
                enabled = title.isNotBlank() || body.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = LedgerBrass)
            ) {
                Text("Save Entry", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
private fun DiaryEntryViewDialog(
    entry: DiaryEntryEntity,
    onDismiss: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val dateFmt = remember { SimpleDateFormat("EEEE, MMMM d, yyyy • h:mm a", Locale.getDefault()) }
    val formattedDate = remember(entry.dateTimestamp) { dateFmt.format(Date(entry.dateTimestamp)) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Column {
                Text(
                    text = entry.title,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = formattedDate,
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = LedgerBrass.copy(alpha = 0.15f),
                    border = BorderStroke(1.dp, LedgerBrass.copy(alpha = 0.3f))
                ) {
                    Text(
                        text = "Tag: ${entry.moodOrTag}",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        color = LedgerBrass,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = entry.body,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        lineHeight = 22.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        },
        confirmButton = {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = onEdit) {
                    Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Edit")
                }
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = LedgerBrass)
                ) {
                    Text("Done", color = Color.White)
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDelete) {
                Text("Delete", color = MaterialTheme.colorScheme.error)
            }
        }
    )
}
