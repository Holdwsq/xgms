package com.huel.xgms.app;


import com.huel.xgms.admin.app.bean.AppClient;
import com.huel.xgms.admin.app.service.AppClientService;
import com.huel.xgms.httpbean.ResponseEntity;
import com.huel.xgms.page.Pagination;
import com.huel.xgms.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


/**
 * @author wsq
 */
@RestController
@RequestMapping("/admin/appup")
public class AppClientController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AppClientService service;
	
	@RequestMapping("list")
	public Pagination list(int pageNum, int pageSize) {
		return this.service.list(pageNum, pageSize);
	}
	
	@RequestMapping("del/{appId}")
	public void del(@PathVariable Integer appId) {
		this.service.delete(appId);
	}
	
	@RequestMapping(value = "add", consumes = "multipart/form-data", method = RequestMethod.POST)
    public ResponseEntity add(@RequestParam("apkfile") CommonsMultipartFile apkfile, AppClient app,
							HttpServletRequest request) throws IllegalStateException, IOException{
        // 判断文件是否存在
        if (!apkfile.isEmpty()) {
        	String path = request.getSession().getServletContext().getRealPath("") 
        			+ "/" + Constants.APK_UPLOAD_PAT + "/" +
        			app.getVersionCode() + "/";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            path += apkfile.getOriginalFilename();
            log.info(path);
            
            File localFile = new File(path);
			apkfile.transferTo(localFile);
			
			app.setDate(System.currentTimeMillis());
			app.setUrl("/" + Constants.APK_UPLOAD_PAT + "/" + app.getVersionCode() +
					"/" + apkfile.getOriginalFilename());
			service.save(app);
        }
        return ResponseEntity.createError("文件为空，请重新选择");
    }
}
