package org.bark.remoting;

import javax.json.JsonObject;

public interface Deserializer {
	public Object readObject(JsonObject jsonObj, Object[] handlers, Object obj) throws Exception;
}
