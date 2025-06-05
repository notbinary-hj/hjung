/**
 * 
 */
document.addEventListener("DOMContentLoaded", ()=>{
//	alert(axios);
	const lprodGuSel = document.getElementById("lprodGu");
	const initVal = lprodGuSel.dataset.initVal;
	const CPATH = document.body.dataset.contextPath  //data속성을 통해 contextpath를 전달받음
	axios.get(`${CPATH}/ajax/lprod`)
		.then(resp=>{
			console.log(resp.data);  //axios는 이거로 fetch와 달리 바로 꺼낼수있음.
			//data-속성이 제대로 설정돼있고 contentType이 json으로 설정돼있어야 꺼낼수있다.	
			//구조분해를 이용해서 불러오기
			
			//구조분해 없이, lprodList 자체가 응답데이터므로 {} 없게.
			const lprodList = resp.data;
			
			if(lprodList){  //있다면...
				//구조분해한 후 객체로{} 꺼낼수있음. LprodVO를 보고 이름 찾아서.
				const options = lprodList.map(({lprodGu, lprodName})=>
									`<option value="${lprodGu}">${lprodName}</option>`)
									.join("\n");
				//innerHTML로 옵션에 집어넣음
				lprodGuSel.innerHTML += options;
				
				//update문에서만 사용되려면. 즉, initVal이 존재할때면
//				if(initVal){  //1)if문 사용
//				lprodGuSel.value = initVal==undefined ? "" : initVal;  //2)삼항연산자
				lprodGuSel.value = initVal ?? "";  //3)null을 처리하는 전용 삼항연산자
			}
		})
});