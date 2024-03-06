[starter-project]

첫 스타트를 위한 데모 프로젝트 [Swagger, SpringSecurity 기반]

프로젝트 네임 변경 방법
1. 파일 - Project Structure (맥 : cmd + ;)
2. settings.gradle -> rootProject.name 수정
3. 새로고침

[요구사항]

기술스택 : Java17 / Springboot3.x / SpringSecurity6 / JPA / H2 / Gradle / OpenAPI3 / JWT / OAuth

- 회원가입, 로그인, UserPage(ROLE_USER), AdminPage(ROLE_ADMIN)
1. 회원가입
    1. EMAIL, PASSWORD, NAME
    2. EMAIL - EMAIL 형식에 맞게  
       3. Ex) prjs3643@gmail.com
    3. PASSWORD - 8자 이상, 영어 대소문자, 특수문자 포함 
       4. Ex) Qwer12#$
    4. NAME - 2글자 이상
2. 로그인
    1. ID, PASSWORD
    2. 첫 로그인은 아이디 / 패스워드를 통해 로그인
    3. 로그인 이후 JWT를 받아 로그인
    4. 응답 헤더에 Authorization 추가
    5. 응답으로는 accessToken을 return
    6. ADMIN은 미리 DB에 값을 세팅
       7. (admin / 1234)
    7. 일반 로그인과 소셜 로그인을 나누어 개발 —> 미정
3. UserPage
    1. ROLE_USER 권한을 지닌 유저만 접근 가능
    2. 단순 네임을 return
4. AdminPage
    1. ROLE_ADMIN 권한을 지닌 유저만 접근 가능
    2. 단순 네임을 return
