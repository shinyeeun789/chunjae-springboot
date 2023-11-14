CREATE DATABASE tstore;

USE tstore;

CREATE TABLE role(
	roleId INT PRIMARY KEY AUTO_INCREMENT,
	role VARCHAR(255) DEFAULT NULL
);

CREATE TABLE user(
	id INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(50) NOT NULL UNIQUE KEY,
	password CHAR(80) NOT NULL,
	NAME VARCHAR(50) NOT NULL,
	email VARCHAR(200) NOT NULL,
	active INT DEFAULT 0
);

CREATE TABLE user_role(
	user_id INT NOT NULL,
	role_id INT NOT NULL,
	PRIMARY KEY (user_id, role_id)
);

