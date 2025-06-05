<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.uploaded{
		background-color: blue;
	}
</style>
</head>
<body>
<form method="post" enctype="multipart/form-data">
	<input type="file" name="uploadFile" />
	<button type="submit">업로드</button>
</form>
<ul>
	<c:forEach items="${fileNames }" var="file">
<!-- 	현재 파일명 = 방금 업로드된 파일명 이면 uploaded라는 클래스를 적용하고 
		아니면 normal이라는 클래스를 적용. 삼항연산자. -->
		<li class="${file eq uploaded ? 'uploaded':'normal'}">
		<a href='<c:url value="/case10/files/${file }"/>'>${file }</a>
		<button class="del" data-name="${file }">삭제</button>  
		<!-- data-로 키값(name) 숨겨들여올수있다. -->
		</li> 
	</c:forEach>
</ul>
<script>
	document.addEventListener("DOMContentLoaded", ()=>{
		$(".del").on("click", async (e)=>{
			if(!confirm("지울래?")) return;
			
			const name = e.target.dataset.name;
			const resp = await axios.delete(`<c:url value="/case10/files/\${name }"/>`);
			const {success, target} = resp.data;
			if(success){
				$(e.target).closest("li").remove();
			}
		});
	});
<%--	//제이쿼리 쓰려면 DOMContentLoaded 안에서 사용해야. 
		사이트매쉬에 의해 제이쿼리가 적용되는데(mantisPostScript), 콘텐츠가 다 로딩 끝나기 전엔 적용 안됨.
		따라서 콘텐츠가 전부 로딩되게 DOMContentLoaded를 이벤트로 준 후, 
	document.addEventListener("DOMContentLoaded", ()=>{
		$(".del").on("click", async (e)=>{
			if(!confirm("지울래?")) return;  //!니까 취소했거나 아니요 누른거고 return 그냥하면됨
			
			//this 못 씀, event(e) 써야
			const name = e.target.dataset.name;  //삭제할 파일 데이터 꺼냄
			const resp = await axios.delete(`<c:url value="/case10/files/\${name }"/>`);  //비동기로 삭제
			//여긴 jsp니까 el이라 역슬래시 붙음  //contextpath 넣으려면 코어 태그
			//c:url은 백그라운드 코드라 사용가능  //${file }은 템플릿으로 사용되는 중
			const {success, target} = resp.data;  //누가 지워졌는지 알려면 target 같이 내보냄
			//{구조분해 문법}
			if(success){
				//방법1) 현재 페이지를 리프래시해서 리스트를 새로 그림
				//방법2) li태그 전체를 지움. 우린 button만 가지니 부모를, 그중에서도 li태그를 찾아야.
// 				$(e.target).parents("li:first")  //$()로 묶어서 제이쿼리 객체 됨
				//parents므로 부모나 조상 중에 li를 찾고 그중 첫번째 걸 가져와
				$(e.target).closest("li").remove();  //혹은 closest로 바로 찾아도 됨.
			}
		});
	}); --%>
</script>
</body>
</html>