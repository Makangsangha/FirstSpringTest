package kr.or.ddit.main.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.NoticeVO;

public interface IMainDAO {

	List<BoardVO> selectBoardList();

	List<NoticeVO> selectNoticeList();

	int selectNoticeCount();

	int selectBoardCount();

	List<FreeVO> selectFreeList();

	int selectFreeCount();

}
