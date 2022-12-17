# 리액티브 스트림과 플로 API
## 리액티브 스트림 
- 잠재적으로 무한의 비동기 데이터를 순서대로, 그리고 블록하지 않는 역압력을 전제해 처리하는 표준 기술
- 역압력 
  - 발행-구독 프로토콜에서 이벤트 스트림의 구독자가, 발행자가 이벤트를 제공하는 속도보다 느린 속도로 이벤트를 소비하면서 문제를 발생하지 않도록 보장하는 장치 
  - 부하가 발생할 경우, 이벤트 발생 속도를 늦추라고 알리거나, 얼마나 많은 이벤트를 수신할 수 있는지 알리거나, 다른 데이터를 받기 전에 기존의 데이터를 처리하는 데 얼마나 시간이 걸리는지를 업스트림 발행자에게 알릴 수 있어야 함 

## Flow
### 포함하고 있는 Interface
- Publisher : 발행 (함수형 인터페이스)
- Subscriber : 한 개씩 또는 한 번에 여러 항목을 소비  
- Subscription : 소비 과정, 제어 흐름, 역압력을 관리 
- Processor

### Subscriber의 메서드 호출 순서 
```text
[Subscriber]
onSubscribe -> onNext* -> (onError | onComplete)?
```
- `onSubscribe`가 항상 처음 호출 됨
- 이어서 `onNext`가 여러번 호출될 수 있음 
- 이벤트 스트림은 영원히 지속되거나 `onComplete`콜백을 통해 더이상의 데이터가 없고 종료됨을 알릴 수 있음 
  - Publisher에 장애가 발생했을 때는 `onError` 메서드를 호출할 수 있음
- Subscriber가 Publisher에 자신을 등록할 때 Publisher는 처음으로 onSubscribe 메서드를 호출하여 Subscription 객체를 전달한다.


### Subscription 인터페이스 
```text
[Subscription]
request() : Publisher에게 주어진 개수의 이벤트를 처리할 준비가 되었음을 알림 
cancel()  : Subscription을 취소, 즉 Publisher에게 더이상 이벤트를 받지 않음을 통지  
```

### 협력 규칙
1. Publisher는 반드시 Subscription의 request 메서드에 정의된 개수 이하의 요소만 Subscriber에게 전달한다.
   - 지정된 개수보다 적은 수의 요소를 onNext에 전달
   - 성공적으로 끝났으면 onComplete 호출
   - 문제 발생 시 onError 호출 -> Subscription 종료

2. Subscriber는 요소를 받아 처리할 수 있음을 Publisher에게 알려야 한다.
   - Subscriber가 이런 방식으로 역압력을 행사한다.
   - Subscriber가 관리할 수 없는 너무 많은 요소를 받는 일을 회피한다.
   - onComplete, onError 신호를 처리하는 상황에서는, Publisher나 Subscription의 어떤 메서드도 호출할 수 없다.
     - 이 때는 Subscription이 취소되었다고 가정한다.
   - Subscription.request() 메서드 호출 없이도 언제든 종료 시그널을 받을 준비가 되어있어야 한다.
   - Subscription.cancel() 메서드가 호출된 이후여도, 한 개 이상의 onNext를 받을 준비가 되어있어야 한다.

3. Publisher와 Subscriber는 정확하게 Subscription을 공유해야 한다.
   - 각각이 고유한 역할을 수행해야 한다.
   - onSubscribe와 onNext 메서드에서 Subscriber는 request 메서드를 동기적으로 호출할 수 있어야 한다. 
   - Subscription.cancel() 메서드는 몇 번을 호출해도 한 번 호출한 것과 같은 효과를 가져야 한다. 
     - 여러번 이 메서드를 호출해도 다른 추가 호출에 별 영향이 없도록 스레드에 안전해야 한다. (멱등성 보장해야 함)
   - 같은 Subscriber 객체에 다시 가입하는 것은 권장하지 않음 (강제는 아님)


### Processor 
- Publisher와 Subscriber를 상속받는 인터페이스 
- 이벤트의 변환 단계를 나타냄 
- 에러를 수신하면 이로부터 회복하거나 (Subscription은 취소로 간주) 즉시 onError 신호로 모든 Subscriber에 에러를 전파할 수 있다.
- 마지막 Subscriber가 Subscription을 취소하면 Processor는 자신의 업스트림 Subscription도 취소함으로 취소 신호를 전파해야 함 

