package com.shop.entity;

import com.shop.entity.BaseEntity;
import com.shop.entity.Board;
import lombok.*;

import javax.persistence.*;

// MyBatis 대신 쓰는 게 entity

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder                                    // .builder를 사용하여 객체 생성 가능
@ToString(exclude = "board")
@Table(name = "Reply", indexes = @Index(name = "idx_reply_board_bno", columnList = "board_bno"))
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)             // auto_increment
    private Long rno;
    
    @ManyToOne(fetch = FetchType.LAZY)                              // N : 1 관계 설정
    private Board board;
    
    private String replyText;
    private String replyer;

    public void changeText(String text) {
        this.replyText = text;
    }

    // Lazy Loading
    // 먼저 디비에서 데이터를 끌고 오고 뷰는 데이터가 도착하기 전까지 대기, 데이터 도착 시 뷰 출력

}
