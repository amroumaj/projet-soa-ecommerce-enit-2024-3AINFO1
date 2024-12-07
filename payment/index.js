const express = require ('express');
const cartesbancairesRoutes = require('./cartesbancaires/routes');
const pool = require ('./db');

const port = 8085;

const app = express();

app.use(express.json());

app.get('/', async (req, res) => {
    try {
        const data = await pool.query('SELECT * FROM cartesbancaires;')
        res.status(200).send(data.rows)
    } catch (err) {
        console.log(err)
        res.sendStatus(500)
    }
})

app.post('/', async (req, res) => {
    const { codeCarte, codeSecret } = req.body
    try {
        await pool.query('INSERT INTO cartesbancaires (codeCarte, codeCecret) VALUES ($1, $2);', [codeCarte, codeSecret])
        res.status(200).send({"Successfully added card": codeCarte})
    } catch (err) {
        console.log(err)
        res.sendStatus(500)
    }
})

//receiving the Amount and sending it to the bank
/* fetch('http://localhost:8084/')
    .then(response => response.json())
    .then(data => app.get('/:data.OrderId', async (req, res) => {
                    try {
                        const amount = parseInt('data.amount')
                    } catch (err) {
                        console.log(err)
                        res.sendStatus(500)
                    }})); */

app.listen(port, () => {
    console.log(`Payment service listening on port ${port}`);
})