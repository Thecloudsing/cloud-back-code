package org.example.service;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;
import org.example.agreement.TypeProcessing;
import org.example.data.CodeData;
import org.example.exception.ExceptionOutLog;
import org.example.flush.ValidationReturns;
import org.example.mapper.UserAuthentication;
import org.example.module.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("GetRegisterCode")
public class GetRegisterCode implements TypeProcessing {
    @Override
    public void main(StreamingMedia streamingMedia) throws IOException {
        String account = streamingMedia.getAuthentication().getAccount();
        String socketValue = streamingMedia.getSocketValue();
        this.getCode(account,socketValue);
    }
    @Autowired
    private SendMail sendMail;
    @Autowired
    private CodeData codeData;
    @Autowired
    private UserAuthentication authentication;
    private void getCode(String account, String socketValue) throws IOException {
        Integer uid = this.authentication.duplicateAccountChecking(account);
            if (uid == null) {
                String registerCode = codeData.addRegisterCode(account);
                sendMail.registerMail(account, registerCode);
                new ValidationReturns(socketValue).registerStatus(TCP_Type_Client.REGISTER_GET_CODE_SUCCESS, null);
            } else
                new ValidationReturns(socketValue).registerStatus(TCP_Type_Client.REGISTER_GET_CODE_FAILURE, null);

    }
}
