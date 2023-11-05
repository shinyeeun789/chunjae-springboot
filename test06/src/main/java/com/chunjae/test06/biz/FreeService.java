package com.chunjae.test06.biz;

import com.chunjae.test06.entity.Free;

import java.util.List;

public interface FreeService {

    public List<Free> freeList();
    public Free getFree(int fno);
    public int insertFree(Free free);
    public int updateFree(Free free);
    public int deleteFree(int fno);
    public int updateViews(int fno);
    public int updateRecommend(int fno);

}
