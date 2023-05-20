package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IBoardDAO {

	int insertBoard(BoardVO boardVO);

	void incrementHit(int boNo);

	BoardVO selectBoard(int boNo);

	int updateBoard(BoardVO boardVO);

	int deleteBoard(int boNo);

	List<BoardVO> selectBoardList();

	int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO);

	List<BoardVO> selectBoardList2(PaginationInfoVO<BoardVO> pagingVO);

}
