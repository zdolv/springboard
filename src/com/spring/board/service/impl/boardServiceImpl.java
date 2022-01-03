package com.spring.board.service.impl;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComcodeVo;
import com.spring.board.vo.PageVo;

@Service
public class boardServiceImpl implements boardService{
	
	@Autowired
	BoardDao boardDao;
	
	@Autowired
	HttpServletRequest request;

	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectTest();
	}
	
	@Override
	public List<BoardVo> SelectBoardList(PageVo pageVo) throws Exception {
		// TODO Auto-generated method stub
		
		return boardDao.selectBoardList(pageVo);
	}
	
	@Override
	public int selectBoardCnt() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectBoardCnt();
	}
	
	@Override
	public BoardVo selectBoard(String boardType, int boardNum) throws Exception {
		// TODO Auto-generated method stub
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		
		return boardDao.selectBoard(boardVo);
		
	}
	
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {

		int result = 1;
		List<BoardVo> list = new ArrayList<>();
		//String[] boardTitle = request.getParameterValues("boardTitle");
		//String[] boardComment = request.getParameterValues("boardComment");
		//String[] boardType = request.getParameterValues("boardType");
		
		//getAttribute 쓰면 되나?

		try {
			
			int[] results = new int[boardVo.getBoardList().size()];
			
			for(int i=0; i<boardVo.getBoardList().size(); i++) {
				
				//boardVo.setBoardType(boardType[i]);
				//boardVo.setBoardTitle(boardTitle[i]);
				//boardVo.setBoardComment(boardComment[i]);
				results[i] = boardDao.boardInsert(boardVo.getBoardList().get(i));
				result *= results[i];
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int boardUpdate(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.boardUpdate(boardVo);
	}

	@Override
	public int boardDelete(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.boardDelete(boardVo);
	}

	@Override
	public List<BoardVo> selectCodeList() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectCodeList();
	}
	
}
