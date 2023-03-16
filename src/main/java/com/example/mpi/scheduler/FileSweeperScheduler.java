package com.example.mpi.scheduler;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.mpi.dto.FileDto;
import com.example.mpi.mapper.FileMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileSweeperScheduler {
	
	@Value("${spring.servlet.multipart.location}")
	private String BASE_PATH;
	
	@Autowired
	private FileMapper fileMapper;
	
	@Scheduled(cron = "1 0 0 * * ?")
	//@Scheduled(cron = "0 */5 * * * *")
	public void fileSweeper() throws Exception {
		log.info(">>> FileSweeper Running Start : {}", LocalDateTime.now());
		
		List<FileDto> dbFiles = fileMapper.getFileListAll();
		
		List<String> currFiles = new ArrayList<String>();
		
		for (FileDto file : dbFiles) {
			String filePath = file.getSaveDir() + "/" + file.getUuid();
			String fileName = file.getFileName();
			
			currFiles.add(BASE_PATH + filePath + "_" + fileName);
			
			if (file.getFileType() > 0) {
				currFiles.add(BASE_PATH + filePath + "_th_" + fileName);
			}
		}
		
		LocalDate now = LocalDate.now();
		String today = now.toString();
		today = File.separator + today.replace("-", File.separator);
		
		File dir = Paths.get(BASE_PATH + today).toFile();
		File[] allFileObjects = dir.listFiles();
		
		for (File file : allFileObjects) {
			String storedFileName = file.toPath().toString();
			if (!currFiles.contains(storedFileName)) {
				file.delete();
				log.info(">>> delete : {}", storedFileName);
			}
		}
		log.info(">>> FileSweeper Running Finish : {}", LocalDateTime.now());
	}
}
