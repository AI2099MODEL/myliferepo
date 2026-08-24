/**
 * stitch-toggle.js
 *
 * Seamless toggle that displays the Stitch AI-designed screen for each section
 * (chat, diary, events, vault, tasks) in a sandboxed, responsive view.
 */

(function () {
    const DESIGNS_BASE = "stitch-designs";

    // Track active stitch view state per section
    const activeStitchStates = new Map();

    /**
     * Finds the relative URL for the section's stitch design
     */
    function getDesignUrl(sectionKey) {
        return `${DESIGNS_BASE}/${sectionKey}.html`;
    }

    /**
     * Toggles between standard interactive UI and the Stitch AI Design view.
     */
    async function toggleStitchDesign(sectionKey, buttonEl) {
        const sectionEl = document.querySelector(`[data-section="${sectionKey}"]`);
        if (!sectionEl) {
            console.warn(`stitch-toggle: section element not found for key "${sectionKey}"`);
            return;
        }

        let isShowing = activeStitchStates.get(sectionKey) || false;
        let iframeContainer = sectionEl.querySelector(`.stitch-preview-container[data-for="${sectionKey}"]`);

        buttonEl.disabled = true;

        try {
            if (!isShowing) {
                // Show Stitch AI Design
                if (!iframeContainer) {
                    iframeContainer = document.createElement("div");
                    iframeContainer.className = "stitch-preview-container";
                    iframeContainer.dataset.for = sectionKey;
                    iframeContainer.innerHTML = `
                        <div class="stitch-preview-banner">
                            <span class="stitch-banner-tag">✨ Stitch AI Design Preview</span>
                            <span class="stitch-banner-sub">Generated with @google/stitch-sdk</span>
                        </div>
                        <iframe class="stitch-design-frame" src="${getDesignUrl(sectionKey)}" title="Stitch Design: ${sectionKey}"></iframe>
                    `;
                    sectionEl.appendChild(iframeContainer);
                }

                // Hide original section content children except the stitch container
                Array.from(sectionEl.children).forEach(child => {
                    if (child !== iframeContainer) {
                        child.classList.add("stitch-hidden-content");
                    }
                });

                iframeContainer.style.display = "flex";
                buttonEl.innerHTML = "↩ Interactive View";
                buttonEl.classList.add("active");
                activeStitchStates.set(sectionKey, true);
            } else {
                // Return to Interactive Ledger UI
                if (iframeContainer) {
                    iframeContainer.style.display = "none";
                }

                Array.from(sectionEl.children).forEach(child => {
                    if (child !== iframeContainer) {
                        child.classList.remove("stitch-hidden-content");
                    }
                });

                buttonEl.innerHTML = "✨ Stitch AI Design";
                buttonEl.classList.remove("active");
                activeStitchStates.set(sectionKey, false);
            }
        } catch (err) {
            console.error("Failed to toggle Stitch design:", err);
            alert(`Couldn't load Stitch design for "${sectionKey}": ${err.message}`);
        } finally {
            buttonEl.disabled = false;
        }
    }

    /**
     * Initializes all [data-stitch-toggle-for] buttons in the DOM.
     */
    function attachStitchToggles() {
        document.querySelectorAll("[data-stitch-toggle-for]").forEach(btn => {
            if (btn._stitchInitialized) return;
            btn._stitchInitialized = true;

            const key = btn.dataset.stitchToggleFor;
            btn.addEventListener("click", () => {
                toggleStitchDesign(key, btn);
            });

            // If this section was already in Stitch mode, update button text
            if (activeStitchStates.get(key)) {
                btn.innerHTML = "↩ Interactive View";
                btn.classList.add("active");
            }
        });
    }

    // Expose global helper for dynamic re-attaching
    window.initStitchToggles = attachStitchToggles;

    document.addEventListener("DOMContentLoaded", attachStitchToggles);
})();
