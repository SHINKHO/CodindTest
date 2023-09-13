# CodindTest

이 리포지토리는 (주)알엠소프트의 2023년도 후반기 JAVA 웹개발자 대규모 채용 과정의 1차 서류 심사를 통과한 인원들에게 주어지는 <br>
2차 코딩 테스트 심사에 쓰인 코드와 개발과정, 참조 등을 기록하기 위한 리포지토리 입니다.

## 코딩 테스트 1번 

- 구현 내용 : RESTful을 이용하여 백앤드 API 서버를 구성 및 AWS CI/CD 시스템 구축, 다음 기능을 구현 <br>
  - 사용자 :
    - 회원가입
  - 도서관리
  
    - 도서를 등록한다
    - 등록된 도서를 수정한다
    - 등록된 도서에 대한 대출이력을 확인한다
    - 아이디를 입력 후 도서에 대한 대출처리를 한다
    - 도서에 대하여 반납처리한다 </br>
- 사용기술 : Amazon EC2 , JAVA , SPRING , 웹 스택(HTML,CSS,JS)

### 진행사항

- #### 2023.09.12 : 프로젝트 시작 , aws mfcec2 인스턴스 생성 , 사용기술 선정
  - 요구사항중 매우 강조되는 amazon ec2 서버에 대해 학습 
  - 익숙한 <b>Spring boot</b> 의 <b>RESTful api</b> 를 백엔드로, <b>mariaDB</b>를 DB로 사용하고, <b>JPA</b>로 DB관리

- #### 2023.09.13 : DB 유저와 테이블 설계 
  - DB에 USER 생성 Librarian. 
  - 책 분류를 위해 한국십진분류법(KDC)을 도입
    - https://if-blog.tistory.com/10987 교육부 블로그 참조
    - 한국십진분류법 제 6판 설명회(2013,문지현 외) 참조
    - ##### 도서 관련 TABLE
      - <b>classification</b> 테이블 : KDC 기준 주 분류와 차 분류의 슈퍼키를 가진 테이블
      - <b>author</b> : index값과 저자의 이름을 가진 테이블
      - <b>book</b> : classification 과 author를 fk로 가지는 책 테이블
        - 중복 가능한 제목을 가짐.
        - 정기 간행,시리즈물일경우 version 으로 몇 번째인지 명시
        - 단순 갯수를 나타내는 Unique key count로 전체 갯수 명시
        - 해당 도서의 isbn를 pk로 사용.
    - ##### 사용자 관련 TABLE
      - 기간상 문제로 jwt 등의 보안 요소를 적용하기 버거워 사용자 비밀번호는 적용하지 않음.
      - <b>오프라인에서 회원 관리를 한다고 가정함</b>
    - ##### 로깅 관련 TABLE
      - <b>transaction_RENT</b> : book 과 회원의 index를 fk로 갖는 테이블
        - 빌려간 날짜 : TIMESTAMP WITH ZONE , 기본값은 SYSTIMESTAMP
      - <b>transaction_RETURN</b> : book 과 회원의 index를 fk로 갖는 테이블
        - 돌려준 날짜 : TIMESTAMP WITH ZONE , 기본값은 SYSTIMESTAMP
    
