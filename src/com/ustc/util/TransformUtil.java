package com.ustc.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Logger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;

/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月14日 下午4:17:35 
* 
*/
public class TransformUtil {
	private Logger log = Logger.getLogger(this.getClass().getName());
    
    /**
     * 将xml以xsl样式转化为html字符串 
     * @param xmlString xml字符串
     * @param xslPath xsl路径
     * @return
     */
    public String getHtmlString(String xmlString,String xslPath){
        log.info("开始执行getHtmlString(...)方法");
        String returnDocStr = "";
        try {
            SAXReader reader = new SAXReader();
            ByteArrayInputStream bais = new ByteArrayInputStream(xmlString.getBytes());
            Document doc = reader.read(bais);
            Document transformDoc = this.transformDocument(doc,xslPath);
            returnDocStr = this.write2String(transformDoc);
            log.info("getHtmlString(...)执行成功!");
        } catch (Exception e) {
            log.info("getHtmlString(...)方法执行失败,提示信息["+e.getMessage()+"]");
        }
        return returnDocStr;
    }
    
    /**
     * 通过xsl将xml数据文件转化doc对象
     * @param doc xml文档对象
     * @param xslPath xls文件路径
     * @return
     */
    private Document transformDocument(Document doc,String xslPath){
        log.info("开始执行 transformDocument(...)方法");
        TransformerFactory factory = TransformerFactory.newInstance();
        Document transformerDoc = null;
        try {
            Transformer transformer = factory.newTransformer(new StreamSource(xslPath));
            DocumentSource docSource = new DocumentSource(doc);
            DocumentResult docResult = new DocumentResult();
            transformer.transform(docSource, docResult);
            transformerDoc = docResult.getDocument();
            log.info("transformDocument(...)执行成功!");
        } catch (Exception e) {
            log.info("transformDocument(...)方法执行失败,提示信息["+e.getMessage()+"]");
        }
        return transformerDoc;
    }
    
    /**
     * 将doc文档对象转化为html字符串
     * @param transformDoc doc文档
     * @return
     */
    private String write2String(Document transformDoc){
        log.info("开始执行 write2String(...)方法");
        StringWriter strWriter = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("GBK");
        format.setXHTML(true);
        HTMLWriter htmlWriter = new HTMLWriter(strWriter,format);
        format.setExpandEmptyElements(false);
        try {
            htmlWriter.write(transformDoc);
            htmlWriter.flush();
            log.info("write2String(...)执行成功!");
        } catch (IOException e) {
            log.info("write2String(...)方法执行失败,提示信息["+e.getMessage()+"]");
        }
        return strWriter.toString();
    }
    
    
    public String transformXmlToHtml(Document document, String path) {
    	//			Document doc = new SAXReader().read(new File("/Users/kingcong/Desktop/success_view.xml"));
		//			String xslPath = "/Users/kingcong/Desktop/success_view.xsl";
		Document transformDoc = transformDocument(document,path);
		String htmlStr = write2String(transformDoc);
		System.out.println("str=="+htmlStr);
		return htmlStr;
	}
}
