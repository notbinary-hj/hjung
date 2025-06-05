package kr.or.ddit.case07.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CalculateVO {
	@Min(0)
	private int op1;
	@Min(0)
	private int op2;
	
	private int result;  //결과 담을 거
}
