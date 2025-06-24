import csv

input_csv = "src\\main\\resources\\Global_Development_Indicators_2000_2020.csv"
output_txt = "py\\output.txt"

with open(input_csv, newline='', encoding='utf-8') as csvfile:
    reader = csv.reader(csvfile)
    headers = next(reader)  # Only read the first row (headers)

with open(output_txt, "w", encoding="utf-8") as outfile:
    for header in headers:
        outfile.write(header + "\n")

print("Headers have been successfully extracted to output.txt")
# This script reads the first row of a CSV file and writes the headers to a text file.