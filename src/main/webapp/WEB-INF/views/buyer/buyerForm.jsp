<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script src="<c:url value='/resources/js/app/buyer/buyerForm.js'/>"></script>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<h5>Form controls</h5>
		</div>
		<div class="card-body">
			<div class="row">
			<form:form modelAttribute="buyer" enctype="application/x-www-form-urlencoded">
					<div class="form-group">
						<label class="form-label" for="buyerName">거래처이름(*)</label>
						<form:input path="buyerName" cssClass="form-control" placeholder="거래처이름(*)"/>
						<!-- path의 buyerName은 vo에서 옴. buyer.buyerName이 되므로, value 따로 안줘도 됨 -->
						<form:errors cssClass="text-danger" path="buyerName"/>
						<!-- 
						모든 커스텀 태그는 백그라운드 코드(서버에서 돌아감), 나중에 다 자바 코드로 바뀜, 
						class가 자바에선 키워드로 사용돼서 충돌날 일 대비해 cssClass를 썼지만 
						그 대신 html의 class 태그를 그대로 써도 작동은 함.
						 -->
					</div>
					<div class="form-group">
						<label class="form-label" for="lprodGu">분류코드(*)</label>
						<select name="lprodGu" id="lprodGu" class="form-select"
							data-init-val="${buyer.lprodGu }">
								<option value="">분류선택</option>
						</select>
						<form:errors cssClass="text-danger" path="lprodGu"/>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerBank">주거래은행</label>
						<form:input path="buyerBank" cssClass="form-control" placeholder="주거래은행"/>
						<form:errors cssClass="text-danger" path="buyerBank"/>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerBankno">계좌번호</label>
						<form:input path="buyerBankno" cssClass="form-control" placeholder="계좌번호"/>
						<form:errors cssClass="text-danger" path="buyerBankno"/>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerBankname">계좌주</label>
						<form:input path="buyerBankname" cssClass="form-control" placeholder="계좌주"/>
						<form:errors cssClass="text-danger" path="buyerBankname"/>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerZip">우편번호</label>
						<form:input path="buyerZip" cssClass="form-control" placeholder="우편번호"/>
						<form:errors cssClass="text-danger" path="buyerZip"/>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerAdd1">기본주소</label>
						<form:input path="buyerAdd1" cssClass="form-control" placeholder="기본주소"/>
						<form:errors cssClass="text-danger" path="buyerAdd1"/>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerAdd2">상세주소</label>
						<form:input path="buyerAdd2" cssClass="form-control" placeholder="상세주소"/>
						<form:errors cssClass="text-danger" path="buyerAdd2"/>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerComtel">회사전화번호</label>
						<form:input path="buyerComtel" cssClass="form-control" placeholder="회사전화번호"/>
						<form:errors cssClass="text-danger" path="buyerComtel"/>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerFax">팩스번호</label>
						<form:input path="buyerFax" cssClass="form-control" placeholder="팩스번호"/>
						<form:errors cssClass="text-danger" path="buyerFax"/>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerMail">메일주소</label>
						<form:input path="buyerMail" cssClass="form-control" type="email" placeholder="메일주소"/>
						<form:errors cssClass="text-danger" path="buyerMail"/>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerCharger">담당자</label>
						<form:input path="buyerCharger" cssClass="form-control" placeholder="담당자"/>
						<form:errors cssClass="text-danger" path="buyerCharger"/>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerTelext">내선번호</label>
						<form:input path="buyerTelext" cssClass="form-control" maxlength="2" placeholder="내선번호"/>
						<form:errors cssClass="text-danger" path="buyerTelext"/>
					</div>
					<div>
					<button type="submit" class="btn btn-primary mb-4">Submit</button>
					<button type="reset" class="btn btn-danger mb-4">Reset</button>
					</div>
				</form:form>
				</form>


					<div class="form-group">
						<label class="form-label" for="exampleFormControlSelect1">Example
							select</label> <select class="form-select" id="exampleFormControlSelect1">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
						</select>
					</div>
					<div class="form-group">
						<label class="form-label" for="exampleFormControlTextarea1">Example
							textarea</label>
						<textarea class="form-control" id="exampleFormControlTextarea1"
							rows="3"></textarea>
					</div>
			</div>
		</div>
	</div>
</body>
</html>