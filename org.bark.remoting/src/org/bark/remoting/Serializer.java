package org.bark.remoting;

import javax.json.JsonObjectBuilder;

public interface Serializer {
	void writeObject(JsonObjectBuilder builder, ReferenceProcessor handler, Object value);
}
