## Jpa Test

#### case 1 - 10,000건 저장시 @Trasactional 유무에 따른 실행시간 차이

#### case 2 - Persistence Context 캐싱, 동일성 보장 

#### case 3 - 변경 감지

#### case 4 - Transanction 테스트 

- 엔티티 생성 후 저장
- 엔티티 필드 값 수정
- 위의 엔티티를 JpaRepository를 통해 find 후 필드 값이 수정됐는지 여부 확인

 