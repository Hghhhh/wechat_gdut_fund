package com.yidong.mapper;

import com.yidong.model.ItemTrackAdd;

public interface ItemTrackAddMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemTrackAdd record);

    int insertSelective(ItemTrackAdd record);

    ItemTrackAdd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemTrackAdd record);

    int updateByPrimaryKey(ItemTrackAdd record);
}