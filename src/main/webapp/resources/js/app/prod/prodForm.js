/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {
	//	alert(axios);
	const lprodGuSel = document.getElementById("lprodGu");
	const buyerIdSel = document.getElementById("buyerId");
	
	const guinitVal = lprodGuSel.dataset.initVal;
	const idinitVal = buyerIdSel.dataset.initVal;
	
	const CPATH = document.body.dataset.contextPath;  //body 안의 data속성임! body 빼먹지X
	axios.get(`${CPATH}/ajax/lprod`)
		.then(resp => {  //promise로 반환되니까 .then으로 받기
			//받은 프로미스에서 resp를 꺼냄
			console.log(resp.data);  //아무것도 아직 없으니 {}로 돌아옴 확인.

			//lprodList를 꺼내면 전부 있음. 얘도 분류코드는 lprod에서 꺼내야 해
			//			const lprodList = resp.data.lprodList  //lprodList를 구조분해 없이 꺼내올 경우...
			const { lprodList } = resp.data;  //lprodList를 구조분해해놓고 바로 꺼내올 경우...

			if (lprodList) {  //있다면...
				//구조분해한 후 객체로{} 꺼낼수있음. LprodVO를 보고 이름 찾아서.
				const options = lprodList.map(({ lprodGu, lprodName }) =>
					`<option value="${lprodGu}">${lprodName}</option>`)
					.join("\n");
				//innerHTML로 옵션에 집어넣음
				lprodGuSel.innerHTML += options;

				//update문에서만 사용되려면. 즉, initVal이 존재할때면
				//				if(initVal){  //1)if문 사용
				//				lprodGuSel.value = initVal==undefined ? "" : initVal;  //2)삼항연산자
				lprodGuSel.value = guinitVal ?? "";  //3)null을 처리하는 전용 삼항연산자
				}
			})
	axios.get(`${CPATH}/ajax/buyer`)
		.then(resp => { 
			console.log(resp.data);  

			const {buyerList } = resp.data;

			if (buyerList) {  
				const options = buyerList.map(({ buyerId, buyerName, lprodGu }) =>
					`<option class="${lprodGu}" value="${buyerId}">${buyerName}</option>`)
					.join("\n");
				buyerIdSel.innerHTML += options;
				buyerIdSel.value = idinitVal ?? ""; 
				}
			})
			
	lprodGuSel.addEventListener("change", (e)=>{
		const selGu = e.target.value;  // P101
		
		//find 함수나 마찬가지(제이쿼리에서)  //하나의 option 태그(op) 가져옴
		//attribute[]로, class 속성이 있는 애들만.  //<option>태그인데 options로 해놔서 안 찾아졌던 거!
		buyerIdSel.querySelectorAll("option[class]").forEach(op=>{ 
			//클래스는 기본적으로 멀티 클래스라서, class 속성이 아니라 classList 속성이 존재.	
			if(op.classList.contains(selGu)){
				//선택된 게 포함돼있으면...즉, 같은 class면... 즉, 같은 분류코드면.
				//스타일에 접근해 눈에 보이는 상태로 만들기(디스플레이 건들기)
				op.style.display = "block";
			}else{
				//눈에 안 보이게 하기
				op.style.display = "none";
			}
			
		}) ;
	});
});