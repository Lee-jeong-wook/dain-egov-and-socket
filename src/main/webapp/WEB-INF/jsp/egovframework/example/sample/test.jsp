<%--
  Created by IntelliJ IDEA.
  User: 다인
  Date: 25. 6. 11.
  Time: 오후 3:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>WebSocket Client</title>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
</head>
<body>
<h2>WebSocket 테스트</h2>
<label>CCTV ID: <input type="text" id="cctvId" value="123" /></label><br/>
<label>메시지: <input type="text" id="messageText" /></label><br/>
<button onclick="sendMessage()">전송</button>

<h3>받은 메시지:</h3>
<pre id="receivedMessages"></pre>

<script>
    let stompClient = null;
    let cctvId = null;

    function connect() {
        const socket = new SockJS('/topic');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function () {
            cctvId = document.getElementById("cctvId").value;
            const subscribeUrl = `/sub/cctv/123`;
            stompClient.subscribe(subscribeUrl, function (message) {
                const msg = JSON.parse(message.body);
                document.getElementById("receivedMessages").textContent += JSON.stringify(msg, null, 2) + '\n';
            });
        });
    }

    function sendMessage() {
        if (!stompClient || !stompClient.connected) {
            connect();
            setTimeout(sendMessage, 500); // 연결 후 전송 딜레이
            return;
        }

        const cctvId = document.getElementById("cctvId").value;
        const message = {
            cctvId: cctvId,
            content: document.getElementById("messageText").value,
            sender: "clientUser"
        };

        stompClient.send("/pub/cctv", {}, JSON.stringify(message));
    }

    // 페이지 로딩 후 자동 연결
    window.onload = connect;
</script>
</body>
</html>
