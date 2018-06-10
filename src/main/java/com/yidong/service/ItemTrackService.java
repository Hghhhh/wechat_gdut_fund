package com.yidong.service;

import com.yidong.model.ItemTrack;

import java.util.List;

public interface ItemTrackService {
    public int addItemtrack(ItemTrack itemTrack);
    public List<ItemTrack> selectByUserId(String user_id);
    public ItemTrack selectById(String id);
    public int updateById(ItemTrack itemTrack);
    public Boolean selectByApplyFiled(String apply_field,String user_id,String id);
}
