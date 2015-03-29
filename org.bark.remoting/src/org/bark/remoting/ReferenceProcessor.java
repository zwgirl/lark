package org.bark.remoting;

public class ReferenceProcessor {
	private Object[] refObjects = {};
	
	public int shared(Object obj){
		int length = refObjects.length;
		for(int i=0; i<length; i++){
			if(obj == refObjects[i]){
				return i;
			}
		}
		
		System.arraycopy(refObjects, 0, refObjects = new Object[length + 1], 0, length);
		refObjects[length] = obj;
		return length;
	}
	
	public Object[] getShares(){
		return refObjects;
	}

	public Object getSharedId(int refId) {
		return refObjects[refId];
	}
}
