package org.somospnt.testcaseviewer

import java.util.Arrays.asList
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.Test

class TestClassSummaryTest {

    @Test
    fun testSuiteGraph() {
        val testSuite = TestClass("TestClass", asList(
                TestClass.Case("method1", "withScenarioA", "returnsValueA"),
                TestClass.Case("method1", "withScenarioB", "returnsValueB"),
                TestClass.Case("method2", "withScenarioA", "returnsValueA")))

        val testSuiteGraph = TestClassSummary.from(testSuite)
        assertThat(testSuiteGraph.name).isEqualTo(testSuite.name)

        val method1 = testSuiteGraph.methods.get(0)
        assertThat(method1.name).isEqualTo("method1")
        assertThat(method1.scenarios).extracting("description", "outcome")
                .containsExactly(tuple("withScenarioA", "returnsValueA"),
                        tuple("withScenarioB", "returnsValueB"))
        val method2 = testSuiteGraph.methods.get(1)
        assertThat(method2.name).isEqualTo("method2")
        assertThat(method2.scenarios).extracting("description", "outcome")
                .containsExactly(tuple("withScenarioA", "returnsValueA"))
    }
}
