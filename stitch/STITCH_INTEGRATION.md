# Stitch AI Design — Integration Notes

Scaffold for generating and toggling AI-designed screens for Ledger's 5
sections (Chat, Diary, Events, Vault, Tasks) via `@google/stitch-sdk`.

## Files in this drop

| File | Goes to | Purpose |
|---|---|---|
| `stitch-generate.mjs` | `d:/p1/web/stitch-generate.mjs` | Generates + downloads all 5 screens |
| `.env.example` | `d:/p1/web/.env.example` | Template for the API key |
| `stitch-toggle.js` | `d:/p1/web/stitch-toggle.js` (or wherever static assets live) | Toggle button logic |
| `.gitignore.stitch-additions` | append into `d:/p1/web/.gitignore` | Keeps `.env` and generated output out of git |

## Setup steps

1. `npm install @google/stitch-sdk` — double check it resolves to the
   `@google/` scope, not a lookalike.
2. Copy `.env.example` → `.env`, paste in the real key from
   `stitch.withgoogle.com/settings`.
3. Run:
   ```bash
   node --env-file=.env stitch-generate.mjs
   ```
   (Node 20.6+ supports `--env-file` natively; if the Ledger project uses an
   older Node, swap in `dotenv` instead.)
4. Confirm `stitch-designs/` now has `chat.html`, `diary.html`, `events.html`,
   `vault.html`, `tasks.html`, matching `.png` screenshots, and a
   `manifest.json` listing success/failure per section.

## What Antigravity still needs to do

This scaffold generates and downloads the designs but doesn't know Ledger's
actual frontend structure, so it stops short of full integration:

- **Serve `stitch-designs/`** as a static asset directory (or copy its
  contents into wherever Ledger already serves static files).
- **Wire `stitch-toggle.js` into the real component tree.** It's written
  framework-agnostic (plain DOM + fetch) on purpose since I don't know if
  Ledger is vanilla JS, React, etc. — if it's React, this logic should
  become a component that fetches the HTML and renders it (iframe or a
  sanitized `dangerouslySetInnerHTML`) rather than mutating the DOM directly.
- **Add `data-section="<key>"` to each of the 5 section containers** and a
  `data-stitch-toggle-for="<key>"` button near each, using keys `chat`,
  `diary`, `events`, `vault`, `tasks` — the script auto-wires from those
  attributes.
- **Re-run `stitch-generate.mjs` on demand**, not on every build — it hits
  a paid/quota-limited API, so it shouldn't be part of the normal CI/build
  pipeline.

## Notes

- `screen.getHtml()` / `screen.getImage()` return **download URLs**, not
  inline content — `stitch-generate.mjs` already handles the extra fetch,
  but flagging it in case this gets refactored.
- The `stitch-designs/` output is being treated as regenerable build output
  (gitignored) rather than source. If you'd rather version-control the
  generated screens, remove that line from `.gitignore`.
