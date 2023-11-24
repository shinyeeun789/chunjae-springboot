package com.shop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor                  // 반드시 @NoArgsConstructor 먼저 선언할 것
@AllArgsConstructor
public class ReplyDTO {             // Reply는 테이블을 만들기 위한 클래스, ReplayDTO는 우리가 알고 있는 DTO 클래스

    private Long rno;

    @NotNull
    private Long bno;

    @NotEmpty                       // Null : NULL vs Empty : ''
    private String replayText;

    @NotEmpty
    private String replyer;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;

    @JsonIgnore
    private LocalDateTime modDate;

}
