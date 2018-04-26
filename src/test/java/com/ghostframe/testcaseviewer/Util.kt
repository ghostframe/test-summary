package com.ghostframe.testcaseviewer

import java.io.File
import org.assertj.core.api.Assertions.contentOf

object Util {

    fun contentOfResource(resource: String): String {
        return contentOf(File("src/test/resources/" + resource))
    }
}
