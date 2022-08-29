# FileStation
File Station

### Java Spring 기반 프로젝트
 <img src="https://img.shields.io/badge/Spring-6DB33F?style=flat&logo=Spring&logoColor=white"/>
 <img src="https://img.shields.io/badge/Apache Tomcat-F8DC75?style=flat&logo=Apache Tomcat&logoColor=white"/>
 <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white"/>
 <img src="https://img.shields.io/badge/Eclipse IDE-2C2255?style=flat&logo=Eclipse IDE&logoColor=white"/>
 <img src="https://img.shields.io/badge/Apache Maven-C71A36?style=flat&logo=Apache Maven&logoColor=white"/>
 <img src="https://img.shields.io/badge/Visual Studio Code-007ACC?style=flat&logo=Visual Studio Code&logoColor=white"/>
 <img src="https://img.shields.io/badge/Java-3178C6?style=flat&logo=Java&logoColor=white"/>
 <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=HTML5&logoColor=white"/>
 <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=CSS3&logoColor=white"/>
 <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=JavaScript&logoColor=white"/>
 <img src="https://img.shields.io/badge/Ajax-3178C6?style=flat&logo=Ajax&logoColor=white"/>
 <img src="https://img.shields.io/badge/Jsp-3178C6?style=flat&logo=Jsp&logoColor=white"/>
 <img src="https://img.shields.io/badge/jQuery-0769AD?style=flat&logo=jQuery&logoColor=white"/>
 <img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=GitHub&logoColor=white"/>
<p>로그인 없는 파일 업,다운로드 웹 사이트.</p>

개발기간 : 2022.08.09 ~ 2022.08.31

### 참여자

#### 박상훈
>    * 팀장
>    * 파일 업,다운로드 기능 구현
>    * 메인 페이지 화면 구현
>    * 데이터베이스 설계
>    * 관리자 댓글 관리 기능 구현
>    * Js담당
 
#### 지윤정
>    * 게시판 페이징 구현
>    * 게시판 화면 구현
>    * 관리자 화면 구현
>    * 관리자 확장자 별 통꼐 그래프

#### 남형주
>   * 게시판 글쓰기 구현
>    * 게시판 수정, 삭제 구현
>    * 관리자 월별 통계 그래프
>    * 서비스 소개화면 구현

#### 박희정
>    * 파일 다운로드 기능 구현
>    * zip다운로드 구현
>    * 자주하는 질문 화면 구현
>    * 관리자 월별 사용자 통계 그래프

#### 개발환경
>    + Java 11.0.15_9
>    + Maven
>    + Spring3
>    + JSP
>    + Apache Tomcat 8.5
>    + Mysql 8.0.29
>    + Eclipse 2020-03
>    + HTML5
>    + CSS3
>    + JavaScript
>    + Jquery
>    + Ajax

#### 주요기능
>    - 파일,텍스트 업로드
>    - 파일,텍스트 다운로드
>    - 스케쥴러를 이용한 자동 삭제
>    - 건의사항 게시판 등록, 수정, 삭제
>    - 건의사항 게시판 관리자 권한 댓글
>    - 사용자 검색
>    - 게시판 페이징
>    - 월별 데이터 사용량
>    - 월별 사용자 수
>    - 확장자별 업로드 수

#### DB
    1. File
    2. Text
    3. Board

#### 구현
>    * File
>    >  - 파일 업로드 시 배열을 이용해 MultipartFile을 이용해 통신.
>    >  - 파일, 텍스트 둘 중 하나만 업로드 되었을 시 처리.
>    >  - 비밀번호 보안에 대한 처리
>    >  - 스케줄러를 이용한 기준시간 이후의 파일, 텍스트의 삭제 처리

>   * Board
>    >  - 페이징 처리
>    >  - 수정 시 ajax로 화면 이동없는 수정 처리
>    >  - 관리자 댓글 시 사용자 수정과 같이 화면 이동없는 수정 처리
>    >  - 사용자 검색
