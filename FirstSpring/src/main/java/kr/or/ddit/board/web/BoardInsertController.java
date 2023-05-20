package kr.or.ddit.board.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardInsertController {

	@Inject
	private IBoardService boardService; 
	
	@RequestMapping(value = "/form.do", method = RequestMethod.GET)
	public String boardForm() {
		return "board/form";
	}
	
	@RequestMapping(value = "/insert.do",method = RequestMethod.POST)
	public String boardInsert(BoardVO boardVO,Model model) {
		String goPage = ""; //페이지를 담을 공간
		Map<String, String> errors = new HashMap<String, String>();
		
		// org.apache.commons 라이브러리를 이용하여 boardVOㅇ나에 들어있는 
		//제목, 내용을 공백/null체킹을 통해 입력값 필터를 처리할 수 있다.
		if(StringUtils.isBlank(boardVO.getBoTitle())) {//commons-lang3라이브러리 함수로 공백과 null모두를 체크해줌
			errors.put("boTitle", "제목을 입력해주세요!");
		}
		if(StringUtils.isBlank(boardVO.getBoContent())) {
			errors.put("boContent","내용을 입력해주세요!");
		}
		if(errors.size()>0) {//에러 발생
			//model은 데이터 전달자
			//내가 넘기고자 하는 데이터를 대신 넘겨주는 역할을 담당한다.
			model.addAttribute("errors",errors);
			model.addAttribute("board",boardVO);
			goPage="board/form";
		}else {//에러가 발생하지 않고 정상적인 데이터가 넘어온 경우
			boardVO.setBoWriter("a001");
			ServiceResult result =boardService.insertBoard(boardVO);
			if(result.equals(ServiceResult.OK)) {
				goPage="redirect:/board/detail.do?boNo="+boardVO.getBoNo();
			}else {
				errors.put("msg", "서버 에러! 다시 시도해주세요!");
				model.addAttribute("errors",errors);
				goPage="board/form";
			}
			
		}
		
		return goPage;
	}
}
