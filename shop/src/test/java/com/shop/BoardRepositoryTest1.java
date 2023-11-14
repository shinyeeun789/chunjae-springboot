package com.shop;

import com.shop.entity.Board;
import com.shop.per.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// 페이징 처리 4대 클래스(Page, PageRequest, Pageable, Sort)
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTest1 {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {                  // 100개의 더미데이터 생성
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Board board = Board.builder()
                    .title("title - " + i)
                    .content("content ------ " + i)
                    .writer("member - 작성자 " + i)
                    .build();
            Board result = boardRepository.save(board);         // save : insert문
            log.info("SEQ : " + result.getSeq());
        });
    }

    @Test
    public void testSelectOne() {               // 100번째 글 조회
        Long seq = 100L;

        Optional<Board> result = boardRepository.findById(seq);
        Board board = result.orElseThrow();
        log.info(board);
    }

    @Test
    public void testUpdate() {                  // 100번째 글 수정
        Long seq = 100L;

        Optional<Board> result = boardRepository.findById(seq);
        Board board = result.orElseThrow();
        board.change("update title 100", "update content 100");

        boardRepository.save(board);
    }

    @Test
    public void testDelete() {                  // 1번째 글 삭제
        Long seq = 1L;
        boardRepository.deleteById(seq);
    }

    @Test
    public void testSelectAll() {
        // seq로 오름차순 정렬해서 1부터 10개의 데이터를 불러옴
        Pageable pageable = PageRequest.of(0, 10, Sort.by("seq").descending());
        Page<Board> result = boardRepository.findAll(pageable);
        log.info("total count: " + result.getTotalElements());
        log.info("total pages: " + result.getTotalPages());
        log.info("page number: " + result.getNumber());
        log.info("page size: " + result.getSize());
        List<Board> todoList = result.getContent();
        todoList.forEach(board -> log.info(board));
    }

}
