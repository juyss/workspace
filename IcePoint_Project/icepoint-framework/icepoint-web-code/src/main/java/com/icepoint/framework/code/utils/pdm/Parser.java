package com.icepoint.framework.code.utils.pdm;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;

@Slf4j
public class Parser {
	private PDM pdm = new PDM();


	public static void main(String[] args) {
		try {
			//PDM pdm = new Parser().pdmParser("src/main/java/com/snowolf/pdm/zhsupg_V3.pdm");
			//String file = "E:/work/source/snowolf/CodeBuilder/src/CodeGenerator/src/main/java/com/snowolf/pdm/Test.pdm";
			//String file = "E:/work/ggfw1.pdm";
			String file = "D:\\code\\CodeGenerator\\src\\main\\java\\com\\snowolf\\pdm\\Test.pdm";
			PDM pdm = new Parser().pdmParser(file);
			ArrayList<PDMPhysicalDiagram> ppds = pdm.getPhysicalDiagrams();
			PDMPhysicalDiagram ppd = null;
			for (int i = 0; i < ppds.size(); ++i){
				ppd = ppds.get(i);
				System.out.println("PhysicalDiagram:" + ppd.getName() + "--" + ppd.getCode());
				ArrayList<PDMTable> pts = ppd.getTables();
				for (int j = 0; j < pts.size(); ++j){
					PDMTable pt = pts.get(j);
					System.out.println("PDMTable:" + pt.getName() + "--" + pt.getCode() + "--" + pt.getId());
					ArrayList<PDMColumn> pcs = pt.getColumns();
					for (int k = 0; k < pcs.size(); ++k){
						PDMColumn pc = pcs.get(k);
						System.out.println("PDMColumn:[Name]" + pc.getName() + "--[Code]" + pc.getCode() + "--[Primary]" + pc.getPrimary() + "--[Identity]" + pc.getIdentity() + "--[Id]" + pc.getId() + "--[DataType]" + pc.getDataType() + "--[Comment]" + pc.getComment() + "--[DefaultValue]" + pc.getDefaultValue() + "--[HighValue]" + pc.getHighValue() + "--[Length]" + pc.getLength() + "--[LowValue]" + pc.getLowValue() + "--[Mandatory]" + pc.getMandatory() + "--[Precision]" + pc.getPrecision());
					}
				}
			}
			System.out.println("-----------------------------");
			ArrayList<PDMTable> pts = pdm.getTables();
			for (int j = 0; j < pts.size(); ++j){
				PDMTable pt = pts.get(j);
				System.out.println("PDMTable:" + pt.getName() + "--" + pt.getCode() + "--" + pt.getId());
				ArrayList<PDMColumn> pcs = pt.getColumns();
				for (int k = 0; k < pcs.size(); ++k){
					PDMColumn pc = pcs.get(k);
					System.out.println("PDMColumn:[Name]" + pc.getName() + "--[Code]" + pc.getCode() + "--[Primary]" + pc.getPrimary() + "--[Identity]" + pc.getIdentity() + "--[Id]" + pc.getId() + "--[DataType]" + pc.getDataType() + "--[Comment]" + pc.getComment() + "--[DefaultValue]" + pc.getDefaultValue() + "--[HighValue]" + pc.getHighValue() + "--[Length]" + pc.getLength() + "--[LowValue]" + pc.getLowValue() + "--[Mandatory]" + pc.getMandatory() + "--[Precision]" + pc.getPrecision());
				}
			}
			System.out.println("-----------------------------");
			ArrayList<PDMReference> prs = pdm.getReferences();
			for (int j = 0; j < prs.size(); ++j){
				PDMReference pr = prs.get(j);
				System.out.println("PDMReference:" + pr.getName() + "[" + pr.getParentTable().getName() + "--" + pr.getChildTable().getName() + "]");				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PDM pdmParser(String pdmFileName) throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(pdmFileName);
		return innerPdmParser(doc);
	}
	
	public PDM pdmParser(File pdmFile) throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(pdmFile);
		return innerPdmParser(doc);
	}
	
	private PDM innerPdmParser(Document doc) throws Exception {
		Node model = doc.selectSingleNode("//c:Children/o:Model");

		pdm.setId(((Element) model).attributeValue("Id"));
		pdm.setName(model.selectSingleNode("a:Name").getText());
		pdm.setCode(model.selectSingleNode("a:Code").getText());

		Node dbms = model.selectSingleNode("//o:Shortcut");
		pdm.setDBMSCode(dbms.selectSingleNode("a:Code").getText());
		pdm.setDBMSName(dbms.selectSingleNode("a:Name").getText());

		log.info("解析PDM为:" + pdm.getCode() + "(" + pdm.getName()
				+ ")  DBMS为:" + pdm.getDBMSCode() + "(" + pdm.getDBMSName()
				+ ")");

		pdm.setUsers(pdmUserParser(model));
		pdm.setTables(pdmTableParser(model));
		pdm.setPhysicalDiagrams(pdmPhysicalDiagramParser(model));
		pdm.setReferences(pdmReferenceParser(model));

		return pdm;
	}

	public ArrayList<PDMPhysicalDiagram> pdmPhysicalDiagramParser(Node node)
			throws Exception {
		ArrayList<PDMPhysicalDiagram> physicalList = new ArrayList<PDMPhysicalDiagram>();
		for (Object o : node
				.selectNodes("c:PhysicalDiagrams/o:PhysicalDiagram")) {
			Node physicalNode = (Node) o;
			PDMPhysicalDiagram pdmPhysical = new PDMPhysicalDiagram();
			pdmPhysical.setId(((Element) physicalNode).attributeValue("Id"));
			pdmPhysical.setName(physicalNode.selectSingleNode("a:Name")
					.getText());
			pdmPhysical.setCode(physicalNode.selectSingleNode("a:Code")
					.getText());
			// 添加Table
			for (Object table : physicalNode
					.selectNodes("c:Symbols/o:TableSymbol/c:Object/o:Table")) {
				String id = ((Element) table).attributeValue("Ref");
				pdmPhysical.addTable(pdm.getPDMTable(id));
			}
			physicalList.add(pdmPhysical);
		}
		return physicalList;
	}

	public ArrayList<PDMTable> pdmTableParser(Node node) throws Exception {
		ArrayList<PDMTable> tableList = new ArrayList<PDMTable>();
		for (Object o : node.selectNodes("c:Tables/o:Table")) {
			Node tableNode = (Node) o;
			PDMTable pdmTable = new PDMTable();
			pdmTable.setId(((Element) tableNode).attributeValue("Id"));
			pdmTable.setName(tableNode.selectSingleNode("a:Name").getText());
			pdmTable.setCode(tableNode.selectSingleNode("a:Code").getText());
			// 添加Columns
			pdmTable.setColumns(pdmColumnParser(tableNode));
			// 添加key
			PDMColumn pdmColumn = null;
			for (Object key : tableNode.selectNodes("c:Keys/o:Key")) {
				Node keyNode = (Node) key;
				PDMKey pdmKey = new PDMKey();
				pdmKey.setId(((Element) keyNode).attributeValue("Id"));
				pdmKey.setName(keyNode.selectSingleNode("a:Name").getText());
				pdmKey.setCode(keyNode.selectSingleNode("a:Code").getText());
				for (Object column : keyNode
						.selectNodes("c:Key.Columns/o:Column")) {
					String id = ((Element) column).attributeValue("Ref");
					pdmColumn = pdmTable.getPDMColumn(id);
					pdmColumn.setKey(1);
					System.out.println("--addKey--id=" + pdmColumn.getId() + "--code=" + pdmColumn.getCode());
					pdmKey.addColumn(pdmColumn);
				}
				pdmTable.addKey(pdmKey);
			}
			// 添加PrimaryKey
			Node primaryKeyNode = tableNode.selectSingleNode("c:PrimaryKey/o:Key");
			if (null != primaryKeyNode){
				String keyId = ((Element) primaryKeyNode).attributeValue("Ref");
				PDMKey pdmKey = pdmTable.getPDMKey(keyId);
				System.out.println("--addPrimaryKey--id=" + pdmKey.getId() + "--code=" + pdmKey.getCode() + "--Name=" + pdmKey.getName() + "--ConstraintName=" + pdmKey.getConstraintName());
				ArrayList<PDMColumn> pmdcs = pdmKey.getColumns();
				for (int k = 0; k < pmdcs.size(); ++k){
					pdmColumn = pmdcs.get(k);
					pdmColumn.setPrimary(1);
					//System.out.println("--addPrimaryKey--PDMColumn--id=" + pmdc.getId() + "--code=" + pmdc.getCode() + "--Name=" + pmdc.getName());
				}
				pdmTable.setPrimaryKey(pdmKey);
			}
			// 添加Indexes
			for (Object index : tableNode.selectNodes("c:Indexes/o:Index")) {
				Node indexNode = (Node) index;
				PDMIndex pdmIndex = new PDMIndex();
				pdmIndex.setId(((Element) indexNode).attributeValue("Id"));
				pdmIndex
						.setName(indexNode.selectSingleNode("a:Name").getText());
				pdmIndex
						.setCode(indexNode.selectSingleNode("a:Code").getText());
				for (Object column : indexNode
						.selectNodes("//c:Column/o:Column")) {
					String id = ((Element) column).attributeValue("Ref");
					pdmIndex.addColumn(pdmTable.getPDMColumn(id));
				}
				pdmTable.addIndex(pdmIndex);
			}
			// 添加User
			Element userElement = (Element) tableNode
					.selectSingleNode("c:Owner/o:User");
			if (userElement != null) {
				String userId = userElement.attributeValue("Ref");
				pdmTable.setUser(pdm.getPDMUser(userId));
			}

			tableList.add(pdmTable);
		}
		return tableList;
	}

	public ArrayList<PDMColumn> pdmColumnParser(Node node) {
		ArrayList<PDMColumn> columnList = new ArrayList<PDMColumn>();
		for (Object o : node.selectNodes("c:Columns/o:Column")) {
			Node columnNode = (Node) o;
			PDMColumn pdmColumn = new PDMColumn();
			pdmColumn.setId(((Element) columnNode).attributeValue("Id"));
			pdmColumn.setName(columnNode.selectSingleNode("a:Name").getText());
			pdmColumn.setCode(columnNode.selectSingleNode("a:Code").getText());
			Node attrNode = columnNode.selectSingleNode("a:DataType");
			if (null != attrNode){
				pdmColumn.setDataType(attrNode.getText());	
			}
			else{
				pdmColumn.setDataType("");
			}			
			pdmColumn
					.setLength(selectSingleNodeIntText(columnNode, "a:Length"));
			pdmColumn.setPrecision(selectSingleNodeIntText(columnNode,
					"a:Precision"));
			pdmColumn.setMandatory(selectSingleNodeIntText(columnNode,
					"a:Mandatory"));
			pdmColumn.setDefaultValue(selectSingleNodeStringText(columnNode,
					"a:DefaultValue"));
			pdmColumn.setLowValue(selectSingleNodeStringText(columnNode,
					"a:LowValue"));
			pdmColumn.setHighValue(selectSingleNodeStringText(columnNode,
					"a:HighValue"));
			pdmColumn.setComment(selectSingleNodeStringText(columnNode,
					"a:Comment"));
			pdmColumn.setIdentity(selectSingleNodeIntText(columnNode,"a:Identity"));
			
//			System.out.println("-----Name=" + columnNode.selectSingleNode("a:Name").getText());
//			System.out.println("-----selectSingleNodeStringText------");
//			System.out.println("primary=" + selectSingleNodeStringText(columnNode,"a:Primary"));
//			System.out.println("primaryKey=" + selectSingleNodeStringText(columnNode,"a:PrimaryKey"));
//			System.out.println("foreign=" + selectSingleNodeStringText(columnNode,"a:Foreign"));
//			System.out.println("foreignKey=" + selectSingleNodeStringText(columnNode,"a:ForeignKey"));			
//			System.out.println("domain=" + selectSingleNodeStringText(columnNode,"a:Domain"));
//			System.out.println("-----selectSingleNodeIntText------");
//			System.out.println("primary=" + selectSingleNodeIntText(columnNode,"a:Primary"));
//			System.out.println("primaryKey=" + selectSingleNodeIntText(columnNode,"a:PrimaryKey"));
//			System.out.println("foreign=" + selectSingleNodeIntText(columnNode,"a:Foreign"));
//			System.out.println("foreignKey=" + selectSingleNodeIntText(columnNode,"a:ForeignKey"));			
//			System.out.println("domain=" + selectSingleNodeIntText(columnNode,"a:Domain"));
//			//System.out.println("-----selectSingleNode.getText------");
//			//System.out.println("primary=" + columnNode.selectSingleNode("a:Primary").getText());
//			//System.out.println("primaryKey=" + columnNode.selectSingleNode("a:PrimaryKey").getText());
//			//System.out.println("foreign=" + columnNode.selectSingleNode("a:Foreign").getText());
//			//System.out.println("foreignKey=" + columnNode.selectSingleNode("a:ForeignKey").getText());			
//			//System.out.println("domain=" + columnNode.selectSingleNode("a:Domain").getText());
//			System.out.println("-----selectSingleNode.getStringValue------");
//			//System.out.println("primary=" + columnNode.selectSingleNode("a:Primary").getStringValue());
//			//System.out.println("primaryKey=" + columnNode.selectSingleNode("a:PrimaryKey").getStringValue());
//			//System.out.println("foreign=" + columnNode.selectSingleNode("a:Foreign").getStringValue());
//			//System.out.println("foreignKey=" + columnNode.selectSingleNode("a:ForeignKey").getStringValue());			
//			//System.out.println("domain=" + columnNode.selectSingleNode("a:Domain").getStringValue());

			columnList.add(pdmColumn);
		}
		return columnList;
	}

	public ArrayList<PDMUser> pdmUserParser(Node node) {
		ArrayList<PDMUser> userList = new ArrayList<PDMUser>();
		for (Object o : node.selectNodes("c:Users/o:User")) {
			Node userNode = (Node) o;
			PDMUser pdmUser = new PDMUser();
			pdmUser.setId(((Element) userNode).attributeValue("Id"));
			pdmUser.setName(userNode.selectSingleNode("a:Name").getText());
			pdmUser.setCode(userNode.selectSingleNode("a:Code").getText());

			userList.add(pdmUser);
		}
		return userList;
	}

	public ArrayList<PDMReference> pdmReferenceParser(Node node)
			throws Exception {
		ArrayList<PDMReference> referenceList = new ArrayList<PDMReference>();
		for (Object reference : node.selectNodes("c:References/o:Reference")) {
			Node referenceNode = (Node) reference;
			PDMReference pdmReference = new PDMReference();
			pdmReference.setId(((Element) referenceNode).attributeValue("Id"));
			pdmReference.setName(referenceNode.selectSingleNode("a:Name")
					.getText());
			pdmReference.setCode(referenceNode.selectSingleNode("a:Code")
					.getText());
			pdmReference.setConstraintName(selectSingleNodeStringText(
					referenceNode, "ForeignKeyConstraintName"));
			pdmReference.setUpdateConstraint(selectSingleNodeIntText(
					referenceNode, "UpdateConstraint"));
			pdmReference.setDeleteConstraint(selectSingleNodeIntText(
					referenceNode, "DeleteConstraint"));
			pdmReference.setImplementationType(selectSingleNodeStringText(
					referenceNode, "ImplementationType"));
			// 添加ParentTable
			String parentTableId = ((Element) referenceNode
					.selectSingleNode("c:ParentTable/o:Table"))
					.attributeValue("Ref");
			pdmReference.setParentTable(pdm.getPDMTable(parentTableId));
			// 添加ChildTable
			String childTableId = ((Element) referenceNode
					.selectSingleNode("c:ChildTable/o:Table"))
					.attributeValue("Ref");
			pdmReference.setChildTable(pdm.getPDMTable(childTableId));
			// 添加Joins
			for (Object jion : referenceNode
					.selectNodes("c:Joins/o:ReferenceJoin")) {
				Node referenceJoinNode = (Node) jion;
				PDMReferenceJoin pdmReferenceJoin = new PDMReferenceJoin();
				pdmReferenceJoin.setId(((Element) referenceJoinNode)
						.attributeValue("Id"));

				String id = ((Element) referenceJoinNode
						.selectSingleNode("c:Object1/o:Column"))
						.attributeValue("Ref");
				pdmReferenceJoin.setParentTable_Col(pdmReference
						.getParentTable().getPDMColumn(id));

				id = ((Element) referenceJoinNode
						.selectSingleNode("c:Object2/o:Column"))
						.attributeValue("Ref");
				pdmReferenceJoin.setChildTable_Col(pdmReference.getChildTable()
						.getPDMColumn(id));

				pdmReference.addReferenceJoin(pdmReferenceJoin);
			}

			referenceList.add(pdmReference);
		}
		return referenceList;
	}

	private String selectSingleNodeStringText(Node parentNode,
			String childNodeName) {
		Node childNode = parentNode.selectSingleNode(childNodeName);
		if (childNode != null) {
			return childNode.getText();
		} else {
			return null;
		}
	}

	private int selectSingleNodeIntText(Node parentNode, String childNodeName) {
		Node childNode = parentNode.selectSingleNode(childNodeName);
		if (childNode != null) {
			return Integer.parseInt(childNode.getText());
		} else {
			return 0;
		}
	}
}
