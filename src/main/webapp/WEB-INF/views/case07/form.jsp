<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 절대경로 만들어낼 때 필요 -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> <!-- 스프링이 제공하는 '폼 태그 라이브러리' -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form modelAttribute="dummy" 
action="${pageContext.request.contextPath }/case07/commandObject02" method="get">
<pre>
<%-- 	<input type="text" name="name" value="${name }" /> --%>
	<form:input path="name" />  <!-- 기본값 type="text" -->
	<form:errors path="name" />
<%-- 	<input type="number" name="age" value="${age }"/> --%>
	<form:input path="age" type="number"/>  <!-- 스프링의 form태그는 html이 가진 모든 옵션 가질 수 있음. 코드 어시스트 안돼도 type을 number로 그대로 가져와 써도 됨 -->
	<form:errors path="age" />
	<!-- form:(태그) 하면 기본값이 설정된 태그 UI를 스프링이 자동으로 만들어줌. -->
	<form:checkbox path="hobbies" value="취미1" label="취미1"/>
	<form:checkbox path="hobbies" value="취미2" label="취미2"/> <!-- 아래 체크박스 만든거랑 똑같음 -->
	<!-- 장점) 정상적인 체크값은 새로고침해도 유지됨. 단점) hobbies가 전달되지 않으면 페이지 렌더링 자체가 오류남.  -->
	
<!-- 	<input type="checkbox" name="hobbies" value="취미3">취미1 -->
<!-- 	<input type="checkbox" name="hobbies" value="취미4">취미2 -->
	<form:errors path="hobbies" /> <!-- dummy는 모델에서, hobbies는 vo의 프로퍼티에서 결정됨. 파라미터의 네임값으로도 그게 반영된거 -->
	기본값 : ${hobbies }
	<button type="submit">전송</button>
</pre>
</form:form>
</body>
</html>