package com.ghostframe.testcaseviewer

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import com.ghostframe.testcaseviewer.Util.contentOfResource
import java.util.Arrays.asList

class TestClassTest {

    @Test
    fun parse_withTestClass_returnsTestCases() {
        val testClassCode = contentOfResource("testClass.txt")
        val expectedTestClass = TestClass("TestClass", asList(
                "method1_withScenarioA_returnsValueA",
                "method1_withScenarioB_returnsValueB",
                "method2_withScenarioA_returnsValueA"))
        assertThat(TestClass.parse(testClassCode)).isEqualTo(expectedTestClass)
    }

}
