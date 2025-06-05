package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.UpdateGroup;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 상품관리를 위한 Domain Layer (JavaBean)
 * 객체 생성 패턴인 Builder 패턴 구현 단계
 * 1. 모든 프로퍼티를 한번에 받을 수 있는 생성자 정의 ex) @AllArgsConstructor
 * 2. 해당 생성자는 클래스 내부에서만 사용 가능한 private 선언. ex) (access = AccessLevel.PRIVATE)
 * 3. 외부에서 객체 생성에 사용할 수 있도록 static 내부 클래스로 Builder 구현.
 * 	  ex) ProdVOBuilder
 * 4. Builder 내부에서 build() 메소드로 객체를 생성함.
 * 5. build 대상이 되는 객체가 가진 프로퍼티를 결정할 수 있는 setter 역할 메소드 구현.
 * (진짜 setter는 아니라서 void가 아니라 builder를 리턴)
 * 	  -> 메소드 체이닝 구조를 위해 해당 메소드 내부에서 builder가 다시 반환됨.
 * 
 * Mybatis를 이용해서 여러 개의 테이블을 조인하는 방법
 * 1. 사용할 테이블의 관계를 파악(메인 테이블을 중심으로..) 누가 1인지가 중요.
 * 	  1 : 1  ex) PROD(1) : BUYER(1)
 *	  1 : N  ex) BUYER(1) : PROD(N)
 * 2. 조인 쿼리 작성
 * 3. 각 테이블을 대상으로 한 VO 정의
 * 	  ProdVO, BuyerVO
 * 4. 테이블의 구조를 VO에 반영.
 * 	  PROD(1) : BUYER(1)
 * 	  ProdVO has A BuyerVO  한 개를 가져서 has a 관계
 *    BUYER(1) : PROD(N)
 *    BuyerVO has Many ProdVO  여러 개를 가져서 has many 관계
 * 5. resultType 대신 resultMap 수동 바인드 설정.
 * 	  1 : 1 (has A) - association 으로 바인드
 * 	  1 : N (has Many) - collection 으로 바인드
 */
@Data
@EqualsAndHashCode(of="prodId")
@NoArgsConstructor  //VO에서의 빌더 패턴엔 기본형 생성자 필수. 여러 개인 결과값을 하나에 담아와야 해서.
@AllArgsConstructor(access = AccessLevel.PRIVATE)  //생성자를 private으로 묶음->외부 생성불가
@Builder
public class ProdVO implements Serializable{
	@NotBlank(groups = {UpdateGroup.class} )
	private String prodId;
	@NotBlank
	private String prodName;
	@NotBlank
	private String lprodGu;
	@NotBlank
	private String buyerId;
	private Integer prodCost;
	private Integer prodPrice;
	private Integer prodSale;
	private String prodOutline;
	@ToString.Exclude
	private String prodDetail;
	private String prodImg;
	private Integer prodTotalstock;
	private LocalDate prodInsdate;
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
	
	private LprodVO lprod;  // has A 관계 (prod가 lprod를 가짐)
	private BuyerVO buyer;  // has A 관계 (prod가 buyer를 가짐, 1:1관계)
	
//	private String lprodName;
//	private String lprodId;
//	private String buyerName;
//	private String buyerAdd1;
	
	
}
