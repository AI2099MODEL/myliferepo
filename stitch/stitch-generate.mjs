// stitch-generate.mjs
//
// Generates AI UI screens for all 5 Ledger sections via the Stitch SDK,
// downloads each screen's HTML + screenshot, and saves them locally.
//
// Requires STITCH_API_KEY to be set (see .env.example).
//
// Usage:
//   node stitch-generate.mjs

import { stitch } from "@google/stitch-sdk";
import { writeFile, mkdir } from "node:fs/promises";
import path from "node:path";

// ---- config -----------------------------------------------------------

const OUTPUT_DIR = path.resolve("stitch-designs");
const PROJECT_NAME = "Ledger";

const SECTIONS = [
  {
    key: "chat",
    prompt:
      "A dark-themed personal binder messaging UI with thread sidebar, " +
      "vintage parchment message bubbles, brass accent colors, offline local chat",
  },
  {
    key: "diary",
    prompt:
      "A journal diary screen with vintage leather notebook aesthetic, " +
      "mood tags, pinned entries, parchment paper texture, entry cards",
  },
  {
    key: "events",
    prompt:
      "A calendar events screen with upcoming/past split, dark navy background, " +
      "gold category badges, event cards with location and notification bell",
  },
  {
    key: "vault",
    prompt:
      "A secure document vault screen with dark theme, document type icons " +
      "(PDF/Image), category chips, file size metadata",
  },
  {
    key: "tasks",
    prompt:
      "A task checklist screen with priority color coding (High/Med/Low), " +
      "completion checkboxes, progress bar, vintage binder aesthetic",
  },
];

// ---- helpers ------------------------------------------------------------

/**
 * screen.getHtml() / screen.getImage() return DOWNLOAD URLS, not raw content.
 * This fetches the actual bytes so we can save them to disk.
 */
async function downloadTo(url, filePath) {
  const res = await fetch(url);
  if (!res.ok) {
    throw new Error(`Failed to download ${url}: ${res.status} ${res.statusText}`);
  }
  const buf = Buffer.from(await res.arrayBuffer());
  await writeFile(filePath, buf);
}

async function ensureProject() {
  // Reuse an existing "Ledger" project if one exists, otherwise create it.
  const projects = await stitch.projects();
  const existing = projects.find((p) => p.title === PROJECT_NAME);
  if (existing) {
    console.log(`Using existing project: ${existing.projectId}`);
    return existing;
  }
  console.log(`Creating new project: ${PROJECT_NAME}`);
  return stitch.createProject(PROJECT_NAME);
}

// ---- main -----------------------------------------------------------------

async function main() {
  if (!process.env.STITCH_API_KEY) {
    console.error(
      "STITCH_API_KEY is not set. Copy .env.example to .env, fill in your key " +
        "from stitch.withgoogle.com/settings, and load it (e.g. `node --env-file=.env stitch-generate.mjs`)."
    );
    process.exit(1);
  }

  await mkdir(OUTPUT_DIR, { recursive: true });

  const project = await ensureProject();

  const results = [];

  for (const section of SECTIONS) {
    console.log(`\n[${section.key}] generating...`);
    try {
      const screen = await project.generate(section.prompt);

      const htmlUrl = await screen.getHtml();
      const imageUrl = await screen.getImage();

      const htmlPath = path.join(OUTPUT_DIR, `${section.key}.html`);
      const imagePath = path.join(OUTPUT_DIR, `${section.key}.png`);

      await downloadTo(htmlUrl, htmlPath);
      await downloadTo(imageUrl, imagePath);

      console.log(`[${section.key}] saved -> ${htmlPath}, ${imagePath}`);
      results.push({ key: section.key, status: "ok", htmlPath, imagePath });
    } catch (err) {
      console.error(`[${section.key}] FAILED: ${err.message}`);
      results.push({ key: section.key, status: "error", error: err.message });
    }
  }

  // Manifest so the web app (or Antigravity) knows what's available.
  const manifestPath = path.join(OUTPUT_DIR, "manifest.json");
  await writeFile(manifestPath, JSON.stringify(results, null, 2));

  console.log("\nDone. Manifest written to", manifestPath);
  const failed = results.filter((r) => r.status === "error");
  if (failed.length) {
    console.log(`${failed.length} section(s) failed — see log above.`);
    process.exitCode = 1;
  }
}

main();
