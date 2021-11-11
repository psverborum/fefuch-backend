cs:
	./mvnw spring-javaformat:apply

db-up:
	docker-compose up -d --build

db-down:
	docker-compose down

db-exec:
	docker exec -it fefuch-backend_db_1 /bin/bash