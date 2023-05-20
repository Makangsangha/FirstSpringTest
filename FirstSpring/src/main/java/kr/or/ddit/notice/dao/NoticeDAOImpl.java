package kr.or.ddit.notice.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Repository
public class NoticeDAOImpl implements INoticeDAO{

	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertNotice(NoticeVO notiveVO) {
		return sqlSession.insert("Notice.insertNotice", notiveVO);
	}

	@Override
	public NoticeVO detailNotice(int boNo) {
		return sqlSession.selectOne("Notice.detailNotice", boNo);
	}

	@Override
	public int deleteNotice(int boNo) {
		return sqlSession.delete("Notice.deleteNotice", boNo);
	}

	@Override
	public int updateNotice(NoticeVO noticeVO) {
		return sqlSession.update("Notice.updateNotice", noticeVO);
	}

	@Override
	public int selectBoardCount(PaginationInfoVO<NoticeVO> pagingVO) {
		return sqlSession.selectOne("Notice.selectBoardCount", pagingVO);
	}

	@Override
	public List<NoticeVO> selectBoardList(PaginationInfoVO<NoticeVO> pagingVO) {
		return sqlSession.selectList("Notice.selectBoardList", pagingVO);
	}

	@Override
	public void upHit(int boNo) {
		sqlSession.update("Notice.upHit", boNo);
	}

	@Override
	public List<Integer> allBono() {
		return sqlSession.selectList("Notice.allBono");
	}

}
