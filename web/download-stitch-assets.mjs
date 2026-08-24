// Script to download Stitch AI generated HTML and images locally
import fs from "fs";
import path from "path";
import https from "https";
import { fileURLToPath } from "url";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const manifestPath = path.join(__dirname, "stitch-designs", "manifest.json");
const manifest = JSON.parse(fs.readFileSync(manifestPath, "utf8"));

const downloadDir = path.join(__dirname, "stitch-designs", "downloaded");
if (!fs.existsSync(downloadDir)) fs.mkdirSync(downloadDir, { recursive: true });

function downloadFile(url, destPath) {
    return new Promise((resolve, reject) => {
        https.get(url, (res) => {
            if (res.statusCode >= 300 && res.statusCode < 400 && res.headers.location) {
                return downloadFile(res.headers.location, destPath).then(resolve).catch(reject);
            }
            if (res.statusCode !== 200) {
                return reject(new Error(`Failed to download ${url}: status code ${res.statusCode}`));
            }
            const fileStream = fs.createWriteStream(destPath);
            res.pipe(fileStream);
            fileStream.on("finish", () => {
                fileStream.close();
                resolve(destPath);
            });
        }).on("error", reject);
    });
}

async function run() {
    console.log("📥 Downloading Stitch AI Designs...");
    for (const screen of manifest.screens) {
        console.log(`Downloading assets for ${screen.name} (${screen.section})...`);
        
        if (screen.htmlUrl) {
            const htmlFile = path.join(downloadDir, `${screen.section}.html`);
            try {
                await downloadFile(screen.htmlUrl, htmlFile);
                console.log(`  ✅ Saved HTML: ${htmlFile}`);
                screen.localHtml = `stitch-designs/downloaded/${screen.section}.html`;
            } catch (err) {
                console.error(`  ❌ Failed to download HTML for ${screen.section}:`, err.message);
            }
        }

        if (screen.imageUrl) {
            const imgFile = path.join(downloadDir, `${screen.section}.png`);
            try {
                await downloadFile(screen.imageUrl, imgFile);
                console.log(`  ✅ Saved Image: ${imgFile}`);
                screen.localImage = `stitch-designs/downloaded/${screen.section}.png`;
            } catch (err) {
                console.error(`  ❌ Failed to download Image for ${screen.section}:`, err.message);
            }
        }
    }

    fs.writeFileSync(manifestPath, JSON.stringify(manifest, null, 2));
    console.log("✨ All Stitch AI assets downloaded and manifest updated!");
}

run().catch(console.error);
