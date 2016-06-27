package com.cloudboy.cloudboyyun.wechat;

import java.lang.reflect.Field;  
import java.lang.reflect.Method;  
import java.util.Iterator;  

import org.apache.log4j.Logger;
import org.dom4j.Document;  
import org.dom4j.DocumentHelper;  
import org.dom4j.Element;  

import com.cloudboy.util.lang.StringUtils;
  
public class ReceiveXmlProcess {
	private Logger logger = Logger.getLogger(ReceiveXmlProcess.class);

	public ReceiveXmlEntity getMsgEntity(String strXml){  
        ReceiveXmlEntity msg = null;  
        try {  
            if (StringUtils.isEmpty(strXml)) {  
                return null;
            }
               
            Document document = DocumentHelper.parseText(strXml);  
            Element root = document.getRootElement();  
            Iterator<?> iter = root.elementIterator();  
            //利用反射机制，调用set方法  
            //获取该实体的元类型  
            Class<?> c = Class.forName("com.cloudboy.cloudboyyun.process.wechat.ReceiveXmlEntity");
            msg = (ReceiveXmlEntity)c.newInstance();//创建这个实体的对象  
              
            while(iter.hasNext()){  
                Element ele = (Element)iter.next();  
                //获取set方法中的参数字段（实体类的属性）  
                Field field = c.getDeclaredField(ele.getName());  
                //获取set方法，field.getType())获取它的参数数据类型  
                Method method = c.getDeclaredMethod("set"+ele.getName(), field.getType());  
                //调用set方法  
                method.invoke(msg, ele.getText());  
            }  
        } catch (Exception e) {
        	logger.error("", e);
        }  
        return msg;  
    }  
}  
