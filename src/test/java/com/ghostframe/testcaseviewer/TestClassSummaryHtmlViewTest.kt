package com.ghostframe.testcaseviewer

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import com.ghostframe.testcaseviewer.Util.contentOfResource

class TestClassSummaryHtmlViewTest {

    @Test
    fun render_shouldSupportClassWithStandardMethodNames() {
        val expectedHtml = contentOfResource("testClass.html")
        assertThat(TestClassSummaryHtmlView.render(contentOfResource("testClass.txt"))).isEqualTo(expectedHtml)
    }

    @Test
    fun render_shouldSupportClassWithNonStandardMethodNames() {
        val expectedHtml = contentOfResource("testClassNonStandardMethodNames.html")
        assertThat(TestClassSummaryHtmlView.render(contentOfResource("testClassNonStandardMethodNames.txt"))).isEqualTo(expectedHtml)
    }
}
