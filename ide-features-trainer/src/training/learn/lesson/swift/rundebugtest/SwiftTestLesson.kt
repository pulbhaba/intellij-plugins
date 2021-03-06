package training.learn.lesson.swift.rundebugtest

import com.intellij.icons.AllIcons
import training.learn.LessonsBundle
import training.learn.interfaces.Module
import training.learn.lesson.kimpl.KLesson
import training.learn.lesson.kimpl.LessonContext
import training.learn.lesson.kimpl.LessonSample
import training.learn.lesson.kimpl.parseLessonSample

class SwiftTestLesson(module: Module) : KLesson("swift.rdt.test", LessonsBundle.message("swift.rdt.test.name"), module, "Swift") {

  private val sample: LessonSample = parseLessonSample("""
import UIKit

class TestExample: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        let x = 0
        let y = 50

        let tableView = UITableView()

        let header = UILabel()
        header.text = "AppCode"
        header.sizeToFit()

        tableView.frame = CGRect(x: x, y: y, width: 320, height: 400)
        tableView.tableHeaderView = header
        self.view.addSubview(tableView)
    }
}""".trimIndent())
  override val lessonContent: LessonContext.() -> Unit = {
    prepareSample(sample)

    task {
      triggers("GotoFile", "LearnProjectTests.swift")
      text(LessonsBundle.message("swift.rdt.test.prepare", code("LearnProjectTests.swift"), action("GotoFile")))
    }
    task {
      text(LessonsBundle.message("swift.rdt.test.intro"))
    }
    task {
      text(LessonsBundle.message("swift.rdt.test.intro.tests"))
    }
    task { caret(5, 5) }
    task {
      triggers("RunClass")
      text(LessonsBundle.message("swift.rdt.test.suite", action("RunClass")))
    }
    task {
      triggers("com.intellij.util.config.DumbAwareToggleInvertedBooleanProperty")
      text(LessonsBundle.message("swift.rdt.test.show", icon(AllIcons.RunConfigurations.ShowPassed)))
    }
    task { caret(17, 19) }
    task {
      triggers("RunClass")
      text(LessonsBundle.message("swift.rdt.test.single", action("RunClass")))
    }
    task { caret(13, 9) }
    task {
      triggers("ToggleLineBreakpoint", "DebugClass")
      text(LessonsBundle.message("swift.rdt.test.debug.single", action("ToggleLineBreakpoint"), action("DebugClass")))
    }
    task {
      triggers("Resume")
      text(LessonsBundle.message("swift.rdt.test.resume", action("Resume")))
    }
    task {
      triggers("Stop")
      text(LessonsBundle.message("swift.rdt.test.stop.debug", action("Stop")))
    }
    task {
      triggers("GotoFile", "LearnProjectTests.swift")
      text(LessonsBundle.message("swift.rdt.test.go.back", code("LearnProjectTests.swift"), action("GotoFile")))
    }
    task { caret(18, 97) }
    task { type("\n") }
    task { caret(19, 9) }
    task { type("\t\tXCTAssert(false)") }
    task { caret(5, 5) }
    task {
      triggers("RunClass")
      text(LessonsBundle.message("swift.rdt.test.failing", action("RunClass")))
    }
    task {
      triggers("com.intellij.util.config.DumbAwareToggleInvertedBooleanProperty")
      text(LessonsBundle.message("swift.rdt.test.filter.failed", icon(AllIcons.RunConfigurations.ShowPassed)))
    }
    task { caret(19, 21) }
    task {
      triggers("EditorDeleteLine")
      text(LessonsBundle.message("swift.rdt.test.delete.failed", code("XCTAssert(false)"), action("EditorDeleteLine")))
    }
    task {
      triggers("com.jetbrains.cidr.execution.testing.unit.AppCodeOCUnitRerunFailedTestsAction")
      text(LessonsBundle.message("swift.rdt.test.rerun.failed", icon(AllIcons.RunConfigurations.RerunFailedTests)))
    }
    task { caret(5, 5) }
    task {
      triggers("RunClass", "com.intellij.execution.testframework.ToolbarPanel\$SortByDurationAction")
      text(LessonsBundle.message("swift.rdt.test.additional", icon(AllIcons.RunConfigurations.SortbyDuration), icon(AllIcons.ObjectBrowser.Sorted), action("RunClass")))
    }
    task {
      triggers("com.intellij.execution.testframework.sm.runner.history.actions.ImportTestsFromHistoryAction")
      text(LessonsBundle.message("swift.rdt.test.history", icon(AllIcons.Vcs.History)))
    }
    task {
      text(LessonsBundle.message("swift.rdt.test.more"))
    }
  }
}