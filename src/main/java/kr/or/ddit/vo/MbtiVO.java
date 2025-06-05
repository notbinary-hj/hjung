package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Domain Layer : 테이블의 스키마(엔터티 구조)를 반영함.
 * JavaBean 규약(캡슐화, getter/setter, 상태 확인, 상태 비교)에 따라
 * 개발되어야 함. -> lombok 활용중.(outline뷰 열어놓고 동작하는지 확인하며 개발)
 */
@Data  //게터, 세터 다 있음->값 수정이 사실 불가하진 않음, 선택시 빌더가 도와줌
//@Getter  //게터만 있음
@EqualsAndHashCode(of="myType")
@NoArgsConstructor  //기본 생성자 만들어줌. 다른 생성자 명시적으로 만들어주면 기본생성자 사라지므로 강제적으로 기본생성자 만들어줌
//@AllArgsConstructor  //property를 한꺼번에 받는 생성자 하나 추가
public class MbtiVO {
	//생성자 강제구현(총 3개)
	public MbtiVO(String mtType) {
		super();
		this.mtType = mtType;
	}
	private Integer mtSort;
	private String mtType;
	private String mtTitle;
	private String mtContent;
	
	private MbtiVO(Integer mtSort, String mtType, String mtTitle, String mtContent) {
		super();
		this.mtSort = mtSort;
		this.mtType = mtType;
		this.mtTitle = mtTitle;
		this.mtContent = mtContent;
	}

	//빌더 메소드로 외부 접근함(public)
	public static MbtiVOBuilder builder() {
		return new MbtiVOBuilder();
	}
	
	//이너클래스
	public static class MbtiVOBuilder{
		private Integer mtSort;
		private String mtType;
		private String mtTitle;
		private String mtContent;
		
		public MbtiVOBuilder mtSort(Integer mtSort){
			this.mtSort = mtSort;  //setter로 이름 바꾸지 않고 프로퍼티명을 그대로 사용가능
			return this;
		}
		public MbtiVOBuilder mtType(String mtType){
			this.mtType = mtType;
			return this;
		}
		public MbtiVOBuilder mtTitle(String mtTitle){
			this.mtTitle = mtTitle;
			return this;
		}
		public MbtiVOBuilder mtContent(String mtContent){
			this.mtContent = mtContent;
			return this;
		}
		
		public MbtiVO build() {
			return new MbtiVO(mtSort, mtType, mtTitle, mtContent);
		}
	}
}
