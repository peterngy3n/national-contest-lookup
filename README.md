# National Contest Lookup API

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue.svg)](https://www.docker.com/)

Hệ thống tra cứu kết quả thi THPT Quốc gia với các tính năng báo cáo thống kê và xếp hạng học sinh.

## 🚀 Tính năng chính

- **📋 Tra cứu điểm thi** theo số báo danh
- **📊 Báo cáo thống kê** theo từng môn học (phân loại: Xuất sắc, Giỏi, Khá, Trung bình)
- **🏆 TOP 10 học sinh** theo khối A (Toán - Lý - Hóa)
- **⚡ Cache tối ưu** với Spring Cache để tăng tốc độ phản hồi
- **🔍 RESTful API** với response chuẩn

## 🛠️ Tech Stack

- **Backend**: Spring Boot 3.5.3, Spring Data JPA, Spring Cache
- **Database**: MySQL 8.0 + Flyway Migration
- **Build Tool**: Maven
- **Java Version**: 21
- **Container**: Docker & Docker Compose
- **Architecture**: ORM-based (không sử dụng Native Query)

## 📂 Cấu trúc project

```
national-contest-lookup/
├── src/main/java/com/timothy/national_contest_lookup/
│   ├── controller/          # REST Controllers
│   ├── service/            # Business Logic
│   ├── repository/         # Data Access Layer
│   ├── entity/            # JPA Entities
│   ├── dto/               # Data Transfer Objects
│   ├── mapper/            # Object Mapping
│   ├── config/            # Configuration Classes
│   ├── seeder/            # Seeder Classes
│   └── exception/         # Exception Handling
├── src/main/resources/
│   ├── db/migration/      # Flyway SQL Scripts
│   └── application.yaml   # Configuration
├── docker-compose.yml     # Container Orchestration
├── Dockerfile            # Application Container
└── README.md
```

## 🚦 API Endpoints

### 📊 Score Management
```
GET  /api/v1/scores/{sbd}           # Tra cứu điểm theo số báo danh
GET  /api/v1/scores/report/top10    # TOP 10 thí sinh có điểm khối A cao nhất
GET  /api/v1/scores/report/{subject}# Báo cáo theo môn học cụ thể
```
- **MySQL 8.0** (nếu chạy local)

### 🐳 Chạy với Docker (Khuyến nghị)

1. **Clone repository**
```bash
git clone https://github.com/peterngy3n/national-contest-lookup
cd national-contest-lookup
```

2. **Chạy docker-compose**
```bash
docker-compose up --build
```

**Lưu ý 1: SAU KHI CHẠY LỆNH DOCKER, 2 CONTAINER CỦA DATABASE VÀ SPRING SẼ ĐƯỢC TẠO, ĐỒNG THỜI DATA TỪ FILE CSV SẼ ĐƯỢC IMPORT VÀO MYSQL**
**Lưu ý 2: NẾU CHẠY TEST FRONTEND Ở LOCAL, HÃY THAY ĐỔI URL CỦA FRONTEND TRONG FILE /src/main/config/WebConfig.java để tránh lỗi CORS**

### Environment Variables (Nếu chạy local không cần tạo file .env vì code đã thiết lập sẵn các giá trị mặc định)

| Variable | Default | Description |
|----------|---------|-------------|
| `DB_HOST` | localhost | MySQL host |
| `DB_PORT` | 3306 | MySQL port |
| `DB_NAME` | scoredb | Database name |
| `DB_USERNAME` | root | Database username |
| `DB_PASSWORD` | 12345678 | Database password |
| `SERVER_PORT` | 8080 | Application port |
| `CONTEXT_PATH` | /api/v1 | API context path |
| `LOG_LEVEL` | INFO | Logging level |
| `JPA_DDL_AUTO` | update | Hibernate DDL mode |

### 📁 File .env mẫu
```bash
# Database
DB_HOST=localhost
DB_PORT=3306
DB_NAME=scoredb
DB_USERNAME=root
DB_PASSWORD=12345678

# Server
SERVER_PORT=8080
CONTEXT_PATH=/api/v1

# Application
FLYWAY_ENABLED=true
JPA_DDL_AUTO=update
LOG_LEVEL=INFO
```

## 🔄 Database Migration & Seeder

Project sử dụng **Flyway** để quản lý database schema:

```bash
# Migration files trong: src/main/resources/db/migration/
V1__Create_scores_table.sql
```

## Deployment
Backend và Database của project đang được host bằng VM của Google Cloud Platform
