# 2022-01-08
* EC2,RDS 서버 구축 100%
* dev/prod 폴더 나누어서 서브 도메인 적용 100%
* 서브 도메인별 SSL 적용(cerbort) 100%
* git에 스프링 부트 템플릿 적용 100%
* ERD 설계 85%
* API 명세서 템플릿 작성 100%

# 2022-01-09
* ERD 리팩토링 90%
  * CheckPoint테이블을 삭제하고 Product테이블에 checkPointImageUrl컬럼 추가
  * Brand -> Product 구조를 Product -> Brand 구조로 변경하면서 중복데이터 방지
* Spring Boot Security Setting (OAuth 사용을 위한) 80%
  * SecurityConfig 작성
    * CORS 지원 활성화
    * 권한 부여
  * CustomOauth2UserService 작성 
    * SNS 로그인시 데이터 베이스 저장 구현
  * OAuthAttribute 작성
    * 무슨 SNS로 로그인하였는지 구별
  * application-oauth.yml 추가
    * Naver, Google 설정 추가
    * application.yml에 profile - oauth 추가
* 회원가입 API validator 적용으로 validation 구현 60%
  * validator 에러 메세지 출력을 위해 BaseResponse 수정
* .gitignore 추가
  
# 2022-01-10
* ERD 리팩토링 92%
  * User테이블에 이용약관 동의 관련 컬럼 추가
  * 회의 결과, Coupon테이블을 product, user 용 두가지 테이블 구현
* 회원가입 API validator 적용으로 validation 구현 100%
  * Validation error 메세지 처리를 위해 refineErrors라는 메세지 정제 함수 생성
  * Validation 정규식 구현 100%
  * Validation Test 100%
  * JWT 토큰 생성
* 로그인API 구현 100%
  * Validation 구현
  * 로그인 성공시 토큰 생성
* API 명세서
  * 로그인, 회원가입 api 추가 
* UserController 리팩토링
  * 권장하지 않는 의존성 주입인 @Autowired 대신 생성자 @RequiredArgsConstructor로 의존성 주입 변경
  * @RestController에 이미 @ResponseBody 기능이 들어가 있으므로 불필요한 @ResponseBody 제거
* 휴대폰 인증 API 추가 20%