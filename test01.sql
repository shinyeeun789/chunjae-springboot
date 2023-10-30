CREATE DATABASE edumon;

USE edumon;

CREATE TABLE test1(
	no int PRIMARY KEY AUTO_INCREMENT, 
	id VARCHAR(20),
	com VARCHAR(2000));
	
INSERT INTO test1 VALUES(DEFAULT, 'kim', '오늘 처음 스프링부트');
INSERT INTO test1 VALUES(DEFAULT, 'lee', '스프링 부트 더미 테스트');
COMMIT;