package kr.or.ddit.case08.vo;

import lombok.Data;

@Data  
public class NativeJavaVO {  //원래의 데이터(crud 대상)
	private String code;
	
	private String prop1;
	private int prop2;
	private String[] prop3;
}
