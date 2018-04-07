package org.somospnt.testcaseviewer

class TestClass(val name: String, val cases: List<TestClass.Case>) {

    class Case(val method: String, val scenario: String, val result: String)

}
