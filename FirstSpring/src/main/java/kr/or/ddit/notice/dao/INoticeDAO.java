package kr.or.ddit.notice.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface INoticeDAO {

	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO);

	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO);

	public NoticeVO selectNotice(int boNo);

	public int deleteNotice(int boNo);

	public int updateNotice(NoticeVO noticeVO);

	public int insertNotice(NoticeVO noticeVO);

	public void incrementHit(int boNo);

}
