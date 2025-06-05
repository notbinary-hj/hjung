package kr.or.ddit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.LprodVO;

@Mapper  //이게 있으면 매퍼 스캐너가 이걸 스캔->스프링?으로 만듦->싱글톤으로 등록
public interface LprodMapper {
	public List<LprodVO> selectLprodList();
}
