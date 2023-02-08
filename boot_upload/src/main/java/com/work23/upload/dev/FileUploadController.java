package com.work23.upload.dev;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/uploader")
@CrossOrigin
public class FileUploadController {

	@Value("${prop.upload-folder}")
	private String UPLOAD_FOLDER;

	@RequestMapping("/singlefile")
	public Object upload(MultipartFile file) {
		if (Objects.isNull(file) || file.isEmpty()) {
			System.err.println("文件为空");
			return "文件为空，请重新上传";
		}
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
			// 如果没有files文件夹，则创建
			if (!Files.isWritable(path)) {
				Files.createDirectories(Paths.get(UPLOAD_FOLDER));
			}
			// 文件写入指定路径
			Files.write(path, bytes);
			System.out.println("文件写入成功...");
			return "文件上传成功";
		} catch (IOException e) {
			e.printStackTrace();
			return "后端异常...";
		}
	}

	@RequestMapping("/files")
	public Object uploadFiles() {
		// TODO
		return null;
	}

}
