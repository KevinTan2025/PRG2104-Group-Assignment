package com.sunway.welovesunway

import scala.io.StdIn.readLine

object CLIMain extends App {
  val data = DataLoader.loadData("src/main/resources/Global_Development_Indicators_2000_2020.csv")
  val analyzer = new Agent(data)

  //Extract all available years and countries for validation later
  val allYears = data.map(_.year).distinct.sorted
  val allCountries = data.map(_.country_name).distinct.sorted

  //Check if data is empty, give warning
  if (data.isEmpty) {
    println("Error: Dataset is empty. Please check your CSV file or path.")
  } else {
    println("Welcome to the Global Development Indicator Analyzer!")
    println("What would you like to analyze?")
    println("1. Get the country/year with the highest or lowest life expectancy.")
    println("2. Rank countries based on selected health and education indicators.")
    println("3. Get the country that lost the highest or lowest forest area between selected years.")
    print("Enter your choice (1/2/3): ")

    val choice = readLine().trim

    choice match {
      //Q1: Life Expectancy
      case "1" =>
        println("Do you want the highest or lowest life expectancy?")
        print("Type 'highest' or 'lowest': ")
        val highest = readLine().trim.toLowerCase match {
          case "lowest" => false
          case _ => true
        }

        print("Filter by country? (leave blank to skip): ")
        val countryFilter = readLine().trim match {
          case "" => None
          case input if allCountries.contains(input) => Some(input)
          case input =>
            println(s"'$input' not found in dataset. Ignoring filter.")
            None
        }

        print("Filter by year? (leave blank to skip): ")
        val yearFilter = readLine().trim match {
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

        val result = analyzer.lifeExpectancyQuery(highest, countryFilter, yearFilter)
        result match {
          case Some((country, year, value)) =>
            println(f"$country had the ${if (highest) "highest" else "lowest"} life expectancy of $value%.2f%% in $year.")
          case None =>
            println("No data found for the specified filters.")
        }

      //Q2: Health & Education ranking
      case "2" =>
        print("How many top countries do you want to rank? (default is 3): ")
        val topCountries = readLine().toIntOption.getOrElse(3)

        val result = analyzer.bestHealthEducationQuery(topCountries = topCountries)
        if (result.isEmpty) {
          println("No data available to compute rankings.")
        } else {
          println(s"Top $topCountries countries in health and education:")
          result.foreach {case (country, score)=>
            println(f"$country%-30s | Average Score: $score%.2f%%")
          }
        }

      //Q3: Forest Area Loss
      case "3" =>
        println(s"Available years: ${allYears.mkString(", ")}")

        val fromYear = askYear("Enter start year: ", 2000, allYears)
        val toYear = askYear("Enter end year: ", 2020, allYears)

        println("Do you want to see the country with the highest or lowest forest ares loss?")
        print("Type 'highest' or 'lowest' (default is highest): ")
        val highest = readLine().trim.toLowerCase match {
          case "lowest" => false
          case _ => true
        }

        val result = analyzer.forestLossQuery(fromYear, toYear, highest)
        result match {
          case Some((country, loss)) =>
            println(f"$country had the ${if (highest) "highest" else "lowest"} forest ares loss of $loss%.2f%% from $fromYear to $toYear.")
          case None =>
            println("No data found or invalid year range.")
        }

      case _ =>
        println("Invalid choice. Please select a valid option (1/2/3).")
    }
  }

  // Helper function to ask for a valid year input
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
}