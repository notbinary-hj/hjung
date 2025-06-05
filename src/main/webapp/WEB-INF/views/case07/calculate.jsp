<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form modelAttribute="cal">  <!-- method="post"는 기본값이라 생략 -->
	<form:input path="op1" type="number"/>
	<form:errors path="op1" cssClass="error"/>
	<!-- path : 파라미터의 이름(name과 id 다 여기서 뽑음), 
	여기까지 전달되는 모델 속성의 프로퍼티 이름 결정 -->
		+
	<form:input path="op2" type="number"/>
	<form:errors path="op2" cssClass="error"/>
	<button type="submit">전송</button>  <!-- 전송은 value가 없으니 굳이 커스텀태그 아녀도 됨 -->
</form:form>


	<c:if test="${not empty cal }">  <!-- result는 Integer가 아니라 int여서 어차피 null이 불가, 그거 대신 cal이 있냐없냐를 봐야. -->
	연산 결과 : ${result }
	</c:if>
</body>
</html>