# 개선된 ConcurrentHashMap
- 동시성 친화적이며, 최신 기술을 반영한 HashMap
- 내부 자료구조의 특정 부분만 잠궈, 동시 추가 및 갱신 작업을 허용함
- 동기화된 HasTable 버전에 비해 읽기, 쓰기 연산 성능이 월등함


## 리듀스와 검색
- 스트림에서 봤던 것과 비슷한 종류의 세 가지 연산을 지원
  - `forEach` : 각 키-값 쌍에 주어진 액션 실행 
  - `reduce`  : 모든 키-값 쌍을 제공된 reduce 함수를 이용해 결과로 합침
  - `search`  : null이 아닌 값을 반환할 때 까지 각 키-값 쌍에 함수를 적용 

- 키-값으로 연산 : `forEach`, `reduce`, `search`
- 키로 연산 : `forEachKey`, `reduceKeys`, `searchKeys`
- 값으로 연산 : `forEachValue`, `reduceValues`, `searchValues`
- Map.entry 객체로 연산 : `forEachEntry`, `reduceEntries`, `searchEntries`

- 이들 연산은 `ConcurrentHashMap`의 상태를 잠그지 않고 연산을 수행한다는 점을 주목하자.
  - 이들 연산에 제공한 함수는 계산이 진행되는 동안 바뀔 수 있는 객체, 값, 순서 등에 의존하지 않아야 함

- 병렬성 기준값(threshold)을 지정해야 함.
  - 맵의 크기가 주어진 기준값보다 작으면 -> 순차 연산
  - 기준값을 1로 지정할 경우, 공통 스레드 풀을 이용해 병렬성을 극대화
  - `Long.MAX_VALUE`를 기준값으로 지정할 경우, 한 개의 스레드로 연산을 실행

  
## 계수 
- 맵의 매핑 개수를 반환하는 `mappingCount` 메서드 제공
- 기존의 `size`메서드 보다는 `mappingCount`메서드 사용을 권고함 (정수형 오버플로 대비) 
```java
int size = map.size();
long mappingCount = map.mappingCount();
```


## 집합 뷰
- `keySet` : 해당 set 변경 시 Map에 영향
- `newKeySet` : 해당 set 변경해도 Map은 유지