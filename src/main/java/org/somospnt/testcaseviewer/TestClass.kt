package org.somospnt.testcaseviewer

class TestClass(val name: String, val testMethods: List<TestClass.TestMethod>) {

    class TestMethod(val testedMethodName: String, val scenario: String, val outcome: String)

}
