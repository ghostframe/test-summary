package org.somospnt.testcaseviewer

import org.apache.commons.io.FileUtils
import org.apache.maven.plugin.AbstractMojo
import org.apache.maven.plugins.annotations.LifecyclePhase
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter
import org.assertj.core.api.Assertions.contentOf
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path

@Mojo(name = "generate", defaultPhase = LifecyclePhase.TEST)
class TestCaseViewerMojo : AbstractMojo() {

    @Parameter(defaultValue = "\${project.build.testSourceDirectory}")
    private var testSourceDirectory: File? = null
    @Parameter(defaultValue = "\${project.build.directory}\\test-summary\\")
    private var outputDirectory: String? = null

    override fun execute() {
        Files.walk(testSourceDirectory!!.toPath())
                .map(Path::toFile)
                .filter{it.name.endsWith(".java")}
                .forEach { writeSummaryForFile(it) }
    }


    private fun writeSummaryForFile(file: File) {
        val fileRelativePath = file.relativeTo(testSourceDirectory!!).path
        val fileRelativePathWithHtmlExtension = fileRelativePath.replace(".java", ".html")
        val html = TestClassSummaryHtmlView.render(contentOf(file))
        FileUtils.write(File("$outputDirectory\\$fileRelativePathWithHtmlExtension"), html, StandardCharsets.UTF_8)
    }
}
