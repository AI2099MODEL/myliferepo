// =========================================================
// LEDGER — SERVICE WORKER (100% OFFLINE PWA ENGINE)
// =========================================================

const CACHE_NAME = "ledger-offline-cache-v1";
const ASSETS_TO_CACHE = [
  "./",
  "./index.html",
  "./style.css",
  "./app.js",
  "./qr-helper.js",
  "./stitch-toggle.js",
  "./manifest.json",
  "./stitch-designs/downloaded/chat.html",
  "./stitch-designs/downloaded/diary.html",
  "./stitch-designs/downloaded/events.html",
  "./stitch-designs/downloaded/vault.html",
  "./stitch-designs/downloaded/tasks.html"
];

// Install Event — Pre-cache static shell
self.addEventListener("install", (event) => {
  event.waitUntil(
    caches.open(CACHE_NAME).then((cache) => {
      console.log("[Ledger SW] Caching offline app shell");
      return cache.addAll(ASSETS_TO_CACHE).catch((err) => {
        console.warn("[Ledger SW] Cache prefetch non-fatal error:", err);
      });
    })
  );
  self.skipWaiting();
});

// Activate Event — Clean up outdated caches
self.addEventListener("activate", (event) => {
  event.waitUntil(
    caches.keys().then((cacheNames) => {
      return Promise.all(
        cacheNames.map((cache) => {
          if (cache !== CACHE_NAME) {
            console.log("[Ledger SW] Clearing old cache:", cache);
            return caches.delete(cache);
          }
        })
      );
    })
  );
  self.clients.claim();
});

// Fetch Event — Cache-first fallback to network
self.addEventListener("fetch", (event) => {
  // Allow external API calls (Gemini, Google APIs) to bypass cache
  if (event.request.url.includes("googleapis.com") || event.request.url.includes("stitch.withgoogle.com")) {
    return;
  }

  event.respondWith(
    caches.match(event.request).then((cachedResponse) => {
      if (cachedResponse) {
        return cachedResponse;
      }
      return fetch(event.request).then((networkResponse) => {
        if (!networkResponse || networkResponse.status !== 200 || networkResponse.type !== "basic") {
          return networkResponse;
        }
        const responseToCache = networkResponse.clone();
        caches.open(CACHE_NAME).then((cache) => {
          cache.put(event.request, responseToCache);
        });
        return networkResponse;
      }).catch(() => {
        // Return offline fallback if network fails
        if (event.request.destination === "document") {
          return caches.match("./index.html");
        }
      });
    })
  );
});
