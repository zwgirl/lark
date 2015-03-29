package org.bark.remoting;

import static org.bark.remoting.LarkConstants.CLASS;
import static org.bark.remoting.LarkConstants.VALUE;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

public class LarkInput {

	@SuppressWarnings("unchecked")
	public Object readObject(JsonArray root) {
		try{
			int length = root.size();
			Object[] handlers = new Object[length];
			Class<?>[] clazzs = new Class<?>[length];
			boolean[] flags = new boolean[length];
			for (int i = 0; i < length; i++) {
				JsonObject element = root.getJsonObject(i);
				String className = element.getString(CLASS);
				
				flags[i] = true;
				
				Class<?> clazz = null;
				if(className.charAt(0) == '<'){   //pritimive type
					switch(className.charAt(1)){
					case 'B':
						clazz = byte.class;
						handlers[i] = (byte)element.getJsonNumber(VALUE).intValue();
						continue;
					case 'S':
						clazz = short.class;
						handlers[i] = (short)element.getJsonNumber(VALUE).intValue();
						continue;
					case 'I':
						clazz = int.class;
						handlers[i] = element.getJsonNumber(VALUE).intValue();
						continue;
					case 'C':
						clazz = char.class;
						handlers[i] = (char)element.getJsonNumber(VALUE).intValue();
						continue;
					case 'L':
						clazz = long.class;
						handlers[i] = (long)element.getJsonNumber(VALUE).longValue();
						continue;
					case 'F':
						clazz = float.class;
						handlers[i] = (float)element.getJsonNumber(VALUE).doubleValue();
						continue;
					case 'D':
						clazz = double.class;
						handlers[i] = (double)element.getJsonNumber(VALUE).doubleValue();
						continue;
					case 'Z':
						clazz = boolean.class;
						handlers[i] = element.getBoolean(VALUE);
						continue;
					}
				} 
				
				clazz = Class.forName(className);
				clazzs[i] = clazz;
				if (clazz.isArray()) {
					JsonArray jsonArray = element.getJsonArray(VALUE);
					handlers[i] = Array.newInstance(Class.forName("java.lang.Object"), jsonArray.size());
					flags[i] = false;
					continue;
				}
				if (clazz.equals(Byte.class)){
					handlers[i] = new Byte((byte)element.getJsonNumber(VALUE).intValue());
				} else if (clazz.equals(Short.class)) {
					handlers[i] = new Short((short)element.getJsonNumber(VALUE).intValue());
				} else if (clazz.equals(Integer.class)) {
					handlers[i] = new Integer((int)element.getJsonNumber(VALUE).intValue());
				} else if (clazz.equals(Long.class)) {
					handlers[i] = new Long(element.getJsonNumber(VALUE).longValue());
				} else if (clazz.equals(Float.class)) {
					handlers[i] = new Float(element.getJsonNumber(VALUE).doubleValue());
				} else if (clazz.equals(Double.class)) {
					handlers[i] = new Double(element.getJsonNumber(VALUE).doubleValue());
				} else if (clazz.equals(Boolean.class)) {
					handlers[i] = new Boolean(element.getBoolean(VALUE));
				} else if (clazz.equals(Class.class)) {
					handlers[i] = Class.forName(element.getString(VALUE));
				} else if (clazz.isEnum()) {
					clazz = Class.forName(className);
					handlers[i] = Enum.valueOf((Class) clazz, element.getString(LarkConstants.VALUE));
				} else if (clazz.equals(String.class)) {
					handlers[i] = element.getString(VALUE);
				} else if (clazz.equals(java.util.Date.class)) {
					handlers[i] = new java.util.Date(element.getInt(VALUE));
				} else if (clazz.equals(java.sql.Date.class)) {
					handlers[i] = new java.sql.Date(element.getInt(VALUE));
				} else if (clazz.equals(java.sql.Time.class)) {
					handlers[i] = new java.sql.Time(element.getInt(VALUE));
				} else if (clazz.equals(java.sql.Timestamp.class)) {
					handlers[i] = new java.sql.Timestamp(element.getInt(VALUE));
				} else {
					handlers[i] = clazz.newInstance();
					flags[i] = false;
				}
			}

			for (int i = 0; i < length; i++) {
				if (flags[i]) {
					continue;
				}
				
				if(clazzs[i].isArray()){
					JsonArray elements = root.getJsonObject(i).getJsonArray(LarkConstants.VALUE);
					Object[] datas = (Object[]) handlers[i];
					for(int j = 0, size = elements.size(); j < size; j++){
						JsonValue element = elements.get(j);
						if(element == JsonValue.NULL){
							datas[j] = null;
						} else {
							datas[j] = handlers[((JsonNumber)element).intValue()];
						}
					}
				} else {
					Deserializer deser = DeserializerFactory.getInstance().getDeserializer(clazzs[i]);
					deser.readObject(root.getJsonObject(i), handlers, handlers[i]);
				}
			}

			return handlers[0];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

		try (InputStream is = getSearchStream();
				JsonReader rdr = Json.createReader(is)) {

			JsonArray array = rdr.readArray();
			for (JsonObject result : array.getValuesAs(JsonObject.class)) {
				System.out.println(result);
			}
			
			System.out.println(rdr.toString());
			
			LarkInput li = new LarkInput();
			Object o = li.readObject(array);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static InputStream getSearchStream() {
		return LarkInput.class.getResourceAsStream("test.json");
	}

}
