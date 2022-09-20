# First Lab

This project is a starter project for our first Kotlin lab. This project comes with the following dependencies wired up and ready to go:

* Spring boot [WebFlux](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html) (Spring reactive web)
* [R2DBC](https://r2dbc.io/) for persistence. This is included via the mortgage database library.
* [Flyway](https://flywaydb.org/) for database schema migrations. This is included via the mortgage database library.

It also includes a few useful testing dependencies

* [AssertJ](https://joel-costigliola.github.io/assertj/) 
* [MockK](https://mockk.io/)

## Local Setup

Because we are using the Upstart artifactory you need to have your local credentials properly configured. To do this you need to set the following environment variables:

```bash
export ARTIFACTORY_CREDS_PSW="password"
export ARTIFACTORY_CREDS_USR="user"
```

You will also want to run a postgres instance locally. To do this, we are going to use `docker-compose.yml`. To start just run the following command:

```bash
docker-compose \
    --file "./docker-compose.yml" \
    up -d
```

To stop postgres and tear down the database _entirely_ use the following command:

```bash
docker-compose \
    --file "./docker-compose.yml" \
    down \
    --volumes
```

# Problem: Moving & Box Packing

We want to build an API that helps our customer in packing their boxes! The first part of this is to create a simple box inventory solution. This is not an API design problem, so a simple REST endpoint for a box and it's contents is sufficient. The following are the recommended endpoints:

    POST /box
       Request body: { label, width, height, depth, weight }
       Response body: { boxId }
    
    POST /box/{id}/content
       Request body: { description, weight (optional), dims, isFragile }
       Response body: { contentId }

    GET /box/{boxId}
       Response body: Box + contents (you decide the fields/format!)

To exercise the spring framework we want to be able to configure the following "constants" for each environment we are deploying to:

    Database connection
    Max number items per box

## Advanced Exercise

If you have time it would be awesome of you to start to help the customer to pack their truck. For this advanced exercise you would need to allow the customer to create a truck with specific dimensions, then suggest a packing structure. The following are a few questions we might help the customer in answering:

* Can all the items in the inventory fit in the truck?
* What item(s) won't fit? (maximize for large items fitting)
* How should the customer pack their truck up?

# Step-by-step instructions

## Part 1: POST "/box"

1. Decide what your box API request and response will look like. Create those DTO's.
2. Create sample data in the test resources folder
3. Write a WebFlux test for your box POST method
   1. Test should load resources
   2. Use the `WebTestClient` to interact with the controller
   3. Write till you have a working (failing) test!
4. Fill in the content of the box create method, write the simplest code that passes
5. Write a second test that is going to force you to interact with the `BoxService`. Mock the interactions with the service. See the failing test!
6. Write the code to make the test pass!

## Part 2: Create the service

Fall behind, want to skip ahead? Stash your changes and move to the "part2" branch.

1. Create the entity and repository for your box. I recommend also creating the table/migration!
2. Write a failing test against the box service. Mock the interactions with the repository
3. Make the test pass by actually saving the entity

## Part 3: Repository test

Fall behind, want to skip ahead? Stash your changes and move to the "part3" branch.

1. Create a repository test. This won't have much mocking - it will interact with a real database.
2. Write a custom method to find all boxes by label. This should return a `Flux`. Write a test against this method to show it works!

Note that your repository tests are going to pass right away because the repository just works. If you want to see a failure you can stop postgres locally and show that the test is failing because it can't connect to postgres. The nice thing about these tests is it helps to identify places where R2DBC does something other than expected. These are particularly powerful tests when using a custom methods because it's easy to make mistakes and the compiler will never complain!

## Part 4: Configuration!

At this point, you should be able to run the code locally and it should work! Let's create a configuration class to satisfy the final requirements we had.

1. Create a configuration object (data class) that has the properties you want to configure (maximum number of items per box and/or other!)
2. Create a test for this. The easiest approach is to use a `@SpringBootTest` with test properties then autowire your properties into the test.

As with the previous integration test, this isn't going to fail to start with _unless_ you have something configured incorrectly. That said, this also provides value by revealing instances where the Spring framework is not configured correctly. It also serves to make the configuration more obvious for anyone reading through your code. (Tests are an important form of documentation!)