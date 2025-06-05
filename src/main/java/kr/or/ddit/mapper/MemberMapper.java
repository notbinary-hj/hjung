package kr.or.ddit.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.MemberVO;

/**
 * 회원 관리를 위한 Persistence Layer
 * 
 * MyBatis 의 Mapper : 데이터베이스를 사용하기 위해 필요한 객체
 * 
 */
@Mapper
public interface MemberMapper {
	public MemberVO selectMember(String username);
}
