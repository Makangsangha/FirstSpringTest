package kr.or.ddit.free.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.free.dao.IFreeDAO;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class FreeServiceImpl implements IFreeService {

	@Inject
	private IFreeDAO dao;

	@Override
	public int insertFree(FreeVO freeVO) {
		return dao.insertFree(freeVO);
	}

	@Override
	public FreeVO detailFree(int boNo) {
		dao.upHit(boNo);
		return dao.detailFree(boNo);
	}

	@Override
	public int deleteFree(int boNo) {
		return dao.deleteFree(boNo);
	}

	@Override
	public int updateFree(FreeVO freeVO) {
		return dao.updateFree(freeVO);
	}

	@Override
	public int selectBoardCount(PaginationInfoVO<FreeVO> pagingVO) {
		return dao.selectBoardCount(pagingVO);
	}

	@Override
	public List<FreeVO> selectBoardList(PaginationInfoVO<FreeVO> pagingVO) {
		return dao.selectBoardList(pagingVO);
	}

	@Override
	public List<Integer> allBono() {
		return dao.allBono();
	}

	

}
