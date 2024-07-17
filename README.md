# Coffee shop receipt service

## Getting started

### Build project

In order to build project, navigate to the root project directory and run
> mvn clean package

This will create JAR artefact in target directory, which could be executed with the following command
> java -jar receipt-service-1.0.jar "large coffee with extra milk and foamed milk, small coffee with special roast, bacon roll, orange juice"


## Assumptions

This application makes the following assumptions:

1. The currency used is CHF. 
2. Only coffee may have extras, and these extras cannot be duplicated.
3. Extras can be empty.
4. The input is always in lower case.
5. Prices are always present for the given product name.
6. If input is present, it is considered valid.
7. The customer card stamp always has two previously purchased beverage.
8. Receipt didn't consist any info about discounts, only final prices
