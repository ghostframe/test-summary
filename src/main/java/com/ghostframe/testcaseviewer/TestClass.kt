package com.ghostframe.testcaseviewer

import com.github.javaparser.JavaParser
import com.github.javaparser.ast.body.MethodDeclaration

data class TestClass(val name: String, val testMethodNames: List<String>) {

    companion object {

        fun parse(testClassJava: String): TestClass {
            val testClassDeclaration = JavaParser.parse(testClassJava).types.first()
            return TestClass(testClassDeclaration.nameAsString,
                    testClassDeclaration.methods
                            .filter { it.annotations.any { it.nameAsString == "Test" } }
                            .map { it.nameAsString })
        }

    }
}
