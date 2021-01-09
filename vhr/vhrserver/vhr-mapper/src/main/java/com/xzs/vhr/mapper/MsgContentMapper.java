package com.xzs.vhr.mapper;

import com.xzs.vhr.model.MsgContent;

public interface MsgContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsgContent record);

    int insertSelective(MsgContent record);

    MsgContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsgContent record);

    int updateByPrimaryKey(MsgContent record);
}