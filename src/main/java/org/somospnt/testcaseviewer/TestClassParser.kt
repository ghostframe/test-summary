package org.somospnt.testcaseviewer

import com.github.javaparser.JavaParser
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.body.TypeDeclaration
import java.util.stream.Collectors.toList

object TestClassParser {

    fun parse(testClassJava: String): TestClass {
        val testClass = JavaParser.parse(testClassJava).types.first()
        return TestClass(testClass.nameAsString,
                testClass.methods
                        .filter { it.annotations.any { it.nameAsString == "Test" } }
                        .map(this::methodDeclarationToTestCase))
    }

    private fun methodDeclarationToTestCase(method: MethodDeclaration): TestClass.Case {
        val (testedMethod, scenario, result) = method.nameAsString.split("_")
        return TestClass.Case(testedMethod, scenario, result)
    }

}
