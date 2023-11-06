package com.chunjae.test06.biz;

import com.chunjae.test06.entity.FreeComment;
import com.chunjae.test06.per.FreeCommentMapper;
import com.chunjae.test06.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FreeCommentServiceImpl implements FreeCommentService {

    @Autowired
    private FreeCommentMapper freeCommentMapper;

    @Override
    public List<FreeComment> commentList(int fno, Page page) {
        Map<String, Object> data = new HashMap<>();
        data.put("fno", fno);
        data.put("page", page);
        return freeCommentMapper.commentList(data);
    }

    @Override
    public int totalCnt(int fno) {
        return freeCommentMapper.totalCnt(fno);
    }

    @Override
    public FreeComment getComment(int cno) {
        return freeCommentMapper.getComment(cno);
    }

    @Override
    public int insertFreeComment(FreeComment comment) {
        return freeCommentMapper.insertFreeComment(comment);
    }

    @Override
    public int updateRecommend(int cno, String type) {
        Map<String, Object> data = new HashMap<>();
        data.put("cno", cno);
        data.put("type", type);
        return freeCommentMapper.updateRecommend(data);
    }
}
