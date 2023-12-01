package com.luoan;

import java.io.*;
import java.nio.file.Files;
import java.util.Properties;

public class Test {

//    private static String name = "面无人色、心旷神怡、耳熟能详、心花怒放、蒙头转向、满面春风、屈指可数、满目疮痍、铁面无私、令人发指、出人头地、魂不附体、食不果腹、不绝于耳、爱不释手、面黄肌瘦、口干舌燥、劈头盖脸、提心吊胆、评头论足、肺腑之言、心口如一、身外之物、足不出户、手无寸铁、心事重重、满腔热忱、促膝谈心、信手拈来、灭顶之灾、另眼相看、腥风血雨、喜笑颜开、大打出手、失之交臂、汗流浃背、光彩夺目、心惊肉跳、面红耳赤、拳打脚踢、鹤发童颜、赏心悦目、抓耳挠腮、浓眉大眼、血肉相连、毛骨悚然、手足无措、掩人耳目、铁石心肠、病入膏肓、沁人肺腑、血流成河、口若悬河、面不改色、眉飞色舞、两手空空、信口开河、垂头丧气、满腹牢骚、燃眉之急、挖空心思、绞尽脑汁、义愤填膺、引人注目、泰山压顶、皮开肉绽、心灵手巧、举手投足、点头哈腰、肥头大耳、心腹之患、心心相印、唇齿相依、骨肉相连、至亲骨肉、徒费唇舌、痛入骨髓、趾高气扬、筋疲力尽、目不暇接、破口大骂、闭目塞听、扬眉吐气、怒发冲冠、切齿痛恨、掩耳盗铃、病从口入、扣人心弦、浪子回头、成竹在胸、不堪回首、眼明手快、胆战心惊、摩拳擦掌、慈眉善目、没头没脑、肝肠寸断、耳目众多、肝胆相照、恨入骨髓、感人肺腑、了如指掌、眼花缭乱、骨瘦如柴、脚踏实地、夺眶而出、惊心动魄、俯首听命、接踵而来、挺身而出、唾手可得、咬紧牙关、为国捐躯、良药苦口、掉以轻心、恨之入骨、耳聪目明、唇亡齿寒、头重脚轻、粉身碎骨、蓬头垢面、五脏六腑、咬牙切齿、血口喷人、腹背受敌、肝脑涂地、沁人心脾、近在眉睫、情同手足、眼高手低、目瞪口呆、胸无点墨、头重脚轻、手足情深、口是心非、眼疾手快、耳闻目睹、头破血流、眉清目秀、袖手傍观、口出不逊、七嘴八舌、交头接耳";
    public static void main(String[] args) throws IOException {
        //BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\idiom.properties"));
        OutputStreamWriter osw = new OutputStreamWriter(Files.newOutputStream(new File(System.getProperty("user.dir") + "\\idiom.conf").toPath()), "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw);
//        Properties properties = new Properties();
//        String[] split = name.split("、");


//        for (int i = 0; i < split.length; i++) {
////            properties.put(String.valueOf(i),split[i]);
//            bw.write(i + "=" + split[i] + "\n");
//            System.out.println(split[i]);
//        }
//        System.out.println(split.length);

        for (int i = 0; i < 50; i++) {
            bw.write(i + "=\n");
        }
        bw.flush();
        bw.close();
//        properties.store(bw,"");
    }
}