package com.asiainfo.controller.microRecord;

import com.asiainfo.domain.entity.microRecord.MicroRecord;
import com.asiainfo.service.microRecord.interfaces.MicroRecordServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jerry on 2017/1/6.
 */
@RestController
public class MicroRecordController {
    @Autowired
    private MicroRecordServices microRecordServices;
    @RequestMapping(path="/queryMicroRecordByCreateDate",method = RequestMethod.GET)
    public List<MicroRecord> queryMicroRecordByCreateDate(long startDate, long endDate){
        return microRecordServices.findByCreateDateBetween(startDate,endDate);
    }
    @PostMapping(path="/queryMicroRecord/{userId}")
    public List<MicroRecord> queryMicroRecordByUserId(@PathVariable("userId") String userId) {
        return microRecordServices.findByReportUserId(userId);
    }
}
