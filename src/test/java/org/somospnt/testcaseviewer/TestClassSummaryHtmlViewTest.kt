package org.somospnt.testcaseviewer

import java.util.Arrays.asList
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.somospnt.testcaseviewer.Util.contentOfResource

class TestClassSummaryHtmlViewTest {

    @Test
    fun new_buildsHtml() {
        val testSuite = TestClass("TestClass", asList(
                TestClass.Case("method1", "withScenarioA", "returnsValueA"),
                TestClass.Case("method1", "withScenarioB", "returnsValueB"),
                TestClass.Case("method2", "withScenarioA", "returnsValueA")))
        assertThat(TestClassSummaryHtmlView.render(testSuite)).isEqualTo(contentOfResource("testClassView.html"))
    }
}
