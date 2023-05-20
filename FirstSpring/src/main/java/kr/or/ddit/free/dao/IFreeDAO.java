package kr.or.ddit.free.dao;

import java.util.List;

import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IFreeDAO {

	public int insertFree(FreeVO freeVO);

	public FreeVO detailFree(int boNo);

	public int deleteFree(int boNo);

	public int updateFree(FreeVO noticeVO);

	public int selectBoardCount(PaginationInfoVO<FreeVO> pagingVO);

	public List<FreeVO> selectBoardList(PaginationInfoVO<FreeVO> pagingVO);

	public void upHit(int boNo);

	public List<Integer> allBono();
	
}
