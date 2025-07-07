package com.sunway.welovesunway

import javafx.fxml.FXML
import javafx.scene.control.{Button, ComboBox, TextField}
import javafx.event.ActionEvent
import javafx.fxml.Initializable
import javafx.collections.FXCollections
import java.net.URL
import java.util.ResourceBundle
import com.sunway.welovesunway.{DataLoader, Agent}

class MainController extends Initializable {
  
  @FXML private var highestLowestCombo1: ComboBox[String] = _
  @FXML private var findbutton1: Button = _
  @FXML private var answerField1: TextField = _
  
  @FXML private var findbutton2: Button = _
  @FXML private var answerField2: TextField = _
  
  @FXML private var fromYearField: TextField = _
  @FXML private var toYearField: TextField = _
  @FXML private var findbutton3: Button = _
  @FXML private var answerField3: TextField = _
  
  @FXML private var resetButton: Button = _
  @FXML private var exitButton: Button = _
  
  // Load data and create analyzer
  private val data: Seq[com.sunway.welovesunway.GdpData] = DataLoader.loadData("src/main/resources/Global_Development_Indicators_2000_2020.csv")
  private val analyzer = new Agent(data)
  
  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    // Initialize ComboBox with options
    val options = FXCollections.observableArrayList("Highest", "Lowest")
    highestLowestCombo1.setItems(options)
    highestLowestCombo1.setValue("Highest") // 设置默认值为 "Highest"
    
    // Set default values for year fields
    fromYearField.setText("2000")
    toYearField.setText("2020")
    
    // Auto-run default queries on startup
    runDefaultQueries()
  }
  
  private def runDefaultQueries(): Unit = {
    // Auto-run Q1 with default "Highest" setting
    try {
      val result = analyzer.lifeExpectancyQuery(highest = true)
      result match {
        case Some((country, year, value)) =>
          val formattedValue = f"$value%.2f"
          answerField1.setText(s"${country.toUpperCase} achieved the highest life expectancy of ${formattedValue}% in $year")
        case None =>
          answerField1.setText("No data found for the specified criteria")
      }
    } catch {
      case e: Exception =>
        answerField1.setText(s"Error: ${e.getMessage}")
    }
    
    // Auto-run Q2
    try {
      val result = analyzer.bestHealthEducationQuery(topCountries = 1)
      if (result.nonEmpty) {
        val (country, score) = result.head
        val formattedScore = f"$score%.2f"
        answerField2.setText(s"${country.toUpperCase} performed best in Health & Education with average score: ${formattedScore}")
      } else {
        answerField2.setText("No data available to compute rankings")
      }
    } catch {
      case e: Exception =>
        answerField2.setText(s"Error: ${e.getMessage}")
    }
    
    // Auto-run Q3 with default years (2000-2020)
    try {
      val result = analyzer.forestLossQuery(fromYear = 2000, toYear = 2020, highest = true)
      result match {
        case Some((country, loss)) =>
          val formattedLoss = f"$loss%.2f"
          answerField3.setText(s"${country.toUpperCase} had the highest forest area loss of ${formattedLoss}% from 2000 to 2020")
        case None =>
          answerField3.setText("No data found for the specified year range")
      }
    } catch {
      case e: Exception =>
        answerField3.setText(s"Error: ${e.getMessage}")
    }
  }
  
  @FXML
  private def handleQ1(event: ActionEvent): Unit = {
    try {
      val selectedValue = highestLowestCombo1.getValue
      val isHighest = selectedValue == "Highest"
      
      val result = analyzer.lifeExpectancyQuery(highest = isHighest)
      
      result match {
        case Some((country, year, value)) =>
          val formattedValue = f"$value%.2f"
          val resultText = s"${country.toUpperCase} achieved the ${selectedValue.toLowerCase} life expectancy of ${formattedValue}% in $year"
          answerField1.setText(resultText)
        case None =>
          answerField1.setText("No data found for the specified criteria")
      }
    } catch {
      case e: Exception =>
        answerField1.setText(s"Error: ${e.getMessage}")
    }
  }
  
  @FXML
  private def handleQ2(event: ActionEvent): Unit = {
    try {
      val result = analyzer.bestHealthEducationQuery(topCountries = 1)
      
      if (result.nonEmpty) {
        val (country, score) = result.head
        val formattedScore = f"$score%.2f"
        answerField2.setText(s"${country.toUpperCase} performed best in Health & Education with average score: ${formattedScore}")
      } else {
        answerField2.setText("No data available to compute rankings")
      }
    } catch {
      case e: Exception =>
        answerField2.setText(s"Error: ${e.getMessage}")
    }
  }
  
  @FXML
  private def handleQ3(event: ActionEvent): Unit = {
    try {
      val fromYear = fromYearField.getText.toIntOption.getOrElse(2000)
      val toYear = toYearField.getText.toIntOption.getOrElse(2020)
      
      val result = analyzer.forestLossQuery(fromYear = fromYear, toYear = toYear, highest = true)
      
      result match {
        case Some((country, loss)) =>
          val formattedLoss = f"$loss%.2f"
          answerField3.setText(s"${country.toUpperCase} had the highest forest area loss of ${formattedLoss}% from $fromYear to $toYear")
        case None =>
          answerField3.setText("No data found for the specified year range")
      }
    } catch {
      case e: Exception =>
        answerField3.setText(s"Error: ${e.getMessage}")
    }
  }
  
  @FXML
  private def handleReset(event: ActionEvent): Unit = {
    // Clear all answer fields
    answerField1.clear()
    answerField2.clear()
    answerField3.clear()
    
    // Reset year fields to default values
    fromYearField.setText("2000")
    toYearField.setText("2020")
    
    // Reset combo box to default value
    highestLowestCombo1.setValue("Highest")
    
    // Re-run default queries after reset
    runDefaultQueries()
  }
  
  @FXML
  private def handleExit(event: ActionEvent): Unit = {
    System.exit(0)
  }
}
