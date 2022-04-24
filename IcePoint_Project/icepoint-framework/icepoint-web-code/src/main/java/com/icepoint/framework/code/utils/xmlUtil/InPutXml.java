package com.icepoint.framework.code.utils.xmlUtil;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ck
 * 读取xml
 */
public class InPutXml {

    public static Document getDocment(File file) {

        Document document = null;
        try {
            SAXReader reader = new SAXReader();
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static GroupEntity inPut(File file) {
        Document docment = getDocment(file);
        Element rootElement = docment.getRootElement();
        List nodes = rootElement.elements("groups");
        GroupEntity groupEntity = null;
        for (Iterator it = nodes.iterator(); it.hasNext(); ) {
            Element nextElement = (Element) it.next();
            List elements = nextElement.elements("group");
            for (Iterator iterator = elements.iterator(); iterator.hasNext(); ) {
                Element group = (Element) iterator.next();
                //获取group 参数
                groupEntity = GroupEntity.builder()
                        .abstracts(group.attributeValue("abstract"))
                        .name(group.attributeValue("psi_cc"))
                        .title(group.attributeValue("title"))
                        .build();

                List process = group.elements("process");
                List<ProcessEntity> processEntityList = new ArrayList<>();
                for (Iterator processIt = process.iterator(); processIt.hasNext(); ) {
                    Element processed = (Element) processIt.next();
                    //获取processed参数
                    ProcessEntity processEntity = ProcessEntity.builder()
                            .abstracted(processed.attributeValue("abstract"))
                            .name(processed.attributeValue("name"))
                            .controller(processed.attributeValue("controller"))
                            .httpMode(processed.attributeValue("httpMode"))
                            .module(processed.attributeValue("module"))
                            .moduleAlias(processed.attributeValue("moduleAlias"))
                            .service(processed.attributeValue("service"))
                            .title(processed.attributeValue("abstrtitleact"))
                            .build();
                    //获取输入参数
                    List inputs = processed.elements("inputs");
                    for (Iterator inputsIt = inputs.iterator(); inputsIt.hasNext(); ) {
                        List<InputEntity> inputEntities = new ArrayList<>();
                        Element element = (Element) inputsIt.next();
                        List input = element.elements("input");
                        for (Iterator inputIt = input.iterator(); inputIt.hasNext(); ) {
                            Element inputEl = (Element) inputIt.next();
                            //获取input
                            InputEntity inputEntity = InputEntity.builder()
                                    .abstracted(inputEl.attributeValue("abstract"))
                                    .name(inputEl.attributeValue("name"))
                                    .title(inputEl.attributeValue("title"))
                                    .type(inputEl.attributeValue("type"))
                                    .build();
                            inputEntities.add(inputEntity);
                        }
                        List output = element.elements("output");
                        OutPutsEntity outPutsEntity = new OutPutsEntity();
                        for (Iterator outputIt = output.iterator(); outputIt.hasNext(); ) {
                            Element outputEl = (Element) outputIt.next();
                            outPutsEntity.setList(outputEl.attributeValue("list"));
                            outPutsEntity.setName(outputEl.attributeValue("name"));
                            List outputs = outputEl.elements("output");
                            List<OutPutEntity> list = new ArrayList<>();
                            for (Iterator outputsIt = outputs.iterator(); outputsIt.hasNext(); ) {
                                Element outputsEl = (Element) outputsIt.next();
                                OutPutEntity outPut = OutPutEntity.builder()
                                        .abstracted(outputsEl.attributeValue("abstract"))
                                        .name(outputsEl.attributeValue("name"))
                                        .title(outputsEl.attributeValue("title"))
                                        .type(outputsEl.attributeValue("type"))
                                        .build();
                                list.add(outPut);
                            }
                            outPutsEntity.setOutPutEntities(list);
                        }
                        processEntity.setOutPutsEntity(outPutsEntity);
                    }
                    processEntityList.add(processEntity);
                }

                groupEntity.setProcesses(processEntityList);
            }

        }
        return groupEntity;
    }




}
