package com.to8to.exportData;

import java.util.List;

public class PutLogReq
{
    public Oid             _id;
    public String          uid;
    public String          cid;
    public String          sid;
    public String          ul;
    public String          ip;
    public String          osv;
    public String          ost;
    public String          pn;
    public String          pv;
    public String          ua;
    public String          ev;
    public String          st;
    public String          nt;
    public String          dt;
    public String          di;
    public String          ds;
    public String          lt;
    public String          vf;
    public List<UserEvent> e;

    public class Oid
    {
        String $oid;

        public String get$oid()
        {
            return $oid;
        }

        public void set$oid(String $oid)
        {
            this.$oid = $oid;
        }

        @Override
        public String toString()
        {
            return "Oid [$oid=" + $oid + "]";
        }
    }

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getCid()
    {
        return cid;
    }

    public void setCid(String cid)
    {
        this.cid = cid;
    }

    public String getSid()
    {
        return sid;
    }

    public void setSid(String sid)
    {
        this.sid = sid;
    }

    public String getUl()
    {
        return ul;
    }

    public void setUl(String ul)
    {
        this.ul = ul;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getOsv()
    {
        return osv;
    }

    public void setOsv(String osv)
    {
        this.osv = osv;
    }

    public String getOst()
    {
        return ost;
    }

    public void setOst(String ost)
    {
        this.ost = ost;
    }

    public String getPn()
    {
        return pn;
    }

    public void setPn(String pn)
    {
        this.pn = pn;
    }

    public String getPv()
    {
        return pv;
    }

    public void setPv(String pv)
    {
        this.pv = pv;
    }

    public String getUa()
    {
        return ua;
    }

    public void setUa(String ua)
    {
        this.ua = ua;
    }

    public String getEv()
    {
        return ev;
    }

    public void setEv(String ev)
    {
        this.ev = ev;
    }

    public String getSt()
    {
        return st;
    }

    public void setSt(String st)
    {
        this.st = st;
    }

    public String getNt()
    {
        return nt;
    }

    public void setNt(String nt)
    {
        this.nt = nt;
    }

    public String getDt()
    {
        return dt;
    }

    public void setDt(String dt)
    {
        this.dt = dt;
    }

    public String getDi()
    {
        return di;
    }

    public void setDi(String di)
    {
        this.di = di;
    }

    public String getDs()
    {
        return ds;
    }

    public void setDs(String ds)
    {
        this.ds = ds;
    }

    public String getLt()
    {
        return lt;
    }

    public void setLt(String lt)
    {
        this.lt = lt;
    }

    public String getVf()
    {
        return vf;
    }

    public void setVf(String vf)
    {
        this.vf = vf;
    }

    public List<UserEvent> getE()
    {
        return e;
    }

    public void setE(List<UserEvent> e)
    {
        this.e = e;
    }

    @Override
    public String toString()
    {
        return "PutLogReq [_id=" + _id.get$oid() + ", uid=" + uid + ", ut="
                + cid + ", sid=" + sid + ", ul=" + ul + ", ip=" + ip + ", osv="
                + osv + ", ost=" + ost + ", pn=" + pn + ", pv=" + pv + ", ua="
                + ua + ", ev=" + ev + ", st=" + st + ", nt=" + nt + ", dt="
                + dt + ", di=" + di + ", ds=" + ds + ", lt=" + lt + ", vf="
                + vf + ", e=" + e + "]";
    }
}
