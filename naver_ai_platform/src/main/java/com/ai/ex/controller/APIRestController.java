package com.ai.ex.controller;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ai.ex.model.ObjectVO;
import com.ai.ex.model.PoseVO;
import com.ai.ex.service.OCRService;
import com.ai.ex.service.ObjectDetectionService;
import com.ai.ex.service.PoseEstimationService;
import com.ai.ex.service.STTService;
import com.ai.ex.service.TTSService;

@RestController
public class APIRestController {
	//요청 받아서 서비스 호출하고 결과 받아서 반환
	@Autowired
	OCRService ocrService;
	@Autowired
	PoseEstimationService poseService;
	@Autowired
	ObjectDetectionService objService;
	@Autowired
	STTService sttService;
	@Autowired
	TTSService ttsService;
	
	// OCR 요청 받아서 서비스 호출하고 결과 받아서 반환
		@RequestMapping("/clovaOCR")
		public String  faceRecogCel(@RequestParam("uploadFile") MultipartFile file) {		
			String result = "";
			
			try {
				// 1. 파일 저장 경로 설정 : 실제 서비스되는 위치 (프로젝트 외부에 저장)
				String uploadPath = "C:/upload/";
				
				// 2. 원본 파일 이름 알아오기
				String originalFileName = file.getOriginalFilename();
				String filePathName = uploadPath + originalFileName;
				
				// 3. 파일 생성
				File file1 = new File(filePathName);
				
				// 4. 서버로 전송
				file.transferTo(file1);				
				
				// 서비스에 파일 path와 파일명 전달  -> 서비스 메소드에서 변경
				// 서비스에서 반환된 텍스트를 result에 저장
				result = ocrService.clovaOCRService(filePathName);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return result;
		}
	
		
		// 포즈 인식
		@RequestMapping("/poseDetect")
		public ArrayList<PoseVO>  poseDetect(@RequestParam("uploadFile") MultipartFile file) {		
			
			ArrayList<PoseVO> poseList = null; 
			
			try {
				// 1. 파일 저장 경로 설정 : 실제 서비스되는 위치 (프로젝트 외부에 저장)
				String uploadPath = "C:/upload/";
				
				// 2. 원본 파일 이름 알아오기
				String originalFileName = file.getOriginalFilename();
				String filePathName = uploadPath + originalFileName;
				
				// 3. 파일 생성
				File file1 = new File(filePathName);
				
				// 4. 서버로 전송
				file.transferTo(file1);				
				
				// 서비스에 파일 path와 파일명 전달  -> 서비스 메소드에서 변경
				// 서비스에서 반환된 PoseVO 리스트 저장
				poseList = poseService.poseEstimate(filePathName);
				
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return poseList;
		}
	
	
		// 객체 인식
				@RequestMapping("/objDetect")
				public ArrayList<ObjectVO>  objDetect(@RequestParam("uploadFile") MultipartFile file) {		
					
					ArrayList<ObjectVO> objList = null; 
					
					try {
						// 1. 파일 저장 경로 설정 : 실제 서비스되는 위치 (프로젝트 외부에 저장)
						String uploadPath = "C:/upload/";
						
						// 2. 원본 파일 이름 알아오기
						String originalFileName = file.getOriginalFilename();
						String filePathName = uploadPath + originalFileName;
						
						// 3. 파일 생성
						File file1 = new File(filePathName);
						
						// 4. 서버로 전송
						file.transferTo(file1);				
						
						// 서비스에 파일 path와 파일명 전달  -> 서비스 메소드에서 변경
						// 서비스에서 반환된 ObjectVO 리스트 저장
						objList = objService.objectDetect(filePathName);
											
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return objList;
				}
				
				// 음성인식
				@RequestMapping("/clovaSTT")
				public String clovaSTT(@RequestParam("uploadFile") MultipartFile file) {		
					String result = "";
										
					try {
						// 1. 파일 저장 경로 설정 : 실제 서비스되는 위치 (프로젝트 외부에 저장)
						String uploadPath = "C:/upload/";
						
						// 2. 원본 파일 이름 알아오기
						String originalFileName = file.getOriginalFilename();
						String filePathName = uploadPath + originalFileName;
						
						// 3. 파일 생성
						File file1 = new File(filePathName);
						
						// 4. 서버로 전송
						file.transferTo(file1);				
						
						// 서비스에 파일 path와 파일명 전달  -> 서비스 메소드에서 변경
						// 서비스에서 반환값 result로 저장
						result = sttService.clovaSpeechToText(filePathName);
											
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return result;
				}
		
				
				// 음성인식: language 언어선택
				@RequestMapping("/clovaSTT2")
				public String clovaSTT2(@RequestParam("uploadFile") MultipartFile file,
													  @RequestParam("language") String language) {		
					String result = "";
										
					try {
						// 1. 파일 저장 경로 설정 : 실제 서비스되는 위치 (프로젝트 외부에 저장)
						String uploadPath = "C:/upload/";
						
						// 2. 원본 파일 이름 알아오기
						String originalFileName = file.getOriginalFilename();
						String filePathName = uploadPath + originalFileName;
						
						// 3. 파일 생성
						File file1 = new File(filePathName);
						
						// 4. 서버로 전송
						file.transferTo(file1);				
						
						// 서비스에 파일 path와 파일명 전달  -> 서비스 메소드에서 변경
						// 서비스에서 저장된 파일명 받아오기
						result = sttService.clovaSpeechToText2(filePathName, language);
								System.out.println("rest");			
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return result;
				}
				
				// TTS 텍스트를 음성파일로
				@RequestMapping("/clovaTTS")
				public String clovaTTS(@RequestParam("uploadFile") MultipartFile file,
													  @RequestParam("language") String language) {		
					String result = "";
										
					try {
						// 1. 파일 저장 경로 설정 : 실제 서비스되는 위치 (프로젝트 외부에 저장)
						String uploadPath = "C:/upload/";
						
						// 2. 원본 파일 이름 알아오기
						String originalFileName = file.getOriginalFilename();
						String filePathName = uploadPath + originalFileName;
						
						// 3. 파일 생성
						File file1 = new File(filePathName);
						
						// 4. 서버로 전송
						file.transferTo(file1);				
						
						// 서비스에 파일 path와 파일명 전달  -> 서비스 메소드에서 변경
						// 서비스에서 반환값 result로 저장
						result = ttsService.clovaTextToSpeech2(filePathName, language);
											
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return result; //저장된 음성 파일명 반환
				}
				
}
