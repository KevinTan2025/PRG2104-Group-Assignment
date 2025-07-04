import scala.io.StdIn.readLine

object AdvanceMain extends App {
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
    println("2. ")
    println("3. ")
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

      case _ =>
        println("Invalid choice. Please select a valid option (1/2/3).")
    }


  }
}