package com.xdd.browse

import org.gradle.api.Plugin
import org.gradle.api.Project

class VersionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // 在这里实现插件逻辑，例如添加任务或配置扩展
        project.tasks.register("compileKotlinJvm") {
            println("Hello from My Custom Plugin!")
        }
    }
}