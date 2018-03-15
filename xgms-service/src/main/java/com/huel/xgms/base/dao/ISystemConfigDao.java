package com.huel.xgms.base.dao;

import com.huel.xgms.system.bean.SystemConfig;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.Result;
import org.jfaster.mango.annotation.Results;
import org.jfaster.mango.annotation.SQL;

import java.util.List;

/**
 * 数据库配置数据层操作
 * @author wsq
 * @date 2018/3/13
 */
@DB(
        table = "t_xgms_system_config"
)
@Results({
        @Result(column = "c_config_id", property = "configId"),
        @Result(column = "c_config_code", property = "configCode"),
        @Result(column = "c_config_name", property = "configName"),
        @Result(column = "c_note", property = "note"),
        @Result(column = "c_value", property = "value"),
        @Result(column = "c_type", property = "type"),
})
public interface ISystemConfigDao {
    String COLUMNS = "c_config_id, c_config_name, c_config_code, c_value, c_note, c_type";

    /**
     * 根据配置类型 获取相关配置列表
     * @param type
     * @return
     */
    @SQL("select " + COLUMNS + " from #table where c_type = :1 ")
    List<SystemConfig> getByType(String type);

    /**
     * 通过配置id获取配置信息
     * @param id
     * @return
     */
    @SQL("select " + COLUMNS + " from #table where c_config_id = :1")
    SystemConfig getById(String id);

    /**
     * 通过Code 获取系统配置
     * @param code
     * @return
     */
    @SQL("select " + COLUMNS + " from #table where c_config_code = :1 ")
    SystemConfig getByCode(String code);

    /**
     * 更新系统配置信息
     * @param code
     * @param value
     */
    @SQL("update #table set c_value = :2 where c_note = :1")
    void updateCodeValue(String code, String value);

    @SQL("update #table set c_config_name = :1.configName, c_value = :1.value, "
            + " c_note = :1.note where c_config_id = :1.configId")
    void update(SystemConfig systemConfig);

    /**
     * 保存系统配置
     * @param systemConfig
     */
    @SQL("insert into #table (" + COLUMNS + ") "
            + " values "
            + " ( :1.configId, :1.configName, :1.configCode, :1.value, :1.note, :1.type)")
    void save(SystemConfig systemConfig);
}
