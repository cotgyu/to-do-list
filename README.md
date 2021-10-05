# To Do List

## 개요 

- 주제별로 관리하는 체크리스트 관리 

- 토이프로젝트 (21.02 ~ 21.10)

  <img src="https://github.com/cotgyu/to-do-list/blob/master/images/intro1.png?raw=true" width="650">
  <img src="https://github.com/cotgyu/to-do-list/blob/master/images/intro2.png?raw=true" width="650">

## 사용 기술

  - Java 11 
  - Spring Boot 2.3.x
    - Spring Batch
    - Spring Data JPA
    - Spring Security
    - Spring Rest Docs
    - Spring HATEOAS

  - Querydsl
  - Gradle
  - H2, MariaDB
  - Docker, Jenkins, AWS Code Deploy, AWS S3, AWS EC2



## 배포 과정

![배포 과정](images/deploy%20img.png)



## 프로젝트 환경

### Gradle 을 이용한 Multi Module 구성

- domain, web, batch 모듈로 구성

  - 공통 모듈 domain 

    <img src="https://github.com/cotgyu/to-do-list/blob/master/images/module2.png?raw=true" width="200">
    
    <img src="https://github.com/cotgyu/to-do-list/blob/master/images/module1.png?raw=true" width="550">


### 테스트 주도 개발 

  
  <img src="https://github.com/cotgyu/to-do-list/blob/master/images/testcase.png?raw=true" width="230">


## 주요 기능 소개 

### REST API 개발

- Uniform Interface 를 지키도록 개발

  - Hypermedia as the Engine of Application State(HATEOAS) :  **Spring HATEOAS 적용**

    <img src="https://github.com/cotgyu/to-do-list/blob/master/images/response.png?raw=true" width="560">


  - Self-descriptive message : **Spring Rest Docs 적용**

    <img src="https://github.com/cotgyu/to-do-list/blob/master/images/apiDocs.png?raw=true" width="600">



### Spring Batch 를 활용한 통계 데이터 생성

- web 모듈에서 매일 12시에 실행할 수 있도록 설정 

  - 메타 데이터 및 통계 데이터
    
    <img src="https://github.com/cotgyu/to-do-list/blob/master/images/batchTable.png?raw=true" width="250">
  
  - 어드민 페이지 통계 화면 

    <img src="https://github.com/cotgyu/to-do-list/blob/master/images/adminPage.png?raw=true" width="850">

  
### Spring Security 를 활용한 OAuth2 소셜 로그인 연동

  <img src="https://github.com/cotgyu/to-do-list/blob/master/images/naver.png?raw=true" width="230"> <img src="https://github.com/cotgyu/to-do-list/blob/master/images/google.png?raw=true" width="230">

---
  
#### 개발 메모

- [gradle task - bootJar, build 비교 메모](https://cotmulgyu.blogspot.com/2021/06/gradle-task-bootjar-build.html)

- [AWS 자동배포 연습해보기](https://cotmulgyu.blogspot.com/2021/05/aws.html)

- [API REST 하게 개선하기](https://cotmulgyu.blogspot.com/2021/08/api-rest.html)

