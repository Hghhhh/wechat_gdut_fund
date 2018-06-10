package com.yidong.mapper;

import com.yidong.model.ItemTrack;

public interface ItemTrackMapper {
    int insert(ItemTrack record);

    int insertSelective(ItemTrack record);
}