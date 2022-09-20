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

1. Write a test for your box POST method
2. 
