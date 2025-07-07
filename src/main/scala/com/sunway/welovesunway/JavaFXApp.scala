/**
 * WeLoveSunway GUI Entry Point
 *
 *
 * Used https://youtu.be/IZCwawKILsk?si=XoMASIBUEfF1I_lF as reference
 * JavaFXApp.scala
 * This file contains the main entry point for the WeLoveSunway GUI application.
 * It sets up the JavaFX application and loads the main FXML layout.
 * @author Tan Kok Feng
 * @author Yeoh JinWei fxml gui part
 * @version 1.0
 */


package com.sunway.welovesunway

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

class JavaFXApp extends Application {
  
  override def start(primaryStage: Stage): Unit = {
    try {
      // Load FXML file
      val loader = new FXMLLoader(getClass.getResource("/com/sunway/welovesunway/MainApp.fxml"))
      val root: Parent = loader.load()
      
      // Create scene
      val scene = new Scene(root)
      
      // Configure stage
      primaryStage.setTitle("Global Development Indicator Analyzer")
      primaryStage.setScene(scene)
      primaryStage.setResizable(false)
      primaryStage.show()
      
    } catch {
      case e: Exception =>
        e.printStackTrace()
        println(s"Error loading FXML: ${e.getMessage}")
    }
  }
}

object JavaFXApp {
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[JavaFXApp], args: _*)
  }
}
