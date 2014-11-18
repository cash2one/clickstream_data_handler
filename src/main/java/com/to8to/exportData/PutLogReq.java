package com.to8to.exportData;

import java.util.List;

public class PutLogReq
{
    public Oid             _id;
    public String          uid="";
    public String          cid="";
    public String          sid="";
    public String          ul="";
    public String          ip="";
    public String          osv="";
    public String          ost="";
    public String          pn="";
    public String          pv="";
    public String          ua="";
    public String          ev="";
    public Integer          st=0;
    public String          nt="";
    public String          dt="";
    public String          di="";
    public String          ds="";
    public String          lt="";
    public String          vf="";
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
        if(uid.equals("")||uid==null)
            uid="";
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getCid()
    {
        if(cid.equals("")||cid==null)
            cid="";
        return cid;
    }

    public void setCid(String cid)
    {
        this.cid = cid;
    }

    public String getSid()
    {
        if(sid.equals("")||sid==null)
            sid="";
        return sid;
    }

    public void setSid(String sid)
    {
        this.sid = sid;
    }

    public String getUl()
    {
        if(ul.equals("")||ul==null)
            ul="";
        return ul;
    }

    public void setUl(String ul)
    {
        this.ul = ul;
    }

    public String getIp()
    {
        if(ip.equals("")||ip==null)
            ip="";
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getOsv()
    {
        if(osv.equals("")||osv==null)
            osv="";
        return osv;
    }

    public void setOsv(String osv)
    {
        this.osv = osv;
    }

    public String getOst()
    {
        if(ost.equals("")||ost==null)
            ost="";
        return ost;
    }

    public void setOst(String ost)
    {
        this.ost = ost;
    }

    public String getPn()
    {
        if(pn.equals("")||pn==null)
            pn="";
        return pn;
    }

    public void setPn(String pn)
    {
        this.pn = pn;
    }

    public String getPv()
    {
        if(pv.equals("")||pv==null)
            pv="";
        return pv;
    }

    public void setPv(String pv)
    {
        this.pv = pv;
    }

    public String getUa()
    {
        if(ua.equals("")||ua==null)
            ua="";
        return ua;
    }

    public void setUa(String ua)
    {
        this.ua = ua;
    }

    public String getEv()
    {
        if(ev.equals("")||ev==null)
            ev="";
        return ev;
    }

    public void setEv(String ev)
    {
        this.ev = ev;
    }

    public Integer getSt()
    {
        if(st==null)
            st=0;
        return st;
    }

    public void setSt(Integer st)
    {
        this.st = st;
    }

    public String getNt()
    {
        if(nt.equals("")||nt==null)
            nt="";
        return nt;
    }

    public void setNt(String nt)
    {
        this.nt = nt;
    }

    public String getDt()
    {
        if(dt.equals("")||dt==null)
            dt="";
        return dt;
    }

    public void setDt(String dt)
    {
        this.dt = dt;
    }

    public String getDi()
    {
        if(di.equals("")||di==null)
            di="";
        return di;
    }

    public void setDi(String di)
    {
        this.di = di;
    }

    public String getDs()
    {
        if(ds.equals("")||ds==null)
            ds="";
        return ds;
    }

    public void setDs(String ds)
    {
        this.ds = ds;
    }

    public String getLt()
    {
        if(lt.equals("")||lt==null)
            lt="";
        return lt;
    }

    public void setLt(String lt)
    {
        this.lt = lt;
    }

    public String getVf()
    {
        if(vf.equals("")||vf==null)
            vf="";
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
