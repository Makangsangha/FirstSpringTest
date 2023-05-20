package kr.or.ddit.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.notice.dao.INoticeDAO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class NoticeServiceImpl implements INoticeService {

	@Inject
	private INoticeDAO dao;

	@Override
	public int insertNotice(NoticeVO notiveVO) {
		return dao.insertNotice(notiveVO);
	}

	@Override
	public NoticeVO detailNotice(int boNo) {
		dao.upHit(boNo);
		return dao.detailNotice(boNo);
	}

	@Override
	public int deleteNotice(int boNo) {
		return dao.deleteNotice(boNo);
	}

	@Override
	public int updateNotice(NoticeVO noticeVO) {
		return dao.updateNotice(noticeVO);
	}

	@Override
	public int selectBoardCount(PaginationInfoVO<NoticeVO> pagingVO) {
		return dao.selectBoardCount(pagingVO);
	}

	@Override
	public List<NoticeVO> selectBoardList(PaginationInfoVO<NoticeVO> pagingVO) {
		return dao.selectBoardList(pagingVO);
	}

	@Override
	public List<Integer> allBono() {
		return dao.allBono();
	}

	

}
