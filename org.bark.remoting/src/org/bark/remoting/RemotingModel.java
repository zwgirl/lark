package org.bark.remoting;

@RemotingBean
public class RemotingModel {
  private String _className;
  private String _methodName;
  private Object[] _parameters;
  private Object[] _parameterTypes;

  public String  getClassName() {
    return this._className;
  }
  public void setClassName(String value) {
    this._className = value;
  }

  public String  getMethodName() {
    return this._methodName;
  }
  public void setMethodName(String value) {
    this._methodName = value;
  }

  public Object[]  getParameters() {
    return this._parameters;
  }
  public void setParameters(Object[] value) {
    this._parameters = value;
  }

  public Object[]  getParameterTypes() {
    return this._parameterTypes;
  }
  public void setParameterTypes(Object[] value) {
    this._parameterTypes = value;
  }
  static {
    org.bark.remoting.SerializerFactory.getInstance().register(RemotingModel.class, new org.bark.remoting.Serializer(){
      @Override
      public void writeObject(javax.json.JsonObjectBuilder builder, org.bark.remoting.ReferenceProcessor handler, Object value) {
        RemotingModel __o = (RemotingModel)value;
        builder = __o._className == null ? builder.addNull("_className") : builder.add("_className", __o._className);
        builder = __o._methodName == null ? builder.addNull("_methodName") : builder.add("_methodName", __o._methodName);
        builder = __o._parameters == null ? builder.addNull("_parameters") : builder.add("_parameters", handler.shared(__o._parameters));
        builder = __o._parameterTypes == null ? builder.addNull("_parameterTypes") : builder.add("_parameterTypes", handler.shared(__o._parameterTypes));
      }
    });
    org.bark.remoting.DeserializerFactory.getInstance().register(RemotingModel.class, new org.bark.remoting.Deserializer(){
      @SuppressWarnings({ "unchecked", "rawtypes" })
      @Override
      public Object readObject(javax.json.JsonObject jsonObj, Object[] handlers, Object obj) throws Exception {
        RemotingModel __o = (RemotingModel)obj;
        __o._className = jsonObj.get("_className") == javax.json.JsonValue.NULL ? null : jsonObj.getString("_className");
        __o._methodName = jsonObj.get("_methodName") == javax.json.JsonValue.NULL ? null : jsonObj.getString("_methodName");
        __o._parameters = jsonObj.get("_parameters") == javax.json.JsonValue.NULL ? null : (Object[])handlers[jsonObj.getInt("_parameters")];
        __o._parameterTypes = jsonObj.get("_parameterTypes") == javax.json.JsonValue.NULL ? null : (Object[])handlers[jsonObj.getInt("_parameterTypes")];
        return obj;
      }
    });
  }
}