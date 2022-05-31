# dol-maplestory
넥슨 게임 메이플스토리를 플레이할 때 해야하는 것들을 정리해보고자 만드는 중

2022.05

21: 
1. 게임 하는데 필수 퀘스트가 너무 많아서 할일 관리 사이트를 만들어보자 마음먹음
2. 스프링 부트 초기 설정(암것도 없는 상태..)


22: 
1. 유저 등록을 하고 심볼 정보를 저장할 수 있으면 좋겠다 싶음(요구사항 정의?)
2. maplestory api 중 GetCharacterInfoByAccountID 사용을 위한 테스트

25:
1. mysql 설정
2. db 설계
3. 몇가지 entity class작성(~ing)

28:
1. TUnion entity class 작성 완료
2. UnionInfo, JobInfo, JobClassificationInfo Enum 작성 완료(메이플 직업 왜 이렇게 많은 거야...)

30:
1. BossInfo Enum 작성 완료(보스는 또 왜 이렇게 많은 거야...)

31:
1. user login 관련 컬럼(pw 생각을 안 했었기 때문에 추가)

## TODOLIST
- [x] BossInfo Enum: 2022.05.30
- [ ] 일반 db관련 소스(repository: select, insert, update, delete)
- [ ] api 정리(우선 뭘 만들 것인가, 메서드는 뭘로 해서 request/response 값의 형태 등)
- [ ] 비즈니스 코드(service)
- [ ] api 테스트 코드
