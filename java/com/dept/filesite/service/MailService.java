package com.dept.filesite.service;

import com.dept.filesite.entity.User;
import com.dept.filesite.utils.DateUtil;
import com.dept.filesite.vo.MaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @className: MailService
 * @description: TODO
 * @author: 201998
 * @create: 2020-12-08 13:53:42
 */

@Component
public class MailService {

    @Autowired
    TemperatureService temperatureService;

    @Autowired
    UserService userService;

    @Autowired
    OfficeService officeService;

    @Autowired
    JavaMailSenderImpl mailSender;

    @Value("${mail.sender}")
    private String sender;

    @Value("${mail.cc}")
    private String cc;

    @Value("${mail.isSend}")
    private int isSend;

    //周一到周五11:00发送
    @Scheduled(cron = "0 0 11 ? * MON-FRI")
    public void sendTemperatureMail(){

        if (isSend == 1){
            SimpleMailMessage message = new SimpleMailMessage();
            // 邮箱设置
            message.setSubject("通知-未提交科室人员体温数据");
            message.setFrom(sender+"@gree.com.cn");
            //抄送
            message.setCc(cc+"@gree.com.cn");

            List<Integer> offices = officeService.listOfficeId();
            Set<String> undoneOffices = new HashSet<String>();
            List<MaskVO> result = temperatureService.sumMask("",DateUtil.getToday());

            for (int i = 0; i < offices.size(); i++){
                boolean flag = true;
                for (MaskVO maskVO : result){
                    if (maskVO.getOffice().equals(String.valueOf(offices.get(i)))){
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    //未提交 除去部门领导，管理员，公司领导
                    if (offices.get(i) != 1 || offices.get(i) != 25 || offices.get(i) != 26){
                        undoneOffices.add(String.valueOf(offices.get(i)));
                    }
                }
            }

            List<User> users = userService.listUserByRoleAndOffice(3,undoneOffices.toArray(new String[undoneOffices.size()]));

            List<String> temp = new ArrayList<String>();
            for (String office : undoneOffices){
                temp.clear();
                for (User user : users){
                    if (office.equals(user.getOffice())){
                        temp.add(user.getMail()+"@gree.com.cn");
                    }
                }
                String[] to = temp.toArray(new String[temp.size()]);
                message.setTo(to);
                message.setText("上午未提交科室人员体温数据");
                //message.setTo("201998@gree.com.cn");
                mailSender.send(message);
            }
        }

    }
}
