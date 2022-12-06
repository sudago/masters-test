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
#### 1-1) 입력 : 1~80 사이의 정수를 콘솔로 입력받는다.
- 1~80 사이의 정수가 아니면 입력을 다시 받는다.
- **노력한 점** : 1~80 사이의 정수가 아닌 경우를 고려하려고 노력했다.
- **아쉬운 점** : String을 입력 받았을 때도 입력을 다시 받고 싶었지만 해결방법을 알아내지 못했다.

#### 1-2) 구현 : 2차원 배열에 원을 만들 문자를 저장한다.
- array[row][column] 으로 2차원 배열을 만들었다. <br>
  - row,column,circle(원의 지름)은 같은 숫자이다.
- 원의 절반 지점을 기준으로 "-" 문자를 넣는다.
- 원의 맨 윗쪽(오른쪽, 왼쪽), 맨 아랫쪽(오른쪽, 왼쪽)
  - 계산해서 넣는 위치가 같기 때문에 동시에 값을 넣어줬다.
  - 절반 지점 이상부터는 이미 값을 넣어줬기 때문에 for문을 절반 지점까지만 돌아준다.
  - 2중 for문으로(왼쪽~오른쪽) 1/4면적만 돌며 __채워진 원__ 이 되는 코드를 static 메서드에 추가했다. (채워진 원, 빈 원 선택 가능)
- 짝수 일 때는 column이 <u>절반 + 1</u>을 해야 위치가 맞기 때문에 evenNumber에 짝수일 경우에는 1, 홀수면 0을 넣어줬다.
#### 1-3) 출력 : 터미널 화면에 해당 크기의 원을 출력한다.
- 향상된 for문으로 2차원 배열의 모든 요소를 돌면서 출력을 해줬다.
  - 요소에 값이 들어있지 않으면 " " 공백 값 출력,
    - 요소에 값이 들어있으면 ("-"밖에 들어있지 않으므로) 출력해줬다. <br>
  - **노력한 점** : 
    1. 함축적으로 예쁜 코드를 만들기 위해 노력했다.
    2. 사용한 기능을 다른 클래스에서도 사용하기 위해서 static 메서드로 빼줬다.
  - **아쉬운 점** : 
    1. 정수 2 이하는 예쁜 원이 만들어지지 않는다..
#### 1-4) 코드 설명 : 코드 안에 주석을 달아두었다.
````java
import java.util.Scanner;

public class MakeCircle { // 클래스 이름 수정
  public static String[][] makeFilledCircleArray(int circle) { // 원을 만드는 기능을 따로 빼준 메서드
    String[][] circleArray = new String[circle][circle];
    int halfCircle = (circle - 1) / 2; // index값 계산을 위해서 -1을 해줌.
    int evenNumber = circle % 2 == 0 ? 1 : 0; //짝수면 1,홀수면 0을 저장.

    for (int i = 0; i <= halfCircle; i++) { // 2차원 배열 채우기.
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
  public static void main(String[] args) {
    System.out.println("1부터 80 사이의 정수를 입력해주세요.");
    System.out.println("원의 크기는?");

    Scanner scanner = new Scanner(System.in);
    int circle = scanner.nextInt();//정수를 입력받는다.

    while (!(circle > 0 && circle <= 80)) {// 1~80정수 외에 다른걸 입력 받으면
      System.out.println("유효하지 않은 숫자입니다. 다시 입력해주세요.");//에러 메세지 출력
      circle = scanner.nextInt(); // 유효한 숫자를 입력 받을 때 까지 반복한다.
    }

    // 빈 원을 만들기
    String[][] circleArray = makeEmptyCircleArray(circle); // MakeCircle 클래스의 static 메서드 사용.

    for (String[] row: circleArray) { //출력
      for (String column : row) {
        if (column == null) { // 아무것도 채워져 있지 않으면
          System.out.print(" "); // 공백 출력
        } else { // 무언가로 채워져 있으면 ("-")
          System.out.print(column); // "-" 출력
        }
      }
      System.out.println(""); // 개행으로 다음 행을 구분해준다.
    }
  }// main end
}
````
#### 1-5) 코드 실행 결과 : 코드 동작과 입력 값, 실행 결과
- **입력**: 0 ➡️ -1 ➡️ 81 ➡️ 6 <br>
  - 0, -1, 81은 유효하지 않음.
  - 6은 유효한 숫자.<br><br>
- **Console**

````
1부터 80 사이의 정수를 입력해주세요.
원의 크기는?
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
#### 2-1) 입력 : "1월 17일"의 형태로 입력을 받는다.
- scanner는 공백 문자를 기준으로 나눠서, next()로 다음 요소(문자)를 받는다.
- month : "월" 기준으로 split()을 하고, 0번째 index의 앞글자를 받아 문자에서 숫자로 바꿔준다.
- day : "일" 기준으로 split()을 하고, 0번째 index의 앞글자를 받아 문자에서 숫자로 바꿔준다.
- 각 month의 최대 일수를 변수 monthDay에 저장하고, day가 month에 해당하는 값이 아니면 오류를 출력 후 다시 입력을 받는다.
#### 2-2) 구현 : 2차원 배열 planet에 출력할 문자열을 담았다.
- 이전 단계에서 원을 만들었던 makeCircleArray를 MakeCircle 클래스의 static 메서드로 꺼내주고,
  이번 단계에서 사용하기 위해서 빈 원으로 구현했던 원을 채워진 원도 선택할 수 있게 기능을 추가했다.
- makeFilledCircleArray() 메서드로 sun, earth의 2차원 배열을 만들었다.
  (moon은 한 글자라서 바로 "\*"을 넣어줬다.)
- 처음에 sun을 기준으로 earth와 moon을 위치시키게 하려고 했지만, 
감이 안잡혀서 earth를 중심으로 sun과 moon을 배치시켰더니 구현이 한층 수월해졌다.
- 모든 기능을 완벽하게 정리하고 구현을 시작하려 했었지만 우선 코드를 실행하고, 실패하고, 그 다음에 수정을 하라는 글을 본 기억이 떠올라 일단 코드를 작성해서 실행시키고, 수정하고를 반복했다.
  - moon은 4가지 경우의 수로 위치하게 설정했다. <br>
    1\) 지구의 오른쪽 : 1일 ~ 7일 <br>
    2\) 지구의 뒷쪽 : 8일 ~ 15일 <br>
    3\) 지구의 왼쪽 : 16일 ~ 23일 <br>
    4\) 지구의 앞쪽 : 24일 ~ 31일 <br>
  - 지구의 뒷쪽과 앞쪽에 있을 때는 지구와 겹치게 되어서 planet에 따로 입력 할 필요가 없었기 때문에 if문 작성에서 제외했다.
- sun은 7가지 경우의 수로 위치하게 설정했다.
  - 코드의 중복, 들여쓰기를 제안하기 위해 static 메서드로 분리해서 기능을 수행하도록 수정했다.
    - 자바에서 제공하는 라이브러리 메서드의 사용을 최소화 하려고 노력함.
  - 1월은 맨 왼쪽, 7월은 맨 오른쪽에 오게 설정했다.
  - 2월과 11월, 3월과 10월, 4월과 9월(중앙), 5월과 8월, 6월과 7월은 동일한 위치에 오기 때문에, 두가지 씩 묶어서 같은 숫자가 되게 코드를 작성했다.
  - 특히 4월과 9월은 중앙에 위치해야하기 때문에 지구의 정중앙에서 지구를 완전히 가리도록 코드를 작성했다.
- 콘솔 창에 좀 더 멋지게 출력하기 위해서 랜덤으로 작은 별을 찍는 기능을 추가해주었다. - static randomStars()
#### 2-3) 출력 : 이전 단계와 동일하게 출력했다.
- planet 전체를 출력했다.
- null(빈값)이면 "-"을 출력하고, 값이 들어있으면 "\*"을 출력했다.
#### 3-4) 코드 설명 : 코드 안에 주석을 달아두었다.
````java
import level1.MakeCircle;

import java.util.Scanner;

public class PlanetLocation {
    /*
    1. 프로그램을 실행하면 1월 1일부터 12월 31일까지 날짜를 입력받는다.
    2. 해당 날짜에 태양 지구 달의 상대적인 위치를 콘솔에 "멋지게" 출력한다.
    3. 단 1월 1일에 태양 - 지구 - 달은 순서대로 일직선상에 위치한다고 가정한다.
    4. 문제의 단순화를 위해 태양 지구 달은 같은 평면상에서 공전하며, 공전궤도는 완전한 원이라고 가정한다.
     */
  
    // 자바에서 제공하는 라이브러리의 메서드의 사용을 최소화하려고 노력했다.
    public static void fillSun(String[][] planet, String[][] sun, int month){
        /*
        1월 (1월과의 거리 == 0), 2월과 12월 (1월과의 거리 == 1), 3월과 11월 (1월과의 거리 == 2), 4월과 10월 (1월과의 거리 == 3),
        5월과 9월 (1월과의 거리 == 4), 6월과 8월 (1월과의 거리 == 5), 7월 (1월과의 거리 == 6)
         */
      int[] distanceOfMonth = {8,9,10,11,12,1,2,3,4,5,6,7};
      // 1월과의 거리를 계산한다. 1월의 index인 5를 미리 넣어준다.
      int calculateDistanceOfJan = 5 - findIndex(distanceOfMonth, month);
      // 절대값을 구한다.
      if (calculateDistanceOfJan < 0) { // 음수라면(2~7월일 경우)
        calculateDistanceOfJan *= -1; // -1을 곱해서 양수로 만들어준다.
      }
      // (20 * 1월과의 거리)를 계산한 값을 j(column)에 더해서 sun의 위치를 정한다.
      int monthlyLocation = 20 * calculateDistanceOfJan;
      for(int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          planet[i][j+monthlyLocation] = sun[i][j];
        }
      }
    } // fillSun - end

    public static int findIndex(int[] array, int month){
      for (int i = 0; i < array.length; i++) {
        if (array[i] == month) { // month와 같은 숫자를 찾으면
          return i; // 해당 index를 반환한다.
        }
      }
      return 0; // month를 찾지 못했을 경우.
    } // findIndex - end 
  
    // 랜덤으로 작은 별찍기
    static void randomStars(String[][] planet, int starNumber, String star){
      int starCount = starNumber;
      while (starCount > 0) {
        int randomRow = (int)(Math.random()*5);
        int randomColumn = (int)(Math.random()*25*5);
  
        if (planet[randomRow][randomColumn] == null) {
          planet[randomRow][randomColumn] = star;
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

        while (!(monthDay[month-1] >= day && day > 0)) { // 에러
            System.out.println("유효하지 않은 날짜를 입력받았습니다. 다시 입력해주세요.");

            // 동일한 코드를 복사해서 붙여넣은 부분이 아쉽다.
            month = Integer.parseInt(scanner.next().split("월")[0]);
            day = Integer.parseInt(scanner.next().split("일")[0]);
        }

        String[][] sun = MakeCircle.makeFilledCircleArray(5); // MakeCircle 클래스에서 static 메서드 사용.
        String[][] earth = MakeCircle.makeFilledCircleArray(3);
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
        fillSun(planet, sun, month); // column 위치 파악

        // 멋지게 구현하기 (작은 별을 원하는 숫자 만큼 랜덤으로 찍기)
        randomStars(planet, 15, ".");
        randomStars(planet, 7, "+");
        
        
        for(String[] row : planet) {
            for (String column : row) {
                if (column == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(column);
                }
            }
            System.out.println("");
        }
    }
}

````
#### 1-5) 코드 실행 결과 : 코드 동작과 입력 값, 실행 결과
- **입력1** : 1월 1일 <br>
  **Console**
````
Sun, Earth, Moon
날짜를 입력하세요.
1월 1일
  *    .                                      +                     .                                                        
 *** .                    +                                   *    .                   .    .         +           .          
*****                                   .       .            ***   *              .                   +                      
 ***                                      .   +              .*                               .                              
  *                                            +                            .        .       +                               

Process finished with exit code 0


````
- **입력2** : 7월 20일 <br>
  **Console**
````
Sun, Earth, Moon
날짜를 입력하세요.
7월 20일
                                              .                                                      +                    *  
              .  .                                            *   .         .          +              .                  *** 
 .                   .                                   *   ***   .      +        .                        .           *****
                                              +               *                  +    .     .       .                    *** 
                       +                                                 +                          .                     *  

Process finished with exit code 0

````
- **입력3** : 4월 9일 <br>
  **Console**
````
Sun, Earth, Moon
날짜를 입력하세요.
4월 9일
                                                              *                                                              
           ..  .           +                                 *** +                                                .          
      .                    +                                *****.                  +                                    + . 
               .                   .           .  .          ***                                     +                      .
                                                              *               +                       .            .      .  

Process finished with exit code 0
````
- **입력4** : 11월 14일 <br>
  **Console**
````
Sun, Earth, Moon
날짜를 입력하세요.
11월 14일
   .                                      *                           .          .                 ..           .            
          .                              ***                  *                           .                                  
    +                                   *****      .         ***  +        +  .                                              
    +                             +      ***                  *            .                                                 
 +     .                                  *     .                     +                     .                         .      

Process finished with exit code 0
````

### 3단계 : 콘솔 태양계 출력 프로그램 완성
___

### 4단계 : 추가기능 구현
___


[jinan159](https://github.com/jinan159/codesquad_2022_sokoban/blob/master/README.md#1-1-4-%EC%A0%84%EC%B2%B4-%EC%86%8C%EC%8A%A4)
님의 README.md를 참고했습니다.