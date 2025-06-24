# CSV Header Extraction Tool

## Project Overview
This is a simple Python script designed to extract column headers from a CSV file and save them to a text file.

## File Description

### run.py
This script's main functionality is to read the first row (headers) of a CSV file and write these headers to a text file.

#### Workflow:
1. **Read CSV File**: The script opens the CSV file located at `src\main\resources\Global_Development_Indicators_2000_2020.csv`
2. **Extract Headers**: Uses `csv.reader` to read the first row and obtain all column header information
3. **Write to Text File**: Writes each header to `py\output.txt` file, one per line
4. **Completion Message**: Outputs a completion message to the console

#### Technical Details:
- Uses `utf-8` encoding to ensure proper handling of special characters
- Uses `csv.reader` module for CSV file parsing
- Each header occupies one line in the output file

## Usage
1. Ensure the CSV file exists at the specified path
2. Run the script: `python run.py`
3. Check the generated `output.txt` file to view the extracted headers

## Output
- **Output File**: `py\output.txt`
- **Content**: All column headers from the CSV file, one per line
