CREATE TABLE File(
	fId			BIGINT 			PRIMARY KEY AUTO_INCREMENT,
	passwd		VARCHAR(20)		NOT NULL,
	url 		VARCHAR(300)	NOT NULL,
	fileOriName	VARCHAR(500)	NOT NULL,
	fileName 	VARCHAR(500)	NOT NULL,
	fileSize	BIGINT			NOT NULL,
	fileType	VARCHAR(10)		NOT NULL, -- IMG, VIDEO
	deleteCheck	VARCHAR(3)		NOT NULL DEFAULT 'NO',
	regDate		TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP,
	deleteDate 	TIMESTAMP		NOT NULL DEFAULT (DATE_ADD(NOW(), INTERVAL 1 day))
)AUTO_INCREMENT 1001;

CREATE TABLE Text (
	tId 		BIGINT			PRIMARY KEY AUTO_INCREMENT,
	passwd 		VARCHAR(20)		NOT NULL,
	content		LONGTEXT	NOT NULL,
	deleteCheck	VARCHAR(3)		NOT NULL DEFAULT 'NO',
	regDate		TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP,
	deleteDate 	TIMESTAMP		NOT NULL DEFAULT (DATE_ADD(NOW(), INTERVAL 1 day))
)AUTO_INCREMENT 2001;

CREATE TABLE Board(
	bId				BIGINT			PRIMARY KEY AUTO_INCREMENT,
	title			VARCHAR(200)	NOT NULL,
	nickName		VARCHAR(30)		NOT NULL,
	passwd			VARCHAR(4)		NOT NULL,
	content			VARCHAR(5000)	NOT NULL,
	adminContent	VARCHAR(500),
	regDate			TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP
)AUTO_INCREMENT 5062;


INSERT INTO Board (title, nickName, passwd, content) VALUES ('테스트 중입니다.건의사항 처리부탁드립니다','윤정','1111','안녕하세요.테스트 중입니다')
DELETE FROM File WHERE FId=1045;

SELECT * FROM File;
SELECT * FROM Text;
SELECT * FROM Board;
SELECT * FROM BoardImage;

DROP TABLE File;
DROP TABLE Text
DROP TABLE Board;

ALTER TABLE File convert to charset utf8;
ALTER TABLE Text convert to charset utf8;
ALTER TABLE Board convert to charset utf8;
ALTER TABLE File convert to charset utf8;


SELECT * FROM Text WHERE passwd = 'tkdgnsrkt1!' AND deleteCheck = 'NO' ORDER BY regDate DESC;
