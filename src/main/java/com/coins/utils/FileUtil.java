package com.coins.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class FileUtil {
	protected static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	private static final String delFileName = "deltemp/";
	private static final int delNum = 30;

	/**
	 * 更新菜谱资源时，需要清理老资源
	 * 
	 * @param sourceFilePath
	 * @param filename
	 * @return
	 */
	public static boolean cleanFileByName(String sourceFilePath, String filename) {
		File targetF = new File(sourceFilePath + filename);
		if (targetF.exists() == false) {
			logger.error("待清理的文件目录：" + targetF + "不存在.");
		} else {
			targetF.delete();
		}
		return true;
	}

	/**
	 * 先在当前目录建一个deltemp 文件夹
	 * 将需要删除的文件拷贝到该文件夹下
	 * 在下次删除时先删除该文件夹下文件，在拷贝需要删除的文件到此文件夹下。
	 * 如果线上项目发现删除文件错误，在一星期之内，我可以进行找回。
	 * @param sourceFilePath
	 * @param fileNames
	 * @return
	 */
	public synchronized static boolean rmFile(String sourceFilePath, Set<String> fileNames) {
		boolean flag = false;
		File rmFile = new File(sourceFilePath + delFileName);
		if (rmFile.exists()) {
			File[] listFiles = rmFile.listFiles();
			if(null != listFiles && listFiles.length>0) {
				for(File f:listFiles) {
					f.delete();
				}
			}
		} else {// 不存在则创建
			rmFile.mkdirs();
		}
		File sourceFile = new File(sourceFilePath);
		if (null == sourceFile || sourceFile.exists() == false || sourceFile.listFiles().length < 1) {
			logger.error("该目录无待整理文件");
			return flag;
		}

		// ===开始处理
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;

		File[] listFiles = sourceFile.listFiles();
		String fName = "";
		int currDel = 0;
		for (File file : listFiles) {// 遍历所有文件
			fName = file.getName();
			if (delFileName.contains(fName))
				continue;
			currDel++;
			if (currDel > delNum)
				break;// 删够500个退出
			if (fileNames.contains(fName))
				continue;// 如果存在，则重新循环
			try {
				fis = new FileInputStream(file);
				fos = new FileOutputStream(sourceFilePath + delFileName + fName);
				bis = new BufferedInputStream(fis);
				bos = new BufferedOutputStream(fos);

				// 缓冲数组
				byte[] b = new byte[1024 * 5];
				int len = 0;
				while ((len = bis.read(b)) != -1) {
					bos.write(b, 0, len);
				}
				// 刷新此缓冲的输出流
				bos.flush();
			} catch (FileNotFoundException e) {
				logger.error("文件清理时.." + fName + "文件或清理文件目录不存在.");
			} catch (IOException e) {
				logger.error("文件清理时.." + fName + "文件删除出错..");
			} finally {
				try {
					if (null != fis)
						fis.close();
					if (null != fos)
						fos.close();
					if (null != bis)
						bis.close();
					if (null != bos)
						bos.close();
					flag = true;
				} catch (IOException e) {
					logger.error("文件流关闭出错..");
				}
			}
			file.delete();
		}
		return flag;
	}

	/**
	 * 文件压缩
	 * 
	 * @param sourceFilePath
	 * @param zipFilePath
	 * @param fileName
	 * @param menuFileNames
	 * @return
	 */
	public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName,
			Set<String> menuFileNames) {
		boolean flag = false;
		File sourceFile = new File(sourceFilePath);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;

		if (sourceFile.exists() == false) {
			logger.error("待压缩的文件目录：" + sourceFilePath + "不存在.");
		} else {
			try {
				File zipFile = new File(zipFilePath + fileName + ".zip");
				if (zipFile.exists()) {
					logger.error(zipFilePath + "目录下存在名字为:" + fileName + ".zip" + "打包文件.");
				} else {
					File[] sourceFiles = sourceFile.listFiles();
					if (null == sourceFiles || sourceFiles.length < 1) {
						logger.error("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
					} else {
						fos = new FileOutputStream(zipFile);
						zos = new ZipOutputStream(new BufferedOutputStream(fos));
						byte[] bufs = new byte[1024 * 10];
						for (int i = 0; i < sourceFiles.length; i++) {
							File file = sourceFiles[i];
							if (!StringUtils.isEmpty(file.getName()) && !menuFileNames.contains(file.getName()))
								continue;
							// 创建ZIP实体，并添加进压缩包
							ZipEntry zipEntry = new ZipEntry(file.getName());
							zos.putNextEntry(zipEntry);
							// 读取待压缩的文件并写进压缩包里
							fis = new FileInputStream(file);
							bis = new BufferedInputStream(fis, 1024 * 10);
							int read = 0;
							while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
								zos.write(bufs, 0, read);
							}
						}
						flag = true;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				// 关闭流
				try {
					if (null != bis)
						bis.close();
					if (null != zos)
						zos.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		return flag;
	}
}
