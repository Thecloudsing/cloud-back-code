package org.example.flush;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;

import java.io.IOException;


public class ValidationReturns extends OutputStreaming {

    public ValidationReturns(String value) {
        super(value);
    }
    private StreamingMedia streamingMedia;
    public void loginStatus(TCP_Type_Client typeClient, StreamingMediaInterface streamingMediaInterface) throws IOException {
        if (typeClient == TCP_Type_Client.LOGIN_SUCCESS)
        {
            streamingMedia = new StreamingMedia(typeClient);
            streamingMedia.setMsg("登录成功");
        } else if (typeClient == TCP_Type_Client.LOGIN_FAILURE)
        {
            streamingMedia = new StreamingMedia(typeClient);
            streamingMedia.setMsg("登录失败，账号或密码错误");
        } else return;
        if (streamingMediaInterface != null)
            streamingMedia = streamingMediaInterface.streaming(streamingMedia);
        super.sendData(streamingMedia);
        streamingMedia = null;
    }


    public void registerStatus(TCP_Type_Client typeClient, StreamingMediaInterface streamingMediaInterface) throws IOException {
        if (typeClient == TCP_Type_Client.REGISTER_SUCCESS)
        {
            streamingMedia = new StreamingMedia(typeClient);
            streamingMedia.setMsg("注册成功，请妥善保管好账号密码");
        }
        else if (typeClient == TCP_Type_Client.REGISTER_GET_CODE_FAILURE)
        {
            streamingMedia = new StreamingMedia(typeClient);
            streamingMedia.setMsg("获取验证码失败，账号已经存在，如果忘记密码请找回");
        }
        else if (typeClient == TCP_Type_Client.REGISTER_GET_CODE_SUCCESS)
        {
            streamingMedia = new StreamingMedia(typeClient);
            streamingMedia.setMsg("获取验证码成功");
        }
        else if (typeClient == TCP_Type_Client.REGISTER_CODE_FAILURE)
        {
            streamingMedia = new StreamingMedia(typeClient);
            streamingMedia.setMsg("注册失败，验证码错误");
        }
        else return;
        if (streamingMediaInterface != null)
            streamingMedia = streamingMediaInterface.streaming(streamingMedia);
        super.sendData(streamingMedia);
        streamingMedia = null;
    }

    public void forgetStatus(TCP_Type_Client typeClient, StreamingMediaInterface streamingMediaInterface) throws IOException {
        if (typeClient == TCP_Type_Client.FORGET_SUCCESS)
        {
            streamingMedia = new StreamingMedia(typeClient);
            streamingMedia.setMsg("密码修改成功，请妥善保管好账号密码");
        }
        else if (typeClient == TCP_Type_Client.FORGET_CODE_FAILURE)
        {
            streamingMedia = new StreamingMedia(typeClient);
            streamingMedia.setMsg("验证码错误");
        }
        else  if (typeClient == TCP_Type_Client.FORGET_GET_CODE_SUCCESS)
        {
            streamingMedia = new StreamingMedia(typeClient);
            streamingMedia.setMsg("获取验证码失败，请确认账号是否注册");
        }
        else if (typeClient == TCP_Type_Client.FORGET_GET_CODE_FAILURE)
        {
            streamingMedia = new StreamingMedia(typeClient);
            streamingMedia.setMsg("获取验证码成功");
        }
        else return;
        if (streamingMediaInterface != null)
            streamingMedia = streamingMediaInterface.streaming(streamingMedia);
        super.sendData(streamingMedia);
        streamingMedia = null;
    }

    public void modifyStatus(TCP_Type_Client typeClient, StreamingMediaInterface streamingMediaInterface) throws IOException {
        if (typeClient == TCP_Type_Client.MODIFY_SUCCESS)
        {
            streamingMedia = new StreamingMedia(typeClient);
            streamingMedia.setMsg("密码修改成功，请妥善保管好账号密码");
        }
        else if (typeClient == TCP_Type_Client.MODIFY_PASSWORD_FAILURE)
        {
            streamingMedia = new StreamingMedia(typeClient);
            streamingMedia.setMsg("密码错误，请检查是否开启大小写");
        }
        else return;
        if (streamingMediaInterface != null) {
            streamingMedia = streamingMediaInterface.streaming(streamingMedia);
        }
        super.sendData(streamingMedia);
        streamingMedia = null;
    }
}
