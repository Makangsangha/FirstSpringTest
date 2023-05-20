package kr.or.ddit.free.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Repository
public class FreeDAOImpl implements IFreeDAO{

	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertFree(FreeVO freeVO) {
		return sqlSession.insert("Free.insertFree", freeVO);
	}

	@Override
	public FreeVO detailFree(int boNo) {
		return sqlSession.selectOne("Free.detailFree", boNo);
	}

	@Override
	public int deleteFree(int boNo) {
		return sqlSession.delete("Free.deleteFree", boNo);
	}

	@Override
	public int updateFree(FreeVO freeVO) {
		return sqlSession.update("Free.updateFree", freeVO);
	}

	@Override
	public int selectBoardCount(PaginationInfoVO<FreeVO> pagingVO) {
		return sqlSession.selectOne("Free.selectBoardCount", pagingVO);
	}

	@Override
	public List<FreeVO> selectBoardList(PaginationInfoVO<FreeVO> pagingVO) {
		return sqlSession.selectList("Free.selectBoardList", pagingVO);
	}

	@Override
	public void upHit(int boNo) {
		sqlSession.update("Free.upHit", boNo);
	}

	@Override
	public List<Integer> allBono() {
		return sqlSession.selectList("Free.allBono");
	}

}
