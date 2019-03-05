package com.nh.oms.service;

import com.nh.oms.model.oms.OmsTestResult;
import com.nh.oms.model.oms.Testresult;

import java.util.List;

/**
 * Created by jjxu on 2018/11/21.
 */
public interface IResultService {

    //处理常卫清报告结果
    boolean insertCwqResult(Testresult testresult);
    //处理常卫宁报告结果
    boolean insertCwnResult(Testresult testresult);
    //处理常卫明报告结果
    boolean insertCwmResult(Testresult testresult);
    //处理费证清报告结果
    boolean insertFzqResult(List<Testresult> testresult);

    OmsTestResult selectBySampleno(String sampleno);

}
