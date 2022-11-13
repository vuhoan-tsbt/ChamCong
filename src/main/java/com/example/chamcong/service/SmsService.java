package com.example.chamcong.service;

import lombok.Data;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Data
@Service
public class SmsService {
    private String apiKey = "6D8538C7425401B9BA68D147740148";
    private String secretKey = "E9BF46521ABA2315AE94966E28358C";

    public boolean sendOTP(String phone, String otp) {
        RestTemplate restTemplate = new RestTemplate();
        SendOTPResponse result = restTemplate.getForObject(buildVoiceOTPUrl(phone, otp), SendOTPResponse.class);
        return result.isSuccess();

    }

    private String buildVoiceOTPUrl(String phone, String otp) {
        return String.format("https://voiceapi.esms.vn/MainService.svc/json/VoiceOTP" +
                "?ApiKey=%s&SecretKey=%s&Phone=%s&Code=%s&Speed=1&Voice=hatieumai", apiKey, secretKey, phone, otp);
    }
    @Setter
    public static class SendOTPResponse {
        private String CodeResult;
        private String SMSID;


        public String getSMSID() {
            return SMSID;
        }

        public boolean isSuccess() {
            return "100".equals(CodeResult); //CodeResult.equals("100");
        }
    }
}
