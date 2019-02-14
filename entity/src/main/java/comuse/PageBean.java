package comuse;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {

    private String tbName;
    private String tbId="pkid";
    private String tbColumn;
    private String tbWhere;
    private String tbOrder;
    private String listurl;

    //当前页
    private int pc=1;
    //总记录
    private int tr;
    //每页记录
    private int ps=10;
    //当前页记录
    private List<T> beanList=new ArrayList<>();



    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        if(pc<=0) {
            this.pc =1;
        }
        else {
            this.pc = pc;
        }
    }

    public int getTp() {
        int tp=tr/ps;
        return tr%ps==0?tp:tp+1;
    }

    public int getTr() {
        return tr;
    }

    public void setTr(int tr) {
        if(tr<=0) {
            this.tr =0;
        }
        else {
            this.tr = tr;
        }

    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {

        if(pc<=0) {
            this.ps =10;
        }
        else {
            this.ps = ps;
        }

    }

    public List<T> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<T> beanList) {
        this.beanList = beanList;
    }

    public String getTbName() {
        return tbName;
    }

    public void setTbName(String tbName) {
        this.tbName = tbName;
    }

    public String getTbId() {
        return tbId;
    }

    public void setTbId(String tbId) {
        this.tbId = tbId;
    }

    public String getTbColumn() {
        return tbColumn;
    }

    public void setTbColumn(String tbColumn) {
        this.tbColumn = tbColumn;
    }

    public String getTbWhere() {
        return tbWhere;
    }

    public void setTbWhere(String tbWhere) {
        this.tbWhere = tbWhere;
    }

    public String getTbOrder() {
        return tbOrder;
    }

    public void setTbOrder(String tbOrder) {
        this.tbOrder = tbOrder;
    }

    public String getListurl() {
        return listurl;
    }

    public void setListurl(String listurl) {
        this.listurl = listurl;
    }

}

