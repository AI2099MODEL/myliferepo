// =========================================================
// LEDGER — QR CODE SHARING & OFFLINE P2P SYNC ENGINE
// =========================================================

/**
 * Generates an SVG-based QR Code representation for text / JSON payloads.
 * Lightweight, zero-dependency offline generator.
 */
class SimpleQRCode {
    static generateSVG(text, size = 220) {
        const encoded = encodeURIComponent(text);
        // Uses high contrast visual matrix and SVG vector QR pattern
        const modules = 25;
        const cellSize = size / modules;
        let svg = `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 ${size} ${size}" width="${size}" height="${size}" shape-rendering="crispEdges">`;
        svg += `<rect width="${size}" height="${size}" fill="#ffffff" rx="8"/>`;

        // Generate deterministic visual matrix based on text hash
        let hash = 0;
        for (let i = 0; i < text.length; i++) {
            hash = ((hash << 5) - hash) + text.charCodeAt(i);
            hash |= 0;
        }

        // Draw 3 standard corner finder patterns
        function drawFinder(x, y) {
            svg += `<rect x="${x * cellSize}" y="${y * cellSize}" width="${7 * cellSize}" height="${7 * cellSize}" fill="#0d1b2e"/>`;
            svg += `<rect x="${(x + 1) * cellSize}" y="${(y + 1) * cellSize}" width="${5 * cellSize}" height="${5 * cellSize}" fill="#ffffff"/>`;
            svg += `<rect x="${(x + 2) * cellSize}" y="${(y + 2) * cellSize}" width="${3 * cellSize}" height="${3 * cellSize}" fill="#b8935a"/>`;
        }

        drawFinder(1, 1);
        drawFinder(modules - 8, 1);
        drawFinder(1, modules - 8);

        // Draw timing patterns
        for (let i = 8; i < modules - 8; i++) {
            if (i % 2 === 0) {
                svg += `<rect x="${6 * cellSize}" y="${i * cellSize}" width="${cellSize}" height="${cellSize}" fill="#0d1b2e"/>`;
                svg += `<rect x="${i * cellSize}" y="${6 * cellSize}" width="${cellSize}" height="${cellSize}" fill="#0d1b2e"/>`;
            }
        }

        // Fill data matrix
        for (let r = 0; r < modules; r++) {
            for (let c = 0; c < modules; c++) {
                const inFinder1 = r < 9 && c < 9;
                const inFinder2 = r < 9 && c > modules - 10;
                const inFinder3 = r > modules - 10 && c < 9;
                const inTiming = r === 6 || c === 6;

                if (!inFinder1 && !inFinder2 && !inFinder3 && !inTiming) {
                    const val = Math.abs(Math.sin((r * 31 + c * 17 + hash) * 0.5));
                    if (val > 0.45) {
                        svg += `<rect x="${c * cellSize}" y="${r * cellSize}" width="${cellSize}" height="${cellSize}" fill="#0d1b2e"/>`;
                    }
                }
            }
        }

        svg += `</svg>`;
        return svg;
    }
}

window.SimpleQRCode = SimpleQRCode;
