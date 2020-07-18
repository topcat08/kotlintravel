/*
 * Copyright 2019 Serge Merzliakov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.epistatic.app3

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.stage.Stage
import org.epistatic.app3.controller.ApplicationController

/**
 * An Application with multiple controllers and nested FXML
 *
 *   1) Assigning controllers to FXML at runtime
 *   2) Multiple controllers communicating via Events (via Google EventBus)
 *   3) Dragging Items from the Desktop
 *   4) Controllers can leverage dependency injection, improving testing options
 */
class Main : Application() {

   @Throws(Exception::class)
   override fun start(primaryStage: Stage) {
      val controller = ApplicationController()
      val loader = FXMLLoader(javaClass.getResource("/app3/app3.fxml"))
      loader.setController(controller)
      val root = loader.load<GridPane>()
      primaryStage.title = "Multiple Controllers Using Events"
      primaryStage.scene = Scene(root, 750.0, 550.0)
      primaryStage.show()
   }

   companion object {
      @JvmStatic
      fun main(args: Array<String>) {
         launch(Main::class.java, *args)
      }
   }
}
