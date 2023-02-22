package seung.util.java;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SFile {

	public static int is_zip(String file_path) {
		int is_zip = 0;
		ZipFile zip_file = null;
		try {
			zip_file = new ZipFile(file_path);
			is_zip = zip_file.size();
		} catch (Exception e) {
			log.error("exception=", e);
			is_zip = -1;
		} finally {
			try {
				if(zip_file != null) {
					zip_file.close();
				}
			} catch (IOException e) {
				log.error("exception=", e);
			}
		}
		return is_zip;
	}
	
	public static byte[] zip(
			List<String> file_path_list
			) {
		
		byte[] zip = null;
		
		ByteArrayOutputStream byteArrayOutputStream = null;
		ZipOutputStream zipOutputStream = null;
		try {
			
			byteArrayOutputStream = new ByteArrayOutputStream();
			zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
			
			File file = null;
			String file_name = null;
			byte[] file_data = null;
			for(String file_path : file_path_list) {
				file = new File(file_path);
				file_name = file.getName();
				file_data = FileUtils.readFileToByteArray(file);
				add_zip_entry(zipOutputStream, file_name, file_data);
			}
			
			zipOutputStream.flush();
			
		} catch (Exception e) {
			log.error("exception=", e);
		} finally {
			try {
				if(zipOutputStream != null) {
					zipOutputStream.close();
				}
				if(byteArrayOutputStream != null) {
					zip = byteArrayOutputStream.toByteArray();
					byteArrayOutputStream.close();
				}
			} catch (IOException e) {
				log.error("exception=", e);
			}
		}
		
		return zip;
	}
	
	public static int zip(
			String zip_path
			, List<String> file_path_list
			) {
		
		File zip = new File(zip_path);
		if(zip.exists()) {
			zip.delete();
		}
		
		FileOutputStream fileOutputStream = null;
		ZipOutputStream zipOutputStream = null;
		try {
			
			fileOutputStream = new FileOutputStream(zip_path);
			zipOutputStream = new ZipOutputStream(fileOutputStream);
			
			File file = null;
			String file_name = null;
			byte[] file_data = null;
			for(String file_path : file_path_list) {
				file = new File(file_path);
				file_name = file.getName();
				file_data = FileUtils.readFileToByteArray(file);
				add_zip_entry(zipOutputStream, file_name, file_data);
			}
			
			zipOutputStream.flush();
			
		} catch (Exception e) {
			log.error("exception=", e);
		} finally {
			try {
				if(zipOutputStream != null) {
					zipOutputStream.close();
				}
				if(fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
				log.error("exception=", e);
			}
		}
		
		return is_zip(zip_path);
	}
	
	public static void add_zip_entry(
			ZipOutputStream zipOutputStream
			, String file_path
			, byte[] file_data
			) throws IOException {
		
		ByteArrayInputStream byteArrayInputStream = null;
		ZipEntry zipEntry = null;
		try {
			
			byteArrayInputStream = new ByteArrayInputStream(file_data);
			zipEntry = new ZipEntry(file_path);
			zipOutputStream.putNextEntry(zipEntry);
			
			byte[] b = new byte[1024 * 4];
			int off = 0;
			int len = 0;
			while((len = byteArrayInputStream.read(b)) >= 0) {
				zipOutputStream.write(b, off, len);
			}
			
			zipOutputStream.closeEntry();
			
		} catch (FileNotFoundException e) {
			log.error("exception=", e);
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if(byteArrayInputStream != null) {
					byteArrayInputStream.close();
				}
			} catch (IOException e) {
				log.error("exception=", e);
			}
		}// end of try
		
	}
	
}
