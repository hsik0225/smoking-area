# Smoking Area
Smoking Area(흡연 구역 검색 서비스)는 사용자가 근처 흡연 구역을 손쉽게 찾을 수 있도록 도와주는 서비스입니다. 

지도 상에서 흡연 가능한 장소를 실시간으로 제공하여 흡연 구역을 빠르게 확인할 수 있습니다.

## WebSite
http://smoking-area.rowan.ink

## 서비스 개발 목적
흡연자들에게 흡연 구역 정보를 제공함으로써 올바른 흡연 문화를 만들어가고자 개발했습니다.
1. **흡연 구역을 찾는 시간 단축**: 흡연자들이 정해진 흡연 구역을 찾지 못해 헤매는 시간을 줄여 편리함을 제공합니다.
2. **불법 흡연 방지**: 흡연 구역을 찾지 못한 흡연자들이 공공장소에서 흡연하는 문제를 막고, 규정을 준수할 수 있도록 지원합니다.

## 기술 스택
- **백엔드**: Kotlin, Spirng Boot 
- **데이터베이스**: MongoDB, Redis

## 설치 및 실행 방법
이 어플리케이션은 흡연 구역 시설 정보를 오픈 데이터 포탈 API 를 통해 가져오며, 흡연 구역 주소를 위, 경도 좌표로 변환하기 위해 Kakao API를 사용합니다.

### 오픈 데이터 포탈, Kakao API 미사용
실제 오픈 데이터 포탈 API 혹은 Kakao API를 사용하지 않을 사용자들을 위한 설명서입니다.
1. 이 리포지토리를 클론합니다.
```bash
git clone https://github.com/hsik0225/smoking-area.git
```

2. IDE에서 Application을 실행합니다.

### 오픈 데이터 포탈, Kakao API 사용
실제 오픈 데이터 포탈 API 혹은 Kakao API를 사용할 사용자들을 위한 설명서입니다.
1. 오픈 데이터 포탈(https://www.data.go.kr/) 혹은 Kakao Developers(https://developers.kakao.com/) 에서 API_KEY를 발급받습니다. 
2. 이 리포지토리를 클론합니다.
```bash
git clone https://github.com/hsik0225/smoking-area.git
```

3. `src/main/resources/application.yaml` 파일에서 `app.open-data-portal.api-key`를 오픈 데이터 포탈에서 발급받은 API_KEY 값으로 입력합니다. 만약, Kakao API 사용자라면, `app.kakao.api-key`를 Kakao Developers에서 발급받은 REST_API_KEY 값으로 입력합니다.

4. `src/main/resources/application.yaml` 파일에서 `app.open-data-portal.use-dummy`를 `false`로 입력합니다. 만약 Kakao API 사용자라면, `app.kakao.use-dummy`를 `false`로 입력합니다.

만약 오픈 데이터 포탈 API를 사용하며 발급받은 API KEY가 `test1010`이라면, `src/main/resources/application.yaml` 파일의 내용은 다음과 같아야 합니다.

```
...
app.open-data-portal.api-key=test1010
app.open-data-portal.use-dummy=false
```
