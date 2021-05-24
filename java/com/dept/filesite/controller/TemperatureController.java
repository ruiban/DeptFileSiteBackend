package com.dept.filesite.controller;


import com.dept.filesite.entity.Page;
import com.dept.filesite.vo.ResultVO;
import com.dept.filesite.entity.Temperature;
import com.dept.filesite.service.TemperatureService;
import com.dept.filesite.vo.MaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @className: TemperatureController
 * @description:
 * @author: 201998
 * @create: 2020-04-27 09:21
 */

@RestController
@RequestMapping("/temperature")
public class TemperatureController {

    @Autowired
    TemperatureService temperatureService;


    @RequestMapping("/insert")
    public ResultVO<String> insertTemperature(@RequestBody List<Temperature> temperatures){
        return temperatureService.insertTemperature(temperatures);
    }

    @RequestMapping("/update")
    public ResultVO<String> updateTemperature(@RequestBody List<Temperature> temperatures){
        return temperatureService.updateTemperature(temperatures);
    }

    @RequestMapping("/list")
    public List<Temperature> listTemperature(Temperature temperature){
        return temperatureService.listTemperature(temperature);
    }

    @RequestMapping("/page")
    public Page listTemperaturePage(Temperature temperature, Integer currentPageNum, Integer pageSize){
        return temperatureService.listTemperaturePage(temperature,currentPageNum,pageSize);
    }

    @RequestMapping("/sum/{office}")
    public List<MaskVO> sumMask(@PathVariable String office){
        return temperatureService.sumMask(office);
    }


    @RequestMapping("/initForm")
    public List<Temperature> initForm(){
        return temperatureService.initForm();
    }

    @RequestMapping("/export")
    public void exportExcel(HttpServletResponse response){
        temperatureService.exportExcel(response);

    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public ResultVO<String> delete(@PathVariable Integer id){
        return temperatureService.deleteTemperatureById(id);
    }

}
