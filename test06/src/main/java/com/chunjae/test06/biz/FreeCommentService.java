package com.chunjae.test06.biz;

import com.chunjae.test06.entity.FreeComment;
import com.chunjae.test06.util.Page;

import java.util.List;

public interface FreeCommentService {
    public List<FreeComment> commentList(int fno, Page page);
    public FreeComment getComment(int cno);
    public int totalCnt(int fno);
    public int insertFreeComment(FreeComment comment);
    public int updateRecommend(int cno, String type);

}
