package kr.or.ddit.case07.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.InsertGroup;
import lombok.Data;

@Data  //데이터 정책을 사용하지 않아서 pk 안 넣어도 된다고?
public class DummyVO {
	// 문자로 전달된 name(필수) - 비어있지 않음, 
	// 숫자로 전달된 age(옵셔널) - 최소 0보다 큼
	// 문자들로 전달된 hobbies(옵셔널) - 검증 어노테이션 불필요
	@NotBlank(groups = InsertGroup.class)  //수정시는 검증 X
	private String name;
	@Min(1)
	private int age;
	private String[] hobbies;
}
