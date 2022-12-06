package level1;

import java.util.Scanner;

public class MakeCircle {
    // 채워진 원 만들기
    public static String[][] makeFilledCircleArray(int circle) {
        String[][] circleArray = new String[circle][circle];
        int halfCircle = (circle - 1) / 2;
        int evenNumber = circle % 2 == 0 ? 1 : 0;

        for (int i = 0; i <= halfCircle; i++) { // 2차원 배열 채우기
            int reverseRow = circle - 1 - i;

            for (int j = (halfCircle - i); j <= (halfCircle + i + evenNumber); j++) { // 왼쪽 ~ 오른쪽 채우기
                circleArray[i][j] = "*"; // 앞쪽
                circleArray[reverseRow][j] = "*"; //뒷쪽
            }
        }
        return circleArray;
    }
    // 빈 원 만들기
    public static String[][] makeEmptyCircleArray(int circle) {
        String[][] circleArray = new String[circle][circle];
        int halfCircle = (circle - 1) / 2;
        int evenNumber = circle % 2 == 0 ? 1 : 0;

        for (int i = 0; i <= halfCircle; i++) { // 2차원 배열 채우기
            int reverseRow = circle - 1 - i;

            circleArray[i][halfCircle - i] = "-";//앞 왼
            circleArray[i][halfCircle + i + evenNumber] = "-";//앞 오
            circleArray[reverseRow][halfCircle - i] = "-";//뒤 왼
            circleArray[reverseRow][halfCircle + i + evenNumber] = "-";//뒤 오
        }
        return circleArray;
    }
    public static void printSpace(String[][] array){ // 프린트 기능 추가
        for (String[] row : array) {
            for (String column : row) {
                checkAndPrint(column);
            }
            System.out.println("");
        }
    }

    public static void checkAndPrint(String string){
        if (string == null) {
            System.out.print(" ");
        } else {
            System.out.print(string);
        }
    }

    public static void main(String[] args) { // main - start
        System.out.println("1부터 80 사이의 정수를 입력해주세요.");
        System.out.println("원의 크기는?");

        Scanner scanner = new Scanner(System.in);
        int circle = scanner.nextInt();

        while (!(circle > 0 && circle <= 80)) { // 에러
            System.out.println("유효하지 않은 숫자입니다. 다시 입력해주세요.");
            circle = scanner.nextInt();
        }

        String[][] circleArray = makeEmptyCircleArray(circle); // MakeCircle 클래스의 static 메서드 사용.

        printSpace(circleArray); // 출력
    }// main end
}
