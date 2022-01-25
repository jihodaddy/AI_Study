package com.ai.ex.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class STTService {
	public String clovaSpeechToText(String filePathName) {
		
		 String clientId = "4g8kao9yy6";             // Application Client ID";
	        String clientSecret = "deJyJETWLxjhXYCP5mHjOdpFoK0qgPZ3UsPQpJDM";     // Application Client Secret";
	        String result= "";
	        
	        try {
	            String imgFile = filePathName;
	            File voiceFile = new File(imgFile);

	            String language = "Kor";        // 언어 코드 ( Kor, Jpn, Eng, Chn )
	            String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;
	            URL url = new URL(apiURL);

	            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	            conn.setUseCaches(false);
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setRequestProperty("Content-Type", "application/octet-stream");
	            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
	            conn.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

	            OutputStream outputStream = conn.getOutputStream();
	            FileInputStream inputStream = new FileInputStream(voiceFile);
	            byte[] buffer = new byte[4096];
	            int bytesRead = -1;
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	            outputStream.flush();
	            inputStream.close();
	            BufferedReader br = null;
	            int responseCode = conn.getResponseCode();
	            if(responseCode == 200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            } else {  // 오류 발생
	                System.out.println("error!!!!!!! responseCode= " + responseCode);
	                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            }
	            String inputLine;

	            if(br != null) {
	                StringBuffer response = new StringBuffer();
	                while ((inputLine = br.readLine()) != null) {
	                    response.append(inputLine);
	                }
	                br.close();
	                System.out.println(response.toString());
	                
	                result= jsonToString(response.toString()); //JSON문자열에서 테스트로 추출
	                resultToFileSave(result); //파일로 저장
	                
	            } else {
	                System.out.println("error !!!");
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        
	        return result;
	}
	
	public String jsonToString(String jsonResultStr) {
		String resultText = "";
		
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonResultStr);
			
			resultText=(String)jsonObj.get("text");
			System.out.println(resultText.toString());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		return resultText;
	}
	
	//음성 파일에서 추출한 텍스트를 txt파일로 저장
	public void resultToFileSave(String result) {
		try {
			String fileName = Long.valueOf(new Date().getTime()).toString();
			String filePathName = "c:/upload/" + "stt_" + fileName + ".txt"; 
					
			FileWriter fw = new FileWriter(filePathName);
			fw.write(result);
			fw.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// STT language 언어 선택
	public String clovaSpeechToText2(String filePathName, String language) {
		
		 String clientId = "";             // Application Client ID";
	        String clientSecret = "";     // Application Client Secret";
	        String result= "";
	        
	        try {
	            String imgFile = filePathName;
	            File voiceFile = new File(imgFile);

	            //String language = lang;        // 언어 코드 ( Kor, Jpn, Eng, Chn )
	            String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;
	            URL url = new URL(apiURL);

	            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	            conn.setUseCaches(false);
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setRequestProperty("Content-Type", "application/octet-stream");
	            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
	            conn.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

	            OutputStream outputStream = conn.getOutputStream();
	            FileInputStream inputStream = new FileInputStream(voiceFile);
	            byte[] buffer = new byte[4096];
	            int bytesRead = -1;
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	            outputStream.flush();
	            inputStream.close();
	            BufferedReader br = null;
	            int responseCode = conn.getResponseCode();
	            if(responseCode == 200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            } else {  // 오류 발생
	                System.out.println("error!!!!!!! responseCode= " + responseCode);
	                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            }
	            String inputLine;

	            if(br != null) {
	                StringBuffer response = new StringBuffer();
	                while ((inputLine = br.readLine()) != null) {
	                    response.append(inputLine);
	                }
	                br.close();
	                System.out.println(response.toString());
	                
	                result= jsonToString(response.toString()); //JSON문자열에서 테스트로 추출
	                resultToFileSave(result); //파일로 저장
	                
	            } else {
	                System.out.println("error !!!");
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        
	        return result;
	
	}
}
