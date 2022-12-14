> 해당 챕터는 예제가 많아, 대부분 Class 파일로 정리하였음.
# 스트림 활용
- 데이터를 어떻게 처리할지는 스트림 API가 관리함
- 스트림 API 내부적으로 다양한 최적화가 일어날 수 있음
- 코드를 병렬로 실행할지 여부도 결정할 수 있음


# 쇼트서킷 평가
- 때로는 전체 스트림을 처리하지 않아도 결과가 반환될 수 있다.
  - `and`연산을 할 때, 표현식에서 하나라도 `false`가 나오면 나머지 표현식의 결과와 상관 없이 전체 결과도 거짓이 됨. 
- 이러한 상황을 "쇼트 서킷"이라고 부름
- allMatch, anyMatch, noneMatch, findFirst, findAny 등의 연산은 모든 스트림의 요소를 처리하지 않고도 결과를 반환할 수 있음.
  - 원하는 요소를 찾았을 경우, 즉시 결과를 반환한다.
  - 이들도 쇼트 서킷이다.
- `limit` 또한 쇼트 서킷인데, 스트림의 모든 요소를 처리할 필요 없이 주어진 크기의 스트림을 생성하기 때문이다.


# Optional<T>
- 값의 존재나 부재 여부를 표현하는 컨테이너 클래스
- findAny는 아무 요소도 반환하지 않을 수도 있는데, 이 때 null은 쉽게 에러를 일으킬 수 있기 때문에 `Optional<T>`를 사용한다.
- 메서드
  - `isPresent()` : Optional이 값을 포함하면 `true`, 없으면 `flase`
  - `ifPresent(Consumer<T> block)` : 값이 있을 경우 주어진 블록을 실행함.
  - `T get()` : 값이 존재하면 반환, 없으면 `NoSuchElementException`
  - `T orElse()` : 값이 있으면 반환, 없으면 기본값 반환.
    - 주의해야 할 것이 있다. `orElse()`내부의 메서드는 Optional 내부에 값이 존재하더라도 1회 수행되게 설계되어있다. 
    - 즉, 값이 없으면 실행되는게 아니고, 값이 있어도 실행, 없어도 실행되는 메서드다.
    - 따라서, `()`안의 메서드가 **값이 존재 할 때만 수행**되기를 바란다면, `orElseThrow()`를 쓰기 바란다.


# reduce 메서드의 장점과 병렬화
- 단계적 반복으로 합계 구하기 vs `reduce`를 이용해서 합계 구하기
  - `reduce`
    - 내부 반복이 추상화 됨.
    - 내부 구현에서 병렬로 `reduce`를 실행할 수 있게 됨
  - 반복적인 합계 (가변 누적자 패턴)
    - `sum` 변수를 공유해야 함.
    - 쉽게 병렬화 하기 어려움
    - 강제로 동기화 시키더라도, 결국 병렬화로 얻어야 할 이득이 스레드 간 소모적인 경쟁 때문에 상쇄되어 버림.
    - 병렬화와 너무 거리가 먼 기법...


# 스트림 연산 : 상태 없음과 상태 있음
- 스트림 연산은 각각 다양한 연산을 수행한다. -> 내부적인 상태를 고려해야 한다.


- 내부 상태를 갖지 않는 연산(stateless operation)
  - `map`, `filter`
  - 입력 스트림에서 요소를 받아 0 또는 결과를 출력 스트림으로 보냄.
  - 사용자가 제공한 람다나 메서드 참조가 내부적인 가변 상태를 갖지 않는다는 가정 하에, 이들은 보통 **상태가 없는, 즉 내부 상태를 갖지 않는 연산**이다.


- 내부 상태를 갖는 연산(stateful operation)
  - 상태 있는 바운드
    - `reduce`, `sum`, `max`
      - 위 연산들은 결과를 누적할 내부 상태가 필요하다.
      - 스트림에서 처리하는 요소의 수와 관계 없이, **내부 상태의 크기는 한정(bound)** 되어 있다.


  - 상태 있는 언바운드
    - `sorted`, `distinct`
      - filter나 map처럼 스트림을 입력으로 받아 다른 스트림을 출력하는 것 처럼 보일 수 있음.
      - 하지만, filter나 map과는 다르다.
      - 스트림의 요소를 정렬하거나, 중복을 제거하려면 **과거의 이력을 알고 있어야 한다**
        - 예를 들어, 어떤 요소를 출력 스트림으로 추가하려면 **모든 요소가 버퍼에 추가되어 있어야 한다**
        - 연산을 수행하는 데 필요한 저장소 크기는 정해져 있지 않다. 따라서, 데이터 스트림의 크기가 크거나 무한이라면 문제가 생길 수 있다.
      - 이러한 연산을 **내부 상태를 갖는 연산**이라고 한다.


# 알게된 것
- 외부 스트림의 요소는 내부에서도 자유롭게 사용할 수 있다.
