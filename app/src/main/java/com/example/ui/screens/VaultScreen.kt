package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.VaultDocumentEntity
import com.example.ui.components.LedgerEmptyState
import com.example.ui.components.LedgerPaperCard
import com.example.ui.components.LedgerTopHeader
import com.example.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun VaultScreen(
    documents: List<VaultDocumentEntity>,
    onAddDocument: (title: String, originalFileName: String, uriString: String, fileType: String, category: String, sizeBytes: Long, notes: String) -> Unit,
    onDeleteDocument: (VaultDocumentEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showAddDialog by remember { mutableStateOf(false) }
    var selectedCategoryFilter by remember { mutableStateOf("All") }
    var viewingDoc by remember { mutableStateOf<VaultDocumentEntity?>(null) }

    val categories = listOf("All", "ID", "Finance", "Legal", "Insurance", "Health", "Receipts", "Personal")

    val filteredDocuments = remember(documents, selectedCategoryFilter) {
        if (selectedCategoryFilter == "All") documents
        else documents.filter { it.category.equals(selectedCategoryFilter, ignoreCase = true) }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top Header
        LedgerTopHeader(
            title = "Document Vault",
            subtitle = "${documents.size} encrypted documents • Offline storage",
            actionButton = {
                FloatingActionButton(
                    onClick = { showAddDialog = true },
                    modifier = Modifier
                        .size(40.dp)
                        .testTag("add_vault_doc_fab"),
                    containerColor = LedgerBrass,
                    contentColor = Color.White,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Document", modifier = Modifier.size(22.dp))
                }
            }
        )

        // Category Filter Chips
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
                items(categories) { cat ->
                    val isSel = selectedCategoryFilter == cat
                    val count = if (cat == "All") documents.size else documents.count { it.category.equals(cat, ignoreCase = true) }
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .clickable { selectedCategoryFilter = cat }
                            .testTag("vault_cat_$cat"),
                        color = if (isSel) LedgerBrass else MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, if (isSel) LedgerBrass else MaterialTheme.colorScheme.outline)
                    ) {
                        Text(
                            text = "$cat ($count)",
                            fontSize = 11.sp,
                            fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSel) Color.White else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        // Documents Grid / Empty State
        if (filteredDocuments.isEmpty()) {
            Box(modifier = Modifier.weight(1f)) {
                LedgerEmptyState(
                    icon = Icons.Outlined.FolderSpecial,
                    title = "Vault is Empty",
                    subtitle = "Safely catalog contracts, identity scans, health cards, and financial PDFs.",
                    actionLabel = "Import Document",
                    onAction = { showAddDialog = true }
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 160.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                items(filteredDocuments, key = { it.id }) { doc ->
                    VaultDocumentGridCard(
                        doc = doc,
                        onClick = { viewingDoc = doc },
                        onOpen = { openDocumentInSystem(context, doc) },
                        onDelete = { onDeleteDocument(doc) }
                    )
                }
            }
        }
    }

    if (showAddDialog) {
        AddVaultDocumentDialog(
            onDismiss = { showAddDialog = false },
            onSave = { title, fileName, uriStr, type, cat, size, notes ->
                onAddDocument(title, fileName, uriStr, type, cat, size, notes)
                showAddDialog = false
            }
        )
    }

    viewingDoc?.let { doc ->
        VaultDocumentDetailDialog(
            doc = doc,
            onDismiss = { viewingDoc = null },
            onOpen = {
                openDocumentInSystem(context, doc)
            },
            onDelete = {
                onDeleteDocument(doc)
                viewingDoc = null
            }
        )
    }
}

@Composable
private fun VaultDocumentGridCard(
    doc: VaultDocumentEntity,
    onClick: () -> Unit,
    onOpen: () -> Unit,
    onDelete: () -> Unit
) {
    val dateFmt = remember { SimpleDateFormat("MMM d, yyyy", Locale.getDefault()) }
    val formattedDate = remember(doc.dateAddedTimestamp) { dateFmt.format(Date(doc.dateAddedTimestamp)) }

    LedgerPaperCard(
        onClick = onClick,
        modifier = Modifier.height(200.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header: Category Pill & Delete Icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = LedgerSlateBlue.copy(alpha = 0.15f),
                    border = BorderStroke(1.dp, LedgerSlateBlue.copy(alpha = 0.3f))
                ) {
                    Text(
                        text = doc.category,
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        color = LedgerSlateBlue,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                    )
                }

                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(22.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error.copy(alpha = 0.6f),
                        modifier = Modifier.size(14.dp)
                    )
                }
            }

            // Central Icon with file type indicator
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(10.dp))
                    .background(LedgerBrass.copy(alpha = 0.15f))
                    .border(1.dp, LedgerBrass.copy(alpha = 0.3f), RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = getDocumentIcon(doc.fileType),
                    contentDescription = doc.fileType,
                    tint = LedgerBrass,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Title and Details
            Column {
                Text(
                    text = doc.title,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = doc.originalFileName,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = formattedDate,
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
private fun AddVaultDocumentDialog(
    onDismiss: () -> Unit,
    onSave: (title: String, fileName: String, uriStr: String, fileType: String, category: String, sizeBytes: Long, notes: String) -> Unit
) {
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("ID") }
    var selectedFileType by remember { mutableStateOf("PDF") }
    var pickedFileName by remember { mutableStateOf("") }
    var pickedUriString by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var pickedFileSize by remember { mutableLongStateOf(0L) }

    // SAF Document Picker
    val docPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            try {
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (_: Exception) {}

            pickedUriString = uri.toString()
            val fileName = uri.lastPathSegment ?: "document"
            pickedFileName = fileName

            if (title.isBlank()) {
                title = fileName.substringBeforeLast(".")
            }

            if (fileName.endsWith(".pdf", ignoreCase = true)) {
                selectedFileType = "PDF"
            } else if (fileName.endsWith(".png", ignoreCase = true) || fileName.endsWith(".jpg", ignoreCase = true) || fileName.endsWith(".jpeg", ignoreCase = true)) {
                selectedFileType = "IMAGE"
            }
        }
    }

    val categories = listOf("ID", "Finance", "Legal", "Insurance", "Health", "Receipts", "Personal")
    val fileTypes = listOf("PDF", "IMAGE", "DOC", "CERTIFICATE")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Add Document to Vault",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Pick file button
                OutlinedButton(
                    onClick = {
                        docPickerLauncher.launch(arrayOf("application/pdf", "image/*", "*/*"))
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.AttachFile, contentDescription = null, tint = LedgerBrass, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (pickedFileName.isNotBlank()) "Attached: $pickedFileName" else "Select File / Attachment",
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Document Title", fontFamily = FontFamily.Serif) },
                    placeholder = { Text("e.g. Passport Scan 2026") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("vault_title_input"),
                    singleLine = true
                )

                Text(
                    text = "Category:",
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(categories) { cat ->
                        val isSel = selectedCategory == cat
                        Surface(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { selectedCategory = cat },
                            color = if (isSel) LedgerBrass else MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = cat,
                                fontSize = 10.sp,
                                color = if (isSel) Color.White else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

                Text(
                    text = "Document Type:",
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(fileTypes) { type ->
                        val isSel = selectedFileType == type
                        Surface(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { selectedFileType = type },
                            color = if (isSel) LedgerSlateBlue else MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = type,
                                fontSize = 10.sp,
                                color = if (isSel) Color.White else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes / Description") },
                    placeholder = { Text("Reference details, policy number, etc.") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        val finalFileName = if (pickedFileName.isNotBlank()) pickedFileName else "${title.lowercase().replace(" ", "_")}.${selectedFileType.lowercase()}"
                        val finalUri = if (pickedUriString.isNotBlank()) pickedUriString else "ledger://vault/$finalFileName"
                        onSave(title.trim(), finalFileName, finalUri, selectedFileType, selectedCategory, pickedFileSize, notes.trim())
                    }
                },
                enabled = title.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = LedgerBrass)
            ) {
                Text("Save to Vault", color = Color.White)
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
private fun VaultDocumentDetailDialog(
    doc: VaultDocumentEntity,
    onDismiss: () -> Unit,
    onOpen: () -> Unit,
    onDelete: () -> Unit
) {
    val dateFmt = remember { SimpleDateFormat("EEEE, MMMM d, yyyy • h:mm a", Locale.getDefault()) }
    val formattedDate = remember(doc.dateAddedTimestamp) { dateFmt.format(Date(doc.dateAddedTimestamp)) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = getDocumentIcon(doc.fileType),
                    contentDescription = null,
                    tint = LedgerBrass,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = doc.title,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = LedgerSlateBlue.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = doc.category,
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace,
                            color = LedgerSlateBlue,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = LedgerBrass.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = doc.fileType,
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace,
                            color = LedgerBrass,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Text(
                    text = "File: ${doc.originalFileName}",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "Added: $formattedDate",
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (doc.notes.isNotBlank()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Notes:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    )
                    Text(
                        text = doc.notes,
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface)
                    )
                }
            }
        },
        confirmButton = {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = onOpen,
                    colors = ButtonDefaults.buttonColors(containerColor = LedgerBrass)
                ) {
                    Icon(Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Open File", color = Color.White)
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

private fun openDocumentInSystem(context: Context, doc: VaultDocumentEntity) {
    if (doc.uriString.startsWith("content://") || doc.uriString.startsWith("file://")) {
        try {
            val uri = Uri.parse(doc.uriString)
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, if (doc.fileType == "PDF") "application/pdf" else if (doc.fileType == "IMAGE") "image/*" else "*/*")
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(Intent.createChooser(intent, "Open with"))
        } catch (_: Exception) {
            Toast.makeText(context, "Cannot open external viewer. Document cataloged in Ledger Vault.", Toast.LENGTH_SHORT).show()
        }
    } else {
        Toast.makeText(context, "Encrypted Vault Record: ${doc.title} (${doc.originalFileName})", Toast.LENGTH_SHORT).show()
    }
}

private fun getDocumentIcon(fileType: String): ImageVector {
    return when (fileType.uppercase()) {
        "PDF" -> Icons.Default.PictureAsPdf
        "IMAGE" -> Icons.Default.Image
        "DOC" -> Icons.Default.Description
        "CERTIFICATE" -> Icons.Default.VerifiedUser
        "RECEIPT" -> Icons.Default.Receipt
        else -> Icons.Default.InsertDriveFile
    }
}
