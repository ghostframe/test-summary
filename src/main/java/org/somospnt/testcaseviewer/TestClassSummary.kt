package org.somospnt.testcaseviewer

data class TestClassSummary(val className: String, val methodSummaries: List<TestClassSummary.MethodSummary>) {

    data class MethodSummary(val methodName: String, val scenarios: List<Scenario>)

    data class Scenario(var description: String?, var outcome: String?)

    companion object {

        fun from(testClass: TestClass): TestClassSummary {
            return TestClassSummary(testClass.name,
                    testClass.testMethodNames
                            .map{TestMethodDescription(it)}
                            .groupBy {it.testedMethodName}
                            .toSortedMap()
                            .map(this::createMethodSummary))
        }

        private fun createMethodSummary(testedMethodNameToCases: Map.Entry<String, List<TestMethodDescription>>): MethodSummary {
            return MethodSummary(testedMethodNameToCases.key,
                    testedMethodNameToCases.value.map { Scenario(it.scenario, it.outcome) })
        }

    }

    private class TestMethodDescription(testMethodName: String) {
        val testedMethodName = testMethodName.split("_")[0]
        val scenario = testMethodName.split("_")[1]
        val outcome = testMethodName.split("_")[2]
    }
}