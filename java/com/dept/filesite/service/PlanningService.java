package com.dept.filesite.service;

import com.dept.filesite.entity.*;
import com.dept.filesite.mapper.PlanningMapper;
import com.dept.filesite.utils.UserUtil;
import com.dept.filesite.vo.ResultVO;
import io.swagger.models.auth.In;
import org.apache.ibatis.mapping.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class PlanningService {


    @Autowired
    PlanningMapper planningMapper;

    public ResultVO<String> insertPlanning(Planning planning) {
        Planning pla = planningMapper.findPlanningByName(planning.getName());
        if(pla !=null){
            return new ResultVO(ResultCodeEnum.FAILED, "策劃命名重複");
        }
        String name = planning.getName();
        int imageNumber = planning.getImageNumber();
        return new ResultVO<String>("創建成功");
    }

    public ResultVO<String> updatePlanning(Planning planning) {
        Planning oldPlanning = planningMapper.findPlanningById(planning.getId());
        String oldName = oldPlanning.getName();
        String name = planning.getName();
        int oldImageNumber = oldPlanning.getImageNumber();
        int imageNumber = planning.getImageNumber();
        if(planningMapper.updatePlanning(planning) > 0 ){
            return new ResultVO<String>("更新成功");
        }else {
            return new ResultVO<String>(ResultCodeEnum.FAILED,"更新失败");
        }
    }

    public Page listPlanning(Planning planning, int currentPageNum, int pageSize) {
        int startIndex = (currentPageNum -1) * pageSize;
        User currentUser = UserUtil.getCurrentUser();

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("planning",planning);

        map.put("startIndex",startIndex);
        map.put("pageSize",pageSize);
        int totalCount = planningMapper.countTotal(map);
        Page page = new Page(currentPageNum,totalCount);
        page.setStartIndex(startIndex);
        //List<Anounce> records = getAnounceList(activeName,map);
        List<Planning> records = planningMapper.listPlanning(map);
        page.setRecords(records);
        return page;

    }

    public ResultVO<String> deletePlanning(Long[] aids) {
        if(planningMapper.deletePlanningById(aids) > 0 ){
            return new ResultVO<String>("刪除成功");
        }else{
            return new ResultVO<String>(ResultCodeEnum.FAILED, "刪除失敗");
        }
    }

    public Planning findPlanningById(Integer id) {
        return planningMapper.findPlanningById(id);
    }

}
