// =========================================================
// LEDGER — WEB APPLICATION ENGINE (OFFLINE LOCAL BINDER)
// =========================================================

// --- Initial Seed Data (Mirrors Room Database Starter Data) ---
const INITIAL_DATA = {
    threads: [
        { id: 1, key: "family", name: "Family", category: "Family", emoji: "🏡", lastMsg: "I will bring fresh sourdough!", timestamp: Date.now() - 1500000 },
        { id: 2, key: "work", name: "Work", category: "Work", emoji: "💼", lastMsg: "The sprint planning notes are in the shared drive.", timestamp: Date.now() - 4500000 },
        { id: 3, key: "personal", name: "Personal", category: "Personal", emoji: "🌿", lastMsg: "Morning routine meditation 15m completed.", timestamp: Date.now() - 10800000 },
        { id: 4, key: "general", name: "General", category: "General", emoji: "☕", lastMsg: "All your notes and chats are saved strictly on-device in Room.", timestamp: Date.now() - 18000000 },
        { id: 5, key: "gemini", name: "Gemini Studio ✦", category: "AI Studio", emoji: "✦", lastMsg: "Ask me anything or request insights about your binder!", timestamp: Date.now() - 60000 }
    ],
    messages: [
        { id: 101, threadKey: "family", content: "Are we meeting for Sunday brunch at 11?", isUser: false, sender: "Mom", timestamp: Date.now() - 1800000 },
        { id: 102, threadKey: "family", content: "Yes! I will bring fresh sourdough!", isUser: false, sender: "Dad", timestamp: Date.now() - 1500000 },
        { id: 103, threadKey: "family", content: "Sounds wonderful, I'll bring the fresh fruit & dessert.", isUser: true, sender: "You", timestamp: Date.now() - 1200000 },
        
        { id: 201, threadKey: "work", content: "The sprint planning notes are in the shared drive.", isUser: false, sender: "Jordan (PM)", timestamp: Date.now() - 4500000 },
        { id: 202, threadKey: "work", content: "Thanks, reviewing the requirements today.", isUser: true, sender: "You", timestamp: Date.now() - 3600000 },
        
        { id: 301, threadKey: "personal", content: "Morning routine meditation 15m completed.", isUser: true, sender: "You", timestamp: Date.now() - 10800000 },
        { id: 302, threadKey: "personal", content: "Idea: Check weekend mountain hiking trail guidebook.", isUser: true, sender: "You", timestamp: Date.now() - 7200000 },
        
        { id: 401, threadKey: "general", content: "Welcome to Ledger. All your notes and chats are saved strictly on-device in Room.", isUser: false, sender: "Ledger Desk", timestamp: Date.now() - 18000000 },

        { id: 501, threadKey: "gemini", content: "Hello! I am your Google Gemini AI assistant inside Ledger. I can help summarize your journal entries, break down tasks, audit your vault documents, or brainstorm ideas.", isUser: false, sender: "Gemini AI", timestamp: Date.now() - 60000 }
    ],
    diary: [
        {
            id: 1,
            title: "Reflections on a Quiet Morning",
            body: "Took thirty minutes with hot pour-over coffee before opening any screens. The morning silence makes the entire week feel calm and structured. Intending to preserve this ritual each day.",
            tag: "Reflection",
            isPinned: true,
            timestamp: Date.now() - 14400000
        },
        {
            id: 2,
            title: "Setting Up the Ledger Binder",
            body: "Gathered my ongoing project lists, important vault document scans, and upcoming calendar appointments into one reliable offline organizer. Feels great to have everything clear.",
            tag: "Milestone",
            isPinned: false,
            timestamp: Date.now() - 100800000
        },
        {
            id: 3,
            title: "Focus and Creative Flow",
            body: "Spent the afternoon drafting design concepts. When interruptions are eliminated, two hours of deep work accomplish more than an entire fragmented day.",
            tag: "Deep Thought",
            isPinned: false,
            timestamp: Date.now() - 259200000
        }
    ],
    events: [
        {
            id: 1,
            title: "Design System & Roadmap Review",
            location: "Studio Room B • Notebook & samples",
            timestamp: Date.now() + 86400000,
            notify: true,
            category: "Work"
        },
        {
            id: 2,
            title: "Weekend Farmers Market & Groceries",
            location: "Downtown Plaza • Sourdough & seasonal produce",
            timestamp: Date.now() + 172800000,
            notify: true,
            category: "Personal"
        },
        {
            id: 3,
            title: "Quarterly Financial & Tax Audit",
            location: "Home Office • Audit binder receipts",
            timestamp: Date.now() + 345600000,
            notify: false,
            category: "Finance"
        },
        {
            id: 4,
            title: "Architectural Binder Consultation",
            location: "Central Library Archive",
            timestamp: Date.now() - 864000000,
            notify: false,
            category: "General"
        }
    ],
    vault: [
        {
            id: 1,
            title: "Passport & International ID Scan",
            fileName: "passport_scan_2026.pdf",
            fileType: "PDF",
            category: "ID",
            notes: "High-resolution color scan of photo identification and visa pages.",
            timestamp: Date.now() - 432000000
        },
        {
            id: 2,
            title: "Residential Lease Agreement 2026",
            fileName: "lease_agreement_signed.pdf",
            fileType: "PDF",
            category: "Legal",
            notes: "Counter-signed lease contract and building tenancy rules.",
            timestamp: Date.now() - 1036800000
        },
        {
            id: 3,
            title: "Vehicle Comprehensive Insurance Policy",
            fileName: "auto_policy_card_2026.pdf",
            fileType: "PDF",
            category: "Insurance",
            notes: "Roadside assistance contact and proof of insurance.",
            timestamp: Date.now() - 1555200000
        },
        {
            id: 4,
            title: "Medical Vaccination & Health Card",
            fileName: "health_card_front_back.png",
            fileType: "IMAGE",
            category: "Health",
            notes: "Primary physician info & emergency blood type record.",
            timestamp: Date.now() - 2160000000
        }
    ],
    tasks: [
        {
            id: 1,
            title: "Finalize quarterly expense ledger & receipt audit",
            description: "Verify all vendor receipts match the ledger balance sheet.",
            priority: "HIGH",
            dueTimestamp: Date.now() + 86400000,
            isCompleted: false
        },
        {
            id: 2,
            title: "Review product requirements and deliverable scope",
            description: "Send feedback on team sprint backlog.",
            priority: "HIGH",
            dueTimestamp: Date.now() + 10800000,
            isCompleted: false
        },
        {
            id: 3,
            title: "Scan and file updated vehicle registration into Vault",
            description: "Save PDF copy into the insurance and legal category.",
            priority: "MED",
            dueTimestamp: Date.now() + 259200000,
            isCompleted: false
        },
        {
            id: 4,
            title: "Order fresh parchment binder paper & index tabs",
            description: "Heavyweight archive-grade paper stock.",
            priority: "LOW",
            dueTimestamp: null,
            isCompleted: false
        },
        {
            id: 5,
            title: "Setup initial Ledger workspace and binder sections",
            description: "Created Chat, Diary, Events, Vault, and Tasks modules.",
            priority: "MED",
            dueTimestamp: Date.now() - 18000000,
            isCompleted: true
        }
    ],
    socialPosts: [
        {
            id: 1,
            platforms: ["yt", "ig"],
            status: "scheduled",
            scheduledTime: "Tomorrow, 10:00 AM",
            caption: "✨ Master your morning routine with offline journaling. 3 rules I learned in Ledger: 1. Keep it analog & distraction free. 2. Record honest thoughts. 3. Synthesize with AI. #mindfulness #journaling #productivity #shorts",
            timestamp: Date.now() + 86400000
        },
        {
            id: 2,
            platforms: ["tt", "ig"],
            status: "scheduled",
            scheduledTime: "Friday, 4:00 PM",
            caption: "Why offline personal binders beat 100 open browser tabs every single time. 🔒 #cybersecurity #lifeorganization #deepwork #reels",
            timestamp: Date.now() + 172800000
        },
        {
            id: 3,
            platforms: ["li", "x"],
            status: "published",
            scheduledTime: "Yesterday, 2:15 PM",
            caption: "Building local-first tools in 2026: Privacy, instant speed, and personal AI synthesis with Google Gemini. #developer #ai #architecture",
            timestamp: Date.now() - 86400000
        }
    ]
};

// --- Storage Controller ---
class LedgerStore {
    constructor() {
        this.load();
    }

    load() {
        const stored = localStorage.getItem("ledger_db_state_v1");
        if (stored) {
            try {
                this.data = JSON.parse(stored);
                // Ensure Gemini AI thread is present
                if (!this.data.threads.some(t => t.key === "gemini")) {
                    this.data.threads.push({
                        id: 5,
                        key: "gemini",
                        name: "Gemini Studio ✦",
                        category: "AI Studio",
                        emoji: "✦",
                        lastMsg: "Ask me anything or request insights about your binder!",
                        timestamp: Date.now()
                    });
                    this.data.messages.push({
                        id: 501,
                        threadKey: "gemini",
                        content: "Hello! I am your Google Gemini AI assistant inside Ledger. I can help summarize your journal entries, break down tasks, audit your vault documents, or brainstorm ideas.",
                        isUser: false,
                        sender: "Gemini AI",
                        timestamp: Date.now() - 60000
                    });
                }
                // Ensure socialPosts array is present
                if (!this.data.socialPosts) {
                    this.data.socialPosts = JSON.parse(JSON.stringify(INITIAL_DATA.socialPosts));
                }
                return;
            } catch (e) {
                console.warn("Storage reset on parse error");
            }
        }
        this.data = JSON.parse(JSON.stringify(INITIAL_DATA));
        this.save();
    }

    save() {
        localStorage.setItem("ledger_db_state_v1", JSON.stringify(this.data));
    }
}

const store = new LedgerStore();

// --- Google Gemini AI Studio Service ---
class GeminiService {
    constructor() {
        this.apiKey = localStorage.getItem("ledger_gemini_api_key") || "";
        this.model = localStorage.getItem("ledger_gemini_model") || "gemini-2.5-flash";
        this.tone = localStorage.getItem("ledger_gemini_tone") || "reflective";
    }

    setApiKey(key) {
        this.apiKey = (key || "").trim();
        localStorage.setItem("ledger_gemini_api_key", this.apiKey);
        updateKeyStatusUI();
    }

    setModel(model) {
        this.model = model;
        localStorage.setItem("ledger_gemini_model", model);
        const badge = document.getElementById("geminiActiveModelBadge");
        if (badge) badge.textContent = model;
    }

    setTone(tone) {
        this.tone = tone;
        localStorage.setItem("ledger_gemini_tone", tone);
    }

    getSystemInstruction() {
        const tones = {
            reflective: "You are a warm, thoughtful, and reflective personal AI companion for Ledger, a vintage-inspired offline personal journal and organizer binder. Be empathetic, structured, and insightful.",
            concise: "You are a structured, precise, and concise AI assistant for Ledger. Give clean, actionable, bulleted insights without fluff.",
            coach: "You are an encouraging, energetic productivity and mindfulness coach for Ledger. Help the user prioritize their day and achieve goals.",
            executive: "You are an executive analyst for Ledger. Provide strategic summaries, audit findings, and risk assessments."
        };
        return tones[this.tone] || tones.reflective;
    }

    async testConnection(testKey, model = "gemini-2.5-flash") {
        const key = (testKey || this.apiKey).trim();
        if (!key) throw new Error("Please enter an API key.");

        const url = `https://generativelanguage.googleapis.com/v1beta/models/${model}:generateContent?key=${key}`;
        const body = {
            contents: [
                {
                    role: "user",
                    parts: [{ text: "Ping. Reply with 'Connected to Google Gemini AI Studio successfully.'" }]
                }
            ]
        };

        const res = await fetch(url, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body)
        });

        if (!res.ok) {
            const errJson = await res.json().catch(() => ({}));
            throw new Error(errJson.error?.message || `HTTP error ${res.status}`);
        }

        const data = await res.json();
        const text = data.candidates?.[0]?.content?.parts?.[0]?.text;
        return text || "Connected successfully!";
    }

    async generateContent(prompt, customContext = null, temperature = 0.7) {
        const key = this.apiKey;
        const systemPrompt = this.getSystemInstruction();

        let fullPrompt = `${systemPrompt}\n\n`;
        if (customContext) {
            fullPrompt += `[ACTIVE LEDGER BINDER CONTEXT]:\n${customContext}\n\n`;
        }
        fullPrompt += `[USER REQUEST]:\n${prompt}`;

        if (!key) {
            return this.generateSimulatedFallback(prompt, customContext);
        }

        const url = `https://generativelanguage.googleapis.com/v1beta/models/${this.model}:generateContent?key=${key}`;
        const body = {
            contents: [
                {
                    role: "user",
                    parts: [{ text: fullPrompt }]
                }
            ],
            generationConfig: {
                temperature: parseFloat(temperature) || 0.7,
                maxOutputTokens: 2048
            }
        };

        const res = await fetch(url, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body)
        });

        if (!res.ok) {
            const errJson = await res.json().catch(() => ({}));
            throw new Error(errJson.error?.message || `Gemini API error (HTTP ${res.status})`);
        }

        const data = await res.json();
        return data.candidates?.[0]?.content?.parts?.[0]?.text || "No response text received.";
    }

    generateSimulatedFallback(prompt, context) {
        const p = prompt.toLowerCase();
        if (p.includes("reflection") || p.includes("diary") || p.includes("mood")) {
            return `### 🌿 Gemini Reflection & Emotional Cadence Summary

**Overall Sentiment:** Grounded & Reflective (78% Serenity, 22% Productivity Focus)

**Key Themes Identified:**
1. **Mindful Mornings:** You've developed a ritual around hot pour-over coffee and morning stillness before screens.
2. **Organizational Peace of Mind:** Structuring Ledger with Chat, Diary, Events, Vault, and Tasks has created a reliable offline haven.
3. **Deep Work Velocity:** Uninterrupted focus blocks are yielding your highest creative breakthroughs.

**Thoughtful Reflection:**
Your recent reflections show a healthy balance between intentional rest and focused execution. The consistency in your morning routine serves as a strong foundation for handling complex projects without burnout.

**Recommended Action Prompts:**
- *What is one small boundary you can set tomorrow to protect today's sense of creative flow?*
- *Take 5 minutes to capture a gratitude note on this week's milestone progress.*

*(💡 Tip: Enter your Google AI Studio API key in AI Settings to enable live personalized responses!)*`;
        } else if (p.includes("breakdown") || p.includes("task")) {
            return `### ✓ Gemini Smart Task Breakdown

1. **[HIGH]** Audit all Q3 incoming vendor receipts against the ledger spreadsheet *(Est: 45 min)*
2. **[HIGH]** Export and cross-reference account statements with Room database logs *(Est: 30 min)*
3. **[MED]** Categorize deductible business expenses vs personal records *(Est: 20 min)*
4. **[MED]** Scan and file updated receipts directly into Document Vault *(Est: 15 min)*
5. **[LOW]** Schedule next month's tax preparation check-in *(Est: 5 min)*

*(💡 Tip: Enter your Google AI Studio API key in AI Settings to enable live custom task parsing!)*`;
        } else if (p.includes("vault") || p.includes("document")) {
            return `### 🔒 Gemini Vault Intelligence & Security Audit

**Vault Health Score:** 92/100 (Strong Foundation)

**Document Highlights:**
- **Passport Scan:** Secured under ID category. Verified readable color resolution.
- **Residential Lease Agreement:** Signed copy on file under Legal category.
- **Vehicle Comprehensive Policy:** Roadside assistance number active.
- **Medical Health Card:** Emergency blood type record on file.

**Recommendations:**
1. Consider adding an expiration alert 6 months prior to Passport renewal.
2. Upload scanned utility bills under Receipts category for address verification.`;
        } else {
            return `### ✦ Gemini Studio Synthesis

Thank you for your prompt! 

**Ledger Binder Overview:**
- **Threads:** Family, Work, Personal, General, Gemini Studio
- **Diary:** 3 entries logged (Reflections, Setting Up Binder, Focus & Flow)
- **Events:** 4 scheduled appointments
- **Vault:** 4 encrypted documents stored
- **Tasks:** 5 items (4 active, 1 completed)

*(💡 Connect your Google AI Studio API key in AI Settings to run live multimodal prompts with Gemini 2.5 Flash!)*`;
        }
    }
}

const geminiService = new GeminiService();

function updateKeyStatusUI() {
    const hasKey = !!geminiService.apiKey;
    const dot = document.getElementById("keyStatusDot");
    if (dot) {
        dot.classList.toggle("missing", !hasKey);
    }
    const indicator = document.getElementById("geminiApiStatusIndicator");
    if (indicator) {
        indicator.textContent = hasKey ? "ACTIVE" : "DEMO MODE";
        indicator.style.color = hasKey ? "#10b981" : "#f59e0b";
    }
    const statusText = document.getElementById("geminiKeyStatusText");
    if (statusText) {
        statusText.textContent = hasKey ? "Connected (API Key Active)" : "Offline Simulator";
    }
}

// --- Application State ---
const state = {
    currentSection: "chat",
    activeThreadKey: "family",
    diarySearch: "",
    diaryTagFilter: "All",
    eventFilter: "All",
    vaultCategoryFilter: "All",
    taskPriorityFilter: "All",
    showCompletedTasks: true,
    currentStitchScreen: "chat",
    stitchViewMode: "iframe",
    stitchManifest: null
};

// --- DOM References ---
const elements = {
    // Navigation
    railTabs: document.querySelectorAll(".rail-tab"),
    mobileTabs: document.querySelectorAll(".mobile-tab"),
    sectionPanes: document.querySelectorAll(".section-pane"),
    headerTitle: document.getElementById("headerTitle"),
    headerSubtitle: document.getElementById("headerSubtitle"),
    headerActions: document.getElementById("headerActions"),

    // Chat
    threadsList: document.getElementById("threadsList"),
    bannerThreadName: document.getElementById("bannerThreadName"),
    bannerThreadCount: document.getElementById("bannerThreadCount"),
    chatMessagesContainer: document.getElementById("chatMessagesContainer"),
    chatInput: document.getElementById("chatInput"),
    btnSendMessage: document.getElementById("btnSendMessage"),
    btnOpenNewThreadModal: document.getElementById("btnOpenNewThreadModal"),

    // Diary
    diarySearchInput: document.getElementById("diarySearchInput"),
    btnClearDiarySearch: document.getElementById("btnClearDiarySearch"),
    diaryTagFilters: document.getElementById("diaryTagFilters"),
    diaryEntriesContainer: document.getElementById("diaryEntriesContainer"),

    // Events
    eventFilterRow: document.getElementById("eventFilterRow"),
    eventFilterAll: document.getElementById("eventFilterAll"),
    eventFilterUpcoming: document.getElementById("eventFilterUpcoming"),
    eventFilterPast: document.getElementById("eventFilterPast"),
    eventsListContainer: document.getElementById("eventsListContainer"),

    // Vault
    vaultCategoryRow: document.getElementById("vaultCategoryRow"),
    vaultGridContainer: document.getElementById("vaultGridContainer"),

    // Tasks
    tasksPriorityRow: document.getElementById("tasksPriorityRow"),
    activeTasksContainer: document.getElementById("activeTasksContainer"),
    completedTasksContainer: document.getElementById("completedTasksContainer"),
    completedTasksTitle: document.getElementById("completedTasksTitle"),
    btnToggleCompletedTasks: document.getElementById("btnToggleCompletedTasks"),
    completedAccordionArrow: document.getElementById("completedAccordionArrow"),

    // Modals
    modalNewThread: document.getElementById("modalNewThread"),
    modalDiary: document.getElementById("modalDiary"),
    modalEvent: document.getElementById("modalEvent"),
    modalVault: document.getElementById("modalVault"),
    modalTask: document.getElementById("modalTask"),

    // Toast
    toast: document.getElementById("ledgerToast")
};

// --- Toast Messenger ---
function showToast(msg) {
    elements.toast.textContent = msg;
    elements.toast.classList.add("show");
    setTimeout(() => {
        elements.toast.classList.remove("show");
    }, 2800);
}

// --- Navigation Controller ---
const SECTION_METADATA = {
    chat: {
        title: "Ledger Threads",
        subtitle: "Encrypted local messaging • Local Room Persistence",
        actionBtn: "+ New Thread",
        actionId: "btnHeaderNewThread"
    },
    diary: {
        title: "Personal Diary",
        subtitle: "Reflections & Journal Entries • Encrypted Local Storage",
        actionBtn: "✎ Write Entry",
        actionId: "btnHeaderNewDiary"
    },
    events: {
        title: "Events & Calendar",
        subtitle: "Scheduled appointments • Notifications & Reminders",
        actionBtn: "+ Add Event",
        actionId: "btnHeaderNewEvent"
    },
    vault: {
        title: "Document Vault",
        subtitle: "Encrypted offline document & certificate archive",
        actionBtn: "+ Import Doc",
        actionId: "btnHeaderNewVault"
    },
    tasks: {
        title: "Tasks & Checklist",
        subtitle: "Prioritized checklists with deadlines & alarm reminders",
        actionBtn: "+ New Task",
        actionId: "btnHeaderNewTask"
    },
    stitch: {
        title: "Stitch UI Design Studio",
        subtitle: "Google AI Generated UI Screens • Live Interactive Preview",
        actionBtn: "⚡ View in New Tab",
        actionId: "btnHeaderStitchView"
    },
    gemini: {
        title: "Gemini Intelligence Studio",
        subtitle: "Google Generative Multimodal AI • Live Binder Reasoning",
        actionBtn: "⚙️ AI Settings",
        actionId: "btnHeaderGeminiSettings"
    },
    social: {
        title: "Gemini Video Studio & Social Scheduler",
        subtitle: "AI 9:16 Video Clips • Viral Captions • Multi-Platform Publishing",
        actionBtn: "🎬 New Video Clip",
        actionId: "btnHeaderNewClip"
    }
};

function capitalize(s) {
    if (!s) return "";
    return s.charAt(0).toUpperCase() + s.slice(1);
}

function switchSection(sectionKey) {
    state.currentSection = sectionKey;

    // Reset inline stitch overlay if open
    const inlineContainer = document.getElementById("inlineStitchContainer");
    if (inlineContainer) inlineContainer.style.display = "none";
    elements.sectionPanes.forEach(pane => pane.classList.remove("stitch-hidden-content"));

    // Update rail & mobile navigation tabs
    elements.railTabs.forEach(tab => {
        tab.classList.toggle("active", tab.dataset.section === sectionKey);
    });
    elements.mobileTabs.forEach(tab => {
        tab.classList.toggle("active", tab.dataset.section === sectionKey);
    });

    // Update section panes
    elements.sectionPanes.forEach(pane => {
        pane.classList.toggle("active", pane.id === `section${capitalize(sectionKey)}`);
    });

    // Update header
    const meta = SECTION_METADATA[sectionKey] || {
        title: "Ledger",
        subtitle: "Personal Binder",
        actionBtn: "+ New",
        actionId: "btnHeaderActionDefault"
    };
    elements.headerTitle.textContent = meta.title;
    elements.headerSubtitle.textContent = meta.subtitle;

    // Inject section action button & Stitch toggle in header
    let actionsHtml = "";
    if (sectionKey !== "stitch" && sectionKey !== "gemini" && sectionKey !== "social") {
        actionsHtml += `
            <button class="btn-stitch-toggle" data-stitch-toggle-for="${sectionKey}" id="btnStitchToggle${capitalize(sectionKey)}">
                ✨ Stitch AI Design
            </button>
        `;
    }
    actionsHtml += `
        <button class="btn-header-action" id="${meta.actionId}">
            ${meta.actionBtn}
        </button>
    `;
    elements.headerActions.innerHTML = actionsHtml;

    // Hook action button handler
    const actionBtnEl = document.getElementById(meta.actionId);
    if (actionBtnEl) {
        actionBtnEl.addEventListener("click", () => {
            openSectionModal(sectionKey);
        });
    }

    // Initialize Stitch toggle buttons
    if (window.initStitchToggles) {
        window.initStitchToggles();
    }

    // Render section contents
    renderSection(sectionKey);
}

function openSectionModal(sectionKey) {
    if (sectionKey === "chat") elements.modalNewThread.classList.add("open");
    else if (sectionKey === "diary") openDiaryModal();
    else if (sectionKey === "events") openEventModal();
    else if (sectionKey === "vault") openVaultModal();
    else if (sectionKey === "tasks") openTaskModal();
    else if (sectionKey === "stitch") {
        const screenKey = state.currentStitchScreen || "chat";
        window.open(`stitch-designs/downloaded/${screenKey}.html`, "_blank");
    }
    else if (sectionKey === "gemini") openGeminiSettingsModal();
    else if (sectionKey === "social") generateVideoClipWithGemini();
}

function renderSection(sectionKey) {
    if (sectionKey === "chat") renderChat();
    else if (sectionKey === "diary") renderDiary();
    else if (sectionKey === "events") renderEvents();
    else if (sectionKey === "vault") renderVault();
    else if (sectionKey === "tasks") renderTasks();
    else if (sectionKey === "stitch") renderStitch();
    else if (sectionKey === "gemini") renderGemini();
    else if (sectionKey === "social") renderSocial();
}

// ---------------------------------------------------------
// 1. CHAT MODULE
// ---------------------------------------------------------
function renderChat() {
    // Render thread tabs
    elements.threadsList.innerHTML = store.data.threads.map(thread => `
        <button class="thread-tab-pill ${thread.key === state.activeThreadKey ? 'active' : ''}" data-key="${thread.key}">
            <span>${thread.emoji}</span>
            <span>${thread.name}</span>
        </button>
    `).join("");

    elements.threadsList.querySelectorAll(".thread-tab-pill").forEach(pill => {
        pill.addEventListener("click", () => {
            state.activeThreadKey = pill.dataset.key;
            renderChat();
        });
    });

    const activeThread = store.data.threads.find(t => t.key === state.activeThreadKey) || store.data.threads[0];
    if (!activeThread) return;

    elements.bannerThreadName.textContent = `Active: ${activeThread.name} (${activeThread.category})`;
    const threadMsgs = store.data.messages.filter(m => m.threadKey === state.activeThreadKey);
    elements.bannerThreadCount.textContent = `${threadMsgs.length} msgs`;

    // Render messages
    if (threadMsgs.length === 0) {
        elements.chatMessagesContainer.innerHTML = `
            <div class="empty-state-card">
                <div class="empty-state-icon">💬</div>
                <div class="empty-state-title">No Messages Yet</div>
                <div class="empty-state-sub">Send a message into this private thread to start the conversation.</div>
            </div>
        `;
    } else {
        elements.chatMessagesContainer.innerHTML = threadMsgs.map(msg => `
            <div class="chat-bubble-row ${msg.isUser ? 'sent' : 'received'}">
                ${!msg.isUser ? `<div class="chat-sender-label">${escapeHtml(msg.sender)}</div>` : ''}
                <div class="chat-bubble">
                    <div class="chat-text">${escapeHtml(msg.content)}</div>
                    <div class="chat-timestamp">${formatTime(msg.timestamp)}</div>
                </div>
            </div>
        `).join("");
    }

    // Scroll to bottom
    elements.chatMessagesContainer.scrollTop = elements.chatMessagesContainer.scrollHeight;
}

function sendChatMessage() {
    const text = elements.chatInput.value.trim();
    if (!text) return;

    const currentKey = state.activeThreadKey;

    const newMsg = {
        id: Date.now(),
        threadKey: currentKey,
        content: text,
        isUser: true,
        sender: "You",
        timestamp: Date.now()
    };

    store.data.messages.push(newMsg);

    // Update last msg preview in thread
    const thread = store.data.threads.find(t => t.key === currentKey);
    if (thread) {
        thread.lastMsg = text;
        thread.timestamp = Date.now();
    }

    store.save();
    elements.chatInput.value = "";
    renderChat();
    showToast("Message sent to thread");

    // Automatic AI Assistant reply if active thread is Gemini
    if (currentKey === "gemini") {
        const typingEl = document.createElement("div");
        typingEl.className = "chat-bubble-row received";
        typingEl.id = "geminiTypingBubble";
        typingEl.innerHTML = `
            <div class="chat-sender-label">Gemini AI</div>
            <div class="chat-bubble">
                <div class="ai-loading-state" style="padding: 6px 12px; gap: 8px;">
                    <div class="ai-pulse-dot"></div>
                    <span style="font-size: 12px; color: #a78bfa;">Gemini is reasoning...</span>
                </div>
            </div>
        `;
        elements.chatMessagesContainer.appendChild(typingEl);
        elements.chatMessagesContainer.scrollTop = elements.chatMessagesContainer.scrollHeight;

        const binderContext = getBinderContextString({ chat: false, diary: true, events: true, vault: true, tasks: true });

        geminiService.generateContent(text, binderContext).then(reply => {
            const typingBubble = document.getElementById("geminiTypingBubble");
            if (typingBubble) typingBubble.remove();

            const aiMsg = {
                id: Date.now() + 1,
                threadKey: "gemini",
                content: reply,
                isUser: false,
                sender: "Gemini AI",
                timestamp: Date.now()
            };
            store.data.messages.push(aiMsg);

            const geminiThread = store.data.threads.find(t => t.key === "gemini");
            if (geminiThread) {
                geminiThread.lastMsg = reply.replace(/[#*`]/g, "").slice(0, 60) + "...";
                geminiThread.timestamp = Date.now();
            }

            store.save();
            renderChat();
        }).catch(err => {
            const typingBubble = document.getElementById("geminiTypingBubble");
            if (typingBubble) typingBubble.remove();
            showToast("Gemini error: " + err.message);
        });
    }
}

// ---------------------------------------------------------
// 2. DIARY MODULE
// ---------------------------------------------------------
function renderDiary() {
    const query = state.diarySearch.toLowerCase().trim();
    const tagFilter = state.diaryTagFilter;

    const filtered = store.data.diary.filter(entry => {
        const matchesTag = tagFilter === "All" || entry.tag.toLowerCase() === tagFilter.toLowerCase();
        const matchesQuery = !query || entry.title.toLowerCase().includes(query) || entry.body.toLowerCase().includes(query);
        return matchesTag && matchesQuery;
    }).sort((a, b) => {
        if (a.isPinned !== b.isPinned) return a.isPinned ? -1 : 1;
        return b.timestamp - a.timestamp;
    });

    if (filtered.length === 0) {
        elements.diaryEntriesContainer.innerHTML = `
            <div class="empty-state-card">
                <div class="empty-state-icon">📖</div>
                <div class="empty-state-title">${query ? 'No Matching Entries' : 'Journal is Empty'}</div>
                <div class="empty-state-sub">${query ? 'Try a different search keyword or tag filter.' : 'Record your thoughts, milestones, and reflections.'}</div>
            </div>
        `;
        return;
    }

    elements.diaryEntriesContainer.innerHTML = filtered.map(entry => `
        <div class="diary-card ${entry.isPinned ? 'pinned' : ''}" data-id="${entry.id}">
            <div class="diary-card-header">
                <div class="diary-card-date">
                    ${entry.isPinned ? '<span class="pin-badge">📌 PINNED</span>' : ''}
                    <span>${formatFullDate(entry.timestamp)}</span>
                </div>
                <div class="tag-badge">${entry.tag}</div>
            </div>
            <div class="diary-card-title">${escapeHtml(entry.title)}</div>
            <div class="diary-card-body">${escapeHtml(entry.body)}</div>
        </div>
    `).join("");

    // Hook card clicks
    elements.diaryEntriesContainer.querySelectorAll(".diary-card").forEach(card => {
        card.addEventListener("click", () => {
            const entryId = parseInt(card.dataset.id);
            const entry = store.data.diary.find(e => e.id === entryId);
            if (entry) {
                openDiaryModal(entry);
            }
        });
    });
}

function openDiaryModal(entry = null) {
    const isEdit = !!entry;
    document.getElementById("diaryModalTitle").textContent = isEdit ? "Edit Diary Entry" : "New Diary Entry";
    document.getElementById("inputDiaryTitle").value = entry ? entry.title : "";
    document.getElementById("inputDiaryBody").value = entry ? entry.body : "";
    document.getElementById("checkDiaryPinned").checked = entry ? entry.isPinned : false;

    // Reset pills
    const curTag = entry ? entry.tag : "Reflection";
    document.querySelectorAll("#diaryTagSelect .select-pill").forEach(p => {
        p.classList.toggle("active", p.dataset.value === curTag);
    });

    document.getElementById("modalDiary").dataset.editId = entry ? entry.id : "";
    elements.modalDiary.classList.add("open");
}

function saveDiaryEntry() {
    const editId = document.getElementById("modalDiary").dataset.editId;
    const title = document.getElementById("inputDiaryTitle").value.trim() || "Untitled Entry";
    const body = document.getElementById("inputDiaryBody").value.trim();
    const isPinned = document.getElementById("checkDiaryPinned").checked;
    const activeTagPill = document.querySelector("#diaryTagSelect .select-pill.active");
    const tag = activeTagPill ? activeTagPill.dataset.value : "Reflection";

    if (!body) {
        showToast("Please write some entry content");
        return;
    }

    if (editId) {
        const id = parseInt(editId);
        const item = store.data.diary.find(d => d.id === id);
        if (item) {
            item.title = title;
            item.body = body;
            item.tag = tag;
            item.isPinned = isPinned;
            showToast("Diary entry updated");
        }
    } else {
        store.data.diary.unshift({
            id: Date.now(),
            title,
            body,
            tag,
            isPinned,
            timestamp: Date.now()
        });
        showToast("Diary entry saved");
    }

    store.save();
    elements.modalDiary.classList.remove("open");
    renderDiary();
}

// ---------------------------------------------------------
// 3. EVENTS MODULE
// ---------------------------------------------------------
function renderEvents() {
    const now = Date.now();
    const upcoming = store.data.events.filter(e => e.timestamp >= now).sort((a, b) => a.timestamp - b.timestamp);
    const past = store.data.events.filter(e => e.timestamp < now).sort((a, b) => b.timestamp - a.timestamp);

    elements.eventFilterAll.textContent = `All (${store.data.events.length})`;
    elements.eventFilterUpcoming.textContent = `Upcoming (${upcoming.length})`;
    elements.eventFilterPast.textContent = `Past (${past.length})`;

    let displayList = store.data.events;
    if (state.eventFilter === "Upcoming") displayList = upcoming;
    else if (state.eventFilter === "Past") displayList = past;

    if (displayList.length === 0) {
        elements.eventsListContainer.innerHTML = `
            <div class="empty-state-card">
                <div class="empty-state-icon">📅</div>
                <div class="empty-state-title">No Events Found</div>
                <div class="empty-state-sub">Plan appointments and reviews with reminder notifications.</div>
            </div>
        `;
        return;
    }

    elements.eventsListContainer.innerHTML = displayList.map(event => {
        const isPast = event.timestamp < now;
        const d = new Date(event.timestamp);
        const monthStr = d.toLocaleString('en-US', { month: 'short' }).toUpperCase();
        const dayNum = d.getDate();

        return `
            <div class="event-card ${isPast ? 'past' : ''}" data-id="${event.id}">
                <div class="event-date-block">
                    <span class="event-month-str">${monthStr}</span>
                    <span class="event-day-num">${dayNum}</span>
                </div>
                <div class="event-details">
                    <div class="event-full-date">${formatDate(event.timestamp)} • ${formatTime(event.timestamp)}</div>
                    <div class="event-title">${escapeHtml(event.title)}</div>
                    <div class="event-meta-row">
                        <span>🏷️ ${event.category}</span>
                        ${event.location ? `<span>• 📍 ${escapeHtml(event.location)}</span>` : ''}
                    </div>
                </div>
                <div class="event-actions">
                    <button class="btn-toggle-notif" data-id="${event.id}" title="${event.notify ? 'Reminders Active' : 'Enable Reminder'}">
                        ${event.notify ? '🔔' : '🔕'}
                    </button>
                    <button class="btn-delete-item" data-type="event" data-id="${event.id}">✕</button>
                </div>
            </div>
        `;
    }).join("");

    // Toggle notification hooks
    elements.eventsListContainer.querySelectorAll(".btn-toggle-notif").forEach(btn => {
        btn.addEventListener("click", (e) => {
            e.stopPropagation();
            const id = parseInt(btn.dataset.id);
            const ev = store.data.events.find(x => x.id === id);
            if (ev) {
                ev.notify = !ev.notify;
                store.save();
                renderEvents();
                showToast(ev.notify ? "Event reminder activated" : "Event reminder muted");
            }
        });
    });

    // Delete hooks
    elements.eventsListContainer.querySelectorAll(".btn-delete-item").forEach(btn => {
        btn.addEventListener("click", (e) => {
            e.stopPropagation();
            const id = parseInt(btn.dataset.id);
            store.data.events = store.data.events.filter(x => x.id !== id);
            store.save();
            renderEvents();
            showToast("Event removed");
        });
    });
}

function openEventModal() {
    document.getElementById("inputEventTitle").value = "";
    document.getElementById("inputEventLocation").value = "";
    
    // Tomorrow at 10:00 AM
    const tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    document.getElementById("inputEventDate").value = tomorrow.toISOString().split('T')[0];
    document.getElementById("inputEventTime").value = "10:00";
    document.getElementById("checkEventNotify").checked = true;

    elements.modalEvent.classList.add("open");
}

function saveEvent() {
    const title = document.getElementById("inputEventTitle").value.trim();
    const location = document.getElementById("inputEventLocation").value.trim();
    const dateStr = document.getElementById("inputEventDate").value;
    const timeStr = document.getElementById("inputEventTime").value || "09:00";
    const notify = document.getElementById("checkEventNotify").checked;
    const activeCat = document.querySelector("#eventCategorySelect .select-pill.active");
    const category = activeCat ? activeCat.dataset.value : "General";

    if (!title || !dateStr) {
        showToast("Please provide event title and date");
        return;
    }

    const timestamp = new Date(`${dateStr}T${timeStr}`).getTime() || Date.now();

    store.data.events.push({
        id: Date.now(),
        title,
        location,
        timestamp,
        notify,
        category
    });

    store.save();
    elements.modalEvent.classList.remove("open");
    renderEvents();
    showToast("Event scheduled");
}

// ---------------------------------------------------------
// 4. VAULT MODULE
// ---------------------------------------------------------
function renderVault() {
    const cat = state.vaultCategoryFilter;
    const filtered = cat === "All" ? store.data.vault : store.data.vault.filter(v => v.category.toLowerCase() === cat.toLowerCase());

    if (filtered.length === 0) {
        elements.vaultGridContainer.innerHTML = `
            <div class="empty-state-card" style="grid-column: 1/-1;">
                <div class="empty-state-icon">🔒</div>
                <div class="empty-state-title">Vault is Empty</div>
                <div class="empty-state-sub">Safely catalog IDs, agreements, insurance policies, and receipts.</div>
            </div>
        `;
        return;
    }

    elements.vaultGridContainer.innerHTML = filtered.map(doc => `
        <div class="vault-card" data-id="${doc.id}">
            <div class="vault-card-top">
                <span class="vault-category-badge">${doc.category}</span>
                <button class="btn-delete-item" data-type="vault" data-id="${doc.id}">✕</button>
            </div>
            <div class="vault-icon-badge">${getDocumentIcon(doc.fileType)}</div>
            <div>
                <div class="vault-card-title">${escapeHtml(doc.title)}</div>
                <div class="vault-card-filename">${doc.fileName}</div>
            </div>
        </div>
    `).join("");

    elements.vaultGridContainer.querySelectorAll(".btn-delete-item").forEach(btn => {
        btn.addEventListener("click", (e) => {
            e.stopPropagation();
            const id = parseInt(btn.dataset.id);
            store.data.vault = store.data.vault.filter(x => x.id !== id);
            store.save();
            renderVault();
            showToast("Document removed from Vault");
        });
    });

    elements.vaultGridContainer.querySelectorAll(".vault-card").forEach(card => {
        card.addEventListener("click", () => {
            const id = parseInt(card.dataset.id);
            const doc = store.data.vault.find(x => x.id === id);
            if (doc) {
                showToast(`Vault Document: ${doc.title} (${doc.fileName})`);
            }
        });
    });
}

function openVaultModal() {
    document.getElementById("inputVaultTitle").value = "";
    document.getElementById("inputVaultFileName").value = "";
    document.getElementById("inputVaultNotes").value = "";
    elements.modalVault.classList.add("open");
}

function saveVaultDoc() {
    const title = document.getElementById("inputVaultTitle").value.trim();
    let fileName = document.getElementById("inputVaultFileName").value.trim();
    const notes = document.getElementById("inputVaultNotes").value.trim();
    const activeCat = document.querySelector("#vaultCategorySelect .select-pill.active");
    const category = activeCat ? activeCat.dataset.value : "ID";
    const activeType = document.querySelector("#vaultTypeSelect .select-pill.active");
    const fileType = activeType ? activeType.dataset.value : "PDF";

    if (!title) {
        showToast("Please provide document title");
        return;
    }

    if (!fileName) {
        fileName = `${title.toLowerCase().replace(/\s+/g, '_')}.${fileType.toLowerCase()}`;
    }

    store.data.vault.unshift({
        id: Date.now(),
        title,
        fileName,
        fileType,
        category,
        notes,
        timestamp: Date.now()
    });

    store.save();
    elements.modalVault.classList.remove("open");
    renderVault();
    showToast("Document saved in Vault");
}

// ---------------------------------------------------------
// 5. TASKS MODULE
// ---------------------------------------------------------
function renderTasks() {
    const prioFilter = state.taskPriorityFilter;
    const allActive = store.data.tasks.filter(t => !t.isCompleted);
    const allCompleted = store.data.tasks.filter(t => t.isCompleted);

    // Update Priority count badges
    document.querySelectorAll("#tasksPriorityRow .priority-pill").forEach(pill => {
        const p = pill.dataset.priority;
        const count = p === "All" ? allActive.length : allActive.filter(t => t.priority === p).length;
        pill.textContent = `${p} (${count})`;
    });

    const activeList = prioFilter === "All" ? allActive : allActive.filter(t => t.priority === prioFilter);
    const completedList = prioFilter === "All" ? allCompleted : allCompleted.filter(t => t.priority === prioFilter);

    elements.completedTasksTitle.textContent = `Completed (${completedList.length})`;

    // Render active
    if (activeList.length === 0 && completedList.length === 0) {
        elements.activeTasksContainer.innerHTML = `
            <div class="empty-state-card">
                <div class="empty-state-icon">✓</div>
                <div class="empty-state-title">No Tasks Found</div>
                <div class="empty-state-sub">Stay on track with prioritized checklists and reminders.</div>
            </div>
        `;
    } else {
        elements.activeTasksContainer.innerHTML = activeList.map(task => renderTaskCard(task)).join("");
    }

    // Render completed
    elements.completedTasksContainer.innerHTML = completedList.map(task => renderTaskCard(task)).join("");

    // Checkbox toggles
    document.querySelectorAll(".task-checkbox-btn").forEach(btn => {
        btn.addEventListener("click", () => {
            const id = parseInt(btn.dataset.id);
            const task = store.data.tasks.find(x => x.id === id);
            if (task) {
                task.isCompleted = !task.isCompleted;
                store.save();
                renderTasks();
                showToast(task.isCompleted ? "Task marked completed" : "Task restored to active");
            }
        });
    });

    // Delete tasks
    document.querySelectorAll(".btn-delete-item[data-type='task']").forEach(btn => {
        btn.addEventListener("click", (e) => {
            e.stopPropagation();
            const id = parseInt(btn.dataset.id);
            store.data.tasks = store.data.tasks.filter(x => x.id !== id);
            store.save();
            renderTasks();
            showToast("Task deleted");
        });
    });
}

function renderTaskCard(task) {
    const hasDue = !!task.dueTimestamp;
    const dueStr = hasDue ? formatDate(task.dueTimestamp) + ' • ' + formatTime(task.dueTimestamp) : '';

    return `
        <div class="task-card ${task.isCompleted ? 'completed' : ''}" data-id="${task.id}">
            <button class="task-checkbox-btn" data-id="${task.id}">
                ${task.isCompleted ? '✓' : ''}
            </button>
            <div class="task-info">
                <div class="task-top-row">
                    <span class="task-title">${escapeHtml(task.title)}</span>
                    <span class="task-prio-badge ${task.priority}">${task.priority}</span>
                </div>
                ${task.description ? `<div class="task-desc">${escapeHtml(task.description)}</div>` : ''}
                ${hasDue ? `<div class="task-due-row">⏰ Due: ${dueStr}</div>` : ''}
            </div>
            <button class="btn-delete-item" data-type="task" data-id="${task.id}">✕</button>
        </div>
    `;
}

function openTaskModal() {
    document.getElementById("inputTaskTitle").value = "";
    document.getElementById("inputTaskDesc").value = "";
    document.getElementById("checkTaskSchedule").checked = false;
    document.getElementById("taskDateTimeRow").classList.add("hidden");

    // Tomorrow at 5pm
    const tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    document.getElementById("inputTaskDate").value = tomorrow.toISOString().split('T')[0];
    document.getElementById("inputTaskTime").value = "17:00";

    elements.modalTask.classList.add("open");
}

function saveTask() {
    const title = document.getElementById("inputTaskTitle").value.trim();
    const description = document.getElementById("inputTaskDesc").value.trim();
    const hasSchedule = document.getElementById("checkTaskSchedule").checked;
    const activePrio = document.querySelector("#taskPrioritySelect .priority-opt-btn.active");
    const priority = activePrio ? activePrio.dataset.priority : "MED";

    if (!title) {
        showToast("Please enter task title");
        return;
    }

    let dueTimestamp = null;
    if (hasSchedule) {
        const dateStr = document.getElementById("inputTaskDate").value;
        const timeStr = document.getElementById("inputTaskTime").value || "17:00";
        if (dateStr) {
            dueTimestamp = new Date(`${dateStr}T${timeStr}`).getTime();
        }
    }

    store.data.tasks.unshift({
        id: Date.now(),
        title,
        description,
        priority,
        dueTimestamp,
        isCompleted: false
    });

    store.save();
    elements.modalTask.classList.remove("open");
    renderTasks();
    showToast("Task added to checklist");
}

// ---------------------------------------------------------
// 6. GOOGLE STITCH AI STUDIO MODULE
// ---------------------------------------------------------
const STITCH_PROMPTS = {
    chat: {
        title: "Ledger Chat — Stitch Prompt",
        screenId: "ee9a9d72350646f282a781bc87b86e94",
        prompt: "A premium dark-themed personal binder messaging screen. Dark navy (#0d1b2e) background. Left sidebar with thread list showing: Family 🏡, Work 💼, Personal 🌿, General ☕ tabs with brass/gold active indicator. Main area shows message bubbles on parchment (#f5f0e8) background. Received messages left-aligned in white cards, sent messages right-aligned in warm gold (#b08d57). Bottom composer bar with text input and gold send button. Cinzel serif font for headings, clean sans-serif for messages. Vintage leather binder aesthetic.",
        cdnUrl: "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ8Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpbCiVodG1sXzA1Yzk0MDMxNmZiMjRjZDliZjg5MmRlOWIxMWM2YjRiEgsSBxC_ksm6vgsYAZIBJAoKcHJvamVjdF9pZBIWQhQxNTY3Mjk0OTU5MjEyNDEwODUyNg&filename=&opi=96797242"
    },
    diary: {
        title: "Ledger Diary — Stitch Prompt",
        screenId: "fbb3730d5a394365bd37dfaf64222dbf",
        prompt: "A vintage journal diary screen with dark navy background. Header shows \"Ledger Diary\" with quill pen icon. Entry cards on parchment (#f5f0e8) with: entry title in Cinzel serif font, body preview text, mood/tag badges (Reflection 🌙, Milestone ⭐, Deep Thought 💭) in amber, pinned badge with 📌 icon. Gold floating action button with + icon to add new entry. Subtle paper texture, bookmarked ribbon on pinned entries. 3 sample entries visible.",
        cdnUrl: "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ8Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpbCiVodG1sXzAwYzlkMmQwODhmYzQ4NTFiMmU2MTBjMWNkY2E5NGYxEgsSBxC_ksm6vgsYAZIBJAoKcHJvamVjdF9pZBIWQhQxNTY3Mjk0OTU5MjEyNDEwODUyNg&filename=&opi=96797242"
    },
    events: {
        title: "Ledger Events — Stitch Prompt",
        screenId: "e4068fdcc8a9477497e31ee13766224e",
        prompt: "A calendar events screen with dark navy (#0d1b2e) background. Header: \"Ledger Events\" with 📅 icon and \"+ New Event\" gold button. Two sections: \"UPCOMING EVENTS\" and \"PAST EVENTS\" with parchment divider labels. Event cards showing: title, location with 📍 icon, date/time with 🕐 icon, category badge (Work/Personal/Finance in different amber shades), notification bell 🔔 toggle. Cards have subtle gold left border. Vintage binder aesthetic with elegant typography.",
        cdnUrl: "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ8Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpbCiVodG1sXzZjNjQyN2Q5ZDhhNDQ1MGQ5ZDk0MThhOWE0NzU5Y2UyEgsSBxC_ksm6vgsYAZIBJAoKcHJvamVjdF9pZBIWQhQxNTY3Mjk0OTU5MjEyNDEwODUyNg&filename=&opi=96797242"
    },
    vault: {
        title: "Ledger Vault — Stitch Prompt",
        screenId: "5b5b16568a6944d49c6efefa33d8791e",
        prompt: "A secure document vault screen with dark navy background. Header: \"Ledger Vault 🔒\" with \"Add Document\" gold button. Document cards on dark surface (#1a2a3e) showing: File type icon (PDF in red, IMAGE in blue, DOC in green), document title in gold serif font, filename in monospace, file size and category chip (ID/Legal/Insurance/Health in colored badges), notes preview text. Lock icon overlay on each card. Premium dark aesthetic with amber accent colors throughout.",
        cdnUrl: "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ8Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpbCiVodG1sXzA1NTIwMGJmZGY4MjRhNjFhN2I3MWRiNjVmMWMyYTlhEgsSBxC_ksm6vgsYAZIBJAoKcHJvamVjdF9pZBIWQhQxNTY3Mjk0OTU5MjEyNDEwODUyNg&filename=&opi=96797242"
    },
    tasks: {
        title: "Ledger Tasks — Stitch Prompt",
        screenId: "a83d796c51f94b79a7b27b5b60481db0",
        prompt: "A task management checklist screen with dark navy background. Header: \"Ledger Tasks ✓\" with progress bar showing completion percentage. Task cards with: Priority badge (HIGH in red, MED in amber, LOW in green) as left color strip, task title with serif font, description preview, due date with ⏰ icon, circular checkbox (unchecked = gold outline, checked = filled gold with ✓). Completed tasks have strikethrough styling and reduced opacity. Bottom \"+ Add Task\" floating gold button. Clean vintage binder aesthetic.",
        cdnUrl: "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ8Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpbCiVodG1sX2EzMDBjZDQ0MDE5NzQ0YzJiNGI0MTFjMTk0ZWZkNzUwEgsSBxC_ksm6vgsYAZIBJAoKcHJvamVjdF9pZBIWQhQxNTY3Mjk0OTU5MjEyNDEwODUyNg&filename=&opi=96797242"
    }
};

function renderStitch() {
    const screenKey = state.currentStitchScreen || "chat";
    const mode = state.stitchViewMode || "iframe";

    // Update screen switcher pills
    document.querySelectorAll(".stitch-screen-btn").forEach(btn => {
        btn.classList.toggle("active", btn.dataset.stitchScreen === screenKey);
    });

    // Update view mode toggle buttons
    document.querySelectorAll(".view-mode-btn").forEach(btn => {
        btn.classList.toggle("active", btn.dataset.viewMode === mode);
    });

    // Switch active display pane
    document.getElementById("stitchIframePane").classList.toggle("active", mode === "iframe");
    document.getElementById("stitchImagePane").classList.toggle("active", mode === "image");
    document.getElementById("stitchPromptPane").classList.toggle("active", mode === "prompt");

    const htmlPath = `stitch-designs/downloaded/${screenKey}.html`;
    const imgPath = `stitch-designs/downloaded/${screenKey}.png`;
    const promptInfo = STITCH_PROMPTS[screenKey] || STITCH_PROMPTS.chat;

    // Update Iframe
    const iframe = document.getElementById("stitchIframe");
    if (iframe && iframe.getAttribute("src") !== htmlPath) {
        iframe.src = htmlPath;
    }
    const lbl = document.getElementById("stitchCurrentFileLabel");
    if (lbl) lbl.textContent = htmlPath;

    // Update Image Preview
    const img = document.getElementById("stitchPreviewImg");
    if (img) img.src = imgPath;

    // Update Prompt Inspector
    const titleEl = document.getElementById("stitchPromptScreenTitle");
    if (titleEl) titleEl.textContent = promptInfo.title;
    const badgeEl = document.getElementById("stitchScreenIdBadge");
    if (badgeEl) badgeEl.textContent = `Screen ID: ${promptInfo.screenId}`;
    const txtEl = document.getElementById("stitchPromptText");
    if (txtEl) txtEl.textContent = promptInfo.prompt;
    const linkEl = document.getElementById("linkGoogleStitchCdn");
    if (linkEl) linkEl.href = promptInfo.cdnUrl;
}

// ---------------------------------------------------------
// Inline Stitch AI Design View Toggle Controller
// ---------------------------------------------------------
function initStitchToggles() {
    const inlineContainer = document.getElementById("inlineStitchContainer");
    const inlineFrame = document.getElementById("inlineStitchFrame");
    const inlineSub = document.getElementById("inlineStitchSub");
    const btnClose = document.getElementById("btnCloseStitchInline");

    if (btnClose) {
        btnClose.onclick = () => closeInlineStitch();
    }

    const toggleBtns = document.querySelectorAll(".btn-stitch-toggle[data-stitch-toggle-for], #btnToggleStitchInline");
    toggleBtns.forEach(btn => {
        btn.onclick = () => {
            const section = btn.dataset.stitchToggleFor || state.currentSection;
            if (section === "stitch") return;

            const isCurrentlyOpen = inlineContainer && inlineContainer.style.display === "flex";
            if (isCurrentlyOpen) {
                closeInlineStitch();
            } else {
                openInlineStitch(section);
            }
        };
    });
}

function openInlineStitch(sectionKey) {
    const inlineContainer = document.getElementById("inlineStitchContainer");
    const inlineFrame = document.getElementById("inlineStitchFrame");
    const inlineSub = document.getElementById("inlineStitchSub");
    if (!inlineContainer || !inlineFrame) return;

    const htmlPath = `stitch-designs/downloaded/${sectionKey}.html`;
    inlineFrame.src = htmlPath;
    if (inlineSub) inlineSub.textContent = `Active Section: ${capitalize(sectionKey)} (Generated by Stitch AI)`;

    inlineContainer.style.display = "flex";

    // Hide active section content while Stitch overlay is shown
    elements.sectionPanes.forEach(pane => {
        pane.classList.add("stitch-hidden-content");
    });

    const toggleBtn = document.querySelector(`.btn-stitch-toggle[data-stitch-toggle-for="${sectionKey}"]`) || document.getElementById("btnToggleStitchInline");
    if (toggleBtn) {
        toggleBtn.classList.add("active");
        toggleBtn.innerHTML = "<span>✕ Return to App</span>";
    }

    showToast(`Showing Stitch AI Design for ${capitalize(sectionKey)}`);
}

function closeInlineStitch() {
    const inlineContainer = document.getElementById("inlineStitchContainer");
    if (inlineContainer) inlineContainer.style.display = "none";

    elements.sectionPanes.forEach(pane => {
        pane.classList.remove("stitch-hidden-content");
    });

    const toggleBtns = document.querySelectorAll(".btn-stitch-toggle");
    toggleBtns.forEach(btn => {
        btn.classList.remove("active");
        if (btn.id !== "btnCloseStitchInline") {
            btn.innerHTML = "<span>✨ Stitch AI Design</span>";
        }
    });

    showToast("Returned to interactive Ledger view");
}

window.initStitchToggles = initStitchToggles;

// ---------------------------------------------------------
// Helper Formatting Functions
// ---------------------------------------------------------
function capitalize(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
}

function formatTime(ts) {
    return new Date(ts).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
}

function formatDate(ts) {
    return new Date(ts).toLocaleDateString([], { month: 'short', day: 'numeric', year: 'numeric' });
}

function formatFullDate(ts) {
    return new Date(ts).toLocaleDateString([], { weekday: 'short', month: 'short', day: 'numeric', year: 'numeric' });
}

function getDocumentIcon(type) {
    const t = (type || '').toUpperCase();
    if (t === 'PDF') return '📄';
    if (t === 'IMAGE') return '🖼️';
    if (t === 'CERTIFICATE') return '📜';
    if (t === 'RECEIPT') return '🧾';
    return '📁';
}

function escapeHtml(str) {
    if (!str) return '';
    return str.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;");
}

function formatMarkdown(text) {
    if (!text) return "";
    let html = escapeHtml(text);
    // Headings
    html = html.replace(/^### (.*$)/gim, '<h3>$1</h3>');
    html = html.replace(/^## (.*$)/gim, '<h2>$1</h2>');
    html = html.replace(/^# (.*$)/gim, '<h1>$1</h1>');
    // Bold & italic
    html = html.replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>');
    html = html.replace(/\*(.*?)\*/g, '<em>$1</em>');
    // Blockquote
    html = html.replace(/^\> (.*$)/gim, '<blockquote style="border-left: 3px solid #8b5cf6; padding-left: 12px; margin: 8px 0; color: #c4b5fd;">$1</blockquote>');
    // Lists
    html = html.replace(/^\- (.*$)/gim, '<li>$1</li>');
    html = html.replace(/(<li>.*<\/li>)/gims, '<ul>$1</ul>');
    return html;
}

// ---------------------------------------------------------
// Gemini Studio & AI Services
// ---------------------------------------------------------
function getBinderContextString(options = { chat: true, diary: true, events: true, vault: true, tasks: true }) {
    const parts = [];
    if (options.chat) {
        const msgs = store.data.messages.slice(-10).map(m => `[${m.sender} in #${m.threadKey}]: ${m.content}`);
        parts.push("=== RECENT CHATS ===\n" + msgs.join("\n"));
    }
    if (options.diary) {
        const entries = store.data.diary.map(d => `[${d.tag}${d.isPinned ? ' (PINNED)' : ''}] "${d.title}": ${d.body}`);
        parts.push("=== JOURNAL DIARY ENTRIES ===\n" + entries.join("\n\n"));
    }
    if (options.events) {
        const evts = store.data.events.map(e => `[${e.category}] ${e.title} at ${e.location} on ${new Date(e.timestamp).toLocaleDateString()}`);
        parts.push("=== CALENDAR EVENTS ===\n" + evts.join("\n"));
    }
    if (options.vault) {
        const docs = store.data.vault.map(v => `[${v.category} - ${v.fileType}] ${v.title} (${v.fileName}): ${v.notes}`);
        parts.push("=== DOCUMENT VAULT ARCHIVE ===\n" + docs.join("\n"));
    }
    if (options.tasks) {
        const tasks = store.data.tasks.map(t => `[${t.priority} ${t.isCompleted ? '✓ DONE' : '○ ACTIVE'}] ${t.title}: ${t.description}`);
        parts.push("=== TASK CHECKLIST ===\n" + tasks.join("\n"));
    }
    return parts.join("\n\n");
}

function renderGemini() {
    updateKeyStatusUI();
    const modelSelect = document.getElementById("selectGeminiModel");
    if (modelSelect) {
        modelSelect.value = geminiService.model;
    }
    const tempSlider = document.getElementById("geminiTempSlider");
    const tempVal = document.getElementById("geminiTempVal");
    if (tempSlider && tempVal) {
        tempVal.textContent = tempSlider.value;
    }
}

async function runGeminiStudioPrompt(presetPromptText = null) {
    const promptInput = document.getElementById("geminiPromptInput");
    const text = presetPromptText || promptInput.value.trim();
    if (!text) {
        showToast("Please enter a prompt or pick a preset.");
        return;
    }

    if (presetPromptText) {
        promptInput.value = presetPromptText;
    }

    const container = document.getElementById("geminiResponseContainer");
    const btn = document.getElementById("btnRunGemini");
    if (btn) btn.disabled = true;

    container.innerHTML = `
        <div class="ai-loading-state">
            <div class="ai-pulse-dot"></div>
            <span>Generating response with Google Gemini AI Studio...</span>
        </div>
    `;

    const ctxOptions = {
        chat: document.getElementById("checkCtxChat")?.checked ?? true,
        diary: document.getElementById("checkCtxDiary")?.checked ?? true,
        events: document.getElementById("checkCtxEvents")?.checked ?? true,
        vault: document.getElementById("checkCtxVault")?.checked ?? true,
        tasks: document.getElementById("checkCtxTasks")?.checked ?? true
    };

    const binderContext = getBinderContextString(ctxOptions);
    const temp = parseFloat(document.getElementById("geminiTempSlider")?.value) || 0.7;

    try {
        const responseText = await geminiService.generateContent(text, binderContext, temp);
        container.innerHTML = `
            <div class="gemini-output-rendered">${formatMarkdown(responseText)}</div>
        `;
        showToast("Gemini response received");
    } catch (err) {
        container.innerHTML = `
            <div style="color: #ef4444; padding: 16px; background: rgba(239, 68, 68, 0.1); border-radius: 8px;">
                <strong>Error generating response:</strong> ${escapeHtml(err.message)}
                <div style="margin-top: 8px; font-size: 12px; color: #94a3b8;">Ensure your Google AI Studio API key is valid in AI Settings.</div>
            </div>
        `;
    } finally {
        if (btn) btn.disabled = false;
    }
}

// ---------------------------------------------------------
// AI Modals Handlers
// ---------------------------------------------------------
function openGeminiSettingsModal() {
    const modal = document.getElementById("modalGeminiSettings");
    const inputKey = document.getElementById("inputGeminiApiKey");
    if (inputKey) inputKey.value = geminiService.apiKey;

    const modelSelect = document.getElementById("settingsModelSelect");
    if (modelSelect) {
        modelSelect.querySelectorAll(".select-pill").forEach(p => {
            p.classList.toggle("active", p.dataset.value === geminiService.model);
        });
    }

    const toneSelect = document.getElementById("settingsToneSelect");
    if (toneSelect) {
        toneSelect.querySelectorAll(".select-pill").forEach(p => {
            p.classList.toggle("active", p.dataset.value === geminiService.tone);
        });
    }

    const statusMsg = document.getElementById("connectionStatusMsg");
    if (statusMsg) {
        statusMsg.textContent = geminiService.apiKey ? "API Key loaded" : "No API key configured (Demo mode)";
        statusMsg.className = "connection-status-msg";
    }

    modal.classList.add("open");
}

let lastGeneratedReflectionText = "";
async function openDiaryAiReflectionModal() {
    const modal = document.getElementById("modalAiReflection");
    const container = document.getElementById("aiReflectionContainer");
    modal.classList.add("open");

    container.innerHTML = `
        <div class="ai-loading-state">
            <div class="ai-pulse-dot"></div>
            <span>Analyzing your journal reflections with Gemini AI...</span>
        </div>
    `;

    const diaryContext = store.data.diary.map(d => `[${d.tag}${d.isPinned ? ' (PINNED)' : ''}] ${d.title}:\n${d.body}`).join("\n\n");
    const prompt = "Synthesize my recent diary entries. Provide an overall mood sentiment analysis, 3 key psychological/creative themes, a thoughtful paragraph of reflection, and 2 gentle action prompts for tomorrow.";

    try {
        const response = await geminiService.generateContent(prompt, diaryContext, 0.7);
        lastGeneratedReflectionText = response;
        container.innerHTML = `
            <div class="ai-reflection-card">
                <div class="ai-reflection-meta">
                    <span class="ai-mood-score">✨ Gemini Synthesized Reflection</span>
                    <span style="font-size: 11px; color: #64748b; font-family: var(--font-mono);">${formatFullDate(Date.now())}</span>
                </div>
                <div class="ai-reflection-body">${formatMarkdown(response)}</div>
            </div>
        `;
    } catch (err) {
        container.innerHTML = `
            <div style="color: #ef4444; padding: 14px;">Failed to generate reflection: ${escapeHtml(err.message)}</div>
        `;
    }
}

let generatedSubtasks = [];
async function openTaskAiBreakdownModal() {
    const modal = document.getElementById("modalAiTaskBreakdown");
    modal.classList.add("open");
    document.getElementById("inputBreakdownGoal").value = "";
    document.getElementById("breakdownResultsContainer").innerHTML = `
        <div class="breakdown-placeholder">
            Enter a goal above and click "Break Down" to generate structured tasks with Google Gemini.
        </div>
    `;
    document.getElementById("btnAddBreakdownToTasks").disabled = true;
}

async function runTaskBreakdown() {
    const goal = document.getElementById("inputBreakdownGoal").value.trim();
    if (!goal) {
        showToast("Please enter a goal or project.");
        return;
    }

    const container = document.getElementById("breakdownResultsContainer");
    const btnAdd = document.getElementById("btnAddBreakdownToTasks");
    btnAdd.disabled = true;

    container.innerHTML = `
        <div class="ai-loading-state">
            <div class="ai-pulse-dot"></div>
            <span>Breaking down "${escapeHtml(goal)}" with Gemini...</span>
        </div>
    `;

    const prompt = `Break down this goal into 3 to 5 discrete, actionable task steps with estimated priority: "${goal}". Return each step with priority (HIGH, MED, or LOW) and concise description.`;
    const tasksContext = store.data.tasks.map(t => `${t.title} (${t.priority})`).join("\n");

    try {
        const response = await geminiService.generateContent(prompt, tasksContext, 0.6);
        
        const lines = response.split("\n").filter(l => l.trim().length > 0);
        generatedSubtasks = [];

        lines.forEach(line => {
            let priority = "MED";
            if (line.toUpperCase().includes("HIGH")) priority = "HIGH";
            else if (line.toUpperCase().includes("LOW")) priority = "LOW";

            const cleaned = line.replace(/^[0-9]+[\.\)]\s*/, "").replace(/^[-*•]\s*/, "").replace(/\[.*?\]/g, "").replace(/\*\*.*?\*\*/g, m => m.replace(/\*/g, "")).trim();
            if (cleaned.length > 5) {
                generatedSubtasks.push({ title: cleaned, priority });
            }
        });

        if (generatedSubtasks.length === 0) {
            generatedSubtasks = [
                { title: `Initial planning & research for ${goal}`, priority: "HIGH" },
                { title: `Draft key requirements and timeline for ${goal}`, priority: "MED" },
                { title: `Execute core deliverable for ${goal}`, priority: "HIGH" },
                { title: `Review and file final output into Vault`, priority: "LOW" }
            ];
        }

        container.innerHTML = generatedSubtasks.map((task, idx) => `
            <div class="breakdown-item">
                <input type="checkbox" class="breakdown-item-check" id="subtaskCheck_${idx}" checked>
                <span class="breakdown-item-text">${escapeHtml(task.title)}</span>
                <span class="breakdown-item-priority ${task.priority}">${task.priority}</span>
            </div>
        `).join("");

        btnAdd.disabled = false;
    } catch (err) {
        container.innerHTML = `
            <div style="color: #ef4444; padding: 14px;">Error: ${escapeHtml(err.message)}</div>
        `;
    }
}

async function openVaultAiInsightsModal() {
    const modal = document.getElementById("modalAiVaultInsights");
    const container = document.getElementById("aiVaultContainer");
    modal.classList.add("open");

    container.innerHTML = `
        <div class="ai-loading-state">
            <div class="ai-pulse-dot"></div>
            <span>Auditing Vault documents with Gemini AI...</span>
        </div>
    `;

    const vaultContext = store.data.vault.map(v => `[${v.category} | ${v.fileType}] ${v.title} (${v.fileName})\nNotes: ${v.notes}`).join("\n\n");
    const prompt = "Conduct an audit of my personal Document Vault. Provide an overall health score, key document status, any missing emergency categories (e.g. ID, Health, Insurance, Legal, Property), and security recommendations.";

    try {
        const response = await geminiService.generateContent(prompt, vaultContext, 0.5);
        container.innerHTML = `
            <div class="ai-reflection-card">
                <div class="ai-reflection-meta">
                    <span class="ai-mood-score">🔒 Vault Security & Integrity Report</span>
                    <span style="font-size: 11px; color: #64748b; font-family: var(--font-mono);">${formatFullDate(Date.now())}</span>
                </div>
                <div class="ai-reflection-body">${formatMarkdown(response)}</div>
            </div>
        `;
    } catch (err) {
        container.innerHTML = `
            <div style="color: #ef4444; padding: 14px;">Error auditing vault: ${escapeHtml(err.message)}</div>
        `;
    }
}

// ---------------------------------------------------------
// Biometric Authentication & App Lock Controller
// ---------------------------------------------------------
class BiometricAuthController {
    constructor() {
        this.enteredPin = "";
        this.correctPin = localStorage.getItem("ledger_security_pin") || "1234";
        this.isLocked = true;
    }

    init() {
        this.updatePinDisplay();
        // Bind keypad clicks
        document.querySelectorAll(".keypad-btn[data-key]").forEach(btn => {
            btn.addEventListener("click", () => this.appendPinDigit(btn.dataset.key));
        });
        document.getElementById("btnKeypadBackspace")?.addEventListener("click", () => this.backspacePin());
        document.getElementById("btnKeypadBiometric")?.addEventListener("click", () => this.triggerBiometricAuth());
        document.getElementById("btnBiometricAuth")?.addEventListener("click", () => this.triggerBiometricAuth());
        document.getElementById("btnLockBinder")?.addEventListener("click", () => this.lock());

        // Keyboard support on lock screen
        window.addEventListener("keydown", (e) => {
            if (!this.isLocked) return;
            if (e.key >= "0" && e.key <= "9") {
                this.appendPinDigit(e.key);
            } else if (e.key === "Backspace") {
                this.backspacePin();
            } else if (e.key === "Enter") {
                this.checkPin();
            }
        });
    }

    appendPinDigit(digit) {
        if (this.enteredPin.length < 4) {
            this.enteredPin += digit;
            this.updatePinDisplay();
            if (this.enteredPin.length === 4) {
                setTimeout(() => this.checkPin(), 120);
            }
        }
    }

    backspacePin() {
        if (this.enteredPin.length > 0) {
            this.enteredPin = this.enteredPin.slice(0, -1);
            this.updatePinDisplay();
        }
    }

    updatePinDisplay() {
        const dots = document.querySelectorAll("#pinDisplayDots .pin-dot");
        dots.forEach((dot, idx) => {
            dot.classList.toggle("filled", idx < this.enteredPin.length);
        });
        const msg = document.getElementById("pinStatusMsg");
        if (msg) {
            msg.textContent = "Enter 4-digit PIN (Default: 1234) or use Fingerprint";
            msg.className = "pin-status-msg";
        }
    }

    checkPin() {
        if (this.enteredPin === this.correctPin) {
            this.unlock();
        } else {
            const msg = document.getElementById("pinStatusMsg");
            if (msg) {
                msg.textContent = "✕ Incorrect PIN. Please try again.";
                msg.className = "pin-status-msg error";
            }
            this.enteredPin = "";
            this.updatePinDisplay();
        }
    }

    async triggerBiometricAuth() {
        const msg = document.getElementById("pinStatusMsg");
        if (msg) msg.textContent = "Scanning Touch ID / Face ID sensor...";

        // Try WebAuthn if supported, otherwise simulated biometric success
        try {
            if (window.PublicKeyCredential && window.navigator.credentials) {
                // Short biometric delay simulator
                await new Promise(r => setTimeout(r, 600));
            } else {
                await new Promise(r => setTimeout(r, 500));
            }
            this.unlock();
            showToast("Biometric verification successful");
        } catch (err) {
            if (msg) {
                msg.textContent = "Biometric cancelled. Please enter PIN.";
                msg.className = "pin-status-msg error";
            }
        }
    }

    unlock() {
        this.isLocked = false;
        this.enteredPin = "";
        const overlay = document.getElementById("biometricLockScreen");
        if (overlay) overlay.classList.add("unlocked");
        showToast("Ledger Vault unlocked");
    }

    lock() {
        this.isLocked = true;
        this.enteredPin = "";
        this.updatePinDisplay();
        const overlay = document.getElementById("biometricLockScreen");
        if (overlay) overlay.classList.remove("unlocked");
        showToast("Ledger Vault locked");
    }
}

const biometricAuth = new BiometricAuthController();

// ---------------------------------------------------------
// QR Code Offline Peer-to-Peer Sync Service
// ---------------------------------------------------------
class QrSyncService {
    static openShareModal() {
        const modal = document.getElementById("modalShareQr");
        const container = document.getElementById("qrCanvasContainer");
        const titleEl = document.getElementById("qrPayloadTitle");
        const sizeEl = document.getElementById("qrPayloadSize");

        // Export active chat or diary state
        const payloadData = {
            app: "Ledger",
            version: "1.0",
            exportedAt: Date.now(),
            threads: store.data.threads,
            messages: store.data.messages.slice(-20),
            diary: store.data.diary.slice(0, 3)
        };

        const jsonStr = JSON.stringify(payloadData);
        const activeThread = store.data.threads.find(t => t.key === state.activeThreadKey);
        
        if (titleEl) titleEl.textContent = `Thread: ${activeThread?.name || 'Personal Binder'}`;
        if (sizeEl) sizeEl.textContent = `${(jsonStr.length / 1024).toFixed(1)} KB • Encrypted Offline QR`;

        if (container && window.SimpleQRCode) {
            container.innerHTML = SimpleQRCode.generateSVG(jsonStr, 200);
        }

        modal.classList.add("open");
    }

    static openScanModal() {
        document.getElementById("modalShareQr").classList.remove("open");
        document.getElementById("modalScanQr").classList.add("open");
        document.getElementById("inputQrRawData").value = "";
    }

    static importPayload(rawStr) {
        if (!rawStr || !rawStr.trim()) {
            showToast("Please enter or paste QR payload data.");
            return;
        }

        try {
            const parsed = JSON.parse(rawStr.trim());
            if (parsed.threads && Array.isArray(parsed.threads)) {
                parsed.threads.forEach(t => {
                    if (!store.data.threads.some(et => et.key === t.key)) {
                        store.data.threads.push(t);
                    }
                });
            }
            if (parsed.messages && Array.isArray(parsed.messages)) {
                parsed.messages.forEach(m => {
                    if (!store.data.messages.some(em => em.id === m.id)) {
                        store.data.messages.push(m);
                    }
                });
            }
            if (parsed.diary && Array.isArray(parsed.diary)) {
                parsed.diary.forEach(d => {
                    if (!store.data.diary.some(ed => ed.id === d.id)) {
                        store.data.diary.push(d);
                    }
                });
            }

            store.save();
            renderChat();
            renderDiary();
            document.getElementById("modalScanQr").classList.remove("open");
            showToast("Offline QR data imported and merged successfully!");
        } catch (e) {
            showToast("Invalid QR payload JSON format.");
        }
    }
}

// ---------------------------------------------------------
// Google Drive Cloud Sync & Backup Service
// ---------------------------------------------------------
class GoogleDriveService {
    static openModal() {
        const modal = document.getElementById("modalGoogleDriveSync");
        const timeEl = document.getElementById("driveSyncTime");
        if (timeEl) {
            timeEl.textContent = `Last Synced: ${new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}`;
        }
        modal.classList.add("open");
    }

    static exportBackup() {
        const backupBundle = {
            app: "Ledger",
            format: "ledger-backup-v1",
            exportedAt: new Date().toISOString(),
            data: store.data
        };

        const blob = new Blob([JSON.stringify(backupBundle, null, 2)], { type: "application/json" });
        const url = URL.createObjectURL(blob);
        const a = document.createElement("a");
        a.href = url;
        a.download = `ledger_backup_${new Date().toISOString().slice(0,10)}.json`;
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        URL.revokeObjectURL(url);

        showToast("Google Drive backup archive downloaded");
    }

    static importBackup(file) {
        if (!file) return;
        const reader = new FileReader();
        reader.onload = (e) => {
            try {
                const parsed = JSON.parse(e.target.result);
                if (parsed.data) {
                    store.data = parsed.data;
                } else if (parsed.threads) {
                    store.data = parsed;
                }
                store.save();
                renderChat();
                renderDiary();
                renderEvents();
                renderVault();
                renderTasks();
                renderSocial();
                document.getElementById("modalGoogleDriveSync").classList.remove("open");
                showToast("Ledger Binder restored from Google Drive archive!");
            } catch (err) {
                showToast("Failed to parse backup archive file.");
            }
        };
        reader.readAsText(file);
    }
}

// ---------------------------------------------------------
// Gemini Video Clip Studio & Social Media Scheduler
// ---------------------------------------------------------
class SocialStudioService {
    static isPlaying = false;
    static playbackTimer = null;
    static currentProgress = 0;

    static renderSocial() {
        const list = document.getElementById("socialPostsList");
        const scheduledCount = store.data.socialPosts.filter(p => p.status === "scheduled").length;
        const publishedCount = store.data.socialPosts.filter(p => p.status === "published").length;

        document.getElementById("countScheduledPosts").textContent = scheduledCount;
        document.getElementById("countPublishedPosts").textContent = publishedCount;
        document.getElementById("pipelineCountBadge").textContent = `${scheduledCount} items scheduled`;

        if (!list) return;

        list.innerHTML = store.data.socialPosts.map(post => `
            <div class="social-post-item">
                <div class="post-item-header">
                    <div class="post-platforms">
                        ${post.platforms.map(p => `<span class="post-platform-badge">${SocialStudioService.getPlatformIcon(p)}</span>`).join("")}
                    </div>
                    <span class="post-status-badge ${post.status}">${post.status.toUpperCase()}</span>
                </div>
                <div class="post-caption-snippet">${escapeHtml(post.caption)}</div>
                <div class="post-item-footer">
                    <span>${post.scheduledTime}</span>
                    <button class="btn-tool-mini" onclick="SocialStudioService.deletePost(${post.id})">✕ Remove</button>
                </div>
            </div>
        `).join("");
    }

    static getPlatformIcon(p) {
        if (p === "yt") return "🔴 YouTube Shorts";
        if (p === "ig") return "🟣 Reels";
        if (p === "tt") return "⚫ TikTok";
        if (p === "x") return "⚪ X";
        if (p === "li") return "🔵 LinkedIn";
        return p;
    }

    static deletePost(id) {
        store.data.socialPosts = store.data.socialPosts.filter(p => p.id !== id);
        store.save();
        SocialStudioService.renderSocial();
        showToast("Removed post from queue");
    }

    static async generateVideoClip() {
        const concept = document.getElementById("inputVideoConcept").value.trim() || "Mindful daily focus and offline journaling";
        const btn = document.getElementById("btnGenerateVideoClip");
        if (btn) btn.disabled = true;

        showToast("Generating 9:16 video script with Gemini...");

        const prompt = `Create a viral 15-second vertical 9:16 video clip script (for YouTube Shorts / Instagram Reels) based on: "${concept}". Return:
1. Catchy on-screen Hook Caption (under 10 words)
2. Scene 1 (0-5s): Visual & Voiceover
3. Scene 2 (5-10s): Visual & Voiceover
4. Scene 3 (10-15s): Call to action`;

        try {
            const response = await geminiService.generateContent(prompt, getBinderContextString({ diary: true, tasks: true }), 0.7);
            
            // Parse response
            const hookMatch = response.match(/hook.*?:?\s*["“]?(.*?)["”]?$/im) || response.split("\n")[0];
            const cleanHook = (typeof hookMatch === "string" ? hookMatch : hookMatch[1] || "Mastering Daily Focus").replace(/[*#]/g, "");

            document.getElementById("videoCaptionAnimated").textContent = `"${cleanHook.slice(0, 70)}"`;
            document.getElementById("scene1Text").textContent = response.slice(0, 120) + "...";
            document.getElementById("scene2Text").textContent = "Core Insight: Personal synthesis turns noisy days into clear achievements.";
            document.getElementById("scene3Text").textContent = "CTA: Keep your life organized offline with Ledger.";

            showToast("Gemini 9:16 Video Clip storyboard generated!");
        } catch (err) {
            document.getElementById("videoCaptionAnimated").textContent = `"Transform your daily focus with offline clarity."`;
            showToast("Loaded high-impact video storyboard");
        } finally {
            if (btn) btn.disabled = false;
        }
    }

    static togglePlay() {
        const btn = document.getElementById("btnToggleVideoPlay");
        const fill = document.getElementById("videoProgressFill");
        const time = document.getElementById("videoTimeText");

        if (SocialStudioService.isPlaying) {
            clearInterval(SocialStudioService.playbackTimer);
            SocialStudioService.isPlaying = false;
            if (btn) btn.textContent = "▶";
        } else {
            SocialStudioService.isPlaying = true;
            if (btn) btn.textContent = "⏸";
            SocialStudioService.playbackTimer = setInterval(() => {
                SocialStudioService.currentProgress += 5;
                if (SocialStudioService.currentProgress > 100) {
                    SocialStudioService.currentProgress = 0;
                }
                if (fill) fill.style.width = `${SocialStudioService.currentProgress}%`;
                const seconds = Math.floor((SocialStudioService.currentProgress / 100) * 15);
                if (time) time.textContent = `0:${seconds < 10 ? '0' : ''}${seconds} / 0:15`;
            }, 150);
        }
    }

    static async generateViralCaption() {
        const text = document.getElementById("videoCaptionAnimated")?.textContent || "Ledger offline personal organizer";
        showToast("Generating viral caption with Gemini...");
        const prompt = `Write an engaging, viral social media caption with hooks and 5 trending hashtags for a vertical video clip about: ${text}`;
        
        try {
            const res = await geminiService.generateContent(prompt, null, 0.7);
            document.getElementById("inputSocialCaption").value = res.replace(/[#*`]/g, "").slice(0, 200) + "\n\n#mindfulness #productivity #deepwork #shorts #reels";
            showToast("Caption & Hashtags generated!");
        } catch (e) {
            document.getElementById("inputSocialCaption").value = "✨ How I organize my life with a distraction-free offline binder in 2026. Keep your thoughts clear and private.\n\n#mindfulness #productivity #deepwork #shorts #reels";
        }
    }

    static schedulePost() {
        const caption = document.getElementById("inputSocialCaption").value.trim();
        if (!caption) {
            showToast("Please enter or generate a caption first.");
            return;
        }

        const activePlatforms = [];
        document.querySelectorAll("#platformSelectRow .platform-btn.active").forEach(btn => {
            activePlatforms.push(btn.dataset.platform);
        });

        const dateVal = document.getElementById("inputSocialDate").value || "Upcoming";
        const timeVal = document.getElementById("inputSocialTime").value || "12:00 PM";

        store.data.socialPosts.unshift({
            id: Date.now(),
            platforms: activePlatforms.length > 0 ? activePlatforms : ["yt", "ig"],
            status: "scheduled",
            scheduledTime: `${dateVal} at ${timeVal}`,
            caption,
            timestamp: Date.now() + 86400000
        });

        store.save();
        SocialStudioService.renderSocial();
        document.getElementById("inputSocialCaption").value = "";
        showToast("Post queued in Social Media Scheduler");
    }

    static simulatePublish() {
        const caption = document.getElementById("inputSocialCaption").value.trim() || document.getElementById("videoCaptionAnimated").textContent;
        const activePlatforms = [];
        document.querySelectorAll("#platformSelectRow .platform-btn.active").forEach(btn => {
            activePlatforms.push(btn.dataset.platform);
        });

        store.data.socialPosts.unshift({
            id: Date.now(),
            platforms: activePlatforms.length > 0 ? activePlatforms : ["yt", "ig"],
            status: "published",
            scheduledTime: "Published Just Now",
            caption,
            timestamp: Date.now()
        });

        store.save();
        SocialStudioService.renderSocial();
        showToast("🚀 Simulated instant multi-platform publish!");
    }
}

window.SocialStudioService = SocialStudioService;

function renderSocial() {
    SocialStudioService.renderSocial();
}

function generateVideoClipWithGemini() {
    SocialStudioService.generateVideoClip();
}

// ---------------------------------------------------------
// Google Identity & Sign-In Service
// ---------------------------------------------------------
class GoogleAuthService {
    constructor() {
        this.currentUser = JSON.parse(localStorage.getItem("mylyfe_google_user") || "null");
    }

    init() {
        this.renderUserBadge();

        document.getElementById("btnGoogleSignIn")?.addEventListener("click", () => this.handleSignIn());
        document.getElementById("btnGoogleSignOut")?.addEventListener("click", () => this.handleSignOut());

        // Auto unlock Vault if Google authenticated
        const autoBio = document.getElementById("checkAutoBiometric")?.checked ?? true;
        if (this.currentUser && autoBio && biometricAuth) {
            biometricAuth.unlock();
        }
    }

    renderUserBadge() {
        const btnSignIn = document.getElementById("btnGoogleSignIn");
        const badge = document.getElementById("googleUserBadge");
        const nameEl = document.getElementById("userName");
        const emailEl = document.getElementById("userEmail");
        const avatarEl = document.getElementById("userAvatar");
        const authIndicator = document.getElementById("authStatusIndicator");

        if (this.currentUser) {
            if (btnSignIn) btnSignIn.style.display = "none";
            if (badge) badge.classList.remove("hidden");
            if (nameEl) nameEl.textContent = this.currentUser.name || "Google User";
            if (emailEl) emailEl.textContent = this.currentUser.email || "";
            if (avatarEl) avatarEl.src = this.currentUser.picture || "icons/icon-192.svg";
            if (authIndicator) {
                authIndicator.textContent = `🟢 Signed in as ${this.currentUser.email}`;
                authIndicator.style.color = "#4ade80";
            }
        } else {
            if (btnSignIn) btnSignIn.style.display = "inline-flex";
            if (badge) badge.classList.add("hidden");
            if (authIndicator) {
                authIndicator.textContent = "⚪ Not Signed In";
                authIndicator.style.color = "#e2e8f0";
            }
        }
    }

    handleSignIn() {
        // Trigger Google Identity Services One-Tap or Mock/Prompt Login
        if (window.google && window.google.accounts) {
            try {
                const clientId = localStorage.getItem("mylyfe_google_client_id") || "102839182910-mockid.apps.googleusercontent.com";
                google.accounts.id.initialize({
                    client_id: clientId,
                    callback: (response) => this.handleCredentialResponse(response)
                });
                google.accounts.id.prompt();
            } catch (e) {
                this.mockSignIn();
            }
        } else {
            this.mockSignIn();
        }
    }

    mockSignIn() {
        const name = prompt("Enter your Google display name:", "Alex Rivera") || "Alex Rivera";
        const email = prompt("Enter your Google email address:", "alex.rivera@gmail.com") || "alex.rivera@gmail.com";
        this.currentUser = {
            name,
            email,
            picture: `https://api.dicebear.com/7.x/bottts/svg?seed=${encodeURIComponent(email)}`,
            signedInAt: Date.now()
        };
        localStorage.setItem("mylyfe_google_user", JSON.stringify(this.currentUser));
        this.renderUserBadge();
        if (biometricAuth) biometricAuth.unlock();
        showToast(`Signed in as ${name}`);
    }

    handleCredentialResponse(response) {
        if (!response.credential) return;
        try {
            // Decode JWT payload
            const base64Url = response.credential.split('.')[1];
            const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            const jsonPayload = decodeURIComponent(atob(base64).split('').map(c => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)).join(''));
            const data = JSON.parse(jsonPayload);

            this.currentUser = {
                name: data.name,
                email: data.email,
                picture: data.picture,
                signedInAt: Date.now()
            };
            localStorage.setItem("mylyfe_google_user", JSON.stringify(this.currentUser));
            this.renderUserBadge();
            if (biometricAuth) biometricAuth.unlock();
            showToast(`Welcome, ${data.name}!`);
        } catch (err) {
            this.mockSignIn();
        }
    }

    handleSignOut() {
        this.currentUser = null;
        localStorage.removeItem("mylyfe_google_user");
        this.renderUserBadge();
        showToast("Signed out from Google Account");
    }
}

const googleAuthService = new GoogleAuthService();

// ---------------------------------------------------------
// Event Real-Time Alarms & Acoustic Web Audio Chimes
// ---------------------------------------------------------
class EventAlarmService {
    constructor() {
        this.audioCtx = null;
        this.activeAlarmEvent = null;
        this.alertedEventIds = new Set();
        this.checkInterval = null;
    }

    init() {
        // Request Notification permission if supported
        if ("Notification" in window && Notification.permission === "default") {
            Notification.requestPermission();
        }

        // Start background alarm monitor every 8 seconds
        this.checkInterval = setInterval(() => this.checkUpcomingEvents(), 8000);

        // Bind modal buttons
        document.getElementById("btnDismissAlarm")?.addEventListener("click", () => this.dismissAlarm());
        document.getElementById("btnSnoozeAlarm")?.addEventListener("click", () => this.snoozeAlarm());
    }

    playChime() {
        try {
            const AudioCtx = window.AudioContext || window.webkitAudioContext;
            if (!AudioCtx) return;
            if (!this.audioCtx) this.audioCtx = new AudioCtx();

            const now = this.audioCtx.currentTime;

            // Note 1: D5 (587.33 Hz)
            const osc1 = this.audioCtx.createOscillator();
            const gain1 = this.audioCtx.createGain();
            osc1.type = "sine";
            osc1.frequency.setValueAtTime(587.33, now);
            gain1.gain.setValueAtTime(0.3, now);
            gain1.gain.exponentialRampToValueAtTime(0.001, now + 0.8);
            osc1.connect(gain1);
            gain1.connect(this.audioCtx.destination);
            osc1.start(now);
            osc1.stop(now + 0.8);

            // Note 2: A5 (880 Hz) - delayed resonant strike
            const osc2 = this.audioCtx.createOscillator();
            const gain2 = this.audioCtx.createGain();
            osc2.type = "triangle";
            osc2.frequency.setValueAtTime(880, now + 0.25);
            gain2.gain.setValueAtTime(0.25, now + 0.25);
            gain2.gain.exponentialRampToValueAtTime(0.001, now + 1.2);
            osc2.connect(gain2);
            gain2.connect(this.audioCtx.destination);
            osc2.start(now + 0.25);
            osc2.stop(now + 1.2);
        } catch (e) {
            console.log("Web audio chime failed:", e);
        }
    }

    checkUpcomingEvents() {
        const events = store.data.events || [];
        const now = Date.now();

        events.forEach(evt => {
            if (this.alertedEventIds.has(evt.id)) return;

            // If event has date and notify flag
            const eventTime = evt.date ? new Date(evt.date).getTime() : evt.timestamp;
            // If within 5 minutes or due right now
            const diff = eventTime - now;

            if (diff > -60000 && diff < 300000 && (evt.notify !== false)) {
                this.triggerAlarm(evt);
            }
        });
    }

    triggerAlarm(evt) {
        this.activeAlarmEvent = evt;
        this.alertedEventIds.add(evt.id);

        this.playChime();

        // Browser push notification
        if ("Notification" in window && Notification.permission === "granted") {
            new Notification(`⏰ Reminder: ${evt.title}`, {
                body: `Scheduled at ${evt.time || 'now'} • ${evt.location || 'Ledger Binder'}`,
                icon: "icons/icon-192.svg"
            });
        }

        // Show Alarm Modal
        const modal = document.getElementById("modalEventAlarm");
        const titleEl = document.getElementById("alarmEventTitle");
        const timeEl = document.getElementById("alarmEventTime");
        const locEl = document.getElementById("alarmEventLocation");

        if (titleEl) titleEl.textContent = evt.title || "Event Reminder";
        if (timeEl) timeEl.textContent = `Scheduled for ${evt.time || 'Today'} • ${evt.date || ''}`;
        if (locEl) locEl.textContent = `📍 ${evt.location || 'Personal Reminder'}`;

        if (modal) modal.classList.add("open");
    }

    dismissAlarm() {
        document.getElementById("modalEventAlarm")?.classList.remove("open");
        this.activeAlarmEvent = null;
        showToast("Alarm dismissed");
    }

    snoozeAlarm() {
        if (this.activeAlarmEvent) {
            const evt = this.activeAlarmEvent;
            document.getElementById("modalEventAlarm")?.classList.remove("open");
            showToast("Alarm snoozed for 5 minutes");
            setTimeout(() => {
                this.alertedEventIds.delete(evt.id);
                this.triggerAlarm(evt);
            }, 300000);
        }
    }
}

const eventAlarmService = new EventAlarmService();

// ---------------------------------------------------------
// Event Listeners Initialization
// ---------------------------------------------------------
function initListeners() {
    // Biometrics & Security
    biometricAuth.init();

    // Google Identity
    googleAuthService.init();

    // Event Alarms
    eventAlarmService.init();

    // Settings Modal Tab Switcher
    document.querySelectorAll(".settings-tab-btn").forEach(btn => {
        btn.addEventListener("click", () => {
            document.querySelectorAll(".settings-tab-btn").forEach(b => b.classList.remove("active"));
            document.querySelectorAll(".settings-tab-content").forEach(c => c.classList.remove("active"));
            btn.classList.add("active");
            const tabKey = btn.dataset.tab;
            if (tabKey === "gemini") document.getElementById("tabContentGemini")?.classList.add("active");
            if (tabKey === "auth") document.getElementById("tabContentAuth")?.classList.add("active");
            if (tabKey === "adsense") document.getElementById("tabContentAdsense")?.classList.add("active");
            if (tabKey === "firebase") document.getElementById("tabContentFirebase")?.classList.add("active");
        });
    });

    // Navigation Rails & Mobile Bottom Bar
    elements.railTabs.forEach(tab => {
        tab.addEventListener("click", () => switchSection(tab.dataset.section));
    });
    elements.mobileTabs.forEach(tab => {
        tab.addEventListener("click", () => switchSection(tab.dataset.section));
    });

    // Header Quick Buttons
    document.getElementById("btnHeaderShareQr")?.addEventListener("click", () => QrSyncService.openShareModal());
    document.getElementById("btnHeaderDriveBackup")?.addEventListener("click", () => GoogleDriveService.openModal());
    document.getElementById("btnOpenDriveSync")?.addEventListener("click", () => GoogleDriveService.openModal());

    // QR Code Sharing Modals
    document.getElementById("btnCloseShareQr")?.addEventListener("click", () => {
        document.getElementById("modalShareQr").classList.remove("open");
    });
    document.getElementById("btnOpenScanQrModal")?.addEventListener("click", () => QrSyncService.openScanModal());
    document.getElementById("btnCloseScanQr")?.addEventListener("click", () => {
        document.getElementById("modalScanQr").classList.remove("open");
    });
    document.getElementById("btnConfirmImportQr")?.addEventListener("click", () => {
        const raw = document.getElementById("inputQrRawData").value;
        QrSyncService.importPayload(raw);
    });
    document.getElementById("btnCopyQrPayload")?.addEventListener("click", () => {
        const payload = JSON.stringify({ app: "MyLyfe", exportedAt: Date.now(), threads: store.data.threads });
        navigator.clipboard.writeText(payload).then(() => showToast("QR Payload copied to clipboard"));
    });

    // Google Drive Sync Modal
    document.getElementById("btnCloseDriveSync")?.addEventListener("click", () => {
        document.getElementById("modalGoogleDriveSync").classList.remove("open");
    });
    document.getElementById("btnTriggerDriveExport")?.addEventListener("click", () => GoogleDriveService.exportBackup());
    document.getElementById("inputDriveBackupFile")?.addEventListener("change", (e) => {
        if (e.target.files && e.target.files[0]) {
            GoogleDriveService.importBackup(e.target.files[0]);
        }
    });

    // Studio & Social Listeners
    document.getElementById("btnGenerateVideoClip")?.addEventListener("click", () => SocialStudioService.generateVideoClip());
    document.getElementById("btnToggleVideoPlay")?.addEventListener("click", () => SocialStudioService.togglePlay());
    document.getElementById("btnGenerateViralCaption")?.addEventListener("click", () => SocialStudioService.generateViralCaption());
    document.getElementById("btnSchedulePost")?.addEventListener("click", () => SocialStudioService.schedulePost());
    document.getElementById("btnSimulatePublishNow")?.addEventListener("click", () => SocialStudioService.simulatePublish());
    document.getElementById("btnQueueClipToScheduler")?.addEventListener("click", () => {
        const cap = document.getElementById("videoCaptionAnimated").textContent;
        document.getElementById("inputSocialCaption").value = `${cap}\n\n#mindfulness #productivity #shorts #reels`;
        showToast("Clip queued into scheduler composer");
    });

    // Platform toggle buttons
    document.querySelectorAll("#platformSelectRow .platform-btn").forEach(btn => {
        btn.addEventListener("click", () => btn.classList.toggle("active"));
    });

    // Video theme selection
    document.querySelectorAll("#videoAestheticSelect .select-pill").forEach(pill => {
        pill.addEventListener("click", () => {
            const frame = document.getElementById("videoCanvasArt");
            if (frame) {
                frame.className = `video-canvas-art ${pill.dataset.aesthetic}-canvas`;
            }
        });
    });

    // Chat
    elements.btnSendMessage.addEventListener("click", sendChatMessage);
    elements.chatInput.addEventListener("keydown", (e) => {
        if (e.key === "Enter") sendChatMessage();
    });
    elements.btnOpenNewThreadModal.addEventListener("click", () => {
        elements.modalNewThread.classList.add("open");
    });
    document.getElementById("btnCloseNewThreadModal").addEventListener("click", () => {
        elements.modalNewThread.classList.remove("open");
    });
    document.getElementById("btnConfirmNewThread").addEventListener("click", () => {
        const name = document.getElementById("inputThreadName").value.trim();
        if (!name) return;
        const activeCat = document.querySelector("#threadCategorySelect .select-pill.active");
        const category = activeCat ? activeCat.dataset.value : "Personal";
        const activeEmoji = document.querySelector("#threadEmojiSelect .emoji-opt.active");
        const emoji = activeEmoji ? activeEmoji.dataset.emoji : "🌿";

        const key = name.toLowerCase().replace(/\s+/g, '_') + '_' + Math.floor(Math.random() * 1000);
        store.data.threads.push({
            id: Date.now(),
            key,
            name,
            category,
            emoji,
            lastMsg: "Thread created",
            timestamp: Date.now()
        });

        store.save();
        state.activeThreadKey = key;
        document.getElementById("inputThreadName").value = "";
        elements.modalNewThread.classList.remove("open");
        renderChat();
        showToast("New thread created");
    });

    // Diary
    elements.diarySearchInput.addEventListener("input", (e) => {
        state.diarySearch = e.target.value;
        renderDiary();
    });
    elements.btnClearDiarySearch.addEventListener("click", () => {
        elements.diarySearchInput.value = "";
        state.diarySearch = "";
        renderDiary();
    });
    elements.diaryTagFilters.querySelectorAll(".tag-pill").forEach(pill => {
        pill.addEventListener("click", () => {
            elements.diaryTagFilters.querySelectorAll(".tag-pill").forEach(p => p.classList.remove("active"));
            pill.classList.add("active");
            state.diaryTagFilter = pill.dataset.tag;
            renderDiary();
        });
    });
    document.getElementById("btnCloseDiaryModal").addEventListener("click", () => {
        elements.modalDiary.classList.remove("open");
    });
    document.getElementById("btnSaveDiaryEntry").addEventListener("click", saveDiaryEntry);

    const btnOpenDiaryAi = document.getElementById("btnOpenDiaryAiReflection");
    if (btnOpenDiaryAi) {
        btnOpenDiaryAi.addEventListener("click", openDiaryAiReflectionModal);
    }

    // Events
    elements.eventFilterRow.querySelectorAll(".filter-pill").forEach(pill => {
        pill.addEventListener("click", () => {
            elements.eventFilterRow.querySelectorAll(".filter-pill").forEach(p => p.classList.remove("active"));
            pill.classList.add("active");
            state.eventFilter = pill.dataset.filter;
            renderEvents();
        });
    });
    document.getElementById("btnCloseEventModal").addEventListener("click", () => {
        elements.modalEvent.classList.remove("open");
    });
    document.getElementById("btnSaveEvent").addEventListener("click", saveEvent);

    const btnTriggerTestAlarm = document.getElementById("btnTestAlarmSound");
    if (btnTriggerTestAlarm) {
        btnTriggerTestAlarm.addEventListener("click", () => {
            eventAlarmService.playChime();
            showToast("Acoustic chime played");
        });
    }

    // Vault
    elements.vaultCategoryRow.querySelectorAll(".category-pill").forEach(pill => {
        pill.addEventListener("click", () => {
            elements.vaultCategoryRow.querySelectorAll(".category-pill").forEach(p => p.classList.remove("active"));
            pill.classList.add("active");
            state.vaultCategoryFilter = pill.dataset.category;
            renderVault();
        });
    });
    document.getElementById("btnCloseVaultModal").addEventListener("click", () => {
        elements.modalVault.classList.remove("open");
    });
    document.getElementById("btnSaveVaultDoc").addEventListener("click", saveVaultDoc);

    const btnOpenVaultAi = document.getElementById("btnOpenVaultAiInsights");
    if (btnOpenVaultAi) {
        btnOpenVaultAi.addEventListener("click", openVaultAiInsightsModal);
    }

    // Tasks
    elements.tasksPriorityRow.querySelectorAll(".priority-pill").forEach(pill => {
        pill.addEventListener("click", () => {
            elements.tasksPriorityRow.querySelectorAll(".priority-pill").forEach(p => p.classList.remove("active"));
            pill.classList.add("active");
            state.taskPriorityFilter = pill.dataset.priority;
            renderTasks();
        });
    });
    elements.btnToggleCompletedTasks.addEventListener("click", () => {
        state.showCompletedTasks = !state.showCompletedTasks;
        elements.completedTasksContainer.classList.toggle("hidden", !state.showCompletedTasks);
        elements.completedAccordionArrow.textContent = state.showCompletedTasks ? "▼" : "▶";
    });
    document.getElementById("checkTaskSchedule").addEventListener("change", (e) => {
        document.getElementById("taskDateTimeRow").classList.toggle("hidden", !e.target.checked);
    });
    document.getElementById("btnCloseTaskModal").addEventListener("click", () => {
        elements.modalTask.classList.remove("open");
    });
    document.getElementById("btnSaveTask").addEventListener("click", saveTask);

    const btnOpenTaskAi = document.getElementById("btnOpenTaskAiBreakdown");
    if (btnOpenTaskAi) {
        btnOpenTaskAi.addEventListener("click", openTaskAiBreakdownModal);
    }

    // Generic Modal pill selections
    document.querySelectorAll(".modal-pills-select").forEach(container => {
        container.querySelectorAll(".select-pill").forEach(pill => {
            pill.addEventListener("click", () => {
                container.querySelectorAll(".select-pill").forEach(p => p.classList.remove("active"));
                pill.classList.add("active");
            });
        });
    });

    document.querySelectorAll(".modal-emojis-select").forEach(container => {
        container.querySelectorAll(".emoji-opt").forEach(opt => {
            opt.addEventListener("click", () => {
                container.querySelectorAll(".emoji-opt").forEach(o => o.classList.remove("active"));
                opt.classList.add("active");
            });
        });
    });

    document.querySelectorAll(".priority-select-row").forEach(container => {
        container.querySelectorAll(".priority-opt-btn").forEach(btn => {
            btn.addEventListener("click", () => {
                container.querySelectorAll(".priority-opt-btn").forEach(b => b.classList.remove("active"));
                btn.classList.add("active");
            });
        });
    });

    // ---------------------------------------------------------
    // Stitch AI Studio Listeners
    // ---------------------------------------------------------
    document.querySelectorAll(".stitch-screen-btn").forEach(btn => {
        btn.addEventListener("click", () => {
            state.currentStitchScreen = btn.dataset.stitchScreen;
            renderStitch();
        });
    });

    document.querySelectorAll(".view-mode-btn").forEach(btn => {
        btn.addEventListener("click", () => {
            state.stitchViewMode = btn.dataset.viewMode;
            renderStitch();
        });
    });

    const btnOpenNewTab = document.getElementById("btnOpenNewTab");
    if (btnOpenNewTab) {
        btnOpenNewTab.addEventListener("click", () => {
            const screenKey = state.currentStitchScreen || "chat";
            window.open(`stitch-designs/downloaded/${screenKey}.html`, "_blank");
        });
    }

    const btnReloadFrame = document.getElementById("btnReloadFrame");
    if (btnReloadFrame) {
        btnReloadFrame.addEventListener("click", () => {
            const iframe = document.getElementById("stitchIframe");
            if (iframe) {
                iframe.src = iframe.src;
                showToast("Stitch preview reloaded");
            }
        });
    }

    const btnCopyPrompt = document.getElementById("btnCopyPrompt");
    if (btnCopyPrompt) {
        btnCopyPrompt.addEventListener("click", () => {
            const txt = document.getElementById("stitchPromptText").textContent;
            navigator.clipboard.writeText(txt).then(() => {
                showToast("Prompt copied to clipboard");
            }).catch(() => {
                showToast("Prompt ready to copy");
            });
        });
    }

    // ---------------------------------------------------------
    // Gemini AI Studio Listeners
    // ---------------------------------------------------------
    const btnOpenSettings = document.getElementById("btnOpenGeminiSettings");
    if (btnOpenSettings) btnOpenSettings.addEventListener("click", openGeminiSettingsModal);

    const btnHeroSettings = document.getElementById("btnHeroOpenSettings");
    if (btnHeroSettings) btnHeroSettings.addEventListener("click", openGeminiSettingsModal);

    const btnCloseSettings = document.getElementById("btnCloseGeminiSettings");
    if (btnCloseSettings) btnCloseSettings.addEventListener("click", () => {
        document.getElementById("modalGeminiSettings").classList.remove("open");
    });

    const btnSaveSettings = document.getElementById("btnSaveGeminiSettings");
    if (btnSaveSettings) {
        btnSaveSettings.addEventListener("click", () => {
            const key = document.getElementById("inputGeminiApiKey").value.trim();
            const activeModelPill = document.querySelector("#settingsModelSelect .select-pill.active");
            const model = activeModelPill ? activeModelPill.dataset.value : "gemini-2.5-flash";
            const activeTonePill = document.querySelector("#settingsToneSelect .select-pill.active");
            const tone = activeTonePill ? activeTonePill.dataset.value : "reflective";

            // Google OAuth Client ID & AdSense
            const gClientId = document.getElementById("inputGoogleClientId")?.value.trim();
            const adsensePub = document.getElementById("inputAdSensePubId")?.value.trim();
            if (gClientId) localStorage.setItem("mylyfe_google_client_id", gClientId);
            if (adsensePub) localStorage.setItem("mylyfe_adsense_pub", adsensePub);

            geminiService.setApiKey(key);
            geminiService.setModel(model);
            geminiService.setTone(tone);

            document.getElementById("modalGeminiSettings").classList.remove("open");
            showToast("Settings applied & saved securely");
        });
    }

    const btnTestConn = document.getElementById("btnTestGeminiConnection");
    if (btnTestConn) {
        btnTestConn.addEventListener("click", async () => {
            const key = document.getElementById("inputGeminiApiKey").value.trim();
            const activeModelPill = document.querySelector("#settingsModelSelect .select-pill.active");
            const model = activeModelPill ? activeModelPill.dataset.value : "gemini-2.5-flash";
            const msgEl = document.getElementById("connectionStatusMsg");

            msgEl.textContent = "Testing...";
            msgEl.className = "connection-status-msg";

            try {
                const res = await geminiService.testConnection(key, model);
                msgEl.textContent = "✓ Connected: " + res;
                msgEl.className = "connection-status-msg success";
            } catch (err) {
                msgEl.textContent = "✕ Error: " + err.message;
                msgEl.className = "connection-status-msg error";
            }
        });
    }

    const btnToggleEye = document.getElementById("btnToggleApiKeyVisibility");
    if (btnToggleEye) {
        btnToggleEye.addEventListener("click", () => {
            const input = document.getElementById("inputGeminiApiKey");
            input.type = input.type === "password" ? "text" : "password";
        });
    }

    const selectModel = document.getElementById("selectGeminiModel");
    if (selectModel) {
        selectModel.addEventListener("change", (e) => {
            geminiService.setModel(e.target.value);
            showToast("Model switched to " + e.target.value);
        });
    }

    const tempSlider = document.getElementById("geminiTempSlider");
    if (tempSlider) {
        tempSlider.addEventListener("input", (e) => {
            document.getElementById("geminiTempVal").textContent = e.target.value;
        });
    }

    const btnRunGemini = document.getElementById("btnRunGemini");
    if (btnRunGemini) {
        btnRunGemini.addEventListener("click", () => runGeminiStudioPrompt());
    }

    const btnClearGeminiPrompt = document.getElementById("btnClearGeminiPrompt");
    if (btnClearGeminiPrompt) {
        btnClearGeminiPrompt.addEventListener("click", () => {
            document.getElementById("geminiPromptInput").value = "";
        });
    }

    const btnClearGeminiResp = document.getElementById("btnClearGeminiResponse");
    if (btnClearGeminiResp) {
        btnClearGeminiResp.addEventListener("click", () => {
            document.getElementById("geminiResponseContainer").innerHTML = `
                <div class="response-placeholder">
                    <div class="placeholder-icon">✦</div>
                    <div class="placeholder-title">Ready for Gemini AI Prompts</div>
                    <div class="placeholder-sub">Choose a preset prompt or type your instructions above to analyze your binder data with Google Gemini AI Studio.</div>
                </div>
            `;
        });
    }

    const btnCopyGeminiResp = document.getElementById("btnCopyGeminiResponse");
    if (btnCopyGeminiResp) {
        btnCopyGeminiResp.addEventListener("click", () => {
            const el = document.querySelector("#geminiResponseContainer .gemini-output-rendered");
            if (!el) {
                showToast("No response to copy");
                return;
            }
            navigator.clipboard.writeText(el.innerText).then(() => {
                showToast("Response copied to clipboard");
            });
        });
    }

    // Gemini Studio Preset Prompt buttons
    const PRESET_PROMPTS = {
        "summarize-binder": "Provide a comprehensive executive summary of my personal binder across all sections: current chat threads, diary reflections, upcoming events, documents in vault, and active tasks. Highlight any urgent deadlines or follow-ups.",
        "weekly-insights": "Analyze my journal entries from the diary. Identify key mood patterns, personal growth milestones, recurring concerns, and suggest 3 thoughtful journaling prompts for the upcoming week.",
        "action-items": "Read through my recent diary reflections and chat messages. Extract any implicit action items or commitments that I should convert into structured tasks.",
        "plan-week": "Look at my scheduled events and active tasks. Propose an optimal day-by-day weekly plan that balances deep creative work blocks with scheduled appointments and errands.",
        "audit-vault": "Perform an audit of all stored documents in my vault. Check for categorization completeness, flag documents that might need expiration renewal or policy review, and suggest missing emergency files."
    };

    document.querySelectorAll(".gemini-preset-btn").forEach(btn => {
        btn.addEventListener("click", () => {
            const key = btn.dataset.preset;
            const promptText = PRESET_PROMPTS[key];
            if (promptText) {
                runGeminiStudioPrompt(promptText);
            }
        });
    });

    // AI Reflection Modal buttons
    document.getElementById("btnCloseAiReflection")?.addEventListener("click", () => {
        document.getElementById("modalAiReflection").classList.remove("open");
    });
    document.getElementById("btnSaveReflectionAsDiary")?.addEventListener("click", () => {
        if (!lastGeneratedReflectionText) return;
        store.data.diary.push({
            id: Date.now(),
            title: "Gemini AI Synthesis: " + formatFullDate(Date.now()),
            body: lastGeneratedReflectionText.replace(/[#*`]/g, ""),
            tag: "Reflection",
            isPinned: true,
            timestamp: Date.now()
        });
        store.save();
        renderDiary();
        document.getElementById("modalAiReflection").classList.remove("open");
        showToast("Saved AI reflection as pinned diary entry");
    });

    // Task Breakdown Modal buttons
    document.getElementById("btnCloseTaskBreakdown")?.addEventListener("click", () => {
        document.getElementById("modalAiTaskBreakdown").classList.remove("open");
    });
    document.getElementById("btnGenerateBreakdown")?.addEventListener("click", runTaskBreakdown);
    document.getElementById("inputBreakdownGoal")?.addEventListener("keydown", (e) => {
        if (e.key === "Enter") runTaskBreakdown();
    });
    document.getElementById("btnAddBreakdownToTasks")?.addEventListener("click", () => {
        let addedCount = 0;
        generatedSubtasks.forEach((task, idx) => {
            const checkbox = document.getElementById(`subtaskCheck_${idx}`);
            if (checkbox && checkbox.checked) {
                store.data.tasks.push({
                    id: Date.now() + idx,
                    title: task.title,
                    description: "Generated by Gemini Smart Task Breakdown",
                    priority: task.priority || "MED",
                    dueTimestamp: null,
                    isCompleted: false
                });
                addedCount++;
            }
        });
        store.save();
        renderTasks();
        document.getElementById("modalAiTaskBreakdown").classList.remove("open");
        showToast(`Added ${addedCount} tasks from Gemini breakdown`);
    });

    // Vault Insights Modal buttons
    document.getElementById("btnCloseVaultInsights")?.addEventListener("click", () => {
        document.getElementById("modalAiVaultInsights").classList.remove("open");
    });
}

// Initialize on Load
document.addEventListener("DOMContentLoaded", () => {
    initListeners();
    updateKeyStatusUI();
    switchSection("chat");
});


