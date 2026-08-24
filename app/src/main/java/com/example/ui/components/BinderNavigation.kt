package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.LedgerSection
import com.example.ui.theme.*

@Composable
fun LedgerBinderBottomBar(
    currentSection: LedgerSection,
    onSectionSelected: (LedgerSection) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 12.dp, shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        color = LedgerInkNavy,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, LedgerBorderTanDark)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 6.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LedgerSection.entries.forEach { section ->
                val isSelected = currentSection == section
                BinderTabItem(
                    section = section,
                    isSelected = isSelected,
                    onClick = { onSectionSelected(section) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun BinderTabItem(
    section: LedgerSection,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tabHeight by animateDpAsState(
        targetValue = if (isSelected) 56.dp else 48.dp,
        animationSpec = tween(durationMillis = 200),
        label = "tabHeight"
    )

    val bgColor by animateColorAsState(
        targetValue = if (isSelected) LedgerParchment else Color.Transparent,
        animationSpec = tween(durationMillis = 200),
        label = "bgColor"
    )

    val textColor by animateColorAsState(
        targetValue = if (isSelected) LedgerInkNavy else LedgerParchmentMutedLight,
        animationSpec = tween(durationMillis = 200),
        label = "textColor"
    )

    val iconColor by animateColorAsState(
        targetValue = if (isSelected) LedgerBrass else LedgerParchmentMutedLight,
        animationSpec = tween(durationMillis = 200),
        label = "iconColor"
    )

    Box(
        modifier = modifier
            .height(58.dp)
            .padding(horizontal = 2.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(tabHeight)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp, bottomStart = 4.dp, bottomEnd = 4.dp))
                .background(bgColor)
                .then(
                    if (isSelected) {
                        Modifier.border(
                            width = 1.dp,
                            color = LedgerBrass.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp, bottomStart = 4.dp, bottomEnd = 4.dp)
                        )
                    } else Modifier
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClick
                )
                .testTag("nav_tab_${section.name.lowercase()}"),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Active protruding top accent bar (like a binder index tab)
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .width(26.dp)
                        .height(3.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(LedgerBrass)
                )
                Spacer(modifier = Modifier.height(3.dp))
            }

            Icon(
                imageVector = getSectionIcon(section, isSelected),
                contentDescription = section.tabLabel,
                tint = iconColor,
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = section.tabLabel,
                fontSize = 11.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                fontFamily = if (isSelected) FontFamily.Serif else FontFamily.SansSerif,
                color = textColor,
                maxLines = 1
            )
        }
    }
}

@Composable
fun LedgerBinderNavRail(
    currentSection: LedgerSection,
    onSectionSelected: (LedgerSection) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxHeight()
            .width(88.dp),
        color = LedgerInkNavy,
        border = androidx.compose.foundation.BorderStroke(1.dp, LedgerBorderTanDark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 8.dp)
                .statusBarsPadding()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // App Binder Header Monogram
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(LedgerBrass),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "L",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = LedgerInkNavy
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            LedgerSection.entries.forEach { section ->
                val isSelected = currentSection == section
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onSectionSelected(section) },
                    color = if (isSelected) LedgerParchment else Color.Transparent,
                    shape = RoundedCornerShape(12.dp),
                    border = if (isSelected) androidx.compose.foundation.BorderStroke(1.dp, LedgerBrass) else null
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = getSectionIcon(section, isSelected),
                            contentDescription = section.tabLabel,
                            tint = if (isSelected) LedgerBrass else LedgerParchmentMutedLight,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = section.tabLabel,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            fontFamily = if (isSelected) FontFamily.Serif else FontFamily.SansSerif,
                            color = if (isSelected) LedgerInkNavy else LedgerParchmentMutedLight
                        )
                    }
                }
            }
        }
    }
}

private fun getSectionIcon(section: LedgerSection, isSelected: Boolean): ImageVector {
    return when (section) {
        LedgerSection.CHAT -> if (isSelected) Icons.Filled.ChatBubble else Icons.Outlined.ChatBubbleOutline
        LedgerSection.DIARY -> if (isSelected) Icons.Filled.MenuBook else Icons.Outlined.MenuBook
        LedgerSection.EVENTS -> if (isSelected) Icons.Filled.CalendarMonth else Icons.Outlined.CalendarMonth
        LedgerSection.VAULT -> if (isSelected) Icons.Filled.FolderSpecial else Icons.Outlined.FolderSpecial
        LedgerSection.TASKS -> if (isSelected) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircleOutline
    }
}
