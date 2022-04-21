DROP DATABASE IF EXISTS jspCommunity;
CREATE DATABASE jspCommunity;
USE jspCommunity;

# 회원 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `name` CHAR(50) NOT NULL,
    `nickname` CHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    loginId CHAR(50) NOT NULL UNIQUE,
    loginPw VARCHAR(200) NOT NULL,
    adminLevel TINYINT(1) UNSIGNED NOT NULL DEFAULT 2 COMMENT '0=탈퇴/1=로그인정지/2=일반/3=인증/4=관리자'
);

# 회원1 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
`name` = "강호성",
`nickname` = "yamto",
`email` = "2601765@naver.com",
loginId = "user1",
loginPw = "user1";

# 회원2 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
`name` = "김미소",
`nickname` = "이또한지나가리라",
`email` = "2601765@naver.com",
loginId = "user2",
loginPw = "user2";

# 게시판 테이블 생성
CREATE TABLE board (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `code` CHAR(10) NOT NULL UNIQUE,
    `name` CHAR(10) NOT NULL UNIQUE
);

# 공지사항 게시판 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = "notice",
`name` = "공지사항";

# 방명록 게시판 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = "guestBook",
`name` = "방명록";

# 자유 게시판 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = "free",
`name` = "자유";

# 테스트 게시물 테이블 생성
CREATE TABLE article  (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    boardId INT(10) UNSIGNED NOT NULL,
    title CHAR(100) NOT NULL,
    `body` LONGTEXT NOT NULL,
    hitsCount INT(10) UNSIGNED NOT NULL DEFAULT 0
);

# 게시물1 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 1,
title = "제목1",
`body` = "내용1";

# 게시물2 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 1,
title = "제목2",
`body` = "내용2";

# 게시물3 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 1,
title = "제목3",
`body` = "내용3";

# 게시물4 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
boardId = 1,
title = "제목4",
`body` = "내용4";

# 게시물5 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
boardId = 1,
title = "제목5",
`body` = "내용5";

# adminLevel 칼럼을 authLevel로 변경
ALTER TABLE `member` CHANGE `adminLevel` `authLevel` TINYINT(1) UNSIGNED DEFAULT 2 NOT NULL COMMENT '0=탈퇴/1=로그인정지/2=일반/3=인증/4=관리자';

# cellphoneNo 칼럼 추가 및 칼럼 순서 재배열
ALTER TABLE `member` CHANGE `loginId` `loginId` CHAR(50) NOT NULL AFTER `updateDate`, CHANGE `loginPw` `loginPw` VARCHAR(200) NOT NULL AFTER `loginId`, ADD COLUMN `cellphoneNo` CHAR(20) NOT NULL AFTER `email`;