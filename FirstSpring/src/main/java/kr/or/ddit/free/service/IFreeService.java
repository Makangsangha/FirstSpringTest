package kr.or.ddit.free.service;

import java.util.List;

import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IFreeService {

	public int insertFree(FreeVO freeVO);

	public FreeVO detailFree(int boNo);

	public int deleteFree(int boNo);

	public int updateFree(FreeVO freeVO);

	public int selectBoardCount(PaginationInfoVO<FreeVO> pagingVO);

	public List<FreeVO> selectBoardList(PaginationInfoVO<FreeVO> pagingVO);

	public List<Integer> allBono();

}
