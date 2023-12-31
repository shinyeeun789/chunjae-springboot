package com.chunjae.test06.per;

import com.chunjae.test06.entity.Free;
import com.chunjae.test06.util.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FreeMapper {
    public List<Free> freeList(Page page);
    public Free getFree(int fno);
    public int insertFree(Free free);
    public int updateFree(Free free);
    public int deleteFree(int fno);
    public int updateViews(int fno);
    public int updateRecommend(int fno);
    public int totalCnt();

}
