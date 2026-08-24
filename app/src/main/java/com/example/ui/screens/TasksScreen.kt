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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.TaskEntity
import com.example.ui.components.*
import com.example.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TasksScreen(
    tasks: List<TaskEntity>,
    onAddTask: (title: String, description: String, scheduledTimestamp: Long?, priority: String, notifyMe: Boolean) -> Unit,
    onToggleComplete: (TaskEntity) -> Unit,
    onDeleteTask: (TaskEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    var showAddDialog by remember { mutableStateOf(false) }
    var selectedPriorityFilter by remember { mutableStateOf("All") }
    var showCompletedSection by remember { mutableStateOf(true) }

    val activeTasks = remember(tasks, selectedPriorityFilter) {
        tasks.filter { !it.isCompleted }.filter {
            selectedPriorityFilter == "All" || it.priority.equals(selectedPriorityFilter, ignoreCase = true)
        }
    }

    val completedTasks = remember(tasks, selectedPriorityFilter) {
        tasks.filter { it.isCompleted }.filter {
            selectedPriorityFilter == "All" || it.priority.equals(selectedPriorityFilter, ignoreCase = true)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top Header
        LedgerTopHeader(
            title = "Tasks & Checklist",
            subtitle = "${activeTasks.size} pending • ${completedTasks.size} completed",
            actionButton = {
                FloatingActionButton(
                    onClick = { showAddDialog = true },
                    modifier = Modifier
                        .size(40.dp)
                        .testTag("add_task_fab"),
                    containerColor = LedgerBrass,
                    contentColor = Color.White,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Task", modifier = Modifier.size(22.dp))
                }
            }
        )

        // Priority Filter Bar
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(listOf("All", "HIGH", "MED", "LOW")) { prio ->
                    val isSel = selectedPriorityFilter == prio
                    val count = if (prio == "All") tasks.count { !it.isCompleted } else tasks.count { !it.isCompleted && it.priority.equals(prio, ignoreCase = true) }
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .clickable { selectedPriorityFilter = prio }
                            .testTag("task_filter_$prio"),
                        color = if (isSel) LedgerBrass else MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, if (isSel) LedgerBrass else MaterialTheme.colorScheme.outline)
                    ) {
                        Text(
                            text = if (prio == "All") "All ($count)" else "$prio ($count)",
                            fontSize = 11.sp,
                            fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                            fontFamily = FontFamily.Monospace,
                            color = if (isSel) Color.White else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        // Tasks List
        if (activeTasks.isEmpty() && completedTasks.isEmpty()) {
            Box(modifier = Modifier.weight(1f)) {
                LedgerEmptyState(
                    icon = Icons.Outlined.CheckCircleOutline,
                    title = "No Tasks Found",
                    subtitle = "Stay organized with prioritized checklists and scheduled alarm reminders.",
                    actionLabel = "Add First Task",
                    onAction = { showAddDialog = true }
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                // Active Tasks
                items(activeTasks, key = { it.id }) { task ->
                    TaskItemCard(
                        task = task,
                        onToggle = { onToggleComplete(task) },
                        onDelete = { onDeleteTask(task) }
                    )
                }

                // Completed Section Header & List
                if (completedTasks.isNotEmpty()) {
                    item {
                        Spacer(modifier = Modifier.height(12.dp))
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { showCompletedSection = !showCompletedSection },
                            color = Color.Transparent
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Completed (${completedTasks.size})",
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Icon(
                                    imageVector = if (showCompletedSection) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }

                    if (showCompletedSection) {
                        items(completedTasks, key = { it.id }) { task ->
                            TaskItemCard(
                                task = task,
                                onToggle = { onToggleComplete(task) },
                                onDelete = { onDeleteTask(task) }
                            )
                        }
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        AddTaskDialog(
            onDismiss = { showAddDialog = false },
            onSave = { title, description, timestamp, priority, notifyMe ->
                onAddTask(title, description, timestamp, priority, notifyMe)
                showAddDialog = false
            }
        )
    }
}

@Composable
private fun TaskItemCard(
    task: TaskEntity,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    val dateFmt = remember { SimpleDateFormat("MMM d • h:mm a", Locale.getDefault()) }
    val formattedDate = remember(task.scheduledTimestamp) {
        task.scheduledTimestamp?.let { dateFmt.format(Date(it)) }
    }

    LedgerPaperCard(
        borderColor = if (task.isCompleted) MaterialTheme.colorScheme.outline.copy(alpha = 0.4f) else LedgerBorderTanLight,
        backgroundColor = if (task.isCompleted) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f) else MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Checkbox
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { onToggle() },
                colors = CheckboxDefaults.colors(
                    checkedColor = LedgerBrass,
                    checkmarkColor = Color.White
                ),
                modifier = Modifier.size(24.dp)
            )

            // Content
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = if (task.isCompleted) FontWeight.Normal else FontWeight.SemiBold,
                            color = if (task.isCompleted) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f) else MaterialTheme.colorScheme.onSurface,
                            textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                        ),
                        modifier = Modifier.weight(1f, fill = false)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    LedgerPriorityBadge(priority = task.priority)
                }

                if (task.description.isNotBlank()) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = task.description,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = if (task.isCompleted) 0.4f else 0.8f),
                        maxLines = 2
                    )
                }

                if (formattedDate != null || task.notifyMe) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        if (task.notifyMe) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Reminder Active",
                                tint = LedgerBrass,
                                modifier = Modifier.size(12.dp)
                            )
                        }
                        if (formattedDate != null) {
                            Text(
                                text = formattedDate,
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace,
                                color = if (task.isCompleted) MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f) else LedgerSlateBlue
                            )
                        }
                    }
                }
            }

            // Delete action
            IconButton(
                onClick = onDelete,
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete Task",
                    tint = MaterialTheme.colorScheme.error.copy(alpha = 0.5f),
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

@Composable
private fun AddTaskDialog(
    onDismiss: () -> Unit,
    onSave: (title: String, description: String, timestamp: Long?, priority: String, notifyMe: Boolean) -> Unit
) {
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("MED") }
    var hasSchedule by remember { mutableStateOf(false) }
    var notifyMe by remember { mutableStateOf(false) }

    val calendar = remember {
        Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, 1)
            set(Calendar.HOUR_OF_DAY, 17)
            set(Calendar.MINUTE, 0)
        }
    }
    var scheduledTimestamp by remember { mutableStateOf(calendar.timeInMillis) }

    val dateFmt = remember { SimpleDateFormat("MMM d, yyyy", Locale.getDefault()) }
    val timeFmt = remember { SimpleDateFormat("h:mm a", Locale.getDefault()) }

    val priorities = listOf("HIGH", "MED", "LOW")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "New Ledger Task",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Task Title") },
                    placeholder = { Text("e.g. Audit ledger balance sheet") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("task_title_input"),
                    singleLine = true
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Details / Note (Optional)") },
                    placeholder = { Text("Additional instructions or links") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2
                )

                Text(
                    text = "Priority Level:",
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    priorities.forEach { prio ->
                        val isSel = priority == prio
                        val color = when (prio) {
                            "HIGH" -> LedgerPriorityHigh
                            "LOW" -> LedgerPriorityLow
                            else -> LedgerPriorityMed
                        }
                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { priority = prio },
                            color = if (isSel) color else MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, color.copy(alpha = 0.5f))
                        ) {
                            Box(
                                modifier = Modifier.padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = prio,
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSel) Color.White else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }

                // Schedule toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Set Due Date & Time", fontSize = 13.sp)
                    Switch(
                        checked = hasSchedule,
                        onCheckedChange = {
                            hasSchedule = it
                            if (!it) notifyMe = false
                        },
                        colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = LedgerBrass)
                    )
                }

                if (hasSchedule) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                val currentCal = Calendar.getInstance().apply { timeInMillis = scheduledTimestamp }
                                showNativeDatePicker(context, currentCal) { year, month, day ->
                                    currentCal.set(Calendar.YEAR, year)
                                    currentCal.set(Calendar.MONTH, month)
                                    currentCal.set(Calendar.DAY_OF_MONTH, day)
                                    scheduledTimestamp = currentCal.timeInMillis
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.CalendarToday, contentDescription = null, modifier = Modifier.size(13.dp), tint = LedgerBrass)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(dateFmt.format(Date(scheduledTimestamp)), fontSize = 10.sp, fontFamily = FontFamily.Monospace)
                        }

                        OutlinedButton(
                            onClick = {
                                val currentCal = Calendar.getInstance().apply { timeInMillis = scheduledTimestamp }
                                showNativeTimePicker(context, currentCal) { hour, minute ->
                                    currentCal.set(Calendar.HOUR_OF_DAY, hour)
                                    currentCal.set(Calendar.MINUTE, minute)
                                    scheduledTimestamp = currentCal.timeInMillis
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.AccessTime, contentDescription = null, modifier = Modifier.size(13.dp), tint = LedgerBrass)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(timeFmt.format(Date(scheduledTimestamp)), fontSize = 10.sp, fontFamily = FontFamily.Monospace)
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Notify reminder on schedule", fontSize = 12.sp)
                        Checkbox(
                            checked = notifyMe,
                            onCheckedChange = { notifyMe = it },
                            colors = CheckboxDefaults.colors(checkedColor = LedgerBrass)
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        onSave(
                            title.trim(),
                            description.trim(),
                            if (hasSchedule) scheduledTimestamp else null,
                            priority,
                            notifyMe && hasSchedule
                        )
                    }
                },
                enabled = title.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = LedgerBrass)
            ) {
                Text("Add Task", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
