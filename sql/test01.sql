CREATE DATABASE edumon;

USE edumon;

CREATE TABLE test1(
	no int PRIMARY KEY AUTO_INCREMENT, 
	id VARCHAR(20),
	com VARCHAR(2000));
	
INSERT INTO test1 VALUES(DEFAULT, 'kim', '오늘 처음 스프링부트');
INSERT INTO test1 VALUES(DEFAULT, 'lee', '스프링 부트 더미 테스트');
COMMIT;
SELECT * FROM test1;

-- 회원 테이블 생성
CREATE TABLE euser(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	password VARCHAR(300) NOT NULL,
	username VARCHAR(50) NOT NULL,
	email VARCHAR(100) NOT NULL,
	address VARCHAR(300),
	tel VARCHAR(20),
	regdate DATETIME DEFAULT CURRENT_TIMESTAMP,
	lev VARCHAR(20) DEFAULT 'USER',
	act VARCHAR(20) DEFAULT 'JOIN',
	CONSTRAINT key_name UNIQUE(name)
);

-- 회원 테이블 더미 데이터
INSERT INTO euser VALUES(DEFAULT, 'admin', '1234', '관리자', 'admin@edumon.com', NULL, '010-1234-5678', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO euser VALUES(DEFAULT, 'test1', '1234', '테스트', 'test1@edumon.com', NULL, '010-2034-5039', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO euser VALUES(DEFAULT, 'kim', '1234', '김이름', 'kim@edumon.com', NULL, '010-5487-2223', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO euser VALUES(DEFAULT, 'lee', '1234', '이이름', 'lee@edumon.com', NULL, '010-7856-6654', DEFAULT, DEFAULT, DEFAULT);

UPDATE euser SET PASSWORD='$2a$10$KXY.EhEskta7wG/HvMSeZ.CQ4FuGQZOmaHTL2eZPnidD6AUvc.rUS'

COMMIT;

SELECT * FROM euser;

-- 게시판 테이블 생성
CREATE TABLE free(
	fno INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	title VARCHAR(50) NOT NULL,
	content VARCHAR(2000) NOT NULL,
	resdate TIMESTAMP DEFAULT CURRENT_TIME,
	views INT DEFAULT 0,
	recommend INT DEFAULT 0
);

-- 댓글 테이블 생성
CREATE TABLE freeComment(
	cno INT PRIMARY KEY AUTO_INCREMENT,
	fno INT NOT NULL,
	name VARCHAR(20) NOT NULL,
	content VARCHAR(2000) NOT NULL,
	resdate TIMESTAMP DEFAULT CURRENT_TIME,
	recommend INT DEFAULT 0,
	FOREIGN KEY (fno) REFERENCES free(fno) ON DELETE CASCADE
);
SELECT cno, fno, name, content, 
case
	when DATEDIFF(NOW(), resdate) > 0
	then CONCAT(DATEDIFF(NOW(), resdate), '일 전')
	when TIMESTAMPDIFF(HOUR, resdate, NOW()) > 0
	then CONCAT(TIMESTAMPDIFF(HOUR, resdate, NOW()), '시간 전')
	ELSE CONCAT(TIMESTAMPDIFF(MINUTE, resdate, NOW()), '분 전')
END AS resdate, recommend
FROM freeComment;