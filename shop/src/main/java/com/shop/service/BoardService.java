package com.shop.service;

import com.shop.dto.BoardDTO;
import com.shop.dto.PageRequestDTO;
import com.shop.dto.PageResponseDTO;

public interface BoardService {

    public Long register(BoardDTO boardDTO);
    public BoardDTO selectOne(long seq);
    public void modify(BoardDTO boardDTO);
    public void remove(long seq);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

}
