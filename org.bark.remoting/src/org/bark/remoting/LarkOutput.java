package org.bark.remoting;

import static org.bark.remoting.LarkConstants.CLASS;
import static org.bark.remoting.LarkConstants.VALUE;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;

public class LarkOutput {
	public JsonArray writeObject(Object obj){
	    final JsonBuilderFactory bf = Json.createBuilderFactory(null);
	    JsonArrayBuilder array = bf.createArrayBuilder();
	    if(obj == null){
		    JsonObjectBuilder first = bf.createObjectBuilder();
	    	first.add(CLASS, Object.class.getName());
	    	first.addNull(VALUE);
	    	array.add(first);
	    } else {
	    	ReferenceProcessor referenceProcess = new ReferenceProcessor();
	    	referenceProcess.shared(obj);
	    	
	    	int current = 0;
	    	while(current < referenceProcess.getShares().length){
	    		JsonObjectBuilder element = bf.createObjectBuilder();
	    		Object shared = referenceProcess.getShares()[current++];
	    		Class<?> clazz = shared.getClass();
	    		if(clazz.equals(String.class)){
	    			element.add(LarkConstants.CLASS, "java.lang.String");
	    			element.add(LarkConstants.VALUE, (String)shared);
	    		} else if(clazz.equals(Byte.class)) {
	    			element.add(LarkConstants.CLASS, "java.lang.Byte");
	    			element.add(LarkConstants.VALUE, (byte)shared);
	    		} else if(clazz.equals(Short.class)) {
	    			element.add(LarkConstants.CLASS, "java.lang.Short");
	    			element.add(LarkConstants.VALUE, (short)shared);
	    		} else if(clazz.equals(Integer.class)) {
	    			element.add(LarkConstants.CLASS, "java.lang.Integer");
	    			element.add(LarkConstants.VALUE, (int)shared);
	    		} else if(clazz.equals(Long.class)) {
	    			element.add(LarkConstants.CLASS, "java.lang.Long");
	    			element.add(LarkConstants.VALUE, (long)shared);
	    		} else if(clazz.equals(Float.class)) {
	    			element.add(LarkConstants.CLASS, "java.lang.Float");
	    			element.add(LarkConstants.VALUE, (float)shared);
	    		} else if(clazz.equals(Double.class)) {
	    			element.add(LarkConstants.CLASS, "java.lang.Double");
	    			element.add(LarkConstants.VALUE, (short)shared);
	    		} else if(clazz.equals(Class.class)) {
	    			element.add(LarkConstants.CLASS, "java.lang.Class");
	    			element.add(LarkConstants.VALUE, (String)clazz.getName());
	    		} else if(clazz.equals(Boolean.class)) {
	    			element.add(LarkConstants.CLASS, "java.lang.Boolean");
	    			element.add(LarkConstants.VALUE, (boolean)shared);
	    		} else if(clazz.isArray()) {
	    			element.add(LarkConstants.CLASS, "[Ljava.lang.Object;");
	    			JsonArrayBuilder values = bf.createArrayBuilder();
	    			for(Object value : (Object[])shared){
	    				values.add(referenceProcess.shared(value));
	    			}
	    			element.add(LarkConstants.VALUE, values);
	    		} else if(clazz.isEnum()){
	    			element.add(LarkConstants.CLASS, clazz.getName());
	    			element.add(LarkConstants.VALUE, ((Enum)shared).name());
	    		} else {
		    		element.add(CLASS, obj.getClass().getName());
		    		Serializer ser = SerializerFactory.getInstance().getSerializer(shared.getClass());
			    	ser.writeObject(element, referenceProcess, shared);
	    		}

	    		array.add(element);
	    	}
	    }
	   
		return array.build();
	}
}



