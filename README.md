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
  - main : 배포 가능한 상태의 브랜치
  - feature : 새로운 기능 개발 `main` 브랜치로부터 분기
  - fix : 수정 사항 및 버그 수정할 때 `main` 브랜치로부터 분기
  
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
