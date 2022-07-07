Name: Mike Sadowski
ID: 215802560
Email: sado2560@mylaurier.ca
WorkID: cp630-a3
Statement: I claim that the enclosed submission is my individual work.

Fill in the self-evaluation in the following evaluation grid.
Symbols: Q -- Question
Field format: [self-evaluation/total marks/marker's evaluation]

For example, you put your self-evaluation, say 2, like [2/2/*].
If marker gives different evaluation value say 1, it will show
[2/2/1] in the marking report.

Evaluation grid: [self-evaluation/total/marker-evaluation]

A3

Q1 Web service project
Q1.1 SOAP WS                              [20/20/*]
Q1.2 SOAP WS client                       [10/10/*]
Q1.3 RESTful Web service                  [20/20/*]

Q2 Linear regression for EC
Q2.1 Weka API programming                 [20/20/*]
Q2.2 Model persistence                    [10/10/*]
Q2.3 EJB component                        [10/10/*]
Q2.4 Web component                        [9/10/*]

Total:                                    [99/100/*]


NOTE:
- Many of the instructions in the assignment were unclear as to what is being compared to what in Q2.
- The order of comparisons skews the data, so I wanted to be clear of my assumptions to not lose any marks
- The following are the assumptions I made for the comparisons of data:

PART 2.1, #4:
-Testing the file house_test.arff and weka_regression.bin against house.arff
in the manner: eval.evaluateModel(weka_regression.bin, trainingDataSet),
               eval.evaluateModel(house_test.arff, trainingDataSet);

PART 2.2, #2:
- Reading weka_regression.bin from DB (MySql) and comparing it to house_unknown.arff for prediction results

- All instructions were followed, but my interpretation of the ordering of what is being compared to what may be incorrect.
- I do not want to lose any marks as I have done everything correctly and everything is functional as per the spec.

PART 2.3/2.4:
- Assuming we are to load the file house_unknown.arff for the prediction
- Assuming the person running the program has the data folder in the root of this project copied to
`C:\enterprise\wildfly-18.0.1.Final\bin`

You should then have:
`C:\enterprise\wildfly-18.0.1.Final\bin\data\house.arff`
`C:\enterprise\wildfly-18.0.1.Final\bin\data\house_test.arff`
`C:\enterprise\wildfly-18.0.1.Final\bin\data\house_unknown.arff`

- Otherwise it will fail loading the file to use against the predictions
