#INTRODUCTION
### This GREP program parses through a given txt file and attempts to find matches to a provided string value. The txt file is chosen and can be changed any time by the user and the string value to match can also be changed.
### 

#Quick Start

#Implementation
###Original
####My original implementation (JavaGrepImp) utilizes loops to recursively search through folders to find files to parse through for the provided string value
###Alternate
####My alternate method (JavaGrepLambdaImp) utilizes streams and a lambda expression to improve efficiency instead of using loops wherever possible.
##Pseudocode
````matchedLines = []
for file in listFilesRecursively(rootDir)
    for line in readLines(file)
        if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
````
##Performance issues
###This application may encounter memory related problems as the entire txt file and matched lines are stored in memory to be passed around. 
###The solution to this issue would to pass around streams, with streams no data would be stored in memory at any point resulting in a much less burdensome program. Though my 2nd implementation does make use of a stream it still inserts the stream information into a list before returning instead of directly returning the stream.

#Deployment
###This project has been deployed using Github to allow user download, docker for container and PSQL initialization and Crontab to initiate data collection for host_usage
#Future Improvements
###1. Include more ready to run SQL queries for frequently requested datasets
###2. Include additional user hardware and host usage fields
###3. Respond to feedback to improve ease of use and facilitate setup for end users