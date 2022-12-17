package modern_java.chapter_17;

import java.util.Random;

/**
 * 현재 보고된 온도를 전달하는 클래스
 */
public class TempInfo {
    public static final Random random = new Random();

    public static TempInfo fetch(String town) {
        if (random.nextInt(10) == 0) { // 1/10 확률로 온도 가져오기 실패
            throw new RuntimeException("Error!");
        }

        return new TempInfo(town, random.nextInt(100));
    }

    private final String town;
    private final int temp;

    public TempInfo(String town, int temp) {
        this.town = town;
        this.temp = temp;
    }

    @Override
    public String toString() {
        return town + " : " + temp;
    }

    public String town() {
        return town;
    }

    public int temp() {
        return temp;
    }
}
