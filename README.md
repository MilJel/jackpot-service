# Jackpot Service

Multi-module Spring Boot project with Kafka producer/consumer i H2 database.


## Run Service

### 1. Run Kafka and Kafka UI
```bash
docker-compose up -d
```
- **Kafka UI** availave on: http://localhost:8080
- **You need to create topic manually** ***jackpot-bets***

### 2. Run Producer module
```bash
cd producer-module
mvn spring-boot:run
```
- **Swagger UI**: http://localhost:8081/swagger-ui.html

### 3. Run Consumer module
```bash
cd consumer-module
mvn spring-boot:run
```
- **Swagger UI**: http://localhost:8082/swagger-ui.html
  - JDBC URL: `jdbc:h2:file:./db/jackpotdb`
  - Username: `sa` | Password: `password`

## Sending bets

**POST** `http://localhost:8081/api/bets/publish`

```json
{
  "betId": "550e8400-e29b-41d4-a716-446655440000",
  "userId": "6ba7b810-9dad-11d1-80b4-00c04fd430c8",
  "jackpotId": "6ba7b811-9dad-11d1-80b4-00c04fd430c8",
  "betAmount": 500.00
}
```

> `betId` is optional — if you leave it empty, it will be generated automatically.

## Jackpot logic
- Every bet makes **10%** from `betAmount` in jackpot fund.
- Jackpot is won when `currentJackpotAmount >= 10,000.00`.
- Threshold is changing in `JackpotService.java` (constant `JACKPOT_THRESHOLD`).

## NOTICE
- **`db/` folder** is in the root of project — H2 for databse is saving here (`jackpotdb.mv.db`)
- **Index on `jackpot_id` i `user_id`** in Liquibase changelog for better performance
- **`JackpotController`** on consumer side with GET endpoints for viewing all contributions and current jackpot amount.
