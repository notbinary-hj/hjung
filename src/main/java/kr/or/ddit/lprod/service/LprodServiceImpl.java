package kr.or.ddit.lprod.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.LprodMapper;
import kr.or.ddit.vo.LprodVO;
import lombok.RequiredArgsConstructor;

//1. 빈으로 등록하고 생성자를 주입
@Service
@RequiredArgsConstructor  //필수 주입은 가능하면 생성자 사용
public class LprodServiceImpl implements LprodService {
	private final LprodMapper mapper;

	@Override
	public List<LprodVO> readLprodList() {
		return mapper.selectLprodList();
	}

}
