package de.hsh.prog.gogodie.game.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtils {
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> getScoreList() {
		Document document = read();
		Element root = document.getRootElement();
		
		ArrayList<String> scoreList = new ArrayList<String>();
		
		List<Element> list = root.elements();
		for(Element e : list) {
			List<Attribute> l = e.attributes();
			for(Attribute att : l) {
				String name = att.getName();
				String value = att.getValue();
				if(name.equals("score"))
					scoreList.add(value);
			}
		}
		
		return scoreList;
	}
	
	public static void updateScore(long score) {
		ArrayList<String> scoreList = getScoreList();
		ArrayList<String> newList = new ArrayList<String>();
		int index = -1;
		for(int i=0;i < scoreList.size();i++) {
			if(score >= Long.parseLong(scoreList.get(i))) {
				index = i;
				break;
			}
		}
		if(index > -1) {
			if(index == 0) {
				newList.add(score + "");
				newList.addAll(scoreList.subList(0, scoreList.size() - 1));
			}else if(index == scoreList.size() - 1) {
				newList.addAll(scoreList.subList(0, scoreList.size() - 1));
				newList.add(score + "");
			}else {
				newList.addAll(scoreList.subList(0, index));
				newList.add(score + "");
				newList.addAll(scoreList.subList(index, scoreList.size() - 1));
			}
			
			updateList(newList);
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void updateList(ArrayList<String> newList) {
		Document document = read();
		Element root = document.getRootElement();
		
		List<Element> list = root.elements();
		for(int i=0;i < list.size();i++) {
			Element e = list.get(i);
			List<Attribute> l = e.attributes();
			for(Attribute att : l) {
				String name = att.getName();
				if(name.equals("score"))
					att.setValue(newList.get(i));
			}
		}
		
		write(document);
	}
	
	public static Document read() {
		SAXReader sax = new SAXReader();
		String path = System.getProperty("user.dir");
		System.out.println(path);
		File file = new File(path + "\\highscore.xml");
		
		Document document = null;
		
		try {
			if(file.exists()) {
				document = sax.read(file);
			}else {
				InputStream in = XmlUtils.class.getResourceAsStream("/res/highscore.xml");
				document = sax.read(in);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return document;
	}
	
	public static void write(Document document) {
		String path = System.getProperty("user.dir");
		File file = new File(path + "\\highscore.xml");
		
		Writer w;
		try {
			w = new FileWriter(file);
			XMLWriter writer = new XMLWriter(w);
			writer.write(document);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
