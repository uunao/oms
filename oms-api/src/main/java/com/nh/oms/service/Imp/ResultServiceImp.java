package com.nh.oms.service.Imp;

import com.nh.oms.common.utils.DateUtil;
import com.nh.oms.config.Interceptor;
import com.nh.oms.dao.oms.OmsTestResultMapper;
import com.nh.oms.dao.oms.ResultDetailMapper;
import com.nh.oms.model.oms.OmsTestResult;
import com.nh.oms.model.oms.ResultDetail;
import com.nh.oms.model.oms.Testresult;
import com.nh.oms.service.IResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by jjxu on 2018/11/21.
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ResultServiceImp implements IResultService {

    private final static Logger logger = LoggerFactory.getLogger(Interceptor.class);

    @Autowired
    OmsTestResultMapper omsTestResultDao;

    @Autowired
    ResultDetailMapper resultDetailDao;

    @Transactional(propagation= Propagation.REQUIRED, isolation= Isolation.READ_COMMITTED, readOnly=false)
    public boolean insertCwqResult(Testresult testresult){

        try{
            String id=UUID.randomUUID().toString();
            OmsTestResult omsTestResult=new OmsTestResult();
            omsTestResult.setId(id);
            omsTestResult.setSampleno(testresult.getSampleno());
            omsTestResult.setDetectedPerson(testresult.getPatientname());
            omsTestResult.setSex(testresult.getSex());
            omsTestResult.setBirthday(DateUtil.StringToDate(testresult.getBirthday()));
            omsTestResult.setToponymy(testresult.getToponymy());
            omsTestResult.setTestid(testresult.getTestid());
            //样本质量2，直接不合格
            if(testresult.getStayhospitalmode().equals("2")){
                omsTestResult.setTestResult("invalid");
                //新增不合格原因 add by xujingjing at 2019-01-29
                omsTestResult.setUnqualifiedReason(testresult.getImReason());
            }else{
                //阳性需要增加肠原因 add by xujingjing at 2019-01-29
                if (Integer.parseInt(testresult.getTestresult()) >= 165){
                    omsTestResult.setImReason(testresult.getImReason());
                }
                omsTestResult.setTestResult(testresult.getTestresult());
            }
            omsTestResult.setResultFlag(testresult.getResultflag());
            omsTestResult.setTestResult(testresult.getTestresult());
            omsTestResult.setMeasureTime(DateUtil.StringToDate(testresult.getMeasuretime()));
            omsTestResult.setOperator(testresult.getOperator());
            if(testresult.getAge()!=null && !testresult.getAge().equals("")){
                omsTestResult.setAge(Integer.parseInt(testresult.getAge()));
            }
            if(testresult.getOperatornotes()!=null && !testresult.getOperatornotes().equals("")){
                omsTestResult.setOperatorNotes(testresult.getOperatornotes());
            }
            if(testresult.getHint()!=null && !testresult.getHint().equals("")){
                omsTestResult.setHint(testresult.getHint());
            }
            if(testresult.getUnit()!=null && !testresult.getUnit().equals("")){
                omsTestResult.setUnit(testresult.getUnit());
            }
            if(testresult.getTrayno()!=null) {
                omsTestResult.setTrayno(Integer.parseInt(testresult.getTrayno()));
            }
            if(testresult.getPosno()!=null){
                omsTestResult.setPosno(Integer.parseInt(testresult.getPosno()));
            }
            omsTestResult.setReceiver(testresult.getReceiver());
            omsTestResult.setReceiveTime(DateUtil.StringToDate(testresult.getReceivetime()));
            omsTestResult.setCheckOperator(testresult.getCheckoperator());
            omsTestResult.setCheckTime(DateUtil.StringToDate(testresult.getChecktime()));
            omsTestResult.setCreateTime(new Date());
            omsTestResultDao.insert(omsTestResult);

            //插入报告表头
            ResultDetail resultDetail=new ResultDetail();
            resultDetail.setId(UUID.randomUUID().toString());
            resultDetail.setResultId(id);
            resultDetail.setTestItemId("E266ED49-A8E6-41B7-B8C4-2D307BC856D9");
            if(Integer.parseInt(testresult.getStayhospitalmode())==2){
                resultDetail.setTestResult("invalid");
                resultDetail.setSample("不合格");
            }else
            {
                resultDetail.setTestResult(testresult.getTestresult());
                resultDetail.setSample("合格");
            }
            resultDetail.setProductName("常卫清");
            resultDetail.setCreateTime(new Date());
            //插入报告明细
            resultDetailDao.insert(resultDetail);

            return true;
        }catch (Exception error){
            //return false;
            logger.info("插入报告错误信息："+error.getMessage());
            throw new RuntimeException();

        }

    }

    //常卫宁报告
    public  boolean insertCwnResult(Testresult testresult){
        try{
            String id=UUID.randomUUID().toString();
            OmsTestResult omsTestResult=new OmsTestResult();
            omsTestResult.setId(id);
            omsTestResult.setSampleno(testresult.getSampleno());
            omsTestResult.setDetectedPerson(testresult.getPatientname());
            omsTestResult.setSex(testresult.getSex());
            omsTestResult.setBirthday(DateUtil.StringToDate(testresult.getBirthday()));
            omsTestResult.setToponymy(testresult.getToponymy());
            omsTestResult.setTestid("");
            omsTestResult.setTestResult("");
            omsTestResult.setResultFlag(testresult.getResultflag());
            omsTestResult.setTestResult(testresult.getTestresult());
            omsTestResult.setMeasureTime(DateUtil.StringToDate(testresult.getMeasuretime()));
            omsTestResult.setOperator(testresult.getOperator());
            if(testresult.getAge()!=null && !testresult.getAge().equals("")){
                omsTestResult.setAge(Integer.parseInt(testresult.getAge()));
            }
            if(testresult.getOperatornotes()!=null && !testresult.getOperatornotes().equals("")){
                omsTestResult.setOperatorNotes(testresult.getOperatornotes());
            }
            if(testresult.getHint()!=null && !testresult.getHint().equals("")){
                omsTestResult.setHint(testresult.getHint());
            }
            if(testresult.getUnit()!=null && !testresult.getUnit().equals("")){
                omsTestResult.setUnit(testresult.getUnit());
            }
            if(testresult.getTrayno()!=null) {
                omsTestResult.setTrayno(Integer.parseInt(testresult.getTrayno()));
            }
            if(testresult.getPosno()!=null){
                omsTestResult.setPosno(Integer.parseInt(testresult.getPosno()));
            }
            omsTestResult.setReceiver(testresult.getReceiver());
            omsTestResult.setReceiveTime(DateUtil.StringToDate(testresult.getReceivetime()));
            omsTestResult.setCheckOperator(testresult.getCheckoperator());
            omsTestResult.setCheckTime(DateUtil.StringToDate(testresult.getChecktime()));
            omsTestResult.setCreateTime(new Date());

            String ybzl="";
            String R2="";
            String R3="";
            if(Float.parseFloat(testresult.getStayhospitalmode())==2){
                ybzl = "不合格";
                R2 = "invalid";
                R3 = "invalid";
                //新增不合格原因 add by xujingjing at 2019-01-29
                omsTestResult.setUnqualifiedReason(testresult.getStomachReason());
            }else
            {
               if(Float.parseFloat(testresult.getFee())==0){
                   ybzl = "合格";
                   R2 = "阴性";
                   R3 = "阴性";
               } else if(Float.parseFloat(testresult.getFee())==1){
                   ybzl = "合格";
                   R2 = "阳性";
                   R3 = "阳性";
                   //阳性 新增胃原因 add by xujingjing at 2019-01-29
                   omsTestResult.setStomachReason(testresult.getStomachReason());

               }else if(Float.parseFloat(testresult.getFee())==2){
                   ybzl = "合格";
                   R2 = "阳性";
                   R3 = "阴性";

                   //阳性 新增胃原因 add by xujingjing at 2019-01-29
                   omsTestResult.setStomachReason(testresult.getStomachReason());
               }
            }

            omsTestResultDao.insert(omsTestResult);
            ResultDetail resultDetail1=new ResultDetail();
            resultDetail1.setId(UUID.randomUUID().toString());
            resultDetail1.setResultId(id);
            resultDetail1.setTestItemId("9B672F67-394A-41B7-B5DA-9E1A5CF3C2ED");
            resultDetail1.setTestResult(R2);
            resultDetail1.setSample(ybzl);
            resultDetail1.setProductName("常卫宁");
            resultDetail1.setCreateTime(new Date());
            //插入报告明细
            resultDetailDao.insert(resultDetail1);

            ResultDetail resultDetail2=new ResultDetail();
            resultDetail2.setId(UUID.randomUUID().toString());
            resultDetail2.setResultId(id);
            resultDetail2.setTestItemId("3FA89FD3-9E7E-431A-8F26-671B67DABC9D");
            resultDetail2.setTestResult(R3);
            resultDetail2.setSample(ybzl);
            resultDetail2.setProductName("常卫宁");
            resultDetail2.setCreateTime(new Date());
            //插入报告明细
            resultDetailDao.insert(resultDetail2);

            return true;
        }catch (Exception error){
            throw new RuntimeException();
        }

    }

    //常卫明报告
    public boolean insertCwmResult(Testresult testresult){
        try{
            String id=UUID.randomUUID().toString();
            OmsTestResult omsTestResult=new OmsTestResult();
            omsTestResult.setId(id);
            omsTestResult.setSampleno(testresult.getSampleno());
            omsTestResult.setDetectedPerson(testresult.getPatientname());
            omsTestResult.setSex(testresult.getSex());
            omsTestResult.setBirthday(DateUtil.StringToDate(testresult.getBirthday()));
            omsTestResult.setToponymy(testresult.getToponymy());
            omsTestResult.setTestid(testresult.getTestid());
            //样本质量2，直接不合格
            if(Integer.parseInt(testresult.getStayhospitalmode())==2){
                omsTestResult.setTestResult("invalid");
            }else{
                omsTestResult.setTestResult(testresult.getTestresult());
            }
            omsTestResult.setResultFlag(testresult.getResultflag());
            omsTestResult.setTestResult(testresult.getTestresult());
            omsTestResult.setMeasureTime(DateUtil.StringToDate(testresult.getMeasuretime()));
            omsTestResult.setOperator(testresult.getOperator());
            if(testresult.getAge()!=null && !testresult.getAge().equals("")){
                omsTestResult.setAge(Integer.parseInt(testresult.getAge()));
            }
            if(testresult.getOperatornotes()!=null && !testresult.getOperatornotes().equals("")){
                omsTestResult.setOperatorNotes(testresult.getOperatornotes());
            }
            if(testresult.getHint()!=null && !testresult.getHint().equals("")){
                omsTestResult.setHint(testresult.getHint());
            }
            if(testresult.getUnit()!=null && !testresult.getUnit().equals("")){
                omsTestResult.setUnit(testresult.getUnit());
            }
            if(testresult.getTrayno()!=null) {
                omsTestResult.setTrayno(Integer.parseInt(testresult.getTrayno()));
            }
            if(testresult.getPosno()!=null){
                omsTestResult.setPosno(Integer.parseInt(testresult.getPosno()));
            }
            omsTestResult.setReceiver(testresult.getReceiver());
            omsTestResult.setReceiveTime(DateUtil.StringToDate(testresult.getReceivetime()));
            omsTestResult.setCheckOperator(testresult.getCheckoperator());
            omsTestResult.setCheckTime(DateUtil.StringToDate(testresult.getChecktime()));
            omsTestResult.setCreateTime(new Date());

            String ybzl="";
            String R1="";
            String R2="";
            String R3="";
            if(Float.parseFloat(testresult.getStayhospitalmode())==2){
                ybzl = "不合格";
                R1 = "invalid";
                R2 = "invalid";
                R3 = "invalid";
                //新增不合格原因 add by xujingjing at 2019-01-29
                omsTestResult.setUnqualifiedReason(testresult.getImReason()+testresult.getStomachReason());
            }else
            {
                if(Float.parseFloat(testresult.getFee())==0){
                    ybzl = "合格";
                    R1=testresult.getTestresult();
                    //肠阳性 新增肠原因 add by xujingjing at 2019-01-29
                    if (Integer.parseInt(testresult.getTestresult()) >= 165){
                        omsTestResult.setImReason(testresult.getImReason());
                    }

                    R2 = "阴性";
                    R3 = "阴性";
                } else if(Float.parseFloat(testresult.getFee())==1){
                    ybzl = "合格";
                    R1=testresult.getTestresult();
                    R2 = "阳性";
                    R3 = "阳性";
                        //肠阳性 新增肠原因 add by xujingjing at 2019-01-29
                    if (Integer.parseInt(testresult.getTestresult()) >= 165){
                        omsTestResult.setImReason(testresult.getImReason());
                    }

                    //阳性 新增胃原因 add by xujingjing at 2019-01-29
                    omsTestResult.setStomachReason(testresult.getStomachReason());

                }else if(Float.parseFloat(testresult.getFee())==2){
                    ybzl = "合格";
                    R1=testresult.getTestresult();
                    //肠阳性 新增肠原因 add by xujingjing at 2019-01-29
                    if (Integer.parseInt(testresult.getTestresult()) >= 165){
                        omsTestResult.setImReason(testresult.getImReason());
                    }

                    R2 = "阳性";
                    R3 = "阴性";

                    //阳性 新增胃原因 add by xujingjing at 2019-01-29
                    omsTestResult.setStomachReason(testresult.getStomachReason());
                }
            }
            omsTestResultDao.insert(omsTestResult);
            ResultDetail resultDetail0=new ResultDetail();
            resultDetail0.setId(UUID.randomUUID().toString());
            resultDetail0.setResultId(id);
            resultDetail0.setTestItemId("E266ED49-A8E6-41B7-B8C4-2D307BC856D9");
            resultDetail0.setTestResult(R1);
            resultDetail0.setSample(ybzl);
            resultDetail0.setProductName("常卫明");
            resultDetail0.setCreateTime(new Date());

            resultDetailDao.insert(resultDetail0);

            ResultDetail resultDetail1=new ResultDetail();
            resultDetail1.setId(UUID.randomUUID().toString());
            resultDetail1.setResultId(id);
            resultDetail1.setTestItemId("9B672F67-394A-41B7-B5DA-9E1A5CF3C2ED");
            resultDetail1.setTestResult(R2);
            resultDetail1.setSample(ybzl);
            resultDetail1.setProductName("常卫明");
            resultDetail1.setCreateTime(new Date());
            //插入报告明细
            resultDetailDao.insert(resultDetail1);

            ResultDetail resultDetail2=new ResultDetail();
            resultDetail2.setId(UUID.randomUUID().toString());
            resultDetail2.setResultId(id);
            resultDetail2.setTestItemId("3FA89FD3-9E7E-431A-8F26-671B67DABC9D");
            resultDetail2.setTestResult(R3);
            resultDetail2.setSample(ybzl);
            resultDetail2.setProductName("常卫明");
            resultDetail2.setCreateTime(new Date());
            //插入报告明细
            resultDetailDao.insert(resultDetail2);

            return true;
        }catch (Exception error){
            throw new RuntimeException();
        }
    }

    public boolean insertFzqResult(List<Testresult> resultList){
        try{
            OmsTestResult omsTestResult=null;
            String id=UUID.randomUUID().toString();
            for (Testresult testresult :resultList){


                if(omsTestResult==null) {
                    omsTestResult = new OmsTestResult();
                    omsTestResult.setId(id);
                    omsTestResult.setSampleno(testresult.getSampleno());
                    omsTestResult.setDetectedPerson(testresult.getPatientname());
                    omsTestResult.setSex(testresult.getSex());
                    omsTestResult.setBirthday(DateUtil.StringToDate(testresult.getBirthday()));
                    omsTestResult.setToponymy(testresult.getToponymy());
                    omsTestResult.setTestid("");
                    omsTestResult.setTestResult("");
                    omsTestResult.setResultFlag(testresult.getResultflag());
                    omsTestResult.setTestResult(testresult.getTestresult());
                    omsTestResult.setMeasureTime(DateUtil.StringToDate(testresult.getMeasuretime()));
                    omsTestResult.setOperator(testresult.getOperator());
                    if (testresult.getAge() != null && !testresult.getAge().equals("")) {
                        omsTestResult.setAge(Integer.parseInt(testresult.getAge()));
                    }
                    if (testresult.getOperatornotes() != null && !testresult.getOperatornotes().equals("")) {
                        omsTestResult.setOperatorNotes(testresult.getOperatornotes());
                    }
                    if (testresult.getHint() != null && !testresult.getHint().equals("")) {
                        omsTestResult.setHint(testresult.getHint());
                    }
                    if (testresult.getUnit() != null && !testresult.getUnit().equals("")) {
                        omsTestResult.setUnit(testresult.getUnit());
                    }
                    if (testresult.getTrayno() != null) {
                        omsTestResult.setTrayno(Integer.parseInt(testresult.getTrayno()));
                    }
                    if (testresult.getPosno() != null) {
                        omsTestResult.setPosno(Integer.parseInt(testresult.getPosno()));
                    }
                    omsTestResult.setReceiver(testresult.getReceiver());
                    omsTestResult.setReceiveTime(DateUtil.StringToDate(testresult.getReceivetime()));
                    omsTestResult.setCheckOperator(testresult.getCheckoperator());
                    omsTestResult.setCheckTime(DateUtil.StringToDate(testresult.getChecktime()));
                    omsTestResult.setCreateTime(new Date());
                    omsTestResultDao.insert(omsTestResult);
                }
                    //插入报告表头
                    ResultDetail resultDetail=new ResultDetail();
                    resultDetail.setId(UUID.randomUUID().toString());
                    resultDetail.setResultId(id);
                    if(testresult.getTestid().equals("0014")) {
                        resultDetail.setTestItemId("4C92A21D-5F33-4DB4-999D-8DDE97903A3E");
                        resultDetail.setTestResult(testresult.getTestresult().toLowerCase());

                    }
                    if(testresult.getTestid().equals("0015")) {
                        resultDetail.setTestItemId("9F6BAA65-A7ED-46B3-95AB-165D6EB4E356");
                        resultDetail.setTestResult(testresult.getTestresult());
                    }
                    if (Float.parseFloat(testresult.getStayhospitalmode())==2) {
                        resultDetail.setSample("不正常");
                    } else {
                        resultDetail.setSample("正常");
                    }
                    resultDetail.setProductName("一次性使用痰液保存管");
                    resultDetail.setCreateTime(new Date());
                    //插入报告明细
                    resultDetailDao.insert(resultDetail);



            }


            return true;
        }catch (Exception error){
            throw new RuntimeException();
        }
    }

    public OmsTestResult selectBySampleno(String sampleno){
        return omsTestResultDao.selectBySampleno(sampleno);
    }


}
