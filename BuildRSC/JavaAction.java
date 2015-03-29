// This method is part of an automatic generation : do NOT edit-modify
protected void consumeRule(int act) {
  switch ( act ) {
    case 35 : if (DEBUG) { System.out.println("Type ::= PrimitiveType"); }  //$NON-NLS-1$
		    consumePrimitiveType();  
			break;
 
    case 49 : if (DEBUG) { System.out.println("ReferenceType ::= ClassOrInterfaceType"); }  //$NON-NLS-1$
		    consumeReferenceType();  
			break;
 
    case 53 : if (DEBUG) { System.out.println("ClassOrInterface ::= Name"); }  //$NON-NLS-1$
		    consumeClassOrInterfaceName();  
			break;
 
    case 54 : if (DEBUG) { System.out.println("ClassOrInterface ::= GenericType DOT Name"); }  //$NON-NLS-1$
		    consumeClassOrInterface();  
			break;
 
    case 55 : if (DEBUG) { System.out.println("GenericType ::= ClassOrInterface TypeArguments"); }  //$NON-NLS-1$
		    consumeGenericType();  
			break;
 
    case 56 : if (DEBUG) { System.out.println("GenericType ::= ClassOrInterface LESS GREATER"); }  //$NON-NLS-1$
		    consumeGenericTypeWithDiamond();  
			break;
 
    case 57 : if (DEBUG) { System.out.println("ArrayTypeWithTypeArgumentsName ::= GenericType DOT Name"); }  //$NON-NLS-1$
		    consumeArrayTypeWithTypeArgumentsName();  
			break;
 
    case 58 : if (DEBUG) { System.out.println("ArrayType ::= PrimitiveType Dims"); }  //$NON-NLS-1$
		    consumePrimitiveArrayType();  
			break;
 
    case 59 : if (DEBUG) { System.out.println("ArrayType ::= Name Dims"); }  //$NON-NLS-1$
		    consumeNameArrayType();  
			break;
 
    case 60 : if (DEBUG) { System.out.println("ArrayType ::= ArrayTypeWithTypeArgumentsName Dims"); }  //$NON-NLS-1$
		    consumeGenericTypeNameArrayType();  
			break;
 
    case 61 : if (DEBUG) { System.out.println("ArrayType ::= GenericType Dims"); }  //$NON-NLS-1$
		    consumeGenericTypeArrayType();  
			break;
 
    case 63 : if (DEBUG) { System.out.println("Name ::= SimpleName"); }  //$NON-NLS-1$
		    consumeZeroTypeAnnotations();  
			break;
 
    case 68 : if (DEBUG) { System.out.println("UnannotatableName ::= UnannotatableName DOT SimpleName"); }  //$NON-NLS-1$
		    consumeUnannotatableQualifiedName();  
			break;
 
    case 69 : if (DEBUG) { System.out.println("QualifiedName ::= Name DOT SimpleName"); }  //$NON-NLS-1$
		    consumeQualifiedName(false);  
			break;
 
    case 70 : if (DEBUG) { System.out.println("QualifiedName ::= Name DOT TypeAnnotations SimpleName"); }  //$NON-NLS-1$
		    consumeQualifiedName(true);  
			break;
 
    case 71 : if (DEBUG) { System.out.println("TypeAnnotationsopt ::="); }  //$NON-NLS-1$
		    consumeZeroTypeAnnotations();  
			break;
 
     case 75 : if (DEBUG) { System.out.println("TypeAnnotations0 ::= TypeAnnotations0 TypeAnnotation"); }  //$NON-NLS-1$
		    consumeOneMoreTypeAnnotation();  
			break;
 
     case 76 : if (DEBUG) { System.out.println("TypeAnnotation ::= NormalTypeAnnotation"); }  //$NON-NLS-1$
		    consumeTypeAnnotation();  
			break;
 
     case 77 : if (DEBUG) { System.out.println("TypeAnnotation ::= MarkerTypeAnnotation"); }  //$NON-NLS-1$
		    consumeTypeAnnotation();  
			break;
 
     case 78 : if (DEBUG) { System.out.println("TypeAnnotation ::= SingleMemberTypeAnnotation"); }  //$NON-NLS-1$
		    consumeTypeAnnotation();  
			break;
 
    case 79 : if (DEBUG) { System.out.println("TypeAnnotationName ::= AT308 UnannotatableName"); }  //$NON-NLS-1$
		    consumeAnnotationName() ;  
			break;
 
    case 80 : if (DEBUG) { System.out.println("NormalTypeAnnotation ::= TypeAnnotationName LPAREN..."); }  //$NON-NLS-1$
		    consumeNormalAnnotation(true) ;  
			break;
 
    case 81 : if (DEBUG) { System.out.println("MarkerTypeAnnotation ::= TypeAnnotationName"); }  //$NON-NLS-1$
		    consumeMarkerAnnotation(true) ;  
			break;
 
    case 82 : if (DEBUG) { System.out.println("SingleMemberTypeAnnotation ::= TypeAnnotationName LPAREN"); }  //$NON-NLS-1$
		    consumeSingleMemberAnnotation(true) ;  
			break;
 
    case 83 : if (DEBUG) { System.out.println("RejectTypeAnnotations ::="); }  //$NON-NLS-1$
		    consumeNonTypeUseName();  
			break;
 
    case 84 : if (DEBUG) { System.out.println("PushZeroTypeAnnotations ::="); }  //$NON-NLS-1$
		    consumeZeroTypeAnnotations();  
			break;
 
    case 85 : if (DEBUG) { System.out.println("VariableDeclaratorIdOrThis ::= this"); }  //$NON-NLS-1$
		    consumeExplicitThisParameter(false);  
			break;
 
    case 86 : if (DEBUG) { System.out.println("VariableDeclaratorIdOrThis ::= UnannotatableName DOT this"); }  //$NON-NLS-1$
		    consumeExplicitThisParameter(true);  
			break;
 
    case 87 : if (DEBUG) { System.out.println("VariableDeclaratorIdOrThis ::= VariableDeclaratorId"); }  //$NON-NLS-1$
		    consumeVariableDeclaratorIdParameter();  
			break;
 
    case 88 : if (DEBUG) { System.out.println("CompilationUnit ::= EnterCompilationUnit..."); }  //$NON-NLS-1$
		    consumeCompilationUnit();  
			break;
 
    case 89 : if (DEBUG) { System.out.println("InternalCompilationUnit ::= PackageDeclaration"); }  //$NON-NLS-1$
		    consumeInternalCompilationUnit();  
			break;
 
    case 90 : if (DEBUG) { System.out.println("InternalCompilationUnit ::= PackageDeclaration..."); }  //$NON-NLS-1$
		    consumeInternalCompilationUnit();  
			break;
 
    case 91 : if (DEBUG) { System.out.println("InternalCompilationUnit ::= PackageDeclaration..."); }  //$NON-NLS-1$
		    consumeInternalCompilationUnitWithTypes();  
			break;
 
    case 92 : if (DEBUG) { System.out.println("InternalCompilationUnit ::= PackageDeclaration..."); }  //$NON-NLS-1$
		    consumeInternalCompilationUnitWithTypes();  
			break;
 
    case 93 : if (DEBUG) { System.out.println("InternalCompilationUnit ::= ImportDeclarations..."); }  //$NON-NLS-1$
		    consumeInternalCompilationUnit();  
			break;
 
    case 94 : if (DEBUG) { System.out.println("InternalCompilationUnit ::= TypeDeclarations"); }  //$NON-NLS-1$
		    consumeInternalCompilationUnitWithTypes();  
			break;
 
    case 95 : if (DEBUG) { System.out.println("InternalCompilationUnit ::= ImportDeclarations..."); }  //$NON-NLS-1$
		    consumeInternalCompilationUnitWithTypes();  
			break;
 
    case 96 : if (DEBUG) { System.out.println("InternalCompilationUnit ::="); }  //$NON-NLS-1$
		    consumeEmptyInternalCompilationUnit();  
			break;
 
    case 103 : if (DEBUG) { System.out.println("ElementListopt ::="); }  //$NON-NLS-1$
		    consumeElementListopt();  
			break;
 
    case 106 : if (DEBUG) { System.out.println("ElementList ::= ElementList Element"); }  //$NON-NLS-1$
		    consumeElementList();  
			break;
 
    case 107 : if (DEBUG) { System.out.println("Script ::= ScriptMethodHeader NestedMethod..."); }  //$NON-NLS-1$
		    consumeScript();  
			break;
 
    case 108 : if (DEBUG) { System.out.println("ScriptMethodHeader ::= SCRIPT_START"); }  //$NON-NLS-1$
		    consumeScriptMethodName();  
			break;

    case 109 : if (DEBUG) { System.out.println("PCDATANode ::= PCDATA"); }  //$NON-NLS-1$
		    consumePCDATANode();  
			break;
 
    case 110 : if (DEBUG) { System.out.println("EmptyElement ::= ElementTag EnterCloseTag EnterPCDATA..."); }  //$NON-NLS-1$
		    consumeEmptyElement();  
			break;
 
    case 111 : if (DEBUG) { System.out.println("SimpleElement ::= ElementTag AttributeList EnterCloseTag"); }  //$NON-NLS-1$
		    consumeSimpleElement();  
			break;
 
    case 112 : if (DEBUG) { System.out.println("ComplexElement ::= ElementTag AttributeListopt..."); }  //$NON-NLS-1$
		    consumeComplexElement();  
			break;
 
    case 113 : if (DEBUG) { System.out.println("EnterCloseTag ::="); }  //$NON-NLS-1$
		    consumeEnterCloseTag();  
			break;
 
    case 114 : if (DEBUG) { System.out.println("EnterPCDATA ::="); }  //$NON-NLS-1$
		    consumeEnterPCADATA();  
			break;
 
    case 115 : if (DEBUG) { System.out.println("AttributeListopt ::="); }  //$NON-NLS-1$
		    consumeAttributeListopt();  
			break;
 
    case 117 : if (DEBUG) { System.out.println("AttributeElementTag ::="); }  //$NON-NLS-1$
		    consumeAttributeElementTag();  
			break;
 
    case 118 : if (DEBUG) { System.out.println("ElementTag ::= LESS SimpleName"); }  //$NON-NLS-1$
		    consumeElementTag();  
			break;
 
    case 119 : if (DEBUG) { System.out.println("AttributeElement ::= LESS SimpleName DOT SimpleName..."); }  //$NON-NLS-1$
		    consumeAttributeElement();  
			break;
 
    case 121 : if (DEBUG) { System.out.println("AttributeList ::= AttributeList Attribute"); }  //$NON-NLS-1$
		    consumeAttributeAttributeList();  
			break;
 
    case 124 : if (DEBUG) { System.out.println("AttachAttribute ::= SimpleName DOT SimpleName EQUAL..."); }  //$NON-NLS-1$
		    consumeAttachAttribute();  
			break;
 
    case 125 : if (DEBUG) { System.out.println("GeneralAttribute ::= SimpleName COLON SimpleName EQUAL..."); }  //$NON-NLS-1$
		    consumeGeneralAttributeWithNS();  
			break;
 
    case 126 : if (DEBUG) { System.out.println("GeneralAttribute ::= SimpleName EQUAL PropertyExpression"); }  //$NON-NLS-1$
		    consumeGeneralAttribute(null);  
			break;
 
    case 129 : if (DEBUG) { System.out.println("MarkupExtensionTag ::="); }  //$NON-NLS-1$
		    consumeMarkupExtensionTag();  
			break;
 
    case 130 : if (DEBUG) { System.out.println("MarkupExtenson ::= LBRACE SimpleName MarkupExtensionTag"); }  //$NON-NLS-1$
		    consumeMarkupExtenson(false);  
			break;
 
    case 131 : if (DEBUG) { System.out.println("MarkupExtenson ::= LBRACE SimpleName MarkupExtensionTag"); }  //$NON-NLS-1$
		    consumeMarkupExtenson(true);  
			break;
 
    case 132 : if (DEBUG) { System.out.println("ReduceImports ::="); }  //$NON-NLS-1$
		    consumeReduceImports();  
			break;
 
    case 133 : if (DEBUG) { System.out.println("EnterCompilationUnit ::="); }  //$NON-NLS-1$
		    consumeEnterCompilationUnit();  
			break;
 
    case 149 : if (DEBUG) { System.out.println("CatchHeader ::= catch LPAREN CatchFormalParameter RPAREN"); }  //$NON-NLS-1$
		    consumeCatchHeader();  
			break;
 
    case 151 : if (DEBUG) { System.out.println("ImportDeclarations ::= ImportDeclarations..."); }  //$NON-NLS-1$
		    consumeImportDeclarations();  
			break;
 
    case 153 : if (DEBUG) { System.out.println("TypeDeclarations ::= TypeDeclarations TypeDeclaration"); }  //$NON-NLS-1$
		    consumeTypeDeclarations();  
			break;
 
    case 154 : if (DEBUG) { System.out.println("PackageDeclaration ::= PackageDeclarationName SEMICOLON"); }  //$NON-NLS-1$
		    consumePackageDeclaration();  
			break;
 
    case 155 : if (DEBUG) { System.out.println("PackageDeclarationName ::= Modifiers package..."); }  //$NON-NLS-1$
		    consumePackageDeclarationNameWithModifiers();  
			break;
 
    case 156 : if (DEBUG) { System.out.println("PackageDeclarationName ::= PackageComment package Name..."); }  //$NON-NLS-1$
		    consumePackageDeclarationName();  
			break;
 
    case 157 : if (DEBUG) { System.out.println("PackageComment ::="); }  //$NON-NLS-1$
		    consumePackageComment();  
			break;
 
    case 162 : if (DEBUG) { System.out.println("SingleTypeImportDeclaration ::=..."); }  //$NON-NLS-1$
		    consumeImportDeclaration();  
			break;
 
    case 163 : if (DEBUG) { System.out.println("SingleTypeImportDeclarationName ::= import Name..."); }  //$NON-NLS-1$
		    consumeSingleTypeImportDeclarationName();  
			break;
 
    case 164 : if (DEBUG) { System.out.println("TypeImportOnDemandDeclaration ::=..."); }  //$NON-NLS-1$
		    consumeImportDeclaration();  
			break;
 
    case 165 : if (DEBUG) { System.out.println("TypeImportOnDemandDeclarationName ::= import Name DOT..."); }  //$NON-NLS-1$
		    consumeTypeImportOnDemandDeclarationName();  
			break;
 
     case 168 : if (DEBUG) { System.out.println("TypeDeclaration ::= SEMICOLON"); }  //$NON-NLS-1$
		    consumeEmptyTypeDeclaration();  
			break;
 
    case 172 : if (DEBUG) { System.out.println("Modifiers ::= Modifiers Modifier"); }  //$NON-NLS-1$
		    consumeModifiers2();  
			break;
 
    case 184 : if (DEBUG) { System.out.println("Modifier ::= Annotation"); }  //$NON-NLS-1$
		    consumeAnnotationAsModifier();  
			break;
 
    case 185 : if (DEBUG) { System.out.println("ClassDeclaration ::= ClassHeader ClassBody"); }  //$NON-NLS-1$
		    consumeClassDeclaration();  
			break;
 
    case 186 : if (DEBUG) { System.out.println("ClassHeader ::= ClassHeaderName ClassHeaderExtendsopt..."); }  //$NON-NLS-1$
		    consumeClassHeader();  
			break;
 
    case 187 : if (DEBUG) { System.out.println("ClassHeaderName ::= ClassHeaderName1 TypeParameters"); }  //$NON-NLS-1$
		    consumeTypeHeaderNameWithTypeParameters();  
			break;
 
    case 189 : if (DEBUG) { System.out.println("ClassHeaderName1 ::= Modifiersopt class Identifier"); }  //$NON-NLS-1$
		    consumeClassHeaderName1();  
			break;
 
    case 190 : if (DEBUG) { System.out.println("ClassHeaderExtends ::= extends ClassType"); }  //$NON-NLS-1$
		    consumeClassHeaderExtends();  
			break;
 
    case 191 : if (DEBUG) { System.out.println("ClassHeaderImplements ::= implements InterfaceTypeList"); }  //$NON-NLS-1$
		    consumeClassHeaderImplements();  
			break;
 
    case 193 : if (DEBUG) { System.out.println("InterfaceTypeList ::= InterfaceTypeList COMMA..."); }  //$NON-NLS-1$
		    consumeInterfaceTypeList();  
			break;
 
    case 194 : if (DEBUG) { System.out.println("InterfaceType ::= ClassOrInterfaceType"); }  //$NON-NLS-1$
		    consumeInterfaceType();  
			break;
 
    case 197 : if (DEBUG) { System.out.println("ClassBodyDeclarations ::= ClassBodyDeclarations..."); }  //$NON-NLS-1$
		    consumeClassBodyDeclarations();  
			break;
 
    case 201 : if (DEBUG) { System.out.println("ClassBodyDeclaration ::= Diet NestedMethod..."); }  //$NON-NLS-1$
		    consumeClassBodyDeclaration();  
			break;
 
    case 202 : if (DEBUG) { System.out.println("Diet ::="); }  //$NON-NLS-1$
		    consumeDiet();  
			break;

    case 203 : if (DEBUG) { System.out.println("Initializer ::= Diet NestedMethod CreateInitializer Block"); }  //$NON-NLS-1$
		    consumeClassBodyDeclaration();  
			break;
 
    case 204 : if (DEBUG) { System.out.println("CreateInitializer ::="); }  //$NON-NLS-1$
		    consumeCreateInitializer();  
			break;

    case 206 : if (DEBUG) { System.out.println("PropertyDeclaration ::= Modifiersopt Type Identifier..."); }  //$NON-NLS-1$
		    consumePropertyDeclaration();  
			break;

    case 207 : if (DEBUG) { System.out.println("PropertyHeader ::="); }  //$NON-NLS-1$
		    consumePropertyHeader();  
			break;

    case 208 : if (DEBUG) { System.out.println("AccessorDeclaration ::="); }  //$NON-NLS-1$
		    consumeEmptyAccessor();  
			break;

    case 212 : if (DEBUG) { System.out.println("SetAccessorDeclarationopt ::= SetAccessorDeclaration"); }  //$NON-NLS-1$
		    consumeAccessoropt();  
			break;

    case 213 : if (DEBUG) { System.out.println("SetAccessorDeclaration ::= SetterMethodHeader MethodBody"); }  //$NON-NLS-1$
		     consumeMethodDeclaration(true, false);   
			break;

    case 214 : if (DEBUG) { System.out.println("SetAccessorDeclaration ::= SetterMethodHeader SEMICOLON"); }  //$NON-NLS-1$
		     consumeMethodDeclaration(false, false);   
			break;

    case 215 : if (DEBUG) { System.out.println("SetterMethodHeader ::= PLUS"); }  //$NON-NLS-1$
		    consumeAccessorMethodName(MethodDeclaration.SETTER);  
			break;

    case 217 : if (DEBUG) { System.out.println("GetAccessorDeclarationopt ::= GetAccessorDeclaration"); }  //$NON-NLS-1$
		    consumeAccessoropt();  
			break;

    case 218 : if (DEBUG) { System.out.println("GetAccessorDeclaration ::= GetterMethodHeader MethodBody"); }  //$NON-NLS-1$
		     consumeMethodDeclaration(true, false);   
			break;

    case 219 : if (DEBUG) { System.out.println("GetAccessorDeclaration ::= GetterMethodHeader SEMICOLON"); }  //$NON-NLS-1$
		     consumeMethodDeclaration(false, false);   
			break;

    case 220 : if (DEBUG) { System.out.println("GetterMethodHeader ::= AND"); }  //$NON-NLS-1$
		    consumeAccessorMethodName(MethodDeclaration.GETTER);  
			break;

    case 222 : if (DEBUG) { System.out.println("IndexerDeclaration ::= IndexerHeader FormalParameter..."); }  //$NON-NLS-1$
		    consumeIndexerDeclaration();  
			break;

    case 223 : if (DEBUG) { System.out.println("IndexerDeclaration ::= IndexerHeader FormalParameter..."); }  //$NON-NLS-1$
		    consumeIndexerDeclarationNoAccessor();  
			break;

    case 224 : if (DEBUG) { System.out.println("IndexerHeader ::= Modifiersopt Type this LBRACKET"); }  //$NON-NLS-1$
		    consumeIndexerHeader();  
			break;

    case 231 : if (DEBUG) { System.out.println("ClassMemberDeclaration ::= SEMICOLON"); }  //$NON-NLS-1$
		    consumeEmptyTypeDeclaration();  
			break;

    case 234 : if (DEBUG) { System.out.println("FieldDeclaration ::= Modifiersopt Type..."); }  //$NON-NLS-1$
		    consumeFieldDeclaration();  
			break;
 
    case 236 : if (DEBUG) { System.out.println("VariableDeclarators ::= VariableDeclarators COMMA..."); }  //$NON-NLS-1$
		    consumeVariableDeclarators();  
			break;
 
    case 239 : if (DEBUG) { System.out.println("EnterVariable ::="); }  //$NON-NLS-1$
		    consumeEnterVariable();  
			break;
 
    case 240 : if (DEBUG) { System.out.println("ExitVariableWithInitialization ::="); }  //$NON-NLS-1$
		    consumeExitVariableWithInitialization();  
			break;
 
    case 241 : if (DEBUG) { System.out.println("ExitVariableWithoutInitialization ::="); }  //$NON-NLS-1$
		    consumeExitVariableWithoutInitialization();  
			break;
 
    case 242 : if (DEBUG) { System.out.println("ForceNoDiet ::="); }  //$NON-NLS-1$
		    consumeForceNoDiet();  
			break;
 
    case 243 : if (DEBUG) { System.out.println("RestoreDiet ::="); }  //$NON-NLS-1$
		    consumeRestoreDiet();  
			break;
 
    case 248 : if (DEBUG) { System.out.println("MethodDeclaration ::= MethodHeader MethodBody"); }  //$NON-NLS-1$
		    // set to true to consume a method with a body
 consumeMethodDeclaration(true, false);  
			break;
 
    case 249 : if (DEBUG) { System.out.println("MethodDeclaration ::= DefaultMethodHeader MethodBody"); }  //$NON-NLS-1$
		    // set to true to consume a method with a body
 consumeMethodDeclaration(true, true);  
			break;
 
    case 250 : if (DEBUG) { System.out.println("AbstractMethodDeclaration ::= MethodHeader SEMICOLON"); }  //$NON-NLS-1$
		    // set to false to consume a method without body
 consumeMethodDeclaration(false, false);  
			break;
 
    case 251 : if (DEBUG) { System.out.println("AbstractMethodDeclaration ::= MethodHeader CODE_DATA..."); }  //$NON-NLS-1$
		    // set to false to consume a method without body
 consumeMethodDeclarationWithCode(false, false);  
			break;
 
    case 252 : if (DEBUG) { System.out.println("MethodHeader ::= MethodHeaderName FormalParameterListopt"); }  //$NON-NLS-1$
		    consumeMethodHeader();  
			break;
 
    case 253 : if (DEBUG) { System.out.println("DefaultMethodHeader ::= DefaultMethodHeaderName..."); }  //$NON-NLS-1$
		    consumeMethodHeader();  
			break;
 
    case 254 : if (DEBUG) { System.out.println("MethodHeaderName ::= Modifiersopt Type Identifier..."); }  //$NON-NLS-1$
		    consumeMethodHeaderNameWithTypeParameters(false);  
			break;
 
    case 255 : if (DEBUG) { System.out.println("MethodHeaderName ::= Modifiersopt Type Identifier LPAREN"); }  //$NON-NLS-1$
		    consumeMethodHeaderName(false);  
			break;
 
    case 256 : if (DEBUG) { System.out.println("DefaultMethodHeaderName ::= ModifiersWithDefault Type..."); }  //$NON-NLS-1$
		    consumeMethodHeaderNameWithTypeParameters(false);  
			break;
 
    case 257 : if (DEBUG) { System.out.println("DefaultMethodHeaderName ::= ModifiersWithDefault Type..."); }  //$NON-NLS-1$
		    consumeMethodHeaderName(false);  
			break;
 
    case 258 : if (DEBUG) { System.out.println("ModifiersWithDefault ::= Modifiersopt default..."); }  //$NON-NLS-1$
		    consumePushCombineModifiers();  
			break;
 
    case 259 : if (DEBUG) { System.out.println("MethodHeaderRightParen ::= RPAREN"); }  //$NON-NLS-1$
		    consumeMethodHeaderRightParen();  
			break;
 
    case 260 : if (DEBUG) { System.out.println("MethodHeaderExtendedDims ::= Dimsopt"); }  //$NON-NLS-1$
		    consumeMethodHeaderExtendedDims();  
			break;
 
    case 261 : if (DEBUG) { System.out.println("MethodHeaderThrowsClause ::= throws ClassTypeList"); }  //$NON-NLS-1$
		    consumeMethodHeaderThrowsClause();  
			break;
 
    case 262 : if (DEBUG) { System.out.println("ConstructorHeader ::= ConstructorHeaderName..."); }  //$NON-NLS-1$
		    consumeConstructorHeader();  
			break;
 
    case 263 : if (DEBUG) { System.out.println("ConstructorHeaderName ::= Modifiersopt Identifier LPAREN"); }  //$NON-NLS-1$
		    consumeConstructorHeaderName();  
			break;
 
    case 265 : if (DEBUG) { System.out.println("FormalParameterList ::= FormalParameterList COMMA..."); }  //$NON-NLS-1$
		    consumeFormalParameterList();  
			break;
 
    case 266 : if (DEBUG) { System.out.println("FormalParameter ::= Modifiersopt Type..."); }  //$NON-NLS-1$
		    consumeFormalParameter(false);  
			break;
 
    case 267 : if (DEBUG) { System.out.println("FormalParameter ::= Modifiersopt Type..."); }  //$NON-NLS-1$
		    consumeFormalParameter(true);  
			break;
 
    case 268 : if (DEBUG) { System.out.println("CatchFormalParameter ::= Modifiersopt CatchType..."); }  //$NON-NLS-1$
		    consumeCatchFormalParameter();  
			break;
 
    case 269 : if (DEBUG) { System.out.println("CatchType ::= UnionType"); }  //$NON-NLS-1$
		    consumeCatchType();  
			break;
 
    case 270 : if (DEBUG) { System.out.println("UnionType ::= Type"); }  //$NON-NLS-1$
		    consumeUnionTypeAsClassType();  
			break;
 
    case 271 : if (DEBUG) { System.out.println("UnionType ::= UnionType OR Type"); }  //$NON-NLS-1$
		    consumeUnionType();  
			break;
 
    case 273 : if (DEBUG) { System.out.println("ClassTypeList ::= ClassTypeList COMMA ClassTypeElt"); }  //$NON-NLS-1$
		    consumeClassTypeList();  
			break;
 
    case 274 : if (DEBUG) { System.out.println("ClassTypeElt ::= ClassType"); }  //$NON-NLS-1$
		    consumeClassTypeElt();  
			break;
 
    case 275 : if (DEBUG) { System.out.println("MethodBody ::= NestedMethod LBRACE BlockStatementsopt..."); }  //$NON-NLS-1$
		    consumeMethodBody();  
			break;
 
    case 276 : if (DEBUG) { System.out.println("NestedMethod ::="); }  //$NON-NLS-1$
		    consumeNestedMethod();  
			break;
 
    case 277 : if (DEBUG) { System.out.println("StaticInitializer ::= StaticOnly Block"); }  //$NON-NLS-1$
		    consumeStaticInitializer();  
			break;

    case 278 : if (DEBUG) { System.out.println("StaticOnly ::= static"); }  //$NON-NLS-1$
		    consumeStaticOnly();  
			break;
 
    case 279 : if (DEBUG) { System.out.println("ConstructorDeclaration ::= ConstructorHeader MethodBody"); }  //$NON-NLS-1$
		    consumeConstructorDeclaration() ;  
			break;
 
    case 280 : if (DEBUG) { System.out.println("ConstructorDeclaration ::= ConstructorHeader SEMICOLON"); }  //$NON-NLS-1$
		    consumeInvalidConstructorDeclaration() ;  
			break;
 
    case 281 : if (DEBUG) { System.out.println("ConstructorDeclaration ::= ConstructorHeader CODE_DATA..."); }  //$NON-NLS-1$
		    consumeInvalidConstructorDeclarationWithJSNI();  
			break;
 
    case 282 : if (DEBUG) { System.out.println("ExplicitConstructorInvocation ::= this LPAREN..."); }  //$NON-NLS-1$
		    consumeExplicitConstructorInvocation(0, THIS_CALL);  
			break;
 
    case 283 : if (DEBUG) { System.out.println("ExplicitConstructorInvocation ::= this OnlyTypeArguments"); }  //$NON-NLS-1$
		    consumeExplicitConstructorInvocationWithTypeArguments(0,THIS_CALL);  
			break;
 
    case 284 : if (DEBUG) { System.out.println("ExplicitConstructorInvocation ::= super LPAREN..."); }  //$NON-NLS-1$
		    consumeExplicitConstructorInvocation(0,SUPER_CALL);  
			break;
 
    case 285 : if (DEBUG) { System.out.println("ExplicitConstructorInvocation ::= super OnlyTypeArguments"); }  //$NON-NLS-1$
		    consumeExplicitConstructorInvocationWithTypeArguments(0,SUPER_CALL);  
			break;
 
    case 286 : if (DEBUG) { System.out.println("ExplicitConstructorInvocation ::= Primary DOT super..."); }  //$NON-NLS-1$
		    consumeExplicitConstructorInvocation(1, SUPER_CALL);  
			break;
 
    case 287 : if (DEBUG) { System.out.println("ExplicitConstructorInvocation ::= Primary DOT super..."); }  //$NON-NLS-1$
		    consumeExplicitConstructorInvocationWithTypeArguments(1, SUPER_CALL);  
			break;
 
    case 288 : if (DEBUG) { System.out.println("ExplicitConstructorInvocation ::= Name DOT super LPAREN"); }  //$NON-NLS-1$
		    consumeExplicitConstructorInvocation(2, SUPER_CALL);  
			break;
 
    case 289 : if (DEBUG) { System.out.println("ExplicitConstructorInvocation ::= Name DOT super..."); }  //$NON-NLS-1$
		    consumeExplicitConstructorInvocationWithTypeArguments(2, SUPER_CALL);  
			break;
 
    case 290 : if (DEBUG) { System.out.println("ExplicitConstructorInvocation ::= Primary DOT this LPAREN"); }  //$NON-NLS-1$
		    consumeExplicitConstructorInvocation(1, THIS_CALL);  
			break;
 
    case 291 : if (DEBUG) { System.out.println("ExplicitConstructorInvocation ::= Primary DOT this..."); }  //$NON-NLS-1$
		    consumeExplicitConstructorInvocationWithTypeArguments(1, THIS_CALL);  
			break;
 
    case 292 : if (DEBUG) { System.out.println("ExplicitConstructorInvocation ::= Name DOT this LPAREN..."); }  //$NON-NLS-1$
		    consumeExplicitConstructorInvocation(2, THIS_CALL);  
			break;
 
    case 293 : if (DEBUG) { System.out.println("ExplicitConstructorInvocation ::= Name DOT this..."); }  //$NON-NLS-1$
		    consumeExplicitConstructorInvocationWithTypeArguments(2, THIS_CALL);  
			break;
 
    case 294 : if (DEBUG) { System.out.println("InterfaceDeclaration ::= InterfaceHeader InterfaceBody"); }  //$NON-NLS-1$
		    consumeInterfaceDeclaration();  
			break;
 
    case 295 : if (DEBUG) { System.out.println("InterfaceHeader ::= InterfaceHeaderName..."); }  //$NON-NLS-1$
		    consumeInterfaceHeader();  
			break;
 
    case 296 : if (DEBUG) { System.out.println("InterfaceHeaderName ::= InterfaceHeaderName1..."); }  //$NON-NLS-1$
		    consumeTypeHeaderNameWithTypeParameters();  
			break;
 
    case 298 : if (DEBUG) { System.out.println("InterfaceHeaderName1 ::= Modifiersopt interface..."); }  //$NON-NLS-1$
		    consumeInterfaceHeaderName1();  
			break;
 
    case 299 : if (DEBUG) { System.out.println("InterfaceHeaderExtends ::= extends InterfaceTypeList"); }  //$NON-NLS-1$
		    consumeInterfaceHeaderExtends();  
			break;
 
    case 302 : if (DEBUG) { System.out.println("InterfaceMemberDeclarations ::=..."); }  //$NON-NLS-1$
		    consumeInterfaceMemberDeclarations();  
			break;
 
    case 303 : if (DEBUG) { System.out.println("InterfaceMemberDeclaration ::= SEMICOLON"); }  //$NON-NLS-1$
		    consumeEmptyTypeDeclaration();  
			break;
 
    case 307 : if (DEBUG) { System.out.println("InterfaceMemberDeclaration ::= DefaultMethodHeader..."); }  //$NON-NLS-1$
		    consumeInterfaceMethodDeclaration(false);  
			break;
 
    case 308 : if (DEBUG) { System.out.println("InterfaceMemberDeclaration ::= MethodHeader MethodBody"); }  //$NON-NLS-1$
		    consumeInterfaceMethodDeclaration(false);  
			break;
 
    case 309 : if (DEBUG) { System.out.println("InterfaceMemberDeclaration ::= DefaultMethodHeader..."); }  //$NON-NLS-1$
		    consumeInterfaceMethodDeclaration(true);  
			break;
 
    case 310 : if (DEBUG) { System.out.println("InvalidConstructorDeclaration ::= ConstructorHeader..."); }  //$NON-NLS-1$
		    consumeInvalidConstructorDeclaration(true);  
			break;
 
    case 311 : if (DEBUG) { System.out.println("InvalidConstructorDeclaration ::= ConstructorHeader..."); }  //$NON-NLS-1$
		    consumeInvalidConstructorDeclaration(false);  
			break;
 
    case 322 : if (DEBUG) { System.out.println("PushLeftBrace ::="); }  //$NON-NLS-1$
		    consumePushLeftBrace();  
			break;
 
    case 323 : if (DEBUG) { System.out.println("ArrayInitializer ::= LBRACE PushLeftBrace ,opt RBRACE"); }  //$NON-NLS-1$
		    consumeEmptyArrayInitializer();  
			break;
 
    case 324 : if (DEBUG) { System.out.println("ArrayInitializer ::= LBRACE PushLeftBrace..."); }  //$NON-NLS-1$
		    consumeArrayInitializer();  
			break;
 
    case 325 : if (DEBUG) { System.out.println("ArrayInitializer ::= LBRACE PushLeftBrace..."); }  //$NON-NLS-1$
		    consumeArrayInitializer();  
			break;
 
    case 327 : if (DEBUG) { System.out.println("VariableInitializers ::= VariableInitializers COMMA..."); }  //$NON-NLS-1$
		    consumeVariableInitializers();  
			break;
 
    case 328 : if (DEBUG) { System.out.println("Block ::= OpenBlock LBRACE BlockStatementsopt RBRACE"); }  //$NON-NLS-1$
		    consumeBlock();  
			break;
 
    case 329 : if (DEBUG) { System.out.println("OpenBlock ::="); }  //$NON-NLS-1$
		    consumeOpenBlock() ;  
			break;
 
    case 330 : if (DEBUG) { System.out.println("BlockStatements ::= BlockStatement"); }  //$NON-NLS-1$
		    consumeBlockStatement() ;  
			break;
 
    case 331 : if (DEBUG) { System.out.println("BlockStatements ::= BlockStatements BlockStatement"); }  //$NON-NLS-1$
		    consumeBlockStatements() ;  
			break;
 
    case 338 : if (DEBUG) { System.out.println("BlockStatement ::= InterfaceDeclaration"); }  //$NON-NLS-1$
		    consumeInvalidInterfaceDeclaration();  
			break;
 
    case 339 : if (DEBUG) { System.out.println("BlockStatement ::= AnnotationTypeDeclaration"); }  //$NON-NLS-1$
		    consumeInvalidAnnotationTypeDeclaration();  
			break;
 
    case 340 : if (DEBUG) { System.out.println("BlockStatement ::= EnumDeclaration"); }  //$NON-NLS-1$
		    consumeInvalidEnumDeclaration();  
			break;
 
    case 341 : if (DEBUG) { System.out.println("LocalVariableDeclarationStatement ::=..."); }  //$NON-NLS-1$
		    consumeLocalVariableDeclarationStatement();  
			break;
 
    case 342 : if (DEBUG) { System.out.println("LocalVariableDeclaration ::= Type PushModifiers..."); }  //$NON-NLS-1$
		    consumeLocalVariableDeclaration();  
			break;
 
    case 343 : if (DEBUG) { System.out.println("LocalVariableDeclaration ::= Modifiers Type..."); }  //$NON-NLS-1$
		    consumeLocalVariableDeclaration();  
			break;
 
    case 344 : if (DEBUG) { System.out.println("PushModifiers ::="); }  //$NON-NLS-1$
		    consumePushModifiers();  
			break;
 
    case 345 : if (DEBUG) { System.out.println("PushModifiersForHeader ::="); }  //$NON-NLS-1$
		    consumePushModifiersForHeader();  
			break;
 
    case 346 : if (DEBUG) { System.out.println("PushRealModifiers ::="); }  //$NON-NLS-1$
		    consumePushRealModifiers();  
			break;
 
    case 370 : if (DEBUG) { System.out.println("EmptyStatement ::= SEMICOLON"); }  //$NON-NLS-1$
		    consumeEmptyStatement();  
			break;
 
    case 371 : if (DEBUG) { System.out.println("LabeledStatement ::= Label COLON Statement"); }  //$NON-NLS-1$
		    consumeStatementLabel() ;  
			break;
 
    case 372 : if (DEBUG) { System.out.println("LabeledStatementNoShortIf ::= Label COLON..."); }  //$NON-NLS-1$
		    consumeStatementLabel() ;  
			break;
 
    case 373 : if (DEBUG) { System.out.println("Label ::= Identifier"); }  //$NON-NLS-1$
		    consumeLabel() ;  
			break;
 
     case 374 : if (DEBUG) { System.out.println("ExpressionStatement ::= StatementExpression SEMICOLON"); }  //$NON-NLS-1$
		    consumeExpressionStatement();  
			break;
 
    case 383 : if (DEBUG) { System.out.println("IfThenStatement ::= if LPAREN Expression RPAREN Statement"); }  //$NON-NLS-1$
		    consumeStatementIfNoElse();  
			break;
 
    case 384 : if (DEBUG) { System.out.println("IfThenElseStatement ::= if LPAREN Expression RPAREN..."); }  //$NON-NLS-1$
		    consumeStatementIfWithElse();  
			break;
 
    case 385 : if (DEBUG) { System.out.println("IfThenElseStatementNoShortIf ::= if LPAREN Expression..."); }  //$NON-NLS-1$
		    consumeStatementIfWithElse();  
			break;
 
    case 386 : if (DEBUG) { System.out.println("SwitchStatement ::= switch LPAREN Expression RPAREN..."); }  //$NON-NLS-1$
		    consumeStatementSwitch() ;  
			break;
 
    case 387 : if (DEBUG) { System.out.println("SwitchBlock ::= LBRACE RBRACE"); }  //$NON-NLS-1$
		    consumeEmptySwitchBlock() ;  
			break;
 
    case 390 : if (DEBUG) { System.out.println("SwitchBlock ::= LBRACE SwitchBlockStatements SwitchLabels"); }  //$NON-NLS-1$
		    consumeSwitchBlock() ;  
			break;
 
    case 392 : if (DEBUG) { System.out.println("SwitchBlockStatements ::= SwitchBlockStatements..."); }  //$NON-NLS-1$
		    consumeSwitchBlockStatements() ;  
			break;
 
    case 393 : if (DEBUG) { System.out.println("SwitchBlockStatement ::= SwitchLabels BlockStatements"); }  //$NON-NLS-1$
		    consumeSwitchBlockStatement() ;  
			break;
 
    case 395 : if (DEBUG) { System.out.println("SwitchLabels ::= SwitchLabels SwitchLabel"); }  //$NON-NLS-1$
		    consumeSwitchLabels() ;  
			break;
 
     case 396 : if (DEBUG) { System.out.println("SwitchLabel ::= case ConstantExpression COLON"); }  //$NON-NLS-1$
		    consumeCaseLabel();  
			break;
 
     case 397 : if (DEBUG) { System.out.println("SwitchLabel ::= default COLON"); }  //$NON-NLS-1$
		    consumeDefaultLabel();  
			break;
 
    case 398 : if (DEBUG) { System.out.println("WhileStatement ::= while LPAREN Expression RPAREN..."); }  //$NON-NLS-1$
		    consumeStatementWhile() ;  
			break;
 
    case 399 : if (DEBUG) { System.out.println("WhileStatementNoShortIf ::= while LPAREN Expression..."); }  //$NON-NLS-1$
		    consumeStatementWhile() ;  
			break;
 
    case 400 : if (DEBUG) { System.out.println("DoStatement ::= do Statement while LPAREN Expression..."); }  //$NON-NLS-1$
		    consumeStatementDo() ;  
			break;
 
    case 401 : if (DEBUG) { System.out.println("ForStatement ::= for LPAREN ForInitopt SEMICOLON..."); }  //$NON-NLS-1$
		    consumeStatementFor() ;  
			break;
 
    case 402 : if (DEBUG) { System.out.println("ForStatementNoShortIf ::= for LPAREN ForInitopt SEMICOLON"); }  //$NON-NLS-1$
		    consumeStatementFor() ;  
			break;
 
    case 403 : if (DEBUG) { System.out.println("ForInit ::= StatementExpressionList"); }  //$NON-NLS-1$
		    consumeForInit() ;  
			break;
 
    case 407 : if (DEBUG) { System.out.println("StatementExpressionList ::= StatementExpressionList COMMA"); }  //$NON-NLS-1$
		    consumeStatementExpressionList() ;  
			break;
 
    case 408 : if (DEBUG) { System.out.println("BreakStatement ::= break SEMICOLON"); }  //$NON-NLS-1$
		    consumeStatementBreak() ;  
			break;
 
    case 409 : if (DEBUG) { System.out.println("BreakStatement ::= break Identifier SEMICOLON"); }  //$NON-NLS-1$
		    consumeStatementBreakWithLabel() ;  
			break;
 
    case 410 : if (DEBUG) { System.out.println("ContinueStatement ::= continue SEMICOLON"); }  //$NON-NLS-1$
		    consumeStatementContinue() ;  
			break;
 
    case 411 : if (DEBUG) { System.out.println("ContinueStatement ::= continue Identifier SEMICOLON"); }  //$NON-NLS-1$
		    consumeStatementContinueWithLabel() ;  
			break;
 
    case 412 : if (DEBUG) { System.out.println("ReturnStatement ::= return Expressionopt SEMICOLON"); }  //$NON-NLS-1$
		    consumeStatementReturn() ;  
			break;
 
    case 413 : if (DEBUG) { System.out.println("ThrowStatement ::= throw Expression SEMICOLON"); }  //$NON-NLS-1$
		    consumeStatementThrow();  
			break;
 
    case 414 : if (DEBUG) { System.out.println("TryStatement ::= try TryBlock Catches"); }  //$NON-NLS-1$
		    consumeStatementTry(false, false);  
			break;
 
    case 415 : if (DEBUG) { System.out.println("TryStatement ::= try TryBlock Catchesopt Finally"); }  //$NON-NLS-1$
		    consumeStatementTry(true, false);  
			break;
 
    case 417 : if (DEBUG) { System.out.println("ExitTryBlock ::="); }  //$NON-NLS-1$
		    consumeExitTryBlock();  
			break;
 
    case 419 : if (DEBUG) { System.out.println("Catches ::= Catches CatchClause"); }  //$NON-NLS-1$
		    consumeCatches();  
			break;
 
    case 420 : if (DEBUG) { System.out.println("CatchClause ::= catch LPAREN CatchFormalParameter RPAREN"); }  //$NON-NLS-1$
		    consumeStatementCatch() ;  
			break;
 
    case 422 : if (DEBUG) { System.out.println("PushLPAREN ::= LPAREN"); }  //$NON-NLS-1$
		    consumeLeftParen();  
			break;
 
    case 423 : if (DEBUG) { System.out.println("PushRPAREN ::= RPAREN"); }  //$NON-NLS-1$
		    consumeRightParen();  
			break;
 
    case 428 : if (DEBUG) { System.out.println("PrimaryNoNewArray ::= this"); }  //$NON-NLS-1$
		    consumePrimaryNoNewArrayThis();  
			break;
 
    case 429 : if (DEBUG) { System.out.println("PrimaryNoNewArray ::= PushLPAREN Expression_NotName..."); }  //$NON-NLS-1$
		    consumePrimaryNoNewArray();  
			break;
 
    case 430 : if (DEBUG) { System.out.println("PrimaryNoNewArray ::= PushLPAREN Name PushRPAREN"); }  //$NON-NLS-1$
		    consumePrimaryNoNewArrayWithName();  
			break;
 
    case 433 : if (DEBUG) { System.out.println("PrimaryNoNewArray ::= Name DOT this"); }  //$NON-NLS-1$
		    consumePrimaryNoNewArrayNameThis();  
			break;
 
    case 434 : if (DEBUG) { System.out.println("PrimaryNoNewArray ::= Name DOT super"); }  //$NON-NLS-1$
		    consumePrimaryNoNewArrayNameSuper();  
			break;
 
    case 435 : if (DEBUG) { System.out.println("PrimaryNoNewArray ::= Name DOT class"); }  //$NON-NLS-1$
		    consumePrimaryNoNewArrayName();  
			break;
 
    case 436 : if (DEBUG) { System.out.println("PrimaryNoNewArray ::= Name Dims DOT class"); }  //$NON-NLS-1$
		    consumePrimaryNoNewArrayArrayType();  
			break;
 
    case 437 : if (DEBUG) { System.out.println("PrimaryNoNewArray ::= PrimitiveType Dims DOT class"); }  //$NON-NLS-1$
		    consumePrimaryNoNewArrayPrimitiveArrayType();  
			break;
 
    case 438 : if (DEBUG) { System.out.println("PrimaryNoNewArray ::= PrimitiveType DOT class"); }  //$NON-NLS-1$
		    consumePrimaryNoNewArrayPrimitiveType();  
			break;
 
    case 444 : if (DEBUG) { System.out.println("ReferenceExpressionTypeArgumentsAndTrunk0 ::=..."); }  //$NON-NLS-1$
		    consumeReferenceExpressionTypeArgumentsAndTrunk(false);  
			break;
 
    case 445 : if (DEBUG) { System.out.println("ReferenceExpressionTypeArgumentsAndTrunk0 ::=..."); }  //$NON-NLS-1$
		    consumeReferenceExpressionTypeArgumentsAndTrunk(true);  
			break;
 
    case 446 : if (DEBUG) { System.out.println("ReferenceExpression ::= PrimitiveType Dims COLON_COLON..."); }  //$NON-NLS-1$
		    consumeReferenceExpressionTypeForm(true);  
			break;
 
    case 447 : if (DEBUG) { System.out.println("ReferenceExpression ::= Name Dimsopt COLON_COLON..."); }  //$NON-NLS-1$
		    consumeReferenceExpressionTypeForm(false);  
			break;
 
    case 448 : if (DEBUG) { System.out.println("ReferenceExpression ::= Name BeginTypeArguments..."); }  //$NON-NLS-1$
		    consumeReferenceExpressionGenericTypeForm();  
			break;
 
    case 449 : if (DEBUG) { System.out.println("ReferenceExpression ::= Primary COLON_COLON..."); }  //$NON-NLS-1$
		    consumeReferenceExpressionPrimaryForm();  
			break;
 
    case 450 : if (DEBUG) { System.out.println("ReferenceExpression ::= super COLON_COLON..."); }  //$NON-NLS-1$
		    consumeReferenceExpressionSuperForm();  
			break;
 
    case 451 : if (DEBUG) { System.out.println("NonWildTypeArgumentsopt ::="); }  //$NON-NLS-1$
		    consumeEmptyTypeArguments();  
			break;
 
    case 453 : if (DEBUG) { System.out.println("IdentifierOrNew ::= Identifier"); }  //$NON-NLS-1$
		    consumeIdentifierOrNew(false);  
			break;
 
    case 454 : if (DEBUG) { System.out.println("IdentifierOrNew ::= new"); }  //$NON-NLS-1$
		    consumeIdentifierOrNew(true);  
			break;
 
    case 455 : if (DEBUG) { System.out.println("LambdaExpression ::= LambdaParameters ARROW LambdaBody"); }  //$NON-NLS-1$
		    consumeLambdaExpression();  
			break;
 
    case 456 : if (DEBUG) { System.out.println("NestedLambda ::="); }  //$NON-NLS-1$
		    consumeNestedLambda();  
			break;
 
    case 457 : if (DEBUG) { System.out.println("LambdaParameters ::= Identifier NestedLambda"); }  //$NON-NLS-1$
		    consumeTypeElidedLambdaParameter(false);  
			break;
 
    case 463 : if (DEBUG) { System.out.println("TypeElidedFormalParameterList ::=..."); }  //$NON-NLS-1$
		    consumeFormalParameterList();  
			break;
 
    case 464 : if (DEBUG) { System.out.println("TypeElidedFormalParameter ::= Modifiersopt Identifier"); }  //$NON-NLS-1$
		    consumeTypeElidedLambdaParameter(true);  
			break;
 
    case 467 : if (DEBUG) { System.out.println("ElidedLeftBraceAndReturn ::="); }  //$NON-NLS-1$
		    consumeElidedLeftBraceAndReturn();  
			break;
 
    case 468 : if (DEBUG) { System.out.println("AllocationHeader ::= new ClassType LPAREN ArgumentListopt"); }  //$NON-NLS-1$
		    consumeAllocationHeader();  
			break;
 
    case 469 : if (DEBUG) { System.out.println("ClassInstanceCreationExpression ::= new OnlyTypeArguments"); }  //$NON-NLS-1$
		    consumeClassInstanceCreationExpressionWithTypeArguments();  
			break;
 
    case 470 : if (DEBUG) { System.out.println("ClassInstanceCreationExpression ::= new ClassType..."); }  //$NON-NLS-1$
		    consumeClassInstanceCreationExpression();  
			break;
 
    case 471 : if (DEBUG) { System.out.println("ClassInstanceCreationExpression ::= Primary DOT new..."); }  //$NON-NLS-1$
		    consumeClassInstanceCreationExpressionQualifiedWithTypeArguments() ;  
			break;
 
    case 472 : if (DEBUG) { System.out.println("ClassInstanceCreationExpression ::= Primary DOT new..."); }  //$NON-NLS-1$
		    consumeClassInstanceCreationExpressionQualified() ;  
			break;
 
    case 473 : if (DEBUG) { System.out.println("ClassInstanceCreationExpression ::=..."); }  //$NON-NLS-1$
		    consumeClassInstanceCreationExpressionQualified() ;  
			break;
 
    case 474 : if (DEBUG) { System.out.println("ClassInstanceCreationExpression ::=..."); }  //$NON-NLS-1$
		    consumeClassInstanceCreationExpressionQualifiedWithTypeArguments() ;  
			break;
 
    case 475 : if (DEBUG) { System.out.println("EnterInstanceCreationArgumentList ::="); }  //$NON-NLS-1$
		    consumeEnterInstanceCreationArgumentList();  
			break;
 
    case 476 : if (DEBUG) { System.out.println("ClassInstanceCreationExpressionName ::= Name DOT new"); }  //$NON-NLS-1$
		    consumeClassInstanceCreationExpressionName() ;  
			break;
 
    case 477 : if (DEBUG) { System.out.println("UnqualifiedClassBodyopt ::="); }  //$NON-NLS-1$
		    consumeClassBodyopt();  
			break;
 
    case 479 : if (DEBUG) { System.out.println("UnqualifiedEnterAnonymousClassBody ::="); }  //$NON-NLS-1$
		    consumeEnterAnonymousClassBody(false);  
			break;
 
    case 480 : if (DEBUG) { System.out.println("QualifiedClassBodyopt ::="); }  //$NON-NLS-1$
		    consumeClassBodyopt();  
			break;
 
    case 482 : if (DEBUG) { System.out.println("QualifiedEnterAnonymousClassBody ::="); }  //$NON-NLS-1$
		    consumeEnterAnonymousClassBody(true);  
			break;
 
    case 484 : if (DEBUG) { System.out.println("ArgumentList ::= ArgumentList COMMA Expression"); }  //$NON-NLS-1$
		    consumeArgumentList();  
			break;
 
    case 485 : if (DEBUG) { System.out.println("ArrayCreationHeader ::= new PrimitiveType..."); }  //$NON-NLS-1$
		    consumeArrayCreationHeader();  
			break;
 
    case 486 : if (DEBUG) { System.out.println("ArrayCreationHeader ::= new ClassOrInterfaceType..."); }  //$NON-NLS-1$
		    consumeArrayCreationHeader();  
			break;
 
    case 487 : if (DEBUG) { System.out.println("ArrayCreationWithoutArrayInitializer ::= new..."); }  //$NON-NLS-1$
		    consumeArrayCreationExpressionWithoutInitializer();  
			break;
 
    case 488 : if (DEBUG) { System.out.println("ArrayCreationWithArrayInitializer ::= new PrimitiveType"); }  //$NON-NLS-1$
		    consumeArrayCreationExpressionWithInitializer();  
			break;
 
    case 489 : if (DEBUG) { System.out.println("ArrayCreationWithoutArrayInitializer ::= new..."); }  //$NON-NLS-1$
		    consumeArrayCreationExpressionWithoutInitializer();  
			break;
 
    case 490 : if (DEBUG) { System.out.println("ArrayCreationWithArrayInitializer ::= new..."); }  //$NON-NLS-1$
		    consumeArrayCreationExpressionWithInitializer();  
			break;
 
    case 492 : if (DEBUG) { System.out.println("DimWithOrWithOutExprs ::= DimWithOrWithOutExprs..."); }  //$NON-NLS-1$
		    consumeDimWithOrWithOutExprs();  
			break;
 
     case 494 : if (DEBUG) { System.out.println("DimWithOrWithOutExpr ::= TypeAnnotationsopt LBRACKET..."); }  //$NON-NLS-1$
		    consumeDimWithOrWithOutExpr();  
			break;
 
     case 495 : if (DEBUG) { System.out.println("Dims ::= DimsLoop"); }  //$NON-NLS-1$
		    consumeDims();  
			break;
 
     case 498 : if (DEBUG) { System.out.println("OneDimLoop ::= LBRACKET RBRACKET"); }  //$NON-NLS-1$
		    consumeOneDimLoop(false);  
			break;
 
     case 499 : if (DEBUG) { System.out.println("OneDimLoop ::= TypeAnnotations LBRACKET RBRACKET"); }  //$NON-NLS-1$
		    consumeOneDimLoop(true);  
			break;
 
    case 500 : if (DEBUG) { System.out.println("FieldAccess ::= Primary DOT Identifier"); }  //$NON-NLS-1$
		    consumeFieldAccess(false);  
			break;
 
    case 501 : if (DEBUG) { System.out.println("FieldAccess ::= super DOT Identifier"); }  //$NON-NLS-1$
		    consumeFieldAccess(true);  
			break;
 
    case 502 : if (DEBUG) { System.out.println("MethodInvocation ::= Name LPAREN ArgumentListopt RPAREN"); }  //$NON-NLS-1$
		    consumeMethodInvocationName();  
			break;
 
    case 503 : if (DEBUG) { System.out.println("MethodInvocation ::= Name DOT OnlyTypeArguments..."); }  //$NON-NLS-1$
		    consumeMethodInvocationNameWithTypeArguments();  
			break;
 
    case 504 : if (DEBUG) { System.out.println("MethodInvocation ::= MethodInvocation LPAREN..."); }  //$NON-NLS-1$
		    consumeMethodInvocationName();  
			break;
 
    case 507 : if (DEBUG) { System.out.println("MethodInvocation ::= Primary DOT OnlyTypeArguments..."); }  //$NON-NLS-1$
		    consumeMethodInvocationPrimaryWithTypeArguments();  
			break;
 
    case 508 : if (DEBUG) { System.out.println("MethodInvocation ::= Primary DOT Identifier LPAREN..."); }  //$NON-NLS-1$
		    consumeMethodInvocationPrimary();  
			break;
 
    case 509 : if (DEBUG) { System.out.println("MethodInvocation ::= super DOT OnlyTypeArguments..."); }  //$NON-NLS-1$
		    consumeMethodInvocationSuperWithTypeArguments();  
			break;
 
    case 510 : if (DEBUG) { System.out.println("MethodInvocation ::= super DOT Identifier LPAREN..."); }  //$NON-NLS-1$
		    consumeMethodInvocationSuper();  
			break;
 
    case 511 : if (DEBUG) { System.out.println("ArrayAccess ::= Name LBRACKET Expression RBRACKET"); }  //$NON-NLS-1$
		    consumeArrayAccess(true);  
			break;
 
    case 512 : if (DEBUG) { System.out.println("ArrayAccess ::= super LBRACKET Expression RBRACKET"); }  //$NON-NLS-1$
		    consumeArrayAccess(true);  
			break;
 
    case 513 : if (DEBUG) { System.out.println("ArrayAccess ::= PrimaryNoNewArray LBRACKET Expression..."); }  //$NON-NLS-1$
		    consumeArrayAccess(false);  
			break;
 
    case 514 : if (DEBUG) { System.out.println("ArrayAccess ::= ArrayCreationWithArrayInitializer..."); }  //$NON-NLS-1$
		    consumeArrayAccess(false);  
			break;
 
    case 516 : if (DEBUG) { System.out.println("PostfixExpression ::= Name"); }  //$NON-NLS-1$
		    consumePostfixExpression();  
			break;
 
    case 519 : if (DEBUG) { System.out.println("PostIncrementExpression ::= PostfixExpression PLUS_PLUS"); }  //$NON-NLS-1$
		    consumeUnaryExpression(OperatorIds.PLUS,true);  
			break;
 
    case 520 : if (DEBUG) { System.out.println("PostDecrementExpression ::= PostfixExpression MINUS_MINUS"); }  //$NON-NLS-1$
		    consumeUnaryExpression(OperatorIds.MINUS,true);  
			break;
 
    case 521 : if (DEBUG) { System.out.println("PushPosition ::="); }  //$NON-NLS-1$
		    consumePushPosition();  
			break;
 
    case 524 : if (DEBUG) { System.out.println("UnaryExpression ::= PLUS PushPosition UnaryExpression"); }  //$NON-NLS-1$
		    consumeUnaryExpression(OperatorIds.PLUS);  
			break;
 
    case 525 : if (DEBUG) { System.out.println("UnaryExpression ::= MINUS PushPosition UnaryExpression"); }  //$NON-NLS-1$
		    consumeUnaryExpression(OperatorIds.MINUS);  
			break;
 
    case 527 : if (DEBUG) { System.out.println("PreIncrementExpression ::= PLUS_PLUS PushPosition..."); }  //$NON-NLS-1$
		    consumeUnaryExpression(OperatorIds.PLUS,false);  
			break;
 
    case 528 : if (DEBUG) { System.out.println("PreDecrementExpression ::= MINUS_MINUS PushPosition..."); }  //$NON-NLS-1$
		    consumeUnaryExpression(OperatorIds.MINUS,false);  
			break;
 
    case 530 : if (DEBUG) { System.out.println("UnaryExpressionNotPlusMinus ::= TWIDDLE PushPosition..."); }  //$NON-NLS-1$
		    consumeUnaryExpression(OperatorIds.TWIDDLE);  
			break;
 
    case 531 : if (DEBUG) { System.out.println("UnaryExpressionNotPlusMinus ::= NOT PushPosition..."); }  //$NON-NLS-1$
		    consumeUnaryExpression(OperatorIds.NOT);  
			break;
 
    case 533 : if (DEBUG) { System.out.println("CastExpression ::= PushLPAREN PrimitiveType Dimsopt..."); }  //$NON-NLS-1$
		    consumeCastExpressionWithPrimitiveType();  
			break;
 
    case 534 : if (DEBUG) { System.out.println("CastExpression ::= PushLPAREN Name..."); }  //$NON-NLS-1$
		    consumeCastExpressionWithGenericsArray();  
			break;
 
    case 535 : if (DEBUG) { System.out.println("CastExpression ::= PushLPAREN Name..."); }  //$NON-NLS-1$
		    consumeCastExpressionWithQualifiedGenericsArray();  
			break;
 
    case 536 : if (DEBUG) { System.out.println("CastExpression ::= PushLPAREN Name PushRPAREN..."); }  //$NON-NLS-1$
		    consumeCastExpressionLL1();  
			break;
 
    case 537 : if (DEBUG) { System.out.println("CastExpression ::= BeginIntersectionCast PushLPAREN..."); }  //$NON-NLS-1$
		    consumeCastExpressionLL1WithBounds();  
			break;
 
    case 538 : if (DEBUG) { System.out.println("CastExpression ::= PushLPAREN Name Dims..."); }  //$NON-NLS-1$
		    consumeCastExpressionWithNameArray();  
			break;
 
    case 539 : if (DEBUG) { System.out.println("AdditionalBoundsListOpt ::="); }  //$NON-NLS-1$
		    consumeZeroAdditionalBounds();  
			break;
 
    case 543 : if (DEBUG) { System.out.println("OnlyTypeArgumentsForCastExpression ::= OnlyTypeArguments"); }  //$NON-NLS-1$
		    consumeOnlyTypeArgumentsForCastExpression();  
			break;
 
    case 544 : if (DEBUG) { System.out.println("InsideCastExpression ::="); }  //$NON-NLS-1$
		    consumeInsideCastExpression();  
			break;
 
    case 545 : if (DEBUG) { System.out.println("InsideCastExpressionLL1 ::="); }  //$NON-NLS-1$
		    consumeInsideCastExpressionLL1();  
			break;
 
    case 546 : if (DEBUG) { System.out.println("InsideCastExpressionLL1WithBounds ::="); }  //$NON-NLS-1$
		    consumeInsideCastExpressionLL1WithBounds ();  
			break;
 
    case 547 : if (DEBUG) { System.out.println("InsideCastExpressionWithQualifiedGenerics ::="); }  //$NON-NLS-1$
		    consumeInsideCastExpressionWithQualifiedGenerics();  
			break;
 
    case 549 : if (DEBUG) { System.out.println("MultiplicativeExpression ::= MultiplicativeExpression..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.MULTIPLY);  
			break;
 
    case 550 : if (DEBUG) { System.out.println("MultiplicativeExpression ::= MultiplicativeExpression..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.DIVIDE);  
			break;
 
    case 551 : if (DEBUG) { System.out.println("MultiplicativeExpression ::= MultiplicativeExpression..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.REMAINDER);  
			break;
 
    case 553 : if (DEBUG) { System.out.println("AdditiveExpression ::= AdditiveExpression PLUS..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.PLUS);  
			break;
 
    case 554 : if (DEBUG) { System.out.println("AdditiveExpression ::= AdditiveExpression MINUS..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.MINUS);  
			break;
 
    case 556 : if (DEBUG) { System.out.println("ShiftExpression ::= ShiftExpression LEFT_SHIFT..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.LEFT_SHIFT);  
			break;
 
    case 557 : if (DEBUG) { System.out.println("ShiftExpression ::= ShiftExpression RIGHT_SHIFT..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.RIGHT_SHIFT);  
			break;
 
    case 558 : if (DEBUG) { System.out.println("ShiftExpression ::= ShiftExpression UNSIGNED_RIGHT_SHIFT"); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.UNSIGNED_RIGHT_SHIFT);  
			break;
 
    case 560 : if (DEBUG) { System.out.println("RelationalExpression ::= RelationalExpression LESS..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.LESS);  
			break;
 
    case 561 : if (DEBUG) { System.out.println("RelationalExpression ::= RelationalExpression GREATER..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.GREATER);  
			break;
 
    case 562 : if (DEBUG) { System.out.println("RelationalExpression ::= RelationalExpression LESS_EQUAL"); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.LESS_EQUAL);  
			break;
 
    case 563 : if (DEBUG) { System.out.println("RelationalExpression ::= RelationalExpression..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.GREATER_EQUAL);  
			break;
 
    case 565 : if (DEBUG) { System.out.println("InstanceofExpression ::= InstanceofExpression instanceof"); }  //$NON-NLS-1$
		    consumeInstanceOfExpression();  
			break;
 
    case 567 : if (DEBUG) { System.out.println("EqualityExpression ::= EqualityExpression EQUAL_EQUAL..."); }  //$NON-NLS-1$
		    consumeEqualityExpression(OperatorIds.EQUAL_EQUAL);  
			break;
 
    case 568 : if (DEBUG) { System.out.println("EqualityExpression ::= EqualityExpression..."); }  //$NON-NLS-1$
		    consumeEqualityExpression(OperatorIds.EQUAL_EQUAL_EQUAL);  
			break;
 
    case 569 : if (DEBUG) { System.out.println("EqualityExpression ::= EqualityExpression NOT_EQUAL_EQUAL"); }  //$NON-NLS-1$
		    consumeEqualityExpression(OperatorIds.NOT_EQUAL_EQUAL);  
			break;
 
    case 570 : if (DEBUG) { System.out.println("EqualityExpression ::= EqualityExpression NOT_EQUAL..."); }  //$NON-NLS-1$
		    consumeEqualityExpression(OperatorIds.NOT_EQUAL);  
			break;
 
    case 572 : if (DEBUG) { System.out.println("AndExpression ::= AndExpression AND EqualityExpression"); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.AND);  
			break;
 
    case 574 : if (DEBUG) { System.out.println("ExclusiveOrExpression ::= ExclusiveOrExpression XOR..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.XOR);  
			break;
 
    case 576 : if (DEBUG) { System.out.println("InclusiveOrExpression ::= InclusiveOrExpression OR..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.OR);  
			break;
 
    case 578 : if (DEBUG) { System.out.println("ConditionalAndExpression ::= ConditionalAndExpression..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.AND_AND);  
			break;
 
    case 580 : if (DEBUG) { System.out.println("ConditionalOrExpression ::= ConditionalOrExpression OR_OR"); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.OR_OR);  
			break;
 
    case 582 : if (DEBUG) { System.out.println("ConditionalExpression ::= ConditionalOrExpression..."); }  //$NON-NLS-1$
		    consumeConditionalExpression(OperatorIds.QUESTIONCOLON) ;  
			break;
 
    case 585 : if (DEBUG) { System.out.println("Assignment ::= PostfixExpression AssignmentOperator..."); }  //$NON-NLS-1$
		    consumeAssignment();  
			break;
 
    case 587 : if (DEBUG) { System.out.println("Assignment ::= InvalidArrayInitializerAssignement"); }  //$NON-NLS-1$
		    ignoreExpressionAssignment(); 
			break;
 
    case 588 : if (DEBUG) { System.out.println("AssignmentOperator ::= EQUAL"); }  //$NON-NLS-1$
		    consumeAssignmentOperator(EQUAL);  
			break;
 
    case 589 : if (DEBUG) { System.out.println("AssignmentOperator ::= MULTIPLY_EQUAL"); }  //$NON-NLS-1$
		    consumeAssignmentOperator(MULTIPLY);  
			break;
 
    case 590 : if (DEBUG) { System.out.println("AssignmentOperator ::= DIVIDE_EQUAL"); }  //$NON-NLS-1$
		    consumeAssignmentOperator(DIVIDE);  
			break;
 
    case 591 : if (DEBUG) { System.out.println("AssignmentOperator ::= REMAINDER_EQUAL"); }  //$NON-NLS-1$
		    consumeAssignmentOperator(REMAINDER);  
			break;
 
    case 592 : if (DEBUG) { System.out.println("AssignmentOperator ::= PLUS_EQUAL"); }  //$NON-NLS-1$
		    consumeAssignmentOperator(PLUS);  
			break;
 
    case 593 : if (DEBUG) { System.out.println("AssignmentOperator ::= MINUS_EQUAL"); }  //$NON-NLS-1$
		    consumeAssignmentOperator(MINUS);  
			break;
 
    case 594 : if (DEBUG) { System.out.println("AssignmentOperator ::= LEFT_SHIFT_EQUAL"); }  //$NON-NLS-1$
		    consumeAssignmentOperator(LEFT_SHIFT);  
			break;
 
    case 595 : if (DEBUG) { System.out.println("AssignmentOperator ::= RIGHT_SHIFT_EQUAL"); }  //$NON-NLS-1$
		    consumeAssignmentOperator(RIGHT_SHIFT);  
			break;
 
    case 596 : if (DEBUG) { System.out.println("AssignmentOperator ::= UNSIGNED_RIGHT_SHIFT_EQUAL"); }  //$NON-NLS-1$
		    consumeAssignmentOperator(UNSIGNED_RIGHT_SHIFT);  
			break;
 
    case 597 : if (DEBUG) { System.out.println("AssignmentOperator ::= AND_EQUAL"); }  //$NON-NLS-1$
		    consumeAssignmentOperator(AND);  
			break;
 
    case 598 : if (DEBUG) { System.out.println("AssignmentOperator ::= XOR_EQUAL"); }  //$NON-NLS-1$
		    consumeAssignmentOperator(XOR);  
			break;
 
    case 599 : if (DEBUG) { System.out.println("AssignmentOperator ::= OR_EQUAL"); }  //$NON-NLS-1$
		    consumeAssignmentOperator(OR);  
			break;
 
    case 600 : if (DEBUG) { System.out.println("Expression ::= AssignmentExpression"); }  //$NON-NLS-1$
		    consumeExpression();  
			break;
 
    case 603 : if (DEBUG) { System.out.println("Expressionopt ::="); }  //$NON-NLS-1$
		    consumeEmptyExpression();  
			break;
 
    case 608 : if (DEBUG) { System.out.println("ClassBodyDeclarationsopt ::="); }  //$NON-NLS-1$
		    consumeEmptyClassBodyDeclarationsopt();  
			break;
 
    case 609 : if (DEBUG) { System.out.println("ClassBodyDeclarationsopt ::= NestedType ComplexElement"); }  //$NON-NLS-1$
		    consumeObjectElementClassBodyDeclarationsopt();  
			break;
 
    case 610 : if (DEBUG) { System.out.println("ClassBodyDeclarationsopt ::= NestedType..."); }  //$NON-NLS-1$
		    consumeClassBodyDeclarationsopt();  
			break;
 
    case 611 : if (DEBUG) { System.out.println("ClassBodyDeclarationsopt ::= NestedType ComplexElement..."); }  //$NON-NLS-1$
		    consumeClassBodyDeclarationsoptWithObjectElement();  
			break;
 
     case 612 : if (DEBUG) { System.out.println("Modifiersopt ::="); }  //$NON-NLS-1$
		    consumeDefaultModifiers();  
			break;
 
    case 613 : if (DEBUG) { System.out.println("Modifiersopt ::= Modifiers"); }  //$NON-NLS-1$
		    consumeModifiers();  
			break;
 
    case 614 : if (DEBUG) { System.out.println("BlockStatementsopt ::="); }  //$NON-NLS-1$
		    consumeEmptyBlockStatementsopt();  
			break;
 
     case 616 : if (DEBUG) { System.out.println("Dimsopt ::="); }  //$NON-NLS-1$
		    consumeEmptyDimsopt();  
			break;
 
     case 618 : if (DEBUG) { System.out.println("ArgumentListopt ::="); }  //$NON-NLS-1$
		    consumeEmptyArgumentListopt();  
			break;
 
    case 622 : if (DEBUG) { System.out.println("FormalParameterListopt ::="); }  //$NON-NLS-1$
		    consumeFormalParameterListopt();  
			break;
 
     case 626 : if (DEBUG) { System.out.println("InterfaceMemberDeclarationsopt ::="); }  //$NON-NLS-1$
		    consumeEmptyInterfaceMemberDeclarationsopt();  
			break;
 
     case 627 : if (DEBUG) { System.out.println("InterfaceMemberDeclarationsopt ::= NestedType..."); }  //$NON-NLS-1$
		    consumeInterfaceMemberDeclarationsopt();  
			break;
 
    case 628 : if (DEBUG) { System.out.println("NestedType ::="); }  //$NON-NLS-1$
		    consumeNestedType();  
			break;

     case 629 : if (DEBUG) { System.out.println("ForInitopt ::="); }  //$NON-NLS-1$
		    consumeEmptyForInitopt();  
			break;
 
     case 631 : if (DEBUG) { System.out.println("ForUpdateopt ::="); }  //$NON-NLS-1$
		    consumeEmptyForUpdateopt();  
			break;
 
     case 635 : if (DEBUG) { System.out.println("Catchesopt ::="); }  //$NON-NLS-1$
		    consumeEmptyCatchesopt();  
			break;
 
     case 637 : if (DEBUG) { System.out.println("EnumDeclaration ::= EnumHeader EnumBody"); }  //$NON-NLS-1$
		    consumeEnumDeclaration();  
			break;
 
     case 638 : if (DEBUG) { System.out.println("EnumHeader ::= EnumHeaderName ClassHeaderImplementsopt"); }  //$NON-NLS-1$
		    consumeEnumHeader();  
			break;
 
     case 639 : if (DEBUG) { System.out.println("EnumHeaderName ::= Modifiersopt enum Identifier"); }  //$NON-NLS-1$
		    consumeEnumHeaderName();  
			break;
 
     case 640 : if (DEBUG) { System.out.println("EnumHeaderName ::= Modifiersopt enum Identifier..."); }  //$NON-NLS-1$
		    consumeEnumHeaderNameWithTypeParameters();  
			break;
 
     case 641 : if (DEBUG) { System.out.println("EnumBody ::= LBRACE EnumBodyDeclarationsopt RBRACE"); }  //$NON-NLS-1$
		    consumeEnumBodyNoConstants();  
			break;
 
     case 642 : if (DEBUG) { System.out.println("EnumBody ::= LBRACE COMMA EnumBodyDeclarationsopt RBRACE"); }  //$NON-NLS-1$
		    consumeEnumBodyNoConstants();  
			break;
 
     case 643 : if (DEBUG) { System.out.println("EnumBody ::= LBRACE EnumConstants COMMA..."); }  //$NON-NLS-1$
		    consumeEnumBodyWithConstants();  
			break;
 
     case 644 : if (DEBUG) { System.out.println("EnumBody ::= LBRACE EnumConstants..."); }  //$NON-NLS-1$
		    consumeEnumBodyWithConstants();  
			break;
 
    case 646 : if (DEBUG) { System.out.println("EnumConstants ::= EnumConstants COMMA EnumConstant"); }  //$NON-NLS-1$
		    consumeEnumConstants();  
			break;
 
    case 647 : if (DEBUG) { System.out.println("EnumConstantHeaderName ::= Modifiersopt Identifier"); }  //$NON-NLS-1$
		    consumeEnumConstantHeaderName();  
			break;
 
    case 648 : if (DEBUG) { System.out.println("EnumConstantHeader ::= EnumConstantHeaderName ForceNoDiet"); }  //$NON-NLS-1$
		    consumeEnumConstantHeader();  
			break;
 
    case 649 : if (DEBUG) { System.out.println("EnumConstant ::= EnumConstantHeader ForceNoDiet ClassBody"); }  //$NON-NLS-1$
		    consumeEnumConstantWithClassBody();  
			break;
 
    case 650 : if (DEBUG) { System.out.println("EnumConstant ::= EnumConstantHeader"); }  //$NON-NLS-1$
		    consumeEnumConstantNoClassBody();  
			break;
 
    case 651 : if (DEBUG) { System.out.println("Arguments ::= LPAREN ArgumentListopt RPAREN"); }  //$NON-NLS-1$
		    consumeArguments();  
			break;
 
    case 652 : if (DEBUG) { System.out.println("Argumentsopt ::="); }  //$NON-NLS-1$
		    consumeEmptyArguments();  
			break;
 
    case 654 : if (DEBUG) { System.out.println("EnumDeclarations ::= SEMICOLON ClassBodyDeclarationsopt"); }  //$NON-NLS-1$
		    consumeEnumDeclarations();  
			break;
 
    case 655 : if (DEBUG) { System.out.println("EnumBodyDeclarationsopt ::="); }  //$NON-NLS-1$
		    consumeEmptyEnumDeclarations();  
			break;
 
    case 657 : if (DEBUG) { System.out.println("EnhancedForStatement ::= EnhancedForStatementHeader..."); }  //$NON-NLS-1$
		    consumeEnhancedForStatement();  
			break;
 
    case 658 : if (DEBUG) { System.out.println("EnhancedForStatementNoShortIf ::=..."); }  //$NON-NLS-1$
		    consumeEnhancedForStatement();  
			break;
 
    case 659 : if (DEBUG) { System.out.println("EnhancedForStatementHeaderInit ::= for LPAREN Type..."); }  //$NON-NLS-1$
		    consumeEnhancedForStatementHeaderInit(false);  
			break;
 
    case 660 : if (DEBUG) { System.out.println("EnhancedForStatementHeaderInit ::= for LPAREN Modifiers"); }  //$NON-NLS-1$
		    consumeEnhancedForStatementHeaderInit(true);  
			break;
 
    case 661 : if (DEBUG) { System.out.println("EnhancedForStatementHeader ::=..."); }  //$NON-NLS-1$
		    consumeEnhancedForStatementHeader();  
			break;
 
    case 662 : if (DEBUG) { System.out.println("SingleStaticImportDeclaration ::=..."); }  //$NON-NLS-1$
		    consumeImportDeclaration();  
			break;
 
    case 663 : if (DEBUG) { System.out.println("SingleStaticImportDeclarationName ::= import static Name"); }  //$NON-NLS-1$
		    consumeSingleStaticImportDeclarationName();  
			break;
 
    case 664 : if (DEBUG) { System.out.println("StaticImportOnDemandDeclaration ::=..."); }  //$NON-NLS-1$
		    consumeImportDeclaration();  
			break;
 
    case 665 : if (DEBUG) { System.out.println("StaticImportOnDemandDeclarationName ::= import static..."); }  //$NON-NLS-1$
		    consumeStaticImportOnDemandDeclarationName();  
			break;
 
    case 666 : if (DEBUG) { System.out.println("TypeArguments ::= LESS TypeArgumentList1"); }  //$NON-NLS-1$
		    consumeTypeArguments();  
			break;
 
    case 667 : if (DEBUG) { System.out.println("OnlyTypeArguments ::= LESS TypeArgumentList1"); }  //$NON-NLS-1$
		    consumeOnlyTypeArguments();  
			break;
 
    case 669 : if (DEBUG) { System.out.println("TypeArgumentList1 ::= TypeArgumentList COMMA..."); }  //$NON-NLS-1$
		    consumeTypeArgumentList1();  
			break;
 
    case 671 : if (DEBUG) { System.out.println("TypeArgumentList ::= TypeArgumentList COMMA TypeArgument"); }  //$NON-NLS-1$
		    consumeTypeArgumentList();  
			break;
 
    case 672 : if (DEBUG) { System.out.println("TypeArgument ::= ReferenceType"); }  //$NON-NLS-1$
		    consumeTypeArgument();  
			break;
 
    case 676 : if (DEBUG) { System.out.println("ReferenceType1 ::= ReferenceType GREATER"); }  //$NON-NLS-1$
		    consumeReferenceType1();  
			break;
 
    case 677 : if (DEBUG) { System.out.println("ReferenceType1 ::= ClassOrInterface LESS..."); }  //$NON-NLS-1$
		    consumeTypeArgumentReferenceType1();  
			break;
 
    case 679 : if (DEBUG) { System.out.println("TypeArgumentList2 ::= TypeArgumentList COMMA..."); }  //$NON-NLS-1$
		    consumeTypeArgumentList2();  
			break;
 
    case 682 : if (DEBUG) { System.out.println("ReferenceType2 ::= ReferenceType RIGHT_SHIFT"); }  //$NON-NLS-1$
		    consumeReferenceType2();  
			break;
 
    case 683 : if (DEBUG) { System.out.println("ReferenceType2 ::= ClassOrInterface LESS..."); }  //$NON-NLS-1$
		    consumeTypeArgumentReferenceType2();  
			break;
 
    case 685 : if (DEBUG) { System.out.println("TypeArgumentList3 ::= TypeArgumentList COMMA..."); }  //$NON-NLS-1$
		    consumeTypeArgumentList3();  
			break;
 
    case 688 : if (DEBUG) { System.out.println("ReferenceType3 ::= ReferenceType UNSIGNED_RIGHT_SHIFT"); }  //$NON-NLS-1$
		    consumeReferenceType3();  
			break;
 
    case 689 : if (DEBUG) { System.out.println("Wildcard ::= TypeAnnotationsopt QUESTION"); }  //$NON-NLS-1$
		    consumeWildcard();  
			break;
 
    case 690 : if (DEBUG) { System.out.println("Wildcard ::= TypeAnnotationsopt QUESTION WildcardBounds"); }  //$NON-NLS-1$
		    consumeWildcardWithBounds();  
			break;
 
    case 691 : if (DEBUG) { System.out.println("WildcardBounds ::= extends ReferenceType"); }  //$NON-NLS-1$
		    consumeWildcardBoundsExtends();  
			break;
 
    case 692 : if (DEBUG) { System.out.println("WildcardBounds ::= super ReferenceType"); }  //$NON-NLS-1$
		    consumeWildcardBoundsSuper();  
			break;
 
    case 693 : if (DEBUG) { System.out.println("Wildcard1 ::= TypeAnnotationsopt QUESTION GREATER"); }  //$NON-NLS-1$
		    consumeWildcard1();  
			break;
 
    case 694 : if (DEBUG) { System.out.println("Wildcard1 ::= TypeAnnotationsopt QUESTION WildcardBounds1"); }  //$NON-NLS-1$
		    consumeWildcard1WithBounds();  
			break;
 
    case 695 : if (DEBUG) { System.out.println("WildcardBounds1 ::= extends ReferenceType1"); }  //$NON-NLS-1$
		    consumeWildcardBounds1Extends();  
			break;
 
    case 696 : if (DEBUG) { System.out.println("WildcardBounds1 ::= super ReferenceType1"); }  //$NON-NLS-1$
		    consumeWildcardBounds1Super();  
			break;
 
    case 697 : if (DEBUG) { System.out.println("Wildcard2 ::= TypeAnnotationsopt QUESTION RIGHT_SHIFT"); }  //$NON-NLS-1$
		    consumeWildcard2();  
			break;
 
    case 698 : if (DEBUG) { System.out.println("Wildcard2 ::= TypeAnnotationsopt QUESTION WildcardBounds2"); }  //$NON-NLS-1$
		    consumeWildcard2WithBounds();  
			break;
 
    case 699 : if (DEBUG) { System.out.println("WildcardBounds2 ::= extends ReferenceType2"); }  //$NON-NLS-1$
		    consumeWildcardBounds2Extends();  
			break;
 
    case 700 : if (DEBUG) { System.out.println("WildcardBounds2 ::= super ReferenceType2"); }  //$NON-NLS-1$
		    consumeWildcardBounds2Super();  
			break;
 
    case 701 : if (DEBUG) { System.out.println("Wildcard3 ::= TypeAnnotationsopt QUESTION..."); }  //$NON-NLS-1$
		    consumeWildcard3();  
			break;
 
    case 702 : if (DEBUG) { System.out.println("Wildcard3 ::= TypeAnnotationsopt QUESTION WildcardBounds3"); }  //$NON-NLS-1$
		    consumeWildcard3WithBounds();  
			break;
 
    case 703 : if (DEBUG) { System.out.println("WildcardBounds3 ::= extends ReferenceType3"); }  //$NON-NLS-1$
		    consumeWildcardBounds3Extends();  
			break;
 
    case 704 : if (DEBUG) { System.out.println("WildcardBounds3 ::= super ReferenceType3"); }  //$NON-NLS-1$
		    consumeWildcardBounds3Super();  
			break;
 
    case 705 : if (DEBUG) { System.out.println("TypeParameterHeader ::= TypeAnnotationsopt Identifier"); }  //$NON-NLS-1$
		    consumeTypeParameterHeader();  
			break;
 
    case 706 : if (DEBUG) { System.out.println("TypeParameters ::= LESS TypeParameterList1"); }  //$NON-NLS-1$
		    consumeTypeParameters();  
			break;
 
    case 708 : if (DEBUG) { System.out.println("TypeParameterList ::= TypeParameterList COMMA..."); }  //$NON-NLS-1$
		    consumeTypeParameterList();  
			break;
 
    case 710 : if (DEBUG) { System.out.println("TypeParameter ::= TypeParameterHeader extends..."); }  //$NON-NLS-1$
		    consumeTypeParameterWithExtends();  
			break;
 
    case 711 : if (DEBUG) { System.out.println("TypeParameter ::= TypeParameterHeader extends..."); }  //$NON-NLS-1$
		    consumeTypeParameterWithExtendsAndBounds();  
			break;
 
    case 713 : if (DEBUG) { System.out.println("AdditionalBoundList ::= AdditionalBoundList..."); }  //$NON-NLS-1$
		    consumeAdditionalBoundList();  
			break;
 
    case 714 : if (DEBUG) { System.out.println("AdditionalBound ::= AND ReferenceType"); }  //$NON-NLS-1$
		    consumeAdditionalBound();  
			break;
 
    case 716 : if (DEBUG) { System.out.println("TypeParameterList1 ::= TypeParameterList COMMA..."); }  //$NON-NLS-1$
		    consumeTypeParameterList1();  
			break;
 
    case 717 : if (DEBUG) { System.out.println("TypeParameter1 ::= TypeParameterHeader GREATER"); }  //$NON-NLS-1$
		    consumeTypeParameter1();  
			break;
 
    case 718 : if (DEBUG) { System.out.println("TypeParameter1 ::= TypeParameterHeader extends..."); }  //$NON-NLS-1$
		    consumeTypeParameter1WithExtends();  
			break;
 
    case 719 : if (DEBUG) { System.out.println("TypeParameter1 ::= TypeParameterHeader extends..."); }  //$NON-NLS-1$
		    consumeTypeParameter1WithExtendsAndBounds();  
			break;
 
    case 721 : if (DEBUG) { System.out.println("AdditionalBoundList1 ::= AdditionalBoundList..."); }  //$NON-NLS-1$
		    consumeAdditionalBoundList1();  
			break;
 
    case 722 : if (DEBUG) { System.out.println("AdditionalBound1 ::= AND ReferenceType1"); }  //$NON-NLS-1$
		    consumeAdditionalBound1();  
			break;
 
    case 728 : if (DEBUG) { System.out.println("UnaryExpression_NotName ::= PLUS PushPosition..."); }  //$NON-NLS-1$
		    consumeUnaryExpression(OperatorIds.PLUS);  
			break;
 
    case 729 : if (DEBUG) { System.out.println("UnaryExpression_NotName ::= MINUS PushPosition..."); }  //$NON-NLS-1$
		    consumeUnaryExpression(OperatorIds.MINUS);  
			break;
 
    case 732 : if (DEBUG) { System.out.println("UnaryExpressionNotPlusMinus_NotName ::= TWIDDLE..."); }  //$NON-NLS-1$
		    consumeUnaryExpression(OperatorIds.TWIDDLE);  
			break;
 
    case 733 : if (DEBUG) { System.out.println("UnaryExpressionNotPlusMinus_NotName ::= NOT PushPosition"); }  //$NON-NLS-1$
		    consumeUnaryExpression(OperatorIds.NOT);  
			break;
 
    case 736 : if (DEBUG) { System.out.println("MultiplicativeExpression_NotName ::=..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.MULTIPLY);  
			break;
 
    case 737 : if (DEBUG) { System.out.println("MultiplicativeExpression_NotName ::= Name MULTIPLY..."); }  //$NON-NLS-1$
		    consumeBinaryExpressionWithName(OperatorIds.MULTIPLY);  
			break;
 
    case 738 : if (DEBUG) { System.out.println("MultiplicativeExpression_NotName ::=..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.DIVIDE);  
			break;
 
    case 739 : if (DEBUG) { System.out.println("MultiplicativeExpression_NotName ::= Name DIVIDE..."); }  //$NON-NLS-1$
		    consumeBinaryExpressionWithName(OperatorIds.DIVIDE);  
			break;
 
    case 740 : if (DEBUG) { System.out.println("MultiplicativeExpression_NotName ::=..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.REMAINDER);  
			break;
 
    case 741 : if (DEBUG) { System.out.println("MultiplicativeExpression_NotName ::= Name REMAINDER..."); }  //$NON-NLS-1$
		    consumeBinaryExpressionWithName(OperatorIds.REMAINDER);  
			break;
 
    case 743 : if (DEBUG) { System.out.println("AdditiveExpression_NotName ::= AdditiveExpression_NotName"); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.PLUS);  
			break;
 
    case 744 : if (DEBUG) { System.out.println("AdditiveExpression_NotName ::= Name PLUS..."); }  //$NON-NLS-1$
		    consumeBinaryExpressionWithName(OperatorIds.PLUS);  
			break;
 
    case 745 : if (DEBUG) { System.out.println("AdditiveExpression_NotName ::= AdditiveExpression_NotName"); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.MINUS);  
			break;
 
    case 746 : if (DEBUG) { System.out.println("AdditiveExpression_NotName ::= Name MINUS..."); }  //$NON-NLS-1$
		    consumeBinaryExpressionWithName(OperatorIds.MINUS);  
			break;
 
    case 748 : if (DEBUG) { System.out.println("ShiftExpression_NotName ::= ShiftExpression_NotName..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.LEFT_SHIFT);  
			break;
 
    case 749 : if (DEBUG) { System.out.println("ShiftExpression_NotName ::= Name LEFT_SHIFT..."); }  //$NON-NLS-1$
		    consumeBinaryExpressionWithName(OperatorIds.LEFT_SHIFT);  
			break;
 
    case 750 : if (DEBUG) { System.out.println("ShiftExpression_NotName ::= ShiftExpression_NotName..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.RIGHT_SHIFT);  
			break;
 
    case 751 : if (DEBUG) { System.out.println("ShiftExpression_NotName ::= Name RIGHT_SHIFT..."); }  //$NON-NLS-1$
		    consumeBinaryExpressionWithName(OperatorIds.RIGHT_SHIFT);  
			break;
 
    case 752 : if (DEBUG) { System.out.println("ShiftExpression_NotName ::= ShiftExpression_NotName..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.UNSIGNED_RIGHT_SHIFT);  
			break;
 
    case 753 : if (DEBUG) { System.out.println("ShiftExpression_NotName ::= Name UNSIGNED_RIGHT_SHIFT..."); }  //$NON-NLS-1$
		    consumeBinaryExpressionWithName(OperatorIds.UNSIGNED_RIGHT_SHIFT);  
			break;
 
    case 755 : if (DEBUG) { System.out.println("RelationalExpression_NotName ::= ShiftExpression_NotName"); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.LESS);  
			break;
 
    case 756 : if (DEBUG) { System.out.println("RelationalExpression_NotName ::= Name LESS..."); }  //$NON-NLS-1$
		    consumeBinaryExpressionWithName(OperatorIds.LESS);  
			break;
 
    case 757 : if (DEBUG) { System.out.println("RelationalExpression_NotName ::= ShiftExpression_NotName"); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.GREATER);  
			break;
 
    case 758 : if (DEBUG) { System.out.println("RelationalExpression_NotName ::= Name GREATER..."); }  //$NON-NLS-1$
		    consumeBinaryExpressionWithName(OperatorIds.GREATER);  
			break;
 
    case 759 : if (DEBUG) { System.out.println("RelationalExpression_NotName ::=..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.LESS_EQUAL);  
			break;
 
    case 760 : if (DEBUG) { System.out.println("RelationalExpression_NotName ::= Name LESS_EQUAL..."); }  //$NON-NLS-1$
		    consumeBinaryExpressionWithName(OperatorIds.LESS_EQUAL);  
			break;
 
    case 761 : if (DEBUG) { System.out.println("RelationalExpression_NotName ::=..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.GREATER_EQUAL);  
			break;
 
    case 762 : if (DEBUG) { System.out.println("RelationalExpression_NotName ::= Name GREATER_EQUAL..."); }  //$NON-NLS-1$
		    consumeBinaryExpressionWithName(OperatorIds.GREATER_EQUAL);  
			break;
 
    case 764 : if (DEBUG) { System.out.println("InstanceofExpression_NotName ::= Name instanceof..."); }  //$NON-NLS-1$
		    consumeInstanceOfExpressionWithName();  
			break;
 
    case 765 : if (DEBUG) { System.out.println("InstanceofExpression_NotName ::=..."); }  //$NON-NLS-1$
		    consumeInstanceOfExpression();  
			break;
 
    case 767 : if (DEBUG) { System.out.println("EqualityExpression_NotName ::= EqualityExpression_NotName"); }  //$NON-NLS-1$
		    consumeEqualityExpression(OperatorIds.EQUAL_EQUAL);  
			break;
 
    case 768 : if (DEBUG) { System.out.println("EqualityExpression_NotName ::= Name EQUAL_EQUAL..."); }  //$NON-NLS-1$
		    consumeEqualityExpressionWithName(OperatorIds.EQUAL_EQUAL);  
			break;
 
    case 769 : if (DEBUG) { System.out.println("EqualityExpression_NotName ::= EqualityExpression_NotName"); }  //$NON-NLS-1$
		    consumeEqualityExpression(OperatorIds.NOT_EQUAL);  
			break;
 
    case 770 : if (DEBUG) { System.out.println("EqualityExpression_NotName ::= Name NOT_EQUAL..."); }  //$NON-NLS-1$
		    consumeEqualityExpressionWithName(OperatorIds.NOT_EQUAL);  
			break;
 
    case 772 : if (DEBUG) { System.out.println("AndExpression_NotName ::= AndExpression_NotName AND..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.AND);  
			break;
 
    case 773 : if (DEBUG) { System.out.println("AndExpression_NotName ::= Name AND EqualityExpression"); }  //$NON-NLS-1$
		    consumeBinaryExpressionWithName(OperatorIds.AND);  
			break;
 
    case 775 : if (DEBUG) { System.out.println("ExclusiveOrExpression_NotName ::=..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.XOR);  
			break;
 
    case 776 : if (DEBUG) { System.out.println("ExclusiveOrExpression_NotName ::= Name XOR AndExpression"); }  //$NON-NLS-1$
		    consumeBinaryExpressionWithName(OperatorIds.XOR);  
			break;
 
    case 778 : if (DEBUG) { System.out.println("InclusiveOrExpression_NotName ::=..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.OR);  
			break;
 
    case 779 : if (DEBUG) { System.out.println("InclusiveOrExpression_NotName ::= Name OR..."); }  //$NON-NLS-1$
		    consumeBinaryExpressionWithName(OperatorIds.OR);  
			break;
 
    case 781 : if (DEBUG) { System.out.println("ConditionalAndExpression_NotName ::=..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.AND_AND);  
			break;
 
    case 782 : if (DEBUG) { System.out.println("ConditionalAndExpression_NotName ::= Name AND_AND..."); }  //$NON-NLS-1$
		    consumeBinaryExpressionWithName(OperatorIds.AND_AND);  
			break;
 
    case 784 : if (DEBUG) { System.out.println("ConditionalOrExpression_NotName ::=..."); }  //$NON-NLS-1$
		    consumeBinaryExpression(OperatorIds.OR_OR);  
			break;
 
    case 785 : if (DEBUG) { System.out.println("ConditionalOrExpression_NotName ::= Name OR_OR..."); }  //$NON-NLS-1$
		    consumeBinaryExpressionWithName(OperatorIds.OR_OR);  
			break;
 
    case 787 : if (DEBUG) { System.out.println("ConditionalExpression_NotName ::=..."); }  //$NON-NLS-1$
		    consumeConditionalExpression(OperatorIds.QUESTIONCOLON) ;  
			break;
 
    case 788 : if (DEBUG) { System.out.println("ConditionalExpression_NotName ::= Name QUESTION..."); }  //$NON-NLS-1$
		    consumeConditionalExpressionWithName(OperatorIds.QUESTIONCOLON) ;  
			break;
 
    case 792 : if (DEBUG) { System.out.println("AnnotationTypeDeclarationHeaderName ::= Modifiers AT..."); }  //$NON-NLS-1$
		    consumeAnnotationTypeDeclarationHeaderName() ;  
			break;
 
    case 793 : if (DEBUG) { System.out.println("AnnotationTypeDeclarationHeaderName ::= Modifiers AT..."); }  //$NON-NLS-1$
		    consumeAnnotationTypeDeclarationHeaderNameWithTypeParameters() ;  
			break;
 
    case 794 : if (DEBUG) { System.out.println("AnnotationTypeDeclarationHeaderName ::= AT..."); }  //$NON-NLS-1$
		    consumeAnnotationTypeDeclarationHeaderNameWithTypeParameters() ;  
			break;
 
    case 795 : if (DEBUG) { System.out.println("AnnotationTypeDeclarationHeaderName ::= AT..."); }  //$NON-NLS-1$
		    consumeAnnotationTypeDeclarationHeaderName() ;  
			break;
 
    case 796 : if (DEBUG) { System.out.println("AnnotationTypeDeclarationHeader ::=..."); }  //$NON-NLS-1$
		    consumeAnnotationTypeDeclarationHeader() ;  
			break;
 
    case 797 : if (DEBUG) { System.out.println("AnnotationTypeDeclaration ::=..."); }  //$NON-NLS-1$
		    consumeAnnotationTypeDeclaration() ;  
			break;
 
    case 799 : if (DEBUG) { System.out.println("AnnotationTypeMemberDeclarationsopt ::="); }  //$NON-NLS-1$
		    consumeEmptyAnnotationTypeMemberDeclarationsopt() ;  
			break;
 
    case 800 : if (DEBUG) { System.out.println("AnnotationTypeMemberDeclarationsopt ::= NestedType..."); }  //$NON-NLS-1$
		    consumeAnnotationTypeMemberDeclarationsopt() ;  
			break;
 
    case 802 : if (DEBUG) { System.out.println("AnnotationTypeMemberDeclarations ::=..."); }  //$NON-NLS-1$
		    consumeAnnotationTypeMemberDeclarations() ;  
			break;
 
    case 803 : if (DEBUG) { System.out.println("AnnotationMethodHeaderName ::= Modifiersopt Type..."); }  //$NON-NLS-1$
		    consumeMethodHeaderNameWithTypeParameters(true);  
			break;
 
    case 804 : if (DEBUG) { System.out.println("AnnotationMethodHeaderName ::= Modifiersopt Type..."); }  //$NON-NLS-1$
		    consumeMethodHeaderName(true);  
			break;
 
    case 805 : if (DEBUG) { System.out.println("AnnotationMethodHeaderDefaultValueopt ::="); }  //$NON-NLS-1$
		    consumeEmptyMethodHeaderDefaultValue() ;  
			break;
 
    case 806 : if (DEBUG) { System.out.println("AnnotationMethodHeaderDefaultValueopt ::= DefaultValue"); }  //$NON-NLS-1$
		    consumeMethodHeaderDefaultValue();  
			break;
 
    case 807 : if (DEBUG) { System.out.println("AnnotationMethodHeader ::= AnnotationMethodHeaderName..."); }  //$NON-NLS-1$
		    consumeMethodHeader();  
			break;
 
    case 808 : if (DEBUG) { System.out.println("AnnotationTypeMemberDeclaration ::=..."); }  //$NON-NLS-1$
		    consumeAnnotationTypeMemberDeclaration() ;  
			break;
 
    case 816 : if (DEBUG) { System.out.println("AnnotationName ::= AT UnannotatableName"); }  //$NON-NLS-1$
		    consumeAnnotationName() ;  
			break;
 
    case 817 : if (DEBUG) { System.out.println("NormalAnnotation ::= AnnotationName LPAREN..."); }  //$NON-NLS-1$
		    consumeNormalAnnotation(false) ;  
			break;
 
    case 818 : if (DEBUG) { System.out.println("MemberValuePairsopt ::="); }  //$NON-NLS-1$
		    consumeEmptyMemberValuePairsopt() ;  
			break;
 
    case 821 : if (DEBUG) { System.out.println("MemberValuePairs ::= MemberValuePairs COMMA..."); }  //$NON-NLS-1$
		    consumeMemberValuePairs() ;  
			break;
 
    case 822 : if (DEBUG) { System.out.println("MemberValuePair ::= SimpleName EQUAL EnterMemberValue..."); }  //$NON-NLS-1$
		    consumeMemberValuePair() ;  
			break;
 
    case 823 : if (DEBUG) { System.out.println("EnterMemberValue ::="); }  //$NON-NLS-1$
		    consumeEnterMemberValue() ;  
			break;
 
    case 824 : if (DEBUG) { System.out.println("ExitMemberValue ::="); }  //$NON-NLS-1$
		    consumeExitMemberValue() ;  
			break;
 
    case 826 : if (DEBUG) { System.out.println("MemberValue ::= Name"); }  //$NON-NLS-1$
		    consumeMemberValueAsName() ;  
			break;
 
    case 829 : if (DEBUG) { System.out.println("MemberValueArrayInitializer ::=..."); }  //$NON-NLS-1$
		    consumeMemberValueArrayInitializer() ;  
			break;
 
    case 830 : if (DEBUG) { System.out.println("MemberValueArrayInitializer ::=..."); }  //$NON-NLS-1$
		    consumeMemberValueArrayInitializer() ;  
			break;
 
    case 831 : if (DEBUG) { System.out.println("MemberValueArrayInitializer ::=..."); }  //$NON-NLS-1$
		    consumeEmptyMemberValueArrayInitializer() ;  
			break;
 
    case 832 : if (DEBUG) { System.out.println("MemberValueArrayInitializer ::=..."); }  //$NON-NLS-1$
		    consumeEmptyMemberValueArrayInitializer() ;  
			break;
 
    case 833 : if (DEBUG) { System.out.println("EnterMemberValueArrayInitializer ::="); }  //$NON-NLS-1$
		    consumeEnterMemberValueArrayInitializer() ;  
			break;
 
    case 835 : if (DEBUG) { System.out.println("MemberValues ::= MemberValues COMMA MemberValue"); }  //$NON-NLS-1$
		    consumeMemberValues() ;  
			break;
 
    case 836 : if (DEBUG) { System.out.println("MarkerAnnotation ::= AnnotationName"); }  //$NON-NLS-1$
		    consumeMarkerAnnotation(false) ;  
			break;
 
    case 837 : if (DEBUG) { System.out.println("SingleMemberAnnotationMemberValue ::= MemberValue"); }  //$NON-NLS-1$
		    consumeSingleMemberAnnotationMemberValue() ;  
			break;
 
    case 838 : if (DEBUG) { System.out.println("SingleMemberAnnotation ::= AnnotationName LPAREN..."); }  //$NON-NLS-1$
		    consumeSingleMemberAnnotation(false) ;  
			break;
 
    case 839 : if (DEBUG) { System.out.println("RecoveryMethodHeaderName ::= Modifiersopt Type Identifier"); }  //$NON-NLS-1$
		    consumeRecoveryMethodHeaderNameWithTypeParameters();  
			break;
 
    case 840 : if (DEBUG) { System.out.println("RecoveryMethodHeaderName ::= Modifiersopt Type Identifier"); }  //$NON-NLS-1$
		    consumeRecoveryMethodHeaderName();  
			break;
 
    case 841 : if (DEBUG) { System.out.println("RecoveryMethodHeaderName ::= ModifiersWithDefault Type..."); }  //$NON-NLS-1$
		    consumeRecoveryMethodHeaderNameWithTypeParameters();  
			break;
 
    case 842 : if (DEBUG) { System.out.println("RecoveryMethodHeaderName ::= ModifiersWithDefault Type..."); }  //$NON-NLS-1$
		    consumeRecoveryMethodHeaderName();  
			break;
 
    case 843 : if (DEBUG) { System.out.println("RecoveryMethodHeader ::= RecoveryMethodHeaderName..."); }  //$NON-NLS-1$
		    consumeMethodHeader();  
			break;
 
    case 844 : if (DEBUG) { System.out.println("RecoveryMethodHeader ::= RecoveryMethodHeaderName..."); }  //$NON-NLS-1$
		    consumeMethodHeader();  
			break;
 
	}
}
