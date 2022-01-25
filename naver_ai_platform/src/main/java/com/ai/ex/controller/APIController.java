package com.ai.ex.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ai.ex.model.CelebrityVO;
import com.ai.ex.model.FaceVO;
import com.ai.ex.service.CFRCelebrityService;
import com.ai.ex.service.CFRFaceRecogService;
import com.ai.ex.service.OCRService;
import com.ai.ex.service.ObjectDetectionService;
import com.ai.ex.service.PoseEstimationService;
import com.ai.ex.service.STTService;
import com.ai.ex.service.TTSService;

@Controller
public class APIController {
	@Autowired
	private CFRCelebrityService cfrServiceCel;
	
	@Autowired
	private CFRFaceRecogService cfrRecogService;
	
	@Autowired
	private OCRService ocrService;
	
	@Autowired
	private PoseEstimationService  poseService;
	
	@Autowired
	private ObjectDetectionService objService;
	
	@Autowired
	private STTService sttService;
	
	@Autowired
	private TTSService ttsService;
	
	// index 페이지 이동
	@RequestMapping("/")
	public String Index() {
		return "index";
	}
	
	
	@RequestMapping("/faceRecogCelForm")
	public String faceRecogCelForm() {
		return "celebrityView";
	}
	
	//(1) 유명인 얼굴인식 API 호출: 결과를 콘솔에 출력
	//변경
	//(2)유명인 얼굴인식 API 호출결과 CelebrityVO 리스트 받아서
	//Model에 담아 celebrityView페이지 전달
	@RequestMapping("/faceRecogCel")
	public String faceRecogCel(@RequestParam("uploadFile")MultipartFile file, 
										Model model) throws IOException {
		
		//1.파일 저장 경로 설정: 실제 서비스되는 위치로 (프로젝트 외부에 저장)
		String uploadPath = "C:/upload/";
		
		//2. 원본 파일 이름 알아오기
		String originalFileName = file.getOriginalFilename();
		String filePathName = uploadPath + originalFileName;
		
		//3. 파일 생성
		File file1 = new File(uploadPath + originalFileName);
		
		//4.서버로 전송
		file.transferTo(file1);
		
		
	
		ArrayList<CelebrityVO> celList = new ArrayList<CelebrityVO>();
		
		//서비스에 파일 path와 파일명 전달 -> 서비스 메소드에서 변결
		//서비스에서 CelebrityVO 리스트 저장
		celList = cfrServiceCel.clovaFaceRecogCel(filePathName);
		
		//Model에 "celList" 이름으로저장 -> view 페이지로 전달
		model.addAttribute("celList", celList);
		model.addAttribute("fileName", originalFileName);
		
		return "celebrityView";
	}
	
		
	//얼굴 감지 뷰페이지 이동
	@RequestMapping("/faceRecogForm")
	public String faceRecogForm() {
		return "faceRecogView";
	}

	
	//(1)얼굴 감지 API 호출: 결과를 콘솔에 출력
	@RequestMapping("/faceRecog")
	public String faceRecog(@RequestParam("uploadFile")MultipartFile file, 
										Model model) throws IOException{

		//1.파일 저장 경로 설정: 실제 서비스되는 위치로 (프로젝트 외부에 저장)
		String uploadPath = "C:/upload/";
		
		//2. 원본 파일 이름 알아오기
		String originalFileName = file.getOriginalFilename();
		String filePathName = uploadPath + originalFileName;
		
		//3. 파일 생성
		File file1 = new File(uploadPath + originalFileName);
		
		//4.서버로 전송
		file.transferTo(file1);
		
		
		ArrayList<FaceVO> faceList = new ArrayList<FaceVO>();
		
		//서비스에 파일 path와 파일명 전달 -> 서비스 메소드에서 변결
		//서비스에서 CelebrityVO 리스트 저장
		faceList = 	cfrRecogService.faceRecog(filePathName);
		
		//Model에 "celList" 이름으로저장 -> view 페이지로 전달
		model.addAttribute("faceList", faceList);
		model.addAttribute("fileName", originalFileName);
		
		return "faceRecogView";
		
		
	}
	
	
		//OCR 
		/*
		 * // (1) OCR API 서비스 호출 결과를 콘솔에 출력
		 * 
		 * @RequestMapping("/clovaOCR") public void clovaOCR() {
		 * ocrService.clovaOCRService();
		 * 
		 * }
		 */


		// 변경 ->
		// (2) ocrView.jsp 페이지로 이동
		@RequestMapping("/clovaOCRForm")
		public String clovaOCRForm() {
			return "ocrView";
		}
		
		/*
		 * //포즈인식: 콘솔에 출력
		 * 
		 * @RequestMapping("/pose") public void Pose() { poseService.PoseEstimation(); }
		 */
		//포즈인식  뷰페이지 이동
		@RequestMapping("/poseForm")
		public String poseForm() {
			return "poseView";
		}
		/*
		 * //(1)객체탐지: 콘솔에 등록
		 * 
		 * @RequestMapping("/objectDetect") public void objectDetect() {
		 * objService.objectDetect(); }
		 */
		
		//(2) View 페이지 이동
		 @RequestMapping("/objectForm")
		 public String objectForm() {
			 return "objView";
		 }
		 
			/*
			 * //(1) STT : 콘솔에 출력
			 * 
			 * @RequestMapping("/clovaSTT") public void clovaSTT() {
			 * sttService.clovaSpeechToText();
			 * 
			 * }
			 */
		 
		 //(2) STT 
		 @RequestMapping("/sttForm")
			public String clovaForm() {
				return "/sttView";
				
			}
		 
		 //(2) STT : 언어 선택
		 @RequestMapping("/sttForm2")
			public String clovaForm2() {
				return "/sttView2";
				
			}
		 
			
			  //텍스트를 음성 파일로 변환 // (1) 결과를 c:/upload폴더에 mp3로 저장
			  
			@RequestMapping("/clovaTTSForm")
			public void clovaTTS() {
					ttsService.clovaTextToSpeech();
			 
			 }
					 
		//텍스트를 음성 파일로 변환
		 // (1) 결과를 c:/upload폴더에 mp3로 저장
		 @RequestMapping("/ttsForm")
			public String ttsForm() {
				return "/ttsView";
			}
}
