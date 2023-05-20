package kr.or.ddit.notice.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface INoticeDAO {

	public int insertNotice(NoticeVO notiveVO);

	public NoticeVO detailNotice(int boNo);

	public int deleteNotice(int boNo);

	public int updateNotice(NoticeVO noticeVO);

	public int selectBoardCount(PaginationInfoVO<NoticeVO> pagingVO);

	public List<NoticeVO> selectBoardList(PaginationInfoVO<NoticeVO> pagingVO);

	public void upHit(int boNo);

	public List<Integer> allBono();

}
