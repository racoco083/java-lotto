package domain;

import java.util.List;

/**
 * 로또 한장을 의미하는 객체
 */
public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    // 추가 기능 구현
    public List<Integer> getLotto(){
        return numbers;
    }

    public void printLotto(){
        System.out.print('[');
        for(int i=0; i<numbers.size(); i++){
            System.out.print(numbers.get(i));
            if(i != numbers.size() - 1) System.out.print(", ");
        }
        System.out.println(']');

    }
}
