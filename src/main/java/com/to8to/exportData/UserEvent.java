package com.to8to.exportData;

public class UserEvent
{
    public String vt="";
    public String  et="";
    public String en="";
    public String vr="";
    public String ci="";

    public String getVt()
    {
        if(vt.equals("")||vt==null)
            vt="";
        return vt;
    }

    public void setVt(String vt)
    {
        this.vt = vt;
    }

    public String getEt()
    {
        if(et.equals("")||et==null)
            et="";
        return et;
    }

    public void setEt(String et)
    {
        this.et = et;
    }

    public String getEn()
    {
        if(en.equals("")||en==null)
            en="";
        return en;
    }

    public void setEn(String en)
    {
        this.en = en;
    }

    public String getVr()
    {
        if(vr.equals("")||vr==null)
            vr="";
        return vr;
    }

    public void setVr(String vr)
    {
        this.vr = vr;
    }

    public String getCi()
    {
        if(ci.equals("")||ci==null)
            ci="";
        return ci;
    }

    public void setCi(String ci)
    {
        this.ci = ci;
    }

    @Override
    public String toString()
    {
        return "UserEvent [vt=" + vt + ", et=" + et + ", en=" + en + ", vr="
                + vr + ", ci=" + ci + "]";
    }
}
