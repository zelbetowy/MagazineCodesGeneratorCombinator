# Używamy obrazu JDK
FROM openjdk:23-slim

# Ustawiamy zmienne środowiskowe dla Spring Boot
ENV SPRING_PROFILES_ACTIVE=web-dev
#ENV SPRING_PROFILES_ACTIVE=web-prod
#ENV SPRING_PROFILES_ACTIVE=desktopConsole

WORKDIR /app

# Kopiujemy pliki projektu (backend) do kontenera
COPY . /app

# Uruchamianie aplikacji bezpośrednio z kodu
CMD ["./mvnw", "spring-boot:run"]