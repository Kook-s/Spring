package kr.or.ddit.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.notice.dao.INoticeDAO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class NoticeServiceImpl implements INoticeService{
	
	@Inject
	private INoticeDAO noticeDao;

	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO) {
		// TODO Auto-generated method stub
		return noticeDao.selectNoticeCount(pagingVO);
	}

	@Override
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		// TODO Auto-generated method stub
		return noticeDao.selectNoticeList(pagingVO);
	}

	@Override
	public NoticeVO selectNotice(int boNo) {
		// TODO Auto-generated method stub
		noticeDao.incrementHit(boNo);
		return noticeDao.selectNotice(boNo);
	}

	@Override
	public ServiceResult deleteNotice(int boNo) {
		// TODO Auto-generated method stub
		ServiceResult result=null;
		int res = noticeDao.deleteNotice(boNo);
		if(res>0) {
			result=ServiceResult.OK;
		}else {
			result=ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult updateNotice(NoticeVO noticeVO) {
		ServiceResult result=null;
		int res = noticeDao.updateNotice(noticeVO);
		if(res>0) {
			result=ServiceResult.OK;
		}else {
			result=ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult insertNotice(NoticeVO noticeVO) {
		ServiceResult result=null;
		int res = noticeDao.insertNotice(noticeVO);
		if(res>0) {
			result=ServiceResult.OK;
		}else {
			result=ServiceResult.FAILED;
		}
		return result;
	}

}
