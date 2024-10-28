# Smoking Area
Smoking Area(흡연 구역 검색 서비스)는 사용자가 근처 흡연 구역을 손쉽게 찾을 수 있도록 도와주는 서비스입니다. 

지도 상에서 흡연 가능한 장소를 실시간으로 제공하여 흡연 구역을 빠르게 확인할 수 있습니다.

## WebSite
https://smoking-area.rowan.ink

## 서비스 개발 목적
흡연자들에게 흡연 구역 정보를 제공함으로써 올바른 흡연 문화를 만들어가고자 개발했습니다.
1. **흡연 구역을 찾는 시간 단축**: 흡연자들이 정해진 흡연 구역을 찾지 못해 헤매는 시간을 줄여 편리함을 제공합니다.
2. **불법 흡연 방지**: 흡연 구역을 찾지 못한 흡연자들이 공공장소에서 흡연하는 문제를 막고, 규정을 준수할 수 있도록 지원합니다.

## 기술 스택
- **백엔드**: Kotlin, Spirng Boot 
- **데이터베이스**: MongoDB, Redis

## 설치 및 실행 방법
이 어플리케이션은 다음과 같은 외부 API를 사용하고 있습니다.
- 오픈 데이터 포탈 API : 흡연 구역 시설 정보 조회
- 카카오 로컬 API : 흡연 구역 주소를 위, 경도 좌표로 변환
- 네이버 지도 API : 브라우저에 네이버 지도 노출

### 외부 API 미사용
네이버 지도를 브라우저에 보여주기 위해서는 네이버 클라우드로부터 발급받은 API KEY가 필요합니다. 해당 섹션에서는 외부 API를 미사용하기에 브라우저에 지도가 표시되지 않고, 어플리케이션만 실행합니다.
1. 이 리포지토리를 클론합니다.
```bash
git clone https://github.com/hsik0225/smoking-area.git
```

2. IDE에서 Application을 실행하거나 `java jar`로 어플리케이션일 실행합니다.

### 외부 API 사용
실제 오픈 데이터 포탈 API 혹은 Kakao API를 사용할 사용자들을 위한 설명서입니다.
1. API KEY 발급
다음의 사이트들에서 API KEY를 발급받습니다. 
- 오픈 데이터 포탈(https://www.data.go.kr/)
- Kakao Developers(https://developers.kakao.com/)
- 네이버 클라우드 (https://console.ncloud.com/naver-service/application)
2. 이 리포지토리를 클론합니다.
```bash
git clone https://github.com/hsik0225/smoking-area.git
```

3. `src/main/resources/application.yaml` 파일을 수정합니다.

외부 API를 호출하기 위해 application.yml 파일을 다음과 같이 수정합니다.
```yml
# 변경 전
...
app:
    open-data-portal:
        host: https://api.odcloud.kr
        api-key: test1234

    kakao:
        host: https://dapi.kakao.com
        api-key: test5678

# 변경 후
...
app:
  open-data-portal:
    host: https://api.odcloud.kr
    api-key: { 발급받은 API_KEY }
    use-dummy: false

  kakao:
    host: https://dapi.kakao.com
    api-key: { 발급받은 API_KEY }
    use-dummy: false
```

4. `src/main/resources/static/index.html` 파일을 수정합니다.

네이버 지도 API를 사용하기 위해 설정되어있는 네이버 클라우드 API KEY를 발급받은 KEY로 수정합니다.
```html
...
<!-- 변경 전 -->
<script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=m9b7lkyrot"></script>

...
<!-- 변경 후 -->
<script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId={ 발급받은 네이버 클라우드 API KEY }"></script>
```

5. 어플리케이션을 실행합니다.
