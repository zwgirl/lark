

public class Snippet {
	 Document doc = new Document("import java.util.List;\nclass X {}\n");
	 ASTParser parser = ASTParser.newParser(AST.JLS3);
	 parser.setSource(doc.get().toCharArray());
	 CompilationUnit cu = (CompilationUnit) parser.createAST(null);
	 cu.recordModifications();
	 AST ast = cu.getAST();
	 ImportDeclaration id = ast.newImportDeclaration();
	 id.setName(ast.newName(new String[] {"java", "util", "Set"});
	 cu.imports().add(id); // add import declaration at end
	 TextEdit edits = cu.rewrite(document, null);
	 UndoEdit undo = edits.apply(document);
	
}