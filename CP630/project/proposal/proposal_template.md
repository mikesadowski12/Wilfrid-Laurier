---
title:  'CP630 project proposal<br>Soccer Outcome Predictor'
author: Mike Sadowski
date: 2021-11-29
---

## Introduction

Sports betting has been a popular activity for many sports enthousiasts over the last few decades. It is a growing hobby that can garner people large amounts of money, or lead them into copious amounts of debt. Information technology and data analysis can provide a promising way for sport betters to gain an edge over the competition in predicting the outcome of sport matches.

The primary focus of this project is to analyze previous match outcomes for soccer teams competing in the English Premier League in order to provide a more accurate estimate of which team is going to beat the other. Team results (opposing team, score, goals for, goals against) will be analyzed over past seasons (primarily the 2018-2019 season) from a publicy available dataset from `data.world`.

Although the data set used in this project is from an older season, a more up to date dataset will provide better results for outcome prediction. In addition, using more than 1 dataset will also improve the accuracy of prediction (i.e. more than 1 season of results). For the sake of this project, only 1 dataset will be analyzed for simplicity.

Weka API is used as the main tool to evaluate the match outcomes and provide statistical analysis of the results.

## Problem solving and algorithms

1. Application data
- The dataset comes from reference [1]
- Dataset contains 22 descriptive features and 381 match results (2018-2019 season)
- The HomeTeam, AwayTeam, FTHG (full time home team goals), FTAG (full time away team goals) will be used for analysis
- For example, Manchester United played Leicester City a total of 2 times, first score being 2-1 in favor of Manchester United, second score being 1-0 in favor of Manchester United. Analysis of these games shows that both times Manchester United came out on top with 3 goals for, 1 goal against, averaging 1.5 goals for in a game, and 0.5 goals against in a match against Leicester City. The prediction result would show Manchester United winning the next game against Leicester City for both home/away game with a probably score of 2-1 (rounded up).

2. Models to represent information, knowledge and patterns.
- HomeTeam: represents the home team of the match (hosting away team in their stadium)
- AwayTeam: represents the away team of the match (travelling to home team's stadium)
- FTHG (full time home team goals): represents the number of goals the home team scored
- FTAG (full time away team goals): represents the number of goals the away team scored
- AverageGoalsFor: number of goals scored, divided by total games played against that particular team (2 in a season)
- AverageGoalsAgainst: number of goals conceded, divided by total games played against that particular team (2 in a season)
- probableOutcome: computed winner based on the above statistics, along with approximate goals scored against the opposing team and approximate goals conceded against
the opposing team

3. Algorithms to solve the problems and compute the models.
- Weka provides a vast set of classification algorithms, including many of the most popular. For this project, minimum, maximum, average (mean), and standard deviation will be used to analyze the results of all the matches.
- Other possible methods of analysis that may be utilized also inlucde linear regression, KMeans, and/or decision trees

## Proposed System Design

1. System design of your solutions as an enterprise computing application.
- Login page:
- If admin user logged in:
- Admin has capability to see the "admin user" user interface
- Admin has capability to see the "standard user" user interface
- Admin has capability to save/load a dataset of their choosing from database (via "admin user" user interface)
- Admin has capability to manually input data for individual matches (via "admin user" user interface)
- Admin has capability to input a home team and an away team and retrieve statistical analysis/prediction of the result of the match (via "standard user" user interface)

- If standard user logged in:
- Standard user has capability to see the "standard user" user interface
- Standard user has capability to input a home team and an away team and retrieve statistical analysis/prediction of the result of the match (via "standard user" user interface)

- About page: information and instructions on how to use the application

2. System architecture (better use a diagram to illustrate the components and their relations.
- Web application user interface, component modules, and database I/O are to be deployed on a Wildfly Server
- Communication from front end to back end/database will be done through a RESTful API interface
- model.java: will save/load prediction model from DB (MySQL)
- all components will have java servlets to make appropriate API calls to REST API interface to send/retrieve appropriate data

3. Platform and tools to be used in the project.
- Eclipse
- MySql
- Java EE
- Weka API

## Project plan and schedule

List of tasks/milestones/check points of the project with time schedule.

| Task ID | Description   |  Due date | Lead   |
| :----:  | :------------ | :-----:   | :------: |
|  1      | Weka API integration for dataset, analysis of data, save/load model to DB (MySQL)  | Day 6 of week 12 | Mike  |
|  2      | Admin user Component development + REST API's     | Day 3 of week 13 | Mike  |
|  3      | Standard user Component development + REST API's    | Day 6 of week 13 | Mike  |
|  4      | Component integration with DB  | Day 6 of week 14 | Mike  |

## References

1. https://data.world/


## Appendices

1. Check the project [readme.txt](readme.txt) for detailed requirements and evaluation.
You need to describe and claim new features in your project, so it is good (not required) to mention some new features in the proposal. For example,

- You can introduce more security features such as password encryption and HTTPS for web component access.
- You can try domain deployment WildFly for load balancing. We only did standalone deployment.
- You can try to deploy on Spring boot based microservice component on Docker or K8S.
- You can use Big Data tool for model computing (will be covered in weeks 10-12).


2. You may use html elements and css for better presentation of your proposal. For example, this html document is generated by using [proposal_template.md](proposal_template.md) and  [proposal.css](proposal.css) and the following pandoc command

~~~
pandoc  -s  -i proposal_template.md -o proposal_template.html --css=proposal.css
~~~

When can submit your proposal in html with css and image files or the PDF file of your proposal.
