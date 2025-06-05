<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>제조사 상세 조회</h4>
	<table class="table table-bordered">
		<tr>
			<td colspan="2">
				<c:url value="/buyer/buyerUpdate.do" var="updateURL">
					<c:param name="what" value="${buyer.buyerId }"></c:param>
				</c:url>
				<a class="btn btn-primary" href="${updateURL }">수정</a>
			</td>
		</tr>
		<tr>
			<th>기본주소</th>
			<td>${buyer.buyerAdd1}</td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td>${buyer.buyerAdd2}</td>
		</tr>
		<tr>
			<th>회사전화번호</th>
			<td>${buyer.buyerComtel}</td>
		</tr>
		<tr>
			<th>팩스번호</th>
			<td>${buyer.buyerFax}</td>
		</tr>
		<tr>
			<th>메일주소</th>
			<td>${buyer.buyerMail}</td>
		</tr>
		<tr>
			<th>담당자</th>
			<td>${buyer.buyerCharger}</td>
		</tr>
		<tr>
			<th>모름</th>
			<td>${buyer.buyerTelext}</td>
		</tr>
		<tr>
			<th>거래처이름(*)</th>
			<td>${buyer.buyerName}</td>
		</tr>
		<tr>
			<th>분류코드(*)</th>
			<td>${buyer.lprodGu}</td>
		</tr>
		<tr>
			<th>주거래은행</th>
			<td>${buyer.buyerBank}</td>
		</tr>
		<tr>
			<th>계좌번호</th>
			<td>${buyer.buyerBankno}</td>
		</tr>
		<tr>
			<th>계좌주</th>
			<td>${buyer.buyerBankname}</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${buyer.buyerZip}</td>
		</tr>
		<tr>
			<th>거래품목</th>
			<td>
				<table>
					<thead>
						<tr>
							<th>품목명</th>
							<th>구매가</th>
							<th>판매가</th>
						</tr>
					</thead>
					<tbody>
					<!-- 거래품목이 있는지 없는지 조건문의 형태 -->
						<c:choose>
							<c:when test="${not empty buyer.prodList }">
								<c:forEach items="${buyer.prodList }" var="prod">
									<tr>
										<td>${prod.prodName }</td>
										<td>${prod.prodCost }</td>
										<td>${prod.prodPrice }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="3">거래품목 없음.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>