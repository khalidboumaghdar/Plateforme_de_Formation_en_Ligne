# Étape de build
FROM node:18-alpine AS build
WORKDIR /app

# Copie des fichiers de configuration
COPY package*.json ./

# Installation des dépendances
RUN npm install

# Copie du code source
COPY . .

# Build de l'application
RUN npm run build --prod

# Étape de production avec Nginx
FROM nginx:alpine

# Copie de la configuration Nginx
COPY nginx.conf /etc/nginx/nginx.conf

# Copie des fichiers buildés
COPY --from=build /app/dist/filrouge-frontend /usr/share/nginx/html

# Port exposé
EXPOSE 80

# Démarrage de Nginx
CMD ["nginx", "-g", "daemon off;"]