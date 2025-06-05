package kr.or.ddit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.MbtiVO;

/**
 * Persistence Layer : 테이블을 대상으로 CRUD 메소드(사용할 쿼리 반영)가 기본 요건.
 */
@Mapper
public interface MbtiMapper {
	public int insertMbti(MbtiVO mbti);
	public List<MbtiVO> selectMbtiList();
	public MbtiVO selectMbti(String mtType);
	public int updateMbti(MbtiVO mbti);
	public int deleteMbti(String mtType);
}
