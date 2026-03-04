# Sistema de ventas

API REST construida con **Spring Boot (Java 17)** usando una **arquitectura hexagonal (ports & adapters)**.
Este proyecto está enfocado en demostrar buenas prácticas de backend: separación por capas, validación, manejo de errores, pruebas y una base lista para crecer (H2/JPA y migración futura a PostgreSQL).

## Stack
- Java 17
- Spring Boot
- Gradle (usar **Gradle Wrapper**)
- Validation (Jakarta Bean Validation)
- (Opcional) Spring Data JPA
- H2 (desarrollo)

## Arquitectura (Hexagonal)
La aplicación separa responsabilidades en capas y define puertos para entrada/salida:



**Flujo típico:**
`Controller (api) → UseCase/Service (application) → Port Out (application) → Adapter (infrastructure) → DB/externos`

## Cómo ejecutar

### Requisitos
- Java 17
- Git

> Importante: **no uses `gradle` del sistema**. Usa siempre el wrapper `./gradlew`.

### Build
```bash
./gradlew clean build
```
### Run
```bash
./gradlew bootRun
```
### Autor

**Eduardo Castro**

LinkedIn: www.linkedin.com/in/eduardo-castro12121

GitHub: https://github.com/laloCastro22