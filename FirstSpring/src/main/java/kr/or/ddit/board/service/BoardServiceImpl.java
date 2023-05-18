package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class BoardServiceImpl implements IBoardService {

	@Inject
	private IBoardDAO boardDao;
	
	@Override
	public ServiceResult insertBoard(BoardVO boardVO) {
		// 성공하면 1, 실패하면 0
		// boardVO안에 boNo가 셋팅되어 들어오 예정
		// selectKey 설정에 의해서 최신 글 번호가 담겨서 들어온다.
		ServiceResult result = null;
		int status = boardDao.insertBoard(boardVO);
		if(status > 0) {	// 등록 성공
			result = ServiceResult.OK;
		}else {				// 등록 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public BoardVO selectBoard(int boNo) {
		boardDao.incrementHit(boNo);	// 조회수 처리
		return boardDao.selectBoard(boNo);
	}

	@Override
	public ServiceResult updateBoard(BoardVO boardVO) {
		ServiceResult result = null;
		int status = boardDao.updateBoard(boardVO);
		if(status > 0) {	// 수정 성공
			result = ServiceResult.OK;	
		}else {				// 수정 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}

	// 원래 string을 받는데 스프링이 자동으로 형변환 하여 해준다.
	@Override
	public ServiceResult deleteBoard(int boNo) {
		ServiceResult result = null;
		int status = boardDao.deleteBoard(boNo);
		if(status > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<BoardVO> selectBoardList() {
		return boardDao.selectBoardList();
	}

	@Override
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO) {
		return boardDao.selectBoardCount(pagingVO);
	}

	@Override
	public List<BoardVO> selectBoardList2(PaginationInfoVO<BoardVO> pagingVO) {
		return boardDao.selectBoardList2(pagingVO);
	}

}
