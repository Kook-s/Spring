package kr.or.ddit.main.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.main.service.IMainService;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
public class MainController {
	
	@Inject
	private IMainService mainService;
	@Inject
	private IFreeService freeService;
	@Inject 
	private IBoardService boardService;
	@Inject
	private INoticeService noticeService;
	
	

	
	
	@RequestMapping(value = {"/","/main.do"}, method = RequestMethod.GET)
	public String main(Model model) {
		//일반게시판 게시글 총 5개의 정보를 가져와서 메인화면에 뿌릴거임
		List<BoardVO> boardList= mainService.selectBoardList();
		List<NoticeVO> noticeList= mainService.selectNoticeList();
		List<FreeVO> freeList= mainService.selectFreeList();
		
		PaginationInfoVO<BoardVO> pagingVO1 = new PaginationInfoVO<BoardVO>();
		int bc=boardService.selectBoardCount(pagingVO1);
		PaginationInfoVO<FreeVO> pagingVO2 = new PaginationInfoVO<FreeVO>();
		int fc=freeService.selectFreeCount(pagingVO2);
		PaginationInfoVO<NoticeVO> pagingVO3 = new PaginationInfoVO<NoticeVO>();
		int nc=noticeService.selectNoticeCount(pagingVO3);
		
		model.addAttribute("boardList",boardList);
		model.addAttribute("noticeList",noticeList);
		model.addAttribute("freeList",freeList);
		model.addAttribute("boardCnt",bc);
		model.addAttribute("noticeCnt",nc);
		model.addAttribute("freeCnt",fc);
		return "main";
	}
	
}
