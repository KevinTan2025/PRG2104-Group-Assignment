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
  }
  
  @FXML
  private def handleQ1(event: ActionEvent): Unit = {
    try {
      val selectedValue = highestLowestCombo1.getValue
      val isHighest = selectedValue == "Highest"
      
      val result = analyzer.lifeExpectancyQuery(highest = isHighest)
      
      result match {
        case Some((country, year, value)) =>
          val resultText = s"$country achieved the ${selectedValue.toLowerCase} life expectancy of ${value}% in $year"
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
        answerField2.setText(s"$country performed best in Health & Education with average score: ${score}")
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
          answerField3.setText(s"$country had the highest forest area loss of ${loss}% from $fromYear to $toYear")
        case None =>
          answerField3.setText("No data found for the specified year range")
      }
    } catch {
      case e: Exception =>
        answerField3.setText(s"Error: ${e.getMessage}")
    }
  }
  
  @FXML
  private def handleExit(event: ActionEvent): Unit = {
    System.exit(0)
  }
}
