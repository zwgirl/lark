package org.bark.remoting;

import java.util.HashMap;
import java.util.Map;

public final class DeserializerFactory {
	private static final DeserializerFactory _INSTANCE = new DeserializerFactory();
	private static final Map<Class<?>, Deserializer> deseserializers = new HashMap<>();
//	private static final EnumDeserializer enumDeserializer = new EnumDeserializer();
//	private static final ArrayDeserializer arrayDeserializer = new ArrayDeserializer();
//	static {
//		deseserializers.put(int.class, new IntDeserializer());
//		deseserializers.put(short.class, new IntDeserializer());
//		deseserializers.put(byte.class, new IntDeserializer());
//		deseserializers.put(long.class, new IntDeserializer());
//		deseserializers.put(float.class, new DoubleDeserializer());
//		deseserializers.put(double.class, new DoubleDeserializer());
//		deseserializers.put(boolean.class, new BooleanDeserializer());
//		
//		deseserializers.put(Integer.class, new IntDeserializer());
//		deseserializers.put(Short.class, new IntDeserializer());
//		deseserializers.put(Byte.class, new IntDeserializer());
//		deseserializers.put(Long.class, new IntDeserializer());
//		deseserializers.put(Float.class, new DoubleDeserializer());
//		deseserializers.put(Double.class, new DoubleDeserializer());
//		deseserializers.put(Boolean.class, new BooleanDeserializer());
//		
//		deseserializers.put(Class.class, new ClassDeserializer());
//	}
	private DeserializerFactory() {
	}

	public static final DeserializerFactory getInstance() {
		return _INSTANCE;
	}
	
	public Deserializer getDeserializer(Class<?> clazz){
//		if(clazz.isEnum()){
//			return enumDeserializer;
//		}
//		
//		if(clazz.isArray()){
//			return arrayDeserializer;
//		}
//		
		Deserializer result = deseserializers.get(clazz);
//		if(result !=null){
//			return result;
//		}
		
//		result = new BeanDeserializer(clazz);
//		deseserializers.put(clazz, result);
		return result;
	}

	public synchronized void register(Class<?> key, Deserializer value) {
		deseserializers.put(key, value);
	}
}
