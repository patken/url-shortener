# url-shortener

Service de raccourcissement d'URL — Spring Boot 3 / Java 21 / Maven.

## Lancer

```bash
mvn spring-boot:run
```

## API

| Méthode | Route              | Description                          |
|---------|--------------------|--------------------------------------|
| POST    | `/api/urls`        | Crée une URL courte `{"url": "..."}` |
| GET     | `/api/urls/{code}` | Statistiques (nb de clics…)          |
| GET     | `/{code}`          | Redirection 302 vers l'URL d'origine |

Console H2 : http://localhost:8080/h2-console (JDBC `jdbc:h2:mem:urlshortener`)

## Structure

```
src/main/java/com/patken/urlshortener
├── api/            # Contrôleurs REST + DTO
├── service/        # Logique métier
├── domain/         # Entités JPA
├── repository/     # Spring Data JPA
├── config/         # Configuration / propriétés
└── exception/      # Exceptions + handler global
```
