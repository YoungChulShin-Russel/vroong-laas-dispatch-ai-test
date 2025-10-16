-- MySQL 초기화 스크립트
-- order, dispatch 스키마 및 사용자 생성

-- 1. 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS `order` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS `dispatch` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 2. 사용자 생성 및 권한 부여
-- order 사용자
CREATE USER IF NOT EXISTS 'order_user'@'%' IDENTIFIED BY 'order_password';
GRANT ALL PRIVILEGES ON `order`.* TO 'order_user'@'%';

-- dispatch 사용자
CREATE USER IF NOT EXISTS 'dispatch_user'@'%' IDENTIFIED BY 'dispatch_password';
GRANT ALL PRIVILEGES ON `dispatch`.* TO 'dispatch_user'@'%';

-- 권한 새로고침
FLUSH PRIVILEGES;

-- 확인용 로그
SELECT 'Databases and users created successfully' AS status;