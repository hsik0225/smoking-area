# Smoking Area
Smoking Area(喫煙所検索サービス）は、ユーザーが近くの喫煙所を簡単に見つけられるようサポートするサービスです。

地図上でリアルタイムに喫煙可能な場所を提供し、喫煙所を素早く確認できます。

## WebSite
https://smoking-area.rowan.ink

## サービス開発の目的
喫煙者に喫煙所の情報を提供することで、適切な喫煙文化を作り上げることを目指して開発しました。
1. **喫煙所を探す時間の短縮**: 喫煙者が指定された喫煙所を見つけられずに迷う時間を減らし、利便性を提供します。
2. **違法喫煙の防止**: 喫煙所を見つけられない喫煙者が公共の場所で喫煙する問題を防ぎ、規則を遵守できるようサポートします。

## 技術スタック
- **Application**: Kotlin, Spirng Boot 
- **DataBase**: MongoDB, Redis

## インストールと実行方法
このアプリケーションは以下の外部APIを使用しています。
- オープンデータポータル API : 喫煙所情報の照会
- Kakao Local API : 喫煙所の住所を緯度, 経度座標に変換
- 네이버 지도 API : ブラウザにNaver地図を表示

### 外部API未使用
Naver地図をブラウザに表示するには、Naver Cloudから発行されたAPIキーが必要です。このセクションでは外部APIを使用しないため、ブラウザに地図は表示されず、アプリケーションのみが実行されます。
1. このリポジトリをクローンします。
```bash
git clone https://github.com/hsik0225/smoking-area.git
```

2. IDEでApplicationを実行するか、java -jarでアプリケーションを実行します。

### 外部API使用
実際にオープンデータポータルAPIまたはKakao APIを使用するユーザー向けの説明書です。
1. APIキーの発行

以下のサイトからAPIキーを発行します。
- オープンデータポータル(https://www.data.go.kr/)
- Kakao Developers(https://developers.kakao.com/)
- Naver Cloud(https://console.ncloud.com/naver-service/application)
2. このリポジトリをクローンします。
```bash
git clone https://github.com/hsik0225/smoking-area.git
```

3. `src/main/resources/application.yaml` ファイルを修正します。

外部APIを呼び出すために、application.ymlファイルを以下のように修正します。
```yml
# 変更 前
...
app:
  open-data-portal:
    host: https://api.odcloud.kr
    api-key: test1234

  kakao:
    host: https://dapi.kakao.com
    api-key: test5678

# 変更 後
...
app:
  open-data-portal:
    host: https://api.odcloud.kr
    api-key: { 発行されたAPI_KEY }
    use-dummy: false

  kakao:
    host: https://dapi.kakao.com
    api-key: { 発行されたAPI_KEY }
    use-dummy: false
```

4. `src/main/resources/static/index.html` ファイルを修正します。
   NAVER地図APIを使用するために設定されているNAVERクラウドAPIキーを発行されたキーに修正します。
```html
...
<!-- 変更 前 -->
<script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=m9b7lkyrot"></script>

...
<!-- 変更 後 -->
<script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId={ 発行された NAVER クラウドAPIキー }"></script>
```

5. アプリケーションを実行します。
