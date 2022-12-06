package level1;

import java.util.Scanner;

public class MakeCircle {
    public static String[][] makeCircleArray(int circle) {
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
    public static void main(String[] args) { // 4번 수정함.
        System.out.println("1부터 80 사이의 정수를 입력해주세요.");
        System.out.println("원의 크기는?");

        Scanner scanner = new Scanner(System.in);
        int circle = scanner.nextInt();

        while (!(circle > 0 && circle <= 80)) { // 에러
            System.out.println("유효하지 않은 숫자입니다. 다시 입력해주세요.");
            circle = scanner.nextInt();
        }

        String[][] circleArray = makeCircleArray(circle); // MakeCircle 클래스의 static 메서드 사용.

        for (String[] row: circleArray) { //출력
            for (String column : row) {
                if (column == null) {
                    System.out.print(" ");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println("");
        }
    }// main end
}
