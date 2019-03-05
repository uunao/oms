package com.nh.oms.transform;

/**
 * Created by ccWang on 2018/12/4.
 */
public class ProductPlan {

    public ProductPlan(String package_name) throws Exception{
        if ("泰康保险50".equals(package_name)) {
            this.risk = "100000.00";
            this.premium = "50.00";
            this.plan_id = "9905001";
        } else if ("泰康保险20".equals(package_name)) {
            this.risk = "200000.00";
            this.premium = "20.00";
            this.plan_id = "9905002";
        } else {
            throw new Exception("险别代码不对");
        }
    }

    private String risk;
    private String plan_id;
    private String premium;

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }
}
