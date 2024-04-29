package com.example.demo.mapper;

import com.example.demo.bean.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysLogMapper {
    @Insert("insert into sys_log(id,username,operation,time,method,params,ip,create_time) " +
            "values(nextval('seq_sys_log'),#{username},#{operation},#{time},#{method},#{params},#{ip},#{createTime})")
    int saveSysLog(SysLog sysLog);

    @Select("select * from sys_log")
    List<SysLog> findSysLog();
}
