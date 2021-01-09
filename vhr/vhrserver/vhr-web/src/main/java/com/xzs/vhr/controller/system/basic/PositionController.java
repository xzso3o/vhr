package com.xzs.vhr.controller.system.basic;

import com.xzs.vhr.model.Position;
import com.xzs.vhr.model.RespBean;
import com.xzs.vhr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    PositionService positionService;

    @GetMapping("/")
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){//添加是以json方式传递的，使用@RequestBody
        if (positionService.addPosition(position) == 1){
            return RespBean.ok("添加成功！");
        }else
            return RespBean.error("添加失败！");
    }

    @PutMapping("/")
    public RespBean updatePositions(@RequestBody Position position){
        if (positionService.updatePositions(position) == 1){
            return RespBean.ok("更新成功!");
        }else {
            return RespBean.error("更新失败！");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deletePositionById(@PathVariable Integer id){
        if (positionService.deletePositionById(id) == 1){
            return RespBean.ok("删除成功!");
        }else {
            return RespBean.error("删除失败！");
        }
    }

    @DeleteMapping("/")
    public RespBean deletePositionsByIds(Integer[] ids){//以数组形式传值
        if (positionService.deletePositionsByIds(ids) == ids.length){
            return RespBean.ok("删除成功！");
        }else {
            return RespBean.error("删除失败！");
        }
    }

}
