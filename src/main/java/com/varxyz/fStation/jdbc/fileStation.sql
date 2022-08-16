CREATE TABLE File(
	fId			BIGINT 			PRIMARY KEY AUTO_INCREMENT,
	passwd		VARCHAR(17)		NOT NULL,
	url 		VARCHAR(300)	NOT NULL,
	fileOriName	VARCHAR(500)	NOT NULL,
	fileName 	VARCHAR(500)	NOT NULL,
	fileSize	BIGINT			NOT NULL,
	fileType	VARCHAR(10)		NOT NULL, -- IMG, VIDEO
	regDate		TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP
)AUTO_INCREMENT 1001;

CREATE TABLE Text (
	tId 		BIGINT			PRIMARY KEY AUTO_INCREMENT,
	passwd 		VARCHAR(17)		NOT NULL,
	content		VARCHAR(16350)	NOT NULL,
	regDate		TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP
)AUTO_INCREMENT 2001;

CREATE TABLE Board(
	bId			BIGINT			PRIMARY KEY AUTO_INCREMENT,
	title		VARCHAR(200)	NOT NULL,
	nickName	VARCHAR(30)		NOT NULL,
	passwd		VARCHAR(4)		NOT NULL,
	content		VARCHAR(300)	NOT NULL,
	regDate		TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP
)AUTO_INCREMENT 5062;

CREATE TABLE BoardImage(
	biId		BIGINT			PRIMARY KEY AUTO_INCREMENT,
	boardFk		BIGINT			NOT NULL,
	imgName		VARCHAR(200)	NOT NULL,
	imgOriName	VARCHAR(300)	NOT NULL,
	imgUrl		VARCHAR(500)	NOT NULL,
	CONSTRAINT BoardImage_boardFk_FK FOREIGN KEY (boardFk) REFERENCES Board(bId)
)AUTO_INCREMENT 3001;

INSERT INTO Board (title, nickName, passwd, content) VALUES ('좀 긴게 예쁜거 같아','nam', '1111', '아무리 테스트지만 내용을 들어갑시다');
INSERT INTO Board (title, nickName, passwd, content) VALUES ('22','nam', '2222', '아무리 테스트지만 내용을 들어갑시다');
INSERT INTO Board (title, nickName, passwd, content) VALUES ('33','nam', '3333', '아무리 테스트지만 내용을 들어갑시다');
INSERT INTO Board (title, nickName, passwd, content) VALUES ('44','nam', '4444', '아무리 테스트지만 내용을 들어갑시다');
INSERT INTO Board (title, nickName, passwd, content) VALUES ('55','nam', '5555', '아무리 테스트지만 내용을 들어갑시다');


SELECT COUNT(*) FROM Board;

SELECT * FROM File;
SELECT * FROM Text;
SELECT * FROM Board;
SELECT * FROM BoardImage;

UPDATE Board SET content = 'test test test' WHERE bId = '1';


DROP TABLE File;
DROP TABLE Text;
DROP TABLE BoardImage;
DROP TABLE Board;