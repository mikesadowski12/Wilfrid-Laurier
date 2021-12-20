# ec-stats
Author: Mike Sadowski
Date: 2021-09-24

## About this project

This project implements the Statistics interface methods to calculate the count/min/max/mean/standard deviation from a data set (arrayList). It store all values in corresponding properties in the class.

When a data value is added (a double) to the set, the class automatically re calculates the new count/min/max/mean/standard deviation values using the new data. The function stats() can be run individually as well to compute the values.

The MyStatistics class is the driver for the ECStatistics method. This main function adds the integers 1..10000 to the data set, computes thecount/min/max/mean/standard deviation values and prints them to the console.

There are automated tests written in `JUNIT` for the classes in this project.

## Build by ANT

This project can be built using the `ANT` tool

1. run `ant clean compile` while inside this projects root directory
2. run `ant junit` while inside this projects root directory (runs automated tests, results are stored in results.txt/results.xml)
3. run ` ant run` while inside this projects root directory (runs the main program)
4. run `ant jar` while inside this projects root directory (creates the runnable ec-stats.jar file in /jar folder)
5. run `cd jar` while inside this projects root directory
6. run ` java -jar ec-stats.jar` (runs the jar program)

## Doxygen for code documentation

This project uses doxygen to generate its code documentation.

1. run `doxygen ecstats.Doxygen` while inside this projects root directory to generate the documentation
2. run `Build Doxyfile..` from within Eclipse to generate the documentation
3. Once documenation is generated, navigate to the `/HTML` directory and click `index.html`