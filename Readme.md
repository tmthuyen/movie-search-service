```bash
docker compose -p search-service -f compose.yml up -d --build
docker compose -p search-service -f compose.yml up -d db-postgres
docker compose -p search-service -f compose.yml up -d search-service
mvn clean package -DskipTests
mvn spring-boot:run "-Duser.timezone=Asia/Ho_Chi_Minh"

```
