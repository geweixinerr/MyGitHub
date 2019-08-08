package ast;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier.Keyword;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

/**
 * @author gewx Java AST语法分析.
 * **/
public class AstTest {

	public static void main(String[] args) {
		CompilationUnit compilationUnit = new CompilationUnit();
		ClassOrInterfaceDeclaration myClass = compilationUnit.addClass("MyClass").setPublic(true);
		myClass.addField(int.class, "A_CONSTANT", Keyword.PUBLIC, Keyword.STATIC);
		myClass.addField(String.class, "name", Keyword.PRIVATE);

		String code = myClass.toString();
		System.out.println("code: " + code);
	}
}
