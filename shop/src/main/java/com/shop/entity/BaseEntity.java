package com.shop.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value={AuditingEntityListener.class})
public class BaseEntity {
    @CreatedDate                                    // 글이 등록될 때에만 등록할 수 있도록
    @Column(name = "regdate", updatable = false)    // 데이터베이스 컬럼명: regdate, update 방지
    private LocalDateTime regDate;                  // 최초등록시간

    @LastModifiedDate
    @Column(name = "moddate", updatable = false)
    private LocalDateTime modDate;                  // 수정시간

}
