# CodindTest

이 리포지토리는 (주)알엠소프트의 2023년도 후반기 JAVA 웹개발자 대규모 채용 과정의 1차 서류 심사를 통과한 인원들에게 주어지는 
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
      - <b>Member 테이블</b> : member_id를 pk로 이외에는 사용자명을 갖는 테이블
    - ##### 로깅 관련 TABLE
      - <b>transaction_RENT</b> : book 과 회원의 index를 fk로 갖는 테이블
        - 빌려간 날짜 : TIMESTAMP WITH ZONE , 기본값은 SYSTIMESTAMP
      - <b>transaction_RETURN</b> : book 과 회원의 index를 fk로 갖는 테이블
        - 돌려준 날짜 : TIMESTAMP WITH ZONE , 기본값은 SYSTIMESTAMP
  - #### 2023.09.14 : Database/Schema 생성 및 테이블 생성, Backend 와 연결
      - <b> 테이터베이스 생성 </b> : UTF8 의, UNICODE_CI 방식으로 대소문자 구별없이 정렬하는 db 파일 생성
      - <b> 테이블 생성 </b> 설계상의 테이블을 구체적으로 type 지정함.
      - 밑은 최종 테이블들이며 <ins>밑줄은 pk</ins> , _이탤릭은 fk_ 을 나타냄
        - ##### KDC_CODE { <ins>KDC_MAIN</ins> : usigned smallint , <ins>KDC_DIV</ins> : unsigned smallint }
          - KDC_MAIN과 KDC_DIV 모두 2 바이트의 값을 가지는 unsigned smallint 사용
          - 처음엔 LITERAL 을 쓰는것을 검토했으나 KDC 1판부터 6판까지 3자릿수의 숫자를 사용하는것은 변하지 않았고 원본이 되는 DDC 또한 3자리 원칙을 고수하였기에 smallint를 사용하기로 결정.
        - ##### AUTHOR { <ins>AUTHOR_ID</ins> : bigint , AUTHOR_NAME_FIRST : varchar(35), AUTHOR_NAME_LAST : varcahr(35) }
          - index값에 포용할수 있는 범위가 가장 큰 값을 넣는 db관리 관행대로 bigint를 적용하기로 결정
          - 저자의 이름 초성+숫자로 저장하는 LITERAL 또한 검토하였으나 한글 저자 특성상 저장이 직관적이지 않아 숫자와 이름을 분리
          - 이름 관련
            - 영문 이름 : 영국 정부 Government Data Standards Catalogue 에 따르면 35+35자 조합을 권장함
            - 한글 이름 : 한국 등본 시스템상 성 1자 이름 5자 로, 영문 이름 제한보다 낮기에 영문 이름을 기준으로 삼음.
        - ##### MEMBER { <ins>MEMBER_ID</ins> : varchar(15) , MEMBER_NAME_FIRST : varchar(35) ,MEMBER_NAME_LAST varchar(35) }
          - member가 회원 등록시 적용하는 member_ID는 중복되어 사용되면 안됨으로 PK 설정
          - NAME TABLE 을 따로 빼내어 AUTHOR와 공유하는 방법을 생각해 보았으나 복잡도만 증가하는거 같아 보류함.
        - ##### BOOK { <ins>ISBN</ins> : bigint 
