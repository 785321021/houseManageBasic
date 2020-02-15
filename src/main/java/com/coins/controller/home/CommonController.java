package com.coins.controller.home;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.coins.configure.jwt.SkipToken;
import com.coins.entity.resp.RespCode;
import com.coins.entity.resp.RespEntity;
import com.coins.service.impl.RolesServiceImpl;
import com.google.common.collect.Maps;

/**
 */
@Controller
@RequestMapping(value = "/c-api/common")
public class CommonController extends BaseController {
	@Value("${juqiimg.url:}")
	private String imgUrl;
	@Value("${juqiimg.path:}")
	private String imgPath;
	@Autowired
	private RolesServiceImpl roleService;

//	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
	@PostMapping(value = "/upload")
	@ResponseBody
	@SkipToken
	public RespEntity uploadFile(HttpServletRequest request, @RequestParam MultipartFile file) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		// 获得原始文件名
		String fileName = file.getOriginalFilename();
		// 新文件名
		String newFileName = String.valueOf(UUID.randomUUID()).replaceAll("-", "")
				.concat(fileName.substring(fileName.lastIndexOf("."), fileName.length()));
		// 上传位置
		File f = new File(imgPath);
		if (!f.exists())
			f.mkdirs();
		if (!file.isEmpty()) {
			try {
				// 2019-05-23 新需求，上传的图片压缩至1024*1366 又改回去了。
//				String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
//				InputStream in = file.getInputStream();
//				if(suffix.toLowerCase().equals(".mp4")){

//					FileOutputStream fos = new FileOutputStream(imgPath + newFileName);
//					int b = 0;
//					while ((b = in.read()) != -1) {
//						fos.write(b);
//					}
//					fos.close();
//					in.close();
//				}else {
//					BufferedImage bim = ImageIO.read(in);
//		            int srcWidth = bim.getWidth();
//		            int srcHeight = bim.getHeight();
//		            ArrayList l = new ArrayList();
//		            l.add(bim);
//		            if(srcWidth<1024&& srcHeight <1366) {
//		            	Thumbnails.fromImages(l).outputQuality(1.0).size(srcWidth,srcHeight).toFile(imgPath+newFileName);
//		            }else {
//		            	Thumbnails.fromImages(l).outputQuality(1.0).size(1024, 1366).toFile(imgPath+newFileName);
////		            	Thumbnails.fromImages(l).size(1024,1366).toFile(imgPath+newFileName);
//		            }
//				}
				// 老代码
				InputStream in = file.getInputStream();
				FileOutputStream fos = new FileOutputStream(imgPath + newFileName);
				int b = 0;
				while ((b = in.read()) != -1) {
					fos.write(b);
				}
				fos.close();
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//        String url=request.getRequestURL().toString().replaceFirst(request.getRequestURI().toString(), "/fileDir/");
		// 这里需要前台将传回的数据传到相应的entity 的相应字段的位置上

		map.put("data", imgUrl.concat(newFileName));
		map.put("filename", newFileName);
		return new RespEntity(RespCode.SUCCESS, map);
	}
}
