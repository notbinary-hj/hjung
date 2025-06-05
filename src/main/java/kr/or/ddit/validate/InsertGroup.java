package kr.or.ddit.validate;

import jakarta.validation.groups.Default;

/**
 * Marker 인터페이스
 *  : 어떤 조건을 표현하기 위한 마킹의 의미로 사용됨.
 *  예) Serializable. 직렬화 가능/불가능 조건만 알려주는 용. 
 */
public interface InsertGroup extends Default{  //default그룹과 insert그룹 다 검증하게 디폴트를 상속받음.

}
