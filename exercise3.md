# Third Exercise

Until now we didn't use any database for our app. But we want to allow to store images, sharing and adding tags to them.

## Step 1

We prepared a H2 database for that including some migrations built with ragtime.

You can run them with

    lein ragtime migrate
    
For the database access we are using [Yesql](https://github.com/krisajenkins/yesql).
    
## Step 2

Now we want to store an image when we converted it. We already prepared the SQL statement in `src/db/add_image.sql`.

Define the function `add-image!` using the `defqueries` macro of Yesql in `imagizer.images.processing`.

## Step 3

Call the function `add-tag!` in `imagizer.core` in the prepared function `store-image-info`. The `add-tag!` function expects the db information as first parameter.