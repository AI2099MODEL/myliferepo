package com.example.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// ==========================================
// LEDGER WARM PERSONAL JOURNAL / BINDER PALETTE
// ==========================================

// Primary Palette
val LedgerInkNavy = Color(0xFF1B2430)       // Deep ink navy for nav headers & dark base
val LedgerInkNavyDark = Color(0xFF121820)   // Darker ink background
val LedgerParchment = Color(0xFFF6F1E7)     // Warm parchment paper background (Light)
val LedgerParchmentDark = Color(0xFF161E28) // Deep warm slate parchment (Dark)

// Accents
val LedgerBrass = Color(0xFFB8935A)         // Warm brass / gold primary accent
val LedgerBrassLight = Color(0xFFD4AF37)    // Bright vintage gold highlight
val LedgerBrassContainer = Color(0xFF2A2318)// Dark warm brass container
val LedgerBrassPale = Color(0xFFF3EBDE)     // Light warm brass tint for cards

val LedgerSlateBlue = Color(0xFF5C7A99)     // Muted slate blue secondary accent
val LedgerSlateBlueLight = Color(0xFF8BA5BF)// Soft slate highlight
val LedgerSlateBlueContainer = Color(0xFF1C2733)

// Card & Surface Colors
val LedgerPaperLight = Color(0xFFFDFCFA)    // Aged crisp paper card
val LedgerPaperDark = Color(0xFF1F2937)     // Ink card surface
val LedgerBorderTanLight = Color(0xFFE5DAC8)// Hairline warm tan border
val LedgerBorderTanDark = Color(0xFF334155) // Hairline dark slate border

// Text Colors
val LedgerInkTextDark = Color(0xFF212936)   // Main ink text on light parchment
val LedgerInkMutedDark = Color(0xFF6B7280)  // Subdued secondary text on light
val LedgerParchmentTextLight = Color(0xFFF7F4EE) // Main text on dark ink
val LedgerParchmentMutedLight = Color(0xFFA0ABC0)// Subdued text on dark

// Functional / Priority Colors
val LedgerPriorityHigh = Color(0xFFC64E45)  // Aged Crimson Red
val LedgerPriorityMed = Color(0xFFB8935A)   // Warm Brass Gold
val LedgerPriorityLow = Color(0xFF5C7A99)   // Muted Slate Blue
val LedgerSuccessSage = Color(0xFF4E8752)   // Vintage Sage Green

// Gradients
val LedgerBinderTabGradient = Brush.verticalGradient(
    colors = listOf(LedgerBrass, Color(0xFF9E7B46))
)

val LedgerInkHeaderGradient = Brush.verticalGradient(
    colors = listOf(LedgerInkNavy, Color(0xFF131A23))
)
