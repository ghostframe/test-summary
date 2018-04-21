package org.somospnt.testcaseviewer

import java.util.Arrays.asList
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.Test

class TestClassSummaryTest {

    @Test
    fun testSuiteGraph() {
        val testClass = TestClass("TestClass", asList(
                TestClass.TestMethod("method1", "withScenarioA", "returnsValueA"),
                TestClass.TestMethod("method1", "withScenarioB", "returnsValueB"),
                TestClass.TestMethod("method2", "withScenarioA", "returnsValueA")))

        val testClassSummary = TestClassSummary.from(testClass)
        assertThat(testClassSummary.className).isEqualTo(testClass.name)

        val method1 = testClassSummary.methodSummaries[0]
        assertThat(method1.methodName).isEqualTo("method1")
        assertThat(method1.scenarios).extracting("description", "outcome")
                .containsExactly(tuple("withScenarioA", "returnsValueA"),
                        tuple("withScenarioB", "returnsValueB"))
        val method2 = testClassSummary.methodSummaries[1]
        assertThat(method2.methodName).isEqualTo("method2")
        assertThat(method2.scenarios).extracting("description", "outcome")
                .containsExactly(tuple("withScenarioA", "returnsValueA"))
    }
}
