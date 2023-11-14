package com.shop.per;


import com.shop.entity.Board;
import com.shop.entity.QBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() { super(Board.class); }

    @Override
    public Page<Board> searchOne(Pageable pageable) {           //예시 문구
        QBoard board = QBoard.board;                            // 가상테이블(메모리), [Build] - [Rebuild Project] 후 오류 사라짐 (그 전에 QBoard import는 필수)
        JPQLQuery<Board> query = from(board);
        BooleanBuilder booleanBuilder = new BooleanBuilder();   //where 절 생성기
        booleanBuilder.or(board.title.contains("11"));          // title like '%11%' or content like '%11%' => title like concat('%',#{keyword},'%') or content like concat('%',#{keyword},'%')
        booleanBuilder.or(board.content.contains("11"));
        query.where(booleanBuilder);
        query.where(board.seq.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);
        List<Board> list = query.fetch();
        long count = query.fetchCount();
        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);
        if( (types != null && types.length > 0) && keyword != null ){
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for(String type: types){
                switch (type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        query.where(board.seq.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);
        List<Board> list = query.fetch();
        long count = query.fetchCount();
        return new PageImpl<>(list, pageable, count);
    }
}
