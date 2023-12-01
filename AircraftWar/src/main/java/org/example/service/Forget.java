package org.example.service;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;
import org.example.agreement.TypeProcessing;
import org.example.data.CodeData;
import org.example.flush.ValidationReturns;
import org.example.mapper.UserAuthentication;
import org.example.pojo.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("Forget")
public class Forget implements TypeProcessing {
    @Override
    public void main(StreamingMedia streamingMedia) throws IOException {
        this.forget(streamingMedia);
    }
    @Autowired
    private CodeData codeData;
    @Autowired
    private UserAuthentication userAuthentication;
    private void forget(StreamingMedia streamingMedia) throws IOException {
        Authentication authentication = streamingMedia.getAuthentication();
        String code = authentication.getCode();
        String account = authentication.getAccount();
        String password = authentication.getPassword();
        String socketValue = streamingMedia.getSocketValue();
        if (this.codeData.verificationForgetCode(account, code)) {
            this.userAuthentication.updateAuthenticationCode(account, password);
            new ValidationReturns(socketValue).forgetStatus(TCP_Type_Client.FORGET_SUCCESS, null);
        } else
            new ValidationReturns(socketValue).forgetStatus(TCP_Type_Client.FORGET_CODE_FAILURE, null);
    }
}
