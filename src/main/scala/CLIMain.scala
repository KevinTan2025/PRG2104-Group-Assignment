/**
 * WeLoveSunway API CLI Main
 *
 * CLIMain.scala
 * This file contains the main entry point for the WeLoveSunway CLI application.
 * It handles user interactions and query execution.
 * Used https://github.com/tototoshi/scala-csv as reference
 *
 * @author Wong Yu Xuan
 * @version 1.1
 */

package com.sunway.welovesunway

import scala.io.StdIn.readLine

object CLIMain extends App {
  val data = DataLoader.loadData("src/main/resources/Global_Development_Indicators_2000_2020.csv")
  val analyzer = new Agent(data)

  // Extract available years and countries for filtering
  val allYears = data.map(_.year).distinct.sorted
  val allCountries = data.map(_.country_name).distinct.sorted

  // Check if data is empty
  if (data.isEmpty) {
    println("Error: Dataset is empty. Please check your CSV file or path.")
  } else {
    println(Console.GREEN + "--- Welcome to the Global Development Indicator Analyzer! ---" + Console.RESET)

    // Show original 3 questions by default
    showBasicAnalysis()

    println("\nWould you like to explore more? (Type 'yes' to continue)")
    val more = readLine().trim.toLowerCase

    if (more == "yes") {
      var continue = true
      while (continue) {
        println("\nWhat would you like to analyze?")
        println("1. Get the country/year with the highest or lowest life expectancy.")
        println("2. Rank countries based on selected health and education indicators.")
        println("3. Get the country that lost the highest or lowest forest area between selected years.")
        println("Type 'exit' to quit.")
        print("Enter your choice (1/2/3): ")

        readLine().trim.toLowerCase match {
          case "1" =>
            val highest = askHighestOrLowest()
            val countryFilter = askCountryFilter(allCountries)
            val yearFilter = askYearFilter(allYears)

            val result = analyzer.lifeExpectancyQuery(highest, countryFilter, yearFilter)
            result match {
              case Some((country, year, value)) =>
                println(f"$country had the ${if (highest) "highest" else "lowest"} life expectancy of $value%.2f in $year.")
              case None =>
                println("No data found for the specified filters.")
            }

          case "2" =>
            print("\nHow many top countries do you want to rank? (default is 3): ")
            val topCountries = readLine().toIntOption.getOrElse(3)

            val result = analyzer.bestHealthEducationQuery(topCountries = topCountries)
            if (result.isEmpty) {
              println("No data available to compute rankings.")
            } else {
              println(s"Top $topCountries countries in health and education:")
              result.foreach { case (country, score) =>
                println(f"$country%-30s | Average Score: $score%.2f")
              }
            }

          case "3" =>
            println(s"\nAvailable years: ${allYears.mkString(", ")}")
            val fromYear = askYear("Enter start year: ", 2000, allYears)
            val toYear = askYear("Enter end year: ", 2020, allYears)
            val highest = askHighestOrLowest()

            val result = analyzer.forestLossQuery(fromYear, toYear, highest)
            result match {
              case Some((country, loss)) =>
                println(f"$country had the ${if (highest) "highest" else "lowest"} forest area loss of $loss%.2f from $fromYear to $toYear.")
              case None =>
                println("No data found or invalid year range.")
            }

          case "exit" =>
            continue = false
            println("Thank you for using the Global Development Indicator Analyzer! Goodbye.")

          case _ =>
            println("Invalid choice. Please select a valid option (1/2/3) or type 'exit' to quit.")
        }
      }
    } else {
      println("Thank you for using the Global Development Indicator Analyzer! Goodbye!")
    }
  }

  //Function to Show Basic 3 Analysis Answers
  def showBasicAnalysis(): Unit = {
    println(Console.BLUE + "Basic Analysis of Global Development Indicators:" + Console.RESET)

    // Q1
    analyzer.lifeExpectancyQuery(highest = true) match {
      case Some((country, year, value)) =>
        println("1. Which country had achieved the highest life expectancy in the dataset and in which year?")
        println(f"Answer: $country had the highest life expectancy of $value%.2f in $year.")
      case None =>
        println("1. No data available for life expectancy.")
    }

    // Q2
    analyzer.bestHealthEducationQuery(topCountries = 1) match {
      case Seq((country, score)) =>
        println("\n2. Which country did well in Health & Education over the entire duration available in the dataset?")
        println(f"Answer: $country with an average score of $score%.2f.")
      case _ =>
        println("2. No data available for health and education ranking.")
    }

    // Q3
    analyzer.forestLossQuery(fromYear = 2000, toYear = 2020, highest = true) match {
      case Some((country, loss)) =>
        println("\n3. Which country had the highest loss of forest area from 2000 to 2020?")
        println(f"Answer: $country had a forest area loss of $loss%.2f from 2000 to 2020.")
      case None =>
        println("3. No data available for forest area loss.")
    }
  }

  //Helper function to ask for a valid year input
  def askYear(prompt: String, default: Int, validYears: Seq[Int]): Int = {
    print(prompt)
    val input = readLine().trim
    input.toIntOption match {
      case Some(year) if validYears.contains(year) => year
      case Some(year) =>
        println(s"Year $year not found. Using default $default.")
        default
      case None =>
        println(s"Invalid input. Using default $default.")
        default
    }
  }

  //Helper function to ask for "highest" or "lowest"
  def askHighestOrLowest(default: Boolean = true): Boolean = {
    println("\nDo you want the highest or lowest?")
    print("Type 'highest' or 'lowest' (default is highest): ")
    readLine().trim.toLowerCase match {
      case "lowest" => false
      case _ => true
    }
  }

  //Helper function to ask for valid country filter
  def askCountryFilter(allCountries: Seq[String]): Option[String] = {
    print("Filter by country? (leave blank to skip): ")
    readLine().trim match {
      case "" => None
      case input if allCountries.contains(input) => Some(input)
      case input =>
        println(s"'$input' not found in dataset. Ignoring filter.")
        None
    }
  }

  //Helper function to ask for valid year filter
  def askYearFilter(allYears: Seq[Int]): Option[Int] = {
    print("Filter by year? (leave blank to skip): ")
    readLine().trim match {
      case "" => None
      case input =>
        input.toIntOption match {
          case Some(y) if allYears.contains(y) => Some(y)
          case Some(y) =>
            println(s"Year $y not in dataset. Ignoring filter.")
            None
          case None =>
            println(s"Invalid year input. Ignoring filter.")
            None
        }
    }
  }
}
