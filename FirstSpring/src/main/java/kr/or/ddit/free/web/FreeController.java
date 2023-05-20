package kr.or.ddit.free.web;

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
import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/free")
public class FreeController {
	
	@Inject
	private IFreeService freeService;

	@RequestMapping("/list.do")
	public String freeList(@RequestParam(name = "page", defaultValue = "1", required = false) int currentPage,
			@RequestParam(defaultValue = "title", required = false) String searchType,
			@RequestParam(required = false) String searchWord, Model model) {
		
		PaginationInfoVO<FreeVO> pagingVO = new PaginationInfoVO<FreeVO>();
		
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType",searchType);
			model.addAttribute("searchWord",searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		int totalrecord = freeService.selectFreeCount(pagingVO);
		pagingVO.setTotalRecord(totalrecord);
		List<FreeVO> dataList = freeService.selectFreeList(pagingVO);
		pagingVO.setDataList(dataList);
		
		model.addAttribute("pagingVO",pagingVO);
		return "/free/list";
	}
	
	
	
	
	
	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public String detail(int boNo, Model model) {
		
		FreeVO freeVO= freeService.selectFree(boNo);
		
		model.addAttribute("free",freeVO);
		return "free/view";
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public String delete(int boNo, Model model) {
		String page = "";
		ServiceResult result= freeService.deleteFree(boNo);
		
		if(ServiceResult.OK.equals(result)) {
			page = "redirect:/free/list.do";
		}else {
			page ="redirect:/board/detail.do?boNo="+boNo;
		}
		return page;
	}
	@RequestMapping(value = "/insert.do", method = RequestMethod.GET)
	public String insertForm(Model model) {
		model.addAttribute("status","i");
		return "free/form";
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String insert(FreeVO freeVO, Model model) {
		String goPage = ""; 
		Map<String, String> errors = new HashMap<String, String>();
		

		if(StringUtils.isBlank(freeVO.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요!");
		}
		if(StringUtils.isBlank(freeVO.getBoContent())) {
			errors.put("boContent","내용을 입력해주세요!");
		}
		if(errors.size()>0) {
			model.addAttribute("errors",errors);
			model.addAttribute("free",freeVO);
			goPage="free/form";
		}else {
			freeVO.setBoWriter("a001");
			ServiceResult result =freeService.insertFree(freeVO);
			if(result.equals(ServiceResult.OK)) {
				goPage="redirect:/free/detail.do?boNo="+freeVO.getBoNo();
			}else {
				errors.put("msg", "서버 에러! 다시 시도해주세요!");
				model.addAttribute("errors",errors);
				goPage="free/form";
			}
			
		}
		
		return goPage;
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String updateForm(int boNo, Model model) {
		
		FreeVO freeVO= freeService.selectFree(boNo);
		
		model.addAttribute("free",freeVO);
		model.addAttribute("status","u");
		return "free/form";
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String update(FreeVO freeVO, Model model) {
		String page = "";
		ServiceResult result = freeService.updateFree(freeVO);
		if(ServiceResult.OK.equals(result)) {
			page ="redirect:/free/detail.do?boNo="+freeVO.getBoNo();
		}else {
			page ="redirect:/free/update.do?boNo="+freeVO.getBoNo();
		}
		return page;
	}
	
}
