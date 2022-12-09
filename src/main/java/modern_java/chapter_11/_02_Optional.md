# Optional 클래스 
- 값이 있으면 `Optional<T>` 
- 값이 없으면 `Optional.empty` -> 싱글턴 인스턴스를 반환하는 정적 팩토리 메서드 


## 반드시 존재해야 하는 값은 오히려 `null`을 반환하는게 나을지도 
- 예제에서는, 보험회사 이름은 반드시 존재해야 하기 때문에, `Optional<String>`을 사용하지 않았다고 말한다. (오히려 NullPointerException을 유발해서 문제를 찾는게 낫다고 한다.)
- 