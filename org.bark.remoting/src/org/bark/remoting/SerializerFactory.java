package org.bark.remoting;

import java.util.HashMap;
import java.util.Map;

public final class SerializerFactory {
	private static final SerializerFactory _INSTANCE = new SerializerFactory();
	private static final Map<Class<?>, Serializer> serializers = new HashMap<>();
//	private static final EnumSerializer enumSerializer = new EnumSerializer();
//	private static final ArraySerializer arraySerializer = new ArraySerializer();
//	static {
//		serializers.put(int.class, new IntSerializer());
//		serializers.put(short.class, new IntSerializer());
//		serializers.put(byte.class, new IntSerializer());
//		serializers.put(long.class, new IntSerializer());
//		serializers.put(float.class, new DoubleSerializer());
//		serializers.put(double.class, new DoubleSerializer());
//		serializers.put(boolean.class, new BooleanSerializer());
//		
//		serializers.put(Integer.class, new IntSerializer());
//		serializers.put(Short.class, new IntSerializer());
//		serializers.put(Byte.class, new IntSerializer());
//		serializers.put(Long.class, new IntSerializer());
//		serializers.put(Float.class, new DoubleSerializer());
//		serializers.put(Double.class, new DoubleSerializer());
//		serializers.put(Boolean.class, new BooleanSerializer());
//		
//		serializers.put(Class.class, new ClassSerializer());
//		
////		serializers.put(java.util.Date.class, new DateSerializer());
////		
////		serializers.put(java.sql.Date.class, new SQLDateSerializer());
////		
////		serializers.put(java.sql.Time.class, new SQLTimeSerializer());
////		
////		serializers.put(java.sql.Timestamp.class, new SQLTimestampSerializer());
//	}
	private SerializerFactory() {
	}

	public static final SerializerFactory getInstance() {
		return _INSTANCE;
	}
	
	public Serializer getSerializer(Class<?> clazz){
//		if(clazz.isEnum()){
//			return enumSerializer;
//		}
//		
//		if(clazz.isArray()){
//			return arraySerializer;
//		}
		
		Serializer result = serializers.get(clazz);
//		if(result !=null){
//			return result;
//		}
//		
//		result = new BeanSerializer(clazz);
//		serializers.put(clazz, result);
		return result;
	}

	public void register(Class<?> key, Serializer value) {
		serializers.put(key, value);
		
	}
}
