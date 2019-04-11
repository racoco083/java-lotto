package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager {
    private User user;
    private ArrayList<Integer> winningNumbesrs;
    private boolean[] checkNumbers;
    private WinningLotto winningLotto;

    Manager(){
        user = new User();
        winningNumbesrs = new ArrayList<>();
        checkNumbers = new boolean[46];
    }

    public void startGame(){
        while(!firstQuery());
        user.makeLottoList();
        user.printLottoList();
        while(!secondQuery());
        while(!thirdQuery());
    }

    public List<Integer> makeWinningList(){
        List<Integer> winningList = new ArrayList<>();

        for(int i = 1; i<46; i++) {
            if(checkNumbers[i]) winningList.add(i);
        }

        return winningList;
    }
    

    public boolean thirdQuery(){
        System.out.println("보너스 볼을 입력해 주세요.");
        String ret = user.inputBonus();

        if(!CheckFormatError(ret) || !checkRangeNumber(Integer.parseInt(ret))
                || !checkDuplicateNumber(Integer.parseInt(ret))){
            return false;
        }

        makeWinningLotto(Integer.parseInt(ret));
        return true;
    }

    public boolean secondQuery(){
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<String> ret = user.inputNumbers();

        if(!checkWinningNumbers(ret)){
            return false;
        }

        return true;
    }

    public void initCheckNumbers(){
        for(int i=1; i<46; i++){
            checkNumbers[i] = false;
        }
    }

    public boolean checkDuplicateNumber(int number){
        if(checkNumbers[number]){
            System.out.println(InputError.DUPLICATE_ERROR);
            return false;
        }
        return true;
    }

    public boolean checkRangeNumber(int number){
        if(number<1 || number>45){
            System.out.println(InputError.NUMBER_RANGE_ERROR);
            return false;
        }
        return true;
    }

    public boolean checkNumberCount(int cnt){
        if(cnt != 6){
            System.out.println(InputError.NUMBER_CNT_ERROR);
            return false;
        }
        return true;
    }

    public boolean checkWinningNumbers(List<String> list){
        initCheckNumbers();

        int cnt = 0;

        for(int i=0; i<list.size(); i++){
            int number = Integer.parseInt(list.get(i));

            if(!CheckFormatError(list.get(i)) || !checkDuplicateNumber(number)
                    || !checkRangeNumber(number)) return false;

            checkNumbers[number] = true;
            cnt++;
        }

        return checkNumberCount(cnt);
    }

    public boolean firstQuery(){
        System.out.println("구입금액을 입력해 주세요.");
        String ret = user.inputMoney();

        if(!CheckFormatError(ret) || !CheckMinusError(Integer.parseInt(ret))
                || !CheckRestError(Integer.parseInt(ret))) return false;

        return true;
    }

    public boolean CheckRestError(int input){
        if(input % 1000 != 0){
            System.out.println(InputError.REST_ERROR);
            return false;
        }
        return true;
    }

    public boolean CheckMinusError(int input){
        if(input <= 0) {
            System.out.println(InputError.MINUS_ERROR);
            return false;
        }
        return true;
    }

    public boolean CheckFormatError(String input){
        try{
            Integer.parseInt(input);
        }catch(NumberFormatException e){
            System.out.println(InputError.NUMBER_FORMAT_ERROR);
            return false;
        }
        return true;
    }

}
