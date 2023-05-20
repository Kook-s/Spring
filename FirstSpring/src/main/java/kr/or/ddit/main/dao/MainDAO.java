package kr.or.ddit.main.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.NoticeVO;

@Repository
public class MainDAO implements IMainDAO {
	
	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<BoardVO> selectBoardList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("Main.selectBoardList");
	}
	
	@Override
	public List<NoticeVO> selectNoticeList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("Main.selectNoticeList");
	}
	
	@Override
	public List<FreeVO> selectFreeList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("Main.selectFreeList");
	}
}
