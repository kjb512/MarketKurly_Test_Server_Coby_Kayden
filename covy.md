# 개발일지



01/08일 일지

- ERD 설계
  - https://aquerytool.com/aquerymain/index/?rurl=5496db81-0301-4982-adf5-a2384bee329d
  - password : o15b12 
  - 1차안 클라이언트 공유

- 기획서 작성
  - 1차 피드백
    - 앱설정의 우선순위 -> 수정
    - 상품기능 세부기획(애매모호함) -> 수정
    - 주문하기 기능이 없어도 더미데이터를 통해 주문 조회 가능 -> 더미데이터를 통해 기능상 우선순위 변경 가능 인지, 하지만 회의에서 주문하기 기능이 이커머스의 필수 기능이라 판단해 그대로 진행
- 기획 회의
  - 상품 세부 정보는 이미지 형태로 준비
  - SNS 로그인 원래 없지만 추후 추가
  - 카테고리 리스트가 너무 많으므로 큰 카테고리 3개 기준으로 그 안에 또 3개씩 구현 (위에부터)
  - 서버 branch 전략 main<-dev<-kayden, covy



01/09일 일지

- ERD 수정

  - 상품 세부정보 변경에 따라 CheckPointSub 등 테이블 변경

  - 기능 구현에 있어 부족한 부분들 추가 및 수정

    - - 상품에서 선물하기 가능한지
      - 세일 기간 (마감기한)
      - 쿠폰
      - 상품 리스트들 볼때 하단에 kurly only/한정수량 같은 정보들
      - Product 정보를 사진으로 바꾸면서 상품설명 사진 /상세정보 사진 각 테이블 추가 

- AWS E2C 

  - Sub domain 및 SSL 적용

- DataGrip Kayden RDS 연결



01/10일 일지

- 기획 회의
  - 개발 진행 상황 공유
  - 컬리 추천부분 우선 순위 설정
    - 후기 ~개 돌파
    - 마감 세일
    - 인기 신상품
    - kurly only 추천 상품
  - S3에 이미지 저장
  - 쿠폰은 후순위로 개발
- ERD로 RDS 테이블 생성
- product API 개발 시작



01/11일 일지

- 1차 피드백

  - ERD

    - ERD 전체적인 테이블 수 부족 -> 9개에서 27개로 보완 완료
    - Flag 컬럼들이 네이밍이 명확하지 않음 -> 수정 완료
    - address,imageUrl 등 varchar 타입 대신 TEXT 타입 변경 -> 수정 완료
    - Question 답변 테이블 제작 -> 

    - Brand 테이블 subtitle이 있으니 name을 title로 통일 -> 수정 완료
    - subCIdx같이 컬럼명을 줄이는 것보다 알아 볼 수 있게 전체를 써도 좋다 -> 수정 완료
    - Product테이블 Price varchar에서 Int로 변경 -> 수정 완료
    - Product테이블 Delivery 네이밍이 명확하지 않음 -> deliveryType 으로 변경 완료
    - 안내사항 같은 줄 바꿈이 있는 텍스트는 배열 형식으로 -> 안내사항 테이블 생성으로 수정 완료

  - 추가적으로 더미데이터는 실제처럼, Api 명세서 리스트업 먼저 하는게 좋음
  - prod,dev 구현 확인  - 나누었지만 403이 나와 수정할 것

- 기획 회의
  - ERD 피드백 바탕으로 수정
  - Table 추가
    - Answer
    - Deliveryinfo
    - PackagingType
    - Address
    - ProductGuide
    - Allergy
    - ProductAllergy
    - Payment
    - SearchKeyword
    - Order
    - Cart
    - Likes
    - Coupon
    - CouponUser
    - CouponProduct
    - Event
    - CartProduct
    - PaymentCard
    - PaymentPhone
- DTO 클래스 생성



01/12일 개발일지



- ERD 재설계한 바탕으로 Table 생성

  - Trouble shooting

    - ```
      CREATE TABLE Order
      (
          `orderIdx`     INT            NOT NULL    AUTO_INCREMENT,
          `userIdx`      INT            NOT NULL,
          `cartIdx`      INT            NOT NULL,
          `paymentType`  INT            NOT NULL,
          `createAt`     TIMESTAMP      NOT NULL    DEFAULT CURRENT_TIMESTAMP,
          `updateAt`     TIMESTAMP      NOT NULL    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
          `status`       VARCHAR(10)    NOT NULL    DEFAULT 'ACTIVE',
           PRIMARY KEY (orderIdx)
      );
      ```

    - order 테이블 생성시 Order가 명령어로 인식되어 오류 발생
    - 'order' 형식으로 따옴표 추가해서 테이블 생성 -> 해결

- 기획 회의

- 개발

  - DTO 클래스 수정
  - product 상세정보 api 개발 진행
  
  

01/13일 개발일지

- Product 상세정보 조회 구현
- product category로 리스트 조회 구현 시작
- covy->dev merge 중에 .idea 폴더 conflict -> dev에 idea로 덮어씌워 해결



01/14일 개발일지

- category, subcategory 조회 api 개발 완료 및 명세서 작성
- cart api 개발 시작
- cart product delete는 status 변경으로



01/15일 개발일지

- cart api query 개발
- ERD 수정 회의
  - Order 고려한 cart,order 컬럼들 수정
  - group by를 통한 카운트 -> cartProduct 중복 개수를 count 컬럼을 통한 카운트
- cart 로직 수정으로 인한 query 수정



01/16일 개발일지

- cart api 개발 및 명세 완료
- review api, question api 개발시작
  - 사진 업로드
    - S3에 저장
    - https://devlog-wjdrbs96.tistory.com/323



01/17일 개발일지

- reivew api, question api 개발 60%
- product 더미데이터 제작 (대략 70개)
- 프론트와 더미데이터 관련 회의
  - 광고 부분은 프론트 측에서
  - 각 카테고리/섹션 별로 5~6개씩 데이터 제작





