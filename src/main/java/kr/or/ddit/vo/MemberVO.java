package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "memId")  // 데이터베이스 설계 참고
//@Getter
//@Setter
//@EqualsAndHashCode
//@ToString
public class MemberVO implements Serializable{  //직렬화해야 완전해짐
	//직렬화는 한쪽 네트워크에서 다른쪽 네트워크로 전송한단 뜻
	private String memId;
	private String memPassword;
	private String memName;
	@ToString.Exclude //제외 부분 설정  //보낼 시에 제외
	private transient String memRegno1;  //transient:투명화. 없듯 동작. 확인 시에도 제외
	@ToString.Exclude 
	private transient String memRegno2; 
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memHobby;
	private String memMemorial;
	private String memMemorialday;
	private Integer memMileage;
	private String memDelete;
}
