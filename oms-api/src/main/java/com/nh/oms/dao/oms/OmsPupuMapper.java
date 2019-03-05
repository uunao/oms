package com.nh.oms.dao.oms;

import com.nh.oms.model.oms.OmsPupu;

import java.util.List;

public interface OmsPupuMapper {

    int insert(OmsPupu omsPupu);

    int updateByPupuCode(OmsPupu omsPupu);

    int selectByPupuCode(String pupuCode);

    int bulkInsertPuPu(List<OmsPupu> list);

}
