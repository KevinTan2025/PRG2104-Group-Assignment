/**
 * WeLoveSunway API GUI Entry Point
 *
 *
 * Used https://youtu.be/IZCwawKILsk?si=XoMASIBUEfF1I_lF as reference
 * Main.scala
 * This file contains the main entry point for the WeLoveSunway application.
 * It handles data loading and query execution.
 * @author Tan Kok Feng
 * @version 1.0
 */

package com.sunway.welovesunway

import javafx.application.Application

object Main {
  def main(args: Array[String]): Unit = {
    println("=" * 60)
    println("  Global Development Indicator Analyzer")
    println("  Starting GUI Application...")
    println("=" * 60)
    
    try {
      // Launch JavaFX Application
      Application.launch(classOf[JavaFXApp], args: _*)
    } catch {
      case e: Exception =>
        println(s"Error starting GUI application: ${e.getMessage}")
        println("Please ensure JavaFX is properly configured.")
        e.printStackTrace()
    }
  }
}
