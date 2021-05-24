package com.dept.filesite.service;

import com.dept.filesite.entity.Office;
import com.dept.filesite.entity.User;
import com.dept.filesite.mapper.OfficeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @className: OfficeServerImpl
 * @description: Office的业务处理类
 * @author: 201998
 * @create: 2020-03-02 14:06
 */

@Service
public class OfficeService {

    @Autowired
    private OfficeMapper officeMapper;

    @Autowired
    private UserService userService;


    public List<Office> listOffice() {

        return officeMapper.listOffice();
    }


    public Office findOfficeById(int id) {
        return officeMapper.findOfficeById(id);
    }


    public String findOfficeByUsername(String username) {
        User user = userService.findUserByUsername(username);
        if (user == null){
            return "";
        }
        return user.getOffice();
    }

    public List<Integer> listOfficeId(){
        return officeMapper.listOfficeId();
    }
}
