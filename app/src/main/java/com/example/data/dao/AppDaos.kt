package com.example.data.dao

import androidx.room.*
import com.example.data.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Query("SELECT * FROM chat_threads ORDER BY lastMessageTimestamp DESC")
    fun getAllThreads(): Flow<List<ChatThreadEntity>>

    @Query("SELECT * FROM chat_threads WHERE threadKey = :key LIMIT 1")
    suspend fun getThreadByKey(key: String): ChatThreadEntity?

    @Query("SELECT * FROM chat_messages WHERE threadKey = :threadKey ORDER BY timestamp ASC")
    fun getMessagesForThread(threadKey: String): Flow<List<ChatMessageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThread(thread: ChatThreadEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThreads(threads: List<ChatThreadEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatMessageEntity): Long

    @Query("UPDATE chat_threads SET lastMessagePreview = :preview, lastMessageTimestamp = :timestamp WHERE threadKey = :threadKey")
    suspend fun updateThreadLastMessage(threadKey: String, preview: String, timestamp: Long)

    @Delete
    suspend fun deleteMessage(message: ChatMessageEntity)

    @Query("DELETE FROM chat_threads WHERE threadKey = :threadKey")
    suspend fun deleteThread(threadKey: String)

    @Query("DELETE FROM chat_messages WHERE threadKey = :threadKey")
    suspend fun clearMessagesForThread(threadKey: String)

    @Query("SELECT COUNT(*) FROM chat_threads")
    suspend fun getThreadCount(): Int
}

@Dao
interface DiaryDao {
    @Query("SELECT * FROM diary_entries ORDER BY isPinned DESC, dateTimestamp DESC")
    fun getAllEntries(): Flow<List<DiaryEntryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: DiaryEntryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntries(entries: List<DiaryEntryEntity>)

    @Update
    suspend fun updateEntry(entry: DiaryEntryEntity)

    @Delete
    suspend fun deleteEntry(entry: DiaryEntryEntity)

    @Query("SELECT COUNT(*) FROM diary_entries")
    suspend fun getEntryCount(): Int
}

@Dao
interface EventDao {
    @Query("SELECT * FROM events ORDER BY eventTimestamp ASC")
    fun getAllEvents(): Flow<List<EventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: EventEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<EventEntity>)

    @Update
    suspend fun updateEvent(event: EventEntity)

    @Delete
    suspend fun deleteEvent(event: EventEntity)

    @Query("SELECT * FROM events WHERE notifyMe = 1 AND eventTimestamp > :now ORDER BY eventTimestamp ASC")
    suspend fun getPendingFutureEvents(now: Long): List<EventEntity>

    @Query("SELECT COUNT(*) FROM events")
    suspend fun getEventCount(): Int
}

@Dao
interface VaultDao {
    @Query("SELECT * FROM vault_documents ORDER BY dateAddedTimestamp DESC")
    fun getAllDocuments(): Flow<List<VaultDocumentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDocument(document: VaultDocumentEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDocuments(documents: List<VaultDocumentEntity>)

    @Update
    suspend fun updateDocument(document: VaultDocumentEntity)

    @Delete
    suspend fun deleteDocument(document: VaultDocumentEntity)

    @Query("SELECT COUNT(*) FROM vault_documents")
    suspend fun getDocumentCount(): Int
}

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY isCompleted ASC, CASE WHEN scheduledTimestamp IS NULL THEN 1 ELSE 0 END, scheduledTimestamp ASC, id DESC")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE notifyMe = 1 AND isCompleted = 0 AND scheduledTimestamp IS NOT NULL AND scheduledTimestamp > :now ORDER BY scheduledTimestamp ASC")
    suspend fun getPendingFutureTasks(now: Long): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<TaskEntity>)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query("SELECT COUNT(*) FROM tasks")
    suspend fun getTaskCount(): Int
}
