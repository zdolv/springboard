package com.spring.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.HomeController;
import com.spring.board.dao.BoardDao;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComcodeVo;
import com.spring.board.vo.PageVo;
import com.spring.common.CommonUtil;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	public String boardList(Locale locale, Model model,PageVo pageVo,
							HttpServletRequest request) throws Exception{
		
		String[] codeId = request.getParameterValues("codeId");
		
		if (codeId != null) {
			for (int i=0; i<codeId.length; i++) {
				System.out.println("String[] codeId : " + codeId[i].toString());
			}
		}
		
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		List<BoardVo> comCodeList = new ArrayList<BoardVo>();
		
		int page = 1;
		int totalCnt = 0;
		
		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);;
		}
		
		boardList = boardService.SelectBoardList(pageVo);
		totalCnt = boardService.selectBoardCnt();
		
		comCodeList = boardService.selectCodeList();
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageNo", page);
		model.addAttribute("comCodeList", comCodeList);
		
		System.out.println(boardList.toString());
		System.out.println(comCodeList.toString());

		return "board/boardList";
	}

	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		
		BoardVo boardVo = new BoardVo();
		
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardView";
	}
	
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, BoardVo boardVo, Model model) throws Exception{
		
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		
		boardList = boardService.selectCodeList();
		model.addAttribute("boardList", boardList);
		
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale,BoardVo boardVo,
								   HttpServletRequest request) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = 0;

		resultCnt = boardService.boardInsert(boardVo);

		result.put("success", (resultCnt > 0) ? "Y" : "N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ", result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		
		return callbackMsg;
	}
	
	@RequestMapping(value="/board/{boardType}/{boardNum}/boardUpdate.do", method=RequestMethod.GET)
	public String boardUpdate(Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception {
		
		BoardVo boardVo = new BoardVo();
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardUpdate";
	}
	
	@RequestMapping(value="/board/{boardType}/{boardNum}/boardUpdateAction.do", method=RequestMethod.POST)
	@ResponseBody
	public String boardUpdateAction(Locale locale, BoardVo boardVo,
									PageVo pageVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		//web.xml에 JSON 추가를 안해주면 CommonUtil 클래스를 통하지 않고서는 JSON을 사용할 수 없다.
		
		int resultCnt = 0;
		
		resultCnt = boardService.boardUpdate(boardVo);
		
		result.put("success", (resultCnt > 0) ? "Y" : "N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ", result);		
		
		return callbackMsg;
	}
	
	@RequestMapping(value="/board/{boardType}/{boardNum}/boardDelete.do", method=RequestMethod.GET)
	public String boardDelete(BoardVo boardVo, Model mo) throws Exception {
		
		int result = boardService.boardDelete(boardVo);
		mo.addAttribute("result", result);
		
		System.out.println("삭제된 값은 : " + result);
		
		return "redirect:/board/boardList.do";
	}
	
}
