<h1 align="middle">Trively</h1>
<p align="middle">여행지 추천 및 여행동행 추천 사이트</p>

<br>

## 💻 프로젝트 소개
SSAFY 1학기 파이널 관통 ***최우수 프로젝트***로, 사용자가 쉽게 국내 여행 정보를 검색할 수 있는 웹 사이트입니다.
<p align="center"><img src="https://github.com/Trively/Trively-server/assets/62243967/9747f252-7c7a-4f90-a552-0471d9da10fe" width="75%"></p>

https://github.com/Trively/Trively-server/assets/62243967/50e24b25-6588-4f21-a744-7052e386df92

<br>

## 🏃 Member
|**Full Stack**|**Full Stack**|
|:--:|:--:|
|<img src="https://avatars.githubusercontent.com/jiHunparkkk" width=200px />|<img src="https://avatars.githubusercontent.com/dain0826" width=200px />|
|[박지훈](https://github.com/jiHunparkkk)|[손다인](https://github.com/dain0826)|

<br>

## 📚 STACK
#### ✔Front-end️
<img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">

#### ✔Back-end️
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">

#### ✔️️Infra
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">


## 🗂 Server 구조
```bash
├── java
│   └── com
│       └── jida
│           ├── EnjoyTripApplication.java
│           ├── ServletInitializer.java
│           ├── common
│           │   └── PageInfo.java
│           ├── config
│           │   ├── DataBaseConfiguration.java
│           │   └── WebConfiguration.java
│           ├── constants
│           │   ├── ExceptionCode.java
│           │   └── SuccessCode.java
│           ├── controller
│           │   ├── AttractionController.java
│           │   ├── BoardController.java
│           │   ├── CommentController.java
│           │   ├── MemberController.java
│           │   ├── MessageController.java
│           │   ├── PlanController.java
│           │   ├── PlanListController.java
│           │   └── PostController.java
│           ├── domain
│           │   ├── Attraction.java
│           │   ├── Authority.java
│           │   ├── Board.java
│           │   ├── Comment.java
│           │   ├── Member.java
│           │   ├── Message.java
│           │   ├── MessageRoom.java
│           │   ├── Plan.javaå
│           │   ├── PlanList.java
│           │   ├── Post.java
│           │   ├── PostLike.java
│           │   └── PostScrap.java
│           ├── dto
│           │   ├── BaseResponse.java
│           │   ├── req
│           │   │   ├── CommentSaveRequestDto.java
│           │   │   ├── MemberRequestDto.java
│           │   │   ├── MemberSaveRequestDto.java
│           │   │   ├── MessageRequestDto.java
│           │   │   ├── PlanListSaveRequestDto.java
│           │   │   ├── PlanSaveRequestDto.java
│           │   │   ├── PlanUpdateRequestDto.java
│           │   │   └── PostSaveRequestDto.java
│           │   └── res
│           │       ├── attraction
│           │       │   ├── AttractionListResponse.java
│           │       │   └── AttractionListResponseDto.java
│           │       ├── board
│           │       │   ├── BoardListResponse.java
│           │       │   └── BoardListResponseDto.java
│           │       ├── comment
│           │       │   ├── CommentDetailResponse.java
│           │       │   ├── CommentDetailResponseDto.java
│           │       │   ├── CommentListResponse.java
│           │       │   ├── CommentListResponseDto.java
│           │       │   └── CommentResponse.java
│           │       ├── member
│           │       │   ├── MemberDetailResponse.java
│           │       │   ├── MemberDetailResponseDto.java
│           │       │   ├── MemberResponse.java
│           │       │   ├── TokenDto.java
│           │       │   └── TokenResponse.java
│           │       ├── message
│           │       │   ├── MessageDetailResponse.java
│           │       │   ├── MessageDetailResponseDto.java
│           │       │   ├── MessageResponse.java
│           │       │   ├── MessageRoomResponse.java
│           │       │   ├── MessageRoomResponseDto.java
│           │       │   ├── MessageSendResponse.java
│           │       │   └── MessageSendResponseDto.java
│           │       ├── plan
│           │       │   ├── PlanAllListResponse.java
│           │       │   ├── PlanAllListResponseDto.java
│           │       │   ├── PlanListResponse.java
│           │       │   ├── PlanListResponseDto.java
│           │       │   ├── PlanMemberResponse.java
│           │       │   ├── PlanMemberResponseDto.java
│           │       │   └── PlanResponse.java
│           │       └── post
│           │           ├── PostDetailResponse.java
│           │           ├── PostDetailResponseDto.java
│           │           ├── PostListResponse.java
│           │           ├── PostListResponseDto.java
│           │           └── PostResponse.java
│           ├── exception
│           │   ├── CustomException.java
│           │   ├── RestExceptionHandler.java
│           │   └── UnAuthorizedException.java
│           ├── interceptor
│           │   └── JWTInterceptor.java
│           ├── jwt
│           │   ├── JwtFilter.java
│           │   └── LoginFilter.java
│           ├── mapper
│           │   ├── AttractionMapper.java
│           │   ├── BoardMapper.java
│           │   ├── CommentMapper.java
│           │   ├── MemberMapper.java
│           │   ├── MessageMapper.java
│           │   ├── MessageRoomMapper.java
│           │   ├── PlanListMapper.java
│           │   ├── PlanMapper.java
│           │   ├── PostLikeMapper.java
│           │   ├── PostMapper.java
│           │   └── PostScrapMapper.java
│           ├── security
│           │   └── SecurityConfig.java
│           ├── service
│           │   ├── AttractionService.java
│           │   ├── AttractionServiceImpl.java
│           │   ├── BoardService.java
│           │   ├── BoardServiceImpl.java
│           │   ├── CommentService.java
│           │   ├── CommentServiceImpl.java
│           │   ├── CustomUserDetails.java
│           │   ├── CustomUserDetailsService.java
│           │   ├── MemberService.java
│           │   ├── MemberServiceImpl.java
│           │   ├── MessageService.java
│           │   ├── MessageServiceImpl.java
│           │   ├── PlanListService.java
│           │   ├── PlanListServiceImpl.java
│           │   ├── PlanService.java
│           │   ├── PlanServiceImpl.java
│           │   ├── PostService.java
│           │   └── PostServiceImpl.java
│           └── util
│               └── JWTUtil.java
├── resources
│   ├── application.properties
│   └── mapper
│       ├── attraction.xml
│       ├── board.xml
│       ├── comment.xml
│       ├── member.xml
│       ├── message.xml
│       ├── messageRoom.xml
│       ├── plan.xml
│       ├── planList.xml
│       ├── post.xml
│       ├── postLike.xml
│       └── postScrap.xml
└── trively-server-tree.txt
