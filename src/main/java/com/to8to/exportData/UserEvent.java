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
        return vt;
    }

    public void setVt(String vt)
    {
        this.vt = vt;
    }

    public String getEt()
    {
        return et;
    }

    public void setEt(String et)
    {
        this.et = et;
    }

    public String getEn()
    {
        return en;
    }

    public void setEn(String en)
    {
        this.en = en;
    }

    public String getVr()
    {
        return vr;
    }

    public void setVr(String vr)
    {
        this.vr = vr;
    }

    public String getCi()
    {
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
