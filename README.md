# masters-test
### 2023 코드 스쿼드 마스터즈 코스 테스트
___
###### 마스터즈 코스 2023 테스트 태양계를 콘솔에 그리기
<br>

## 구현과정 상세 설명
____
<br>

### 📄 목차
____
총 4단계의 과정으로 구성되어 있다. <br>
**_자바의 언어 공부 미숙으로 인해 객체 지향형이 아닌 함수형으로 프로그램을 구성했다._**

**단계**
- ~~0단계 : Gist 생성 및 Git 클론하기~~
- [1단계 : 콘솔로 원 그리기](#1단계--콘솔로-원-그리기)
- [2단계 : 지구 태양 달의 위치 표시하기](#2단계--지구-태양-달의-위치-표시하기)
- [3단계 : 콘솔 태양계 출력 프로그램 완성](#3단계--콘솔-태양계-출력-프로그램-완성)
- [4단계 : 추가기능 구현 (미구현)](#4단계--추가기능-구현-(미구현))

**세부 내용**
- 1\) 구현 설명 (풀이 과정)
  - 설계
  - 코드 구성
- 2\) 코드 + 주석 설명 (코드 설명)
- 3\) 입력 + 출력 (실행 결과)
- 4\) 느낀 점 (아쉬운 점)
  <br><br>
## 1단계 : 콘솔로 원 그리기
___
### 1-1) 구현 설명 (풀이 과정)

- #### 설계
1. array[row][column]으로 2차원 배열을 만든다.
   - row,column,circle(원의 지름)은 같은 숫자이다. <br>
2. circle 의 홀짝을 판별한다.
   - 홀수면 맨 위, 맨 아래는 중앙에 "-"문자 1개만 찍혀야 한다.
   - 짝수면 맨 위, 맨 아래의 중앙에 "-"문자 2개가 찍혀야 한다. <br>
3. 반복문을 돌며 "-"문자로 원의 호 (테두리)를 찍는다.
   - 1/2 까지만 구해서 맨 위, 맨 아래가 동시에 찍히게 한다. (column 의 index 동일)
   - (짝수 일 경우에는 index 0 부터, 홀수일 경우 index 1 부터) 그 다음 row의 왼쪽, 오른쪽을 동시에 찍는다. <br>
   - 왼쪽의 index를 leftIndex라 할 때, 오른쪽의 index는 array.length ( == circle - 1) -leftIndex이다. <br>

+ (추가) 4. 채워진 원 찍기 (PlanetLocation 클래스에서 필요함)
  - 위와 동일하지만, for문이 추가 되면서, 왼쪽의 index ~ 오른쪽의 index 까지 "*" 문자를 넣으며 원을 채운다.
  - 백준 별찍기-7 문제와 유사하다. <br>
+ (추가) 5. 모든 클래스에서 사용 할 2차원 배열을 출력하는 메서드 만들기.
  - element에 값이 들어있지 않으면 (= null 이면) 공백(" ")을 출력한다.
  - element에 값이 들어있으면, 그 요소를 그대로 출력한다.
  
- #### 코드 구성
  **static 메서드**
1. 채워진 원을 만드는 메서드
2. 빈 원을 만드는 메서드 
3. 2차원 배열을 출력하는 메서드 <br>
<br>
   **main 문**
4. 원의 크기를 받아 변수 circle 에 저장한다.
5. 유효한 숫자 (1~80 사이)가 아니면 에러를 출력하고 입력을 다시 받는다.
6. 유효한 숫자를 받으면 빈 원을 찍고 종료한다.
<br><br>

### 1-2) 코드 + 주석 설명 (코드 설명)
```java
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
      
    printSpace(circleArray); // 출력
  }// main end
}
```
<br><br>
### 1-3) 입력 + 출력 (실행 결과)

- **Console**
  - **입력** : 6 + ↵enter <br>
  - **출력**

````
1부터 80 사이의 정수를 입력해주세요.
원의 크기는?
6  
  --  
 -  - 
-    -
-    -
 -  - 
  --  

Process finished with exit code 0

````
- **Console - 에러**
  - **입력** : : 유효하지 않은 입력을 넣었을 때
    - 1 ~ 80의 정수가 아닐 때
  - **출력**
````
1부터 80 사이의 정수를 입력해주세요.
원의 크기는?
0
유효하지 않은 숫자입니다. 다시 입력해주세요.
-1
유효하지 않은 숫자입니다. 다시 입력해주세요.
81
유효하지 않은 숫자입니다. 다시 입력해주세요.

````
<br>

### 1-4) 느낀 점
**아쉬운 점**
1. 1~80에 포함되지 않은 정수 뿐만 아니라, String을 입력 받았을 때도 오류를 출력하고 싶었지만 구현하지 못했다.
2. 정수 2는 예쁜 원이 만들어지지 않는데, 대체 방안을 찾지 못했다.
<br>


## 2단계 : 지구 태양 달의 위치 표시하기
___

### 2-1) 구현 설명 (풀이 과정)

- #### 설계
1. 날짜 (월, 일)를 입력 받는다.
    - 각각 월, 일로 분리해서 int형 변수에 저장해준다.
    - 유효하지 않은 날짜라면 입력을 다시 받는다.
2. 지구를 중심으로, 태양과 달이 돌게 한다.
   - 태양과 지구는 MakeCircle 클래스의 makeFilledCircleArray() 메서드로 생성해준다.
3. 2차원 배열인 우주에 태양과 지구, 달을 해당 날짜에 따라 위치시켜 준다.
   - 1월 1일에 태양 - 지구 - 달은 순서대로 일직선상에 위치한다고 가정한다.
   - 문제의 단순화를 위해 태양 지구 달은 같은 평면상에서 공전하며, 공전궤도는 완전한 원이라고 가정한다.
4. 지구, 달의 공전 주기에 따라 우주에 위치한다.
   - month는 1년에 12가지(12달) 경우의 수로 지구가 태양을 돈다. <br>
     - 하지만 지구가 중심에 위치하고, 태양이 움직이게 구현했다.
     - 원래라면 12가지의 경우의 수이지만, 일직선상의 평면에 구현 할 예정이므로 7가지가 된다.
       - 1월, 2월 (=12월), 3월 (=11월), 4월 (=10월), 5월 (=9월), 6월 (=8월), 7월
       - 1월과의 거리 차이를 기준으로 같은 숫자로 인식하게 했다.
   - day는 한달에 4가지 경우의 수로 달이 지구를 돈다.
     - 4가지 경우의 수 :
       - 1\) 지구의 오른쪽 : 1일 ~ 7일 <br>
       - 2\) 지구의 뒷쪽 : 8일 ~ 15일 <br>
       - 3\) 지구의 왼쪽 : 16일 ~ 23일 <br>
       - 4\) 지구의 앞쪽 : 24일 ~ 31일 <br>
       
- (추가) 5. 작은 별들을 우주에 채운다.
  - 설정한 별과 설정한 별의 갯수만큼, 별을 우주의 랜덤한 위치에 채운다.

- #### 코드 구성
    **static 메서드**
1. 태양을 우주에 채우는 메서드
   - 각 month 의 1월과의 거리 차이를 구해서 태양을 우주의 어디에 위치 시킬 지(index)를 계산한다.
2. index 를 파악하는 메서드
3. 우주에 랜덤으로 작은 별을 찍는 메서드 <br><br>
    **main 문**
4. 입력 값을 받는다.
   - month, day 로 나눈다.
   - month가 (1~12)월이 아니거나, day가 각 month에 해당하는 날짜인지 확인한다.
   - month나 day가 유효한 날짜가 아니라면 오류를 출력하고 입력을 다시 받는다.
5. 태양, 지구, 달을 만든다.
   - 태양, 지구는 2차원 배열이므로 MakeCircle 클래스의 makeFilledCircleArray() 메서드로 원을 만든다.
6. 우주를 만든다.
7. 우주에 지구, 달, 태양 순으로 넣는다.
   - 지구는 가운데에 위치.
   - 달은 day에 따라 4가지 경우의 수 중 하나로 위치.
   - 태양은 month에 따라 <u>7가지 경우의 수</u> (일직선 상의 평면이므로) 중 하나로 위치.
8. 우주의 공백 부분에 작은 별들을 조금 채워 넣는다.
9. 우주를 출력한다. (MakeCircle 클래스의 printSpace() 메서드 사용)
<br><br>
### 2-2) 코드 + 주석 설명 (코드 설명)

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
    
    public static void fillSun(String[][] space, String[][] sun, int month){
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
          space[i][j+monthlyLocation] = sun[i][j];
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
    public static void randomStars(String[][] space, int starNumber, String star){
    int starCount = starNumber;
        while (starCount > 0) {
        int randomRow = (int)(Math.random()* space.length);
        int randomColumn = (int)(Math.random()*space[0].length);

        // null 이거나 공백" " 인 곳에만 별을 넣기.
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

        while (!(monthDay[month-1] >= day && day > 0)) { // 에러
            System.out.println("유효하지 않은 날짜를 입력받았습니다. 다시 입력해주세요.");

            // 동일한 코드를 복사해서 붙여넣은 부분이 아쉽다.
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
                space[1+i][12*5+1+j] = earth[i][j]; // space 의 가운데에 위치하게 함.
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

````
<br><br>
### 2-3) 입력 + 출력 (실행 결과)

- **Console 1**
  - **입력** : 1월 1일 + ↵enter <br>
  - **출력**
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
- **Console 2**
  - **입력** : 7월 20일 + ↵enter <br>
  - **출력**
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
- **Console 3**
  - **입력** : 4월 9일 + ↵enter <br>
  - **출력**
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
- **Console 4**
  - **입력** : 11월 14일 + ↵enter <br>
  - **출력**
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
- **Console - 에러**
  - **입력** : 유효하지 않은 입력을 넣었을 때
      1. month 가 1부터 12 사이가 아닐 때
      2. day 가 1부터 <u>각 month에 해당하는 마지막 날짜</u> 사이가 아닐 때
  - **출력**
````
Sun, Earth, Moon
날짜를 입력하세요.
0월 1일
유효하지 않은 날짜를 입력하셨습니다. 다시 입력해주세요.
2월 30일
유효하지 않은 날짜를 입력하셨습니다. 다시 입력해주세요.
13월 5일
유효하지 않은 날짜를 입력하셨습니다. 다시 입력해주세요.
````
<br>

### 2-4) 느낀 점 (아쉬운 점)
제출 할 시간이 가까워져서 수정하지는 못했지만, 태양이 가운데에 위치하고 지구가 움직이는 것 처럼 보이게 하기 위해서
**_index 앞부분에 null 이었던 부분은 출력하지 않고 그냥 넘어가게 했다면 어땠을까?_** 하는 생각이 들어서 아쉬움이 남았다.

## 3단계 : 콘솔 태양계 출력 프로그램 완성
___

### 3-1) 구현 설명 (풀이 과정)

#### 설계
1. 태양과 지구를 먼저 만든다.
- 지구의 위치는 지구 위에 <u>문자와 화살표</u> 로 나타낸다. => 지구를 기준으로 위치를 설정하게 한다. (글자도 점점 사라지게)
  - <u>지구의 공전 주기</u>만 생각한다.<br>
  (지구만 시간에 따라 움직이게 구현하기 위해 month 만 고려)
2. 은하수는 오른쪽 위 ~ 왼쪽 아래로 내려가는 느낌으로 작은 별들을 찍는다.
- 추가적인 별들은 PlanetLocation으로 만들었던 작은 별들을 랜덤으로 찍는 메서드를 가져와서 사용한다.

#### 코드 구성
1. 입력을 받는다. <br>
   (오류를 확인하고, 올바른 값이 들어오면 다음 코드를 진행한다.)
2. 2차원 배열 ( __우주, 태양, 지구, 은하수__ ) 을 만든다.
3. 만든 2차원 배열을 (태양 ➡️ 은하수 ➡️ 지구) 순서대로 <u> 각자가 우주에서 해당하는 위치</u> 에 넣어준다.
   <br> (지구의 위치는 month에 영향을 받는다.)
4. 만들어진 우주에 작은 별들을 랜덤한 위치에 넣어준다.
5. 우주를 출력한다.

### 3-2) 코드 + 주석 설명 (코드 설명)
````java
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

````

### 3-3) 입력 + 출력 (실행 결과)
- **Console 1** <br>
  - **입력** : 1년 1월 1일 + ↵enter <br>
  - **출력**
````
날짜를 입력하세요.
1년 1월 1일
   .           .                        .. . +.        
      *                               .  . ...       . 
                           .         .  .         .    
                      *               +  *       *.    
               + "You Are Here"       . .      .    .  
.            +         |         +. .     +     *.     
                       |           +  +.+.* . .        
   .     +           \ | /     +  ...+..+.       .     
               +       V       .  .   .    ..          
                       .      .+  .+    .   *          
                               .  .   .                
                .           *.+    . . +          !/   
        .               . .  +   .      .        -O-   
                   *      .           *  +  *    /|    
   .        *       +   *  ..       ..  .   .     ..   
                              .               .        
  .                 + .    ..   ..                     

Process finished with exit code 0
````

- **Console 2** <br>
  - **입력** : 9321년 2월 3일 + ↵enter <br>
  - **출력**
````
날짜를 입력하세요.
9321년 2월 3일
    .                               +               +  
                                      . .  *  .   *  * 
 .                          .           +     ..  +.   
  .                .      +         +     .   .    .   
            .                      ...++   ...         
                     "You Are Here". .    . ... .     .
             *             |       + .      +      .   
                           |               .          .
    .               *    \ | /      ...          +   . 
      +                    V       .+     . +          
  +                *       .                           
                *                       .         !/   
        .                  . . . .    .  +*  .   -O-   
          .              * +..     +..  ..       /|    
.                        .  * .   . ..+                
             .      .   .       +   . .                
               .          +      .    *                

Process finished with exit code 0
````

- **Console 3** <br>
  - **입력** : 234년 7월 28일 + ↵enter <br>
  - **출력**
````
날짜를 입력하세요.
234년 7월 28일
     ..                            .  +  .  . .+*  .   
           .       .                          ...  .   
                                     + .. .  .   . .   
                                               +.      
 .                         .       *   .    .        . 
   .+                                    + +    +.     
        .    *    *       *        . .   +.   .      + 
                                  ..      ..   +       
                  .           *         .   . .        
                             +.   . +..++.   .         
                                   .     "You Are Here"
                            .  .   .   .       |       
          +    +                  .+ .. .      |       
                  .        ..    .. . .      \ | /     
                             . **.      *      V       
     .                      . ++. +. . *       .       
                        . * +  **.  .                  

Process finished with exit code 0
````

- **Console 4** <br>
  - **입력** : 52년 9월 10일 + ↵enter <br>
  - **출력**
````
날짜를 입력하세요.
52년 9월 10일
                . .                      +             
                       .   .       *        + .    ..  
 .                                  * ...     ...     *
   .                                 .    +.  .    .   
                                  ..+   ++ + + ..      
 +.+      +                .       *   .     +   .     
                 +              +  .  .  . ..       *  
      .           .                     *     .        
      .                          "You Are Here"        
    *         .               .        |          .    
                             .  .      |               
                     *               \ | /        !/   
               .                .      V         -O-   
                               *       .         /|    
              +            +   ..             .    +   
                     . .+   + . .   +                  
    .                 ..  .    * . .   .     .   .   + 

Process finished with exit code 0
````

- **Console 5** <br>
  - **입력** : 1994년 11월 1일 + ↵enter <br>
  - **출력**
````
날짜를 입력하세요.
1994년 11월 1일
                                               +.    * 
.     .                   .           +.    . ..+  ... 
 +                       .          .       .**    .   
            *                     ....    .  *.. .     
 .                                         ....  +     
                                     +.   .     .      
             .           "You Are Here"           * +  
   .                           |       ..    .         
            .      .           |                .      
        +             .      \ | /       . *..         
                   +           V       .  ..           
                               .      .           !/   
        .+       +                        *      -O-  .
                          .      .  .   .        /|    
  .          +           *   +  *    +                 
                          .. .    .                  . 
       *                 . .  . +    +.                

Process finished with exit code 0
````
- **Console - 에러** <br>
  - **입력** : 유효하지 않은 입력을 넣었을 때
    1. year 가 1부터 9999 사이가 아닐 때
    2. month 가 1부터 12 사이가 아닐 때
    3. day 가 1부터 <u>각 month에 해당하는 마지막 날짜</u> 사이가 아닐 때

  - **출력**
````
날짜를 입력하세요.
3년 2월 30일
유효하지 않은 날짜를 입력하셨습니다. 다시 입력해주세요.
200년 0월 28일
유효하지 않은 날짜를 입력하셨습니다. 다시 입력해주세요.
0년 5월 9일
유효하지 않은 날짜를 입력하셨습니다. 다시 입력해주세요.
10000년 9월 8일
유효하지 않은 날짜를 입력하셨습니다. 다시 입력해주세요.

````

### 3-4) 느낀 점
**구현에서 아쉬운 점**
1. 6월부터 태양이 조금 가려짐,
2. 7월은 태양이 완전히 가려짐.
3. 1월~7월은 태양 아래 (남쪽), 8월~12월은 태양 위 (북쪽)로 돌아서 나오도록 공전을 구현하고 싶었지만 실패함.
  - 둥근 타원형으로 공전하는 것을 구현하고 싶었지만, 대각선 일직선 상으로 밖에 구현하지 못했다.
    - 태양 주위를 서 -> 남 -> 동 -> 북 순서로 돈다.
    - 12가지 경우의 수로 위치를 정한다. (12달이니까)
      <br>
4. 은하수에 랜덤한 위치에 찍힌 별들이 골고루 퍼져있도록 하지 못한 것이 아쉽다. (별들이 뭉친 부분이 생긴다.) <br>

<br>

### 4단계 : 추가기능 구현 (미구현)
___
**요구사항** <br>
- 콘솔에서 애니메이션으로 동작하도록 구현해 본다.
- 프레임당 시간을 조절할 수 있게 구현해 본다.
- 웹 UI 또는 기타 GUI 프로그램으로 구현해 본다.
<br>
____


[jinan159](https://github.com/jinan159/codesquad_2022_sokoban/blob/master/README.md#1-1-4-%EC%A0%84%EC%B2%B4-%EC%86%8C%EC%8A%A4)
님의 README.md를 참고했습니다.