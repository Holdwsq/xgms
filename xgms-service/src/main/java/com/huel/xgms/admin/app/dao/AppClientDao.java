package com.huel.xgms.admin.app.dao;

import com.huel.xgms.admin.app.bean.AppClient;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.Result;
import org.jfaster.mango.annotation.Results;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.plugin.page.Page;

import java.util.List;

@DB(
        table = "t_appclient"
)
@Results({
        @Result(column = "n_id", property = "id"),
        @Result(column = "n_ver_code", property = "versionCode"),
        @Result(column = "c_ver_name", property = "versionName"),
        @Result(column = "n_date", property = "date"),
        @Result(column = "c_url", property = "url"),
        @Result(column = "c_desc", property = "description")
})
public interface AppClientDao {
    String COLUMNS = "n_id, n_ver_code, c_ver_name, n_date, c_url, c_desc";

    @SQL("insert into #table(" + COLUMNS + ") values(:id, :versionCode, :versionName, :date, :url, :description)")
    void addAppClient(AppClient appClient);

    @SQL("select " + COLUMNS + " from #table where n_id = :1")
    AppClient getAppClient(int id);

    @SQL("update #table set n_ver_code=:versionCode, c_ver_name=:versionName, n_date=:date, c_url=:url, c_desc=:description where n_id = :id")
    boolean updateAppClient(AppClient appClient);

    @SQL("delete from #table where n_id = :1")
    boolean deleteAppClient(int id);

    @SQL("select " + COLUMNS + " from #table where n_ver_code > :1 order by n_ver_code desc")
	AppClient queryUpgrade(Integer versionCode, Page page);
    
    @SQL("select " + COLUMNS + " from #table order by n_date desc")
    List<AppClient> query(Page page);
}