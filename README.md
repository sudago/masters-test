# masters-test
### 2023 코드 스쿼드 마스터즈 코스 테스트
___
###### 마스터즈 코스 2023 테스트 태양계를 콘솔에 그리기

## 구현과정 상세 설명
____
### 📄 목차
총 4단계의 과정으로 구성되어 있다. <br>
- ~~0단계 : Gist 생성 및 Git 클론하기~~
- [1단계 : 콘솔로 원 그리기](#1단계-:-콘솔로-원-그리기)
- [2단계 : 지구 태양 달의 위치 표시하기](#2단계-:-지구-태양-달의-위치-표시하기)
- [3단계 : 콘솔 태양계 출력 프로그램 완성](#3단계-:-콘솔-태양계-출력-프로그램-완성)
- [4단계 : 추가기능 구현](#4단계-:-추가기능-구현)

### 1단계 : 콘솔로 원 그리기
___
#### 1) 입력 : 1~80 사이의 정수를 콘솔로 입력받는다.
- 1~80 사이의 정수가 아니면 입력을 다시 받는다.
- **노력한 점** : 1~80 사이의 정수가 아닌 경우를 고려하려고 노력했다.
- **아쉬운 점** : String을 입력 받았을 때도 입력을 다시 받고 싶었지만 해결방법을 알아내지 못했다.

#### 2) 구현 : 2차원 배열에 원을 만들 문자를 저장한다.
- array[row][column] 으로 2차원 배열을 만들었다. <br>
  - row,column,circle(원의 지름)은 같은 숫자이다.
- 원의 절반 지점을 기준으로 "-" 문자를 넣는다.
- 원의 맨 윗쪽(오른쪽, 왼쪽), 맨 아랫쪽(오른쪽, 왼쪽)
  - 계산해서 넣는 위치가 같기 때문에 동시에 값을 넣어줬다.
  - 절반 지점 이상부터는 이미 값을 넣어줬기 때문에 for문을 절반 지점까지만 돌아준다.
- 짝수 일 때는 column이 <u>절반 + 1</u>을 해야 위치가 맞기 때문에 evenNumber에 짝수일 경우에는 1, 홀수면 0을 넣어줬다.
#### 3) 출력 : 터미널 화면에 해당 크기의 원을 출력한다.
- 향상된 for문으로 2차원 배열의 모든 요소를 돌면서 출력을 해줬다.
  - 요소에 값이 들어있지 않으면 " " 공백 값 출력,
  - 요소에 값이 들어있으면 ("-"밖에 들어있지 않으므로) "-"를 출력해줬다. <br>
  - **노력한 점** : 함축적으로 예쁜 코드를 만들기 위해 노력했다.
  - **아쉬운 점** : 
    1. 정수 2 이하는 예쁜 원이 만들어지지 않는다..
    2. 직관성을 위해서 if else 문 안에 둘 다 System.out.println()으로 적었지만, 배열 안에 "-" 값을 넣고도 System.out.println("-")로 명시 해 줄 거라면 배열에 "-" 말고 다른 요소를 넣어도 되지 않았을까 싶다.
#### 4) 코드 설명 : 코드 안에 주석을 달아두었다.
````java
import java.util.Scanner;

public class PrintCircle {
    public static void main(String[] args) {
        System.out.println("1부터 80 사이의 정수를 입력해주세요.");
        System.out.println("(2 이하의 수는 제대로 된 원이 만들어지지 않습니다.)");

        Scanner scanner = new Scanner(System.in);
        int circle = scanner.nextInt();//정수를 입력받는다.

        while (!(circle > 0 && circle <= 80)) {// 1~80정수 외에 다른걸 입력 받으면
            System.out.println("유효하지 않은 숫자입니다. 다시 입력해주세요.");//에러 메세지 출력
            circle = scanner.nextInt(); // 유효한 숫자를 입력 받을 때 까지 반복한다.
        }

        String[][] circleArray = new String[circle][circle];
        int halfCircle = (circle - 1) / 2;// index값 계산을 위해서 -1을 해줌.
        int evenNumber = circle % 2 == 0 ? 1 : 0;//짝수면 1,홀수면 0을 저장.

        // "-"문자 넣어주기.
        for (int i = 0; i <= halfCircle; i++) {//데칼코마니로 앞,뒤가 동시에 채워지게 함.
            int reverseRow = circle - 1 - i;

            circleArray[i][halfCircle - i] = "-";//앞 왼
            circleArray[i][halfCircle + i + evenNumber] = "-";//앞 오
            circleArray[reverseRow][halfCircle - i] = "-";//뒤 왼
            circleArray[reverseRow][halfCircle + i + evenNumber] = "-";//뒤 오
        }

        for (String[] row: circleArray) { //출력
            for (String column : row) {
                if (column == null) { // 아무것도 채워져 있지 않으면
                    System.out.print(" "); // 공백 출력
                } else { // 무언가로 채워져 있으면 ("-")
                    System.out.print("-"); // "-" 출력
                }
            }
            System.out.println(""); // 개행으로 다음 행을 구분해준다.
        }
    }// main end
}
````
#### 5) 코드 실행 결과 : 코드 동작과 입력값, 실행 결과
- **입력**: 0 ➡️ -1 ➡️ 81 ➡️ 6 <br>
  - 0, -1, 81은 유효하지 않음.
  - 6은 유효한 숫자.<br><br>
- **Console**

````
1부터 80 사이의 정수를 입력해주세요.
(2 이하의 수는 제대로 된 원이 만들어지지 않습니다.)
0
유효하지 않은 숫자입니다. 다시 입력해주세요.
-1
유효하지 않은 숫자입니다. 다시 입력해주세요.
81
유효하지 않은 숫자입니다. 다시 입력해주세요.
6
  --  
 -  - 
-    -
-    -
 -  - 
  --  

Process finished with exit code 0

````


### 2단계 : 지구 태양 달의 위치 표시하기
___


### 3단계 : 콘솔 태양계 출력 프로그램 완성
___

### 4단계 : 추가기능 구현
___


[jinan159](https://github.com/jinan159/codesquad_2022_sokoban/blob/master/README.md#1-1-4-%EC%A0%84%EC%B2%B4-%EC%86%8C%EC%8A%A4)
님의 README.md를 참고했습니다.