package org.example.agreement;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public class AgreementUDP {

    /*
    *4

     */
    @Test
    public void test() {
        byte[] bytes = "123456789方法111".getBytes(StandardCharsets.UTF_8);
        char aa = '方';
        char bb = 'o';
        int cc = 0x456;
        int dd = 0456;
        int ee = 55555555;
        int hh = ee >> 8;
        int h = ee / 128;
        int ff = ee & 255;
        int gg = (hh << 8) + ff;
        System.out.println(cc);
        System.out.println(dd);
        System.out.println(ee);
        System.out.println(ff);
        System.out.println("gg="+gg);
        System.out.println(hh);
        System.out.println((int)aa);
        System.out.println((int)bb);
        System.out.println(Arrays.toString(bytes));
        System.out.println(new String(bytes));
        Integer q = 44;
        System.out.println(q.byteValue());
        System.out.println(Arrays.toString("123".getBytes()));
        String pp = "uiweghfuiweguifgweouigfuioew";
        int[] ints = new int[1];
        ints[0] = pp.hashCode();
        org.example.agreement.UDPMessage udpMessage = new UDPMessage(hh, ff, 1, ints);
        String s = JSONObject.toJSON(udpMessage).toString();
        System.out.println(s);
    }

    private int inter(int i) {
        return i;
    }

    private byte[] copyBytes(byte[] src, int srcBegin, int srcEnd, byte[] dst, int dstBegin) {
        int length = srcEnd - srcBegin + 1;
        if (dst.length > (dstBegin + length)) {
            throw new RuntimeException("Bytes Subscript Out Of Bounds");
        }
        for (int i = srcBegin, j = dstBegin; i < length; i++,j++) {
            dst[i] = src[i];
        }
        return dst;
    }
    public byte[][] strSplit(String str) {
        byte[][] bytes;
        int length = str.getBytes().length;
        if (length <= 1000) {
            int OffSetShift = length >> 8;
            int ComplementBit = length & 255;
            bytes = new byte[2][1024];
            String message = "";
        }
        return null;
    }

    public String getStreamString(DatagramSocket datagramSocket) throws IOException {
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        datagramSocket.receive(datagramPacket);
        byte[] data = datagramPacket.getData();
        int length = datagramPacket.getLength();
        return "";
    }
}
