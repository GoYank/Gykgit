package entity;

/**
 * 项目名：BombDemo
 * 包名：entity
 * 文件名：CourierData
 * 创建者：Gyk
 * 创建时间：2018/5/15 9:27
 * 描述：  CourierData
 */

public class CourierData {
    //时间
    private String datetime;
    //状态
    private String remark;
    //地点
    private String zone;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "CourierData{" +
                "datetime='" + datetime + '\'' +
                ", remark='" + remark + '\'' +
                ", zone='" + zone + '\'' +
                '}';
    }
}
