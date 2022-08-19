# node.js

## 프로젝트 사전 설치

```
npm install
```

```
npm install -g pm2
```

### 서버 실행

```
pm2 start server.js
```

### 서버 상태 확인

```
pm2 list
```

### 서버 중단
```
pm2 stop 해당 하는 server id
```
```
pm2 delete 해당 하는 server id
```

### 기본 포트 번호

- 포트번호 : **9002**

### 사용법

- post로 tts/ttsrequest에 String을 보냅니다.
- tts 음성이 binary파일이 돌아옵니다.
- decode를 통해 데이터를 음성파일로 바꾼 후 출력하거나 파일을 만들 수 있습니다.
