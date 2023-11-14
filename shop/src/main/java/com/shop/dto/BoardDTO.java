package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private long seq;

    @NotEmpty
    @Size(min = 3, max = 100)
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String writer;
    private LocalDateTime regdate;
    private LocalDateTime moddate;

}
