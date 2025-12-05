# Toko (media service)

A EntJava2 Project by **Moritz Chester Saribay**, **Geoff Ronyl Orosco** and **Takuya Nakasone**

(Statolumn)[https://statolumn.com] clone (portfolio project by M) but rewritten for simplier and lightweight use.

## Instructions

### 1. Main service
- Get the [main service](https://github.com/TuxPenguin09/toko.media) here first, then follow its `README.md` to proceed
- Before you run this program, make sure that the main service is active and running at port `8090`.

### 2. Run the Spring Boot app (after Docker Compose is up)
- Terminal:
    - Maven wrapper: `./mvnw spring-boot:run` (Windows: `mvnw.cmd spring-boot:run`)
    - Or with installed tools: `mvn spring-boot:run`
- IntelliJ:
    - Open/import the project as Maven.
    - Locate the main class annotated with `@SpringBootApplication`.
    - Click the Run icon or create an "Application" run configuration and start it.

## 3. Notes
- Default app URL: `http://localhost:8092` (port may differ based on configuration).

## Postman

This service exposes a small REST API for media storage. You can import the Postman collection at `postman/toko-media.postman_collection.json` into Postman, or use curl examples below.

Available endpoints:

- `GET /api/media` — list all media records
- `GET /api/media/{id}` — get media record metadata (JSON)
- `POST /api/media` — create media metadata (JSON)
- `POST /api/media/upload` — upload a file (multipart/form-data). Returns the saved media JSON including `id`, `url`, and `type`.

Examples (curl):

# List media
```bash
curl http://localhost:8092/api/media
```

# Get single media
```bash
curl http://localhost:8092/api/media/1
```

# Upload a file
```bash
curl -X POST -F "file=@/path/to/file.jpg" http://localhost:8092/api/media/upload
```

Notes:
- Uploaded files are stored in the runtime `uploads/` directory and are served at `/uploads/{filename}` (the app now exposes these static files).
- Import `postman/toko-media.postman_collection.json` into Postman to get ready-to-run requests.