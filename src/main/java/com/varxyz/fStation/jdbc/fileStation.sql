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
	content		VARCHAR(16350)	NOT NULL,
	deleteCheck	VARCHAR(3)		NOT NULL DEFAULT 'NO',
	regDate		TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP,
	deleteDate 	TIMESTAMP		NOT NULL DEFAULT (DATE_ADD(NOW(), INTERVAL 1 day))
)AUTO_INCREMENT 2001;

CREATE TABLE Board(
	bId				BIGINT			PRIMARY KEY AUTO_INCREMENT,
	title			VARCHAR(200)	NOT NULL,
	nickName		VARCHAR(30)		NOT NULL,
	passwd			VARCHAR(4)		NOT NULL,
	content			VARCHAR(1000)	NOT NULL,
	adminContent	VARCHAR(500),
	regDate			TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP
)AUTO_INCREMENT 5062;

CREATE TABLE BoardImage(
	biId		BIGINT			PRIMARY KEY AUTO_INCREMENT,
	boardFk		BIGINT			NOT NULL,
	imgName		VARCHAR(200)	NOT NULL,
	imgOriName	VARCHAR(300)	NOT NULL,
	imgUrl		VARCHAR(500)	NOT NULL,
	CONSTRAINT BoardImage_boardFk_FK FOREIGN KEY (boardFk) REFERENCES Board(bId)
)AUTO_INCREMENT 3001;


SELECT * FROM File;
SELECT * FROM Text;
SELECT * FROM Board;
SELECT * FROM BoardImage;

DROP TABLE File;
DROP TABLE Text

ALTER TABLE File convert to charset utf8;
ALTER TABLE Text convert to charset utf8;
ALTER TABLE Board convert to charset utf8;
ALTER TABLE File convert to charset utf8;
