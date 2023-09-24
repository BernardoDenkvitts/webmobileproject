package com.horseecommerce.project

import io.mongock.runner.springboot.EnableMongock
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableMongock
class Project1Application

fun main(args: Array<String>) {
	runApplication<Project1Application>(*args)
}
