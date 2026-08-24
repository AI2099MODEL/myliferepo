// ================================================================
// LEDGER — Google Stitch SDK UI Generator
// Generates AI-designed screens for all 5 Ledger sections
// ================================================================

import { stitch } from "@google/stitch-sdk";
import { writeFileSync, mkdirSync, existsSync } from "fs";
import { join, dirname } from "path";
import { fileURLToPath } from "url";

const __dirname = dirname(fileURLToPath(import.meta.url));

// API key from environment
const API_KEY = process.env.STITCH_API_KEY || "";


// Output directory for generated designs
const OUT_DIR = join(__dirname, "stitch-designs");
if (!existsSync(OUT_DIR)) mkdirSync(OUT_DIR, { recursive: true });

// Detailed prompts for each Ledger section
const SCREENS = [
  {
    id: "chat",
    name: "Ledger Chat",
    prompt: `A premium dark-themed personal binder messaging screen. Dark navy (#0d1b2e) background. 
Left sidebar with thread list showing: Family 🏡, Work 💼, Personal 🌿, General ☕ tabs with brass/gold active indicator.
Main area shows message bubbles on parchment (#f5f0e8) background. Received messages left-aligned in white cards, 
sent messages right-aligned in warm gold (#b08d57). Bottom composer bar with text input and gold send button. 
Cinzel serif font for headings, clean sans-serif for messages. Vintage leather binder aesthetic.`,
  },
  {
    id: "diary",
    name: "Ledger Diary",
    prompt: `A vintage journal diary screen with dark navy background. 
Header shows "Ledger Diary" with quill pen icon. 
Entry cards on parchment (#f5f0e8) with: entry title in Cinzel serif font, body preview text, 
mood/tag badges (Reflection 🌙, Milestone ⭐, Deep Thought 💭) in amber, pinned badge with 📌 icon.
Gold floating action button with + icon to add new entry. 
Subtle paper texture, bookmarked ribbon on pinned entries. 3 sample entries visible.`,
  },
  {
    id: "events",
    name: "Ledger Events",
    prompt: `A calendar events screen with dark navy (#0d1b2e) background.
Header: "Ledger Events" with 📅 icon and "+ New Event" gold button.
Two sections: "UPCOMING EVENTS" and "PAST EVENTS" with parchment divider labels.
Event cards showing: title, location with 📍 icon, date/time with 🕐 icon, 
category badge (Work/Personal/Finance in different amber shades), 
notification bell 🔔 toggle. 
Cards have subtle gold left border. Vintage binder aesthetic with elegant typography.`,
  },
  {
    id: "vault",
    name: "Ledger Vault",
    prompt: `A secure document vault screen with dark navy background.
Header: "Ledger Vault 🔒" with "Add Document" gold button.
Document cards on dark surface (#1a2a3e) showing:
File type icon (PDF in red, IMAGE in blue, DOC in green),
document title in gold serif font, filename in monospace, 
file size and category chip (ID/Legal/Insurance/Health in colored badges),
notes preview text. Lock icon overlay on each card.
Premium dark aesthetic with amber accent colors throughout.`,
  },
  {
    id: "tasks",
    name: "Ledger Tasks",
    prompt: `A task management checklist screen with dark navy background.
Header: "Ledger Tasks ✓" with progress bar showing completion percentage.
Task cards with:
Priority badge (HIGH in red, MED in amber, LOW in green) as left color strip,
task title with serif font, description preview, due date with ⏰ icon,
circular checkbox (unchecked = gold outline, checked = filled gold with ✓).
Completed tasks have strikethrough styling and reduced opacity.
Bottom "+ Add Task" floating gold button. Clean vintage binder aesthetic.`,
  },
];

async function generateLedgerDesigns() {
  console.log("🧵 Google Stitch — Ledger UI Generator");
  console.log("========================================");
  console.log(`📁 Output directory: ${OUT_DIR}`);
  console.log("");

  let project;

  try {
    console.log("🔑 Authenticating with Stitch API...");
    // Try creating a new project
    project = await stitch.createProject("Ledger Personal Organizer");
    console.log(`✅ Project created: ${project.id || "Ledger Personal Organizer"}`);
  } catch (err) {
    console.error("❌ Failed to create project:", err.message);
    console.log("\n💡 Trying alternative auth approach...");
    try {
      // Try listing existing projects
      const projects = await stitch.projects();
      if (projects && projects.length > 0) {
        project = projects[0];
        console.log(`✅ Using existing project: ${project.id}`);
      } else {
        throw new Error("No projects available");
      }
    } catch (err2) {
      console.error("❌ Auth failed:", err2.message);
      console.log("\n📋 Auth error details:", JSON.stringify(err2, null, 2));
      process.exit(1);
    }
  }

  const results = [];

  for (const screen of SCREENS) {
    console.log(`\n⏳ Generating: ${screen.name}...`);
    try {
      const generated = await project.generate(screen.prompt);

      // Try to get HTML
      let html = null;
      let imageUrl = null;

      try {
        html = await generated.getHtml();
        console.log(`   📄 HTML URL: ${html}`);
      } catch (e) {
        console.log(`   ⚠️  HTML not available: ${e.message}`);
      }

      try {
        imageUrl = await generated.getImage();
        console.log(`   🖼️  Image URL: ${imageUrl}`);
      } catch (e) {
        console.log(`   ⚠️  Image not available: ${e.message}`);
      }

      // Save result metadata
      const result = {
        section: screen.id,
        name: screen.name,
        screenId: generated.id || null,
        htmlUrl: html,
        imageUrl: imageUrl,
        prompt: screen.prompt,
        generatedAt: new Date().toISOString(),
      };

      results.push(result);

      // Save individual JSON
      writeFileSync(
        join(OUT_DIR, `${screen.id}.json`),
        JSON.stringify(result, null, 2)
      );

      console.log(`   ✅ ${screen.name} — DONE`);
    } catch (err) {
      console.error(`   ❌ Failed to generate ${screen.name}:`, err.message);
      results.push({
        section: screen.id,
        name: screen.name,
        error: err.message,
        generatedAt: new Date().toISOString(),
      });
    }
  }

  // Save combined manifest
  const manifest = {
    projectId: project.id || "ledger-project",
    generatedAt: new Date().toISOString(),
    screens: results,
  };

  writeFileSync(
    join(OUT_DIR, "manifest.json"),
    JSON.stringify(manifest, null, 2)
  );

  console.log("\n========================================");
  console.log("📊 GENERATION SUMMARY");
  console.log("========================================");
  results.forEach((r) => {
    const status = r.error ? "❌ FAILED" : "✅ DONE";
    console.log(`${status} — ${r.name}`);
    if (r.htmlUrl) console.log(`       HTML: ${r.htmlUrl}`);
    if (r.imageUrl) console.log(`       IMG:  ${r.imageUrl}`);
    if (r.error) console.log(`       ERR:  ${r.error}`);
  });

  console.log(`\n📁 Manifest saved to: ${join(OUT_DIR, "manifest.json")}`);
  console.log("✨ Stitch generation complete!");

  return manifest;
}

generateLedgerDesigns().catch((err) => {
  console.error("Fatal error:", err);
  process.exit(1);
});
