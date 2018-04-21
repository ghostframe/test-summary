package org.somospnt.testcaseviewer

data class TestClass(val name: String, val testMethods: List<TestClass.TestMethod>) {

    data class TestMethod(val testedMethodName: String, val scenario: String, val outcome: String)

}
