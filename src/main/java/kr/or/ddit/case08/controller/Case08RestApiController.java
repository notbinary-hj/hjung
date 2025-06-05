package kr.or.ddit.case08.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;
import kr.or.ddit.case08.vo.NativeJavaVO;


/**
 *  RestFul URI
 *  명사(URI) + 동사(method)
 *  /case08/dummy + GET
 *  /case08/dummy/{key} + GET  --파라미터로 넘기던걸 URI에 넘겨서, 경로처럼 표현. 경로변수(path) 리액트 라우터의 useParams에서도 params가 파라미터가 아니라 경로변수!
 *  /case08/dummy + POST
 *  /case08/dummy + DELETE  --전체 삭제. 불완전구조
 *  /case08/dummy/{key} + DELETE  --일부 삭제. 경로변수 key
 */
//@Controller
//@ResponseBody  //crud 처리 결과가 json 엔터티로 자동 나감(핸들러 어뎁터가). 뷰 리졸버로 가지X
@RestController  //@Controller + @ResponseBody 합친 메타 어노테이션.
@RequestMapping("/case08/dummy")
public class Case08RestApiController {
	private Map<String, NativeJavaVO> dummyDB;
	{
		dummyDB = new HashMap<>();
		NativeJavaVO njv1 = new NativeJavaVO();
		dummyDB.put("pk1", njv1);
		njv1.setCode("pk1");
		njv1.setProp1("문자열");
		njv1.setProp2(32);
		njv1.setProp3(new String[] {"v1", "v2"});
		NativeJavaVO njv2 = new NativeJavaVO();
		dummyDB.put("pk2", njv2);
		njv2.setCode("pk2");
		njv2.setProp1("텍스트");
		njv2.setProp2(385);
		njv2.setProp3(new String[] {"v3", "v4"});
	}
	
	@GetMapping  //맵 혹은 vo를 이용해 데이터를 데이터로
	public List<NativeJavaVO> restGet() {
		//NJV가 2개 반환이라 리스트로.
		return new ArrayList<>(dummyDB.values());
	}
	
	@GetMapping("{key}")  //경로변수의 이름 key. 리액트에서는 :key 로 표현.
//	public NativeJavaVO restGet(String key) {  //아무것도 안써주면 @RequestParam을 붙임
	public NativeJavaVO restGet(@PathVariable String key) {
		//rest api 에서는 경로변수로 표현
//		return dummyDB.get(key);
		//없어도 반환시키려고 함...->있나 없나 보고 예외 발생시켜서 그걸 404로.
		NativeJavaVO target = dummyDB.get(key);
		if(target==null) {
			// 커스텀 익셉션
			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
		}
		return target;
	}
	
	@PostMapping  //@RequestBody 안의 데이터를 통으로 읽는데, json이어야 읽음->그걸 NJV가 네이티브 자바 객체로 바꿔 읽음
	public NativeJavaVO create(@RequestBody NativeJavaVO njv) {
		String newPk = "pk" + (dummyDB.size()+10);  //pk라는 문자 뒤에 다음 순서의 숫자가 오게 하려면, 더하기 연산은 미리 괄호로 묶어야. 안 그러면 pk 뒤에 콘캣해서 엄청 큰 숫자가 와버림(예: "pk110")
		njv.setCode(newPk);
		dummyDB.put(newPk, njv);
		return njv;  //비동기는 기존 값까지 보낼 필욘 없고 새로 추가된 객체만 보내도 안다.
		//PRG라, 동기일 땐 기존값이 이렇게 바뀌었다~도 get 조회로 보내줘야.(?)
	}
	
	//dummyDB에서 하나(pk1이나 pk2)를 수정. pk1이나 pk2 안에 njv의 vo값들이 세팅돼있음. 그래도 key 자체는 스트링.
	@PutMapping("{key}")  //한건 수정이니 경로변수(pk1이나 pk2가 uri에 들어갈 것)
	public Map<String, Object> modify(@PathVariable String key, @RequestBody NativeJavaVO njv) {  //경로변수보다도, 수정할 데이터 대상(@RequestBody)이 중요.
		dummyDB.put(key, njv);
		//수정 후, delete 처럼 수정 성공여부와 수정된 대상 맵으로 보내기
		return Map.of("success", true, "target", njv);
	}
	
	@DeleteMapping("{key}")
	public Map<String, Object> restDelete(@PathVariable String key) {
		NativeJavaVO target = dummyDB.remove(key);  //지워진 값을 target으로.
		//?? redirection으로 다건조회 list로 보냄->PRG 패턴이 아니니 Delete는 안됨!
		//1. 정상적으로 지웠음을 메시지로 알려줌. 맵에 담음
		return Map.of("success", true, "target", target);
	}
}
