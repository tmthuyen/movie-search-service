# Java Spring Boot Search Service & Docker & EC2 Instance (Linux)

## EC2 Instance (Linux) Setup

```bash
=============================
EC2 Instance Setup (Linux)
=============================
sudo apt update && sudo apt upgrade -y  # ubuntu
sudo yum update -y      # linux

sudo yum install httpd -y
sudo systemctl start httpd
sudo systemctl enable httpd

# static web
cd /var/www/html
sudo vi index.html

# nginx
sudo dnf update -y
sudo dnf install nginx -y
sudo ss -tulpn | grep :80
sudo systemctl stop httpd
sudo systemctl disable httpd
sudo systemctl start nginx
sudo systemctl enable nginx
sudo nano /etc/nginx/conf.d/nginx.conf
sudo nginx -t
sudo systemctl reload nginx

# git
sudo dnf install git-all

# Docker
sudo dnf update -y
sudo yum install -y docker
# sudo amazon-linux-extras install docker -y
sudo systemctl enable --now docker
sudo systemctl start docker
sudo usermod -a -G docker ec2-user

# tao thu muc chu CLI plugin compose, buildx
sudo mkdir -p /usr/local/lib/docker/cli-plugins

# compose
ARCH=$(uname -m)
GH_HOST="https://github.com"
COMPOSE_PATH="docker/compose/releases/latest/download/docker-compose-linux-${ARCH}"

sudo curl -fSL "${GH_HOST}/${COMPOSE_PATH}" -o /usr/local/lib/docker/cli-plugins/docker-compose
sudo chmod +x /usr/local/lib/docker/cli-plugins/docker-compose

# buildx
ARCH=$(uname -m)
if [ "$ARCH" = "x86_64" ]; then BX_ARCH="amd64"; else BX_ARCH="arm64"; fi

GH_HOST="https://github.com"
BX_VER="v0.19.3"
BX_PATH="docker/buildx/releases/download/${BX_VER}/buildx-${BX_VER}.linux-${BX_ARCH}"

sudo curl -fSL "${GH_HOST}/${BX_PATH}" -o /usr/local/lib/docker/cli-plugins/docker-buildx
sudo chmod +x /usr/local/lib/docker/cli-plugins/docker-buildx

sudo chmod +x /usr/local/lib/docker/cli-plugins/docker-compose /usr/local/lib/docker/cli-plugins/docker-buildx

docker --version
docker compose version
docker buildx version


# Java (21)
java -version
sudo dnf update -y
sudo dnf install -y java-21-amazon-corretto-devel
sudo dnf install maven -y
chmod +x mvnw
./mvnw clean package -DskipTests
sudo mvn clean package -DskipTests
sudo docker compose -p search-service -f compose.yml up -d --build

```

## Docker Compose Commands

- `sudo` required

```bash
docker compose -p search-service -f compose.yml up -d --build
docker compose -p search-service -f compose.yml down
docker compose -p search-service -f compose.yml up -d --scale search-service=2


```

## Java Spring Boot (Without Docker)

```bash

mvn clean package -DskipTests
mvn spring-boot:run "-Duser.timezone=Asia/Ho_Chi_Minh"
```

## Accessing EC2 public IP

```
#public IP
http://<EC2_PUBLIC_IP>/search/health

# EC2 SSH
curl -i http://localhost/search/health
curl -X GET http://localhost/search/health
```
