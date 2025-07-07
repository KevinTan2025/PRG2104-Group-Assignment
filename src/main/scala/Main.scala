package com.sunway.welovesunway

import javafx.application.Application

/**
 * Main entry point for the Global Development Indicator Analyzer GUI application.
 * This is the primary launcher that starts the JavaFX GUI interface.
 * 
 * Usage:
 *   - Run with: sbt run
 *   - Or directly: scala Main
 */
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
