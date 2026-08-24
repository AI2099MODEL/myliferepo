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

- **Repository**: [https://github.com/AI2099MODEL/myliferepo](https://github.com/AI2099MODEL/myliferepo)
- **Zero Secrets Rule**:
  - API Keys, Google OAuth tokens, and AdSense Publisher IDs are inputted in client settings and stored in browser `localStorage`.
  - `.gitignore` protects `.env`, `node_modules/`, and build artifacts from accidental commits.

---

*MyLyfe — Crafted with luxury ink aesthetics, privacy, and cutting-edge Google AI.*
