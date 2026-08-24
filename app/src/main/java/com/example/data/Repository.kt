package com.example.data

import android.content.Context
import com.example.data.dao.*
import com.example.data.model.*
import com.example.util.NotificationHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.Calendar

class LedgerRepository(
    private val database: AppDatabase,
    private val context: Context
) {
    private val chatDao: ChatDao = database.chatDao()
    private val diaryDao: DiaryDao = database.diaryDao()
    private val eventDao: EventDao = database.eventDao()
    private val vaultDao: VaultDao = database.vaultDao()
    private val taskDao: TaskDao = database.taskDao()

    // ----------------------------------------------------
    // CHAT
    // ----------------------------------------------------
    val allChatThreads: Flow<List<ChatThreadEntity>> = chatDao.getAllThreads()

    fun getMessagesForThread(threadKey: String): Flow<List<ChatMessageEntity>> =
        chatDao.getMessagesForThread(threadKey)

    suspend fun sendMessage(
        threadKey: String,
        content: String,
        isSentByUser: Boolean = true,
        senderName: String = "You"
    ) = withContext(Dispatchers.IO) {
        val now = System.currentTimeMillis()
        val message = ChatMessageEntity(
            threadKey = threadKey,
            content = content,
            isSentByUser = isSentByUser,
            senderName = senderName,
            timestamp = now
        )
        chatDao.insertMessage(message)
        chatDao.updateThreadLastMessage(threadKey, content, now)
    }

    suspend fun createThread(name: String, category: String, iconEmoji: String): String = withContext(Dispatchers.IO) {
        val key = name.trim().lowercase().replace(" ", "_") + "_" + (System.currentTimeMillis() % 1000)
        val thread = ChatThreadEntity(
            threadKey = key,
            name = name.trim(),
            category = category,
            iconEmoji = iconEmoji,
            lastMessagePreview = "Thread created",
            lastMessageTimestamp = System.currentTimeMillis()
        )
        chatDao.insertThread(thread)
        key
    }

    suspend fun deleteThread(threadKey: String) = withContext(Dispatchers.IO) {
        chatDao.clearMessagesForThread(threadKey)
        chatDao.deleteThread(threadKey)
    }

    // ----------------------------------------------------
    // DIARY
    // ----------------------------------------------------
    val allDiaryEntries: Flow<List<DiaryEntryEntity>> = diaryDao.getAllEntries()

    suspend fun addDiaryEntry(title: String, body: String, moodOrTag: String, isPinned: Boolean, timestamp: Long = System.currentTimeMillis()) =
        withContext(Dispatchers.IO) {
            val entry = DiaryEntryEntity(
                title = title.trim(),
                body = body.trim(),
                dateTimestamp = timestamp,
                moodOrTag = moodOrTag.trim().ifEmpty { "Reflection" },
                isPinned = isPinned
            )
            diaryDao.insertEntry(entry)
        }

    suspend fun updateDiaryEntry(entry: DiaryEntryEntity) = withContext(Dispatchers.IO) {
        diaryDao.updateEntry(entry)
    }

    suspend fun deleteDiaryEntry(entry: DiaryEntryEntity) = withContext(Dispatchers.IO) {
        diaryDao.deleteEntry(entry)
    }

    // ----------------------------------------------------
    // EVENTS
    // ----------------------------------------------------
    val allEvents: Flow<List<EventEntity>> = eventDao.getAllEvents()

    suspend fun addEvent(
        title: String,
        locationOrNote: String,
        eventTimestamp: Long,
        notifyMe: Boolean,
        category: String = "General"
    ) = withContext(Dispatchers.IO) {
        val notificationId = (System.currentTimeMillis() % 100000).toInt()
        val event = EventEntity(
            title = title.trim(),
            locationOrNote = locationOrNote.trim(),
            eventTimestamp = eventTimestamp,
            notifyMe = notifyMe,
            notificationScheduledId = notificationId,
            category = category
        )
        val id = eventDao.insertEvent(event)

        if (notifyMe) {
            NotificationHelper.scheduleReminder(
                context = context,
                notificationId = notificationId,
                title = title.trim(),
                message = if (locationOrNote.isNotBlank()) "Location: $locationOrNote" else "Upcoming scheduled event in Ledger",
                timestampMillis = eventTimestamp,
                type = "EVENT"
            )
        }
        id
    }

    suspend fun deleteEvent(event: EventEntity) = withContext(Dispatchers.IO) {
        if (event.notifyMe && event.notificationScheduledId != 0) {
            NotificationHelper.cancelReminder(context, event.notificationScheduledId)
        }
        eventDao.deleteEvent(event)
    }

    suspend fun toggleEventNotification(event: EventEntity) = withContext(Dispatchers.IO) {
        val newNotify = !event.notifyMe
        val notifId = if (event.notificationScheduledId != 0) event.notificationScheduledId else (System.currentTimeMillis() % 100000).toInt()
        val updated = event.copy(notifyMe = newNotify, notificationScheduledId = notifId)
        eventDao.updateEvent(updated)

        if (newNotify) {
            NotificationHelper.scheduleReminder(
                context = context,
                notificationId = notifId,
                title = event.title,
                message = if (event.locationOrNote.isNotBlank()) "Location: ${event.locationOrNote}" else "Scheduled event in Ledger",
                timestampMillis = event.eventTimestamp,
                type = "EVENT"
            )
        } else {
            NotificationHelper.cancelReminder(context, notifId)
        }
    }

    // ----------------------------------------------------
    // VAULT
    // ----------------------------------------------------
    val allVaultDocuments: Flow<List<VaultDocumentEntity>> = vaultDao.getAllDocuments()

    suspend fun addVaultDocument(
        title: String,
        originalFileName: String,
        uriString: String,
        fileType: String,
        category: String,
        fileSizeBytes: Long = 0L,
        notes: String = ""
    ) = withContext(Dispatchers.IO) {
        val doc = VaultDocumentEntity(
            title = title.trim(),
            originalFileName = originalFileName.trim().ifEmpty { "attachment" },
            uriString = uriString,
            fileType = fileType,
            category = category,
            dateAddedTimestamp = System.currentTimeMillis(),
            fileSizeBytes = fileSizeBytes,
            notes = notes.trim()
        )
        vaultDao.insertDocument(doc)
    }

    suspend fun deleteVaultDocument(document: VaultDocumentEntity) = withContext(Dispatchers.IO) {
        vaultDao.deleteDocument(document)
    }

    // ----------------------------------------------------
    // TASKS
    // ----------------------------------------------------
    val allTasks: Flow<List<TaskEntity>> = taskDao.getAllTasks()

    suspend fun addTask(
        title: String,
        description: String,
        scheduledTimestamp: Long?,
        priority: String,
        notifyMe: Boolean
    ) = withContext(Dispatchers.IO) {
        val notifId = (System.currentTimeMillis() % 100000).toInt()
        val task = TaskEntity(
            title = title.trim(),
            description = description.trim(),
            scheduledTimestamp = scheduledTimestamp,
            priority = priority,
            isCompleted = false,
            notifyMe = notifyMe,
            notificationScheduledId = notifId
        )
        val id = taskDao.insertTask(task)

        if (notifyMe && scheduledTimestamp != null) {
            NotificationHelper.scheduleReminder(
                context = context,
                notificationId = notifId,
                title = title.trim(),
                message = if (description.isNotBlank()) description else "Priority: $priority task due in Ledger",
                timestampMillis = scheduledTimestamp,
                type = "TASK"
            )
        }
        id
    }

    suspend fun toggleTaskComplete(task: TaskEntity) = withContext(Dispatchers.IO) {
        val newStatus = !task.isCompleted
        val updated = task.copy(
            isCompleted = newStatus,
            completedTimestamp = if (newStatus) System.currentTimeMillis() else null
        )
        taskDao.updateTask(updated)

        // Cancel notification if completed
        if (newStatus && task.notifyMe && task.notificationScheduledId != 0) {
            NotificationHelper.cancelReminder(context, task.notificationScheduledId)
        }
    }

    suspend fun deleteTask(task: TaskEntity) = withContext(Dispatchers.IO) {
        if (task.notifyMe && task.notificationScheduledId != 0) {
            NotificationHelper.cancelReminder(context, task.notificationScheduledId)
        }
        taskDao.deleteTask(task)
    }

    // ----------------------------------------------------
    // SEED INITIAL DATA IF FRESH INSTALL
    // ----------------------------------------------------
    suspend fun seedInitialDataIfNeeded() = withContext(Dispatchers.IO) {
        if (chatDao.getThreadCount() == 0) {
            val now = System.currentTimeMillis()
            val initialThreads = listOf(
                ChatThreadEntity(
                    threadKey = "family",
                    name = "Family",
                    category = "Family",
                    iconEmoji = "🏡",
                    lastMessagePreview = "I will bring fresh sourdough!",
                    lastMessageTimestamp = now - 1000 * 60 * 25
                ),
                ChatThreadEntity(
                    threadKey = "work",
                    name = "Work",
                    category = "Work",
                    iconEmoji = "💼",
                    lastMessagePreview = "The sprint planning notes are in the shared drive.",
                    lastMessageTimestamp = now - 1000 * 60 * 75
                ),
                ChatThreadEntity(
                    threadKey = "personal",
                    name = "Personal",
                    category = "Personal",
                    iconEmoji = "🌿",
                    lastMessagePreview = "Morning routine meditation 15m completed.",
                    lastMessageTimestamp = now - 1000 * 60 * 180
                ),
                ChatThreadEntity(
                    threadKey = "general",
                    name = "General",
                    category = "General",
                    iconEmoji = "☕",
                    lastMessagePreview = "All your notes and chats are saved strictly on-device in Room.",
                    lastMessageTimestamp = now - 1000 * 60 * 300
                )
            )
            chatDao.insertThreads(initialThreads)

            // Messages
            val messages = listOf(
                ChatMessageEntity(threadKey = "family", content = "Are we meeting for Sunday brunch at 11?", isSentByUser = false, senderName = "Mom", timestamp = now - 1000 * 60 * 30),
                ChatMessageEntity(threadKey = "family", content = "Yes! I will bring fresh sourdough!", isSentByUser = false, senderName = "Dad", timestamp = now - 1000 * 60 * 25),
                ChatMessageEntity(threadKey = "family", content = "Sounds wonderful, I'll bring the fresh fruit & dessert.", isSentByUser = true, senderName = "You", timestamp = now - 1000 * 60 * 20),

                ChatMessageEntity(threadKey = "work", content = "The sprint planning notes are in the shared drive.", isSentByUser = false, senderName = "Jordan (PM)", timestamp = now - 1000 * 60 * 75),
                ChatMessageEntity(threadKey = "work", content = "Thanks, reviewing the requirements today.", isSentByUser = true, senderName = "You", timestamp = now - 1000 * 60 * 60),

                ChatMessageEntity(threadKey = "personal", content = "Morning routine meditation 15m completed.", isSentByUser = true, senderName = "You", timestamp = now - 1000 * 60 * 180),
                ChatMessageEntity(threadKey = "personal", content = "Idea: Check weekend mountain hiking trail guidebook.", isSentByUser = true, senderName = "You", timestamp = now - 1000 * 60 * 120),

                ChatMessageEntity(threadKey = "general", content = "Welcome to Ledger. All your notes and chats are saved strictly on-device in Room.", isSentByUser = false, senderName = "Ledger Desk", timestamp = now - 1000 * 60 * 300)
            )
            for (msg in messages) {
                chatDao.insertMessage(msg)
            }
        }

        if (diaryDao.getEntryCount() == 0) {
            val now = System.currentTimeMillis()
            val entries = listOf(
                DiaryEntryEntity(
                    title = "Reflections on a Quiet Morning",
                    body = "Took thirty minutes with hot pour-over coffee before opening any screens. The morning silence makes the entire week feel calm and structured. Intending to preserve this ritual each day.",
                    dateTimestamp = now - 1000 * 60 * 60 * 4,
                    moodOrTag = "Reflection",
                    isPinned = true
                ),
                DiaryEntryEntity(
                    title = "Setting Up the Ledger Binder",
                    body = "Gathered my ongoing project lists, important vault document scans, and upcoming calendar appointments into one reliable offline organizer. Feels great to have everything clear.",
                    dateTimestamp = now - 1000 * 60 * 60 * 28,
                    moodOrTag = "Milestone",
                    isPinned = false
                ),
                DiaryEntryEntity(
                    title = "Focus and Creative Flow",
                    body = "Spent the afternoon drafting design concepts. When interruptions are eliminated, two hours of deep work accomplish more than an entire fragmented day.",
                    dateTimestamp = now - 1000 * 60 * 60 * 72,
                    moodOrTag = "Deep Thought",
                    isPinned = false
                )
            )
            diaryDao.insertEntries(entries)
        }

        if (eventDao.getEventCount() == 0) {
            val cal = Calendar.getInstance()
            val events = mutableListOf<EventEntity>()

            // Event 1: Tomorrow 10:00 AM
            cal.add(Calendar.DAY_OF_YEAR, 1)
            cal.set(Calendar.HOUR_OF_DAY, 10)
            cal.set(Calendar.MINUTE, 0)
            events.add(
                EventEntity(
                    title = "Design System & Roadmap Review",
                    locationOrNote = "Studio Room B • Notebook & samples",
                    eventTimestamp = cal.timeInMillis,
                    notifyMe = true,
                    notificationScheduledId = 101,
                    category = "Work"
                )
            )

            // Event 2: In 3 days 9:30 AM
            cal.add(Calendar.DAY_OF_YEAR, 2)
            cal.set(Calendar.HOUR_OF_DAY, 9)
            cal.set(Calendar.MINUTE, 30)
            events.add(
                EventEntity(
                    title = "Weekend Farmers Market & Groceries",
                    locationOrNote = "Downtown Plaza • Sourdough & seasonal produce",
                    eventTimestamp = cal.timeInMillis,
                    notifyMe = true,
                    notificationScheduledId = 102,
                    category = "Personal"
                )
            )

            // Event 3: In 7 days 2:00 PM
            cal.add(Calendar.DAY_OF_YEAR, 4)
            cal.set(Calendar.HOUR_OF_DAY, 14)
            cal.set(Calendar.MINUTE, 0)
            events.add(
                EventEntity(
                    title = "Quarterly Financial & Tax Audit",
                    locationOrNote = "Home Office • Audit binder receipts",
                    eventTimestamp = cal.timeInMillis,
                    notifyMe = false,
                    notificationScheduledId = 103,
                    category = "Finance"
                )
            )

            // Event 4 (Past): 3 days ago
            cal.add(Calendar.DAY_OF_YEAR, -10)
            cal.set(Calendar.HOUR_OF_DAY, 15)
            events.add(
                EventEntity(
                    title = "Architectural Binder Consultation",
                    locationOrNote = "Central Library Archive",
                    eventTimestamp = cal.timeInMillis,
                    notifyMe = false,
                    notificationScheduledId = 104,
                    category = "General"
                )
            )

            eventDao.insertEvents(events)
        }

        if (vaultDao.getDocumentCount() == 0) {
            val now = System.currentTimeMillis()
            val docs = listOf(
                VaultDocumentEntity(
                    title = "Passport & International ID Scan",
                    originalFileName = "passport_scan_2026.pdf",
                    uriString = "ledger://sample/passport_scan_2026.pdf",
                    fileType = "PDF",
                    category = "ID",
                    dateAddedTimestamp = now - 1000 * 60 * 60 * 24 * 5,
                    fileSizeBytes = 2450000L,
                    notes = "High-resolution color scan of photo identification and visa pages."
                ),
                VaultDocumentEntity(
                    title = "Residential Lease Agreement 2026",
                    originalFileName = "lease_agreement_signed.pdf",
                    uriString = "ledger://sample/lease_agreement_signed.pdf",
                    fileType = "PDF",
                    category = "Legal",
                    dateAddedTimestamp = now - 1000 * 60 * 60 * 24 * 12,
                    fileSizeBytes = 4120000L,
                    notes = "Counter-signed lease contract and building tenancy rules."
                ),
                VaultDocumentEntity(
                    title = "Vehicle Comprehensive Insurance Policy",
                    originalFileName = "auto_policy_card_2026.pdf",
                    uriString = "ledger://sample/auto_policy_card_2026.pdf",
                    fileType = "PDF",
                    category = "Insurance",
                    dateAddedTimestamp = now - 1000 * 60 * 60 * 24 * 18,
                    fileSizeBytes = 1890000L,
                    notes = "Roadside assistance contact and proof of insurance."
                ),
                VaultDocumentEntity(
                    title = "Medical Vaccination & Health Card",
                    originalFileName = "health_card_front_back.png",
                    uriString = "ledger://sample/health_card_front_back.png",
                    fileType = "IMAGE",
                    category = "Health",
                    dateAddedTimestamp = now - 1000 * 60 * 60 * 24 * 25,
                    fileSizeBytes = 3200000L,
                    notes = "Primary physician info & emergency blood type record."
                )
            )
            vaultDao.insertDocuments(docs)
        }

        if (taskDao.getTaskCount() == 0) {
            val cal = Calendar.getInstance()
            cal.add(Calendar.DAY_OF_YEAR, 1)
            cal.set(Calendar.HOUR_OF_DAY, 17)
            cal.set(Calendar.MINUTE, 0)
            val tomorrow5pm = cal.timeInMillis

            cal.add(Calendar.DAY_OF_YEAR, 2)
            cal.set(Calendar.HOUR_OF_DAY, 12)
            val in3daysNoon = cal.timeInMillis

            val tasks = listOf(
                TaskEntity(
                    title = "Finalize quarterly expense ledger & receipt audit",
                    description = "Verify all vendor receipts match the ledger balance sheet.",
                    scheduledTimestamp = tomorrow5pm,
                    priority = "HIGH",
                    isCompleted = false,
                    notifyMe = true,
                    notificationScheduledId = 201
                ),
                TaskEntity(
                    title = "Review product requirements and deliverable scope",
                    description = "Send feedback on team sprint backlog.",
                    scheduledTimestamp = System.currentTimeMillis() + 1000 * 60 * 60 * 3,
                    priority = "HIGH",
                    isCompleted = false,
                    notifyMe = false,
                    notificationScheduledId = 202
                ),
                TaskEntity(
                    title = "Scan and file updated vehicle registration into Vault",
                    description = "Save PDF copy into the insurance and legal category.",
                    scheduledTimestamp = in3daysNoon,
                    priority = "MED",
                    isCompleted = false,
                    notifyMe = false,
                    notificationScheduledId = 203
                ),
                TaskEntity(
                    title = "Order fresh parchment binder paper & index tabs",
                    description = "Heavyweight archive-grade paper stock.",
                    scheduledTimestamp = null,
                    priority = "LOW",
                    isCompleted = false,
                    notifyMe = false,
                    notificationScheduledId = 204
                ),
                TaskEntity(
                    title = "Setup initial Ledger workspace and binder sections",
                    description = "Created Chat, Diary, Events, Vault, and Tasks modules.",
                    scheduledTimestamp = System.currentTimeMillis() - 1000 * 60 * 60 * 5,
                    priority = "MED",
                    isCompleted = true,
                    completedTimestamp = System.currentTimeMillis() - 1000 * 60 * 60 * 2,
                    notifyMe = false,
                    notificationScheduledId = 205
                )
            )
            taskDao.insertTasks(tasks)
        }
    }
}
