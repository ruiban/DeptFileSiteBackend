package com.dept.filesite.service;

import com.alibaba.excel.EasyExcel;
import com.dept.filesite.entity.*;
import com.dept.filesite.mapper.TemperatureMapper;
import com.dept.filesite.utils.DateUtil;
import com.dept.filesite.utils.UserUtil;
import com.dept.filesite.vo.MaskVO;
import com.dept.filesite.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: TemperatureServerImpl
 * @description: Temperature的业务处理类
 * @author: 201998
 * @create: 2020-04-27 09:19
 */

@Service
public class TemperatureService {

    @Autowired
    TemperatureMapper temperatureMapper;

    @Autowired
    UserService userService;

    @Autowired
    OfficeService officeService;

    public ResultVO<String> insertTemperature(List<Temperature> list) {
        Temperature temp = new Temperature();
        temp.setToday(DateUtil.getToday());
        temp.setOffice(list.get(0).getOffice());
        List<Temperature> temperatureList = temperatureMapper.listTemperature(temp);
        if (temperatureList.size() > 0){
            return new ResultVO<String>(ResultCodeEnum.FAILED,"录入失败,数据已存在!");
        }
        for(Temperature temperature : list){
            temperature.setToday(DateUtil.getToday());
            temperature.setForenoon(DateUtil.getCurrentTime());
            temperature.setAfternoon(DateUtil.getCurrentTime());
            String note = temperature.getNote();
            if ("请假一天".equals(note)){
                temperature.setMask(0);
            }else{
                temperature.setMask(1);
            }
        }
        int result = -1;
        if (list.size() > 0){
            result = temperatureMapper.insertTemperature(list);
        }

        if (result == list.size() && result > 0){
            return new ResultVO<String>("录入成功");
        }else if(result == -2){
            return new ResultVO<String>(ResultCodeEnum.FAILED,"录入失败,数据已存在!");
        }else {
            return new ResultVO<String>(ResultCodeEnum.FAILED,"录入失败!");
        }

    }

    public ResultVO<String> updateTemperature(List<Temperature> list) {
        for(Temperature temperature : list){
            temperature.setAfternoon(DateUtil.getCurrentTime());
            String note = temperature.getNote();
            if ("请假一天".equals(note)){
                temperature.setMask(0);
            }else{
                temperature.setMask(1);
            }
        }

        int result = temperatureMapper.updateTemperature(list);
        if (result == list.size()){
            return new ResultVO<String>("更新成功");
        }else{
            return new ResultVO<String>(ResultCodeEnum.FAILED,"更新失败");
        }

    }


    public List<Temperature> listTemperature(Temperature temperature) {
        return temperatureMapper.listTemperature(temperature);
    }

    public List<MaskVO> sumMask(String office, String today){
        return temperatureMapper.sumMask(office,today);
    }

    public List<MaskVO> sumMask(String office) {
        List<Office> offices = officeService.listOffice();
        if("all".equals(office)){
            office = "";
        }
        List<MaskVO> result = sumMask(office,DateUtil.getToday());
        if("".equals(office)){
            List<HashMap<String,Object>> officesGroup = userService.countUserGroupByOffice();
            int len = result.size();
            for(int i = 0; i < officesGroup.size(); i++){
                int flag = -1;
                if(len > 0){
                    for(int j = 0; j < len; j++){
                        if (officesGroup.get(i).get("office").equals(result.get(j).getOffice())){
                            result.get(j).setPersons((Long) officesGroup.get(i).get("persons"));
                            flag = -1;
                            break;
                        }else{
                            flag = i;
                        }
                    }
                }else{
                    flag = i;
                }
                if (flag != -1){
                    MaskVO maskVO = new MaskVO();
                    maskVO.setOffice((String) officesGroup.get(i).get("office"));
                    maskVO.setPersons((Long) officesGroup.get(i).get("persons"));
                    result.add(maskVO);
                }
            }

        }else{
            if (result.size() <= 0){
                result = new ArrayList<MaskVO>();
                MaskVO maskVO = new MaskVO();
                maskVO.setOffice(office);
                maskVO.setPersons((long) userService.listUserByOffice(office).size());
                result.add(maskVO);
            }
        }

        //科室id转名称
        for(int i = 0; i < result.size(); i++){
            for (int j = 0; j < offices.size(); j++){
                if(Integer.parseInt(result.get(i).getOffice()) == offices.get(j).getId()){
                    result.get(i).setOffice(offices.get(j).getName());
                    break;
                }
            }
        }

        return result;
    }


    public Page listTemperaturePage(Temperature temperature, int currentPageNum, int pageSize) {
        int startIndex = (currentPageNum-1)*pageSize;
        int totalCount;
        totalCount = temperatureMapper.countTemperature(temperature);

        Map<String,Object> map = new HashMap<String, Object>(3);
        map.put("temperature",temperature);
        map.put("startIndex",startIndex);
        map.put("pageSize",pageSize);
        Page page = new Page(currentPageNum,totalCount);
        page.setStartIndex(startIndex);
        List<Temperature> records = temperatureMapper.listTemperaturePage(map);
        page.setRecords(records);
        return page;
    }


    public ResultVO<String> deleteTemperatureById(int id) {
        int result = temperatureMapper.deleteTemperatureById(id);
        if (result != 0){
            return new ResultVO<String>("删除成功");
        }else{
            return new ResultVO<String>(ResultCodeEnum.FAILED,"删除失败");
        }

    }


    public int deleteTemperatureByName(String name, String office) {
        return temperatureMapper.deleteTemperatureByName(name,office);
    }


    public void exportExcel(HttpServletResponse response) {
        try{
            response.setHeader("Content-disposition","attachment; filename="+"体温记录"+".xlsx");
            //设置类型
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            List<MaskVO> maskList = sumMask("");
            EasyExcel.write(response.getOutputStream(),MaskVO.class).sheet("体温记录").doWrite(maskList);

        }catch (Exception e){
            //重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            throw new APIException("下载文件失败");
//            Map<String,String> result = new HashMap<String, String>();
//            result.put("status","error");
//            result.put("message","下载文件失败  "+e.getMessage());
//            try {
//                response.getWriter().println(JSON.toJSONString(result));
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
        }
    }

    public List<Temperature> initForm(){
        User currentUser = UserUtil.getCurrentUser();
        Temperature temperature = new Temperature();
        temperature.setOffice(currentUser.getOffice());
        temperature.setToday(DateUtil.getToday());
        List<Temperature> temperatures;
        //从数据库中查找
        temperatures = temperatureMapper.listTemperature(temperature);
        List<User> users = userService.listUserByOffice(currentUser.getOffice());
        //数据库中没有数据，重新创建
        if (temperatures.size() < 1){
            temperatures = getInitTemperatures(currentUser);
        }
        boolean flag = false;
        if (users.size() > 0 && temperatures.size() > 0 ){
            for (User user : users){
                flag = false;
                for (Temperature temper : temperatures){
                    if (user.getUsername().equals(temper.getName())){
                        flag = true;
                        break;
                    }
                }
                //没有温度记录
                if (!flag){

                    //创建温度记录
                    Temperature temper = new Temperature();
                    temper.setToday(DateUtil.getToday());
                    temper.setOffice(currentUser.getOffice());
                    temper.setName(user.getUsername());
                    temper.setNote("正常");
                    temper.setMask(1);
                    temperatureMapper.addTemperature(temper);
                    temperatures.add(temperatureMapper.findTemperatureById(temper.getId()));

                }
            }

        }
        return temperatures;
    }

    private List<Temperature> getInitTemperatures(User currentUser){
        Office office = officeService.findOfficeById(Integer.parseInt(currentUser.getOffice()));
        List<User> list =  userService.listUserByOffice(currentUser.getOffice());
        List<Temperature> temperatures = new ArrayList<Temperature>();
        for(User user : list){
            Temperature temperature = new Temperature();
            temperature.setOffice(office.getName());
            temperature.setName(user.getUsername());
            temperature.setNote("正常");
            temperature.setMask(1);
            temperatures.add(temperature);
        }
        return temperatures;
    }
}
