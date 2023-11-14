package com.shop.biz;

import com.shop.dto.BoardDTO;
import com.shop.dto.PageRequestDTO;
import com.shop.dto.PageResponseDTO;

import java.util.List;

public interface BoardService {

    public Long register(BoardDTO boardDTO);
    public BoardDTO selectOne(long seq);
    public void modify(BoardDTO boardDTO);
    public void remove(long seq);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

}
