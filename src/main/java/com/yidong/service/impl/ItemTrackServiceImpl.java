package com.yidong.service.impl;

import com.yidong.mapper.DepartmentMapper;
import com.yidong.mapper.ItemTrackMapper;
import com.yidong.mapper.UserMapper;
import com.yidong.model.ItemTrack;
import com.yidong.model.User;
import com.yidong.service.ItemTrackService;
import com.yidong.service.UserService;
import com.yidong.util.Time;
import com.yidong.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemTrackServiceImpl implements ItemTrackService {

    @Autowired
    private ItemTrackMapper itemTrackMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private UserMapper userMapper;
    /**
     *
     * @param itemTrack
     * @return 如果添加操作成功返回1，否则0
     */
    @Override
    public int addItemtrack(ItemTrack itemTrack) {
        itemTrack.setId(UUIDUtils.getUUID());
        String id=itemTrack.getUser_id();
        User user=userMapper.getUser(id);
        itemTrack.setName(user.getName());
        itemTrack.setTelephone(user.getTelephone());
        itemTrack.setEmail(user.getEmail());
        itemTrack.setDepartment(departmentMapper.select(user.getDepartment()));
        itemTrack.setApply_year(Time.getTime());
        return itemTrackMapper.insert(itemTrack);
    }

    /*
      通过user_id（用户的ID）来获取跟踪表的信息
     */
    @Override
    public List<ItemTrack> selectByUserId(String user_id) {

        return itemTrackMapper.selectByUserId(user_id);
    }

    /*
       通过跟踪表的ID来检索获取表格信息
     */
    @Override
    public ItemTrack selectById(String id) {

        return itemTrackMapper.selectById(id);
    }

    /*
      更新ID所对应的跟踪表的信息
     */
    @Override
    public int updateById(ItemTrack itemTrack) {

        return itemTrackMapper.updateById(itemTrack);
    }

    /**
     * 根据提交的申请领域判断查找今年已经申请过该领域
     * @param apply_field
     * @return
     */
    @Override
    public Boolean selectByApplyFiled(String apply_field,String user_id,String id) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("apply_field",apply_field);
        map.put("apply_year",Time.getTime());
        map.put("user_id",user_id);
        map.put("id",id);
        if(itemTrackMapper.selectByApplyField(map)==0)return true;
        else return false;
    }


}
