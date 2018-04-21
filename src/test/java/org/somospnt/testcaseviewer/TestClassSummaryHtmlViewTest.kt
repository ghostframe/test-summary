package org.somospnt.testcaseviewer

import java.util.Arrays.asList
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.somospnt.testcaseviewer.Util.contentOfResource

class TestClassSummaryHtmlViewTest {

    @Test
    fun new_buildsHtml() {
        val testClass = TestClass("TestClass", asList(
                TestClass.TestMethod("method1", "withScenarioA", "returnsValueA"),
                TestClass.TestMethod("method1", "withScenarioB", "returnsValueB"),
                TestClass.TestMethod("method2", "withScenarioA", "returnsValueA")))
        assertThat(TestClassSummaryHtmlView.render(testClass)).isEqualTo(contentOfResource("testClassView.html"))
    }
}
