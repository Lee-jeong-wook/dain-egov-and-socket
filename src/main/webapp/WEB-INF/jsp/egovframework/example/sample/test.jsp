<%@ page import="egovframework.example.sample.service.CCTV" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<CCTV> cctvList = (List<CCTV>)request.getAttribute("vo");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>WebSocket Client</title>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .cctv-button {
            margin: 5px;
            padding: 10px;
            border: 1px solid #ccc;
            background-color: #f9f9f9;
            cursor: pointer;
            width: 300px;
        }
        .cctv-button:hover {
            background-color: #eef;
        }
        .toggle {
            display: none;
            border: 1px solid #aaa;
            padding: 15px;
            margin-top: 15px;
            width: 300px;
            background: #f4f4f4;
        }
        .toggle.active {
            display: block;
        }
        .toggle input {
            width: 90%;
            padding: 5px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<h2>WebSocket 테스트</h2>

<label>메시지: <input type="text" class="toggle-event" /></label><br/>
<button onclick="sendMessage()">전송</button>

<h3>받은 메시지:</h3>
<pre id="receivedMessages"></pre>

<% for (CCTV cctv : cctvList) { %>
<button class="cctv-button" onclick="cctvClick('<%=cctv.getCctvID()%>')">
    <div>
        <strong>ID:</strong> <%=cctv.getCctvID()%><br/>
        <strong>이름:</strong> <%=cctv.getCctvName()%>
    </div>
</button>
<% } %>

<div class="toggle">
    <p>이 CCTV를 구독하시겠습니까?</p>
    <button onclick="subscribe()">이 CCTV 구독하기</button>
</div>

<script>
    let stompClient = null;
    let cctvId = null;
    let subscribed = false;

    function connect() {
        if (stompClient && stompClient.connected) return;
        const socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function () {
            console.log("WebSocket 연결됨");
        });
    }

    function cctvClick(id) {
        cctvId = id;
        subscribed = false;  // 새 CCTV 선택하면 구독 초기화
        document.querySelector(".toggle").classList.add("active");
        console.log("CCTV 선택됨:", id);
    }

    function subscribe() {
        if (!stompClient || !stompClient.connected) {
            alert("WebSocket이 아직 연결되지 않았습니다.");
            return;
        }
        if (subscribed) {
            alert("이미 구독 중입니다.");
            return;
        }
        const subscribeUrl = `/sub/cctv/`+cctvId;
        console.log(subscribeUrl);
        stompClient.subscribe(subscribeUrl, function (message) {
            const msg = JSON.parse(message.body);
            alert(msg.cctvLocname + `에`+ msg.event+`를`+ msg.cctvId + `가 감지`);
        });
        subscribed = true;
        alert(cctvId+` 구독 시작`);
    }

    function sendMessage() {
        if (!stompClient || !stompClient.connected) {
            alert("WebSocket이 아직 연결되지 않았습니다.");
            return;
        }
        if (!cctvId) {
            alert("CCTV를 선택하세요.");
            return;
        }
        const message = {
            cctvId: cctvId,
            event: document.querySelector(".toggle-event").value
        };
        stompClient.send("/pub/cctv", {}, JSON.stringify(message));
    }

    window.onload = connect;
</script>
</body>
</html>
