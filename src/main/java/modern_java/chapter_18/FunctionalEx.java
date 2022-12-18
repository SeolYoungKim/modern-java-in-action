package modern_java.chapter_18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FunctionalEx {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(1, 4, 9));
        List<List<Integer>> subsets = subsets(numbers);
        System.out.println(subsets);
    }

    static List<List<Integer>> subsets(List<Integer> list) {
        if (list.isEmpty()) { // 입력 리스트가 없을 경우, 빈 리스트 자신이 서브 집합
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }

        Integer first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());

        List<List<Integer>> subans = subsets(rest);  // 빈 리스트가 아니면 하나의 요소를 꺼내고, 나머지 요소의 모든 서브집합을 찾아서 subans로 전달. (정답의 절반)
        List<List<Integer>> subans2 = insertAll(first, subans);  // 정답의 나머지 절반을 포함하는 subans2는 subans의 모든 리스트에 처음 꺼낸 요소를 앞에 추가해서 만든다.
        return concat(subans, subans2);  // subans, subans2를 연결하면 정답이 완성된다
    }

    private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> subans) {
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> suban : subans) {
            List<Integer> addFor = new ArrayList<>();
            addFor.add(first);
            addFor.addAll(suban);

            result.add(addFor);
        }

        return result;
    }

    private static List<List<Integer>> concat(List<List<Integer>> subans,
            List<List<Integer>> subans2) {
        List<List<Integer>> result = new ArrayList<>(subans);
        result.addAll(subans2);

        return result;
    }
}
