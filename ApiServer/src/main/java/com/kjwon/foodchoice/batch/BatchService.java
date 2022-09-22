package com.kjwon.foodchoice.batch;

import com.kjwon.foodchoice.mapper.RegisterKeywordMapper;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.kjwon.foodchoice.util.FunctionUtil.getConsonantVowel;

@Service
public class BatchService {

    private final RegisterKeywordMapper registerKeywordMapper;

    BatchService(RegisterKeywordMapper registerKeywordMapper){
        this.registerKeywordMapper = registerKeywordMapper;
    }
    private boolean kewordDbSetting = false;

    @Scheduled(fixedRate  = 10000) // 1초마다 실행
    public void run() {
        if(!kewordDbSetting) {
            keywordSetting("C:\\kjwon\\Project\\Spring boot\\foodChoice\\src\\main\\resources\\data\\station\\line1-8.csv");
            kewordDbSetting = true;
        }
    }

    public void keywordSetting(String path){
        File csv = new File(path);
        BufferedReader br = null;
        String line = "";
        int idx = 0;
        try {
            br = new BufferedReader(new FileReader(csv));
            while ((line = br.readLine()) != null) {
                idx++;
                if(idx == 1){
                    continue;
                }
                String[] lineArr = line.split(",");
                String keyword = getConsonantVowel(lineArr[3]);

                if(registerKeywordMapper.findKeyword(keyword).isEmpty())
                    registerKeywordMapper.insertKeyword(lineArr[3], keyword, Double.parseDouble(lineArr[5]), Double.parseDouble(lineArr[4]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (br != null) {
                    br.close(); // 사용 후 BufferedReader를 닫아준다.
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
