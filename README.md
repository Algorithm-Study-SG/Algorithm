# ⌨️ Algorithm Study

| 진행 기간        | 2023년 7월 31일 ~ ing                                                                                                                                                                        |
| ---------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 스터디 장소      | [역삼역](https://map.naver.com/v5/entry/subway-station/221) (SSAFY 건물 주변)                                                                                                                |
| 정기 스터디 시간 | SSAFY 정규 수업 시간 이후                                                                                                                                                                    |
| 플랫폼           | [백준](https://www.acmicpc.net/)                                                                                                                                                             |
| 언어             | ![C++](https://img.shields.io/badge/C++-00599C?style=flat-square&logo=C%2B%2B&logoColor=white) ![Java](https://img.shields.io/badge/Java-3766AB?style=flat-square&logo=Java&logoColor=white) |

## 🤼 스터디 멤버

|              ![snowman2810](https://avatars.githubusercontent.com/u/140784660?)               |                 ![JisinKeo](https://avatars.githubusercontent.com/u/97288251)                 |               ![KIMSEI1124](https://avatars.githubusercontent.com/u/74192619?)                |                 ![sjhjack](https://avatars.githubusercontent.com/u/102958758?)                 |                 ![INSEA-99](https://avatars.githubusercontent.com/u/61490878?)                 |
| :-------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------: |
|                         [snowman2810](https://github.com/snowman2810)                         |                            [JisinKeo](https://github.com/JisinKeo)                            |                          [KIMSEI1124](https://github.com/KIMSEI1124)                          |                             [sjhjack](https://github.com/sjhjack)                              |                            [INSEA-99](https://github.com/INSEA-99)                             |
| ![Java](https://img.shields.io/badge/Java-3766AB?style=flat-square&logo=Java&logoColor=white) | ![Java](https://img.shields.io/badge/Java-3766AB?style=flat-square&logo=Java&logoColor=white) | ![Java](https://img.shields.io/badge/Java-3766AB?style=flat-square&logo=Java&logoColor=white) | ![C++](https://img.shields.io/badge/C++-00599C?style=flat-square&logo=C%2B%2B&logoColor=white) | ![C++](https://img.shields.io/badge/C++-00599C?style=flat-square&logo=C%2B%2B&logoColor=white) |

## ❇️ 이번 주 문제

|     날짜      |                    문제 유형                    |                                                                                                                                                 문제                                                                                                                                                  |                                                                                                                                                                                                                                          난이도                                                                                                                                                                                                                                           |
| :-----------: | :---------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| 07-31 ~ 08-06 | 스택<br />자료구조<br />백트래킹<br />완전 탐색 | 1. [스택 수열](https://www.acmicpc.net/problem/1874)<br />2. [절대값 힙](https://www.acmicpc.net/problem/11286)<br />3. [부분 수열의 합](https://www.acmicpc.net/problem/1182)<br />4. [암호 만들기](https://www.acmicpc.net/problem/1759)<br />5. [치킨 배달](https://www.acmicpc.net/problem/15686) | <img height="20px" width="25px" src="https://d2gd6pc034wcta.cloudfront.net/tier/9.svg"/><br /><img height="20px" width="25px" src="https://d2gd6pc034wcta.cloudfront.net/tier/10.svg"/><br /><img height="20px" width="25px" src="https://d2gd6pc034wcta.cloudfront.net/tier/9.svg"/><br /><img height="20px" width="25px" src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg"/><br /><img height="20px" width="25px" src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg"/><br /> |

## 📂 파일 구조

```text
Algorithm
├── KIMSEI1124
│   └── week01
│       ├── Main1.java
│       ├── Main2.java
│       ├── Main3.java
│       ├── Main4.java
│       └── Main5.java
│   └── week02
│       ├── Main1.java
|       ├── ...
│       └── Main5.java
├── JisinKeo
│   └── week01
│       ├── Main1.java
|       ├── ...
│       └── Main5.java
└── README.md
```

1. 상위 폴더 하위에 자신의 `GIHUB` 아이디의 폴더를 만듭니다.
2. 주차별로 `week${주차 번호}`의 폴더를 만듭니다. 예시) 2주차이면 `week02` 폴더를 만듭니다.
3. 하위에 `BJ${문제 번호}.java` 혹은 `BJ${문제 번호}.cpp` 소스 코드를 작성합니다.

설명 문서를 추가할 경우 추가하여도 좋습니다.

> ⚠️ Warning
>
> 1. **10주차 이상으로 할 수도 있으니 1부터 9의 숫자 앞에는 0을 반드시 붙여주세요!**
>    예시) `week01`, `week07`
>
> 2. **절대로 남의 폴더를 건드리지마세요!**

## 🎋 브랜치 규칙

브랜치는 주차별로 각자 만든 후 스터디 진행 전까지 `PR`을 진행합니다.

브랜치 네이밍 규칙은 다음과 같습니다.

`${자신의 GITHUB ID}/week${주차 번호}` 입니다.
예시) KIMSEI1124, 2주차이면 브랜치 네이밍은 다음과 같습니다 -> KIMSEI1124/week02

### 터미널에서 브랜치 만드는 방법

- **`git checkout -b ${브랜치 이름}`**

예시는 다음과 같습니다. `git checkout -b KIMSEI1124/week02`

> ⭐ Info  
> `git checkout -b ${브랜치 이름}` 은 두개의 명령어가 합쳐진 명령어입니다.
>
> 1. `git branch ${브랜치 이름}` : 브랜치를 생성하는 명령어 입니다.
> 2. `git checkout ${브랜치 이름}` : 해당 브랜치로 이동하는 명령어입니다.
>
> 해당 두개의 명령어를 한꺼번에 실행하는 명령어가 위에서 설명한 명령어입니다.

### 현재 브랜치 확인하는 방법

- **`git branch`**

<img width="418" alt="2023-08-03_10-13-37" src="https://github.com/Algorithm-Study-SG/Algorithm/assets/74192619/86ab88c5-a72c-405b-8391-68763eb9bbd7">

다음과 같이 `*` 가 있는 브랜치가 현재 브랜치입니다. 나가는 방법은 `q` 를 누르면 됩니다.

> ⭐ Info  
> `q`는 `quit`의 단축어입니다.

### 브랜치 삭제하는 방법

- `git branch -D ${브랜치 이름}`

해당 명령어로 사용하지 않는 브랜치를 삭제할 수 있습니다.

> ⚠️ Warning  
> `main` 브랜치는 절대로 삭제하시면 안됩니다.

## 🌈 PR 규칙

- PR 제목은 다음과 같이 작성합니다. `[주차] 내용` 예시로는 다음과 같습니다. 예시) [week01] 4문제 풀었습니다.
- PR의 내용은 특이사항과 같은 내용을 작성해 주시고 추후 템플릿을 작성하겠습니다.
- 리뷰어로 전부를 추가하고 코드를 **반드시 보고** 궁금한 부분이 있으면 **스터디 당일날 혹은 코멘트로 질문**합니다.
- `Merge`후 원격저장소에 있는 브랜치는 삭제합니다.

## 💽 Git 명령어 간편 설명서

**다음은 CLI 환경에서 진행합니다**

1. 작성한 파일을 `git` 에 추가합니다. `git add .`
   주의할 점은 프로젝트 최상위 폴더에서 진행해야 합니다.
2. 추가한 파일을 커밋합니다 `git commit -m "${커밋 메시지}"`
   커밋이란? 출처 : [위키백과](<https://ko.wikipedia.org/wiki/%EC%BB%A4%EB%B0%8B_(%EB%B2%84%EC%A0%84_%EA%B4%80%EB%A6%AC)>)
   저장소에 소스 코드의 일부의 최신 변경사항을 추가함으로써 이러한 변경사항을 저장소의 최상위 리비전의 일부분으로 만들어주는 것을 말한다.
   - `-m` 옵션은 커밋 메시지를 추가할 수 있습니다.
3. `git push origin ${브랜치 이름}` 으로 원격 저장소에 브랜치가 없으면 해당 브랜치를 생성하고 있으면 업데이트 합니다.

---

해당 README 는 다음 `Repository`를 참고하여 제작했습니다.
[CodeSquad-2023-BE-Study Algorithm-Study](https://github.com/CodeSquad-2023-BE-Study/Algorithm-Study/tree/week23)
