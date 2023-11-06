package com.chunjae.test06.per;

import com.chunjae.test06.biz.FreeCommentService;
import com.chunjae.test06.entity.FreeComment;
import com.chunjae.test06.util.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface FreeCommentMapper {
    public List<FreeComment> commentList(Map<String, Object> data);
    public FreeComment getComment(int cno);
    public int totalCnt(int fno);
    public int insertFreeComment(FreeComment freeComment);
    public int updateRecommend(Map<String, Object> data);

}
