package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 하나의 제조사 정보
 * 하나의 제조사 분류 정보
 * N개의 거래품목 정보를 담기 위한 Domain 
 * 
 * 제조사 관리용 Domain layer
 */
@Data
@EqualsAndHashCode(of="buyerId")
public class BuyerVO implements Serializable{
	@NotBlank(groups = {UpdateGroup.class, DeleteGroup.class} )  //검증 룰을 그룹 단위로 분리.
	private String buyerId;  //수정시, 삭제시 검증. 동시에 검증해야 되는 애는 기본그룹으로 만들어놓음
	@NotBlank
	private String buyerName;  //등록, 수정시 검증이라 기본그룹으로.
	@NotBlank
	private String lprodGu;  //그룹 힌트 적용 안한 기본그룹으로서 적용됨
	@NotBlank
	private String buyerBank;
	@NotBlank
	private String buyerBankno;
	@NotBlank
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	@NotBlank
	private String buyerComtel;
	private String buyerFax;
	@NotBlank
	@Email
	private String buyerMail;
	@NotBlank
	private String buyerCharger;
	private String buyerTelext;
	
//	@NotNull  //객체니까. blank는 문자열 대상.
	private transient LprodVO lprod;  // has A 관계 (하나의 제조사 정보만 담으면 됨)
	//직렬화에서 제외시키고 싶은 애는 transient 적음
	
//	@NotNull
//	@NotEmpty  //공백이 포함됐더라도(?), 거래 품목 하나 이상 있길 바람
	private transient List<ProdVO> prodList;  // has Many 관계 (N개의 거래품목 정보)
	//일대일, 일대다에서 누가 1인지를 보는 게 중요!
}
