package com.lovo.csc.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * 生成动态验证码
 */
public class verityCode {

    /**
     * @Description: 用于生成前端验证码
     * @Param: width:宽度
     * @Param: height 高度
     * @Param: codeLength 验证码长度
     * @return: 返回验证码以及图片流
     * @Author: Dingmingming
     * @Date: 19-2-18
     **/
    public static <List> Map<String,Object> GraphicCode(int width, int height, int codeLength) throws IOException {
        String graphicCode="";
        //定义验证码 字符组
        String code[]=new String[]{"Q","W","E","R","T","Y","U","I","O","P","A","S","D","F"
                ,"G","H","J","K","L","Z","X","C","V","B","N","M","0","1","2","3","4","5","6","7","8","9"};
        //定义验证字符集合
        java.util.List<String> codeList=new ArrayList<String>();
        for (int i=0;i<codeLength;i++){
            int index=(int)(Math.random()*code.length);
            if(index%2==0){
                codeList.add(code[index].toLowerCase());
                graphicCode=graphicCode+code[index].toLowerCase();
            }else{
                codeList.add(code[index]);
                graphicCode=graphicCode+code[index];
            }
        }
        //图片缓存区
        BufferedImage img=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //设置线条交集处理
        BasicStroke borderStrock=new BasicStroke(1,BasicStroke.JOIN_MITER,BasicStroke.JOIN_MITER);
        //创建画笔
        Graphics2D graphics2D=img.createGraphics();
        //绑定线条处理对象
        graphics2D.setStroke(borderStrock);
        //设置字体 (风格，字体大小)
        graphics2D.setFont(new Font(Font.DIALOG,Font.BOLD,height/2+5));
        //绘制图片背景
        graphics2D.setColor(Color.white);
        //从哪里开始绘制 绘制多大
        graphics2D.fillRect(0,0,width,height);
        //绘制验证码画笔颜色

        for (int i=0;i<codeList.size();i++) {
            //让验证码倾斜
            graphics2D.rotate(1 * Math.PI / 180);
            graphics2D.setColor(Color.red);
            //绘制验证码
            graphics2D.drawString(codeList.get(i),(i+1)*(width/codeList.size()-(i==0?10:4)),height/2+(height/6));
        }

        Random random = new Random();
        graphics2D.setColor(Color.black);
        //绘制线条
        for (int i = 0; i < height/2+5; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            graphics2D.drawLine(x, y, x + xl + 40, y + yl + 20);
        }

        //绘制点
        for(int i=0,n = 80;i<n;i++){
            graphics2D.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
        }

        graphics2D.dispose();
        img.flush();

        //构建结果集
        Map<String,Object> graphicMap=new HashMap<>();
        //放入验证码用于对比
        graphicMap.put("graphicCode",graphicCode);
        //验证码图片流
        graphicMap.put("img",img);

        //用于生成图片 我们这里用不到
        //ImageIO.write(img,"jpg",new File("/home/lazyer/Desktop/a.jpg"));
        return graphicMap;

    }
}
