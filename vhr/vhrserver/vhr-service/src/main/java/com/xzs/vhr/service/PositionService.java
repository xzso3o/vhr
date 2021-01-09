package com.xzs.vhr.service;


import com.xzs.vhr.mapper.MenuRoleMapper;
import com.xzs.vhr.mapper.PositionMapper;
import com.xzs.vhr.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PositionService {
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    MenuRoleMapper menuRoleMapper;

    public List<Position> getAllPositions() {
        return positionMapper.getAllPositions();
    }

    public Integer addPosition(Position position) {
        position.setEnabled(true);
        position.setCreateDate(new Date());
        return positionMapper.insertSelective(position);//生成的mapper就有添加方法
    }

    public Integer updatePositions(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public Integer deletePositionById(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    public Integer deletePositionsByIds(Integer[] ids) {
        return positionMapper.deletePositionsByIds(ids);
    }

    public List<Position> getAllPositionNames() {
        return positionMapper.getAllPositionNames();
    }
}

