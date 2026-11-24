```bash
docker compose -p search-service -f compose.yml up -d --build
docker compose -p search-service -f compose.yml down
docker compose -p search-service -f compose.yml up -d db-postgres
docker compose -p search-service -f compose.yml up -d search-service
docker compose -p search-service -f compose.yml up -d --scale search-service=3
docker compose up -d --scale search-service=3

mvn clean package -DskipTests
mvn spring-boot:run "-Duser.timezone=Asia/Ho_Chi_Minh"

```
