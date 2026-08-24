package com.example

import com.example.data.model.ChatMessageEntity
import com.example.data.model.ChatThreadEntity
import com.example.data.model.DiaryEntryEntity
import com.example.data.model.EventEntity
import com.example.data.model.TaskEntity
import com.example.data.model.VaultDocumentEntity
import com.example.ui.LedgerSection
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class LedgerLogicUnitTest {

    @Test
    fun testLedgerSectionEntries() {
        val sections = LedgerSection.entries
        assertEquals(5, sections.size)
        assertEquals(LedgerSection.CHAT, sections[0])
        assertEquals(LedgerSection.DIARY, sections[1])
        assertEquals(LedgerSection.EVENTS, sections[2])
        assertEquals(LedgerSection.VAULT, sections[3])
        assertEquals(LedgerSection.TASKS, sections[4])
    }

    @Test
    fun testChatThreadAndMessageCreation() {
        val thread = ChatThreadEntity(
            threadKey = "work_notes",
            name = "Work Notes",
            category = "Work",
            iconEmoji = "💼"
        )
        assertEquals("work_notes", thread.threadKey)
        assertEquals("💼", thread.iconEmoji)

        val message = ChatMessageEntity(
            threadKey = thread.threadKey,
            content = "Review the ledger roadmap",
            isSentByUser = true,
            senderName = "You"
        )
        assertTrue(message.isSentByUser)
        assertEquals("Review the ledger roadmap", message.content)
    }

    @Test
    fun testDiaryEntryCreationAndPin() {
        val entry = DiaryEntryEntity(
            title = "Morning Coffee & Notes",
            body = "Productive morning working on the new binder interface.",
            moodOrTag = "Reflection",
            isPinned = true
        )
        assertTrue(entry.isPinned)
        assertEquals("Reflection", entry.moodOrTag)
    }

    @Test
    fun testEventEntitySchedule() {
        val now = System.currentTimeMillis()
        val future = now + 86400000L
        val event = EventEntity(
            id = 42L,
            title = "Quarterly Review",
            locationOrNote = "Studio B",
            eventTimestamp = future,
            notifyMe = true,
            notificationScheduledId = 42
        )
        assertTrue(event.eventTimestamp > now)
        assertEquals(42, event.notificationScheduledId)
    }

    @Test
    fun testTaskCompletionToggle() {
        val task = TaskEntity(
            id = 10L,
            title = "Finalize tax records",
            priority = "HIGH",
            isCompleted = false
        )
        assertFalse(task.isCompleted)

        val completed = task.copy(
            isCompleted = true,
            completedTimestamp = System.currentTimeMillis()
        )
        assertTrue(completed.isCompleted)
        assertNotNull(completed.completedTimestamp)
    }

    @Test
    fun testVaultDocumentEntity() {
        val doc = VaultDocumentEntity(
            title = "Passport Scan",
            originalFileName = "passport.pdf",
            uriString = "ledger://vault/passport.pdf",
            fileType = "PDF",
            category = "ID",
            fileSizeBytes = 1024000L
        )
        assertEquals("PDF", doc.fileType)
        assertEquals("ID", doc.category)
    }
}
