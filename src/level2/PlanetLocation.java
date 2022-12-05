package level2;

import level1.MakeCircle;

import java.util.Scanner;

public class PlanetLocation {
    /*
    1. 프로그램을 실행하면 1월 1일부터 12월 31일까지 날짜를 입력받는다.
    2. 해당 날짜에 태양 지구 달의 상대적인 위치를 콘솔에 "멋지게" 출력한다.
    3. 단 1월 1일에 태양 - 지구 - 달은 순서대로 일직선상에 위치한다고 가정한다.
    4. 문제의 단순화를 위해 태양 지구 달은 같은 평면상에서 공전하며, 공전궤도는 완전한 원이라고 가정한다.
     */
    public static void main(String[] args) { // ✅위치가 중요!! 객체+배열 사용, 전역변수 사용x
        System.out.println("Sun, Earth, Moon");
        System.out.println("날짜를 입력하세요.");

        Scanner scanner = new Scanner(System.in);

        int[] monthDay = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //scanner은 공백문자를 기준으로 나눠서, next()로 다음 요소(문자)를 받는다.
        int month = Integer.parseInt(scanner.next().split("월")[0]); //"월" 기준으로 앞글자 받아 문자->숫자로 바꾸기
        int day = Integer.parseInt(scanner.next().split("일")[0]); //"일" 기준으로 앞글자 받아 문자->숫자로 바꾸기

        while (!(monthDay[month-1] >= day && day > 0)) { // 에러
            System.out.println("유효하지 않은 날짜를 입력받았습니다. 다시 입력해주세요.");

            // 동일한 코드를 복사해서 붙여넣은 부분이 아쉽다.
            month = Integer.parseInt(scanner.next().split("월")[0]);
            day = Integer.parseInt(scanner.next().split("일")[0]);
        }

        String[][] sun = MakeCircle.makeCircleArray(5); // MakeCircle 클래스에서 static 메서드 사용.
        String[][] earth = MakeCircle.makeCircleArray(3);
        String moon = "*";

        String[][] planet = new String[5][25*5]; // 초기에 정한 넓이의 *5를 해줬다.
        // 지구 넣기 (가운데, 위치 고정)
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                planet[1+i][12*5+1+j] = earth[i][j]; // planet의 가운데에 위치하게 함.
            }
        }

        // 달 넣기 (위치의 경우의 수는 4가지, 지구와의 거리는 3, 지구 기준으로 위치 정하기)
        if (day/8 == 0) { // 달이 지구의 오른쪽에 존재할 때
            planet[2][(12*5)+7] = moon;
        } else if (day/8 == 2) { // 달이 지구의 왼쪽에 존재할 때
            planet[2][(12*5)-3] = moon;
        }
        // day/8이 1, 3일 경우에는 지구와 겹침.

        // 태양 넣기 (위치의 경우의 수는 12가지 -> 12달에 따라 서서히 위치가 변화하게 하고 싶었음.)
        if (month == 1) { // column의 위치만 서서히 바뀐다.
            for(int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    planet[i][j] = sun[i][j];
                }
            }
        } else if (month == 2 || month == 12) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    planet[i][20+j] = sun[i][j];
                }
            }
        } else if (month == 3 || month == 11) {
            for(int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    planet[i][40+j] = sun[i][j];
                }
            }
        } else if (month == 4 || month == 10) { // 중앙 == 지구와 겹침.
            for(int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    planet[i][60+j] = sun[i][j];
                }
            }
        } else if (month == 5 || month == 9) {
            for(int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    planet[i][80+j] = sun[i][j];
                }
            }
        } else if (month == 6 || month == 8) {
            for(int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    planet[i][100+j] = sun[i][j];
                }
            }
        }else if (month == 7) {
            for(int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    planet[i][120+j] = sun[i][j];
                }
            }
        }


        for(String[] row : planet) {
            for (String column : row) {
                if (column == null) {
                    System.out.print("-");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println("");
        }
    }
}
