package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.LedgerRepository
import com.example.data.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

enum class LedgerSection(val title: String, val tabLabel: String, val iconEmoji: String) {
    CHAT("Chat", "Chat", "💬"),
    DIARY("Personal Diary", "Diary", "📖"),
    EVENTS("Events & Calendar", "Events", "📅"),
    VAULT("Document Vault", "Vault", "🔒"),
    TASKS("Tasks & Checklist", "Tasks", "✓")
}

@OptIn(ExperimentalCoroutinesApi::class)
class LedgerViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LedgerRepository

    val selectedSection = MutableStateFlow(LedgerSection.CHAT)
    val activeChatThreadKey = MutableStateFlow("family")

    val chatThreads: StateFlow<List<ChatThreadEntity>>
    val activeThreadMessages: StateFlow<List<ChatMessageEntity>>
    val diaryEntries: StateFlow<List<DiaryEntryEntity>>
    val events: StateFlow<List<EventEntity>>
    val vaultDocuments: StateFlow<List<VaultDocumentEntity>>
    val tasks: StateFlow<List<TaskEntity>>

    init {
        val db = AppDatabase.getDatabase(application)
        repository = LedgerRepository(db, application)

        // Seed default initial data
        viewModelScope.launch {
            repository.seedInitialDataIfNeeded()
        }

        chatThreads = repository.allChatThreads
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        activeThreadMessages = activeChatThreadKey
            .flatMapLatest { key ->
                if (key.isBlank()) flowOf(emptyList())
                else repository.getMessagesForThread(key)
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        diaryEntries = repository.allDiaryEntries
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        events = repository.allEvents
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        vaultDocuments = repository.allVaultDocuments
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        tasks = repository.allTasks
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    }

    fun setSection(section: LedgerSection) {
        selectedSection.value = section
    }

    fun setActiveChatThread(threadKey: String) {
        activeChatThreadKey.value = threadKey
    }

    // ---------------- CHAT ----------------
    fun sendChatMessage(threadKey: String, text: String) {
        if (text.isBlank()) return
        viewModelScope.launch {
            repository.sendMessage(threadKey, text.trim(), isSentByUser = true, senderName = "You")
        }
    }

    fun createChatThread(name: String, category: String, iconEmoji: String) {
        viewModelScope.launch {
            val newKey = repository.createThread(name, category, iconEmoji)
            activeChatThreadKey.value = newKey
        }
    }

    fun deleteChatThread(threadKey: String) {
        viewModelScope.launch {
            repository.deleteThread(threadKey)
            if (activeChatThreadKey.value == threadKey) {
                val remaining = chatThreads.value.filter { it.threadKey != threadKey }
                if (remaining.isNotEmpty()) {
                    activeChatThreadKey.value = remaining.first().threadKey
                }
            }
        }
    }

    // ---------------- DIARY ----------------
    fun addDiaryEntry(title: String, body: String, moodOrTag: String, isPinned: Boolean, timestamp: Long = System.currentTimeMillis()) {
        if (title.isBlank() && body.isBlank()) return
        viewModelScope.launch {
            repository.addDiaryEntry(title, body, moodOrTag, isPinned, timestamp)
        }
    }

    fun updateDiaryEntry(entry: DiaryEntryEntity) {
        viewModelScope.launch {
            repository.updateDiaryEntry(entry)
        }
    }

    fun deleteDiaryEntry(entry: DiaryEntryEntity) {
        viewModelScope.launch {
            repository.deleteDiaryEntry(entry)
        }
    }

    // ---------------- EVENTS ----------------
    fun addEvent(title: String, locationOrNote: String, eventTimestamp: Long, notifyMe: Boolean, category: String = "General") {
        if (title.isBlank()) return
        viewModelScope.launch {
            repository.addEvent(title, locationOrNote, eventTimestamp, notifyMe, category)
        }
    }

    fun deleteEvent(event: EventEntity) {
        viewModelScope.launch {
            repository.deleteEvent(event)
        }
    }

    fun toggleEventNotification(event: EventEntity) {
        viewModelScope.launch {
            repository.toggleEventNotification(event)
        }
    }

    // ---------------- VAULT ----------------
    fun addVaultDocument(
        title: String,
        originalFileName: String,
        uriString: String,
        fileType: String,
        category: String,
        fileSizeBytes: Long = 0L,
        notes: String = ""
    ) {
        if (title.isBlank()) return
        viewModelScope.launch {
            repository.addVaultDocument(title, originalFileName, uriString, fileType, category, fileSizeBytes, notes)
        }
    }

    fun deleteVaultDocument(document: VaultDocumentEntity) {
        viewModelScope.launch {
            repository.deleteVaultDocument(document)
        }
    }

    // ---------------- TASKS ----------------
    fun addTask(
        title: String,
        description: String,
        scheduledTimestamp: Long?,
        priority: String,
        notifyMe: Boolean
    ) {
        if (title.isBlank()) return
        viewModelScope.launch {
            repository.addTask(title, description, scheduledTimestamp, priority, notifyMe)
        }
    }

    fun toggleTaskComplete(task: TaskEntity) {
        viewModelScope.launch {
            repository.toggleTaskComplete(task)
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}
