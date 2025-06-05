<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<title>제조사목록</title>
<script>
	document.addEventListener("DOMContentLoaded", ()=>{
// 		$("body")
	});
</script>
</head>
<body>
<table class="table">
	<thead>
		<tr>
			<th>제조사명</th>
			<th>분류명</th>
			<th>소재지</th>
			<th>전화번호</th>
			<th>담당자</th>
			<th>거래은행</th>
			<th>거래품목수</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty buyerList }">
			<c:forEach items="${buyerList }" var="buyer">
				<c:url value="/buyer/buyerDetail.do" var="detailURL">
					<c:param name="what" value="${buyer.buyerId }"/>
				</c:url>
				<tr>
					<td>
					<a href="${detailURL }">${buyer.buyerName }</a>
					</td>
					<td>${buyer.lprod.lprodName}</td>  <!-- has A 관계 -->
					<td>${buyer.buyerAdd1}</td>
					<td>${buyer.buyerComtel}</td>
					<td>${buyer.buyerCharger}</td>
					<td>${buyer.buyerBank}</td>
					<td>${buyer.prodList.size() }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty buyerList }">
			<tr>
				<td colspan="7">제조사 없음.</td>
			</tr>
		</c:if>		
	</tbody>
</table>
</body>
</html>