package level2;

import level1.MakeCircle;

import java.util.Scanner;
public class PlanetLocation {
    public static void fillSun(String[][] space, String[][] sun, int month){
        /*
        1월 (1월과의 거리 == 0), 2월과 12월 (1월과의 거리 == 1), 3월과 11월 (1월과의 거리 == 2), 4월과 10월 (1월과의 거리 == 3),
        5월과 9월 (1월과의 거리 == 4), 6월과 8월 (1월과의 거리 == 5), 7월 (1월과의 거리 == 6)
         */
        int[] distanceOfMonth = {8,9,10,11,12,1,2,3,4,5,6,7};
        int calculateDistanceOfJan = 5 - findIndex(distanceOfMonth, month);

        if (calculateDistanceOfJan < 0) { // 절대값 구하기
            calculateDistanceOfJan *= -1;
        }
        int monthlyLocation = 20 * calculateDistanceOfJan;
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                space[i][j+monthlyLocation] = sun[i][j];
            }
        }
    } // fillSun - end

    public static int findIndex(int[] array, int month){
        for (int i = 0; i < array.length; i++) {
            if (array[i] == month) {
                return i; // index 반환
            }
        }
        return 0; // month를 찾지 못했을 경우.
    } // findIndex - end

    // 랜덤으로 작은 별찍기
    public static void randomStars(String[][] space, int starNumber, String star){
        int starCount = starNumber;
        while (starCount > 0) {
            int randomRow = (int)(Math.random()* space.length);
            int randomColumn = (int)(Math.random()*space[0].length);

            if ((space[randomRow][randomColumn] == null) || (space[randomRow][randomColumn] == " ")) {
                space[randomRow][randomColumn] = star;
                starCount--;
            }
        } // while - end
    } // randomStars - end

    public static void main(String[] args) { // ✅위치가 중요!! 객체+배열 사용, 전역변수 사용x
        System.out.println("Sun, Earth, Moon");
        System.out.println("날짜를 입력하세요.");

        Scanner scanner = new Scanner(System.in);

        int[] monthDay = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //scanner은 공백문자를 기준으로 나눠서, next()로 다음 요소(문자)를 받는다.
        int month = Integer.parseInt(scanner.next().split("월")[0]); //"월" 기준으로 앞글자 받아 문자->숫자로 바꾸기
        int day = Integer.parseInt(scanner.next().split("일")[0]); //"일" 기준으로 앞글자 받아 문자->숫자로 바꾸기

        while (true) {
            // 올바른 값이 들어오면
            if ((month > 0 && month < 13) && (day > 0 && day <= monthDay[month - 1])) {
                break; //while문 탈출하고 그 아래의 코드를 실행.
            }
            // 에러 메세지 출력
            System.out.println("유효하지 않은 날짜를 입력하셨습니다. 다시 입력해주세요.");
            // if 문에서 탈출하지 못했다면 올바른 입력을 받을 때 까지 입력을 다시 받는다.
            month = Integer.parseInt(scanner.next().split("월")[0]);
            day = Integer.parseInt(scanner.next().split("일")[0]);

        }

        String[][] sun = MakeCircle.makeFilledCircleArray(5); // MakeCircle 클래스에서 static 메서드 사용.
        String[][] earth = MakeCircle.makeFilledCircleArray(3);
        String moon = "*";

        String[][] space = new String[5][25*5]; // 초기에 정한 넓이의 *5를 해줬다.
        // 지구 넣기 (가운데, 위치 고정)
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                space[1+i][12*5+1+j] = earth[i][j]; // space의 가운데에 위치하게 함.
            }
        }

        // 달 넣기 (위치의 경우의 수는 4가지, 지구와의 거리는 3, 지구 기준으로 위치 정하기)
        if (day/8 == 0) { // 달이 지구의 오른쪽에 존재할 때
            space[2][(12*5)+7] = moon;
        } else if (day/8 == 2) { // 달이 지구의 왼쪽에 존재할 때
            space[2][(12*5)-3] = moon;
        }
        // day/8이 1, 3일 경우에는 지구와 겹침.

        // 태양 넣기 (위치의 경우의 수는 12가지 -> 12달에 따라 서서히 위치가 변화하게 하고 싶었음.)
        fillSun(space, sun, month); // column 위치 파악

        // 멋지게 구현하기 (작은 별을 원하는 숫자 만큼 랜덤으로 찍기)
        randomStars(space, 15, ".");
        randomStars(space, 7, "+");

        MakeCircle.printSpace(space); // 출력 하기
    } //main - end
}
