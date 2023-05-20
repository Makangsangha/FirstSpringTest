package kr.or.ddit.notice.web;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Inject
	private INoticeService service;
	
	@RequestMapping(value = "/form.do", method = RequestMethod.GET)
	public String boardForm(Model model) {
		return "notice/form";
	}
	
	@RequestMapping(value="/list.do")
	public String boardList(
			@RequestParam(name="page",required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		
		PaginationInfoVO<NoticeVO> pagingVO = new PaginationInfoVO<NoticeVO>();
		
		if(StringUtils.isNoneBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord.trim());
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		// 목록 총 게시글 수 가져오기
		int totalRecord = service.selectBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<NoticeVO> detaList = service.selectBoardList(pagingVO);
		pagingVO.setDataList(detaList);
		model.addAttribute("pagingVO", pagingVO);
		return "notice/list";
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String insertNotice(NoticeVO noticeVO, Model model) {
		
		noticeVO.setBoWriter("asd1");
		int result = service.insertNotice(noticeVO);
		
		String url = "";
		if(result > 0) {
			url = "redirect:/notice/detail.do?boNo=" + noticeVO.getBoNo();
		}else {
			model.addAttribute("errorVO", noticeVO);
			url = "notice/form";
		}	
		return url;
	}
	
	@RequestMapping(value="/detail.do", method = RequestMethod.GET)
	public String detailNotice(int boNo, Model model) {
		
		List<Integer> boNos = service.allBono();
		boolean errorCheck = false;
		
		if(!boNos.contains(boNo)) {
			errorCheck = true;
		}		
		
		NoticeVO vo = service.detailNotice(boNo);
		String url = "";
		if(vo!=null && !errorCheck) {
			model.addAttribute("notice", vo);
			url = "notice/view";
		}else if(errorCheck) {
			model.addAttribute("error", "error");
			url = "error";
		}else {
			url = "redirect:/notice/form.do";
		}
				
		return url;	
	}
	
	@RequestMapping(value="/delete.do", method = RequestMethod.POST)
	public String deleteNotice(int boNo, Model model) {
		int result = service.deleteNotice(boNo);
		List<Integer> boNos = service.allBono();
		boolean errorCheck = false;
		
		if(!boNos.contains(boNo)) {
			errorCheck = true;
		}
		
		String url = "";
		if(result > 0) {
			url = "redirect:/notice/list.do";
		}else if(errorCheck) {
			model.addAttribute("error", "error");
			url = "error";
		}else {
			url = "redirect:/notice/detil.do?boNo="+boNo;
		}
		return url;
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.GET)
	public String updateDetailNotice(NoticeVO noticeVO, Model model) {		
		NoticeVO vo = service.detailNotice(noticeVO.getBoNo());
		List<Integer> boNos = service.allBono();
		boolean errorCheck = false;
		
		if(!boNos.contains(noticeVO.getBoNo())) {
			errorCheck = true;
		}
		
		String url = "notice/form";
		if(errorCheck) {
				model.addAttribute("error", "error");
				url = "error";
		}
		
		model.addAttribute("notice", vo);
		model.addAttribute("status", "u");
		
		return url;
		
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.POST)
	public String updateNotice(NoticeVO noticeVO, Model model) {		
		int result = service.updateNotice(noticeVO);
		
		String url = "";
		if(result > 0) {
			url = "redirect:/notice/detail.do?boNo="+noticeVO.getBoNo();
		}else {
			url = "redirect:/notice/update.do?noticeVO="+noticeVO;
		}
		
		return url;
		
	}
	
}
