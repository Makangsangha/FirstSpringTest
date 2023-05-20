package kr.or.ddit.free.web;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/free")
public class FreeController {

	@Inject
	private IFreeService service;

	@RequestMapping(value = "/form.do", method = RequestMethod.GET)
	public String boardForm(Model model) {
		return "free/form";
	}

	@RequestMapping(value = "/list.do")
	public String boardList(@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord, Model model) {

		PaginationInfoVO<FreeVO> pagingVO = new PaginationInfoVO<FreeVO>();

		if (StringUtils.isNoneBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord.trim());
		}

		pagingVO.setCurrentPage(currentPage);
		// 목록 총 게시글 수 가져오기
		int totalRecord = service.selectBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<FreeVO> detaList = service.selectBoardList(pagingVO);
		pagingVO.setDataList(detaList);
		model.addAttribute("pagingVO", pagingVO);
		return "free/list";
	}

	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String insertFree(FreeVO freeVO, Model model) {

		freeVO.setBoWriter("asd1");
		int result = service.insertFree(freeVO);

		String url = "";
		if (result > 0) {
			url = "redirect:/free/detail.do?boNo=" + freeVO.getBoNo();
		} else {
			model.addAttribute("errorVO", freeVO);
			url = "free/form";
		}
		return url;
	}

	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public String detailFree(int boNo, Model model) {
		List<Integer> boNos = service.allBono();
		boolean errorCheck = false;
		
		if(!boNos.contains(boNo)) {
			errorCheck = true;
		}	
		
		FreeVO vo = service.detailFree(boNo);
		String url = "";
		if (vo != null) {
			model.addAttribute("free", vo);
			url = "free/view";
		}else if(errorCheck) {
			model.addAttribute("error", "error");
			url = "error";
		} else {
			url = "redirect:/free/form.do";
		}

		return url;

	}

	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String deleteNotice(int boNo, Model model) {
		int result = service.deleteFree(boNo);

		String url = "";
		if (result > 0) {
			url = "redirect:/free/list.do";
		} else {
			url = "redirect:/free/detil.do?boNo=" + boNo;
		}
		return url;
	}

	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String updateDetailFree(FreeVO freeVO, Model model) {
		FreeVO vo = service.detailFree(freeVO.getBoNo());
		List<Integer> boNos = service.allBono();
		boolean errorCheck = false;
		
		if(!boNos.contains(freeVO.getBoNo())) {
			errorCheck = true;
		}
		
		String url = "free/form";
		if(errorCheck) {
				model.addAttribute("error", "error");
				url = "error";
		}
		
		model.addAttribute("free", vo);
		model.addAttribute("status", "u");

		return url;

	}

	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String updateNotice(FreeVO freeVO, Model model) {
		int result = service.updateFree(freeVO);

		String url = "";
		if (result > 0) {
			url = "redirect:/free/detail.do?boNo=" + freeVO.getBoNo();
		} else {
			url = "redirect:/free/update.do?noticeVO=" + freeVO;
		}

		return url;

	}

}
