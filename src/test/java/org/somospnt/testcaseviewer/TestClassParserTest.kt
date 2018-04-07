package org.somospnt.testcaseviewer

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.somospnt.testcaseviewer.Util.contentOfResource
import java.util.Arrays.asList

class TestClassParserTest {

    @Test
    fun parse_withTestClass_returnsTestCases() {
        assertThat(TestClassParser.parse(contentOfResource("testClass.txt")))
                .isEqualToComparingFieldByFieldRecursively(TestClass("TestClass", asList(
                        TestClass.Case("method1", "withScenarioA", "returnsValueA"),
                        TestClass.Case("method1", "withScenarioB", "returnsValueB"),
                        TestClass.Case("method2", "withScenarioA", "returnsValueA")))
                )
    }

}
