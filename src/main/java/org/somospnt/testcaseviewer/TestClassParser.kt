package org.somospnt.testcaseviewer

import com.github.javaparser.JavaParser
import com.github.javaparser.ast.body.MethodDeclaration

object TestClassParser {

    fun parse(testClassJava: String): TestClass {
        val testClassDeclaration = JavaParser.parse(testClassJava).types.first()
        return TestClass(testClassDeclaration.nameAsString,
                testClassDeclaration.methods
                        .filter { it.annotations.any { it.nameAsString == "Test" } }
                        .map(this::methodDeclarationToTestMethod))
    }

    private fun methodDeclarationToTestMethod(method: MethodDeclaration): TestClass.TestMethod {
        val (testedMethodName, scenario, outcome) = method.nameAsString.split("_")
        return TestClass.TestMethod(testedMethodName, scenario, outcome)
    }

}
