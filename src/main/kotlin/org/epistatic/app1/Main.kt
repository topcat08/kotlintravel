/*
 * Copyright 2019
*/
    package org.epistatic.app1

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {

   @Throws(Exception::class)
   override fun start(primaryStage: Stage) {
      val root = FXMLLoader.load<Parent>(javaClass.getResource("/app1/app1.fxml"))
      primaryStage.title = "Keyword tool for TravelSource LLC [(c)2020]"
      primaryStage.scene = Scene(root)
      primaryStage.show()
		
   }

   companion object {
      @JvmStatic
      fun main(args: Array<String>) {
         launch(Main::class.java, *args)
	   
   }
   }
}

