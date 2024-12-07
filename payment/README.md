# payment

## Some notes
This is the first iteration the code will be later split into different files.
The PUT and DELETE APIs will be added later since this is just an iteration with rudimentary functionalities.
bun can be replaced with different runtimes such as ==npm or yarn==.

## To install dependencies:

```bash
bun install
```

To run:

```bash
bun start
```
## Postgres

Open up a terminal in the project directory and run:

```bash
docker-compose up
```
### To recreate the DB

```bash
psql -h localhost -p 5438 -U postgres -d db_payment
```
You will be prompted for the password `azerty`

```SQL
CREATE TABLE cartes_bancaires (
    idUser SERIAL PRIMARY KEY,
    codeCarte VARCHAR(16) NOT NULL UNIQUE,
    CodeSecret VARCHAR(4) NOT NULL,
    CHECK (codeCarte ~ '^[0-9]+$'),
    CHECK (CodeSecret ~ '^[0-9]+$')
);
```
The card code must be unique and it was made to be VARCHAR with length of 16 characters with a constraint of it being all integers instead of making it an integer type so that the leading zeros wouldn't be omitted, same for the secret code.

## Testings

![This is an alt text.](/assets/DB.png "DB")
![This is an alt text.](/assets/test.png "cURL Test")

This project was created using `bun init` in bun v1.1.33. [Bun](https://bun.sh) is a fast all-in-one JavaScript runtime.
