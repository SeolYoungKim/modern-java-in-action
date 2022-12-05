# Spliterator 인터페이스
- 자동으로 스트림을 분할하는 기법
- 분할할 수 있는 반복자 라는 의미. (Splitable iterator)
- Iterator처럼 소스의 요소 탐색 기능을 제공하지만, Spliterator는 병렬 작업에 특화되어 있다.
  - 이를 반드시 커스텀 해야하는 것은 아니지만, 어떻게 동작하는지 이해하면 병렬 스트림 동작과 관련한 통찰력을 얻을 수 있음
- 자바 8은 컬렉션 프레임워크에 포함된 모든 자료구조에 사용할 수 있는 디폴트 Spliterator 구현을 제공한다.
  - 컬렉션은 spliterator 메서드를 제공하는 Spliterator 인터페이스를 구현한다.


## 인터페이스 구조
```java
public interface Spliterator<T> {
    boolean tryAdvance(Consumer<? super T> action);
    Spliterator<T> trySplit();
    long estimateSize();
    int characteristics();
}
```
- `T` : Spliterator 에서 탐색하는 요소의 형식
- `tryAdvance` : Spliterator의 요소를 하나씩 순차적으로 소비하면서, 탐색해야 할 요소가 남아있으면 참을 반환함. (일반적인 Iterator와 동일)
- `trySplit` : Spliterator의 일부 요소(자신이 반환한 요소)를 분할해서 두 번째 Spliterator를 생성하는 메서드.
- `estimateSize` : 탐색해야 할 요소 수 정보를 제공 (정확하진 않더라도, 제공된 값을 이용해서 더 쉽고 공평하게 분할)
- `characteristics` : Spliterator의 특성


## 분할 과정
- 스트림을 여러 스트림으로 분할하는 과정은 **재귀적으로 일어난다**
1. 첫 번째 Spliterator에 `trySplit`호출 -> 두 번째 Spliterator가 생성 됨
2. 두 개의 Spliterator에 `trySplit`호출 -> 네 개의 Spliterator가 생성 됨  
3. 1~2 과정을 `trySplit`의 결과가 `null`이 될 때 까지 반복함 -> `trySplit`이 `null`을 반환했다면, 더이상 자료구조를 분할할 수 없음을 의미
4. Spliterator에 호출한 모든 `trySplit`의 결과가 `null`이면 재귀 분할 과정 종료

- 해당 분할 과정은 `characteristics`메서드로 정의하는 Spliterator 특성에 영향을 받음


## Spliterator 특성
- `Spliterator` 자체의 특성 집합을 포함하는 `int`를 반환 
- `Spliterator`를 이용하는 프로그램은 이들 특성을 참고해서 `Spliterator`를 더 잘 제어하고 최적화 할 수 있음
- 특성 정보
  - `ORDERED`   : 요소에 정해진 순서가 있음. Spliterator는 요소를 탐색하고 분할할 때 순서에 유의해야 함
  - `DISTINCT`  : x, y 두 요소를 방문했을 때 `x.equals(y)`는 항상 false를 반환함
  - `SORTED`    : 탐색된 요소는 미리 정의된 정렬 순서를 따름
  - `SIZED`     : 크기가 알려진 소스(Set과 같은)로 Spliterator를 생성했으므로, `estimatedSize()`는 정확한 값을 반환함 
  - `NON-NULL`  : 탐색하는 모든 요소는 null이 아님
  - `IMMUTABLE` : 해당 Spliterator의 소스는 불변임. 탐색하는 동안 CRUD 작업이 불가능함.
  - `CONCURRENT`: 동기화 없이 `Spliterator` 소스를 여러 스레드에서 동시에 고칠 수 있음.
  - `SUBSIZED`  : 해당 Spliterator와 분할되는 모든 Spliterator는 `SIZED`특성을 가짐.


 


























