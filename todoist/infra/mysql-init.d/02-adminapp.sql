-- Create database
CREATE DATABASE IF NOT EXISTS `adminappdb`
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

-- Create application user (safe if re-run)
CREATE USER IF NOT EXISTS 'adminappdbuser'@'%' IDENTIFIED BY 'adminappdbuserpwd';

-- Grant privileges on just this database
GRANT ALL PRIVILEGES ON `adminappdb`.* TO 'adminappdbuser'@'%';

-- Apply
FLUSH PRIVILEGES;