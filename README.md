# java-web-gradle-spring.batch-thyme-dropwizard-postgres-github

## Description
A portal to classify and filter all my repos.
[Previous version](https://github.com/bearddan2000/java-web-gradle-spring-thyme-dropwizard-postgres-github)

### web service
Serves a thyme based web page which uses
bootstrap + jquery to render repos and filters.

Uses RestTemplate calls from spring to dropwizard api.

#### tech stack
- spring
  - thyme
  - RestTemplate
- generics

### api service
Generic api that returns list of filters from postgres
database. The entity uses paramertized HQL named query.

#### tech stack
- dropwizard
- postgresql drivers
- hibernate
  - named query

### db service
Postgres database with 3 tables and 1 view in schema `filter`.
Joins 2 tables to create the view.

## Tech stack
- java
  - springboot
  - dropwizard

## Design patterns
- bridge
- composit
- strategy
- template

## Microservice design patterns
- strangler
- api gateway
- schema per service
- shared database

## Docker stack
- gradle:jdk11
- maven:3-openjdk-17
- postgresql:alpine

## To run
`sudo ./install.sh -u`

## To stop
`sudo ./install.sh -d`

## For help
`sudo ./install.sh -h`
