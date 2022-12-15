package modern_java.chapter_15;

public class ArithmeticCell extends SimpleCell {
    public static void main(String[] args) {
        //TODO 책 예제가 잘못된 것 같은데.. 한번 알아보자..
//        ArithmeticCell c3 = new ArithmeticCell("C3");
//        SimpleCell c2 = new SimpleCell("C2");
//        SimpleCell c1 = new SimpleCell("C1");
//
//        c1.subscribe(c3);
//        c2.subscribe(c3);
//
//        c1.onNext(10);
//        c2.onNext(20);
//        c1.onNext(15);

    }

    private Integer left;
    private Integer right;

    public ArithmeticCell(String name) {
        super(name);
    }

    public void setLeft(Integer left) {
        this.left = left;
        onNext(left + this.right);
    }

    public void setRight(Integer right) {
        this.right = right;
        onNext(right + this.left);
    }
}
