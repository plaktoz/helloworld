-- Create database
CREATE DATABASE IF NOT EXISTS `keycloakdb`
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

-- Create application user (safe if re-run)
CREATE USER IF NOT EXISTS 'keycloakdbuser'@'%' IDENTIFIED BY 'keycloakdbuserpwd';

-- Grant privileges on just this database
GRANT ALL PRIVILEGES ON `keycloakdb`.* TO 'keycloakdbuser'@'%';

-- Apply
FLUSH PRIVILEGES;