package org.somospnt.testcaseviewer

import java.util.Arrays.asList
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.Test

class TestClassSummaryTest {

    @Test
    fun from_testClass_returnsSummaryGroupingScenariosByMethodName() {
        val testClass = TestClass("TestClass", asList(
                "method1_withScenarioA_returnsValueA",
                "method1_withScenarioB_returnsValueB",
                "method2_withScenarioA_returnsValueA"))

        val testClassSummary = TestClassSummary.from(testClass)
        val expectedTestClassSummary = TestClassSummary(
                testClass.name, asList(
                TestClassSummary.MethodSummary(
                        "method1", asList(
                        TestClassSummary.Scenario("withScenarioA", "returnsValueA"),
                        TestClassSummary.Scenario("withScenarioB", "returnsValueB"))
                ),
                TestClassSummary.MethodSummary(
                        "method2", asList(
                        TestClassSummary.Scenario("withScenarioA", "returnsValueA"))
                ))
        )
        assertThat(testClassSummary).isEqualTo(expectedTestClassSummary)
    }
}
