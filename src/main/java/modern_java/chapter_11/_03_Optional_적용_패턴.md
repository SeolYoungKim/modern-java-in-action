# Optional 적용 패턴
```java
Optional<Object> empty = Optional.empty();
Optional<Car> car = Optional.of(new Car());  //TODO Optional.of 는 null 비허용 -> null이 들어가면 NullPointerException!
Optional<Object> nullable = Optional.ofNullable(null);  //TODO Optional.ofNullable은 null 허용
```


## map으로 Optional의 값을 추출하고 변환할 수 있다.
- map 은 매핑한 값을 `Optional`로 감싸서 줌
- flatMap은 매핑된 값을 감싸지 않고 그대로 내보내줌. (그래서 `Optional<Optional<Car>>`가 아니라 `Optional<Car>`로 나오게 되는 것임)


## 도메인 모델에 Optional을 사용했을 때 데이터를 직렬화 할 수 없는 이유 
- Optional은 선택형 반환값을 지원하기 위한 목적으로 탄생했다 
  - 필드 형식으로 사용될 것을 가정하지 않았다. 
  - 때문에, `Serializable`인터페이스를 구현하지 않는다.
  - 도메인 모델에 `Optional`을 사용하게 되면, 직렬화 모델을 사용하는 도구나 프레임워크에서 문제가 발생할 수 있다. 















