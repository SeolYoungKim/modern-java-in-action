# 이 장의 요약 
- 람다 표현식은 익명 함수의 일종이다.
  - 파라미터 리스트, 바디, 반환 형식을 가지며 예외를 던질 수 있다.
- 람다 표현식으로 간결한 코드를 구현할 수 있다.
- 함수형 인터페이스는 하나의 추상 메서드만을 정의하는 인터페이스이다.
- 함수형 인터페이스를 기대하는 곳에서만 람다 표현식을 사용할 수 있다.
- 람다 표현식 전체가 함수형 인터페이스의 인스턴스로 취급된다.
- java.util.function 패키지는 다양한 함수형 인터페이스를 제공한다.
  - 기본형 특화 인터페이스도 제공함 (오토박싱 기피)
- 실행 어라운드 패턴을 람다와 활용하면 유연성과 재사용성을 추가로 얻을 수 있음
- 람다 표현식의 "기대 형식(type expected)"을 "대상 형식(target type)"이라고 함
- 메서드 참조를 이용하면 기존의 메서드 구현을 재사용하고 직접 전달할 수 있음
- Comparator, Predicate, Function과 같은 함수형 인터페이스는 람다 표현식을 조합할 수 있는 다양한 디폴트 메서드 제공
