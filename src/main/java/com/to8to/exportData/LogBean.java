package com.to8to.exportData;

public class LogBean
{

    public String  user_id          = "";
    public String  cookie_id        = "";
    public String  session_id       = "";
    public String  user_location    = "";
    public String  ip_address       = "";
    public String  os_version       = "";
    public String  os_type          = "";
    public String  product_name     = "";
    public String  product_version  = "";
    public String  user_agent       = "";
    public String  explorer_version = "";
    public Integer sp_type          = 0;
    public String  network_type     = "";
    public String  visit_time       = "";
    public String  leave_time       = "";
    public String  event_type       = "";
    public String  event_name       = "";
    public String  visit_from       = "";
    public String  visit_resouce    = "";
    public String  device_type      = "";
    public String  device_id        = "";
    public String  display_solution = "";
    public String  parent_id        = "";
    public String  current_id       = "";

    @Override
    public String toString()
    {
        return user_id + "\t" + cookie_id + "\t" + session_id + "\t"
                + user_location + "\t" + ip_address + "\t" + os_version + "\t"
                + os_type + "\t" + product_name + "\t" + product_version + "\t"
                + user_agent + "\t" + explorer_version + "\t" + sp_type + "\t"
                + network_type + "\t" + visit_time + "\t" + leave_time + "\t"
                + event_type + "\t" + event_name + "\t" + visit_from + "\t"
                + visit_resouce + "\t" + device_type + "\t" + device_id + "\t"
                + display_solution + "\t" + parent_id + "\t" + current_id;
    }

    public LogBean()
    {
        super();
    }

    public LogBean(String _id, String user_id, String cookie_id,
            String session_id, String user_location, String ip_address,
            String os_version, String os_type, String product_name,
            String product_version, String user_agent, String explorer_version,
            Integer sp_type, String network_type, String visit_time,
            String leave_time, String event_type, String event_name,
            String visit_from, String visit_resouce, String device_type,
            String device_id, String display_solution, String parent_id,
            String current_id, String visit_from_domain,
            String visit_resouce_domain, String visit_from_adress,
            String visit_resouce_adress)
    {
        super();
        this.user_id = user_id;
        this.cookie_id = cookie_id;
        this.session_id = session_id;
        this.user_location = user_location;
        this.ip_address = ip_address;
        this.os_version = os_version;
        this.os_type = os_type;
        this.product_name = product_name;
        this.product_version = product_version;
        this.user_agent = user_agent;
        this.explorer_version = explorer_version;
        this.sp_type = sp_type;
        this.network_type = network_type;
        this.visit_time = visit_time;
        this.leave_time = leave_time;
        this.event_type = event_type;
        this.event_name = event_name;
        this.visit_from = visit_from;
        this.visit_resouce = visit_resouce;
        this.device_type = device_type;
        this.device_id = device_id;
        this.display_solution = display_solution;
        this.parent_id = parent_id;
        this.current_id = current_id;
    }

    public String getUser_id()
    {
        return user_id;
    }

    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }

    public String getCookie_id()
    {
        return cookie_id;
    }

    public void setCookie_id(String cookie_id)
    {
        this.cookie_id = cookie_id;
    }

    public String getSession_id()
    {
        return session_id;
    }

    public void setSession_id(String session_id)
    {
        this.session_id = session_id;
    }

    public String getUser_location()
    {
        return user_location;
    }

    public void setUser_location(String user_location)
    {
        this.user_location = user_location;
    }

    public String getIp_address()
    {
        return ip_address;
    }

    public void setIp_address(String ip_address)
    {
        this.ip_address = ip_address;
    }

    public String getOs_version()
    {
        return os_version;
    }

    public void setOs_version(String os_version)
    {
        this.os_version = os_version;
    }

    public String getOs_type()
    {
        return os_type;
    }

    public void setOs_type(String os_type)
    {
        this.os_type = os_type;
    }

    public String getProduct_name()
    {
        return product_name;
    }

    public void setProduct_name(String product_name)
    {
        this.product_name = product_name;
    }

    public String getProduct_version()
    {
        return product_version;
    }

    public void setProduct_version(String product_version)
    {
        this.product_version = product_version;
    }

    public String getUser_agent()
    {
        return user_agent;
    }

    public void setUser_agent(String user_agent)
    {
        this.user_agent = user_agent;
    }

    public String getExplorer_version()
    {
        return explorer_version;
    }

    public void setExplorer_version(String explorer_version)
    {
        this.explorer_version = explorer_version;
    }

    public Integer getSp_type()
    {
        return sp_type;
    }

    public void setSp_type(Integer sp_type)
    {
        this.sp_type = sp_type;
    }

    public String getNetwork_type()
    {
        return network_type;
    }

    public void setNetwork_type(String network_type)
    {
        this.network_type = network_type;
    }

    public String getVisit_time()
    {
        return visit_time;
    }

    public void setVisit_time(String visit_time)
    {
        this.visit_time = visit_time;
    }

    public String getLeave_time()
    {
        return leave_time;
    }

    public void setLeave_time(String leave_time)
    {
        this.leave_time = leave_time;
    }

    public String getEvent_type()
    {
        return event_type;
    }

    public void setEvent_type(String event_type)
    {
        this.event_type = event_type;
    }

    public String getEvent_name()
    {
        return event_name;
    }

    public void setEvent_name(String event_name)
    {
        this.event_name = event_name;
    }

    public String getVisit_from()
    {
        return visit_from;
    }

    public void setVisit_from(String visit_from)
    {
        this.visit_from = visit_from;
    }

    public String getVisit_resouce()
    {
        return visit_resouce;
    }

    public void setVisit_resouce(String visit_resouce)
    {
        this.visit_resouce = visit_resouce;
    }

    public String getDevice_type()
    {
        return device_type;
    }

    public void setDevice_type(String device_type)
    {
        this.device_type = device_type;
    }

    public String getDevice_id()
    {
        return device_id;
    }

    public void setDevice_id(String device_id)
    {
        this.device_id = device_id;
    }

    public String getDisplay_solution()
    {
        return display_solution;
    }

    public void setDisplay_solution(String display_solution)
    {
        this.display_solution = display_solution;
    }

    public String getParent_id()
    {
        return parent_id;
    }

    public void setParent_id(String parent_id)
    {
        this.parent_id = parent_id;
    }

    public String getCurrent_id()
    {
        return current_id;
    }

    public void setCurrent_id(String current_id)
    {
        this.current_id = current_id;
    }

    public class Oid
    {
        String $oid;

        public Oid()
        {

        }

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
}