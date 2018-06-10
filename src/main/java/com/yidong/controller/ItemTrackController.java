package com.yidong.controller;
 /**
 * 用于项目跟踪表操作的controller
 */

import org.springframework.security.access.prepost.PreAuthorize;
import com.yidong.model.ItemTrack;
import com.yidong.service.ItemTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@PreAuthorize("hasRole('USER')")
public class ItemTrackController {
    /**
     * 自动注入itemTrackService
     */
    @Autowired
    private ItemTrackService itemTrackService;

    public void setItemTrackService(ItemTrackService itemTrackService) {

        this.itemTrackService = itemTrackService;
    }

    /**
     *
     * @param itemTrack
     * @return 添加ItemTrack，如果成功则返回1，不成功会返回状态码404
     */
    @RequestMapping("/addItemTrack")
    public ResponseEntity addItemTrack(ItemTrack itemTrack){
        int A=itemTrackService.addItemtrack(itemTrack);
        if(A==0){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /*
     *  通过user_id查找到对应的所有跟踪表
     */
    @RequestMapping("/selectByUserId")
    public List<ItemTrack> selectByUserId(@RequestParam String user_id){
        return itemTrackService.selectByUserId(user_id);
    }

    /**
     * 根据id查找对应的itemTrack
     * @param id
     * @return
     */
    @RequestMapping("/selectById")
    public ItemTrack selectById(@RequestParam String id){
        return  itemTrackService.selectById(id);
    }

    /**
     * 根据传入的ItemTrack对象修改itemTrack
     * @param itemTrack
     * @return
     */
    @RequestMapping("/updateItemTrack")
    public ResponseEntity updateById(ItemTrack itemTrack){
        int A=itemTrackService.updateById(itemTrack);
        if(A==0){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 传入申请领域判断是否重复
     * @param apply_field
     * @return 如果申请领域不重复则返回true，否则返回false
     */
    @RequestMapping("/checkApplyField")
    public Boolean checkApplyFiled(@RequestParam String apply_field,
                                   @RequestParam String user_id,
                                   @RequestParam String id){
        return itemTrackService.selectByApplyFiled(apply_field,user_id,id);
    }
}
