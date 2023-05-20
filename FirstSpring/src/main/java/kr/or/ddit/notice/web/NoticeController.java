package kr.or.ddit.notice.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Inject
	private INoticeService noticeService;
	
	
	@RequestMapping(value = "list.do")
	public String noticeList(@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord, Model model) {
		
		PaginationInfoVO<NoticeVO> pagingVO = new PaginationInfoVO<NoticeVO>();
		
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType",searchType);
			model.addAttribute("searchWord",searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		
		//목록 총 게시글 수 가져오기
		int totalRecord = noticeService.selectNoticeCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<NoticeVO> dataList = noticeService.selectNoticeList(pagingVO);
		pagingVO.setDataList(dataList);
		model.addAttribute("pagingVO",pagingVO);
		
		return "notice/list";

	}
	
	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public String detail(int boNo, Model model) {
		
		NoticeVO noticeVO= noticeService.selectNotice(boNo);
		
		model.addAttribute("notice",noticeVO);
		return "notice/view";
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String delete(int boNo, Model model) {
		String page = "";
		ServiceResult result= noticeService.deleteNotice(boNo);
		
		if(ServiceResult.OK.equals(result)) {
			page = "redirect:/notice/list.do";
		}else {
			page ="redirect:/board/detail.do?boNo="+boNo;
		}
		return page;
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.GET)
	public String insertForm(Model model) {
		model.addAttribute("status","i");
		return "notice/form";
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String insert(NoticeVO noticeVO, Model model) {
		String goPage = ""; 
		Map<String, String> errors = new HashMap<String, String>();
		

		if(StringUtils.isBlank(noticeVO.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요!");
		}
		if(StringUtils.isBlank(noticeVO.getBoContent())) {
			errors.put("boContent","내용을 입력해주세요!");
		}
		if(errors.size()>0) {
			model.addAttribute("errors",errors);
			model.addAttribute("notice",noticeVO);
			goPage="notice/form";
		}else {
			noticeVO.setBoWriter("a001");
			ServiceResult result =noticeService.insertNotice(noticeVO);
			if(result.equals(ServiceResult.OK)) {
				goPage="redirect:/notice/detail.do?boNo="+noticeVO.getBoNo();
			}else {
				errors.put("msg", "서버 에러! 다시 시도해주세요!");
				model.addAttribute("errors",errors);
				goPage="notice/form";
			}
			
		}
		
		return goPage;
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String updateForm(int boNo, Model model) {
		
		NoticeVO noticeVO= noticeService.selectNotice(boNo);
		
		model.addAttribute("notice",noticeVO);
		model.addAttribute("status","u");
		return "notice/form";
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String update(NoticeVO noticeVO, Model model) {
		String page = "";
		ServiceResult result = noticeService.updateNotice(noticeVO);
		if(ServiceResult.OK.equals(result)) {
			page ="redirect:/notice/detail.do?boNo="+noticeVO.getBoNo();
		}else {
			page ="redirect:/notice/update.do?boNo="+noticeVO.getBoNo();
		}
		return page;
	}
}
