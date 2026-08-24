# MyLyfe (`https://mylyfe.web.app`) — Comprehensive Documentation

**MyLyfe** (formerly Ledger) is a luxury antique-leather styled, offline-first personal binder, life organizer, and multimodal AI studio. It is engineered with local Room/LocalStorage data persistence, Google Authentication, Biometric Security, Event Alarms with acoustic chimes, QR peer-to-peer chat sharing, Google Drive backup, Google Gemini AI intelligence, and 9:16 vertical video social media scheduling.

---

## 📑 Table of Contents

1. [Architecture Overview](#1-architecture-overview)
2. [Hosting & Domain (`mylyfe.web.app`)](#2-hosting--domain-mylyfewebapp)
3. [App Icons & Mobile PWA Installation](#3-app-icons--mobile-pwa-installation)
4. [Security & Authentication](#4-security--authentication)
   - [Google Account Sign-In](#google-account-sign-in)
   - [Biometric Security Vault & Tactile Keypad](#biometric-security-vault--tactile-keypad)
5. [Core Sections Guide](#5-core-sections-guide)
   - [Section 1: Encrypted Chat Threads](#section-1-encrypted-chat-threads)
   - [Section 2: Journal Diary & Reflections](#section-2-journal-diary--reflections)
   - [Section 3: Calendar Events, Alarms & Audio Notifications](#section-3-calendar-events-alarms--audio-notifications)
   - [Section 4: Document Vault](#section-4-document-vault)
   - [Section 5: Task Management & Checklists](#section-5-task-management--checklists)
   - [Section 6: Google Stitch AI Studio](#section-6-google-stitch-ai-studio)
   - [Section 7: Google Gemini AI Studio](#section-7-google-gemini-ai-studio)
   - [Section 8: Gemini Video Clip Studio & Social Media Scheduler](#section-8-gemini-video-clip-studio--social-media-scheduler)
6. [Offline Peer-to-Peer QR Sharing](#6-offline-peer-to-peer-qr-sharing)
7. [Google Drive Cloud Sync & Backups](#7-google-drive-cloud-sync--backups)
8. [Google AdSense Integration Scaffolding](#8-google-adsense-integration-scaffolding)
9. [Safe Secret Management & GitHub Deployment](#9-safe-secret-management--github-deployment)

---

## 1. Architecture Overview

```
                          ┌────────────────────────┐
                          │     MyLyfe Engine      │
                          │   (Vanilla JS + PWA)   │
                          └───────────┬────────────┘
                                      │
     ┌──────────────┬─────────────────┼─────────────────┬──────────────┐
     │              │                 │                 │              │
┌────▼────┐   ┌─────▼─────┐    ┌──────▼──────┐    ┌─────▼─────┐  ┌─────▼──────┐
│ Local   │   │  Biometric│    │Google SignIn│    │ Event     │  │  Google    │
│ Storage │   │  Security │    │  Identity   │    │ Alarms    │  │  Gemini AI │
│ Engine  │   │  Vault    │    │  Services   │    │ & Audio   │  │  Studio    │
└─────────┘   └───────────┘    └─────────────┘    └───────────┘  └────────────┘
```

- **Zero-Dependency Core**: Pure HTML5, Modern CSS custom properties, and Vanilla ES6+ JavaScript.
- **Local-First Reliability**: `LedgerStore` persists all user data in browser `localStorage`.
- **PWA Service Worker (`sw.js`)**: Cache-first asset network interceptor guaranteeing instant loading offline on iOS and Android.

---

## 2. Hosting & Domain (`mylyfe.web.app`)

MyLyfe is pre-configured for **Firebase Hosting** under target `mylyfe.web.app`:
- **`firebase.json`**: SPA rewrites redirecting all routes to `/index.html` with immutable asset caching headers.
- **`.firebaserc`**: Default project set to `mylyfe`.

### Deploying to Firebase Hosting
```bash
# 1. Login to Firebase CLI
npx -y firebase-tools login

# 2. Deploy to mylyfe.web.app
npx -y firebase-tools deploy --only hosting
```

> [!TIP]
> You do **not** need to buy a custom domain. Firebase Hosting provisions free global CDN distribution with automatic SSL certificates for `https://mylyfe.web.app`.

---

## 3. App Icons & Mobile PWA Installation

- **App Icons**: High-resolution vector icons in `/web/icons/`:
  - `icon-192.svg` (192x192) & `icon-512.svg` (512x512): Brass "M" monogram on midnight navy leather texture with gold stitching.
  - `favicon.svg`: Browser tab emblem.
- **Mobile Installation (iOS & Android)**:
  1. Open `https://mylyfe.web.app` in Mobile Safari or Chrome.
  2. Tap **Share** (iOS) or **Menu ⋮** (Android).
  3. Select **"Add to Home Screen"**.
  4. The app launches fullscreen as an independent standalone mobile application.

---

## 4. Security & Authentication

### Google Account Sign-In
- Powered by **Google Identity Services** (`accounts.google.com/gsi/client`).
- Click **"Google Sign-In"** in the top-right header to link your Google profile.
- Displays user avatar, display name, and verified email address.
- Enables optional automatic Vault unlock when authenticated.

### Biometric Security Vault & Tactile Keypad
- **Overlay Shield**: Blocks access on app launch until authenticated.
- **Sensors**: Supports Touch ID, Face ID, and WebAuthn fingerprint sensors.
- **Tactile Keypad**: 4-digit PIN fallback (Default: `1234`).
- **Lock Button**: Lock the binder instantly at any time from the navigation rail footer.

---

## 5. Core Sections Guide

### Section 1: Encrypted Chat Threads 💬
- **Organized Channels**: Category tags (*Family, Work, Project, Personal, Archive*).
- **Custom Emojis & Avatars**: Distinct visual identifiers for each thread.
- **Search & Filter**: Real-time thread and message filtering.

### Section 2: Journal Diary & Reflections 📖
- **Paper Ink Aesthetics**: Lined parchment texture with elegant Newsreader serif typography.
- **Mood & Tagging**: Track mood states (*Calm, Productive, Grateful, Reflective*).
- **Gemini AI Reflection**: Generate psychological insights and journal prompts from your recent diary entries.

### Section 3: Calendar Events, Alarms & Audio Notifications 📅
- **Event Scheduling**: Date, time, location, category, and reminder alerts.
- **Acoustic Web Audio Chimes**: Synthesizes resonant dual-tone bell chimes (587.33Hz + 880Hz) via `AudioContext`.
- **Browser Push Notifications**: Desktop and phone banner alerts when an event is due.
- **Real-Time Alarm Modal**: Popup reminder with *Dismiss* and *Snooze 5 Min* actions.

### Section 4: Document Vault 🔒
- **Document Metadata**: Store policies, certificates, IDs, and financial records.
- **Expiration Tracking**: Flags upcoming document renewals.
- **Gemini Vault Intelligence**: Audit your vault for missing emergency records and policy coverage.

### Section 5: Task Management & Checklists ✓
- **Priority Tiering**: HIGH (Crimson), MED (Gold), LOW (Slate).
- **Smart Task Breakdown**: Convert high-level goals into step-by-step actionable tasks using Gemini AI.
- **Completed Archive**: Collapsible accordion of completed items.

### Section 6: Google Stitch AI Studio 🧵
- Preview and switch between downloaded Google Stitch HTML designs (`chat.html`, `diary.html`, `events.html`, `vault.html`, `tasks.html`).
- Inspect design system tokens, responsive viewports (Desktop, Tablet, Mobile), and copy raw prompts.

### Section 7: Google Gemini AI Studio ✦
- **Multimodal Context Selector**: Attach live data from Chat, Diary, Events, Vault, or Tasks into your Gemini prompts.
- **Model Switching**: Fast Gemini 2.5 Flash, Gemini 1.5 Flash, or Deep Gemini 1.5 Pro.
- **Persona Tones**: Reflective, Concise, Productivity Coach, Executive Analyst.

### Section 8: Gemini Video Clip Studio & Social Media Scheduler 🎬
- **9:16 Vertical Video Storyboards**: Turn thoughts into viral 15-second YouTube Shorts, Instagram Reels, and TikTok scripts.
- **Interactive Phone Player Simulator**: Live playback animation with animated typography overlays and 3-scene breakdown.
- **Social Media Scheduler**: Queue posts across YouTube Shorts, Instagram Reels, TikTok, X (Twitter), and LinkedIn with AI-generated viral captions and hashtags.

---

## 6. Offline Peer-to-Peer QR Sharing 📱

- **Zero-Server Sharing**: Encodes chat threads and notes into vector SVG QR Codes.
- **Offline Import**: Scan or paste payload strings on another device to merge records completely offline without internet or server access.

---

## 7. Google Drive Cloud Sync & Backups ☁️

- **One-Click Export**: Downloads an encrypted JSON backup bundle (`ledger_backup_YYYY-MM-DD.json`).
- **Archive Restore**: Upload any previous backup file to restore full binder state across all devices.

---

## 8. Google AdSense Integration Scaffolding 📢

- **Configurable Publisher ID**: Configure your `ca-pub-XXXXXXXXXXXXXXXX` in **App Settings > Google AdSense**.
- **Privacy First**: Publisher ID is stored strictly in `localStorage` without hardcoding credentials into source code.
- **Responsive Ad Placements**: Non-intrusive banner slots adhering to Google Publisher policies.

---

## 9. Safe Secret Management & GitHub Deployment 🐙

---

## 10. Multi-Language Translation Engine (12 Languages) 🌍

My Lyfe is localized for global accessibility with real-time dynamic string translation across 12 languages:
1. **English** (🇬🇧 English - Default)
2. **Spanish** (🇪🇸 Español)
3. **French** (🇫🇷 Français)
4. **German** (🇩🇪 Deutsch)
5. **Hindi** (🇮🇳 हिन्दी)
6. **Chinese** (🇨🇳 中文)
7. **Japanese** (🇯🇵 日本語)
8. **Arabic** (🇦🇪 العربية)
9. **Portuguese** (🇧🇷 Português)
10. **Russian** (🇷🇺 Русский)
11. **Italian** (🇮🇹 Italiano)
12. **Korean** (🇰🇷 한국어)

All navigation tabs, header actions, status banners, buttons, and placeholders update instantly with language persistence in `localStorage`.

---

## 11. Liquid Glassmorphism & 4 Luxury Themes 🎨

Tailored visual aesthetics matching Apple Liquid Glass and Google Stitch design principles:
- **💎 Apple Luxe (Default)**: Deep midnight obsidian acrylic with luminous brass and gold highlights.
- **🌿 Emerald Zen**: Calming sage and deep emerald glass for mindful journaling.
- **🔮 Cyber Velvet**: Rich ultraviolet glass with neon violet accents for high-energy focus.
- **🌅 Amber Sunset**: Warm bronze and amber sunset gradients with rich parchment textures.

---

## 12. Interactive Phone App Frame View (Mobile Simulator) 📱

- **Phone Mode Toggle**: Click **📱 Phone View** in the top bar to preview My Lyfe inside an authentic iPhone 16 Pro mockup frame with curved corners, bezel, Dynamic Island, and responsive bottom binder tabs.
- **Laptop / Tablet Responsive**: Automatically switches layout based on viewport width:
  - **Desktop / Laptop**: Ergonomic left binder spine rail navigation.
  - **Tablet**: Hybrid responsive layout with adaptive cards.
  - **Mobile**: Bottom tab navigation bar with haptic feedback touch states.

---

## 13. Category-Specific Offline QR Code Sharing 📱

Share specific contact cards and join threads segmented by category without internet connectivity:
- **👨‍👩‍👧‍👦 Family QR**: Encrypted Family category join link and contact payload.
- **🤝 Friends QR**: Social and peer sharing payload.
- **💼 Work QR**: Business, professional, and meeting thread invites.
- **🌿 Personal QR**: Private companion link.
- **🚀 Project QR**: Collaborative task sprint invite.

---

## 14. First-Time Setup & Configuration Wizard ✨

An interactive 5-step onboarding wizard (`#modalOnboardingWizard`):
1. **Profile & Identity**: Display name and initial luxury theme selection.
2. **Security & Biometrics**: 4-digit master PIN and Touch ID / Face ID hardware sensor enablement.
3. **Google Identity & Drive**: Account linkage for automated encrypted cloud backups.
4. **Global Language**: 12-language preference configuration.
5. **Gemini AI Studio**: Google AI Studio API key input and default model selection.

*Users can re-run the wizard at any time via the **✨ Setup Wizard** button in the header.*

---

## 15. TinyURL Social Media Campaign Generator 🔗

- **Instant Shortener**: Paste long links to video clips, diary reflections, or app download URLs (`https://mylyfe.web.app/share/daily-focus-clip`).
- **Shortened Format**: Generates `https://tinyurl.com/lyfe-XXXXX` with one-click copy to clipboard for viral promotion on YouTube Shorts, Instagram Reels, TikTok, X, LinkedIn, Facebook Reels, and Pinterest.

---

## 16. Google Play Store 2026 Standards & Legal Policies 🛡️

- **Android SDK Compliance**:
  - `compileSdk = 35` (Android 15)
  - `targetSdk = 35`
  - `minSdk = 26` (Android 8.0 Oreo+)
  - `JavaVersion.VERSION_17` (JDK 17)
  - `applicationId = "com.mylyfe.app"`
- **Mandatory Disclosures Included**:
  - **Privacy Policy Modal**: Local-first storage disclosure, zero third-party sale, encrypted Google Drive usage.
  - **Terms of Service Modal**: Software usage rights and local AI assistant boundaries.
  - **Data Safety Declaration Modal**: Transparent audit of permissions (0% telemetry, 100% user device retention).

---

## 17. Universal Item Deletion & Safety Safeguards 🗑️

Full lifecycle management and granular deletion controls across every module:
- **Journal & Diary**: Delete directly via the red `🗑️ Delete Entry` button inside the entry edit dialog or via the card quick-action icon.
- **Calendar & Alarms**: Click any scheduled event to edit or remove via `🗑️ Delete Event` with automatic alarm timer unregistration.
- **Biometric Vault**: Safely remove documents using the `🗑️ Delete Doc` button inside the document preview modal or the quick card `✕` button.
- **Hierarchical Tasks**: Instant `✕` removal on task cards or completed task cleanup.
- **Chat Threads**: Dedicated `🗑️ Delete Thread` button inside the thread header banner to permanently delete custom threads and their associated conversation histories (primary AI assistant thread is preserved).
- **Social Queue**: Remove individual queued or scheduled video clips with one-click `✕ Remove`.

---

## 18. 7-Platform Social Media Accounts Configuration 🎬

Configure and connect your personal or creator accounts directly in **App Settings (⚙️) -> Social Accounts** or via the **⚙️ Accounts** button in Social Studio:
1. 🔴 **YouTube Shorts**: Channel Name/ID, OAuth 2.0 Upload Token / API Key, and Privacy defaults (`Public`, `Unlisted`, `Private`).
2. 🟣 **Instagram Reels**: Creator Handle and Meta Graph API Access Token.
3. 🔷 **Facebook Reels**: Page ID and Page Access Token.
4. ⚫ **TikTok**: Creator Username and TikTok Open API Session Key.
5. 📌 **Pinterest**: Target Board Name and Idea Pin Access Token.
6. ⚪ **X (Twitter)**: Handle, API Key, and Bearer Token.
7. 🔵 **LinkedIn**: Profile / Company URN and UGC Video OAuth 2.0 Token.

*Includes one-click **⚡ Test** verification buttons and persistent storage in your local browser state and Google Drive backups.*

---

## 19. Official Product Showcase Website (`landing.html`) & Typography 🌐

A standalone, ultra-premium product showcase website is bundled at `web/landing.html`:
- **Liquid Glassmorphism**: Ambient glowing orbs, 32px acrylic backdrop-blur effects, and Apple-grade micro-interactions.
- **Live 3D Phone Preview**: Embedded interactive iframe mockup demonstrating live app features.
- **8 Feature Showcases**: Deep-dive cards into Chat, Diary, Calendar, Vault, Tasks, Social Studio, Translation, and Drive Sync.
- **Google Play Store Standards**: Transparent Android 15 & SDK 35 compliance metrics.
- **Curated Typography**:
  - `Cinzel Decorative` (Monograms & Titles)
  - `Outfit` (Modern UI Headings & Hero text)
  - `Plus Jakarta Sans` (Body & Crisp Interfaces)
  - `JetBrains Mono` (API Keys, Codes, Timestamps)
- **Reciprocal Navigation**: Seamless toggling between `landing.html` (Showcase Website) and `index.html` (Web App) via the top header navigation pill.

---

*My Lyfe — Crafted with luxury ink aesthetics, privacy, and cutting-edge Google AI.*

