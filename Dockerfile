# Sử dụng JDK nhẹ
FROM eclipse-temurin:17-jdk-alpine

# Tạo thư mục làm việc
WORKDIR /app

# Copy file jar từ target vào container
COPY target/QL50-0.0.1-SNAPSHOT.jar app.jar

# Mở port (trùng với server.port trong application.properties)
EXPOSE 8080

# Lệnh chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
