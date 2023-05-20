package kr.or.ddit.main.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.main.dao.IMainDAO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.NoticeVO;

@Service
public class MainServiceImpl implements IMainService {
	
	@Inject
	private IMainDAO mainDao;
	
	@Override
	public List<BoardVO> selectBoardList() {
		// TODO Auto-generated method stub
		return mainDao.selectBoardList();
	}

	@Override
	public List<FreeVO> selectFreeList() {
		// TODO Auto-generated method stub
		return mainDao.selectFreeList();
	}

	@Override
	public List<NoticeVO> selectNoticeList() {
		// TODO Auto-generated method stub
		return mainDao.selectNoticeList();
	}

}
