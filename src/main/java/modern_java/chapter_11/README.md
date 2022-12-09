# 이 장의 요약 
- 역사적으로 프로그래밍 언어에서는 nul1 참조로 값이 없는 상황을 표현해왔다.
- 자바 8에서는 값이 있거나 없음을 표현할 수 있는 클래스 java.uti1.0ptional T>를 제공 한다.
- 팩토리 메서드 Optional.empty, Optional.of, Optional.ofNullable 등을 이용해서 Optional 객체를 만들 수 있다.
- Optional 클래스는 스트림과 비슷한 연산을 수행하는 map, flatwap, filter 등의 메서드 를 제공한다.
- Optional로 값이 없는 상황을 적절하게 처리하도록 강제할 수 있다. 즉, optional로 예 상치 못한 nuL1 예외를 방지할 수 있다.
- Optional을 활용하면 더 좋은 API를 설계할 수 있다. 즉, 사용자는 메서드의 시그니처만 보고도 Optional 값이 사용되거나 반환되는지 예측할 수 있다.