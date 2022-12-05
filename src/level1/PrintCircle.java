package level1;

import java.util.Scanner;

public class PrintCircle {
    public static void main(String[] args) {
        System.out.println("1부터 80 사이의 정수를 입력해주세요.");
        System.out.println("(2 이하의 수는 제대로 된 원이 만들어지지 않습니다.)");

        Scanner scanner = new Scanner(System.in);
        int circle = scanner.nextInt();

        while (!(circle > 0 && circle <= 80)) { // 에러
            System.out.println("유효하지 않은 숫자입니다. 다시 입력해주세요.");
            circle = scanner.nextInt();
        }

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

        for (String[] row: circleArray) { //출력
            for (String column : row) {
                if (column == null) {
                    System.out.print(" ");
                } else {
                    System.out.print("-");
                }
            }
            System.out.println("");
        }
    }// main end
}
