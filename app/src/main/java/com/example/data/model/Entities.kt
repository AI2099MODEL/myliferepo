package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_threads")
data class ChatThreadEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val threadKey: String,
    val name: String,
    val category: String,
    val iconEmoji: String = "💬",
    val lastMessagePreview: String = "",
    val lastMessageTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "chat_messages")
data class ChatMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val threadKey: String,
    val content: String,
    val isSentByUser: Boolean,
    val senderName: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "diary_entries")
data class DiaryEntryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val body: String,
    val dateTimestamp: Long = System.currentTimeMillis(),
    val moodOrTag: String = "Reflection",
    val isPinned: Boolean = false
)

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val locationOrNote: String = "",
    val eventTimestamp: Long,
    val notifyMe: Boolean = true,
    val notificationScheduledId: Int = 0,
    val category: String = "General"
)

@Entity(tableName = "vault_documents")
data class VaultDocumentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val originalFileName: String,
    val uriString: String,
    val fileType: String = "PDF", // PDF, IMAGE, DOC, CERTIFICATE, RECEIPT
    val category: String = "Personal", // ID, Finance, Legal, Insurance, Health, Personal
    val dateAddedTimestamp: Long = System.currentTimeMillis(),
    val fileSizeBytes: Long = 0L,
    val notes: String = ""
)

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val scheduledTimestamp: Long? = null,
    val priority: String = "MED", // "HIGH", "MED", "LOW"
    val isCompleted: Boolean = false,
    val completedTimestamp: Long? = null,
    val notifyMe: Boolean = false,
    val notificationScheduledId: Int = 0
)
