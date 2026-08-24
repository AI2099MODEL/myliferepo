/**
 * stitch-toggle.js
 *
 * Framework-agnostic toggle that swaps a section's current markup for its
 * Stitch-generated design, loaded from stitch-designs/<section>.html.
 *
 * Antigravity note: this is written as plain DOM/fetch so it can be dropped
 * into any stack (vanilla, React, Vue, etc). If Ledger is a React app, wrap
 * this logic in a component that iframes or dangerouslySetInnerHTML's the
 * fetched HTML instead of touching the DOM directly.
 *
 * Usage:
 *   <div id="ledger-section" data-section="chat">...current UI...</div>
 *   <button id="stitch-toggle">✨ Stitch AI Design</button>
 *   <script src="stitch-toggle.js"></script>
 */

(function () {
  const DESIGNS_BASE = "/stitch-designs"; // adjust if served from elsewhere

  async function loadStitchDesign(sectionKey) {
    const res = await fetch(`${DESIGNS_BASE}/${sectionKey}.html`);
    if (!res.ok) {
      throw new Error(`No Stitch design found for "${sectionKey}"`);
    }
    return res.text();
  }

  function initToggle(sectionEl, buttonEl) {
    const sectionKey = sectionEl.dataset.section;
    if (!sectionKey) {
      console.warn("stitch-toggle: element has no data-section attribute", sectionEl);
      return;
    }

    let originalHtml = null;
    let stitchHtml = null;
    let showingStitch = false;

    buttonEl.addEventListener("click", async () => {
      buttonEl.disabled = true;
      try {
        if (!showingStitch) {
          if (originalHtml === null) originalHtml = sectionEl.innerHTML;
          if (stitchHtml === null) stitchHtml = await loadStitchDesign(sectionKey);
          sectionEl.innerHTML = stitchHtml;
          buttonEl.textContent = "↩ Original Design";
        } else {
          sectionEl.innerHTML = originalHtml;
          buttonEl.textContent = "✨ Stitch AI Design";
        }
        showingStitch = !showingStitch;
      } catch (err) {
        console.error(err);
        alert(`Couldn't load Stitch design for "${sectionKey}": ${err.message}`);
      } finally {
        buttonEl.disabled = false;
      }
    });
  }

  // Auto-wire any [data-section] element paired with a
  // [data-stitch-toggle-for="<section>"] button.
  document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll("[data-stitch-toggle-for]").forEach((btn) => {
      const key = btn.dataset.stitchToggleFor;
      const section = document.querySelector(`[data-section="${key}"]`);
      if (section) initToggle(section, btn);
    });
  });
})();
