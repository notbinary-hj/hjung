package kr.or.ddit.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리(CRUD)를 위한 Persistence Layer
 */
@Mapper
public interface ProdMapper {
	public int insertProd(ProdVO prod);
	public List<ProdVO> selectProdList();
	public List<Map<String, Object>> selectProdListForMap();
	public ProdVO selectProd(String prodId);
	public int updateProd(ProdVO prod);
	public default int deleteProd(String prodId) {
		throw  new IllegalStateException("상품은 삭제 불가");
	};
}
