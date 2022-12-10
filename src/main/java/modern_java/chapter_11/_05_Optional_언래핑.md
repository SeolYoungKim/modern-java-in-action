
- get() : 값이 있으면 반환, 없으면 NoSuchElementException -> 쓰지말것
- orElse(T other) : Optional이 값을 포함하지 않을 때 기본값 제공
- orElseGet(Supplier<? extends T> other) : orElse() 메서드의 "게으른"버전. Optional에 값이 없을 때만 Supplier가 실행 됨
  - defalut 메서드를 만드는 데 시간이 걸리거나, 
  - Optional이 비어있을 때만 기본값을 생성하고 싶을 경우에 사용 
- orElseThrow(Supplier<? extends X> exceptionSupplier) : Optional이 비어있을 때 예외
- ifPresent(Consumer<? super T> consumer) : 값이 존재할 때 인수로 넘겨준 동작 실행. 없으면 아무일도 발생 안함
- ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)
  - Optional이 비었을 때 실행할 수 있는 Runnable을 인수로 받는다. 
        
