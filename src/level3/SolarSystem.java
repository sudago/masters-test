package level3;

import level1.MakeCircle;
import level2.PlanetLocation;

import java.util.Scanner;

public class SolarSystem {


    // string -> array로 만들기.
    static String[][] splitArray(String[][] array){
        String[][] splitedArray = new String[array.length][];

        for (int i = 0; i < array.length; i++) {
            splitedArray[i] = array[i][0].split("");
        }
        return splitedArray;
    }


    public static void main(String[] args) { // main - start
        System.out.println("날짜를 입력하세요.");
        // 입력 값을 받기.
        Scanner scanner = new Scanner(System.in);
        int year = Integer.parseInt(scanner.next().split("년")[0]);
        int month = Integer.parseInt(scanner.next().split("월")[0]);
        int day = Integer.parseInt(scanner.next().split("일")[0]);

        //입력값 맞게 들어왔는지 확인하기.
        int[] monthDay = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // day 확인을 위한 array
        while (true) {
            // 올바른 값이 들어오면
            if ((year > 0 && year < 10000) && (month > 0 && month < 13)
                    && (day > 0 && day <= monthDay[month - 1])) {
                break; //while문 탈출하고 그 아래의 코드(태양계를 만드는 프로그램)를 실행.
            }
            // 에러 메세지 출력
            System.out.println("유효하지 않은 날짜를 입력하셨습니다. 다시 입력해주세요.");
            // if 문에서 탈출하지 못했다면 올바른 입력을 받을 때 까지 입력을 다시 받는다.
            year = Integer.parseInt(scanner.next().split("년")[0]);
            month = Integer.parseInt(scanner.next().split("월")[0]);
            day = Integer.parseInt(scanner.next().split("일")[0]);

        }

        String[][] space = new String[17][55]; // 출력 예시의 문자열은 17x55.
        String[][] sun = { // 3x3
                {" !/"},
                {"-O-"},
                {"/| "}};
        String[][] earth = { // 7x14
                {"\"You Are Here\""},
                {"      |      "},
                {"      |      "},
                {"    \\ | /     "}, // 백틱 출력하기 위해 이스케이프 문자(\백틱)을 또 한번 넣어줌.
                {"      V      "},
                {"      .      "},
                {"             "}};

        sun = splitArray(sun); // split으로 요소를 하나씩 분리해준다.
        earth = splitArray(earth);

        // 태양을 우주의 오른쪽 밑 끝에 위치하기.
        for (int i = 0; i < sun.length; i++) {
            for (int j = 0; j < sun[i].length; j++){
                // 오른쪽 밑 6x6 부분부터 sun을 채워넣음.
                int row = space.length - (sun.length) * 2;
                int column = space[0].length - (sun[i].length) * 2;
                space[row+i][column+j] = sun[i][j];
            }
        }

        // 은하수 생성하기.
        String[][] milkyWay = new String[17][17];
        // 중간중간 별이 뭉치지 않게 하기 위해 중간중간 공백" "도 넣어줌.
        PlanetLocation.randomStars(milkyWay, 4, " ");
        PlanetLocation.randomStars(milkyWay, 30, ".");
        PlanetLocation.randomStars(milkyWay, 3, " ");
        PlanetLocation.randomStars(milkyWay, 14, "+");
        PlanetLocation.randomStars(milkyWay, 25, ".");
        PlanetLocation.randomStars(milkyWay, 4, " ");
        PlanetLocation.randomStars(milkyWay, 8, "*");

        // 은하 우주에 위치하기.
        for (int i = 0; i < milkyWay.length; i++) {
            int column = space[0].length - 1;
            for (int j = 0; j < milkyWay[i].length; j++) {
                // 은하수를 (오른쪽 위 -> 왼쪽 아래)의 대각선으로 만들기 위해 column의 위치에 i를 추가로 빼줬다.
                space[i][column - j - i] = milkyWay[i][j];
            }
        }

        // month에 따른 지구 위치 구하기. (첫 위치 (==1월)는 4x17로 지정.)
        // 지구는 태양 기준으로 서쪽 (1월) -> 동쪽 (7월) -> 서쪽 (12월) 으로 움직인다.
        // 7가지 경우의 수로 위치를 정한다.

        // 우주에서 지구의 첫 위치 구하기. PlanetLocation 클래스의 fillSun 메서드 재구성.
        int[] distanceOfMonth = {8,9,10,11,12,1,2,3,4,5,6,7};

        int distance = 5 - PlanetLocation.findIndex(distanceOfMonth, month);
        if (distance < 0) { // 무조건 양수로 만들어 준다. (절댓값)
            distance *= -1;
        }

        int rowDistance = 4 + distance;
        int columnDistance = 17 + (4 * distance);

        // 12가지의 month에 따른 지구의 거리를, 계산한 우주의 위치에 넣기.
        for (int i = 0; i < earth.length; i++) {
            for (int j = 0; j < earth[i].length; j++) {
                space[rowDistance + i][columnDistance + j] = earth[i][j];
            }
        }

        // 추가로 작은 별들 넣기
        PlanetLocation.randomStars(space, 5, "*");
        PlanetLocation.randomStars(space, 10, ".");
        PlanetLocation.randomStars(space, 8, "+");
        PlanetLocation.randomStars(space, 10, ".");

        // 출력하기
        MakeCircle.printSpace(space);
    }
}
