package org.somospnt.testcaseviewer

class TestClassSummary(val className: String, val methodSummaries: List<TestClassSummary.MethodSummary>) {

    class MethodSummary(val methodName: String, val scenarios: List<Scenario>)

    class Scenario(var description: String?, var outcome: String?)

    companion object {

        fun from(testClass: TestClass): TestClassSummary {
            return TestClassSummary(testClass.name,
                    testClass.testMethods
                            .groupBy(TestClass.TestMethod::testedMethodName)
                            .toSortedMap()
                            .map(this::createMethodSummary))
        }

        private fun createMethodSummary(testedMethodNameToCases: Map.Entry<String, List<TestClass.TestMethod>>): MethodSummary {
            return MethodSummary(testedMethodNameToCases.key,
                    testedMethodNameToCases.value.map { Scenario(it.scenario, it.outcome) })
        }

    }

}