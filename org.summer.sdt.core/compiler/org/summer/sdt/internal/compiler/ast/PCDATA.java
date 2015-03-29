package org.summer.sdt.internal.compiler.ast;

import java.util.HashMap;
import java.util.Map;

import org.summer.sdt.core.compiler.CharOperation;
import org.summer.sdt.internal.compiler.codegen.CodeStream;
import org.summer.sdt.internal.compiler.lookup.BlockScope;
import org.summer.sdt.internal.compiler.lookup.ClassScope;
import org.summer.sdt.internal.compiler.lookup.ElementScope;
import org.summer.sdt.internal.compiler.lookup.Scope;

public class PCDATA  extends XAMLElement {
	public char[] source;

	public PCDATA(char[] source, long pos) {
		super();
		this.source = source;
		//by default the position are the one of the field (not true for super access)
		this.sourceStart = (int) (pos >>> 32);
		this.sourceEnd = (int) (pos & 0x00000000FFFFFFFFL);
	}

	@Override
	public void generateCode(BlockScope currentScope, CodeStream codeStream) {
		
	}
	
	public void resolve(ClassScope scope) {

	}

	@Override
	public void resolve(BlockScope scope) {
		
	}
	
	public void resolve(ElementScope scope) {

	}

	@Override
	public StringBuffer doGenerateExpression(Scope scope, int indent, StringBuffer output) {
		output.append(source);
		return output;
	}

	@Override
	public StringBuffer printExpression(int indent, StringBuffer output) {
		return output;
	}

	@Override
	protected void printTagName(int indent, StringBuffer output) {
		
	}
	
	public char[] translateEntity(boolean reservedReturn){
		boolean flag = false;
		int i = 0, length = 0;
		
		char[] newSrc = CharOperation.eascape(source);
		
		char[] entity = new char[newSrc.length];
		char[] result = new char[newSrc.length];
		for(char currentChar : newSrc){
			switch(currentChar){
			case '&':
				if(flag){
					System.arraycopy(entity, 0, result, length, i);
					length += i;
					i = 0;
				} else {
					flag = true;
				}
				i = 0;
				entity[i++] = currentChar;
				break;
			case ';':
				entity[i++] = currentChar;
				if(flag){
					char c = trans(CharOperation.subarray(entity, 0, i));
					if(c != 0){
						if(c == '"'){
							result[length++] = '\\';
							result[length++] = c;
						} else if(c == '\''){
							result[length++] = '\\';
							result[length++] = c;
						} else {
							result[length++] = c;
						}
					} else {
						System.arraycopy(entity, 0, result, length, i);
						length += i;
					}
				} else {
					result[length++] = currentChar;
				}
				
				i = 0;
				flag = false;
				break;
			default:
				if(flag){
					entity[i++] = currentChar;
				} else {
					result[length++] = currentChar;
				}
			}
		}
		if(i>0){
			System.arraycopy(entity, 0, result, length, i);
			length += i;
		}
		return CharOperation.subarray(result, 0, length);
	}
	
	private static final Map<String, Character> entityMap = new HashMap<String, Character>();
	static{
		entityMap.put("quot", '\"'); //$NON-NLS-1$ 
		entityMap.put("apos", '\''); //$NON-NLS-1$ 
		entityMap.put("lt", '<'); //$NON-NLS-1$ 
		entityMap.put("gt", '>'); //$NON-NLS-1$ 
		entityMap.put("amp", '&'); //$NON-NLS-1$ 
		entityMap.put("nbsp", ' '); //$NON-NLS-1$ 
		entityMap.put("cent", '\uFFE0'); //$NON-NLS-1$ 
		entityMap.put("pound", '£'); //$NON-NLS-1$ 
		entityMap.put("yen", '¥'); //$NON-NLS-1$ 
		entityMap.put("euro", '\u20AC'); //$NON-NLS-1$ 
		entityMap.put("sect", '§'); //$NON-NLS-1$ 
		entityMap.put("trade", '\u2122'); //$NON-NLS-1$ 
		entityMap.put("times", '×'); //$NON-NLS-1$ 
		entityMap.put("divide", '÷'); //$NON-NLS-1$ 
		//TODO to be continue
	}

	private char trans(char[] name) {
		if(name[1] == '#'){
			char[] num = CharOperation.subarray(name, 2, name.length - 1);
			return (char) CharOperation.parseInt(num, 0, num.length);
		} else {
//			char[] name = CharOperation.subarray(array, 1, array.length-1);
			Character c = entityMap.get(new String(CharOperation.subarray(name, 1, name.length - 1)));
			return c == null ? 0 : c ;
		}
	}
	
	@Override
	public StringBuffer generateStaticHTML(Scope scope, int indent, StringBuffer output) {
		return output.append(source);
	}

}
