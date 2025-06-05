package kr.or.ddit.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="lprodGu")
public class LprodVO {
	private Integer lprodId;
	private String lprodGu;
	private String lprodName;
}
