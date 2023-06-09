<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>

<div class="container my-3">
    <div class="container">
        <form action="/join" method="post" onsubmit="return valid()"> <%--valid()함수를 호출하고 true를 리턴하면 action이 발동됨--%>
            <div class="d-flex form-group mb-2">
                <input type="text" name="username" class="form-control" placeholder="Enter username"
                       id="username">
                <button type="button" class="badge bg-secondary ms-2">중복확인</button>
            </div>

            <div class="form-group mb-2">
                <input type="password" name="password" class="form-control" placeholder="Enter password"
                       id="password">
            </div>

            <div class="form-group mb-2">
                <input type="password" class="form-control" placeholder="Enter passwordCheck"
                       id="passwordCheck"> <%--name이 없는 애들은 서버쪽으로 전송을 안하겠다는 의미--%>
            </div>

            <div class="form-group mb-2">
                <input type="email" name="email" class="form-control" placeholder="Enter email">
            </div>

            <button class="btn btn-primary">회원가입</button> <%--form 태그 안에 있는 버튼은 submit 타입 = 클릭하면 form 태그의 action 발동--%>
        </form>

    </div>
</div>

<script>
    function valid() {
        alert("회원가입 유효성 검사");
        return true;
    }
</script>

<%@ include file="../layout/footer.jsp" %>