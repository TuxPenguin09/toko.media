# Toko (media service)

A EntJava2 Project by **Moritz Chester Saribay**, **Geoff Ronyl Orosco** and **Takuya Nakasone**

Statolumn clone (portfolio project by M) but rewritten for simplier and lightweight use.

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