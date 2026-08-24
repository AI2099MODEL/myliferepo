package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.data.model.EventEntity
import com.example.ui.components.*
import com.example.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun EventsScreen(
    events: List<EventEntity>,
    onAddEvent: (title: String, locationOrNote: String, timestamp: Long, notifyMe: Boolean, category: String) -> Unit,
    onDeleteEvent: (EventEntity) -> Unit,
    onToggleNotification: (EventEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    var showAddDialog by remember { mutableStateOf(false) }
    var selectedFilter by remember { mutableStateOf("All") } // "All", "Upcoming", "Past"

    val now = System.currentTimeMillis()

    val upcomingEvents = remember(events, now) {
        events.filter { it.eventTimestamp >= now }.sortedBy { it.eventTimestamp }
    }

    val pastEvents = remember(events, now) {
        events.filter { it.eventTimestamp < now }.sortedByDescending { it.eventTimestamp }
    }

    val displayedEvents = when (selectedFilter) {
        "Upcoming" -> upcomingEvents
        "Past" -> pastEvents
        else -> events.sortedBy { it.eventTimestamp }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top Header
        LedgerTopHeader(
            title = "Events & Calendar",
            subtitle = "${upcomingEvents.size} upcoming • Notification reminders",
            actionButton = {
                FloatingActionButton(
                    onClick = { showAddDialog = true },
                    modifier = Modifier
                        .size(40.dp)
                        .testTag("add_event_fab"),
                    containerColor = LedgerBrass,
                    contentColor = Color.White,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Event", modifier = Modifier.size(22.dp))
                }
            }
        )

        // Filter Tabs (All, Upcoming, Past)
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("All", "Upcoming", "Past").forEach { filter ->
                    val isSel = selectedFilter == filter
                    val count = when (filter) {
                        "Upcoming" -> upcomingEvents.size
                        "Past" -> pastEvents.size
                        else -> events.size
                    }
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .clickable { selectedFilter = filter }
                            .testTag("event_filter_$filter"),
                        color = if (isSel) LedgerBrass else MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, if (isSel) LedgerBrass else MaterialTheme.colorScheme.outline)
                    ) {
                        Text(
                            text = "$filter ($count)",
                            fontSize = 11.sp,
                            fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSel) Color.White else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                        )
                    }
                }
            }
        }

        // Events List or Empty State
        if (displayedEvents.isEmpty()) {
            Box(modifier = Modifier.weight(1f)) {
                LedgerEmptyState(
                    icon = Icons.Outlined.CalendarMonth,
                    title = if (selectedFilter == "Upcoming") "No Upcoming Events" else "No Events Scheduled",
                    subtitle = "Plan your appointments, reviews, and milestones with scheduled reminders.",
                    actionLabel = "Schedule New Event",
                    onAction = { showAddDialog = true }
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                items(displayedEvents, key = { it.id }) { event ->
                    val isPast = event.eventTimestamp < now
                    EventCard(
                        event = event,
                        isPast = isPast,
                        onToggleNotification = { onToggleNotification(event) },
                        onDelete = { onDeleteEvent(event) }
                    )
                }
            }
        }
    }

    if (showAddDialog) {
        AddEventDialog(
            onDismiss = { showAddDialog = false },
            onSave = { title, location, timestamp, notifyMe, category ->
                onAddEvent(title, location, timestamp, notifyMe, category)
                showAddDialog = false
            }
        )
    }
}

@Composable
private fun EventCard(
    event: EventEntity,
    isPast: Boolean,
    onToggleNotification: () -> Unit,
    onDelete: () -> Unit
) {
    val dayFmt = remember { SimpleDateFormat("d", Locale.getDefault()) }
    val monthFmt = remember { SimpleDateFormat("MMM", Locale.getDefault()) }
    val timeFmt = remember { SimpleDateFormat("h:mm a", Locale.getDefault()) }
    val fullDateFmt = remember { SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault()) }

    val dateObj = remember(event.eventTimestamp) { Date(event.eventTimestamp) }
    val dayNum = remember(event.eventTimestamp) { dayFmt.format(dateObj) }
    val monthStr = remember(event.eventTimestamp) { monthFmt.format(dateObj).uppercase() }
    val timeStr = remember(event.eventTimestamp) { timeFmt.format(dateObj) }
    val fullDateStr = remember(event.eventTimestamp) { fullDateFmt.format(dateObj) }

    LedgerPaperCard(
        borderColor = if (isPast) MaterialTheme.colorScheme.outline.copy(alpha = 0.5f) else LedgerBorderTanLight
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Date Block (Day Number in large Serif + Month in uppercase Monospace)
            Surface(
                modifier = Modifier
                    .width(54.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(10.dp),
                color = if (isPast) MaterialTheme.colorScheme.surfaceVariant else LedgerBrass.copy(alpha = 0.15f),
                border = BorderStroke(
                    1.dp,
                    if (isPast) MaterialTheme.colorScheme.outline else LedgerBrass.copy(alpha = 0.4f)
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = monthStr,
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        color = if (isPast) MaterialTheme.colorScheme.onSurfaceVariant else LedgerBrass
                    )
                    Text(
                        text = dayNum,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = if (isPast) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Event Details
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = fullDateStr,
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    if (isPast) {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            Text(
                                text = "PAST",
                                fontSize = 9.sp,
                                fontFamily = FontFamily.Monospace,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = event.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = if (isPast) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) else MaterialTheme.colorScheme.onSurface
                    )
                )

                Spacer(modifier = Modifier.height(3.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = LedgerSlateBlue,
                        modifier = Modifier.size(13.dp)
                    )
                    Text(
                        text = timeStr,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                        color = LedgerSlateBlue
                    )

                    if (event.locationOrNote.isNotBlank()) {
                        Text("•", fontSize = 10.sp, color = MaterialTheme.colorScheme.outline)
                        Text(
                            text = event.locationOrNote,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1
                        )
                    }
                }
            }

            // Notification Bell Toggle & Delete
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                IconButton(
                    onClick = onToggleNotification,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = if (event.notifyMe) Icons.Filled.NotificationsActive else Icons.Outlined.NotificationsOff,
                        contentDescription = if (event.notifyMe) "Notification Active" else "Notification Disabled",
                        tint = if (event.notifyMe) LedgerBrass else MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(18.dp)
                    )
                }

                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete Event",
                        tint = MaterialTheme.colorScheme.error.copy(alpha = 0.6f),
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun AddEventDialog(
    onDismiss: () -> Unit,
    onSave: (title: String, location: String, timestamp: Long, notifyMe: Boolean, category: String) -> Unit
) {
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var locationOrNote by remember { mutableStateOf("") }
    var notifyMe by remember { mutableStateOf(true) }
    var category by remember { mutableStateOf("General") }

    val calendar = remember {
        Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, 1)
            set(Calendar.HOUR_OF_DAY, 10)
            set(Calendar.MINUTE, 0)
        }
    }
    var eventTimestamp by remember { mutableStateOf(calendar.timeInMillis) }

    val dateFmt = remember { SimpleDateFormat("MMM d, yyyy", Locale.getDefault()) }
    val timeFmt = remember { SimpleDateFormat("h:mm a", Locale.getDefault()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Schedule Event",
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
                    label = { Text("Event Title", fontFamily = FontFamily.Serif) },
                    placeholder = { Text("e.g. Strategy Review") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("event_title_input"),
                    singleLine = true
                )

                OutlinedTextField(
                    value = locationOrNote,
                    onValueChange = { locationOrNote = it },
                    label = { Text("Location / Note") },
                    placeholder = { Text("e.g. Studio Room B or Zoom link") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                // Date and Time selectors
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            val currentCal = Calendar.getInstance().apply { timeInMillis = eventTimestamp }
                            showNativeDatePicker(context, currentCal) { year, month, day ->
                                currentCal.set(Calendar.YEAR, year)
                                currentCal.set(Calendar.MONTH, month)
                                currentCal.set(Calendar.DAY_OF_MONTH, day)
                                eventTimestamp = currentCal.timeInMillis
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.CalendarToday, contentDescription = null, modifier = Modifier.size(14.dp), tint = LedgerBrass)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(dateFmt.format(Date(eventTimestamp)), fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                    }

                    OutlinedButton(
                        onClick = {
                            val currentCal = Calendar.getInstance().apply { timeInMillis = eventTimestamp }
                            showNativeTimePicker(context, currentCal) { hour, minute ->
                                currentCal.set(Calendar.HOUR_OF_DAY, hour)
                                currentCal.set(Calendar.MINUTE, minute)
                                eventTimestamp = currentCal.timeInMillis
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.AccessTime, contentDescription = null, modifier = Modifier.size(14.dp), tint = LedgerBrass)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(timeFmt.format(Date(eventTimestamp)), fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                    }
                }

                // Notify toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(Icons.Default.Notifications, contentDescription = null, tint = LedgerBrass, modifier = Modifier.size(18.dp))
                        Text("Notify me with reminder", fontSize = 13.sp)
                    }
                    Switch(
                        checked = notifyMe,
                        onCheckedChange = { notifyMe = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = LedgerBrass)
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        onSave(title.trim(), locationOrNote.trim(), eventTimestamp, notifyMe, category)
                    }
                },
                enabled = title.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = LedgerBrass)
            ) {
                Text("Schedule Event", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
