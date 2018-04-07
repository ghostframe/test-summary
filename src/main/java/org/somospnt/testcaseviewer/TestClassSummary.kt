package org.somospnt.testcaseviewer

class TestClassSummary(val name: String, val methods: List<TestClassSummary.Method>) {

    class Method(val name: String, val scenarios: List<Scenario>)

    class Scenario(var description: String?, var outcome: String?)

    companion object {

        fun from(testSuite: TestClass): TestClassSummary {
            return TestClassSummary(testSuite.name,
                    testSuite.cases.groupBy(TestClass.Case::method).toSortedMap().map(this::createMethod))
        }

        private fun createMethod(methodWithCases: Map.Entry<String, List<TestClass.Case>>): Method {
            return Method(methodWithCases.key,
                    methodWithCases.value.map { Scenario(it.scenario, it.result) })
        }

    }

}