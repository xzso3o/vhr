package com.xzs.vhr.service;

import com.xzs.vhr.mapper.HrMapper;
import com.xzs.vhr.mapper.HrRoleMapper;
import com.xzs.vhr.model.Hr;
import com.xzs.vhr.utils.HrUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HrService implements UserDetailsService {

    @Resource
    HrMapper hrMapper;
    @Resource
    HrRoleMapper hrRoleMapper;

    //根据用户名来加载你的User对象
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if (hr == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        hr.setRoles(hrMapper.getHrRolesById(hr.getId()));
        return hr;
    }

    public List<Hr> getAllHrs(String keywords) {
        return hrMapper.getAllHrs(HrUtils.getCurrentHr().getId(),keywords);//除去我自己，把自己id传进去
    }

    public Integer updateHr(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    @Transactional
    public boolean updateHrRole(Integer hrid, Integer[] rids) {
        hrRoleMapper.deleteByHrId(hrid);
        return hrRoleMapper.addRole(hrid,rids) == rids.length;
    }

    public Integer deleteHr(Integer id) {
        return hrMapper.deleteByPrimaryKey(id);
    }

    public List<Hr> getAllHrsExceptCurrentHr() {
        return hrMapper.getAllHrsExceptCurrentHr(HrUtils.getCurrentHr().getId());
    }

    public boolean updateHrPasswd(String oldpass, String pass, Integer hrid) {
        Hr hr = hrMapper.selectByPrimaryKey(hrid);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldpass, hr.getPassword())) {
            String encodePass = encoder.encode(pass);//加密一下新密码再进行更新
            Integer result = hrMapper.updatePasswd(hrid, encodePass);
            if (result == 1) {
                return true;
            }
        }
        return false;
    }

//    public Integer updateUserface(String url, Integer id) {
//        return hrMapper.updateUserface(url, id);
//    }
}
