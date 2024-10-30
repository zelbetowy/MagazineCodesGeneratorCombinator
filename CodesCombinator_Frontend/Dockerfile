FROM node:18

WORKDIR /app  # Wewnątrz kontenera, gdzie będą umieszczone pliki

# Kopiujemy wszystkie pliki z folderu z kodem frontendu (ustawionego w `context`)
COPY . .

# Instalacja zależności i uruchomienie aplikacji
RUN npm install
#RUN npm run build - budowanie aplikacji, na gotowo

#CMD ["npm", "start"]
CMD ["npm", "run", "dev"]