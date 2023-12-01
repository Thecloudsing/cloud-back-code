package org.example.service;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;
import org.example.agreement.TypeProcessing;
import org.example.data.CodeData;
import org.example.flush.ValidationReturns;
import org.example.mapper.UserAuthentication;
import org.example.mapper.UserInterface;
import org.example.pojo.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service("Register")
public class Register implements TypeProcessing {
    @Override
    public void main(StreamingMedia streamingMedia) throws IOException {
        this.register(streamingMedia);
    }

    @Autowired
    private CodeData codeData;
    @Autowired
    private UserInterface userInterface;
    @Autowired
    private UserAuthentication userAuthentication;
    private void register(StreamingMedia streamingMedia) throws IOException {
        Authentication authentication = streamingMedia.getAuthentication();
        String code = authentication.getCode();
        String username = streamingMedia.getUsername();
        String account = authentication.getAccount();
        String password = authentication.getPassword();
        if (this.codeData.verificationRegisterCode(account, code)) {
            String uuid = UUID.randomUUID().toString();
            this.userInterface.addUser(uuid, username);
            org.example.pojo.UserInterface user = this.userInterface.getUser_uuid(uuid);
            this.userAuthentication.addUser(user.getId(), account, password);
            new ValidationReturns(streamingMedia.getSocketValue()).registerStatus(TCP_Type_Client.REGISTER_SUCCESS,null);
        } else
            new ValidationReturns(streamingMedia.getSocketValue()).registerStatus(TCP_Type_Client.REGISTER_CODE_FAILURE, null);
    }
}
