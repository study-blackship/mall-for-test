## 테이블 다이어그램
![Image](image/diagram.png)

## 코딩 전략

### 공통
- 들여쓰기(indent) 3단계 이상 금지

### 객체 생성
- service 단에서 new 금지
- MapStruct로 매핑

### 결합도
- 의존성 사이클 생성 금지
- 패키지 외부의 엔티티끼리 의존관계 생성 금지

### 함수
- 함수 이름에 맞는 기능
- 한개의 함수에 한개의 기능

### 테스트 수준
- 단위 테스트를 지향하되 의미있는 로직에 대해서 테스트
- 통합 테스트는 x

### Branch Strategy

`{branch header}/{issue number}/{short description}`

- branch header rule
  - master : 배포 가능한 상태의 브랜치 -> `team10`, `dev`, `backend`, `frontend`
  - develop : 개발을 진행하는 브랜치 -> 이 프로젝트에서는 `master` 브랜치와 동일하므로 생략
  - feature : 새로운 기능 개발 및 버그 수정이 필요할 때마다 `develop` 브랜치로부터 분기
    -> 하지만 `develop`대신에 새로운 기능 개발 시 그냥 `feature` 사용
  - hotfix : `master`(배포 가능한 상태)에서 긴급하게 수정을 해야할 경우 `master`로부터 분기
  - release : 이번 출시 버전을 준비하는 브랜치, 이번 프로젝트에서는 사용 x

- example : `feature/2/login`

### Commit Convention

`{commit header}: {commit title} (#{issue number})`

- commit header rule
    - feat : 새로운 기능에 대한 커밋
    - fix : 버그 수정에 대한 커밋
    - build : 빌드 관련 파일 수정에 대한 커밋
    - chore : 그 외 자잘한 수정에 대한 커밋
    - ci: CI 관련 설정 수정에 대한 커밋
    - perf: 코드 성능 개선에 대한 커밋
    - docs : 도큐먼트에 수정에 대한 커밋
    - style : 코드 문법 또는 포맷에 대한 수정에 대한 커밋
    - refactor : 코드 리팩토링에 대한 커밋
    - test : 테스트 코드 수정에 대한 커밋

- example : `feat: Add Card create logic (#12)`
- commit message rule
  - 제목은 한글이나 영어로 작성하되, 헤더는 반드시 위의 내용을 따른다.
  - 본문은 최대한 상세하게 한글 또는 영어로 작성하도록 한다.
