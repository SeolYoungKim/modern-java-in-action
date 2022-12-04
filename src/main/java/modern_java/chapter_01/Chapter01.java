package modern_java.chapter_01;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Chapter01 {

    public static void main(String[] args) {
        File[] hiddenFilesBeforeJava8 = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });

        FileFilter isHiddenLambda = (File pathname) -> pathname.isHidden();
        FileFilter isHiddenMethodReference = File::isHidden;
        File[] files = new File(".").listFiles(isHiddenMethodReference);

        // filterList 사용 예
        List<String> strList = List.of("김김김", "정상수", "이루다", "김뭐시기");
        Predicate<String> predicate = (name) -> name.startsWith("김");  // 필터 조건을 적어준다.
        Predicate<String> methodRef = String::isBlank;  // 메서드 참조도 가능하다. (메서드 참조가 가능한 조건은 여러가지가 있는데, 책에서 설명해줄 것 같다.)

        List<String> results = filterList(strList, predicate);  // strList가 predicate 조건에 따라 필터링된다. "김"으로 시작되는 문자열이 담긴다.
        System.out.println(results);
    }

    static <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }

        return result;
    }
}
